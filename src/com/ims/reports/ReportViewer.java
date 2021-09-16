package com.ims.reports;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import com.ims.misc.Statics;

public class ReportViewer {

	private java.sql.Connection sqlConnection;
	private java.util.HashMap<String, Object> reportParam;
	private String reportFileName;
	private String subReportFileName;
	boolean success;
	private ReportShow show;
	private String waitLabel;
	private JasperReport report;
	private JasperPrint print;

	public ReportViewer()
	{
		init();
	}

	private void init()
	{
		waitLabel="Working on Report... Please wait...";
		show=new ReportShow();
		show.addPropertyChangeListener("success", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				success=Boolean.parseBoolean(arg0.getNewValue().toString());
				show.setVisible(false);
			}
		});
	}
	public ReportViewer(String reportfile, java.util.HashMap<String, Object> map,
			java.sql.Connection con) {
		this.sqlConnection = con;
		this.reportParam = map;
		this.reportFileName = reportfile;
		init();
	}

	public boolean showReport() {
		show.setVisible(true);
		Thread thr=new Thread(show);
		thr.start();
		return success;
	}
	
	public void compileAndShowSubReport()
	{
		Thread th=new Thread()
		{
			@Override
			public void run() {
				super.run();
				ReportShow wait=new ReportShow();
				wait.setVisible(true);
				Statics.makeDir("temp");
				InputStream read=Statics.getReportIStream(subReportFileName+".jrxml");
				int b;
				File f=new File(Statics.ROOT+"temp"+Statics.DIR_SEP+subReportFileName+".jrxml");
				FileWriter write=null;
				try {
					Statics.makeFile("temp"+Statics.DIR_SEP+subReportFileName+".jrxml");
					if(!f.exists())
						f.createNewFile();
					write=new FileWriter(f);
					while((b=read.read())!=-1)
					{
						write.write(b);
					}
					write.close();
					read.close();
				} catch (IOException e) {
					wait.dispose();
					Statics.handleException(e);
				}
				
				try {
					JasperCompileManager.compileReportToFile(Statics.ROOT+"temp"+Statics.DIR_SEP+subReportFileName+".jrxml", Statics.ROOT+"temp"+Statics.DIR_SEP+subReportFileName+".jasper");
				} catch (JRException e) {
					wait.dispose();
					Statics.handleException(e);
				}
				reportParam.put("SUBREPORT_DIR", Statics.ROOT+"temp"+Statics.DIR_SEP+subReportFileName+".jasper");
				wait.dispose();
				showReport();
			}
		};
		th.start();
	}

	private class ReportShow extends JWindow implements Runnable
	{
		private static final long serialVersionUID = 4843337590300675478L;
		
		private JProgressBar progress;
		private JLabel lblLabel;
		public ReportShow() {
			setSize(700, 400);
			setAlwaysOnTop(true);
			setLocation((Toolkit.getDefaultToolkit().getScreenSize().width/2-350),
					(Toolkit.getDefaultToolkit().getScreenSize().height/2-200));
			progress=new JProgressBar();
			progress.setIndeterminate(true);
			add(progress, BorderLayout.SOUTH);
			
			lblLabel=new JLabel(waitLabel);
			lblLabel.setHorizontalAlignment(JLabel.CENTER);
			lblLabel.setHorizontalTextPosition(JLabel.CENTER);
			lblLabel.setVerticalAlignment(JLabel.CENTER);
			lblLabel.setVerticalTextPosition(JLabel.CENTER);
			add(lblLabel, BorderLayout.CENTER);
		}
		
		@Override
		public void run() {
			try {
				report = JasperCompileManager.compileReport(Statics
						.getReportIStream(reportFileName+".jrxml"));
				print = JasperFillManager.fillReport(report, reportParam, sqlConnection);
				firePropertyChange("success", false, true);
				if(print.getPages().size()==0)
				{
					JOptionPane.showMessageDialog(null, "No data available for given information!", "Report", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				JasperViewer view = new JasperViewer(print, false);
				view.setExtendedState(JFrame.MAXIMIZED_BOTH);
				view.setTitle("Inventory Management System");
				view.setIconImage(ImageIO.read(Statics.getImageStream("print.png")));
				view.setVisible(true);
			} catch (JRException | IOException e) {
				Statics.handleException(e);
				firePropertyChange("success", true, false);
			}
		}
	}


	
public void setSqlConnection(java.sql.Connection sqlConnection) {
		this.sqlConnection = sqlConnection;
	}

	public void setReportParam(java.util.HashMap<String, Object> reportParam) {
		this.reportParam = reportParam;
	}

	public String getReportFileName() {
		return reportFileName;
	}

	public void setReportFileName(String reportFileName) {
		this.reportFileName = reportFileName;
	}

	public void setSubReportFileName(String subReportFileName) {
		this.subReportFileName = subReportFileName;
	}
}

// INFO Code for further reference
// try {
//
// JasperReport
// report=JasperCompileManager.compileReport(Statics.getReportIStream("trial.jrxml"));
// HashMap reportParam=new HashMap();
// reportParam.put("firm", "Laxmi Agencies");
// JasperPrint print=JasperFillManager.fillReport(report, reportParam, new
// JREmptyDataSource());
// JasperViewer view=new JasperViewer(print);
// view.setVisible(true);

// TODO Exporter
// JFileChooser ch=new JFileChooser();
// if(ch.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
// {
// JasperExportManager.exportReportToHtmlFile(print,
// ch.getSelectedFile().getAbsolutePath());

// JasperExportManager.exportReportToPdfFile(print,
// ch.getSelectedFile().getAbsolutePath());

// JRXlsExporter exp=new JRXlsExporter();
// exp.setParameter(JRExporterParameter.JASPER_PRINT, print);
// exp.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
// ch.getSelectedFile().getAbsolutePath());
// exp.exportReport();

// JRDocxExporter exp=new JRDocxExporter();
// exp.setParameter(JRExporterParameter.JASPER_PRINT, print);
// exp.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
// ch.getSelectedFile().getAbsolutePath());
// exp.exportReport();

// JRPptxExporter exp=new JRPptxExporter();
// exp.setParameter(JRExporterParameter.JASPER_PRINT, print);
// exp.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
// ch.getSelectedFile().getAbsolutePath());
// exp.exportReport();
// }
// } catch (JRException e) {
//
// e.printStackTrace();
// }

/*package com.ims.reports;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JWindow;
import javax.swing.text.AsyncBoxView;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.Element;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import com.ims.misc.Statics;

public class ReportViewer {

	private java.sql.Connection sqlConnection;
	private java.util.HashMap<String, Object> reportParam;
	private String reportFileName;

	public ReportViewer(String reportFileName, java.util.HashMap<String, Object> reportParam,
			java.sql.Connection sqlConnection) {
		this.con = sqlConnection;
		this.map = reportParam;
		this.reportfile = reportFileName;
	}

	public boolean showReport() {
		boolean success = false;
		try {
			JasperReport report = JasperCompileManager.compileReport(Statics
					.getReportIStream(reportFileName));
			JasperPrint print = JasperFillManager.fillReport(report, reportParam, sqlConnection);
			JasperViewer view = new JasperViewer(print, false);
			view.setExtendedState(JFrame.MAXIMIZED_BOTH);
			view.setTitle("Report from Inventor Management System");
			view.setIconImage(ImageIO.read(Statics.getImageStream("print.png")));
			view.setVisible(true);
			success = true;
		} catch (JRException | IOException e) {
			success = false;
			Statics.handleException(e);
		}
		
		return success;
	}
}

class Loading extends JWindow
{

	private static final long serialVersionUID = 345909476421716143L;
	public Loading()
	{
		setBounds(0, 0, 500, 500);
		setAlwaysOnTop(true);
	}
}

// INFO Code for further reference
// try {
//
// JasperReport
// report=JasperCompileManager.compileReport(Statics.getReportIStream("trial.jrxml"));
// HashMap reportParam=new HashMap();
// reportParam.put("firm", "Laxmi Agencies");
// JasperPrint print=JasperFillManager.fillReport(report, reportParam, new
// JREmptyDataSource());
// JasperViewer view=new JasperViewer(print);
// view.setVisible(true);

// TODO Exporter
// JFileChooser ch=new JFileChooser();
// if(ch.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
// {
// JasperExportManager.exportReportToHtmlFile(print,
// ch.getSelectedFile().getAbsolutePath());

// JasperExportManager.exportReportToPdfFile(print,
// ch.getSelectedFile().getAbsolutePath());

// JRXlsExporter exp=new JRXlsExporter();
// exp.setParameter(JRExporterParameter.JASPER_PRINT, print);
// exp.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
// ch.getSelectedFile().getAbsolutePath());
// exp.exportReport();

// JRDocxExporter exp=new JRDocxExporter();
// exp.setParameter(JRExporterParameter.JASPER_PRINT, print);
// exp.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
// ch.getSelectedFile().getAbsolutePath());
// exp.exportReport();

// JRPptxExporter exp=new JRPptxExporter();
// exp.setParameter(JRExporterParameter.JASPER_PRINT, print);
// exp.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
// ch.getSelectedFile().getAbsolutePath());
// exp.exportReport();
// }
// } catch (JRException e) {
//
// e.printStackTrace();
// }*/