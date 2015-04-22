<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Object loginUser = session.getAttribute("loginUser");
  if (loginUser == null) {
    response.sendRedirect("login");
  } else {
    response.sendRedirect("users/list");
  }
%>