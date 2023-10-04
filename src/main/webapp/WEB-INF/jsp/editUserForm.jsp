<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Edit user</title>
  </head>
  <body>
    <jsp:include page="navbar.jsp" />
    <h1>Edit user</h1>
    <form method="post" action="/users/${requestScope.user.id}/edit">
      <label for="name">Name:</label>
      <input
        id="name"
        name="name"
        type="text"
        value="${requestScope.user.name}"
      />
      <br />
      <label for="last_name">Last name:</label>
      <input
        id="last_name"
        name="lastName"
        type="text"
        value="${requestScope.user.lastName}"
      />
      <br />
      <label for="email">Email:</label>
      <input
        id="email"
        name="email"
        type="email"
        value="${requestScope.user.email}"
      />
      <br />
      <label for="login">Login:</label>
      <input
        id="login"
        name="login"
        type="text"
        value="${requestScope.user.login}"
      />
      <br />
      <label for="password">Password:</label>
      <input
        id="password"
        name="password"
        type="password"
        value="${requestScope.user.password}"
      />
      <br />
      <legend>Choose user's role</legend>
      <div>
        <input
          type="radio"
          id="ADMIN"
          name="roleDto"
          value="ADMIN"
          <c:if test="${requestScope.user.roleDto eq 'ADMIN'}">checked</c:if>
        />
        <label for="ADMIN">ADMIN</label>
      </div>
      <div>
        <input
          type="radio"
          id="MANAGER"
          name="roleDto"
          value="MANAGER"
          <c:if test="${requestScope.user.roleDto eq 'MANAGER'}">checked</c:if>
        />
        <label for="MANAGER">MANAGER</label>
      </div>
      <div>
        <input
          type="radio"
          id="CUSTOMER"
          name="roleDto"
          value="CUSTOMER"
          <c:if test="${requestScope.user.roleDto eq 'CUSTOMER'}">checked</c:if>
        />
        <label for="CUSTOMER">CUSTOMER</label>
      </div>
      <br />
      <input type="submit" value="UPDATE" />
    </form>
    <p>
      <a href="/users">
        <button>Back to all users</button>
      </a>
    </p>
  </body>
</html>
