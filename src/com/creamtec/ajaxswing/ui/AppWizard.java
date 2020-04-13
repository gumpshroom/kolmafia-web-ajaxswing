/**
 * Copyright 2000 by CreamTec, LLC. All rights reserved.
 */
package com.creamtec.ajaxswing.ui;

import  java.awt.*;
import  java.awt.event.*;
import  javax.swing.*;
import  javax.swing.border.*;
import  com.creamtec.core.*;
import  java.io.*;
import  com.creamtec.ajaxswing.core.AjaxSwingProperties;
import  javax.swing.event.*;
import  java.beans.*;
import  java.util.*;


public class AppWizard
    extends JDialog
{
    boolean committed = false;
    JPanel panel1 = new JPanel();
    JButton btnPrevious = new JButton();
    JButton btnCancel = new JButton();
    Border border1;
    JPanel jPanel1 = new JPanel();
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    JButton btnNext = new JButton();
    GridLayout gridLayout1 = new GridLayout();
    JButton btnFinish = new JButton();
    AppWizNewEditPanel newEditPanel = new AppWizNewEditPanel();
    AppWizApplicationPanel appPanel = new AppWizApplicationPanel();
    AppWizWebPropsPanel webPanel = new AppWizWebPropsPanel();
    AppWizCheckListPanel checkListPanel = new AppWizCheckListPanel();
    AppWizSummaryPanel summaryPanel = new AppWizSummaryPanel();
    protected boolean dirty = false;
    AppProperties props;
    boolean isNew = true;
    JTabbedPane tabPane = new JTabbedPane() {

        public void setSelectedIndex (int index) {
            try {
                if (tabPane.getSelectedComponent() != null) {
                    if (((AppWizPanel)tabPane.getSelectedComponent()).apply()) {
                        super.setSelectedIndex(index);
                    }

                    ((AppWizPanel)tabPane.getSelectedComponent()).activate();
                    btnNext.setEnabled(tabPane.getSelectedIndex() < tabPane.getComponentCount() - 1);
                    btnPrevious.setEnabled(true);

                    if (index == tabPane.getComponentCount() - 1) {
                        btnFinish.setEnabled(true);
                    }
                }
                else {
                    super.setSelectedIndex(index);
                }

                if (isNew && index < tabPane.getComponentCount() - 1) {
                    tabPane.setEnabledAt(index + 1, true);
                }
            }
            catch (Exception x) {
                x.printStackTrace();
                String msg = TraceMgr.getExceptionText("", x);
                JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    };


    public AppWizard (AjaxSwingConsole frame, String title, boolean modal) {
        super(frame, title, modal);
        console = frame;

        try {
            jbInit();
            this.tabPane.add("New/Edit", newEditPanel);
            this.tabPane.add("Application Properties", appPanel);
            this.tabPane.add("Web-enabling Properties", webPanel);
            this.tabPane.add("Summary", summaryPanel);
            //		this.tabPane.add("Check List", checkListPanel);
            newEditPanel.setWizard(this);
            appPanel.setWizard(this);
            webPanel.setWizard(this);
            checkListPanel.setWizard(this);
            summaryPanel.setWizard(this);
            setSize(640, 400);
            newEditPanel.onNew();
            this.getRootPane().setDefaultButton(this.btnNext);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public AppWizard (AjaxSwingConsole frame, String title) {
        this(frame, title, true);
    }


    public AppWizard (AjaxSwingConsole frame) {
        this(frame, "Application Wizard", true);
    }


    private void jbInit ()
        throws Exception
    {
        border1 = BorderFactory.createRaisedBevelBorder();
        jPanel1.setLayout(gridLayout1);
        btnPrevious.setEnabled(false);
        btnPrevious.setText("Previous");
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed (ActionEvent e) {
                btnPrevious_actionPerformed(e);
            }

        });
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed (ActionEvent e) {
                btnCancel_actionPerformed(e);
            }

        });
        panel1.setLayout(gridBagLayout1);
        btnNext.setText("Next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed (ActionEvent e) {
                btnNext_actionPerformed(e);
            }

        });
        btnFinish.setEnabled(false);
        btnFinish.setText("Finish");
        btnFinish.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed (ActionEvent e) {
                btnFinish_actionPerformed(e);
            }

        });
        this.setTitle("Application Wizard");
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {

            public void windowClosing (WindowEvent e) {
                this_windowClosing(e);
            }


            public void windowOpened (WindowEvent e) {
                this_windowOpened(e);
            }

        });
        panel1.add(jPanel1, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(4, 8, 4, 8), 0, 0));
        jPanel1.add(btnPrevious, null);
        jPanel1.add(btnNext, null);
        jPanel1.add(btnCancel, null);
        jPanel1.add(btnFinish, null);
        panel1.add(tabPane, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        getContentPane().add(panel1);
    }


    void btnPrevious_actionPerformed (ActionEvent e) {
        tabPane.setSelectedIndex(tabPane.getSelectedIndex() - 1);
    }


    void checkAndClose () {
        if (isDirty()) {
            if (JOptionPane.showConfirmDialog(this, "Changes are not saved. Are you sure you want to close?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                dispose();
            }
        }
        else {
            dispose();
        }
    }


    boolean isDirty () {
        return  dirty;
    }


    void setDirty (boolean dirty) {
        this.dirty = dirty;
    }


    void btnNext_actionPerformed (ActionEvent e) {
        tabPane.setSelectedIndex(tabPane.getSelectedIndex() + 1);
    }


    void btnCancel_actionPerformed (ActionEvent e) {
        checkAndClose();
    }


    void this_windowClosing (WindowEvent e) {
        checkAndClose();
    }


    void btnFinish_actionPerformed (ActionEvent e) {
        try {
            if (tabPane.getSelectedComponent() != null) {
                if (((AppWizPanel)tabPane.getSelectedComponent()).apply()) {
                    doSave();
                    this.committed = true;
                    dispose();
                }
            }
        }
        catch (Exception x) {
            x.printStackTrace();
            String msg = TraceMgr.getExceptionText("", x);
            JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    void doSave () {
        try {
            FileOutputStream fos = new FileOutputStream(getAppPropertiesFileName());
            Properties keysToSave = getKeysToSave();
            keysToSave.store(fos, "AjaxSwing properties file");
            fos.close();

            String fileName = getAppName();
            if (fileName.indexOf('.') != -1) {
                fileName = fileName.substring(0, fileName.indexOf('.'));
            }

            String appURL = "http://0.0.0.0:8040/ajaxswing/apps/" + fileName;
            JTextArea txaMsg = new JTextArea("Properties file has been successfully saved.\n" +
                "If your web server is configured, you can access it via a URL similar to this\n" +
                appURL + "\n" +
                "Would you like to preview your application using built-in web server?");
            txaMsg.setBackground(SystemColor.window);
            txaMsg.setEditable(false);

            if (JOptionPane.showConfirmDialog(this, txaMsg, "Success", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                // Shutdown the server if it was running and restart it so that changes can take effect
                if (console.webServerManager.webServerRunning)
                    console.mniStopWebserver_actionPerformed(null);

                console.mniStartWebserver_actionPerformed(null);
                console.showStatus("Type " + appURL + " in the browser to view your application");

                if (console.webServerManager.webServerRunning) {
                    if (console.webServerManager.windows) {
                        Runtime.getRuntime().exec("..\\bin\\previewApp.bat " + appURL);
                    }
                    else {
                        Runtime.getRuntime().exec("netscape " + appURL);
                    }
                }
            }
        }
        catch (Exception x) {
            x.printStackTrace();
            String msg = TraceMgr.getExceptionText("Error", x);
            JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getAppName() {
        if (newEditPanel.rbtNew.isSelected()) {
            return newEditPanel.txfName.getText();
        }
        else {
            return newEditPanel.lstApps.getSelectedValue().toString();
        }
    }

    public String getAppPropertiesFileName() {
        String fileName = Utilities.getConfFullPath(getAppName() + ".properties");
        File file = new File(fileName);
        try {
            return file.getCanonicalPath();
        }
        catch (Exception x) {
            x.printStackTrace();
        }
        return file.getAbsolutePath();
    }

    /**
     * Save only keys that are new or different from default
     */
    public Properties getKeysToSave() throws Exception {
        AppProperties defaultProps = new AppProperties(Utilities.getConfFullPath("default.properties"));

        Set keySet = this.props.keySet();
        Object[] keys = keySet.toArray();
        Arrays.sort(keys);

        Properties keysToSave = new Properties();
        for (int i = 0; i < keys.length; i++) {
            Object defaultValue = defaultProps.get(keys[i]);
            String newValue = this.props.get(keys[i]).toString();
            if (newValue.equals(defaultValue) == false)
                keysToSave.put(keys[i], newValue);
        }

        return keysToSave;
    }


    AjaxSwingConsole console;


    void this_windowOpened (WindowEvent e) {
        this.newEditPanel.txfName.requestFocus();
    }

}

