<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <%@ include file="/WEB-INF/include/base.jsp"%>
    <link rel="stylesheet" href="static/css/minireset.css" />
    <link rel="stylesheet" href="static/css/common.css" />
    <link rel="stylesheet" href="static/css/cart.css" />
    <link rel="stylesheet" href="static/css/bookManger.css" />
    <script type="text/javascript">
      $(function () {
          //提示用户【是否删除Book信息】
          $(".del").click(function () {
              //confirm()
              //获取title值
              var title = $(this).prop("name");
              var yOn = confirm("确定删除【"+title+"】Book信息吗？");
              if(yOn == false){
                  return false;
              }
          });

          //实现跳转页面功能
          $("#btnPNo").click(function () {
              //获取pageNo参数
              var pNo = $("#pgNo").val();
              //请求
              location = "BookManagerServlet?method=getAllBooksByPage&pageNo="+pNo;
          });


      });
    </script>
  </head>
  <body>
    <div class="header">
      <div class="w">
        <div class="header-left">
          <a href="index.jsp">
            <img src="static/img/logo.gif" alt=""
          /></a>
          <h1>图书管理系统</h1>
        </div>
        <%@ include file="/WEB-INF/include/header.jsp"%>
      </div>
    </div>
    <div class="list">
      <div class="w">
        <div class="add">
          <a href="pages/manager/book_edit.jsp">添加图书</a>
        </div>
        <table>
          <thead>
            <tr>
              <th>图片</th>
              <th>商品名称</th>
              <th>价格</th>
              <th>作者</th>
              <th>销量</th>
              <th>库存</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="book" items="${requestScope.page.list}">
              <tr>
                <td>
                  <img src="${book.imgPath}" alt="" />
                </td>
                <td>${book.title}</td>
                <td>
                    ${book.price}
                </td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                <td>
                  <a href="BookManagerServlet?method=getBookById&bookId=${book.id}">修改</a><a name="${book.title}" href="BookManagerServlet?method=delBookById&bookId=${book.id}" class="del">删除</a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
        <div class="footer">
          <div class="footer-right">
            <a href="BookManagerServlet?method=getAllBooksByPage&pageNo=1">首页</a>&emsp;
            <c:if test="${page.pageNo>1}">
              <a href="BookManagerServlet?method=getAllBooksByPage&pageNo=${page.pageNo-1}">上一页</a>&emsp;
            </c:if>
            <c:if test="${page.pageNo<page.totalPageNo}">
              <a href="BookManagerServlet?method=getAllBooksByPage&pageNo=${page.pageNo+1}">下一页</a>&emsp;
            </c:if>
            <a href="BookManagerServlet?method=getAllBooksByPage&pageNo=${page.totalPageNo}">末页</a>&emsp;
            <span>共${requestScope.page.pageNo}/${requestScope.page.totalPageNo}页</span>
            <span>${requestScope.page.totalRecode}条记录</span>
            <span>跳转到第</span>
            <input type="text" id="pgNo" value="${requestScope.page.pageNo}" />
            <span>页</span>
            <button id="btnPNo">确定</button>
          </div>
        </div>
      </div>
    </div>
    <div class="bottom">
      <div class="w">
        <div class="top">
          <ul>
            <li>
              <a href="">
                <img src="static/img/bottom1.png" alt="" />
                <span>大咖级讲师亲自授课</span>
              </a>
            </li>
            <li>
              <a href="">
                <img src="static/img/bottom.png" alt="" />
                <span>课程为学员成长持续赋能</span>
              </a>
            </li>
            <li>
              <a href="">
                <img src="static/img/bottom2.png" alt="" />
                <span>学员真是情况大公开</span>
              </a>
            </li>
          </ul>
        </div>
        <div class="content">
          <dl>
            <dt>关于尚硅谷</dt>
            <dd>教育理念</dd>
            <!-- <dd>名师团队</dd>
            <dd>学员心声</dd> -->
          </dl>
          <dl>
            <dt>资源下载</dt>
            <dd>视频下载</dd>
            <!-- <dd>资料下载</dd>
            <dd>工具下载</dd> -->
          </dl>
          <dl>
            <dt>加入我们</dt>
            <dd>招聘岗位</dd>
            <!-- <dd>岗位介绍</dd>
            <dd>招贤纳师</dd> -->
          </dl>
          <dl>
            <dt>联系我们</dt>
            <dd>http://www.atguigu.com</dd>
            <dd></dd>
          </dl>
        </div>
      </div>
      <div class="down">
        尚硅谷书城.Copyright ©2015
      </div>
    </div>
  </body>

</html>
