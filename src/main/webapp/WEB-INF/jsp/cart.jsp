<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Cart</title>
  </head>
  <body>
    <jsp:include page="navbar.jsp" />
    <c:if test="${sessionScope.cart == null}">
      <h1>Cart is empty</h1>
    </c:if>
    <c:if test="${sessionScope.cart != null}">
      <table>
        <tr>
          <td>Author</td>
          <td>Title</td>
          <td>quantity</td>
        </tr>
        <c:forEach items="${books}" var="book">
          <tr>
            <td>${book.key.author}</td>
            <td>${book.key.title}</td>
            <td>${book.value}</td>
            <td>
              <a href="/cart/add/${book.key.id}">
                <button>+</button>
              </a>
            </td>
            <td>
              <a href="/cart/remove/${book.key.id}">
                <button>-</button>
              </a>
            </td>
          </tr>
        </c:forEach>
      </table>
      <a href="/cart/clear">
        <button>Clear</button>
      </a>
    </c:if>
  </body>
</html>
