/**
 * Copyright 2000 by CreamTec, LLC. All rights reserved.
 */
package com.creamtec.ajaxswing.ui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;


public class AppWizCheckListPanel
    extends AppWizPanel
{

    GridBagLayout gridBagLayout1 = new GridBagLayout();
    JLabel lblEngineConfigured = new JLabel();
    JLabel lblServletConfigured = new JLabel();
    Border border1;
    JCheckBox chkEngineConfigured = new JCheckBox();
    JCheckBox chkServletConfigured = new JCheckBox();
    JLabel lblHeader = new JLabel();


    public AppWizCheckListPanel () {
        try {
            jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void jbInit ()
        throws Exception
    {
        border1 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        this.setLayout(gridBagLayout1);
        lblEngineConfigured.setText("<html><font size=2><i>You <b>must</b> make sure that the JVM, that " + "will execute AjaxSwingRouter servlet has been configured to start " + "with -Xbootclasspath parameter as described in Setup document</i></html>");
        lblServletConfigured.setText("<html><font size=2><i>You <b>must</b> make sure that AjaxSwingRouter " + "servlet has been configured within the servlet engine and that the " + "application properties file, created by this  Wizard is accessible " + "to AjaxSwingRouter as described in Setup document</i></html>");
        this.setBorder(border1);
        chkEngineConfigured.setText("WebServer/Servlet Engine configured");
        chkServletConfigured.setText("AjaxSwingRouter servlet configured");
        lblHeader.setText("<html>The following check list is for information purposes only. " + "If you have already completed the steps required here there is no " + "need to check the checkboxes again</html>");
        this.add(chkEngineConfigured, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        this.add(chkServletConfigured, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        this.add(lblEngineConfigured, new GridBagConstraints(0, 2, 2, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.add(lblServletConfigured, new GridBagConstraints(0, 4, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.add(lblHeader, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    }

}

