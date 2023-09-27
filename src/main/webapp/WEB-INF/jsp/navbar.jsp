<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ page
contentType="text/html;charset=UTF-8" %>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/css/style.css" />
    <title>Document</title>
  </head>
  <body>
    <ul class="navigation">
      <li class="buttons"><a href="/">Home</a></li>
      <li class="buttons"><a href="/books">Catalog</a></li>
      <c:if test="${sessionScope.user != null}">
        <li class="buttons"><a href="/users">Users</a></li>
        <li class="buttons"><a href="/orders">Orders</a></li>
        <li class="buttons"><a href="/books/create">Add book</a></li>
      </c:if>
      <c:if test="${sessionScope.user == null}">
        <li class="buttons" style="float: right; border-left: 1px solid #bbb">
          <a href="/users/create">Sign up</a>
        </li>
        <li class="buttons" style="float: right; border-left: 1px solid #bbb">
          <a href="/login">Sign in</a>
        </li>
      </c:if>
      <c:if test="${sessionScope.user != null}">
        <li class="buttons" style="float: right; border-left: 1px solid #bbb">
          <a href="/logout">Sign out</a>
        </li>
      </c:if>
    </ul>
  </body>
</html>
