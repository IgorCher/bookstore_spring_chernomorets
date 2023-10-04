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
    <form method="post" action="/users/delete/${user.id}">
      <input type="submit" value="Delete" />
    </form>
    <p>
      <a href="/users">
        <button>Back to all users</button>
      </a>
    </p>
  </body>
</html>
