/**
 * Copyright 2000 by CreamTec, LLC. All rights reserved.
 */
package com.creamtec.ajaxswing.ui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.table.*;
import com.creamtec.ajaxswing.core.AjaxSwingProperties;
import java.io.File;
import java.util.StringTokenizer;


public class AppWizApplicationPanel
    extends AppWizPanel
{

    GridBagLayout gridBagLayout1 = new GridBagLayout();
    JLabel lblMainClass = new JLabel();
    JTextField txfMainClass = new JTextField();
    JLabel lblCmdLine = new JLabel();
    JTextField txfCmdLine = new JTextField();
    Border border1;
    JLabel lblAppClassPath = new JLabel();
    JScrollPane sclClassPath = new JScrollPane();
    JTable tblClassPath = new JTable();
    JPanel jPanel1 = new JPanel();
    GridBagLayout gridBagLayout2 = new GridBagLayout();
    JButton btnAdd = new JButton();
    JButton btnRemove = new JButton();
    DefaultTableModel pathModel = new DefaultTableModel(new String[] {"Path to a directory or a .jar"},0) {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    protected JLabel lblAppletSize = new JLabel();
    protected JTextField txfWidth = new JTextField();
    protected JLabel jLabel3 = new JLabel();
    protected JTextField txfHeight = new JTextField();
    protected JPanel jPanel2 = new JPanel();


    public AppWizApplicationPanel () {
        try {
            jbInit();
            tblClassPath.setModel(pathModel);
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
        lblMainClass.setText("<html><font color=red>*</font>Main class. <font size=2><i>Serves as the entry point into the application.</i></font></html>");
        lblCmdLine.setText("<html>Application command line. <font size=2><i>Will be passed to the main class\'s main() function. "
            + "<b>Applet</b> parameters should be specified in the properties file manually, see documentation for details</i></html>");
        lblAppClassPath.setText("<html>Application CLASSPATH. <font size=2><i>When running in InProcess mode each online user will have his own copy of"
            + " classes loaded through this CLASSPATH. Visist creamtec.com/ajaxswing for more information</i></html>");
        jPanel1.setLayout(gridBagLayout2);
        btnAdd.setText("Add...");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnAdd_actionPerformed(e);
            }
        });
        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnRemove_actionPerformed(e);
            }
        });
        this.tblClassPath.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        lblAppletSize.setMinimumSize(new Dimension(300, 40));
        lblAppletSize.setText("<html>Applet size <font size=2><i>(not applicable for applications)</i></font>. Width:");
        jLabel3.setText("<html>Height:</html>");
        this.add(lblMainClass,       new GridBagConstraints(0, 0, 5, 1, 0.0, 0.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(5, 0, 0, 0), 0, 0));
        this.add(lblCmdLine,       new GridBagConstraints(0, 2, 5, 1, 0.0, 0.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(5, 0, 0, 0), 0, 0));
        this.add(txfMainClass,       new GridBagConstraints(0, 1, 5, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.add(txfCmdLine,         new GridBagConstraints(0, 3, 5, 1, 1.0, 0.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
        this.add(lblAppClassPath,          new GridBagConstraints(0, 6, 5, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 0, 0, 0), 0, 0));
        this.add(jPanel1,       new GridBagConstraints(0, 7, 5, 1, 1.0, 1.0
            ,GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        jPanel1.add(sclClassPath, new GridBagConstraints(0, 0, 1, 2, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), -64, -325));
        jPanel1.add(btnAdd, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 0, 0), 0, 0));
        jPanel1.add(btnRemove, new GridBagConstraints(1, 1, 1, 1, 0.0, 1.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));
        this.add(lblAppletSize,          new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.add(jLabel3,      new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 5, 0));
        this.add(txfWidth,  new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 10), 60, 0));
        this.add(txfHeight, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 60, 0));
        this.add(jPanel2, new GridBagConstraints(4, 4, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        sclClassPath.getViewport().add(tblClassPath, null);
    }


    public void activate ()
        throws Exception
    {
        this.txfMainClass.setText(getWizard().props.getProperty(AjaxSwingProperties.AGENT_START_CLASS_NAME));
        this.txfCmdLine.setText(getWizard().props.getProperty(AjaxSwingProperties.AGENT_APP_PARAMS));
        setClassPath(getWizard().props.getProperty(AjaxSwingProperties.AGENT_CLASSPATH));
    }


    public boolean apply ()
        throws Exception
    {
/*        try { -- Do not try to load the class anymore since the CLASSPATH can be old or dynamic
            Class.forName(this.txfMainClass.getText());
        }
        catch (Throwable x) {
            if (JOptionPane.showConfirmDialog(this, "Unable to load class " + this.txfMainClass.getText() + ". \nThe class should be available through CLASSPATH. \n" + "If you have changed your CLASSPATH after installing AjaxSwing \nyou need to start the console using asConsole.bat (asConsole.sh on UNIX)\n or reinstall AjaxSwing. Do not use the shortcut created by install.\n" + "Would you like to continue?", "Warning", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                return false;
            }
        }*/

        getWizard().props.setProperty(AjaxSwingProperties.AGENT_START_CLASS_NAME, this.txfMainClass.getText());
        getWizard().props.setProperty(AjaxSwingProperties.AGENT_APP_PARAMS, this.txfCmdLine.getText());

        if (this.txfWidth.getText().length() != 0) {
            Integer.parseInt(this.txfWidth.getText());
            getWizard().props.setProperty(AjaxSwingProperties.APPLET_WIDTH, this.txfWidth.getText());
        }
        if (this.txfHeight.getText().length() != 0) {
            Integer.parseInt(this.txfHeight.getText());
            getWizard().props.setProperty(AjaxSwingProperties.APPLET_HEIGHT, this.txfHeight.getText());
        }

        String classPath = getClassPath();
        if (classPath != null)
            getWizard().props.setProperty(AjaxSwingProperties.AGENT_CLASSPATH, classPath);
        return true;
    }

    void btnAdd_actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Choose a file or directory");
        chooser.setMultiSelectionEnabled(true);
        chooser.setFileSelectionMode(chooser.FILES_AND_DIRECTORIES);

        ExampleFileFilter filter = new ExampleFileFilter();
        filter.addExtension("jar");
        filter.addExtension("zip");
        filter.setDescription("Java class libraries");
        chooser.addChoosableFileFilter(filter);

        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File[] files = chooser.getSelectedFiles();
            if (files == null || files.length == 0)
                files = new File[] {chooser.getSelectedFile()};
            if (files != null) {
                for (int i = 0; i < files.length; i++)
                    this.pathModel.addRow(new String[] {getFileName(files[i])});
            }
        }
    }

    String getFileName(File file) {
        boolean isDirectory = false;
        String fileName = null;
        try {
            isDirectory = file.isDirectory();
            fileName = file.getCanonicalPath();
        }
        catch (Exception x) {
            x.printStackTrace();
            fileName = file.getAbsolutePath();
        }

        // For directories add a slash to make a valid URL for class loader
        if (isDirectory && fileName != null)
            fileName += "/";

        return fileName;
    }

    void btnRemove_actionPerformed(ActionEvent e) {
        int sel = this.tblClassPath.getSelectedRow();
        if (sel != -1)
            this.pathModel.removeRow(sel);
    }

    String getClassPath() {
        String classPath = null;
        for (int i = 0; i < this.pathModel.getRowCount(); i++) {
            if (classPath == null)
                classPath = (String)this.pathModel.getValueAt(i, 0);
            else
                classPath += ";" + (String)this.pathModel.getValueAt(i, 0);
        }
        return classPath;
    }

    void setClassPath(String classPath) throws Exception {
        // Clear the model
        while (this.pathModel.getRowCount() != 0)
            this.pathModel.removeRow(0);

        if (classPath == null)
            return;

        StringTokenizer tok = new StringTokenizer(classPath, ";");
        while (tok.hasMoreTokens()) {
            String el = tok.nextToken();
            this.pathModel.addRow(new Object[] {el});
        }
    }

}

