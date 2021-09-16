package com.ims.ui.common;

import java.awt.Toolkit;

import javax.swing.JDialog;

public class InlineWindow extends JDialog {

	private static final long serialVersionUID = 5736383783594062506L;

	public InlineWindow() {
		super();
		super.setModal(true);
		super.setAlwaysOnTop(true);
		super.setTitle("Inline Window");
		super.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height - 60);
		super.setLocation(
				((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (super
						.getWidth() / 2)), ((Toolkit.getDefaultToolkit()
						.getScreenSize().height / 2) - super.getHeight() / 2));
	}
}