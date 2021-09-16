package com.ims.ui.common;

import javax.swing.JLabel;

import com.jdev.girish.ui.iframe.IFrame;

public class AboutUs extends IFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4542063025572303166L;

	private JLabel lbldev;

	private JLabel lblabt;

	public AboutUs() {
		super("About Us", 360, 257);

		lbldev = new JLabel();
		lbldev.setFont(new java.awt.Font("Times New Roman", 2, 30)); // NOI18N
		lbldev.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbldev.setText("3 Square Technologies");
		lbldev.setToolTipText("Developed By...");
		panel.add(lbldev);
		lbldev.setBounds(10, 10, 340, 36);

		lblabt = new JLabel();
		lblabt.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
		lblabt.setText("<html><body><div style=\"width: 280px; padding: 5px;\">Developed By : Girish Salaskar (9860911209)<br/><br/>\n\nVersion : 1.0.1<br/><br/>\n\nDeveloped for : Laxmi Agencies, Vakhari, Pandharpur.<br/><br/>\n\nLicense : <br/>\n&nbsp;&nbsp;&nbsp;&nbsp;This software is licensed to Laxmi Agencies, Vakhari, Pandharpur and this software is not applicable to any other firm. This software is distributed for life time usage and there is no any time restrictions to use this software.</div></body></html>");
		lblabt.setVerticalAlignment(javax.swing.SwingConstants.TOP);
		lblabt.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
		panel.add(lblabt);
		lblabt.setBounds(10, 50, 340, 170);
	}
}