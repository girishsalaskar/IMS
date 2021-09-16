package com.ims;

import java.awt.Font;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import com.ims.misc.Statics;
import com.ims.models.IMSDB;
import com.ims.ui.Parent;
import com.jdev.girish.app.wizard.WizardStarter;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;

public class Program {
	public static final Properties APP = new Properties();
	public static final Properties LANG = new Properties();

	public static void main(String[] args) {
		System.setProperty("file.encoding", "UTF-8");
		initLAF();
		try {
			APP.load(Class.class.getResourceAsStream("/app.properties"));
		} catch (IOException | NullPointerException e) {
			Statics.handleException(e);
			System.exit(0);
		}

		try {
			IMSDB db = new IMSDB();
			db.executeQuery("select value from application where property='lang'");
			String lang = "";
			if (db.getLastResultSet().next()) {
				lang = db.getLastResultSet().getString("value");
				if (lang.equalsIgnoreCase("mr")) {
					Parent.mfont = new Font("Mangal", java.awt.Font.BOLD, 15);
					Statics.TITLE_FONT = new java.awt.Font("Mangal",
							java.awt.Font.BOLD + java.awt.Font.ITALIC, 30);
				} else if (lang.equalsIgnoreCase("en")) {
					Parent.mfont = new Font("Serif", java.awt.Font.BOLD, 15);
					Statics.TITLE_FONT = new java.awt.Font("Monotype Corsiva",
							1, 48);
				}
			} else {
				lang = "en";
				Parent.mfont = new Font("Serif", java.awt.Font.BOLD, 15);
				Statics.TITLE_FONT = new java.awt.Font("Monotype Corsiva", 1,
						48);
			}
			LANG.put("lang", lang);
			db.executeQuery("select string, " + lang + " from strings");
			while (db.getLastResultSet().next()) {
				LANG.setProperty(db.getLastResultSet().getString("string"), db
						.getLastResultSet().getString(lang));
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			Statics.handleException(e);
			System.exit(0);
		}

		Parent p = new Parent();
		new WizardStarter(p, APP);
	}

	static void initLAF() {
		UIManager.installLookAndFeel("Acryl",
				"com.jtattoo.plaf.acryl.AcrylLookAndFeel");
		UIManager.installLookAndFeel("Aero",
				"com.jtattoo.plaf.aero.AeroLookAndFeel");
		UIManager.installLookAndFeel("Aluminium",
				"com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
		UIManager.installLookAndFeel("Bernstein",
				"com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
		UIManager.installLookAndFeel("Fast",
				"com.jtattoo.plaf.fast.FastLookAndFeel");
		UIManager.installLookAndFeel("Graphite",
				"com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
		UIManager.installLookAndFeel("HiFi",
				"com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		UIManager.installLookAndFeel("Luna",
				"com.jtattoo.plaf.luna.LunaLookAndFeel");
		UIManager.installLookAndFeel("McWin",
				"com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		UIManager.installLookAndFeel("Mint",
				"com.jtattoo.plaf.mint.MintLookAndFeel");
		UIManager.installLookAndFeel("Noire",
				"com.jtattoo.plaf.noire.NoireLookAndFeel");
		UIManager.installLookAndFeel("Smart",
				"com.jtattoo.plaf.smart.SmartLookAndFeel");
		UIManager.installLookAndFeel("Texture",
				"com.jtattoo.plaf.texture.TextureLookAndFeel");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
				if (info.getName().equalsIgnoreCase("Acryl")) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			Statics.handleException(e1);
		}
	}
}