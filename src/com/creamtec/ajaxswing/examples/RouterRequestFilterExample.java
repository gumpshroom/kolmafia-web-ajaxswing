/**
 * Copyright 2000-2004 by CreamTec, LLC. All rights reserved.
 */
package com.creamtec.ajaxswing.examples;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.creamtec.core.TraceMgr;
import com.creamtec.ajaxswing.core.*;
import com.creamtec.ajaxswing.RouterRequestFilter;
import com.creamtec.ajaxswing.servlet.AppDesc;

/**
 * Demonstrates an implementation of RouterRequestFilter interface.
 * See documentation for more information.
 */
public class RouterRequestFilterExample
    implements RouterRequestFilter
{

    public RouterRequestFilterExample() {
        TraceMgr.trace(this, "Filter loaded");
    }

    public boolean preProcessRequest(HttpServletRequest request, HttpServletResponse response) {
        TraceMgr.trace(this, "Pre-processing request");
        // Nothing to do, so just return to continue processing
        return true;
    }

    public boolean postProcessRequest(HttpServletRequest request, HttpServletResponse response, AppDesc appDesc, ClientAgentRemote agent, HttpRequestData requestData)
        throws Exception
    {
        TraceMgr.trace(this, "Post-processing request");
        // Obtain client IP address from the HTTP request and save that information in
        // AjaxSwing's request data
        String ipAddress = request.getRemoteAddr();
        TraceMgr.trace(this, "IP address = " + ipAddress, 6);
        requestData.getParams().put("custom.ip_address", ipAddress);
        return true;
    }

    public boolean processResponse(HttpServletRequest request, HttpServletResponse response, AppDesc appDesc, ClientAgentRemote agent, HttpRequestData requestData, HttpResponseData responseData)
        throws Exception
    {
        if (responseData != null && responseData.getHtmlPage() != null) {
            String title = "About AjaxSwing<\\/div>";
            
            if (responseData.getHtmlPage().indexOf(title) != -1) {
                String page = responseData.getHtmlPage();
                StringBuffer buf = new StringBuffer(page.length() + 100);
                int endOfMessageIndex = page.indexOf(title);
                buf.append(page.substring(0, endOfMessageIndex));
                buf.append("Filtered About");
                buf.append(page.substring(endOfMessageIndex + title.length()));
                responseData.setHtmlPage(buf.toString());
            }

            // Demonstrates how Swing applications can set cookies
            Map params = responseData.getParams();
            Iterator keys = params.keySet().iterator();
            while (keys.hasNext()) {
                String key = keys.next().toString();
                if(key.startsWith("cookie.")) {
                    String value = (String) params.get(key);
                    Cookie cookie = new Cookie(key.substring(7), value);
                    response.addCookie(cookie);
                }
            }
        }
        return true;
    }

}
