<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>Error</title>
  </head>
  <body>
    <jsp:include page="navbar.jsp" />
    <h1>Something went wrong...</h1>
    <p>Status: ${status}</p>
    <p>${message}</p>
  </body>
</html>
