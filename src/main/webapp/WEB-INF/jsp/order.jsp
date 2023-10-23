<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Order</title>
  </head>
  <body>
    <jsp:include page="navbar.jsp" />
    <h1>Order: ${order.id}</h1>
    <p>User: ${order.user.id}</p>
    <p>Total cost: ${order.totalCost}</p>

    <table>
      <tr>
        <th>#</th>
        <th>Title</th>
        <th>Author</th>
        <th>Price</th>
        <th>Quantity</th>
      </tr>

      <tr>
        <c:forEach
          items="${order.orderItems}"
          var="orderItem"
          varStatus="counter"
        >
          <tr>
            <td>${counter.count}</td>
            <td>${orderItem.book.title}</td>
            <td>${orderItem.book.author}</td>
            <td>${orderItem.book.price}</td>
            <td>${orderItem.bookQuantity}</td>
          </tr>
        </c:forEach>
      </tr>
    </table>

    <br />
    <p>
      <a href="/orders">
        <button>Back</button>
      </a>
    </p>
  </body>
</html>
