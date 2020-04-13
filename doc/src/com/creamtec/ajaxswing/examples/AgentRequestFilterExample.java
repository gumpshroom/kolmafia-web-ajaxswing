package com.creamtec.ajaxswing.examples;

import java.awt.*;
import java.util.List;

import javax.swing.JDialog;
import com.creamtec.ajaxswing.core.*;
import com.creamtec.ajaxswing.rendering.html.HTMLPage;
import com.creamtec.core.TraceMgr;

/**
 * Demonstrates the usage of AgentRequestFilter interface to intercept standard AjaxSwing
 * "resize" action and to modify the generated HTML pages.
 */
public class AgentRequestFilterExample
    implements AgentRequestFilter {

    public AgentRequestFilterExample() {
        TraceMgr.trace(this, "Filter loaded");
    }

    /**
     * Overridden to intercept "resize" action.
     * @param agent ClientAgent
     * @return HttpResponseData
     */
    public HttpResponseData preProcessRequest(ClientAgent agent) {
        TraceMgr.trace(this, "preProcessRequest");
        try {
            if ("About AjaxSwing".equals(agent.getUIManager().getWindowInFocusTitle())) {
                // Only apply post processing to the About dialog
                String action = agent.getRequestData().getAction();
                if (action != null && action.startsWith("resize:")) {
                    // Override the processing of resize with our own cool page
                    TraceMgr.trace(this, "Intercepted resize of About dialog");
                    Window aboutWindow = agent.getUIManager().getWindowInFocus();
                    aboutWindow.setSize(300, 300);
                    if (aboutWindow instanceof JDialog) {
                        JDialog dlg = (JDialog) aboutWindow;
                        dlg.setTitle("Filtered Resize");
                    }
                    return agent.renderWindows(agent.getRequestData());
                }
            }
        }
        catch (Exception x) {
            TraceMgr.trace(this, "Internal error", x);
        }
        return null; // Let the processing continue
    }


    /**
     * Overridden to modify the generated HTML page by adding the user IP address.
     * @param agent ClientAgent
     * @param responseData HttpResponseData
     */
    public void postProcessRequest(ClientAgent agent, HttpResponseData responseData) {
        TraceMgr.trace(this, "postProcessRequest");
        String title = "About AjaxSwing";
        String message = "AjaxSwing is awesome!"; 

        try {
            if (title.equals(agent.getUIManager().getWindowInFocusTitle())) {
                String page = responseData.getHtmlPage();
                String ipAddress = (String) agent.getRequestData().getParams().get("custom.ip_address");
                StringBuffer buf = new StringBuffer(page.length() + 100);
                int endOfMessageIndex = page.indexOf(message);
                if (endOfMessageIndex != -1) {
                    buf.append(page.substring(0, endOfMessageIndex));
                    buf.append("<b>Filter IP address: ");
                    buf.append(ipAddress);
                    buf.append("</b>");
                    buf.append(page.substring(endOfMessageIndex + message.length()));
                    responseData.setHtmlPage(buf.toString());
                }
            }
            
            if (agent.getRequestData().getParams().get("test") != null) {
                TraceMgr.trace("Remove query string by redirecting to " + agent.getInitData().getSubmitURL());
                responseData.setRedirectURL(agent.getInitData().getSubmitURL());
            }
        }
        catch (Exception x) {
            TraceMgr.trace(this, "Failed to post-process the request", x);
        }
    }

    /**
     * Called after the controls have been updated with the data but before the action
     * is emulated. If there is no action to be emulated (for instance, if "update" was
     * submitted, this method will not be called.
     * @param agent client agent
     * @param data contains the information about the action to be emulated
     * @return true to emulate the action, false to not emulate
     */
    public boolean preEmulateAction(ClientAgent agent, ActionData data) {
        TraceMgr.trace(this, "preEmulateAction");
        return true; // Let the processing continue
    }

    /**
     * Called after the action emulation is complete. (even if preEmulateAction returned false).
     * @param agent client agent
     * @param data contains the information about the action to be emulated
     */
    public void postEmulateAction(ClientAgent agent, ActionData data) {
        TraceMgr.trace(this, "postEmulateAction");
    }

    /**
     * Called before the given window is rendered as HTML page
     * @param agent client agent
     * @param windows Window to be rendered
     */
    public void preRenderWindows(ClientAgent agent, List windows) {
        TraceMgr.trace(this, "preRenderWindow");
    }

    /**
     * Called after the given window is rendered as HTML page
     * @param agent client agent
     * @param windows Window that was rendered
     * @param htmlCode The HTML code for the page produced by AjaxSwing engine. Modifications
     * to the HTMLPage object associated with the client agent will not be reflected in the
     * generated HTML code
     * @return HTML code of the page that will be returned to the browser. The filter has
     * to return the HTML code whether it modified it or not, or
     */
    public String postRenderWindows(ClientAgent agent, List windows, String htmlCode) {
        TraceMgr.trace(this, "postRenderWindow");
        return htmlCode;
    }

}
