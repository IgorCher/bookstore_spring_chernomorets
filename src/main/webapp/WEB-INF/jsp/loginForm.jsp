<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="/css/style.css" />
    <title>Login</title>
  </head>
  <body>
    <jsp:include page="navbar.jsp" />
    <h1>LOGIN</h1>
    <form class="loginForm" method="post" action="/login">
      <label>Login: <input type="text" name="login" /></label>
      <label>Password: <input type="password" name="password" /></label>
      <button>Login</button>
    </form>
    <p>
      <a href="/">
        <button>Back</button>
      </a>
    </p>
  </body>
</html>
