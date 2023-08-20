<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Orders</title>
  </head>
  <body>
    <jsp:include page="navbar.jsp" />
    <h1>My Orders</h1>
    <table>
      <tr>
        <th>#</th>
        <th>OrderId</th>
        <th>TotalCost</th>
      </tr>

      <tr>
        <c:forEach items="${orders}" var="order" varStatus="counter">
          <tr>
            <td>${counter.count}</td>
            <td>
              <a href="controller?command=order&id=${order.id}">${order.id}</a>
            </td>
            <td>${order.totalCost}</td>
          </tr>
        </c:forEach>
      </tr>
    </table>

    <p>
      <a href="controller?command=users">
        <button>Back</button>
      </a>
    </p>
  </body>
</html>