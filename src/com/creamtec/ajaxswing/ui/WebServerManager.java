/**
 * Copyright 2000 by CreamTec, LLC. All rights reserved.
 */
package com.creamtec.ajaxswing.ui;

import java.net.URL;
import java.net.URLConnection;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;

import com.creamtec.ajaxswing.core.LicenseManager;
import com.creamtec.ajaxswing.gui.peer.ClientAgentBase;
import com.creamtec.core.AppProperties;
import com.creamtec.core.TraceMgr;
import com.creamtec.core.Utilities;


public class WebServerManager {

    public WebServerManager (AjaxSwingConsole console)
        throws Exception
    {
        TraceMgr.trace(this, "constructor");
        this.console = console;
        this.windows = System.getProperty("os.name").indexOf("indows") != -1;
        TraceMgr.trace(this, "OS windows? " + windows);
        this.webServerRunning = checkIfWebServerRunning();
        initLicense();
    }


    void startWebServer () {
        try {
            TraceMgr.trace(this, "startWebServer");

            if (this.webServerRunning) {
                console.showStatus("Web server is running");
                return;
            }

            if (firstTime && windows == false) {
                firstTime = false;
            }

            console.showStatus("Starting web server...");
            Utilities.execProcess(getStartServerCmdLine(), true);

            int attempt = 5;
            while (attempt > 0) {
                if (checkIfWebServerRunning())
                    break;
                Thread.currentThread().sleep(3000);
                attempt--;
            }
            if (attempt > 0) {
                this.webServerRunning = true;
                console.showStatus("Web server is running");
            }
            else {
                console.showStatus("Failed to start web server");
            }
        }
        catch (Exception x) {
            TraceMgr.trace(this, "Failed to run test web server", x);
            JOptionPane.showMessageDialog(console, TraceMgr.getExceptionText("Failed to run test web server process", x), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    void stopWebServer () {
        TraceMgr.trace(this, "stopWebServer");

        try {
            if (checkIfWebServerRunning() == true) {
                console.showStatus("Stopping web server...");
                Utilities.execProcess(getStopServerCmdLine(), true);
            }
            console.showStatus("Web server stopped");
        }
        catch (Exception x) {
            TraceMgr.trace(this, "Failed to stop test web server", x);
            JOptionPane.showMessageDialog(console, TraceMgr.getExceptionText("Failed to run test web server process", x), "Error", JOptionPane.ERROR_MESSAGE);
        }

        this.webServerRunning = false;
    }


    String getStartServerCmdLine () {
        return  windows? START_SERVER_CMDLINE_WIN : START_SERVER_CMDLINE_UNIX;
    }


    String getStopServerCmdLine () {
        return  windows? STOP_SERVER_CMDLINE_WIN : STOP_SERVER_CMDLINE_UNIX;
    }


    void setServerDirty (boolean dirty) {
        if (dirty && webServerRunning) {
            stopWebServer();
        }
    }


    public boolean isServerRunning () {
        return  this.webServerRunning;
    }


    public void initLicense() {
        AppProperties licenseProps = ClientAgentBase.initLicense(true);
        LicenseManager.initInstance(licenseProps);  
    }


    public boolean checkIfWebServerRunning() {
        try {
            console.showStatus("Checking if web server is running...");
            // Trying to connect to the webserver to make sure it's up
            URL url = new URL("http://0.0.0.0:$PORT");
            URLConnection con = url.openConnection();
            java.io.InputStream is = con.getInputStream();
            is.close();
            console.showStatus("Web server is running");
            return true;
        }
        catch (Throwable x) {
            TraceMgr.trace(this, "WebServer check failed with exception " + x.getMessage());
        }
        console.showStatus("Web server is not running");
        return false;
    }


    boolean webServerRunning = false;
    boolean firstTime = true;
    AjaxSwingConsole console;
    boolean windows = true;
    Registry registry;

    // Assumes current directory to be wcapps
    public static final String START_SERVER_CMDLINE_WIN = "..\\bin\\startServer.bat";
    public static final String STOP_SERVER_CMDLINE_WIN = "..\\bin\\stopServer.bat";
    public static final String START_SERVER_CMDLINE_UNIX = "../bin/startServer.sh";
    public static final String STOP_SERVER_CMDLINE_UNIX = "../bin/stopServer.sh";

}

