<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ page
contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="/css/style.css" />
    <title>Authorisation</title>
  </head>
  <body>
    <jsp:include page="navbar.jsp" />
    <h1>SIGN IN</h1>
    <p>
      <c:if test="${message != null}">${message}</c:if>
    </p>
    <form class="loginForm" method="post" action="/login">
      <label>Login: <input type="text" name="login" /></label>
      <label>Password: <input type="password" name="password" /></label>
      <button>Sign in</button>
    </form>
    <p>
      <a href="/">
        <button>Back</button>
      </a>
    </p>
  </body>
</html>
