﻿<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@page import="com.creamtec.ajaxswing.i18n.I18NUtils"%>
<%@page import="com.creamtec.ajaxswing.i18n.I18NKeys"%>
<%
	String appName = request.getParameter("appName");
	if (StringUtilities.isEmpty(appName)) {
		appName="default";
	}
	AjaxSwingProperties defaultProps = null;
	try {
		defaultProps = AjaxSwingPropertiesManager.getInstance().getAppProperties(appName);
	} catch(Exception ex) {
		appName="default";
		defaultProps = AjaxSwingPropertiesManager.getInstance().getAppProperties(appName);
	}
	String currentLocale = request.getParameter(defaultProps.getProperty(AjaxSwingProperties.HTTP_URL_LOCALE,"locale"));
	if (currentLocale == null) {
		currentLocale = defaultProps.getProperty(AjaxSwingProperties.AGENT_DEFAULT_LOCALE);
	}
	Locale userLocale = I18NUtils.createLocale(currentLocale);
	if(userLocale == null) userLocale = Locale.getDefault();
	String messageAjaxswingErrorTitle = I18NUtils.getMessage(I18NKeys.MESSAGE_AJAXSWING_ERROR_TITLE, userLocale);
	String messageAjaxswingErrorInternal = I18NUtils.getMessage(I18NKeys.MESSAGE_AJAXSWING_ERROR_INTERNAL, userLocale);
	String messageAjaxswingErrorText = I18NUtils.getMessage(I18NKeys.MESSAGE_AJAXSWING_ERROR_TEXT, userLocale);
	String messageAjaxswingErrorButton_text = I18NUtils.getMessage(I18NKeys.MESSAGE_AJAXSWING_ERROR_BUTTON_TEXT, userLocale);
	String messageAjaxswingErrorBottom_text = I18NUtils.getMessage(I18NKeys.MESSAGE_AJAXSWING_ERROR_BOTTOM_TEXT, userLocale);
%>
<%@page import="java.util.Locale"%>
<%@page import="com.creamtec.ajaxswing.core.AjaxSwingProperties"%>
<%@page import="com.creamtec.ajaxswing.core.AjaxSwingPropertiesManager"%>
<%@page import="com.creamtec.core.StringUtilities"%><html>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="description" content="CreamTec developed Java to HTML conversion utility that web-enables Java applications and deploys Swing in browser">
<meta name="keywords" content="creamtec,ajaxswing,Java to HTML conversion,web-enabling java applications,swing applications in browser,deploy java through html,applets executing within browser,java conversion,convert swing,java to html,automatic web-enabling">
<title><%=messageAjaxswingErrorTitle%></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">
</head>
<style>
h1 {font-family: 'Dosis', sans-serif;
	font-size: 32px;
	font-weight: normal;
	color: 
	#E99420;}
p, div, li, td {font-family: 'Dosis', sans-serif;
	font-size: 14px;
	font-weight: normal;
	color: 
	#333;}
.sidebar {
	width: 320px;
	float: right;
	padding: 0px 0 0 0;
}
a { color: 
	#345D9E;
	text-decoration: underline;
}
.help {
	padding:5px;
	width:144px;
	border-width:0px;
	border-style:solid;
	border-color:#7C335D;
}
pre {
	width:550px;
	height:250px;
	overflow:scroll;
	font-size:9px;
}
.main > a {
	text-decoration:none;position:absolute;display:block;
}
.sidebar > a {
	text-decoration:none;
}
</style>
<body style="background:url(../bg.gif) repeat;text-align:center;margin:0px;">
<div class="main" style="margin: 0px auto;position:relative;width:780px;height:600px;text-align:center;background:transparent url(images/com/creamtec/ajaxswing/laf/icons/banner.png) no-repeat left top;">	
	<div style="text-decoration:none;position:absolute;display:block;top:155px;left:0px;width:500px;height:100px;text-align:left">
		<h1><%=messageAjaxswingErrorInternal%></h1>
		<div>
			<%
				Object value = session.getAttribute("ajaxswing.errorMessage");
				if ((value != null) && (value.toString().length()>0)) {
			%>
			<%=session.getAttribute("ajaxswing.errorMessage")%>			
			<p><p>
			<%
				}
			%>
			<i><%=messageAjaxswingErrorText %></i>
		</div>
		<form method=POST action="<%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(String.valueOf(session.getAttribute("ajaxswing.submitURL")))%>">
			<input type="hidden" name="__Action" value="refresh">
			<INPUT TYPE="image" SRC="images/com/creamtec/ajaxswing/laf/icons/refresh.png">
		</form>
		<p><%=messageAjaxswingErrorBottom_text%></p>
		<div style="margin:0px;margin-top:42px;color:#898989;text-align:left;">2012 © CreamTec, LLC. All rights reserved.</div>
	</div>
	<div class="sidebar" style="position:absolute;left:588px;top:172px;text-align:left;">	
		<a href="http://www.creamtec.com/services/conversion_to_ajax.html">
		<div class="help">
			<span style="font-size:16px;font-weight:bold;color:#345D9E;">Need Help?</span><br>
			<span>Experts at CreamTec professional services can help you quickly get to 100% working web UI</span>
		</div>
		</a>
		<br>
		<br>
		<a href="http://creamtec.com/services/index.html">
		<div class="help">
			<span style="font-size:16px;font-weight:bold;color:#345D9E;">Talent On Demand</span><br>
			<span>We offer full range of services from web design to development</span>
		</div>
		</a>
	</div>	
	<a href="http://creamtec.com/" style="top:0px;left:0px;width:150px;height:100px;">&nbsp;</a>
	<a href="../" style="top:110px;left:0px;width:150px;height:32px;">&nbsp;</a>
	<a href="http://creamtec.com/" style="top:110px;left:180px;width:150px;height:32px;">&nbsp;</a>
	<a href="http://creamtec.com/services/index.html" style="top:110px;left:588px;width:150px;height:32px;">&nbsp;</a>
</div>
</html>
