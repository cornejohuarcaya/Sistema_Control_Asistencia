/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import Clase.Connexion;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Erick
 */
@ManagedBean
@SessionScoped
public class ReporteBean {

    /**
     * Creates a new instance of ReporteBean
     */
    public ReporteBean() {
    }
   
   /////////////////IREPORT

    	public void exportaPdf(ActionEvent actionEvent ) throws JRException, IOException, Exception{
            Connexion cn =new Connexion();
            cn.Conectar();
		Map<String,Object> parametros= new HashMap<String,Object>();
		//parametros.put("param1", idcodigo);
		
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/Reportlistaestudiantes.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametros, cn.getCnn());
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=jsfReporte.pdf");
		ServletOutputStream stream = response.getOutputStream();
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
                cn.Cerrar();
	}
        public void exportarExcel(ActionEvent actionEvent) throws JRException, IOException, Exception{
		Connexion cn =new Connexion();
            cn.Conectar();
            Map<String,Object> parametros= new HashMap<String,Object>();
		//parametros.put("param1", idcodigo);
		
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/Reportlistaestudiantes.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametros, cn.getCnn());
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=jsfReporte.xls");
		ServletOutputStream stream = response.getOutputStream();
		
		          JRXlsExporter exporter = new JRXlsExporter();
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
		exporter.exportReport();
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
                cn.Cerrar();
	}
	
	public void exportarPPT(ActionEvent actionEvent) throws JRException, IOException, Exception{
		Connexion cn =new Connexion();
            cn.Conectar();
            Map<String,Object> parametros= new HashMap<String,Object>();
		//parametros.put("param1", idcodigo);
		
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/Reportlistaestudiantes.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametros, cn.getCnn());
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=jsfReporte.ppt");
		ServletOutputStream stream = response.getOutputStream();
		
		JRPptxExporter exporter = new JRPptxExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
		exporter.exportReport();
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
                cn.Cerrar();
	}
	
	public void exportarDOC(ActionEvent actionEvent) throws JRException, IOException, Exception{
		Connexion cn =new Connexion();
            cn.Conectar();
            Map<String,Object> parametros= new HashMap<String,Object>();
		//parametros.put("param1", idcodigo);
		
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/Reportlistaestudiantes.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametros, cn.getCnn());
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=jsfReporte.doc");
		ServletOutputStream stream = response.getOutputStream();
		
		JRDocxExporter exporter = new JRDocxExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
		exporter.exportReport();
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
                cn.Cerrar();
	}
	
	public void verPDF(ActionEvent actionEvent) throws Exception{
            Connexion cn =new Connexion();
            cn.Conectar();
            Map<String,Object> parametros= new HashMap<String,Object>();
		//parametros.put("param1", idcodigo);
		
		
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/Reportlistaestudiantes.jasper"));		
		
		byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, cn.getCnn());
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		ServletOutputStream outStream = response.getOutputStream();
		outStream.write(bytes, 0 , bytes.length);
		outStream.flush();
		outStream.close();
			
		FacesContext.getCurrentInstance().responseComplete();
                cn.Cerrar();
	}
      
}
