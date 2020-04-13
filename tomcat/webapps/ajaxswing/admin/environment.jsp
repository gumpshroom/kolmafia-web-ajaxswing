<html>

<%@ page import="javax.servlet.*, javax.servlet.http.*, java.io.*, java.util.*" %>

<head>
	<title>Environment Information</title>
</head>

<body bgcolor="#f7f0e1">
<table width=100%>
<tr>
<td>
<h1>Environment Information</h1>
</td>
<td align=right>
<img src="logo.gif" width="64" height="41">
</td>
</tr>
</table>

<p>
	<b>Class path</b>

<%
	String cwd = "n/a";
	String ajaxswingHome = "n/a";

	try
	{

		String clPath = System.getProperty("java.class.path");
		System.out.println("class path: " + clPath );
		StringTokenizer st = new StringTokenizer(clPath, File.pathSeparator);
        	while (st.hasMoreTokens())
        	{
        		String classsPathEntry = st.nextToken();
			System.out.println( classsPathEntry );
%>
			<br>
				<%=classsPathEntry%>

<%
	     	}
%>
		<p>
		<b>Boot class path</b>
<%
		clPath = System.getProperty("sun.boot.class.path");
		System.out.println("boot class path: " + clPath );
		st = new StringTokenizer(clPath, File.pathSeparator);
        	while (st.hasMoreTokens())
        	{
        		String classsPathEntry = st.nextToken();
			System.out.println( classsPathEntry );
%>
			<br>
				<%=classsPathEntry%>

<%
		}
		System.out.println("Your current working dir" );



                File f = new File("temp");
                String absPath = f.getAbsolutePath();
		cwd = absPath.substring( 0, absPath.indexOf( File.separator + "temp" ));
		System.out.println( "cwd: " + cwd );

                f = new File(System.getProperty("ajaxswing.home"));
                ajaxswingHome = f.getCanonicalPath();
//		ajaxswingHome = absPath.substring( 0, absPath.indexOf( File.separator + "temp" ));
		System.out.println( "ajaxswing.home: " + ajaxswingHome );

	}

        catch(Exception e)
	{
		e.printStackTrace();
	}

%>

<p>
<b>Your current working directory</b>
<br>
<%=cwd%>
</br>

<p>
<b>AjaxSwing home directory (ajaxswing.home)</b>
<br>
Literal: <%=System.getProperty("ajaxswing.home")%> <br>
Canonical: <%=ajaxswingHome%>
</br>

<p>
<b>Java version</b>
<br>
<%=System.getProperty("java.version")%>

<p>
<b>OS name, version</b>
<br>
<%=System.getProperty("os.name")%>, <%=System.getProperty("os.version")%>


<p>
<b>Host Name (Address)</b>
<br>
<%
  String hostName = "unknown";
  String hostAddress = "unknown";
  try {
    hostName = java.net.InetAddress.get0.0.0.0().getHostName();
    hostAddress = java.net.InetAddress.get0.0.0.0().getHostAddress();
  }
  catch (Exception x) {
    x.printStackTrace();
  }
%>
<%=hostName%>&nbsp;(<%=hostAddress%>)


<p>
</p>


</body>
</html>
