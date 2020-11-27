<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <%@ include file="/WEB-INF/include/base.jsp"%>
    <link rel="stylesheet" href="static/css/minireset.css" />
    <link rel="stylesheet" href="static/css/common.css" />
    <link rel="stylesheet" href="static/css/style.css" />
    <link rel="stylesheet" href="static/css/cart.css" />
    <link rel="stylesheet" href="static/css/bookManger.css" />
    <link rel="stylesheet" href="static/css/register.css" />
    <link rel="stylesheet" href="static/css/book_edit.css" />
  </head>
  <body>
    <div class="header">
      <div class="w">
        <div class="header-left">
          <a href="index.jsp">
            <img src="static/img/logo.gif" alt=""
          /></a>
          <h1>编辑图书</h1>
        </div>
        <%@ include file="/WEB-INF/include/header.jsp"%>
      </div>
    </div>
    <div class="login_banner">
      <div class="register_form">
        <form action="BookManagerServlet?method=saveOrUpdateBook" method="post">
          <input type="hidden" name="bookId" value="${requestScope.book.id}">
          <div class="form-item">
            <div>
              <label>名称:</label>
              <input value="${requestScope.book.title}" name="title" type="text" placeholder="请输入名称" />
            </div>
            <span class="errMess" style="visibility: visible;"
              >请输入正确的名称</span
            >
          </div>
          <div class="form-item">
            <div>
              <label>价格:</label>
              <input value="${requestScope.book.price}" name="price" type="number" placeholder="请输入价格" />
            </div>
            <span class="errMess">请输入正确数字</span>
          </div>
          <div class="form-item">
            <div>
              <label>作者:</label>
              <input value="${requestScope.book.author}" name="author" type="text" placeholder="请输入作者" />
            </div>
            <span class="errMess">请输入正确作者</span>
          </div>
          <div class="form-item">
            <div>
              <label>销量:</label>
              <input value="${requestScope.book.sales}" name="sales" type="number" placeholder="请输入销量" />
            </div>
            <span class="errMess">请输入正确销量</span>
          </div>
          <div class="form-item">
            <div>
              <label>库存:</label>
              <input value="${requestScope.book.stock}" name="stock" type="number" placeholder="请输入库存" />
            </div>
            <span class="errMess">请输入正确库存</span>
          </div>
          <input class="btn" type="submit" value="提交">
        </form>
      </div>
    </div>
    <div class="bottom">
      尚硅谷书城.Copyright ©2015
    </div>
  </body>
</html>
