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
    <script type="text/javascript">
      $(function () {
        $("#del").click(function () {
          var title = $(this).prop("name");
          var flag = confirm("确认删除" + title + "这本书吗？");
          if (flag == false) {
            return false;
          }
        })
        $(".count-num").change(function () {
          var bookId = $(this).attr("id");
          var newCount = $(this).val();
          var defValue = this.defaultValue;
          var newCountReg = /^\+?[1-9][0-9]*$/;
          if (newCountReg.test(newCount) == false) {
            $(this).val(defValue);
            alert("输入购物数量不合法，请重新输入");
            return false;
          }
          // location ="CartServlet?method=updateCartItemCount&bookId="+bookId+"&newCount="+newCount;
          var url = "CartServlet?method=updateCartItemCount";
          var data = {"bookId": bookId, "newCount": newCount};
          var $amount = $(this).parent().next().next();
          $.getJSON(url, data, function (rs) {
            $("#tcount").html(rs.totalCount);
            $("#tamount").html(rs.totalAmount);
            $amount.html(rs.amount);
          })
        })
        $("span[name='sub']").click(function () {
          var $countEle = $(this).next();
          var count = $countEle.val();
          var newCount = count;
          if (parseInt(count) >= 2) {
            newCount = parseInt(count) - 1;

          } else {
            alert("不可再减少");
          }
          $countEle.val(newCount);
          var bookId = $countEle.attr("id");
          // location ="CartServlet?method=updateCartItemCount&bookId="+bookId+"&newCount="+newCount;
          var url = "CartServlet?method=updateCartItemCount";
          var data = {"bookId": bookId, "newCount": newCount};
          var $amount = $(this).parent().next().next();
          $.getJSON(url, data, function (rs) {
            $("#tcount").html(rs.totalCount);
            $("#tamount").html(rs.totalAmount);
            $amount.html(rs.amount);
          })
        });
        $("span[name='add']").click(function () {
          var $countEle = $(this).prev();
          var count = $countEle.val();
          var newCount = parseInt(count) + 1;
          $countEle.val(newCount);
          var bookId = $countEle.attr("id");
          // location ="CartServlet?method=updateCartItemCount&bookId="+bookId+"&newCount="+newCount;
          var url = "CartServlet?method=updateCartItemCount";
          var data = {"bookId": bookId, "newCount": newCount};
          var $amount = $(this).parent().next().next();
          $.getJSON(url, data, function (rs) {
            $("#tcount").html(rs.totalCount);
            $("#tamount").html(rs.totalAmount);
            $amount.html(rs.amount);
          })
        })
      })
    </script>
  </head>
  <body>
    <div class="header">
      <div class="w">
        <div class="header-left">
          <a href="index.jsp">
            <img src="static/img/logo.gif" alt=""
          /></a>
          <h1>我的购物车</h1>
        </div>
        <%@ include file="/WEB-INF/include/welcome.jsp"%>
      </div>
    </div>
    <c:if test="${empty sessionScope.cart.cartItems}">
      <div style="font-size: 36px;" align="center">购物车中暂无商品，快来<a style="display: inline;color:red;" href="index.jsp">购物</a>呀！ </div>
    </c:if>
    <c:if test="${not empty sessionScope.cart.cartItems}">
    <div class="list">
      <div class="w">
        <table>
          <thead>
            <tr>
              <th>图片</th>
              <th>商品名称</th>

              <th>数量</th>
              <th>单价</th>
              <th>金额</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
          <c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
            <tr>
              <td>
                <img src="${cartItem.book.imgPath}" alt="" />
              </td>
              <td>${cartItem.book.title}</td>
              <td>
                <span class="count" name="sub">-</span>
                <input id="${cartItem.book.id}" class="count-num" type="text" value="${cartItem.count}" />
                <span class="count" name="add">+</span>
              </td>
              <td>${cartItem.book.price}</td>
              <td>${cartItem.amount}</td>
              <td><a href="CartServlet?method=delCartItemById&bookId=${cartItem.book.id}" id="del" name="${cartItem.book.title}">删除</a></td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
        <div class="footer">
          <div class="footer-left">
            <a href="CartServlet?method=clearCart" class="clear-cart">清空购物车</a>
            <a href="#">继续购物</a>
          </div>
          <div class="footer-right">
            <div>共<span id="tcount">${sessionScope.cart.totalCount}</span>件商品</div>
            <div class="total-price">总金额<span id="tamount">${sessionScope.cart.totalAmount}</span>元</div>
            <a class="pay" href="OrderServlet?method=checkout">去结账</a>
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
    </c:if>
  </body>
</html>
