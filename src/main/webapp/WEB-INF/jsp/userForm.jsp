<%@page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Create user</title>
  </head>
  <body>
    <jsp:include page="navbar.jsp" />
    <h1>Create new user</h1>
    <form method="post" action="/users/create">
      <label for="name">Name:</label>
      <input id="name" name="name" type="text" />
      <br />
      <label for="last_name">Last name:</label>
      <input id="last_name" name="lastName" type="text" />
      <br />
      <label for="email">Email:</label>
      <input id="email" name="email" type="email" />
      <br />
      <label for="login">Login:</label>
      <input id="login" name="login" type="text" />
      <br />
      <label for="password">Password:</label>
      <input id="password" name="password" type="password" />
      <br />
      <legend>Choose user's role</legend>
      <div>
        <input type="radio" id="ADMIN" name="roleDto" value="ADMIN" />
        <label for="ADMIN">ADMIN</label>
      </div>
      <div>
        <input type="radio" id="MANAGER" name="roleDto" value="MANAGER" />
        <label for="MANAGER">MANAGER</label>
      </div>
      <div>
        <input type="radio" id="CUSTOMER" name="roleDto" value="CUSTOMER" />
        <label for="CUSTOMER">CUSTOMER</label>
      </div>
      <br />
      <input type="submit" value="CREATE" />
    </form>
  </body>
</html>