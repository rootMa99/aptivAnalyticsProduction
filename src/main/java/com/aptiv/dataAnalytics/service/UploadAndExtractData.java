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

        List<DataExcel> dataExcels=new ArrayList<>();
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
                DataExcel dataExcel=new DataExcel();
                DataTargetExcel dataTargetExcel=new DataTargetExcel();
                ActualDataExcel actualDataExcel=new ActualDataExcel();
                System.out.println(rowIndex);
                while (cellIterator.hasNext()) {
                    //Cell cell = cellIterator.next();
                    Cell cell = row.getCell(cellIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    System.out.println(cellIndex);
                    switch (cellIndex) {
                        case 0 -> {
                            System.out.println(cell.getCellType());
                            if (cell.getCellType() == CellType.STRING) {
                                System.out.println("plant ######### " + cell.getStringCellValue());
                            } else {
                                System.out.println("plant " + cell.getCellType());
                            }
                        }
                        case 1 -> {
                            System.out.println(cell.getCellType());
                            if (cell.getCellType() == CellType.STRING) {
                                System.out.println("area " + cell.getStringCellValue());
                            } else {
                                System.out.println("area " + cell.getCellType());
                            }
                        }
                        case 2 -> {
                            System.out.println(cell.getCellType());
                            if (cell.getCellType() == CellType.STRING) {
                                dataExcel.setProject(cell.getStringCellValue());
                                System.out.println("project " + cell.getStringCellValue());
                            } else {
                                System.out.println("project " + cell.getCellType());
                            }
                        }
                        case 3 -> {
                            System.out.println(cell.getCellType());
                            if (cell.getCellType() == CellType.STRING) {
                                dataExcel.setFamily(cell.getStringCellValue());
                                System.out.println("family " + cell.getStringCellValue());
                            } else {
                                System.out.println("family " + cell.getCellType());
                            }
                        }
                        case 4 -> {
                            System.out.println(cell.getCellType());
                            if (cell.getCellType() == CellType.STRING) {
                                dataExcel.setCrew(cell.getStringCellValue());
                                System.out.println("crew " + cell.getStringCellValue());
                            } else {
                                System.out.println("crew " + cell.getCellType());
                            }
                        }
                        case 5 -> {
                            System.out.println(cell.getCellType());
                            if (cell.getCellType() == CellType.STRING && cell.getCellType() != CellType.BLANK) {
                                dataExcel.setTeamLeader(cell.getStringCellValue());
                                System.out.println("team leader " + cell.getStringCellValue());
                            } else {
                                System.out.println("team leader " + cell.getCellType());
                            }
                        }
                        case 6 -> {
                            System.out.println(cell.getCellType());
                            if (cell.getCellType() == CellType.STRING && cell.getCellType() != CellType.BLANK) {
                                dataExcel.setShiftLeader(cell.getStringCellValue());
                                System.out.println("shift leader " + cell.getStringCellValue());
                            } else {
                                System.out.println("shift leader " + cell.getCellType());
                            }
                        }
                        case 7 -> {
                            System.out.println(cell.getCellType());
                            if (cell.getCellType() == CellType.STRING && cell.getCellType() != CellType.BLANK) {
                                dataExcel.setCoordinator(cell.getStringCellValue());
                                System.out.println("coordinator " + cell.getStringCellValue());
                            } else {
                                System.out.println("coordinator " + cell.getCellType());
                            }
                        }
                        case 8 -> {
                            System.out.println(cell.getCellType());
                            if (cell.getCellType() == CellType.STRING) {
                                dataExcel.setMonth(cell.getStringCellValue());
                                System.out.println("month " + cell.getStringCellValue());
                            } else {
                                System.out.println("month " + cell.getCellType());
                            }
                        }
                        case 9 -> {
                            System.out.println(cell.getCellType());
                            if (cell.getCellType() == CellType.STRING) {
                                dataExcel.setWeek(cell.getStringCellValue());
                                System.out.println("week " + cell.getStringCellValue());
                            } else {
                                System.out.println("week " + cell.getCellType());
                            }
                        }
                        case 10 -> {
                            System.out.println(cell.getCellType());
                            if (cell.getCellType() == CellType.NUMERIC) {
                                dataExcel.setDate(cell.getDateCellValue());
                                System.out.println("date " + cell.getDateCellValue());
                            } else {
                                System.out.println("date " + cell.getCellType());
                            }
                        }
                        case 11 -> {
                            System.out.println(cell.getCellType());
                            if (cell.getCellType() == CellType.STRING) {
                                System.out.println("filter " + cell.getStringCellValue());
                            } else {
                                System.out.println("filter " + cell.getCellType());
                            }
                        }
                        case 12 -> {
                            System.out.println(cell.getCellType());
                            if (cell.getCellType() == CellType.NUMERIC) {
                                actualDataExcel.setOutput((int) cell.getNumericCellValue());
                                System.out.println("output " + cell.getNumericCellValue());

                            } else {
                                System.out.println("output " + cell.getCellType());
                            }
                        }
                        case 13 -> {
                            System.out.println(cell.getCellType());

                            if (cell.getCellType() == CellType.NUMERIC) {
                                actualDataExcel.setProdH(cell.getNumericCellValue());
                                System.out.println("prod-h " + cell.getNumericCellValue());
                            } else {
                                System.out.println("prod-h " + cell.getCellType());
                            }
                        }
                        case 14 -> {
                            System.out.println(cell.getCellType());

                            if (cell.getCellType() == CellType.NUMERIC) {
                                actualDataExcel.setPaidH(cell.getNumericCellValue());
                                System.out.println("paid-h " + cell.getNumericCellValue());
                            } else {
                                System.out.println("paid-h " + cell.getCellType());
                            }
                        }
                        case 15 -> {
                            System.out.println(cell.getCellType());

                            if (cell.getCellType() == CellType.NUMERIC) {
                                actualDataExcel.setTotalhc(cell.getNumericCellValue());
                                System.out.println("totalHc " + cell.getNumericCellValue());
                            } else {
                                System.out.println("totalHc " + cell.getCellType());
                            }
                        }
                        case 16 -> {
                            System.out.println(cell.getCellType());

                            if (cell.getCellType() == CellType.NUMERIC) {
                                actualDataExcel.setHc(cell.getNumericCellValue());
                                System.out.println("hc " + cell.getNumericCellValue());
                            } else {
                                System.out.println("hc " + cell.getCellType());
                            }
                        }
                        case 17 -> {
                            System.out.println(cell.getCellType());

                            if (cell.getCellType() == CellType.NUMERIC) {
                                actualDataExcel.setOt(cell.getNumericCellValue());
                                System.out.println("ot " + cell.getNumericCellValue());
                            } else {
                                System.out.println("ot " + cell.getCellType());
                            }
                        }
                        case 18 -> {
                            System.out.println(cell.getCellType());

                            if (cell.getCellType() == CellType.NUMERIC) {
                                actualDataExcel.setAb(cell.getNumericCellValue());
                                System.out.println("ab " + cell.getNumericCellValue());
                            } else {
                                System.out.println("ab " + cell.getCellType());
                            }
                        }
                        case 19 -> {
                            System.out.println(cell.getCellType());

                            if (cell.getCellType() == CellType.NUMERIC) {
                                actualDataExcel.setTlo(cell.getNumericCellValue());
                                System.out.println("tlo " + cell.getNumericCellValue());
                            } else {
                                System.out.println("tlo " + cell.getCellType());
                            }
                        }
                        case 20 -> {
                            System.out.println(cell.getCellType());

                            if (cell.getCellType() == CellType.NUMERIC) {
                                actualDataExcel.setDt(cell.getNumericCellValue());
                                System.out.println("dt " + cell.getNumericCellValue());
                            } else {
                                System.out.println("dt " + cell.getCellType());
                            }
                        }
                        case 21 -> {
                            System.out.println(cell.getCellType());

                            if (cell.getCellType() == CellType.NUMERIC) {
                                dataTargetExcel.setOutputTarget(cell.getNumericCellValue());
                                System.out.println("output target " + cell.getNumericCellValue());
                            } else {
                                System.out.println("output target " + cell.getCellType());
                            }
                        }
                        case 22 -> {
                            System.out.println(cell.getCellType());

                            if (cell.getCellType() == CellType.NUMERIC) {
                                dataTargetExcel.setProdTarget(cell.getNumericCellValue());
                                System.out.println("prod target " + cell.getNumericCellValue());
                            } else {
                                System.out.println("prod target " + cell.getCellType());
                            }
                        }
                        case 23 -> {
                            System.out.println(cell.getCellType());

                            if (cell.getCellType() == CellType.NUMERIC) {
                                dataTargetExcel.setPayedTarget(cell.getNumericCellValue());
                                System.out.println("payed target " + cell.getNumericCellValue());
                            } else {
                                System.out.println("payed target " + cell.getCellType());
                            }
                        }
                        case 24 -> {
                            System.out.println(cell.getCellType());

                            if (cell.getCellType() == CellType.NUMERIC) {
                                dataTargetExcel.setHcTarget(cell.getNumericCellValue());
                                System.out.println("hc target " + cell.getNumericCellValue());
                            } else {
                                System.out.println("hc target " + cell.getCellType());
                            }
                        }
                        case 25 -> {
                            System.out.println(cell.getCellType());

                            if (cell.getCellType() == CellType.NUMERIC) {
                                dataTargetExcel.setAbsTarget(cell.getNumericCellValue());
                                System.out.println("abs target " + cell.getNumericCellValue());
                            } else {
                                System.out.println("abs target " + cell.getCellType());
                            }
                        }
                        case 26 -> {
                            System.out.println(cell.getCellType());

                            if (cell.getCellType() == CellType.NUMERIC) {
                                dataTargetExcel.setDtTarget(cell.getNumericCellValue());
                                System.out.println("dt target " + cell.getNumericCellValue());
                            } else {
                                System.out.println("dt target " + cell.getCellType());
                            }
                        }
                        case 27 -> {
                            System.out.println(cell.getCellType());

                            if (cell.getCellType() == CellType.NUMERIC) {
                                dataTargetExcel.setScrap(cell.getNumericCellValue());
                                System.out.println("scrap " + cell.getNumericCellValue());
                            } else {
                                System.out.println("scrap " + cell.getCellType());
                            }
                        }
                        case 28 -> {
                            System.out.println(cell.getCellType());

                            if (cell.getCellType() == CellType.NUMERIC) {
                                dataTargetExcel.setScrapTarget(cell.getNumericCellValue());
                                System.out.println("scrap target " + cell.getNumericCellValue());
                            } else {
                                System.out.println("scrap target " + cell.getCellType());
                            }
                        }
                        default -> {

                        }
                    }
                    cellIndex++;
                    if (cellIndex == 29) {
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
