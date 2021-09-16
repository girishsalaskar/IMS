package com.ims.reports;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import com.ims.misc.Statics;

public class ReportViewer {

	private java.sql.Connection con;
	private java.util.HashMap map;
	private String reportfile;

	public ReportViewer(String reportfile, java.util.HashMap map,
			java.sql.Connection con) {
		this.con = con;
		this.map = map;
		this.reportfile = reportfile;
	}

	public boolean showReport() {
		boolean success = false;

		try {
			JasperReport report = JasperCompileManager.compileReport(Statics
					.getReportIStream(reportfile));
			JasperPrint print = JasperFillManager.fillReport(report, map, con);
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

// INFO Code for further reference
// try {
//
// JasperReport
// report=JasperCompileManager.compileReport(Statics.getReportIStream("trial.jrxml"));
// HashMap map=new HashMap();
// map.put("firm", "Laxmi Agencies");
// JasperPrint print=JasperFillManager.fillReport(report, map, new
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