<html>

<%@ page import="javax.servlet.*, javax.servlet.http.* " %>

<head>
	<title>AjaxSwing 4.0</title>
	<link rel="stylesheet" href="../AjaxSwing_docs/scripts/AjaxSwing.css">
</head>

<body>
<img src="logo.gif" width="64" height="41">

<h2>Unsuppoted class</h2>
<%
	String className = request.getParameter("classname");
	if( className != null && className.length() > 0 )
	{
		String superClassName = "-- unknown --"; 
                                           try {
          			Class cl = Class.forName( className );
			Class parentCl = cl.getSuperclass();
			if( parentCl != null)
				superClassName = parentCl.getName();
		}
		catch(Throwable x) {
		}
%>
        <div class="ERRORMSG">
        	class <%=className%> is unsupported
        </div>

        The class <b><%=className%></b>, derived from <b><%= superClassName %></b>, is unsupported by AjaxSwing because it is neither standard AWT nor 
        Swing component.

        <p>
How to fix it? You need to write a <a href="http://creamtec.com/ajaxswing/doc/CustomRenderers.html">Custom Renderer</a> for this component or you can hire professional services from CreamTec LLC.

<%
	}
%>
</body>
</html>
