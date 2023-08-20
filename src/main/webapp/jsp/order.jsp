<!DOCTYPE html>
<html>
  <head>
    <title>Order</title>
  </head>
  <body>
    <jsp:include page="navbar.jsp" />
    <h1>Order: ${order.id}</h1>
    <p>User: ${order.userDto.id}</p>
    <p>Total cost: ${order.totalCost}</p>
    <p>Order items: ${orderItems}</p>

    <table>
      <tr>
        <th>#</th>
        <th>Title</th>
        <th>Author</th>
        <th>Price</th>
      </tr>

      <tr>
        <c:forEach items="${orderItems}" var="orderItem" varStatus="counter">
          <tr>
            <td>${counter.count}</td>
            <td>${orderItem.bookDto.title}</td>
            <td>${orderItem.bookDto.author}</td>
            <td>${orderItem.bookDto.price}</td>
          </tr>
        </c:forEach>
      </tr>
    </table>

    <br />
    <p>
      <a href="controller?command=orders">
        <button>Back</button>
      </a>
    </p>
  </body>
</html>
