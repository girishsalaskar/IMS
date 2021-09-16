package com.ims.ui.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.User;
import com.jdev.girish.ui.iframe.IFrame;

public class NewUser extends IFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3285157067565060142L;

	private JButton breset;

	private JButton bsave;

	private JLabel lconpass;
	private JLabel lmob;
	private JLabel lpwd;
	private JLabel luid;
	private JLabel luname;
	private JPasswordField pconpass;
	private JPasswordField pupass;
	private JTextField tmob;
	private JTextField tuid;
	private JTextField tuname;
	private NewUserListener listen;
	private JComboBox<String> cutype;
	private JLabel lutype;

	public NewUser() {
		super(Program.LANG.getProperty("mninewuser"), 415, 330);
		listen = new NewUserListener();
		initComponents();
		
		//INFO Multilanguage Text
		luid.setText(Program.LANG.getProperty("lbluserid"));
		lpwd.setText(Program.LANG.getProperty("lblpassword"));
		lconpass.setText(Program.LANG.getProperty("lblconfirmpassword"));
		lutype.setText(Program.LANG.getProperty("lblusertype"));
		luname.setText(Program.LANG.getProperty("lblusername"));
		lmob.setText(Program.LANG.getProperty("lblcontactno"));
		breset.setText(Program.LANG.getProperty("btnreset"));
		bsave.setText(Program.LANG.getProperty("btnsave"));
	}

	private void initComponents() {

		luid = new JLabel();
		lpwd = new JLabel();
		luname = new JLabel();
		lmob = new JLabel();
		lutype = new JLabel();
		tuid = new JTextField();
		pupass = new JPasswordField();
		tuname = new JTextField();
		tmob = new JTextField();
		bsave = new JButton();
		breset = new JButton();
		pconpass = new JPasswordField();
		lconpass = new JLabel();
		cutype = new JComboBox<String>();

		luid.setFont(Statics.NORMAL_LARGE_FONT);
		luid.setText("User ID");
		panel.add(luid);
		luid.setBounds(10, 14, 110, 30);

		lpwd.setFont(Statics.NORMAL_LARGE_FONT);
		lpwd.setText("Password");
		panel.add(lpwd);
		lpwd.setBounds(10, 54, 110, 30);

		luname.setFont(Statics.NORMAL_LARGE_FONT);
		luname.setText("User Name");
		panel.add(luname);
		luname.setBounds(10, 169, 140, 30);

		lmob.setFont(Statics.NORMAL_LARGE_FONT);
		lmob.setText("Mobile Number");
		panel.add(lmob);
		lmob.setBounds(10, 209, 140, 30);

		tuid.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(tuid);
		tuid.setBounds(180, 10, 220, 30);

		pupass.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(pupass);
		pupass.setBounds(180, 50, 220, 30);

		tuname.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(tuname);
		tuname.setBounds(180, 170, 220, 30);

		tmob.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(tmob);
		tmob.setBounds(180, 210, 220, 30);

		bsave.setFont(Statics.NORMAL_LARGE_FONT);
		bsave.setText("Save");
		panel.add(bsave);
		bsave.setBounds(10, 250, 190, 50);

		breset.setFont(Statics.NORMAL_LARGE_FONT);
		breset.setText("Reset");
		panel.add(breset);
		breset.setBounds(210, 250, 190, 50);

		pconpass.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(pconpass);
		pconpass.setBounds(180, 90, 220, 30);

		lconpass.setFont(Statics.NORMAL_LARGE_FONT);
		lconpass.setText("Confirm Password");
		panel.add(lconpass);
		lconpass.setBounds(10, 95, 170, 30);

		cutype.setFont(Statics.NORMAL_LARGE_FONT);
		cutype.addItem("Simple User");
		cutype.addItem("Administrator");
		cutype.setSelectedIndex(-1);
		panel.add(cutype);
		cutype.setBounds(180, 130, 220, 30);

		lutype.setFont(Statics.NORMAL_LARGE_FONT);
		lutype.setText("User Type");
		panel.add(lutype);
		lutype.setBounds(10, 129, 140, 30);

		breset.addActionListener(listen);
		bsave.addActionListener(listen);
	}

	private void reset() {
		tuid.setText("");
		tuname.setText("");
		tmob.setText("");
		pupass.setText("");
		pconpass.setText("");
	}

	@SuppressWarnings("deprecation")
	private boolean valid() {
		if (tuid.getText().equals("")) {
			tuid.requestFocus();
			Statics.showMessage(rootPane, "Please Enter User ID!",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (pupass.getText().equals("")) {
			pupass.requestFocus();
			Statics.showMessage(rootPane, "Please Enter Password!",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (pconpass.getText().equals("")) {
			pconpass.requestFocus();
			Statics.showMessage(rootPane,
					"Please Enter Password for Confirmation!",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (tuname.getText().equals("")) {
			tuname.requestFocus();
			Statics.showMessage(rootPane, "Please Enter User's Name!",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (tmob.getText().equals("")) {
			tmob.requestFocus();
			Statics.showMessage(rootPane, "Please Enter Mobile Number!",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (cutype.getSelectedIndex() == -1) {
			cutype.requestFocus();
			Statics.showMessage(rootPane, "Please Select User Type",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!pupass.getText().equals(pconpass.getText())) {
			pupass.setText("");
			pconpass.setText("");
			Statics.showMessage(
					rootPane,
					"Password and Confirmation Password did not match! Please try again!",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			pupass.requestFocus();
			return false;
		}
		return true;
	}

	private class NewUserListener implements ActionListener {

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == breset) {
				reset();
			}

			else if (e.getSource() == bsave) {
				if (valid()) {
					User user = new User(tuid.getText());
					if (user.addNewUser(tuname.getText(), tmob.getText(),
							pupass.getText(), cutype.getSelectedIndex())) {
						Statics.showMessage(rootPane,
								"User Created Successfully!",
								javax.swing.JOptionPane.INFORMATION_MESSAGE);
						reset();
					} else
						Statics.showMessage(rootPane,
								"User not created successfully!",
								javax.swing.JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}