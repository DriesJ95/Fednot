<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Guestbook</title>
</head>
<body style="height: 100vh; width: 100vw; display: grid; grid-template: auto auto / auto ; grid-gap: 4em; font-family: 'Segoe UI'">
<h1 style="grid-row: 1; color:#5193af" align="center" >Guestbook</h1>
<br/>
<div style="display:grid; grid-template: auto / auto auto auto; margin-left: 10vw; margin-right:10vw">
<form style ="grid-row: 2; grid-column:1";align = "center" method="post" action="servlet">
    <p>Enter your name here: <input type = "text" name="Name" required></p>
    <p>Enter your message here: <input type = "text" name = "Message" required></p>
    <p><input type="submit" name="Add to DB"></p>
</form>
<br/>
<table style="grid-row: 2; grid-column: 2 / 3 span; background-color: #5193af; color: white; border-color: white; border-radius: 5px;" align="center" border="1" cellpadding=10">
    <tr>
        <th>Date</th>
        <th>Name</th>
        <th>Message</th>
    </tr>
    <c:forEach items="${guestBookList}" var ="guest">
        <tr>
            <td>${guest.getDate()}</td>
            <td>${guest.getName()}</td>
            <td>${guest.getMessage()}</td>
        </tr>
    </c:forEach>
</table>
</div>
</body>
</html>
