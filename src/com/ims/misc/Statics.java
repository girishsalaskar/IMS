package com.ims.misc;

import java.awt.Component;
import java.awt.image.RenderedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.JTable;

import com.ims.Program;

public class Statics {
	public static final String DIR_SEP = java.io.File.separator;

	public static final java.awt.Font NORMAL_LARGE_FONT = new java.awt.Font(
			"Mangal", java.awt.Font.BOLD, 16);

	//public static final String RESOURCE = "/com/ims/res/";

	public static String ROOT;

	//public static final java.util.Properties STRINGS = new java.util.Properties();

	public static java.awt.Font TITLE_FONT;

	static {
		File f=new File("");
		ROOT = (f.getAbsolutePath() + (!f
				.getAbsolutePath().endsWith(DIR_SEP) ? DIR_SEP : ""));
	}

	// INFO customizeColumnWidth
	public static void customColumnWidth(javax.swing.JTable table,
			int colwidths[]) {
		table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		for (int i = 0; i < colwidths.length; i++)
			table.getColumnModel().getColumn(i).setPreferredWidth(colwidths[i]);
	}

	// INFO sha1
	/*
	 * public static String sha1(String input) throws
	 * java.security.NoSuchAlgorithmException { java.security.MessageDigest
	 * mDigest = java.security.MessageDigest.getInstance("SHA1"); byte[] result
	 * = mDigest.digest(input.getBytes()); StringBuffer sb = new StringBuffer();
	 * for (int i = 0; i < result.length; i++) {
	 * sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
	 * }
	 * 
	 * return sb.toString(); }
	 */

	// INFO validate
	// public static boolean validate(String pattern, String input)
	// {
	// String line=new String(pattern.toCharArray());
	// java.util.regex.Pattern pat=java.util.regex.Pattern.compile(line);
	// java.util.regex.Matcher match=pat.matcher(input);
	// return match.find() && (match.start()==0?true:false) &&
	// (match.end()==input.length()?true:false);
	// }

	// INFO file copy
	public static void fileCopy(File source, File dest) {
		try {
			FileReader reader = new FileReader(source);
			FileWriter writer = new FileWriter(dest);
			int b;
			while ((b = reader.read()) != -1) {
				writer.write(b);
			}
			reader.close();
			writer.close();
		} catch (IOException e) {
			handleException(e);
		}
	}

	// INFO get Image Input Stream
	public static InputStream getImageStream(String imgname) {
		return Statics.class.getResourceAsStream("/com/ims/res/img/" + imgname);
	}

	// INFO get Report Stream
	public static InputStream getReportIStream(String jrxmlname) {
		return Statics.class.getResourceAsStream("/com/ims/reports/jrxml/"
				+ jrxmlname);
	}

