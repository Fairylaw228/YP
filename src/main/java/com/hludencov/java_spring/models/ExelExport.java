package com.hludencov.java_spring.models;

import com.hludencov.java_spring.interfaces.IExelExport;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class ExelExport {

    private IExelExport[] exelExportList;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;


    public ExelExport(IExelExport[] exelExportList) {
        this.exelExportList = exelExportList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeader() {
        sheet = workbook.createSheet("Summary");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        if (Arrays.stream(exelExportList).findAny().isPresent()) {
            int i = 0;
            for (Object cellData : exelExportList[0].getHeaders()) {
                createCell(row, i++, cellData, style);
            }
        }


    }

    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Double) {
            cell.setCellValue((Double) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }

    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (IExelExport record : exelExportList) {
            Row row = sheet.createRow(rowCount++);

            int i = 0;
            for (Object cellData : record.getData()) {
                createCell(row, i++, cellData, style);
            }
        }

//        sheet.createRow(rowCount++).createCell(3).setCellFormula("AVERAGE(C2:C800)");
//        createCell(sheet.createRow(rowCount++),3,"",style );
    }

    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}