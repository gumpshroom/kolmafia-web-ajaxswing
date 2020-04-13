/**
 * Copyright 2000 by CreamTec, LLC. All rights reserved.
 */
package com.creamtec.ajaxswing.ui;

import  java.awt.*;
import  javax.swing.JPanel;


public class AppWizPanel
    extends JPanel
{

    public boolean apply ()
        throws Exception
    {
        return  true;
    }


    public void activate ()
        throws Exception
    {

    }


    public AppWizard getWizard () {
        return  wizard;
    }


    public void setWizard (AppWizard wizard) {
        this.wizard = wizard;
    }


    AppWizard wizard;

}

