package com.ims;

import java.awt.Font;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.ims.misc.Statics;
import com.ims.models.IMSDB;
import com.ims.ui.Parent;
import com.jdev.girish.app.wizard.WizardStarter;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;

public class Program {
	public static final Properties APP = new Properties();
	public static final Properties LANG= new Properties();

	public static void main(String[] args) {
		System.setProperty("file.encoding", "UTF-8");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		try {
			APP.load(Class.class.getResourceAsStream("/app.properties"));
		} catch (IOException | NullPointerException e) {
			Statics.handleException(e);
			System.exit(0);
		}

		try {
			IMSDB db=new IMSDB();
			db.executeQuery("select value from application where property='lang'");
			String lang="";
			if(db.getLastResultSet().next())
			{
				lang=db.getLastResultSet().getString("value");
				if(lang.equalsIgnoreCase("mr"))
				{
					Parent.mfont=new Font("Mangal", java.awt.Font.BOLD, 15);
					Statics.TITLE_FONT=new java.awt.Font("Mangal", java.awt.Font.BOLD
							+java.awt.Font.ITALIC, 30);
				}
				else if(lang.equalsIgnoreCase("en"))
				{
					Parent.mfont=new Font("Serif", java.awt.Font.BOLD, 15);
					Statics.TITLE_FONT = new java.awt.Font("Monotype Corsiva", 1, 48);
				}
			}
			else
			{
				lang="en";
				Parent.mfont=new Font("Serif", java.awt.Font.BOLD, 15);
				Statics.TITLE_FONT = new java.awt.Font("Monotype Corsiva", 1, 48);
			}
			LANG.put("lang", lang);
			db.executeQuery("select string, "+lang+" from strings");
			while(db.getLastResultSet().next())
			{
				LANG.setProperty(db.getLastResultSet().getString("string"), db.getLastResultSet().getString(lang));
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			Statics.handleException(e);
			return;
		}

		// javax.swing.plaf.FontUIResource font=new
		// javax.swing.plaf.FontUIResource(Statics.NORMAL_LARGE_FONT);
		// java.util.Enumeration<?> key=UIManager.getDefaults().keys();
		// while(key.hasMoreElements())
		// {
		// Object k=key.nextElement();
		// Object val=UIManager.get(k);
		// System.out.println(k+" : "+val+"\n");
		// if(val!=null && val instanceof javax.swing.plaf.FontUIResource)
		// {
		// UIManager.put(k, font);
		// }
		// }
		
		Parent p = new Parent();
		new WizardStarter(p, APP);

		// INFO run Garbage collector at every 5 minute
		// Timer timer=new Timer();
		// timer.schedule(new TimerTask() {
		// @Override
		// public void run() {
		// Runtime rt=Runtime.getRuntime();
		// System.out.println("Total : "+(rt.totalMemory()/1024/1024)+"MB");
		// System.out.println("Maximum : "+(rt.maxMemory()/1024/1024)+"MB");
		// System.out.println("Free : "+(rt.freeMemory()/1024/1024)+"MB");
		// rt.gc();
		// System.out.println("Free after gc : "+(rt.freeMemory()/1024/1024)+"MB");
		// }
		// }, 1000*60, (1000*60*5));
	}
}