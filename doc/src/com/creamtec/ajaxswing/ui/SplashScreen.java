package com.creamtec.ajaxswing.ui;

import java.awt.*;


public class SplashScreen
    extends Frame
{

    protected Image imgLogo = null;
    protected Label lblTitle = new Label();
    protected Label label1 = new Label();
    protected Label label2 = new Label();
    protected Label label3 = new Label();
    protected Label label4 = new Label();
    protected Label label5 = new Label();
    protected Label label6 = new Label();
    public Label lblStatus = new Label();
    protected Panel pnlLogo = new Panel() {

        public void paint (Graphics g) {
            if (imgLogo != null) {
                g.drawImage(imgLogo, 0, 0, null);
            }
        }

    };


    public SplashScreen (String title) {
        super(title);
        try {
            jbInit();
            setIconImage(Toolkit.getDefaultToolkit().getImage("images/AjaxSwing.gif"));
            imgLogo = Toolkit.getDefaultToolkit().getImage("images/logo.gif");
            MediaTracker tracker = new MediaTracker(this);
            tracker.addImage(imgLogo, 0);
            tracker.waitForAll();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void jbInit ()
        throws Exception
    {
        this.setLayout(null);
        this.setBackground(SystemColor.control);
        lblTitle.setBounds(new Rectangle(14, 27, 152, 26));
        lblTitle.setFont(new java.awt.Font("Dialog", 1, 14));
        lblTitle.setText("AjaxSwing Console");
        label1.setBounds(new Rectangle(17, 64, 211, 23));
        label1.setText("To test AjaxSwing configuration");
        label2.setText("1. Start built-in web server");
        label2.setBounds(new Rectangle(37, 85, 289, 23));
        label3.setBounds(new Rectangle(37, 106, 304, 23));
        label3.setText("2. Select AjaxSwingDemo from View menu");
        label4.setText("2. Select your application name from View menu");
        label4.setBounds(new Rectangle(37, 188, 301, 23));
        label5.setBounds(new Rectangle(37, 167, 211, 23));
        label5.setText("1. Run Application Wizard");
        label6.setText("To web-enable your application");
        label6.setBounds(new Rectangle(17, 147, 211, 23));
        lblStatus.setBounds(new Rectangle(14, 230, 334, 20));
        lblStatus.setFont(new java.awt.Font("Dialog", 2, 12));
        lblStatus.setAlignment(1);
        lblStatus.setText("Loading...");
        pnlLogo.setBounds(new Rectangle(280, 27, 64, 40));
        this.add(label4, null);
        this.add(label6, null);
        this.add(label5, null);
        this.add(lblStatus, null);
        this.add(label1, null);
        this.add(label2, null);
        this.add(label3, null);
        this.add(lblTitle, null);
        this.add(pnlLogo, null);
        this.setSize(370, 270);
    }

}

