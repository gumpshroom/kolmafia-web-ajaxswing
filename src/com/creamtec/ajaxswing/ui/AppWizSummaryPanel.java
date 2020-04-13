/**
 * Copyright 2000 by CreamTec, LLC. All rights reserved.
 */
package com.creamtec.ajaxswing.ui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;


public class AppWizSummaryPanel
    extends AppWizPanel
{

    GridBagLayout gridBagLayout1 = new GridBagLayout();
    Border border1;
    JLabel lblHeader = new JLabel();
    JLabel lblInfo = new JLabel();


    public AppWizSummaryPanel () {
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
        this.setBorder(border1);
        lblHeader.setText("<html><b>Congratulations!</b></html>");
        lblInfo.setText("info");
        this.add(lblHeader, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.add(lblInfo, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    }

    public void activate ()
        throws Exception
    {
        lblInfo.setText("<html>You have provided enough information to preview your applet/applicatoin" +
            " with AjaxSwing. You can specify additional information and customize your application deployment" +
            " options in <i>" + this.getWizard().getAppPropertiesFileName() + "</i> file");
    }

}

