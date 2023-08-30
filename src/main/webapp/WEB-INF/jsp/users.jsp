<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Users</title>
  </head>
  <body>
    <jsp:include page="navbar.jsp" />
    <h1>All Users</h1>
    <table>
      <tr>
        <th>#</th>
        <th>Id</th>
        <th>Email</th>
        <th>Name</th>
        <th>Last name</th>
        <th>Role</th>
      </tr>

      <tr>
        <c:forEach items="${users}" var="user" varStatus="counter">
          <tr>
            <td>${counter.count}</td>
            <td>${user.id}</td>
            <td>
              <a href="/users/${user.id}">${user.email}</a>
            </td>
            <td>${user.name}</td>
            <td>${user.lastName}</td>
            <td>${user.roleDto}</td>
            <td>
              <a href="/orders/user_orders/${user.id}">
                <button>Orders</button>
              </a>
            </td>
            <td>
              <a href="/users/edit/${user.id}">
                <button>Edit</button>
              </a>
            </td>
          </tr>
        </c:forEach>
      </tr>
    </table>
  </body>
</html>
