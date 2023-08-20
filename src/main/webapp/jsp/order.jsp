<!DOCTYPE html>
<html>
  <head>
    <title>Order</title>
  </head>
  <body>
    <jsp:include page="navbar.jsp" />
    <h1>Order: ${order.id}</h1>
    <p>User: ${order.userId}</p>
    <p>Total cost: ${order.totalCost}</p>
    <br />
    <p>
      <a href="controller?command=orders">
        <button>Back</button>
      </a>
    </p>
  </body>
</html>
