package com.ims.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.Firm;
import com.ims.models.IMSDB;
import com.ims.reports.ReportViewer;
import com.ims.ui.common.ChangePassword;
import com.ims.ui.common.InlineWindow;
import com.ims.ui.common.NewUser;
import com.ims.ui.common.UpdateUser;
import com.jdev.girish.app.wizard.WizardStarter;
import com.jdev.girish.calc.Calculator;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;
import com.jdev.girish.lookandfeel.LookAndFeelMenu;
import com.jdev.girish.ui.border.BackgroundImageBorder;
import com.jdev.girish.ui.iframe.IFrame;
import com.jdev.girish.ui.mdiparent.MDIParent;

public class Parent extends MDIParent {

	private static final long serialVersionUID = -8400508082685875068L;

	public static Font mfont;

	private JLabel lblUser;
	private JLabel lblTime;

	private JPopupMenu popupmenu;
		private JMenuItem mniCalc;
		private JMenu mnuLang;
			private JMenuItem mniEn;
			private JMenuItem mniMr;

	private JMenu mnuEmployee;
		private JMenuItem mniEmpEnroll;
		private JMenuItem mniEmpAdvance;
		private JMenuItem mniEmpAttend;
		private JMenuItem mniEmpAttendReport;
		private JMenu mnuEmpSal;
			private JMenuItem mniEmpSalPay;
			private JMenuItem mniEmpSalSlip;
			private JMenuItem mniEmpSalReport;
			private JMenuItem mniEmpResign;

	private JMenu mnuProd;
	private JMenuItem mniProdCat;
	private JMenuItem mniProducts;

	private JMenu mnuTranz;
	
	private JMenu mnuCustomer;
	private JMenuItem mniCustDetail;
	private JMenuItem mniCustPayment;
	
	private JMenu mnuSuppl;
	private JMenuItem mniSuppDetail;
	private JMenuItem mniSuppPayment;
	private JMenuItem mniSupPayRpt;
	
	private JMenu mnuPurchases;
	private JMenuItem mniPurchOrder;
	private JMenuItem mniPurchases;
	
	private JMenu mnuSales;
	private JMenuItem mniSalesOrder;
	private JMenuItem mniSales;
	private JMenuItem mniSalesReturn;
	
	private JMenuItem mniQuickSales;
	private JMenuItem mniExpenses;

	private JMenu mnuReports;
	private JMenuItem mniSalesRpt;
	private JMenuItem mniSalesReturnRpt;
	private JMenuItem mniStockRpt;
	private JMenuItem mniPurchRpt;
	private JMenuItem mniShopStat;
	private JMenuItem mniTestReport;
	private JMenuItem mniCustDebtReport;
	private JMenuItem mniSupCrReport;
	private JMenuItem mniExpensesReport;

	private JMenu mnuFirm;
	private JMenuItem mniFirmProf;

	private JMenu mnuUser;
	private JMenuItem mniNewUser;
	private JMenuItem mniUpdateUser;
	private JMenuItem mniChangePass;

	private ParentListener listen;

	private static String username = "";

	private boolean isadmin;
	private FirmSwitchUI fs;

