/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.33
 * Generated at: 2020-04-11 22:54:12 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.Light_005fdocs;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.creamtec.ajaxswing.i18n.I18NUtils;
import com.creamtec.ajaxswing.i18n.I18NKeys;
import com.creamtec.ajaxswing.core.AjaxSwingProperties;
import java.util.Locale;
import com.creamtec.ajaxswing.core.AjaxSwingPropertiesManager;
import com.creamtec.core.StringUtilities;

public final class exit_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("com.creamtec.ajaxswing.i18n.I18NKeys");
    _jspx_imports_classes.add("java.util.Locale");
    _jspx_imports_classes.add("com.creamtec.ajaxswing.core.AjaxSwingProperties");
    _jspx_imports_classes.add("com.creamtec.core.StringUtilities");
    _jspx_imports_classes.add("com.creamtec.ajaxswing.i18n.I18NUtils");
    _jspx_imports_classes.add("com.creamtec.ajaxswing.core.AjaxSwingPropertiesManager");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>");
      out.print(sessionClosed_Title);
      out.write("</title>\r\n");
      out.write("<meta http-equiv=\"Pragma\" content=\"no-cache\">\r\n");
 
    Object themeDocsURL = request.getAttribute("ajaxswingThemeDocsURL");
    if (themeDocsURL != null) {

      out.write("    \r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(themeDocsURL);
      out.write("/css/ajaxswing/styles_common.css\">\r\n");

    }
    else {

      out.write("\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/ajaxswing/styles_common.css\">\r\n");

    }

      out.write("\r\n");
      out.write("<style>\r\n");
      out.write("h1, h2, h3 {font-family:Arial, Helvetica, sans-serif; text-align:left}\r\n");
      out.write("p, div, li, td {font-size:13px;font-family:Verdana, Arial, Helvetica, sans-serif; text-align:left}\r\n");
      out.write("div {font-family:Arial, Helvetica, sans-serif; padding:30px; margin:200px; border:1px dashed gray}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("<form method=POST action=\"");
      out.print(org.apache.commons.lang.StringEscapeUtils.escapeHtml(String.valueOf(session.getAttribute("ajaxswing.submitURL"))));
      out.write('?');
      out.print(localeParameter + "=" + currentLocale );
      out.write("\">\r\n");
      out.write("<center>\r\n");
      out.write("  <div class=\"border\" style=\"border:1px dashed gray\">\r\n");
      out.write("      <h3>");
      out.print(sessionClosed_Title);
      out.write("</h3>\r\n");
      out.write("      <p>");
      out.print(sessionClosed_Text);
      out.write("</p>\r\n");
      out.write("      <p>&nbsp;</p>\r\n");
      out.write("      \t<input type=\"hidden\" name=\"");
      out.print(localeParameter);
      out.write("\" value=\"");
      out.print(currentLocale );
      out.write("\" >\r\n");
      out.write("        <input type=\"hidden\" name=\"__Action\" value=\"refresh\">\r\n");
      out.write("        <input type=\"submit\" value=\"");
      out.print(sessionClosed_Button);
      out.write("\" class=\"submit\">\r\n");
      out.write("  </div>\r\n");
      out.write("</form>\r\n");
      out.write("</center>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}