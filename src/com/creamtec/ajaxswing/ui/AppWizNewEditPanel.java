/**
 * Copyright 2000 by CreamTec, LLC. All rights reserved.
 */
package com.creamtec.ajaxswing.ui;

import  java.awt.*;
import  javax.swing.*;
import  java.awt.event.*;
import  javax.swing.border.*;
import  com.creamtec.core.*;
import  java.io.*;


public class AppWizNewEditPanel
    extends AppWizPanel
{

    GridBagLayout gridBagLayout1 = new GridBagLayout();
    Border border1;
    ButtonGroup newEditGroup = new ButtonGroup();
    JRadioButton rbtNew = new JRadioButton();
    JLabel lblAppName = new JLabel();
    JTextField txfName = new JTextField();
    JRadioButton rbtEdit = new JRadioButton();
    JList lstApps = new JList();
    JScrollPane spnApps = new JScrollPane();


    public AppWizNewEditPanel () {
        try {
            jbInit();
            newEditGroup.add(rbtNew);
            newEditGroup.add(rbtEdit);
            String[] files = AjaxSwingConsole.getAppsList();

            if (files != null && files.length != 0) {
                lstApps.setListData(files);
                lstApps.setSelectedIndex(0);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void jbInit ()
        throws Exception
    {
        border1 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        rbtNew.setSelected(true);
        rbtNew.setText("New application");
        rbtNew.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed (ActionEvent e) {
                rbtNew_actionPerformed(e);
            }

        });
        this.setLayout(gridBagLayout1);
        this.setBorder(border1);
        lblAppName.setMinimumSize(new Dimension(240, 26));
        lblAppName.setPreferredSize(new Dimension(240, 26));
        lblAppName.setText("<html>Application Name. <font size=2><i>Used as application URL, so must contain no spaces or control characters</i></font></html>");
        rbtEdit.setText("Edit existing application");
        rbtEdit.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed (ActionEvent e) {
                rbtEdit_actionPerformed(e);
            }

        });
        lstApps.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.add(rbtNew, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        this.add(lblAppName, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 18, 0, 0), 0, 0));
        this.add(rbtEdit, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        this.add(txfName, new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 18, 0, 0), 0, 0));
        this.add(spnApps, new GridBagConstraints(1, 5, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 18, 0, 0), 0, 0));
        spnApps.getViewport().add(lstApps, null);
    }


    /*	void btnBrowseDir_actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			this.txfDir.setText(chooser.getSelectedFile().getAbsolutePath());
		}
	}

	void btnBrowseFile_actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
		chooser.addChoosableFileFilter(null);
		chooser.addChoosableFileFilter(new javax.swing.filechooser.FileFilter() {
			public boolean accept(File f) {
				if(f.getName().toLowerCase().indexOf(".properties") != -1)
					return true;
				else
					return false;
			}

			public String getDescription() {
				return "Application properties files (*.properties)";
			}
		});

		if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			this.txfFileName.setText(chooser.getSelectedFile().getAbsolutePath());
		}
	}
*/
    public boolean apply ()
        throws Exception
    {
        TraceMgr.trace(this, "apply");

        if (rbtNew.isSelected() == true) {
            if (this.txfName.getText().length() == 0) {
                throw  new Exception("Application name is missing");
            }

            String fileName = Utilities.getConfFullPath(txfName.getText() + ".properties");

            if (new File(fileName).exists()) {
                if (JOptionPane.showConfirmDialog(this, "Do you want to overwrite the existing file?", "Confirm", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                    return  false;
                }
            }

            getWizard().props = new AppProperties(Utilities.getConfFullPath("default.properties"));
        }
        else {
            if (this.lstApps.getSelectedValue() == null) {
                throw  new Exception("Please select the application that you want to edit");
            }

            String fileName = Utilities.getConfFullPath(this.lstApps.getSelectedValue().toString() + ".properties");
            getWizard().props = new AppProperties(fileName);
        }

        this.wizard.setDirty(true);
        return  true;
    }


    void rbtNew_actionPerformed (ActionEvent e) {
        onNew();
    }


    void rbtEdit_actionPerformed (ActionEvent e) {
        onEdit();
    }


    void onNew () {
        getWizard().isNew = true;
        this.rbtNew.setSelected(true);
        this.txfName.setEnabled(true);
        this.lstApps.setEnabled(false);
        getWizard().btnFinish.setEnabled(false);

        for (int i = 2; i < getWizard().tabPane.getComponentCount(); i++) {
            getWizard().tabPane.setEnabledAt(i, false);
        }
    }


    void onEdit () {
        getWizard().isNew = false;
        this.txfName.setEnabled(false);
        this.lstApps.setEnabled(true);
        getWizard().btnFinish.setEnabled(true);

        for (int i = 2; i < getWizard().tabPane.getComponentCount(); i++) {
            getWizard().tabPane.setEnabledAt(i, true);
        }
    }

}

