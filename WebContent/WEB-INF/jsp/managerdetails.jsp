
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ page isELIgnored = "false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
function refresh(){
	
	setTimeout(function(){
		window.location.reload();
	}, 5000)
}
</script>
<body onload="refresh()">
<table>
<tr>
<th>&nbsp;Request Id&nbsp;</th><th>&nbsp;Manager Id&nbsp;</th><th>&nbsp;&nbsp;Status&nbsp;&nbsp;</th>
</tr>
<c:forEach items="${managerPendingRequests}" var="adminrequest">
<tr>
<td><c:out value="${managerrequest.requestId}"/>
</td>
<td><c:out value="${managerrequest.managerId}"/></td>
<td><c:out value="${managerrequest.status}"/>
<a href="changeRequestStatus.htm?requestId=${managerrequest.requestId}&managerId=${managerrequest.managerId}&action=approve">Approve</a>
<a href="changeRequestStatus.htm?requestId=${managerrequest.requestId}&managerId=${managerrequest.managerId}&action=reject">Reject</a>
</td>

</tr>
</c:forEach>
</table>
Status:<c:out value="${status}"/><br><br>
 Pending requests for approval:<c:out value="${pendingCount}"/>
</body>
</html>