	// INFO handleException
	public static void handleException(Exception e) {
		StringWriter str = new StringWriter();
		PrintWriter pr = new PrintWriter(str, true);
		e.printStackTrace(pr);
		String stack = str.toString();
		// javax.swing.JOptionPane.showMessageDialog(null,
		// "Message : "+e.getMessage()+"\n"+stack, "Error",
		// javax.swing.JOptionPane.ERROR_MESSAGE);

		String buffer = "";
		buffer += ("Date and Time : " + new SimpleDateFormat(
				"dd-MMM-yyyy hh:mm:ss").format(new java.util.Date()));
		buffer += ("\r\nMessage : " + e.getMessage());
		buffer += ("\r\nStack trace : \r\n" + stack);
		buffer += ("\r\n----------End of Exception----------" + "\r\n\r\n\r\n");
		System.out.println(buffer);
		javax.swing.JOptionPane
				.showMessageDialog(
						null,
						"Error : "
								+ e.getMessage()
								+ "\nFor more on this error, contact to your service provider.",
						"Error", javax.swing.JOptionPane.ERROR_MESSAGE);

		makeDir("log");
		try {
			makeFile("log" + DIR_SEP + "error.3sqlog");
		} catch (IOException e1) {
			e1.printStackTrace();
			javax.swing.JOptionPane.showMessageDialog(null,
					"Problem while creating error log at step 1!"
							+ "\nPlease contact to your service provider."
							+ "\nMessage : " + e1.getMessage(), "Error",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			return;
		}
		File errorlog = new File(ROOT + "log" + DIR_SEP + "error.3sqlog");

		FileWriter write = null;
		try {
			write = new FileWriter(errorlog, true);
		} catch (IOException e1) {
			e1.printStackTrace();
			javax.swing.JOptionPane.showMessageDialog(null,
					"Problem while creating error log at step 2!"
							+ "\nPlease contact to your service provider."
							+ "\nMessage : " + e1.getMessage(), "Error",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (write != null) {
			BufferedWriter bwrite = new BufferedWriter(write);
			try {
				bwrite.write(buffer);
				bwrite.close();
			} catch (IOException e1) {
				e1.printStackTrace();
				javax.swing.JOptionPane.showMessageDialog(null,
						"Problem while creating error log at step 3!"
								+ "\nPlease contact to your service provider."
								+ "\nMessage : " + e1.getMessage(), "Error",
						javax.swing.JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		// File file=null;
		// File log=null;
		// try {
		// file=new
		// File(Statics.class.getProtectionDomain().getCodeSource().getLocation().toURI());
		// log=new File(file.getPath()+DIR_SEP+"log");
		// } catch (URISyntaxException e1) {
		// e1.printStackTrace();
		// }
		// if(file!=null && log!=null)
		// {
		// if(!log.exists())
		// {
		// log.mkdir();
		// }
		// File logfile=new File(log.getPath()+DIR_SEP+"error.log");
		// if(!logfile.exists())
		// {
		// try {
		// logfile.createNewFile();
		// } catch (IOException e1) {
		// e1.printStackTrace();
		// }
		// }
		// FileWriter wr=null;
		// try {
		// wr=new FileWriter(logfile, true);
		// } catch (IOException e1) {
		// e1.printStackTrace();
		// System.exit(0);
		// }
		// if(wr!=null)
		// {
		// BufferedWriter buf=new BufferedWriter(wr);
		// try {
		// buf.write(buffer.toString());
		// buf.close();
		// } catch (IOException e1) {
		// e1.printStackTrace();
		// System.exit(0);
		// }
		// }
		// }
	}

	// INFO Hide column of jtable
	public static void hideColumn(JTable tbl, int column) {
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbl.getColumnModel().getColumn(column).setMinWidth(0);
		tbl.getColumnModel().getColumn(column).setMaxWidth(0);
		tbl.getColumnModel().getColumn(column).setWidth(0);
	}

	// INFO Image Copy
	public static void imageCopy(File source, File dest, String filetype) {
		try {
			RenderedImage img = ImageIO.read(source);
			ImageIO.write(img, filetype, dest);
		} catch (IOException e) {
			handleException(e);
		}
	}

	// INFO make directory
	public static boolean makeDir(String dirname) {
		java.io.File file = new File(ROOT + dirname);
		if (!file.exists())
			return file.mkdir();
		return file.exists();
	}

	// INFO make file
	public static boolean makeFile(String filename) throws IOException {
		java.io.File file = new File(ROOT + filename);
		if (!file.exists())
			return file.createNewFile();
		return file.exists();
	}

	// INFO empty fields
	public static void resetFields(Component[] components) {
		for (java.awt.Component comp : components) {
			if (comp instanceof javax.swing.JTextField) {
				((javax.swing.JTextField) comp).setText("");
			}

			else if (comp instanceof javax.swing.JTextArea) {
				((javax.swing.JTextArea) comp).setText("");
			}

			if (comp instanceof java.awt.Container) {
				resetFields(((java.awt.Container) comp).getComponents());
			}
		}
	}

	// INFO resizeTableColumns
	public static void resizeColumnWidth(javax.swing.JTable table) {
		table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		final javax.swing.table.TableColumnModel columnModel = table
				.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 50; // Min width
			for (int row = 0; row < table.getRowCount(); row++) {
				javax.swing.table.TableCellRenderer renderer = table
						.getCellRenderer(row, column);
				java.awt.Component comp = table.prepareRenderer(renderer, row,
						column);
				width = Math.max(comp.getPreferredSize().width, width);
			}
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}

	// INFO showMessage
	public static void showMessage(java.awt.Component c, String msg, int opt) {
		javax.swing.JOptionPane.showMessageDialog(c, msg,
				Program.APP.getProperty("APPTITLE"), opt);
	}

	// INFO check empty
	public static boolean validateEmpty(java.awt.Container con) {
		// boolean ret=true;
		// for (Component comp:con.getComponents()) {
		// if (comp.isEnabled()) {
		// if (comp instanceof javax.swing.JTextField) {
		// if (((javax.swing.JTextField) comp).getText().equals("")) {
		// comp.requestFocus();
		// ret=false;
		// }
		// }
		//
		// else if (comp instanceof javax.swing.JTextArea) {
		// if (((javax.swing.JTextArea) comp).getText().equals("")) {
		// comp.requestFocus();
		// ret=false;
		// }
		// }
		//
		// else if (comp instanceof java.awt.Container) {
		// return validateEmpty(((java.awt.Container) comp));
		// }
		// }
		// }
		//
		// return ret;
		boolean ret = true;
		Component comp = null;
		for (int i = 0; i < con.getComponents().length; i++) {
			comp = con.getComponent(i);
			if (comp.isEnabled()) {
				if (comp instanceof javax.swing.text.JTextComponent) {
					if (((javax.swing.JTextField) comp).getText().equals("")) {
						comp.requestFocus();
						// return comp;
						return false;
					}
				}

				else if (comp instanceof javax.swing.JTextArea) {
					if (((javax.swing.JTextArea) comp).getText().equals("")) {
						((javax.swing.JTextArea) comp).requestFocus();
						// return comp;
						break;
					}
				}

				else if (comp instanceof java.awt.Container) {
					return validateEmpty(((java.awt.Container) comp));
				}
			}
		}
		// return comp;
		return ret;
	}
}