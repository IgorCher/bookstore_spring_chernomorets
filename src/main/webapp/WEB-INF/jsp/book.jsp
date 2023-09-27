<!DOCTYPE html>
<html>
  <head>
    <title>Book</title>
  </head>
  <body>
    <jsp:include page="navbar.jsp" />
    <h1>Book: ${book.id}</h1>
    <p>Title: ${book.title}</p>
    <p>Author: ${book.author}</p>
    <p>Publication date: ${book.year}</p>
    <p>Price: ${book.price} euro</p>
    <p>ISBN: ${book.isbn}</p>
    <br />
    <p>
      <form method="post" action="/books/delete">
        <input type="hidden" name="id" value="${book.id}"/>
        <input type="submit"  value="Delete"/>
      </form>
    </p>
    <p>
      <a href="/books">
        <button>Back to all books</button>
      </a>
    </p>
  </body>
</html>