	public Parent() {
		super(true);
		setTitleVisible(false);
		super.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		menubar.setBackground(new java.awt.Color(224, 208, 160, 0));

		listen = new ParentListener();
		addPropertyChangeListener(listen);
		menubar.setAutoscrolls(true);

		fs=new FirmSwitchUI();
		fs.setSize(303, 164);
		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent arg0) {
				fs.setLocation(jdp.getSize().width-fs.getSize().width-10, 20);
			}
		});
		jdp.add(fs);
		
		mnuEmployee = new JMenu(Program.LANG.getProperty("mnuemployee"));

		mnuEmployee.setFont(mfont);
		mnuEmployee.setMnemonic('e');
		mniEmpEnroll = new JMenuItem(Program.LANG.getProperty("mniempenroll"));
		mniEmpEnroll.addActionListener(listen);
		mniEmpEnroll.setFont(mfont);
		mnuEmployee.add(mniEmpEnroll);

		mniEmpAdvance = new JMenuItem(Program.LANG.getProperty("mniempadvance"));
		mniEmpAdvance.addActionListener(listen);
		mniEmpAdvance.setFont(mfont);
		mnuEmployee.add(mniEmpAdvance);

		mniEmpAttend = new JMenuItem(Program.LANG.getProperty("mniempattend"));
		mniEmpAttend.addActionListener(listen);
		mniEmpAttend.setFont(mfont);
		mnuEmployee.add(mniEmpAttend);

		mniEmpAttendReport=new JMenuItem(Program.LANG.getProperty("mniempattendancereport"));
		mniEmpAttendReport.setFont(mfont);
		mniEmpAttendReport.addActionListener(listen);
		mnuEmployee.add(mniEmpAttendReport);

		mnuEmpSal = new JMenu(Program.LANG.getProperty("mnuempsalary"));
		mnuEmpSal.setFont(mfont);

		mniEmpSalPay = new JMenuItem(Program.LANG.getProperty("mniempsalpayment"));
		mniEmpSalPay.addActionListener(listen);
		mniEmpSalPay.setFont(mfont);
		mnuEmpSal.add(mniEmpSalPay);

		mniEmpSalSlip = new JMenuItem(Program.LANG.getProperty("mniempsalslip"));
		mniEmpSalSlip.addActionListener(listen);
		mniEmpSalSlip.setFont(mfont);
		mnuEmpSal.add(mniEmpSalSlip);

		mniEmpSalReport = new JMenuItem(Program.LANG.getProperty("mniempsalreport"));
		mniEmpSalReport.setFont(mfont);
		mniEmpSalReport.addActionListener(listen);
		mnuEmpSal.add(mniEmpSalReport);

		mnuEmployee.add(mnuEmpSal);

		mniEmpResign = new JMenuItem(Program.LANG.getProperty("mniempsettle"));
		mniEmpResign.setFont(mfont);
		mniEmpResign.addActionListener(listen);
		mnuEmployee.add(mniEmpResign);
		
		menubar.add(mnuEmployee);

		mnuProd = new JMenu(Program.LANG.getProperty("mnuproduct"));
		mnuProd.setFont(mfont);
		mnuProd.setMnemonic('p');
		mniProdCat = new JMenuItem(Program.LANG.getProperty("mniprodcat"));
		mniProdCat.addActionListener(listen);
		mniProdCat.setFont(mfont);
		mnuProd.add(mniProdCat);

		mniProducts = new JMenuItem(Program.LANG.getProperty("mniproduct"));
		mniProducts.addActionListener(listen);
		mniProducts.setFont(mfont);
		mnuProd.add(mniProducts);
		menubar.add(mnuProd);

		mnuTranz = new JMenu(Program.LANG.getProperty("mnutrans"));
		mnuTranz.setMnemonic('t');
		mnuTranz.setFont(mfont);
		mnuCustomer = new JMenu(Program.LANG.getProperty("mnucust"));
		mnuCustomer.setFont(mfont);
		mniCustDetail = new JMenuItem(Program.LANG.getProperty("mnicustdetails"));
		mniCustDetail.addActionListener(listen);
		mniCustDetail.setFont(mfont);
		mnuCustomer.add(mniCustDetail);
		
		mniCustPayment=new JMenuItem(Program.LANG.getProperty("mnicustpayment"));
		mniCustPayment.addActionListener(listen);
		mniCustPayment.setFont(mfont);
		mnuCustomer.add(mniCustPayment);
		mnuTranz.add(mnuCustomer);

		mnuSuppl = new JMenu(Program.LANG.getProperty("mnusupplier"));
		mnuSuppl.setFont(mfont);
		mniSuppDetail = new JMenuItem(Program.LANG.getProperty("mnisuppdetails"));
		mniSuppDetail.addActionListener(listen);
		mniSuppDetail.setFont(mfont);
		mnuSuppl.add(mniSuppDetail);

		mniSuppPayment = new JMenuItem(Program.LANG.getProperty("mnisuppayment"));
		mniSuppPayment.addActionListener(listen);
		mniSuppPayment.setFont(mfont);
		mnuSuppl.add(mniSuppPayment);

		mniSupPayRpt = new JMenuItem(Program.LANG.getProperty("mnisuppaymentrpt"));
		mniSupPayRpt.setFont(mfont);
		mniSupPayRpt.addActionListener(listen);
		mnuSuppl.add(mniSupPayRpt);
		mnuTranz.add(mnuSuppl);

		mnuPurchases = new JMenu(Program.LANG.getProperty("mnupurch"));
		mnuPurchases.setFont(mfont);
		mniPurchOrder = new JMenuItem(Program.LANG.getProperty("mnipurchorder"));
		mniPurchOrder.addActionListener(listen);
		mniPurchOrder.setFont(mfont);
		mnuPurchases.add(mniPurchOrder);

		mniPurchases = new JMenuItem(Program.LANG.getProperty("mnipurchases"));
		mniPurchases.addActionListener(listen);
		mniPurchases.setFont(mfont);
		mnuPurchases.add(mniPurchases);
		mnuTranz.add(mnuPurchases);

		mnuSales = new JMenu(Program.LANG.getProperty("mnusales"));
		mnuSales.setFont(mfont);
		mniSalesOrder = new JMenuItem(Program.LANG.getProperty("mnisalesorder"));
		mniSalesOrder.setFont(mfont);
		mniSalesOrder.addActionListener(listen);
		mnuSales.add(mniSalesOrder);

		mniSales = new JMenuItem(Program.LANG.getProperty("mnisales"));
		mniSales.addActionListener(listen);
		mniSales.setFont(mfont);
		mnuSales.add(mniSales);
		
		mniSalesReturn = new JMenuItem(Program.LANG.getProperty("mnisalesreturn"));
		mniSalesReturn.addActionListener(listen);
		mniSalesReturn.setFont(mfont);
		mnuSales.add(mniSalesReturn);

		mnuTranz.add(mnuSales);
		menubar.add(mnuTranz);

		mniQuickSales=new JMenuItem(Program.LANG.getProperty("mniquicksales"));
		mniQuickSales.addActionListener(listen);
		mniQuickSales.setFont(mfont);
		mnuTranz.add(mniQuickSales);
		
		mniExpenses = new JMenuItem(Program.LANG.getProperty("mniexpenses"));
		mniExpenses.setFont(mfont);
		mniExpenses.addActionListener(listen);
		mnuTranz.add(mniExpenses);

		mnuReports = new JMenu(Program.LANG.getProperty("mnureport"));
		mnuReports.setMnemonic('r');
		mnuReports.setFont(mfont);
		mniPurchRpt = new JMenuItem(Program.LANG.getProperty("mnipurchrpt"));
		mniPurchRpt.addActionListener(listen);
		mniPurchRpt.setFont(mfont);
		mnuReports.add(mniPurchRpt);

		mniStockRpt = new JMenuItem(Program.LANG.getProperty("mnicurstock"));
		mniStockRpt.addActionListener(listen);
		mniStockRpt.setFont(mfont);
		mnuReports.add(mniStockRpt);

		mniSalesRpt = new JMenuItem(Program.LANG.getProperty("mnisalesrpt"));
		mniSalesRpt.addActionListener(listen);
		mniSalesRpt.setFont(mfont);
		mnuReports.add(mniSalesRpt);
		
		mniSalesReturnRpt=new JMenuItem(Program.LANG.getProperty("mnisalesreturnrpt"));
		mniSalesReturnRpt.addActionListener(listen);
		mniSalesReturnRpt.setFont(mfont);
		mnuReports.add(mniSalesReturnRpt);

		mniShopStat = new JMenuItem(Program.LANG.getProperty("mnishopstat"));
		mniShopStat.addActionListener(listen);
		mniShopStat.setFont(mfont);
		mnuReports.add(mniShopStat);
		
		mniTestReport=new JMenuItem(Program.LANG.getProperty("mnitestreport"));
		mniTestReport.addActionListener(listen);
		mniTestReport.setFont(mfont);
		mnuReports.add(mniTestReport);
		
		mniCustDebtReport=new JMenuItem(Program.LANG.getProperty("mnicustdebtreport"));
		mniCustDebtReport.setFont(mfont);
		mniCustDebtReport.addActionListener(listen);
		mnuReports.add(mniCustDebtReport);
		
		mniSupCrReport=new JMenuItem(Program.LANG.getProperty("mnisupcrreport"));
		mniSupCrReport.setFont(mfont);
		mniSupCrReport.addActionListener(listen);
		mnuReports.add(mniSupCrReport);
		
		mniExpensesReport=new JMenuItem(Program.LANG.getProperty("mniexpensesreport"));
		mniExpensesReport.setFont(mfont);
		mniExpensesReport.addActionListener(listen);
		mnuReports.add(mniExpensesReport);
		
		menubar.add(mnuReports);
		
		mnuFirm = new JMenu(Program.LANG.getProperty("mnufirm"));
		mnuFirm.setFont(mfont);
		mnuFirm.setMnemonic('f');
		mniFirmProf = new JMenuItem(Program.LANG.getProperty("mnifirmprof"));
		mniFirmProf.setFont(mfont);
		mniFirmProf.addActionListener(listen);
		mnuFirm.add(mniFirmProf);
		menubar.add(mnuFirm);

		mnuUser = new JMenu(Program.LANG.getProperty("mnuuser"));
		mnuUser.setFont(mfont);
		mnuUser.setMnemonic('u');
		mniNewUser = new JMenuItem(Program.LANG.getProperty("mninewuser"));
		mniNewUser.addActionListener(listen);
		mniNewUser.setFont(mfont);
		mnuUser.add(mniNewUser);

		mniUpdateUser = new JMenuItem(Program.LANG.getProperty("mniupdateuser"));
		mniUpdateUser.addActionListener(listen);
		mniUpdateUser.setFont(mfont);
		mnuUser.add(mniUpdateUser);

		mnuUser.addSeparator();

		mniChangePass = new JMenuItem(Program.LANG.getProperty("mnichangepass"));
		mniChangePass.addActionListener(listen);
		mniChangePass.setFont(mfont);
		mnuUser.add(mniChangePass);
		menubar.add(mnuUser);

		// TODO Toolbar
		lblUser = new JLabel();
		lblUser.setFont(mfont);

		statusbar.add(lblUser);

		statusbar.addSeparator();
		lblTime = new JLabel();
		lblTime.setFont(mfont);
		final SimpleDateFormat format = new SimpleDateFormat(
				"dd-MMM-yyyy hh:mm:ss a");
		Timer time = new Timer();
		time.schedule(new TimerTask() {
			@Override
			public void run() {
				lblTime.setText(format.format(new java.util.Date()));
			}
		}, 500, 500);

		statusbar.add(lblTime);

		statusbar.addSeparator();
		JLabel lblDev = new JLabel();
		lblDev.setText("RGS Developers : 9860911209, 9763640205, 8087810789");
		lblDev.setFont(mfont);
		lblDev.setVerticalAlignment((int) JLabel.RIGHT_ALIGNMENT);
		statusbar.add(lblDev, BorderLayout.EAST);
		super.add(statusbar, BorderLayout.SOUTH);

		// TODO JPopup Menu
		popupmenu = new JPopupMenu();
			mniCalc = new JMenuItem("Calculator");
			mniCalc.addActionListener(listen);
			mniCalc.setFont(mfont);
			popupmenu.add(mniCalc);
	
			LookAndFeelMenu laf = new LookAndFeelMenu(this);
			laf.addPropertyChangeListener(listen);
			laf.setFont(mfont);
			popupmenu.add(laf);
			
			mnuLang=new JMenu(Program.LANG.getProperty("mnulang"));
				mnuLang.setFont(mfont);
				mniEn=new JMenuItem("English");
				mniEn.setFont(mfont);
				mniEn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						IMSDB db=new IMSDB();
						try {
							if(db.executeUpdate("update application set value='en' where property='lang'")>0)
							{
								Statics.showMessage(getContentPane(), "Language Successfully Updated!"
										+ "\nYou need to restart application!", JOptionPane.INFORMATION_MESSAGE);
							}
							else if(db.execute("insert into application(property, value) values('lang', 'en')"))
							{
								Statics.showMessage(getContentPane(), "Language Successfully Updated!"
										+ "\nYou need to restart application!", JOptionPane.INFORMATION_MESSAGE);
							}
						} catch (SQLException | DatabaseNotConnectedException e) {
							Statics.handleException(e);
						}
					}
				});
				mnuLang.add(mniEn);

				mniMr=new JMenuItem("मराठी");
				mniMr.setFont(mfont);
				mniMr.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						IMSDB db=new IMSDB();
						try {
							if(db.executeUpdate("update application set value='mr' where property='lang'")>0)
							{
								Statics.showMessage(getContentPane(), "भाषा यशस्वीरित्या बदलली!"
										+ "\nतुम्हाला सॉफ्टवेअर बंद करुन पुन्हा एकदा सुरु करावे लागेल!", JOptionPane.INFORMATION_MESSAGE);
							}
							else if(db.execute("insert into application(property, value) values('lang', 'mr')"))
							{
								Statics.showMessage(getContentPane(), "भाषा यशस्वीरित्या बदलली!"
										+ "\nतुम्हाला सॉफ्टवेअर बंद करुन पुन्हा एकदा सुरु करावे लागेल!", JOptionPane.INFORMATION_MESSAGE);
							}
						} catch (SQLException | DatabaseNotConnectedException e) {
							Statics.handleException(e);
						}
					}
				});
				mnuLang.add(mniMr);
				
			popupmenu.add(mnuLang);
			
		jdp.addMouseListener(listen);
		try {
			jdp.setBorder(new BackgroundImageBorder(ImageIO.read(Statics
					.getImageStream("imsbg.jpg")), true));

			BufferedImage img = ImageIO.read(Statics.getImageStream("inv.png"));
			super.setIconImage(img);
			mnuProd.setIcon(new ImageIcon(img));
			mnuEmployee.setIcon(new ImageIcon(ImageIO.read(Statics
					.getImageStream("emp.png"))));
			mnuFirm.setIcon(new ImageIcon(ImageIO.read(Statics
					.getImageStream("shop.png"))));
			mnuTranz.setIcon(new ImageIcon(ImageIO.read(Statics
					.getImageStream("trans.png"))));
			mnuReports.setIcon(new ImageIcon(ImageIO.read(Statics
					.getImageStream("print.png"))));
			mnuUser.setIcon(new ImageIcon(ImageIO.read(Statics
					.getImageStream("lockuser.png"))));
		} catch (IOException e) {
			Statics.handleException(e);
		}
		Insets in = new Insets(0, 15, 0, 15);
		for (int i = 0; i < menubar.getMenuCount(); i++) {
			menubar.getMenu(i).setMargin(in);
			menubar.getMenu(i).setHorizontalTextPosition(JMenu.CENTER);
			menubar.getMenu(i).setVerticalTextPosition(JMenu.BOTTOM);
			menubar.getMenu(i).setForeground(java.awt.Color.DARK_GRAY);
		}

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// INFO Window close event
				if (JOptionPane.showConfirmDialog(getContentPane(),
						"Are You Sure You Want to Exit?",
						Program.APP.getProperty("APPTITLE"),
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					setVisible(false);
					dispose();
					if (WizardStarter.isDemo) {
						JOptionPane
								.showMessageDialog(null,
										"Exiting from demo mode! Please register soon for full version!");
						IMSDB db = new IMSDB();
						try {
							db.execute("SET FOREIGN_KEY_CHECKS = 0");
							DatabaseMetaData md = db.getConnectionObj()
									.getMetaData();
							ResultSet res = md.getTables(null, null, "%", null);
							while (res.next()) {
								if (res.getString(4).equalsIgnoreCase("table")) {
									String q = "truncate table "
											+ res.getString(3);
									db.execute(q);
								}
							}
							db.execute("SET FOREIGN_KEY_CHECKS = 1");
							db.execute("INSERT INTO `users`(`userid`, `pwd`, `uautho`, `uname`,`mob`) values('admin', sha1('password'), 1, 'Administrator', 'n/a')");
						} catch (DatabaseNotConnectedException | SQLException e1) {
							Statics.handleException(e1);
						}
					}
					System.exit(0);
				}
			}
		});
	}

	public static String getUserID() {
		return username;
	}

	private void addFrame(IFrame frame) {
		closeAllFrames();
		jdp.add(frame, BorderLayout.CENTER);
		frame.setTitleFont(Statics.TITLE_FONT);
		frame.setVisible(true);
		resizeIFrames();
	}

	private void checkConfig() {
		Firm firm=new Firm();
		if (firm.getFirmData()==null) {
			Statics.showMessage(getContentPane(),
					"Firm details not found! Please Update your firm details!",
					JOptionPane.ERROR_MESSAGE);
			closeAllFrames();
			addFrame(new FirmDetails_UI());
		}
		enableMenus();
	}

	private void enableMenus() {
		for (int i = 0; i < menubar.getMenuCount(); i++) {
			menubar.getMenu(i).setEnabled(true);
		}
		mniNewUser.setEnabled(isadmin);
		mniUpdateUser.setEnabled(isadmin);
		mnuEmployee.setEnabled(isadmin);
		mnuProd.setEnabled(isadmin);
		mnuFirm.setEnabled(isadmin);
		mniCustDetail.setEnabled(isadmin);
		mniSuppDetail.setEnabled(isadmin);
		mnuReports.setEnabled(isadmin);
		// TODO Enable/Disable tasks using authentication
	}

	private void showPopup(MouseEvent e) {
		if (e.isPopupTrigger()) {
			popupmenu.show(e.getComponent(), e.getX(), e.getY());
		}
	}

	private class ParentListener implements PropertyChangeListener,
			ActionListener, MouseListener {
		@Override
		public void propertyChange(PropertyChangeEvent e) {
			// INFO Login Attempt
			if (e.getPropertyName().equals("loginattempt")) {
				IMSDB db = new IMSDB();
				int auth = -1;
				try {
					ResultSet rs = db
							.executeQuery("select * from USERS where binary USERID='"
									+ user + "' and pwd=sha1('" + pass + "')");
					if (rs.next()) {
						setTitle("Inventory Management System : "
								+ rs.getString("UNAME"));
						if (WizardStarter.isDemo) {
							setTitle(getTitle() + " Warning! Demo mode!");
						}
						auth = rs.getInt("UAUTHO");
						isadmin = (auth == 0 ? false : true);
						username = user;
						lblUser.setText("User Name : " + username);
						checkConfig();
					} else {
						Statics.showMessage(
								getContentPane(),
								"User ID and/or Password did not match! Please try again!",
								JOptionPane.ERROR_MESSAGE);
						showLoginWin();
					}
				} catch (DatabaseNotConnectedException | SQLException e1) {
					Statics.handleException(e1);
					showLoginWin();
				}
			}

			// TODO Use this code when look and feel changed!
			else if (e.getPropertyName().equals("laf")) {
				for (javax.swing.JInternalFrame frame : jdp.getAllFrames()) {
					frame.setBorder(javax.swing.BorderFactory
							.createBevelBorder(3, java.awt.Color.blue,
									java.awt.Color.blue));
					((javax.swing.plaf.basic.BasicInternalFrameUI) frame
							.getUI()).setNorthPane(null);
					((IFrame) frame).jsp.setBorder(null);
				}
				resizeIFrames();
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {// INFO Action Listener

			if (e.getSource() == mniChangePass) {
				if (!isFrameOpened(ChangePassword.class.getName(), true))
					addFrame(new ChangePassword());
			}

			else if (e.getSource() == mniNewUser) {
				if (!isFrameOpened(NewUser.class.getName(), true))
					addFrame(new NewUser());
			}

			else if (e.getSource() == mniUpdateUser) {
				if (!isFrameOpened(UpdateUser.class.getName(), true))
					addFrame(new UpdateUser());
			}

			else if (e.getSource() == mniEmpEnroll) {
				if (!isFrameOpened(EmployeeM_UI.class.getName(), true))
					addFrame(new EmployeeM_UI());
			}

			else if (e.getSource() == mniEmpAdvance) {
				if (!isFrameOpened(EmployeeAdvPay_UI.class.getName(), true))
					addFrame(new EmployeeAdvPay_UI());
			}

			else if (e.getSource() == mniEmpAttend) {
				if (!isFrameOpened(EmployeeAttend_UI.class.getName(), true))
					addFrame(new EmployeeAttend_UI());
			}

			else if (e.getSource() == mniEmpSalPay) {
				if (!isFrameOpened(EmployeeSalary_UI.class.getName(), true))
					addFrame(new EmployeeSalary_UI());
			}

			else if (e.getSource() == mniEmpSalSlip) {
				if (!isFrameOpened(EmpSalSleep.class.getName(), true))
					addFrame(new EmpSalSleep());
			}

			else if (e.getSource() == mniFirmProf) {
				if (!isFrameOpened(FirmDetails_UI.class.getName(), true))
					addFrame(new FirmDetails_UI());
			}

			else if (e.getSource() == mniProdCat) {
				if (!isFrameOpened(ProdCategoryUI.class.getName(), true))
					addFrame(new ProdCategoryUI());
			}

			else if (e.getSource() == mniProducts) {
				if (!isFrameOpened(ProductsUI.class.getName(), true))
					addFrame(new ProductsUI());
			} else if (e.getSource() == mniCustDetail) {
				if (!isFrameOpened(CustomersUI.class.getName(), true))
					addFrame(new CustomersUI());
			} else if (e.getSource() == mniSuppDetail) {
				if (!isFrameOpened(SuppliersUI.class.getName(), true))
					addFrame(new SuppliersUI());
			} else if (e.getSource() == mniPurchOrder) {
				if (!isFrameOpened(PurchaseOrderUI.class.getName(), true))
					addFrame(new PurchaseOrderUI());
			} else if (e.getSource() == mniPurchases) {
				if (!isFrameOpened(PurchasesUI.class.getName(), true))
					addFrame(new PurchasesUI());
			} else if (e.getSource() == mniSales) {
				if (!isFrameOpened(SalesUI.class.getName(), true))
					addFrame(new SalesUI());
			} else if(e.getSource()==mniSalesReturn) {
				if(!isFrameOpened(SalesReturnUI.class.getName(), true))
					addFrame(new SalesReturnUI());
			} else if (e.getSource() == mniSalesRpt) {
				if (!isFrameOpened(SalesReportUI.class.getName(), true))
					addFrame(new SalesReportUI());
			} else if (e.getSource()==mniSalesReturnRpt) {
				if(!isFrameOpened(SalesReturnReportUI.class.getName(), true))
					addFrame(new SalesReturnReportUI());
			} else if (e.getSource() == mniExpenses) {
				if (!isFrameOpened(ExpensesUI.class.getName(), true))
					addFrame(new ExpensesUI());
			} else if (e.getSource() == mniStockRpt) {
				Firm firm = new Firm();
				HashMap<String, Object> map = new HashMap<>();
				map.put("prmfirmid", firm.getFirmData().firmid);
				ReportViewer view = new ReportViewer("stock", map,
						firm.getConnectionObj());
				view.showReport();
			} else if (e.getSource() == mniPurchRpt) {
				if (!isFrameOpened(PurchaseReportUI.class.getName(), true))
					addFrame(new PurchaseReportUI());
			} else if (e.getSource() == mniEmpSalReport) {
				if (!isFrameOpened(EmpSalReportUI.class.getName(), true))
					addFrame(new EmpSalReportUI());
			} else if (e.getSource() == mniSalesOrder) {
				if (!isFrameOpened(SalesOrderUI.class.getName(), true))
					addFrame(new SalesOrderUI());
			} else if (e.getSource() == mniCalc) {
				InlineWindow win = new InlineWindow();
				win.setIconImage(getIconImage());
				win.setTitle("IMS : Calculator");
				win.setModal(false);
				win.setAlwaysOnTop(true);
				Calculator calc = new Calculator();
				win.setContentPane(calc);
				win.setBounds(100, 100, calc.getSize().width + 15,
						calc.getSize().height + 15);
				win.setVisible(true);
			} else if (e.getSource() == mniSuppPayment) {
				if (!isFrameOpened(SupplierPaymentUI.class.getName(), true))
					addFrame(new SupplierPaymentUI());
			} else if (e.getSource() == mniSupPayRpt) {
				if (!isFrameOpened(SupPayReportUI.class.getName(), true))
					addFrame(new SupPayReportUI());
			} else if (e.getSource() == mniShopStat) {
				if (!isFrameOpened(ShopStatus.class.getName(), true))
					addFrame(new ShopStatus());
			} else if (e.getSource() == mniEmpResign) {
				if (!isFrameOpened(EmployeeResignUI.class.getName(), true))
					addFrame(new EmployeeResignUI());
			}
			else if(e.getSource()==mniCustPayment)
			{
				if(!isFrameOpened(CustPaymentUI.class.getName(), true))
					addFrame(new CustPaymentUI());
			}
			else if(e.getSource()==mniTestReport)
			{
				if(!isFrameOpened(TestReportEntryUI.class.getName(), true))
					addFrame(new TestReportEntryUI());
			}
			else if(e.getSource()==mniCustDebtReport)
			{
				HashMap<String, Object> map=new HashMap<>();
				map.put("prmfirmid", new Firm().getFirmData().firmid);
				ReportViewer view=new ReportViewer("custdebt", map, new IMSDB().getConnectionObj());
				view.showReport();
			}
			else if (e.getSource()==mniSupCrReport) {
				HashMap<String, Object> map=new HashMap<>();
				Firm firm=new Firm();
				map.put("prmfirmid", firm.getFirmData().firmid);
				ReportViewer view=new ReportViewer("supcredit", map, firm.getConnectionObj());
				view.showReport();
			}
			else if(e.getSource()==mniExpensesReport) {
				if(!isFrameOpened(ExpensesReportUI.class.getName(), true))
					addFrame(new ExpensesReportUI());
			}
			else if (e.getSource()==mniEmpAttendReport) {
				if (!isFrameOpened(EmpAttendanceReportUI.class.getName(), true)) {
					addFrame(new EmpAttendanceReportUI());
				}
			}
			else if(e.getSource()==mniQuickSales)
			{
				if(!isFrameOpened(RunnupCustomersUI.class.getName(), true))
					addFrame(new RunnupCustomersUI());
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getSource() == jdp) {
				showPopup(e);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.getSource() == jdp) {
				showPopup(e);
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}
	}
}