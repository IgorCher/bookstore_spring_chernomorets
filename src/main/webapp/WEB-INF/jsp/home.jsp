<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ page
contentType="text/html;charset=UTF-8" %>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/css/style.css" />
    <title>Bookstore</title>
  </head>
  <body>
    <jsp:include page="navbar.jsp" />
    <h1>The best bookstore you've ever seen</h1>
    <c:if test="${sessionScope.user != null}">
      <p>Enjoy, mister ${sessionScope.user.lastName}!</p>
    </c:if>
    <p>Owner: Individual entrepreneur Igor Chernomorets</p>
  </body>
</html>
