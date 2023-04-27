package com.example.pbl.util;

import com.example.pbl.entity.Citizen;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGeneratorUtility {

    public static ByteArrayInputStream citizenDetailReport(List<Citizen> citizens) {
        try(HSSFWorkbook workbook = new HSSFWorkbook()){
            HSSFSheet sheet = workbook.createSheet("List citizen");

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

            HSSFFont font = ((HSSFWorkbook) workbook).createFont();
            font.setFontName("Time New Roman");
            font.setFontHeightInPoints((short) 16);
            font.setBold(true);
            headerStyle.setFont(font);
            cellStyle.setFont(font);

            // Header
            HSSFRow row = sheet.createRow(0);
            Cell cell0 = row.createCell(0);
            cell0.setCellValue("STT");
            cell0.setCellStyle(cellStyle);

            HSSFCell cell = row.createCell(1);
            cell.setCellValue("CitizenId");
            cell.setCellStyle(cellStyle);

            HSSFCell cell1 = row.createCell(2);
            cell1.setCellValue("Name");
            cell1.setCellStyle(cellStyle);


            HSSFCell cell2 = row.createCell(3);
            cell2.setCellValue("Birthday");
            cell2.setCellStyle(cellStyle);

            HSSFCell cell3 = row.createCell(4);
            cell2.setCellValue("Gender");
            cell2.setCellStyle(cellStyle);

            HSSFCell cell4 = row.createCell(5);
            cell2.setCellValue("Phone");
            cell2.setCellStyle(cellStyle);

            HSSFCell cell5 = row.createCell(6);
            cell2.setCellValue("Address");
            cell2.setCellStyle(cellStyle);

            HSSFCell cell6 = row.createCell(7);
            cell2.setCellValue("Profession");
            cell2.setCellStyle(cellStyle);

            HSSFCell cell7 = row.createCell(8);
            cell2.setCellValue("FamilyId");
            cell2.setCellStyle(cellStyle);

            HSSFCell cell8 = row.createCell(9);
            cell2.setCellValue("Criminal Record");
            cell2.setCellStyle(cellStyle);

            HSSFCell cell9 = row.createCell(10);
            cell2.setCellValue("Married");
            cell2.setCellStyle(cellStyle);

            HSSFCell cell10 = row.createCell(11);
            cell2.setCellValue("Religion");
            cell2.setCellStyle(cellStyle);

            //Set data
            int rowNum = 1;
            for (Citizen citizen : citizens) {
                HSSFRow citizenDataRow = sheet.createRow(rowNum++);

                HSSFCell stt = citizenDataRow.createCell(0);
                stt.setCellStyle(cellStyle);
                stt.setCellValue(citizens.indexOf(citizen)+1);

                HSSFCell IdCell = citizenDataRow.createCell(1);
                IdCell.setCellStyle(cellStyle);
                IdCell.setCellValue(citizen.getId());

                HSSFCell citizenNameCell = citizenDataRow.createCell(2);
                citizenNameCell.setCellStyle(cellStyle);
                citizenNameCell.setCellValue(citizen.getName());

                HSSFCell citizenBirthCell = citizenDataRow.createCell(3);
                citizenBirthCell.setCellStyle(cellStyle);
                citizenBirthCell.setCellValue(citizen.getBirth());

                HSSFCell citizenGenderCell = citizenDataRow.createCell(4);
                citizenGenderCell.setCellStyle(cellStyle);
                citizenGenderCell.setCellValue(citizen.isGender());

                HSSFCell citizenPhoneCell = citizenDataRow.createCell(5);
                citizenPhoneCell.setCellStyle(cellStyle);
                citizenPhoneCell.setCellValue(citizen.getPhone());

                HSSFCell citizenAddressCell = citizenDataRow.createCell(6);
                citizenAddressCell.setCellStyle(cellStyle);
                citizenAddressCell.setCellValue(citizen.getAddress());

                HSSFCell citizenProfession = citizenDataRow.createCell(7);
                citizenProfession.setCellStyle(cellStyle);
                citizenProfession.setCellValue(citizen.getProfession());

                HSSFCell citizenFamilyId = citizenDataRow.createCell(8);
                citizenFamilyId.setCellStyle(cellStyle);
                citizenFamilyId.setCellValue(citizen.getFamily());

                HSSFCell citizenCriminalRecord = citizenDataRow.createCell(9);
                citizenCriminalRecord.setCellStyle(cellStyle);
                citizenCriminalRecord.setCellValue(citizen.getCriminalRecord());

                HSSFCell citizenMarried = citizenDataRow.createCell(10);
                citizenMarried.setCellStyle(cellStyle);
                citizenMarried.setCellValue(citizen.isMarried());

                HSSFCell citizenReligion = citizenDataRow.createCell(11);
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
