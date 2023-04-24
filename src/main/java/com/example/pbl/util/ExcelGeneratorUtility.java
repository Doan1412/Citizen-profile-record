package com.example.pbl.util;

import com.example.pbl.entity.Citizen;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExcelGeneratorUtility {

    public static void employeeDetailReport(HttpServletResponse response, List<Citizen> citizens) {
        try(Workbook workbook = new XSSFWorkbook()){
            Sheet sheet = workbook.createSheet("List citizen");

            CellStyle cellStyle = workbook.createCellStyle();


            //set border to table
            cellStyle.setBorderTop(BorderStyle.MEDIUM);
            cellStyle.setBorderRight(BorderStyle.MEDIUM);
            cellStyle.setBorderBottom(BorderStyle.MEDIUM);
            cellStyle.setBorderLeft(BorderStyle.MEDIUM);
            cellStyle.setAlignment(HorizontalAlignment.LEFT);


            // Header
            Row row = sheet.createRow(0);
            Cell cell0 = row.createCell(0);
            cell0.setCellValue("STT");
            cell0.setCellStyle(cellStyle);

            Cell cell = row.createCell(1);
            cell.setCellValue("CitizenId");
            cell.setCellStyle(cellStyle);

            Cell cell1 = row.createCell(2);
            cell1.setCellValue("Name");
            cell1.setCellStyle(cellStyle);


            Cell cell2 = row.createCell(3);
            cell2.setCellValue("Birthday");
            cell2.setCellStyle(cellStyle);

            Cell cell3 = row.createCell(4);
            cell2.setCellValue("Gender");
            cell2.setCellStyle(cellStyle);

            Cell cell4 = row.createCell(5);
            cell2.setCellValue("Phone");
            cell2.setCellStyle(cellStyle);

            Cell cell5 = row.createCell(6);
            cell2.setCellValue("Address");
            cell2.setCellStyle(cellStyle);

            Cell cell6 = row.createCell(7);
            cell2.setCellValue("Profession");
            cell2.setCellStyle(cellStyle);

            Cell cell7 = row.createCell(8);
            cell2.setCellValue("FamilyId");
            cell2.setCellStyle(cellStyle);

            Cell cell8 = row.createCell(9);
            cell2.setCellValue("Criminal Record");
            cell2.setCellStyle(cellStyle);

            Cell cell9 = row.createCell(10);
            cell2.setCellValue("Married");
            cell2.setCellStyle(cellStyle);

            Cell cell10 = row.createCell(11);
            cell2.setCellValue("Religion");
            cell2.setCellStyle(cellStyle);

            //Set data
            int rowNum = 1;
            for (Citizen citizen : citizens) {
                Row citizenDataRow = sheet.createRow(rowNum++);

                Cell stt = citizenDataRow.createCell(0);
                stt.setCellStyle(cellStyle);
                stt.setCellValue(citizens.indexOf(citizen)+1);

                Cell empIdCell = citizenDataRow.createCell(1);
                empIdCell.setCellStyle(cellStyle);
                empIdCell.setCellValue(citizen.getId());

                Cell citizenNameCell = citizenDataRow.createCell(2);
                citizenNameCell.setCellStyle(cellStyle);
                citizenNameCell.setCellValue(citizen.getName());

                Cell citizenBirthCell = citizenDataRow.createCell(3);
                citizenBirthCell.setCellStyle(cellStyle);
                citizenBirthCell.setCellValue(citizen.getBirth());

                Cell citizenGenderCell = citizenDataRow.createCell(4);
                citizenGenderCell.setCellStyle(cellStyle);
                citizenGenderCell.setCellValue(citizen.isGender());

                Cell citizenPhoneCell = citizenDataRow.createCell(5);
                citizenPhoneCell.setCellStyle(cellStyle);
                citizenPhoneCell.setCellValue(citizen.getPhone());

                Cell citizenAddressCell = citizenDataRow.createCell(6);
                citizenAddressCell.setCellStyle(cellStyle);
                citizenAddressCell.setCellValue(citizen.getAddress());

                Cell citizenProfession = citizenDataRow.createCell(7);
                citizenProfession.setCellStyle(cellStyle);
                citizenProfession.setCellValue(citizen.getProfession());

                Cell citizenFamilyId = citizenDataRow.createCell(8);
                citizenFamilyId.setCellStyle(cellStyle);
                citizenFamilyId.setCellValue(citizen.getFamily());

                Cell citizenCriminalRecord = citizenDataRow.createCell(9);
                citizenCriminalRecord.setCellStyle(cellStyle);
                citizenCriminalRecord.setCellValue(citizen.getCriminalRecord());

                Cell citizenMarried = citizenDataRow.createCell(10);
                citizenMarried.setCellStyle(cellStyle);
                citizenMarried.setCellValue(citizen.isMarried());

                Cell citizenReligion = citizenDataRow.createCell(11);
                citizenReligion.setCellStyle(cellStyle);
                citizenReligion.setCellValue(citizen.getReligion());
            }

            //write output to response
            workbook.write(response.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
