<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@page import="com.creamtec.ajaxswing.i18n.I18NUtils"%>
<%@page import="com.creamtec.ajaxswing.i18n.I18NKeys"%>
<%
String appName = request.getParameter("appName");
if (StringUtilities.isEmpty(appName)) {
	appName="default";
}
AjaxSwingProperties appProps = null;
try {
	appProps = AjaxSwingPropertiesManager.getInstance().getAppProperties(appName);
} catch(Exception ex) {
	appName="default";
	appProps = AjaxSwingPropertiesManager.getInstance().getAppProperties(appName);
}
String localeParameter = appProps.getProperty(AjaxSwingProperties.HTTP_URL_LOCALE, "locale");
String currentLocale = request.getParameter(localeParameter);
Locale userLocale = null;
if (StringUtilities.isEmpty(currentLocale)){
	userLocale = Locale.getDefault();
} else {
	userLocale = I18NUtils.createLocale(currentLocale);
	currentLocale = userLocale.toString();
}
Object sessionClosed_Title = I18NUtils.getMessage(I18NKeys.MESSAGE_SESSION_CLOSED_TITLE, userLocale);
Object sessionClosed_Text = I18NUtils.getMessage(I18NKeys.MESSAGE_SESSION_CLOSED_TEXT, userLocale);
Object sessionClosed_Button = I18NUtils.getMessage(I18NKeys.MESSAGE_SESSION_CLOSED_BUTTON, userLocale);
%>
<%@page import="com.creamtec.ajaxswing.core.AjaxSwingProperties"%>
<%@page import="java.util.Locale"%>
<%@page import="com.creamtec.ajaxswing.core.AjaxSwingPropertiesManager"%>
<%@page import="com.creamtec.core.StringUtilities"%><html>
<head>
<title><%=sessionClosed_Title%></title>
<meta http-equiv="Pragma" content="no-cache">
<% 
    Object themeDocsURL = request.getAttribute("ajaxswingThemeDocsURL");
    if (themeDocsURL != null) {
%>    
    <link rel="stylesheet" type="text/css" href="<%=themeDocsURL%>/css/ajaxswing/styles_common.css">
<%
    }
    else {
%>
    <link rel="stylesheet" type="text/css" href="css/ajaxswing/styles_common.css">
<%
    }
%>
<style>
h1, h2, h3 {font-family:Arial, Helvetica, sans-serif; text-align:left}
p, div, li, td {font-size:13px;font-family:Verdana, Arial, Helvetica, sans-serif; text-align:left}
div {font-family:Arial, Helvetica, sans-serif; padding:30px; margin:200px; border:1px dashed gray}
</style>
</head>

<body>
<form method=POST action="<%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(String.valueOf(session.getAttribute("ajaxswing.submitURL")))%>?<%=localeParameter + "=" + currentLocale %>">
<center>
  <div class="border" style="border:1px dashed gray">
      <h3><%=sessionClosed_Title%></h3>
      <p><%=sessionClosed_Text%></p>
      <p>&nbsp;</p>
      	<input type="hidden" name="<%=localeParameter%>" value="<%=currentLocale %>" >
        <input type="hidden" name="__Action" value="refresh">
        <input type="submit" value="<%=sessionClosed_Button%>" class="submit">
  </div>
</form>
</center>
</html>
