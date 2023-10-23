<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ page
contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>Error</title>
  </head>
  <body>
    <jsp:include page="navbar.jsp" />
    <h1>
      <c:if test="${status != null}">Error</c:if>
    </h1>
    <p>
      <c:if test="${status != null}">Status: ${status}</c:if>
    </p>
    <p>${message != null ? message : 'Something went wrong...'}</p>
  </body>
</html>
