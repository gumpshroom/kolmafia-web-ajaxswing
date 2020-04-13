<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <%
    String webappUrl = request.getRequestURL().toString();
    webappUrl = webappUrl.substring(0, webappUrl.lastIndexOf('/'));
    %>
    <script type="text/javascript" src="<%=webappUrl%>/scripts/ajaxswing/ajaxswing.js"></script>
	<title>Initializing browser cache</title>
</head>

<body>
   This page is used to load expensive resources into the browser cache
</body>

</html>