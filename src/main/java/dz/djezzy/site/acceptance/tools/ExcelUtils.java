package dz.djezzy.site.acceptance.tools;

import dz.djezzy.site.acceptance.business.data.dto.ResponseMessage;
import dz.djezzy.site.acceptance.business.data.dto.SubCategoriesDto;
import dz.djezzy.site.acceptance.exception.ApplicationException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtils {

    public static void getUpdatedTemplate(List<SubCategoriesDto> list) {
        String message;
        String excelFilePath = ".\\uploads\\template-d9.xlsx";

        try {
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheetAt(0);
            sheet.protectSheet("djezzydevs@2020");
            CellStyle lockedStyle = getCellLockedStyle(workbook);
            CellStyle openStyle = getCellOpenStyle(workbook);
            int rowCount = 0;
            int lastRow = sheet.getLastRowNum();

            for (SubCategoriesDto category : list) {
                Row row = sheet.getRow(++rowCount);
                if (row != null) {
                    Cell cell = row.getCell(0);
                    cell.setCellValue("" + category.getCategoriesId());
                    cell.setCellStyle(lockedStyle);

                    cell = row.getCell(1);
                    cell.setCellValue(category.getCategoriesLabel());
                    cell.setCellStyle(lockedStyle);

                    cell = row.getCell(2);
                    cell.setCellValue("" + category.getId());
                    cell.setCellStyle(lockedStyle);

                    cell = row.getCell(3);
                    cell.setCellValue(category.getLabel());
                    cell.setCellStyle(lockedStyle);

                    cell = row.getCell(4);
                    cell.setCellValue("");
                    cell.setCellStyle(openStyle);

                    cell = row.getCell(5);
                    cell.setCellValue("");
                    cell.setCellStyle(openStyle);
                } else {
                    Row newRow = sheet.createRow(rowCount);
                    newRow.setRowStyle(sheet.getRow(rowCount - 1).getRowStyle());
                    createList(category, newRow, workbook);
                }
            }

            if (list.size() < lastRow) {
                removeRows(sheet, list.size(), lastRow);
            }

            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch (IOException | EncryptedDocumentException ex) {
            ex.printStackTrace();
            throw new ApplicationException(ex.getMessage());
        }

    }

    private static void removeRows(Sheet sheet, int size, int lastRow) {
        for (int i = size + 1; i <= lastRow; i++) {
            sheet.removeRow(sheet.getRow(i));
        }
    }

    private static void createList(SubCategoriesDto subCategories, Row row, Workbook workbook) {

        CellStyle lockedStyle = getCellLockedStyle(workbook);
        CellStyle openStyle = getCellOpenStyle(workbook);

        Cell cell = row.createCell(0);
        cell.setCellValue("" + subCategories.getCategoriesId());
        cell.setCellStyle(lockedStyle);

        cell = row.createCell(1);
        cell.setCellValue(subCategories.getCategoriesLabel());
        cell.setCellStyle(lockedStyle);

        cell = row.createCell(2);
        cell.setCellValue("" + subCategories.getId());
        cell.setCellStyle(lockedStyle);

        cell = row.createCell(3);
        cell.setCellValue(subCategories.getLabel());
        cell.setCellStyle(lockedStyle);

        cell = row.createCell(4);
        cell.setCellValue("");
        cell.setCellStyle(openStyle);

        cell = row.createCell(5);
        cell.setCellValue("");
        cell.setCellStyle(openStyle);

    }

    private static CellStyle getCellLockedStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setLocked(true);
        style.setWrapText(true);
        return style;
    }

    private static CellStyle getCellOpenStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setLocked(false);
        return style;
    }
}
