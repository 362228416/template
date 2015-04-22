<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<br/>

Users:
&nbsp;&nbsp;&nbsp;&nbsp;<a href="/users?form">Add</a>

<ul>
    <c:forEach items="${users.content}" var="user">
        <li>${user.name}, ${user.age}</li>
    </c:forEach>
</ul>