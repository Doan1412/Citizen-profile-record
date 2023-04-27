package com.example.pbl.util;

import com.example.pbl.entity.Citizen;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGeneratorUtility {

    public static ByteArrayInputStream citizenDetailReport(List<Citizen> citizens) {
        try(XSSFWorkbook workbook = new XSSFWorkbook()){
            XSSFSheet sheet = workbook.createSheet("List citizen");

            CellStyle cellStyle = workbook.createCellStyle();
            CellStyle headerStyle = workbook.createCellStyle();

            //set border to table
            cellStyle.setBorderTop(BorderStyle.MEDIUM);
            cellStyle.setBorderRight(BorderStyle.MEDIUM);
            cellStyle.setBorderBottom(BorderStyle.MEDIUM);
            cellStyle.setBorderLeft(BorderStyle.MEDIUM);
            cellStyle.setAlignment(HorizontalAlignment.LEFT);

            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            XSSFFont font = ((XSSFWorkbook) workbook).createFont();
            font.setFontName("Time New Roman");
            font.setFontHeightInPoints((short) 16);
            font.setBold(true);
            headerStyle.setFont(font);
            cellStyle.setFont(font);

            // Header
            XSSFRow row = sheet.createRow(0);
            Cell cell0 = row.createCell(0);
            cell0.setCellValue("STT");
            cell0.setCellStyle(cellStyle);

            XSSFCell cell = row.createCell(1);
            cell.setCellValue("CitizenId");
            cell.setCellStyle(cellStyle);

            XSSFCell cell1 = row.createCell(2);
            cell1.setCellValue("Name");
            cell1.setCellStyle(cellStyle);


            XSSFCell cell2 = row.createCell(3);
            cell2.setCellValue("Birthday");
            cell2.setCellStyle(cellStyle);

            XSSFCell cell3 = row.createCell(4);
            cell2.setCellValue("Gender");
            cell2.setCellStyle(cellStyle);

            XSSFCell cell4 = row.createCell(5);
            cell2.setCellValue("Phone");
            cell2.setCellStyle(cellStyle);

            XSSFCell cell5 = row.createCell(6);
            cell2.setCellValue("Address");
            cell2.setCellStyle(cellStyle);

            XSSFCell cell6 = row.createCell(7);
            cell2.setCellValue("Profession");
            cell2.setCellStyle(cellStyle);

            XSSFCell cell7 = row.createCell(8);
            cell2.setCellValue("FamilyId");
            cell2.setCellStyle(cellStyle);

            XSSFCell cell8 = row.createCell(9);
            cell2.setCellValue("Criminal Record");
            cell2.setCellStyle(cellStyle);

            XSSFCell cell9 = row.createCell(10);
            cell2.setCellValue("Married");
            cell2.setCellStyle(cellStyle);

            XSSFCell cell10 = row.createCell(11);
            cell2.setCellValue("Religion");
            cell2.setCellStyle(cellStyle);

            //Set data
            int rowNum = 1;
            for (Citizen citizen : citizens) {
                XSSFRow citizenDataRow = sheet.createRow(rowNum++);

                XSSFCell stt = citizenDataRow.createCell(0);
                stt.setCellStyle(cellStyle);
                stt.setCellValue(citizens.indexOf(citizen)+1);

                XSSFCell IdCell = citizenDataRow.createCell(1);
                IdCell.setCellStyle(cellStyle);
                IdCell.setCellValue(citizen.getId());

                XSSFCell citizenNameCell = citizenDataRow.createCell(2);
                citizenNameCell.setCellStyle(cellStyle);
                citizenNameCell.setCellValue(citizen.getName());

                XSSFCell citizenBirthCell = citizenDataRow.createCell(3);
                citizenBirthCell.setCellStyle(cellStyle);
                citizenBirthCell.setCellValue(citizen.getBirth());

                XSSFCell citizenGenderCell = citizenDataRow.createCell(4);
                citizenGenderCell.setCellStyle(cellStyle);
                citizenGenderCell.setCellValue(citizen.isGender());

                XSSFCell citizenPhoneCell = citizenDataRow.createCell(5);
                citizenPhoneCell.setCellStyle(cellStyle);
                citizenPhoneCell.setCellValue(citizen.getPhone());

                XSSFCell citizenAddressCell = citizenDataRow.createCell(6);
                citizenAddressCell.setCellStyle(cellStyle);
                citizenAddressCell.setCellValue(citizen.getAddress());

                XSSFCell citizenProfession = citizenDataRow.createCell(7);
                citizenProfession.setCellStyle(cellStyle);
                citizenProfession.setCellValue(citizen.getProfession());

                XSSFCell citizenFamilyId = citizenDataRow.createCell(8);
                citizenFamilyId.setCellStyle(cellStyle);
                citizenFamilyId.setCellValue(citizen.getFamily());

                XSSFCell citizenCriminalRecord = citizenDataRow.createCell(9);
                citizenCriminalRecord.setCellStyle(cellStyle);
                citizenCriminalRecord.setCellValue(citizen.getCriminalRecord());

                XSSFCell citizenMarried = citizenDataRow.createCell(10);
                citizenMarried.setCellStyle(cellStyle);
                citizenMarried.setCellValue(citizen.isMarried());

                XSSFCell citizenReligion = citizenDataRow.createCell(11);
                citizenReligion.setCellStyle(cellStyle);
                citizenReligion.setCellValue(citizen.getReligion());
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            //write output to response
            workbook.write(outputStream);
            outputStream.close();
            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
