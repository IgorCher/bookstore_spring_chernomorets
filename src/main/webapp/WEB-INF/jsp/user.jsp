<!DOCTYPE html>
<html>
  <head>
    <title>User</title>
  </head>
  <body>
    <jsp:include page="navbar.jsp" />
    <h1>User: ${user.id}</h1>
    <p>Name: ${user.name} ${user.lastName}</p>
    <p>Login: ${user.login}</p>
    <p>Email: ${user.email}</p>
    <p>Role: ${user.roleDto}</p>
    <br />
    <p>
      <form method="post" action="/users/delete">
        <input type="hidden" name="id" value="${user.id}"/>
        <input type="submit"  value="Delete"/>
      </form>
    </p>
    <p>
      <a href="/users">
        <button>Back to all users</button>
      </a>
    </p>
  </body>
</html>
