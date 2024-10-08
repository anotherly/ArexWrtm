package kr.co.hivesys.comm.excel.support;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.AbstractView;


public class ExportView extends AbstractView {
	
	 String filename;
	 FileOutputStream fileout;
	 public ExportView(){
        //받드시 octet-stream으로 설정해야함
        super.setContentType("application/octet-stream");
     }
	 protected void renderMergedOutputModel(Map model,
			 HttpServletRequest request,
			 HttpServletResponse response) throws Exception{
	 }
	 @SuppressWarnings({ "unused", "unchecked" })
	 public void buildExcelDocument(String filename,
			 						   List Headrlist,
			 						   List DataList,
			 						   HttpServletRequest request,
                                       HttpServletResponse response) {
		try{
			response.setHeader("Cache-Control", "no-cache");
			this.setContentType("application/vnd.ms-excel;charset=UTF-8");
			
			HSSFWorkbook wb = new HSSFWorkbook();
			//sheet 생성
			HSSFSheet sheet1 = wb.createSheet("데이터");
			//header 생성
			HSSFRow headerrow = sheet1.createRow(0);
			for(int i=0;i<Headrlist.size();i++){
				headerrow.createCell(i).setCellValue(Headrlist.get(i).toString());
			}
			//data 생성
			HSSFRow datarow;
			List dataobject = null;
			for(int j=0;j<DataList.size();j++){
				datarow = sheet1.createRow(j+1);
				dataobject = (List)DataList.get(j);
				for(int k=0;k<dataobject.size();k++){
					if(dataobject.get(k)!=null){
						datarow.createCell(k).setCellValue(dataobject.get(k).toString());
					}
				}
			}
			//ListExcelView
			OutputStream out = response.getOutputStream();
			response.setContentType(super.getContentType());
		    response.setHeader("Content-Transfer-Encoding", "binary");
		    response.setHeader("Content-Disposition","attachment;fileName=\""+filename+"\";");
			wb.write(out);
		}
		catch(Exception ioe){
			ioe.printStackTrace();
		}
	 }
	 
	 @SuppressWarnings({ "unused", "unchecked" })
	 public void buildExcelDocument(String filename,
			 						   String[] sheetlist,
			 						   String[] titleList,
			 						   List[] Headrlist,
			 						   List[] DataList,
			 						   HttpServletRequest request,
                                       HttpServletResponse response) {
		try{
			response.setHeader("Cache-Control", "no-cache");
			this.setContentType("application/vnd.ms-excel;charset=UTF-8");
			HSSFWorkbook wb = new HSSFWorkbook();

			for(int a = 0 ; a < sheetlist.length; a++){
				//sheet 생성
				HSSFSheet sheet = wb.createSheet(sheetlist[a]); 
				//title 생성
				HSSFRow titlerow = sheet.createRow(0);
				titlerow.createCell(0).setCellValue(titleList[a]);
				//header 생성
				HSSFRow headerrow = sheet.createRow(1);
				for(int i=0;i<Headrlist[a].size();i++){	
					headerrow.createCell(i).setCellValue(Headrlist[a].get(i).toString());
				}
				//data 생성
				HSSFRow datarow;
				List dataobject = null;
				for(int j=0;j<DataList[a].size();j++){
					datarow = sheet.createRow(j+2);
					dataobject = (List)DataList[a].get(j);
					for(int k=0;k<dataobject.size();k++){
						if(dataobject.get(k)!=null){
							datarow.createCell(k).setCellValue(dataobject.get(k).toString());
						}
					}
				}		
			}
			
			//ListExcelView
			OutputStream out = response.getOutputStream();
			response.setContentType(super.getContentType());
		    response.setHeader("Content-Transfer-Encoding", "binary");
		    response.setHeader("Content-Disposition","attachment;fileName=\""+filename+"\";");
			wb.write(out);
		}
		catch(Exception ioe){
			ioe.printStackTrace();
		}
	 }	 
	
	public String getFilename() {
		return filename;
	}
	public FileOutputStream getFileout() {
		return fileout;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public void setFileout(FileOutputStream fileout) {
		this.fileout = fileout;
	}
}