package com.creamtec.ajaxswing.ui;

import  java.awt.*;
import  javax.swing.*;
import  java.net.*;
import  java.awt.event.*;
import  java.io.*;

import  com.creamtec.core.*;
import  com.creamtec.ajaxswing.core.*;
import  com.creamtec.ajaxswing.gui.VersionHelper;


public class AjaxSwingConsole
    extends JFrame
{
    Icon icnAppWizard = new ImageIcon("images/AppWizard.gif");
    Icon icnStartServer = new ImageIcon("images/StartServer.gif");
    Icon icnStopServer = new ImageIcon("images/StopServer.gif");
    Icon icnAjaxSwingDemo = new ImageIcon("images/AjaxSwingDemo.gif");
    int viewMenuItemCount = 0;
    JScrollPane scrPane = new JScrollPane();
    JEditorPane edpBody = new JEditorPane();
    JMenuBar mnbMain = new JMenuBar();
    JMenu mnuFile = new JMenu();
    JMenuItem mniExit = new JMenuItem();
    JMenu mnuHelp = new JMenu();
    JRadioButtonMenuItem rbtSetup = new JRadioButtonMenuItem();
    JRadioButtonMenuItem rbtLicense = new JRadioButtonMenuItem();
    JMenu mnuTools = new JMenu();
    JMenuItem mniAbout = new JMenuItem();
    JMenuItem mniAppWizard = new JMenuItem();
    ButtonGroup viewGroup = new ButtonGroup();
    JToolBar toolBar = new JToolBar();
    JButton btnWizard = new JButton();
    JMenuItem mniStartWebserver = new JMenuItem();
    JMenuItem mniStopWebserver = new JMenuItem();
    JLabel statusBar = new JLabel();


    public AjaxSwingConsole ()
        throws Exception
    {
        try {
            jbInit();
            viewGroup.add(this.rbtLicense);
            viewGroup.add(this.rbtSetup);
            Image icon = Toolkit.getDefaultToolkit().getImage("images/AjaxSwing.gif");

            if (icon != null) {
                this.setIconImage(icon);
            }

            this.reloadViewMenu();

            webServerManager = new WebServerManager(this);
            updateWebServerRunningUI();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main (String[] args)
        throws Exception
    {
        String javaHome = System.getProperty("env.JAVA_HOME");
        if (javaHome == null || javaHome.length() == 0) {
            JOptionPane.showMessageDialog(null, "<html>JAVA_HOME environment variable should be set to point to JDK installation directory.<br>Built-in web server may fail.</html>",
            "Warning", JOptionPane.WARNING_MESSAGE);
        }

        TraceMgr.setLoadSettings(false);
        try {
            VersionHelper.init(false); // No GUI init
        }
        catch (SecurityException x) {
            // Security exception will come under JDK 1.4 because we are trying to load classes from non bootclasspath into java.awt package
        }
        catch (Exception x) {
            String message = "<html>" + x.getMessage() + "</html>";
            if (JOptionPane.showConfirmDialog(null,
                    message,
                    "Do you want to continue?", JOptionPane.ERROR_MESSAGE) != JOptionPane.OK_OPTION)
                System.exit(1);
        }
        final SplashScreen splash = showSplashScreen();
        final AjaxSwingConsole console = new AjaxSwingConsole();
        console.setSize(800, 600);
        console.displayFile(".." + File.separator + "license.txt");
        console.setVisible(true);

        reshowSplashScreen(splash);
        splash.addFocusListener(new FocusAdapter() {
            int skipTimes = 1;

            public void focusLost (FocusEvent e) {
                if (skipTimes-- > 0) {
                    splash.toFront();
                    splash.requestFocus();
                }
                else {
                    splash.dispose();
                }
            }

        });
    }

    public static void reshowSplashScreen(final SplashScreen splash) {
        SwingUtilities.invokeLater(new Runnable () {
            public void run() {
                splash.lblStatus.setText("Ready. Click outside of this window to dismiss it");
                splash.toFront();

                // Spawn a thread that will dispose the splash screen
                new Thread() {
                    public void run() {
                        try {
                            Thread.sleep(5000);
                        }
                        catch (Exception x) {}
                        splash.dispose();
                    }
                }.start();
            }
        });
    }


    static SplashScreen showSplashScreen () {
        final SplashScreen splash = new SplashScreen("AjaxSwing Console");
        splash.setLocationRelativeTo(null);
        splash.addWindowListener(new WindowAdapter() {

            public void windowClosing (WindowEvent e) {
                splash.dispose();
            }

        });
        splash.setVisible(true);
        return  splash;
    }


    private void jbInit ()
        throws Exception
    {
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setJMenuBar(mnbMain);
        this.setTitle("AjaxSwing Console");
        this.addWindowListener(new java.awt.event.WindowAdapter() {

            public void windowClosed (WindowEvent e) {
                this_windowClosed(e);
            }

        });
        edpBody.setText("jEditorPane1");
        edpBody.setEditable(false);
        mnuFile.setText("File");
        mniExit.setText("Exit");
        mniExit.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed (ActionEvent e) {
                mniExit_actionPerformed(e);
            }

        });
        mniStopWebserver.setEnabled(false);
        mniStopWebserver.setActionCommand("StopServer");
        mnuHelp.setText("Help");
        rbtSetup.setText("Setup and Application Configuration instructions");
        rbtSetup.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed (ActionEvent e) {
                rbtSetup_actionPerformed(e);
            }

        });
        rbtLicense.setSelected(true);
        rbtLicense.setText("License Agreement");
        rbtLicense.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed (ActionEvent e) {
                rbtLicense_actionPerformed(e);
            }

        });
        mnuTools.setText("Tools");
        mniAbout.setText("About...");
        mniAbout.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed (ActionEvent e) {
                mniAbout_actionPerformed(e);
            }

        });
        mniAppWizard.setText("Application Wizard...");
        mniAppWizard.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed (ActionEvent e) {
                mniAppWizard_actionPerformed(e);
            }

        });

        btnWizard.setIcon(icnAppWizard);
        btnWizard.setText("App Wizard");
        btnWizard.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed (ActionEvent e) {
                btnWizard_actionPerformed(e);
            }

        });
        mniStartWebserver.setActionCommand("StartServer");
        mniStartWebserver.setText("Start Web Server");
        mniStartWebserver.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed (ActionEvent e) {
                mniStartWebserver_actionPerformed(e);
            }

        });
        mniStopWebserver.setText("Stop Web Server");
        mniStopWebserver.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed (ActionEvent e) {
                mniStopWebserver_actionPerformed(e);
            }

        });
        statusBar.setIconTextGap(10);
        statusBar.setText("Ready");
        btnStartServer.setActionCommand("StartServer");
        btnStartServer.setIcon(icnStartServer);
        btnStartServer.setText("Start Server");
        btnStartServer.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed (ActionEvent e) {
                btnStartServer_actionPerformed(e);
            }

        });
        btnStopServer.setEnabled(false);
        btnStopServer.setActionCommand("StopServer");
        btnStopServer.setIcon(icnStopServer);
        btnStopServer.setText("Stop Server");
        btnStopServer.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed (ActionEvent e) {
                btnStopServer_actionPerformed(e);
            }

        });
        btnPreview.setActionCommand("AjaxSwingDemo");
        btnPreview.setIcon(icnAjaxSwingDemo);
        btnPreview.setText("AjaxSwingDemo");
        btnPreview.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed (ActionEvent e) {
                btnPreview_actionPerformed(e);
            }

        });
        toolBar.setEnabled(false);
        mnuView.setText("View");

        this.getContentPane().add(scrPane, BorderLayout.CENTER);
        this.getContentPane().add(toolBar, BorderLayout.NORTH);
        this.getContentPane().add(statusBar, BorderLayout.SOUTH);
        toolBar.add(btnWizard, null);
        toolBar.add(btnStartServer, null);
        toolBar.add(btnStopServer, null);
        toolBar.add(btnPreview, null);
        scrPane.getViewport().add(edpBody, null);
        mnbMain.add(mnuFile);
        mnbMain.add(mnuTools);
        mnbMain.add(mnuView);
        mnbMain.add(mnuHelp);
        mnuFile.add(mniExit);
        mnuHelp.add(rbtSetup);
        mnuHelp.add(rbtLicense);
        mnuHelp.addSeparator();
        mnuHelp.add(mniAbout);
        mnuTools.add(mniAppWizard);
        mnuTools.addSeparator();
        mnuTools.add(mniStartWebserver);
        mnuTools.add(mniStopWebserver);
        //mnuView.add(mniDemoApp);
        //mnuView.addSeparator();
    }


    private void displayFile (String fileName) {
        try {
            URL url = null;
            String prefix = "file:" + System.getProperty("user.dir") + System.getProperty("file.separator");
            url = new URL(prefix + fileName);
            this.edpBody.setPage(url);
            //			html.addHyperlinkListener(this);
        }
        catch (Exception x) {
            x.printStackTrace();
            this.edpBody.setText("Error displaying document - " + x.getMessage());
        }
    }


    void exit () {
//        if (webServerManager.webServerRunning) {
//            webServerManager.stopWebServer();
//        }

        System.exit(0);
    }


    void mniExit_actionPerformed (ActionEvent e) {
        exit();
    }


    void mniAbout_actionPerformed (ActionEvent e) {
        AboutDialog dlg = new AboutDialog();
        dlg.setLocationRelativeTo(null);
        dlg.setVisible(true);
    }


    void rbtSetup_actionPerformed (ActionEvent e) {
        try {
            this.edpBody.setPage("http://creamtec.com/products/ajaxswing/doc/setup.html");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    void rbtLicense_actionPerformed (ActionEvent e) {
        displayFile(".." + File.separator + "license.txt");
    }


    void this_windowClosed (WindowEvent e) {
        exit();
    }


    void mniAppWizard_actionPerformed (ActionEvent e) {
        AppWizard wiz = new AppWizard(this);
        Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
        wiz.setLocation(scr.width/2 - wiz.getWidth()/2, scr.height/2 - wiz.getHeight()/2);
        wiz.setVisible(true);

        if (wiz.committed == true) {
            this.reloadViewMenu();
        }
    }


    void btnWizard_actionPerformed (ActionEvent e) {
        mniAppWizard_actionPerformed(e);
    }


    static {
        // Make sure TraceMgr gets initialized with the correct directory
        if (Utilities.getRootDir() == null) {
            String dirPath = System.getProperty("user.dir");
            dirPath += (File.separatorChar + "..");
            Utilities.setRootDir(dirPath);
            TraceMgr.trace(AjaxSwingConsole.class, "conf dir = " + Utilities.getConfDir());
        }

    }


    void mniStartWebserver_actionPerformed (ActionEvent e) {
        webServerManager.startWebServer();
        updateWebServerRunningUI();
    }


    void mniStopWebserver_actionPerformed (ActionEvent e) {
        webServerManager.stopWebServer();
        updateWebServerRunningUI();
    }


    public void showStatus (String msg) {
        this.statusBar.setText(msg);
        this.statusBar.paintImmediately(0, 0, statusBar.getWidth(), statusBar.getHeight());
    }


    public void updateWebServerRunningUI() {
        boolean running = webServerManager.webServerRunning;

        mniStopWebserver.setEnabled(running);
        this.btnStopServer.setEnabled(running);
        mniStartWebserver.setEnabled(!running);
        this.btnStartServer.setEnabled(!running);
    }


    WebServerManager webServerManager;
    protected JButton btnStartServer = new JButton();
    protected JButton btnStopServer = new JButton();
    protected JButton btnPreview = new JButton();
    protected JMenuItem mniDemoApp = new JMenuItem();
    protected JMenu mnuView = new JMenu();


    void btnStartServer_actionPerformed (ActionEvent e) {
        mniStartWebserver_actionPerformed(e);
    }


    void btnStopServer_actionPerformed (ActionEvent e) {
        mniStopWebserver_actionPerformed(e);
    }


    void btnPreview_actionPerformed (ActionEvent e) {
        viewApp("http://0.0.0.0:$PORT/ajaxswing/apps/SwingSet2");
    }


    void viewApp (String appURL) {
        try {
            this.mniStartWebserver_actionPerformed(null);

            if (this.webServerManager.isServerRunning() == false) {
                return;
            }

            showStatus("Running browser...");
            Process process = null;

            if (webServerManager.windows == true) {
                process = Runtime.getRuntime().exec("..\\bin\\previewapp.bat " + appURL);
                process.waitFor();
            }
            else {
                process = Runtime.getRuntime().exec("firefox " + appURL);
            }
        }
        catch (Exception x) {
            TraceMgr.trace(this, "Failed to view application " + appURL, x);
            JOptionPane.showMessageDialog(this, TraceMgr.getExceptionText("Failed to run the browser", x), "Error", JOptionPane.ERROR_MESSAGE);
        }

        showStatus("Ready");
    }


    void reloadViewMenu () {
        if (this.viewMenuItemCount == 0) {
            this.viewMenuItemCount = this.mnuView.getItemCount();
        }
        else {
            for (int i = this.mnuView.getItemCount() - 1; i >= this.viewMenuItemCount; i--) {
                this.mnuView.remove(i);
            }
        }

        String[] apps = getAppsList();

        for (int i = 0; i < apps.length; i++) {
            JMenuItem item = new JMenuItem(apps[i]);
            item.addActionListener(this.viewAppListener);
            this.mnuView.add(item);
        }
    }


    public static String[] getAppsList () {
        File confDir = new File(Utilities.getConfDir());
        String[] files = confDir.list(new FilenameFilter() {

            public boolean accept (File dir, String name) {
                if (name.indexOf(".properties") == -1) {
                    return  false;
                }

                if (name.equals("default.properties") == true) {
                    return  false;
                }

                RandomAccessFile file = null;

                try {
                    file = new RandomAccessFile(new File(dir, name), "rw");
                    String header = file.readLine();

                    if (header != null && header.indexOf("AjaxSwing properties") != -1) {
                        return true;
                    }
                }
                catch (Exception x) {
                    x.printStackTrace();
                }
                finally {
                    try {
                        if (file != null) {
                            file.close();
                        }
                    }
                    catch (Exception x) {

                    }
                }

                return  false;
            }

        });

        if (files == null) {
            JOptionPane.showMessageDialog(null, "No properties files found in configuration directory " + Utilities.getConfDir(),
                "Configuration error", JOptionPane.ERROR_MESSAGE);
            return new String[0];
        }

        String[] apps = new String[files.length];

        for (int i = 0; i < files.length; i++) {
            apps[i] = files[i].substring(0, files[i].indexOf('.'));
        }

        return  apps;
    }


    ActionListener viewAppListener = new ActionListener() {

        public void actionPerformed (ActionEvent e) {
            viewApp("http://0.0.0.0:$PORT/ajaxswing/apps/" + e.getActionCommand());
        }

    };

    public static String getEditionString () {
        String version;

        if (AjaxSwingSecurityManager.EDITION_TYPE == AjaxSwingSecurityManager.EDITION_PRO) {
            version = "Professional";
        }
        else if (AjaxSwingSecurityManager.EDITION_TYPE == AjaxSwingSecurityManager.EDITION_ENT) {
            version = "Enterprise";
        }
        else if (AjaxSwingSecurityManager.EDITION_TYPE == AjaxSwingSecurityManager.EDITION_UNL) {
            version = "Unlimited";
        }
        else {
            version = "Standard";
        }

        return  version + " Edition, version " + VersionHelper.getAjaxSwingVersion();
    }


}

