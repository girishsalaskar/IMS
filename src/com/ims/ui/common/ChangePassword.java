package com.ims.ui.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import com.ims.Program;
import com.ims.misc.Statics;
import com.ims.models.User;
import com.ims.ui.Parent;
import com.jdev.girish.ui.iframe.IFrame;

public class ChangePassword extends IFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 905482823424456374L;

	public JLabel loldpass, lconpass, lnewpass;

	public JPasswordField poldpass, pconpass, pnewpass;

	private JButton bchange;

	private ChangePListen listen;

	public ChangePassword() {
		super(Program.LANG.getProperty("mnichangepass"), 380, 210);
		listen = new ChangePListen();

		loldpass = new JLabel();
		lconpass = new JLabel();
		poldpass = new JPasswordField();
		pconpass = new JPasswordField();
		pnewpass = new JPasswordField();
		lnewpass = new JLabel();
		bchange = new JButton("Change Password");
		;
		lnewpass.setLabelFor(pnewpass);

		loldpass.setFont(Statics.NORMAL_LARGE_FONT);
		loldpass.setText("Old Password");
		panel.add(loldpass);
		loldpass.setBounds(10, 10, 150, 30);

		lnewpass.setFont(Statics.NORMAL_LARGE_FONT);
		lnewpass.setText("New Password");
		panel.add(lnewpass);
		lnewpass.setBounds(10, 50, 150, 30);

		lconpass.setFont(Statics.NORMAL_LARGE_FONT);
		lconpass.setText("Confirm Password");
		panel.add(lconpass);
		lconpass.setBounds(10, 90, 150, 30);

		pconpass.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(pconpass);
		pconpass.setBounds(170, 90, 200, 30);

		pnewpass.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(pnewpass);
		pnewpass.setBounds(170, 50, 200, 30);

		poldpass.setFont(Statics.NORMAL_LARGE_FONT);
		panel.add(poldpass);
		poldpass.setBounds(170, 10, 200, 30);

		bchange.setFont(Statics.NORMAL_LARGE_FONT);
		bchange.setText("Change Password");
		panel.add(bchange);
		bchange.setBounds(110, 130, 180, 60);

		bchange.addActionListener(listen);
		
		//INFO Multilanguage Text
		lconpass.setText(Program.LANG.getProperty("lblconfirmpassword"));
		loldpass.setText(Program.LANG.getProperty("lbloldpassword"));
		lnewpass.setText(Program.LANG.getProperty("lblnewpassword"));
		bchange.setText(Program.LANG.getProperty("btnsave"));
	}

	private void bchangeActionPerformed(ActionEvent e) {
		if (String.copyValueOf(poldpass.getPassword()).equals("")) {
			Statics.showMessage(rootPane, "Please Enter Old Password!",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			poldpass.setText("");
			pnewpass.setText("");
			pconpass.setText("");
			poldpass.requestFocus();
			return;
		} else if (String.copyValueOf(pnewpass.getPassword()).equals("")) {
			Statics.showMessage(rootPane, "Please Enter New Password!",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			poldpass.setText("");
			pnewpass.setText("");
			pconpass.setText("");
			poldpass.requestFocus();
			return;
		} else if (String.copyValueOf(pconpass.getPassword()).equals("")) {
			Statics.showMessage(rootPane, "Please Enter New Password Again!",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			poldpass.setText("");
			pnewpass.setText("");
			pconpass.setText("");
			poldpass.requestFocus();
			return;
		} else if (!String.copyValueOf(pnewpass.getPassword()).equals(
				String.copyValueOf(pconpass.getPassword()))) {
			Statics.showMessage(rootPane,
					"New Password and Confirm Password does not match!",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			poldpass.setText("");
			pnewpass.setText("");
			pconpass.setText("");
			poldpass.requestFocus();
			return;
		}
		User usr = new User(Parent.getUserID());
		if (usr.changePassword(String.copyValueOf(poldpass.getPassword()),
				String.copyValueOf(pnewpass.getPassword()))) {
			Statics.showMessage(rootPane, "Password Changed Successfully!",
					javax.swing.JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}
	}

	private class ChangePListen implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			bchangeActionPerformed(arg0);
		}

	}
}