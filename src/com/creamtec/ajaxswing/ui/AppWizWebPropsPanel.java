/**
 * Copyright 2000 by CreamTec, LLC. All rights reserved.
 */

package com.creamtec.ajaxswing.ui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import com.creamtec.ajaxswing.core.*;


public class AppWizWebPropsPanel extends AppWizPanel {
	GridBagLayout gridBagLayout1 = new GridBagLayout();
	JLabel lblAppName = new JLabel();
	JPanel pnlInProcess = new JPanel();
	Border border1;
	JRadioButton rbtInProcess = new JRadioButton();
	FlowLayout flowLayout1 = new FlowLayout();
	JRadioButton rbtStandAlone = new JRadioButton();
	ButtonGroup inProcessGroup = new ButtonGroup();
    protected JLabel lblTheme = new JLabel();
    protected JComboBox cmbTheme = new JComboBox();

	public AppWizWebPropsPanel() {
		try  {
			jbInit();
			inProcessGroup.add(rbtInProcess);
			inProcessGroup.add(rbtStandAlone);

            String[] items = new String[] {"Light"};
            this.cmbTheme.setModel(new DefaultComboBoxModel(items));

		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		border1 = BorderFactory.createEmptyBorder(5,5,5,5);
		this.setLayout(gridBagLayout1);
		lblAppName.setText("<html><font color=red>*</font>InProcess clients. <font size=2><i> " +
	"Indicates whether the multiple instances of client application will be sharing the same shared JVM " +
	"process, or each client will get its own stand alone JVMs (<b>recommended for production</b>)</i></html>");
		this.setBorder(border1);
		rbtInProcess.setText("InProcess in shared JVM");
		pnlInProcess.setLayout(flowLayout1);
		flowLayout1.setAlignment(FlowLayout.LEFT);
        rbtStandAlone.setText("Stand Alone JVM");
		lblTheme.setAlignmentY((float) 0.0);
        lblTheme.setMinimumSize(new Dimension(135, 28));
        lblTheme.setText("<html>Theme <font size=2><i>(colors, fonts)</i></font>:");
        this.add(lblAppName,  new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		this.add(pnlInProcess,  new GridBagConstraints(0, 1, 2, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		pnlInProcess.add(rbtInProcess, null);
		pnlInProcess.add(rbtStandAlone, null);
        this.add(lblTheme,  new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(15, 0, 0, 0), 0, 0));
        this.add(cmbTheme,  new GridBagConstraints(1, 3, 1, 1, 0.0, 1.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(15, 0, 0, 0), 0, 0));
	}

	public void activate() throws Exception {
		this.rbtInProcess.setSelected(getWizard().props.getBooleanProperty(AjaxSwingProperties.ROUTER_IN_PROCESS, true));
		this.cmbTheme.setSelectedItem(getWizard().props.getProperty(AjaxSwingProperties.HTML_THEME, "AjaxSwing"));
	}

	public boolean apply() throws Exception {
		getWizard().props.setBooleanProperty(AjaxSwingProperties.ROUTER_IN_PROCESS, this.rbtInProcess.isSelected());
		getWizard().props.setProperty(AjaxSwingProperties.HTML_THEME, (String)this.cmbTheme.getSelectedItem());

		return true;
	}

}

