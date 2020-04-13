package com.creamtec.ajaxswing.ui;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.creamtec.ajaxswing.gui.VersionHelper;


public class AboutDialog extends JDialog {
    JPanel panel1 = new JPanel();
    JLabel lblTitle = new JLabel();
    JLabel lblEdition = new JLabel();
    JLabel jLabel3 = new JLabel();
    JButton jButton2 = new JButton();
    JLabel jLabel4 = new JLabel();
    JLabel lblLogo = new JLabel();
    ImageIcon imgLogo;
    protected GridBagLayout gridBagLayout1 = new GridBagLayout();
    private JLabel lblSupportedJDK = new JLabel();

    public AboutDialog(Frame frame, String title, boolean modal) {
        super(frame, title, modal);
        try {
            imgLogo = new ImageIcon("images/logo.gif");
            jbInit();
            this.setSize(360, 250);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public AboutDialog() {
        this(null, "About AjaxSwing", false);
    }

    void jbInit() throws Exception {
        panel1.setLayout(gridBagLayout1);
        lblTitle.setFont(new java.awt.Font("Dialog", 1, 22));
        lblTitle.setText("AjaxSwing");
        lblEdition.setText(AjaxSwingConsole.getEditionString());
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel3.setText("<html>Copyright 2000-2012 by CreamTec, LLC</html>");
        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton2_actionPerformed(e);
            }
        });
        jLabel4.setText("<html><center>For more information visit us at <b>http://creamtec.com/ajaxswing</b></html>");
        lblLogo.setIcon(this.imgLogo);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        lblSupportedJDK.setText("Built for JDK version " + VersionHelper.SUPPORTED_JDK_VERSION);
        getContentPane().add(panel1, null);
        panel1.add(lblEdition,  new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
        panel1.add(lblTitle,  new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
        panel1.add(lblLogo,  new GridBagConstraints(1, 0, 1, 2, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
        panel1.add(jLabel4,  new GridBagConstraints(0, 3, 2, 1, 1.0, 1.0
            ,GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 0, 5), 0, 0));
        panel1.add(jLabel3,  new GridBagConstraints(0, 4, 2, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
        panel1.add(jButton2,  new GridBagConstraints(0, 5, 2, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20, 5, 5, 5), 0, 0));
        panel1.add(lblSupportedJDK,    new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
    }

    void jButton2_actionPerformed(ActionEvent e) {
        this.dispose();
    }
}
