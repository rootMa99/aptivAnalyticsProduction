package com.aptiv.dataAnalytics.service;

import com.aptiv.dataAnalytics.model.ActualDataExcel;
import com.aptiv.dataAnalytics.model.DataExcel;
import com.aptiv.dataAnalytics.model.DataTargetExcel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class UploadAndExtractData {
    public static boolean isValidFormat(MultipartFile file) {
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<DataExcel> getDataFromEXcelFile(InputStream inputStream) {

        List<DataExcel> dataExcels = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("DATA");
            int rowIndex = 0;
            for (Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }

                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                DataExcel dataExcel = new DataExcel();
                DataTargetExcel dataTargetExcel = new DataTargetExcel();
                ActualDataExcel actualDataExcel = new ActualDataExcel();
                while (cellIterator.hasNext()) {
                    //Cell cell = cellIterator.next();
                    Cell cell = row.getCell(cellIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    switch (cellIndex) {
                        case 0, 1, 11 -> {}
                        case 2 -> {
                            if (cell.getCellType() == CellType.STRING) {
                                dataExcel.setProject(cell.getStringCellValue());
                            }
                        }
                        case 3 -> {
                            if (cell.getCellType() == CellType.STRING) {
                                dataExcel.setFamily(cell.getStringCellValue());
                            }
                        }
                        case 4 -> {
                            if (cell.getCellType() == CellType.STRING) {
                                dataExcel.setCrew(cell.getStringCellValue());
                            }
                        }
                        case 5 -> {
                            if (cell.getCellType() == CellType.STRING && cell.getCellType() != CellType.BLANK) {
                                dataExcel.setTeamLeader(cell.getStringCellValue());
                            }
                        }
                        case 6 -> {
                            if (cell.getCellType() == CellType.STRING && cell.getCellType() != CellType.BLANK) {
                                dataExcel.setShiftLeader(cell.getStringCellValue());
                            }
                        }
                        case 7 -> {
                            if (cell.getCellType() == CellType.STRING && cell.getCellType() != CellType.BLANK) {
                                dataExcel.setCoordinator(cell.getStringCellValue());
                            }
                        }
                        case 8 -> {
                            if (cell.getCellType() == CellType.STRING) {
                                dataExcel.setMonth(cell.getStringCellValue());
                            }
                        }
                        case 9 -> {
                            if (cell.getCellType() == CellType.STRING) {
                                dataExcel.setWeek(cell.getStringCellValue());
                            }
                        }
                        case 10 -> {
                            if (cell.getCellType() == CellType.NUMERIC) {
                                dataExcel.setDate(cell.getDateCellValue());
                            }
                        }
                        case 12 -> {
                            if (cell.getCellType() == CellType.NUMERIC) {
                                actualDataExcel.setOutput((int) cell.getNumericCellValue());

                            }
                        }
                        case 13 -> {

                            if (cell.getCellType() == CellType.NUMERIC) {
                                actualDataExcel.setProdH(cell.getNumericCellValue());
                            }
                        }
                        case 14 -> {

                            if (cell.getCellType() == CellType.NUMERIC) {
                                actualDataExcel.setPaidH(cell.getNumericCellValue());
                            }
                        }
                        case 15 -> {

                            if (cell.getCellType() == CellType.NUMERIC) {
                                actualDataExcel.setTotalhc(cell.getNumericCellValue());
                            }
                        }
                        case 16 -> {

                            if (cell.getCellType() == CellType.NUMERIC) {
                                actualDataExcel.setHc(cell.getNumericCellValue());
                            }
                        }
                        case 17 -> {

                            if (cell.getCellType() == CellType.NUMERIC) {
                                actualDataExcel.setOt(cell.getNumericCellValue());
                            }
                        }
                        case 18 -> {

                            if (cell.getCellType() == CellType.NUMERIC) {
                                actualDataExcel.setAb(cell.getNumericCellValue());
                            }
                        }
                        case 19 -> {

                            if (cell.getCellType() == CellType.NUMERIC) {
                                actualDataExcel.setTlo(cell.getNumericCellValue());
                            }
                        }
                        case 20 -> {

                            if (cell.getCellType() == CellType.NUMERIC) {
                                actualDataExcel.setDt(cell.getNumericCellValue());
                            }
                        }
                        case 21 -> {

                            if (cell.getCellType() == CellType.NUMERIC) {
                                dataTargetExcel.setOutputTarget(cell.getNumericCellValue());
                            }
                        }
                        case 22 -> {

                            if (cell.getCellType() == CellType.NUMERIC) {
                                dataTargetExcel.setProdTarget(cell.getNumericCellValue());
                            }
                        }
                        case 23 -> {

                            if (cell.getCellType() == CellType.NUMERIC) {
                                dataTargetExcel.setPayedTarget(cell.getNumericCellValue());
                            }
                        }
                        case 24 -> {

                            if (cell.getCellType() == CellType.NUMERIC) {
                                dataTargetExcel.setHcTarget(cell.getNumericCellValue());
                            }
                        }
                        case 25 -> {

                            if (cell.getCellType() == CellType.NUMERIC) {
                                dataTargetExcel.setAbsTarget(cell.getNumericCellValue());
                            }
                        }
                        case 26 -> {

                            if (cell.getCellType() == CellType.NUMERIC) {
                                dataTargetExcel.setDtTarget(cell.getNumericCellValue());
                            }
                        }
                        case 27 -> {

                            if (cell.getCellType() == CellType.NUMERIC) {
                                dataTargetExcel.setScrap(cell.getNumericCellValue());
                            }
                        }
                        case 28 -> {

                            if (cell.getCellType() == CellType.NUMERIC) {
                                dataTargetExcel.setScrapTarget(cell.getNumericCellValue());
                            }
                        }
                        case 29 -> {

                            if (cell.getCellType() == CellType.NUMERIC) {
                                actualDataExcel.setWsd(cell.getNumericCellValue());
                            }
                        }
                        default -> {

                        }
                    }
                    cellIndex++;
                    if (cellIndex == 30) {
                        rowIndex++;
                        break;
                    }
                }

                rowIndex++;
                dataExcel.setActualDataExcel(actualDataExcel);
                dataExcel.setDataTargetExcel(dataTargetExcel);
                dataExcels.add(dataExcel);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return dataExcels;
    }

}
