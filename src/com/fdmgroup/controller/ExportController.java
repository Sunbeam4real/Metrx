package com.fdmgroup.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.model.T_User;

@Controller
//session_attribute
@SessionAttributes("totalActivitiesSize")
public class ExportController {
	private static String[] columns = {"Activity Type", "Assigned", "Jan","Feb", "Mar","Apr", "May",  "Jun","Jul","Aug", "Sep", "Oct","Nov","Dec"};
	
	@RequestMapping(value="/export_action", method = RequestMethod.GET)
	public ModelAndView export (HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Workbook workbook = new XSSFWorkbook(); 
		ModelAndView mv = new ModelAndView();

        CreationHelper createHelper = workbook.getCreationHelper();

        
        Sheet sheet = workbook.createSheet("T_User");

        
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLACK.getIndex());

        
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

       
        Row headerRow = sheet.createRow(0);

        
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

       
        int rowNum = 1;
   
     
        HashMap<T_User, ArrayList> exportStuff = (HashMap<T_User, ArrayList>) request.getSession().getAttribute("totActivities");
    
        for(T_User User: exportStuff.keySet()) {
   
             
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("Call");
            row.createCell(1).setCellValue(User.getUsername());
            for (int p = 0; p < exportStuff.get(User).size(); p++) {
                    row.createCell(p+2).setCellValue((double) (int) ((ArrayList) exportStuff.get(User).get(p)).get(0));
            
            } 
           
        }
        
        for(T_User User: exportStuff.keySet()) {
        
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("Deal");
            row.createCell(1).setCellValue(User.getUsername());
            for (int p = 0; p < exportStuff.get(User).size(); p++) {
            	 row.createCell(p+2).setCellValue((double) (int) ((ArrayList) exportStuff.get(User).get(p)).get(1));
            
            } 
           
        }
        
        for(T_User User: exportStuff.keySet()) {
     
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("Email");
            row.createCell(1).setCellValue(User.getUsername());
            for (int p = 0; p < exportStuff.get(User).size(); p++) {
                    row.createCell(p+2).setCellValue((double) (int) ((ArrayList) exportStuff.get(User).get(p)).get(2));
            
            } 
           
        }

        
        

              
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

       

        System.out.println(""+System.getProperty("user.home")+"\\Desktop\\result.xlsx");
        FileOutputStream fileOut = new FileOutputStream(""+System.getProperty("user.home")+"\\Desktop\\result.xlsx");
        workbook.write(fileOut);
        fileOut.close();

        
        workbook.close();
        
        mv.addObject("totalActivities", exportStuff);
        mv.addObject("totalActivitiesSize", exportStuff.size());
        mv.setViewName("view-doc.jsp");
        
        return mv;
	}
}
