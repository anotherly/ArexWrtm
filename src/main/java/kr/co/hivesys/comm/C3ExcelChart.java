package kr.co.hivesys.comm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFObjectData;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class C3ExcelChart {

	String filePath = "J:\\test\\새 Microsoft Excel 워크시트.xls";
	String cpFilePath = "J:\\test\\새 Microsoft Excel 워크시트2.xls";
	File excelFile = new File(filePath);

	public void inputImg() {

		if (!excelFile.exists()) {
			System.out.println("Cannot Found File. PATH :" + filePath);
		}

		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(excelFile));

			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			HSSFFont font = null;
			HSSFCellStyle cellStyle = null;

			HSSFWorkbook wb2 = new HSSFWorkbook();
			HSSFSheet sheet2 = null;
			HSSFRow row2 = null;
			HSSFCell cell2 = null;
			HSSFFont font2 = null;
			HSSFCellStyle cellStyle2 = null;

			HSSFPatriarch patriarch = null;
			HSSFPatriarch patriarch2 = null;

			HSSFClientAnchor anchor = null;

			int fontNum = wb.getNumberOfFonts();
			int fontNum2 = wb2.getNumberOfFonts();

			int styleNum = wb.getNumCellStyles();
			int styleNum2 = wb2.getNumCellStyles();

			int sheeNum = wb.getNumberOfSheets();
			int firstRowNum = 0;
			int lastRowNum = 0;
			int cellNum = 0;

			int maxCellNum = 0;
			int MergedRegionNum = 0;

			/************************************************************************************************/
			/* 시트의 등록된 폰트를 복사한다. */
			/************************************************************************************************/

			// System.out.println("ORIGINAL FONT:"+wb.getNumberOfFonts()+" COPY
			// FONT:"+wb2.getNumberOfFonts());
			for (int fontCnt = fontNum2; fontCnt < fontNum; fontCnt++) {
				wb2.createFont();
			}
			// System.out.println("ORIGINAL FONT:"+wb.getNumberOfFonts()+" COPY
			// FONT:"+wb2.getNumberOfFonts());
			for (int fontCnt = 0; fontCnt <= fontNum; fontCnt++) {
				font2 = wb2.getFontAt((short) fontCnt);
				font = wb.getFontAt((short) fontCnt);
				copyFont(font, font2);
				// System.out.println("FONT1 INDEX:"+ font.getIndex()+ " FONT2 INDEX:" +
				// font2.getIndex());
			}

			// System.out.println("ORIGINAL FONT:"+wb.getNumberOfFonts()+" COPY
			// FONT:"+wb2.getNumberOfFonts());

			/************************************************************************************************/
			/* 시트의 등록된 스타일을 복사한다. */
			/************************************************************************************************/

			System.out.println("ORIGINAL STYLE:" + wb.getNumCellStyles() + " COPY STYLE:" + wb2.getNumCellStyles());

			for (int styleCnt = styleNum2; styleCnt < styleNum; styleCnt++) {
				wb2.createCellStyle();
			}

			System.out.println("ORIGINAL STYLE:" + wb.getNumCellStyles() + " COPY STYLE:" + wb2.getNumCellStyles());

			for (int styleCnt = 0; styleCnt < styleNum; styleCnt++) {
				cellStyle2 = wb2.getCellStyleAt((short) styleCnt);
				cellStyle = wb.getCellStyleAt((short) styleCnt);

				copyStyle(cellStyle, cellStyle2);
				cellStyle2.setFont(wb2.getFontAt(cellStyle.getFontIndex()));
			}

			List<HSSFPictureData> picList = wb.getAllPictures();

			int picNum = picList.size();
			HSSFPictureData picData = null;
			for (int picCnt = 0; picCnt < picNum; picCnt++) {
				System.out.println("PIC COUNT : " + picCnt);
				picData = picList.get(picCnt);
				wb2.addPicture(picData.getData(), picData.getFormat());

				anchor = new HSSFClientAnchor(0, 0, 0, 255, (short) 2, 2, (short) 4, 7);
				anchor.setAnchorType(2);
			}

			// System.out.println("ORIGINAL STYLE:"+wb.getNumCellStyles()+" COPY
			// STYLE:"+wb2.getNumCellStyles());

			/************************************************************************************************/
			/* 시트의 내용을 복사한다. */
			/************************************************************************************************/
			// SHEET
			for (int sheetCount = 0; sheetCount < sheeNum; sheetCount++) {
				sheet = wb.getSheetAt(sheetCount);
				sheet2 = wb2.createSheet(sheet.getSheetName());

				sheet2.setDefaultColumnWidth(sheet.getDefaultColumnWidth());
				sheet2.setDefaultRowHeight(sheet.getDefaultRowHeight());

				firstRowNum = sheet.getFirstRowNum();
				lastRowNum = sheet.getLastRowNum();

				// ROW
				for (int rowCount = firstRowNum; rowCount <= lastRowNum; rowCount++) {
					row = sheet.getRow(rowCount);
					row2 = sheet2.createRow(rowCount);

					if (row != null) {
						cellNum = row.getLastCellNum();
						System.out.println(maxCellNum + ":" + cellNum);
						if (maxCellNum < cellNum) {
							maxCellNum = cellNum;
						}

						// CELL
						for (int cellCount = 0; cellCount < cellNum; cellCount++) {
							cell = row.getCell(cellCount);
							cell2 = row2.createCell(cellCount);

							if (cell != null) {
								switch (cell.getCellType()) {
								case HSSFCell.CELL_TYPE_BLANK:
									cell2.setCellValue(cell.getStringCellValue());
									break;
								case HSSFCell.CELL_TYPE_NUMERIC:
									cell2.setCellValue(cell.getNumericCellValue());
									break;
								case HSSFCell.CELL_TYPE_STRING:
									cell2.setCellValue(cell.getStringCellValue());
									break;
								case HSSFCell.CELL_TYPE_ERROR:
									cell2.setCellValue(cell.getErrorCellValue());
									break;
								case HSSFCell.CELL_TYPE_BOOLEAN:
									cell2.setCellValue(cell.getBooleanCellValue());
									break;
								case HSSFCell.CELL_TYPE_FORMULA:
									cell2.setCellFormula(cell.getCellFormula());
									break;
								}
								cell2.setCellStyle(wb2.getCellStyleAt(cell.getCellStyle().getIndex()));
								cell2.setCellComment(cell.getCellComment());
								cell2.setCellType(cell.getCellType());
								// cell2.setHyperlink(cell.getHyperlink());
							}

						}

						row2.setHeight(row.getHeight());
						row2.setHeightInPoints(row.getHeightInPoints());
						row2.setZeroHeight(row.getZeroHeight());

					}
				}

				MergedRegionNum = sheet.getNumMergedRegions();
				for (int MergedRegionCnt = 0; MergedRegionCnt < MergedRegionNum; MergedRegionCnt++) {
					sheet2.addMergedRegion(sheet.getMergedRegion(MergedRegionCnt));
				}

				for (int cellWidthCnt = 0; cellWidthCnt < maxCellNum; cellWidthCnt++) {
					sheet2.setColumnWidth(cellWidthCnt, sheet.getColumnWidth(cellWidthCnt));
				}

				patriarch = sheet.getDrawingPatriarch();
				if (patriarch != null) {
					patriarch2 = sheet2.createDrawingPatriarch();
					patriarch2.setCoordinates(patriarch.getX1(), patriarch.getY1(), patriarch.getX2(),
							patriarch.getY2());
					patriarch2.createPicture(anchor, 2);
					// System.out.println("patriarchNum:"+patriarchNum );
				}

			}
			String localPics = "J:\\test\\Image";
			File imgDir = new File(localPics);

			List allPics = wb.getAllPictures();

			for (int l = 0; l < allPics.size() / 2; l++) {

				Object o = allPics.get(l);
				HSSFPictureData hssfPic = (HSSFPictureData) o;
				System.out.println(hssfPic.toString());
				byte[] hssfPicData = hssfPic.getData();

				/* Compare pic against all available icons */

				FileOutputStream localPicIn = new FileOutputStream(localPics + l + ".jpeg");
				localPicIn.write(hssfPicData);
				localPicIn.close();

			}

			wb2.setActiveSheet(wb.getActiveSheetIndex());

			FileOutputStream fileOut = new FileOutputStream(cpFilePath);
			wb2.write(fileOut);
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}

	/* 폰트의 설정을 복사한다. */
	static void copyFont(HSSFFont fromFont, HSSFFont toFont) {
		toFont.setBoldweight(fromFont.getBoldweight());
		toFont.setCharSet(fromFont.getCharSet());
		toFont.setColor(fromFont.getColor());
		toFont.setFontHeight(fromFont.getFontHeight());
		toFont.setFontHeightInPoints(fromFont.getFontHeightInPoints());
		toFont.setFontName(fromFont.getFontName());
		toFont.setItalic(fromFont.getItalic());
		toFont.setStrikeout(fromFont.getStrikeout());
		toFont.setTypeOffset(fromFont.getTypeOffset());
		toFont.setUnderline(fromFont.getUnderline());
	}

	/* 스타일 설정을 복사한다. */
	static void copyStyle(HSSFCellStyle fromCellStyle, HSSFCellStyle toCellStyle) {
		toCellStyle.setAlignment(fromCellStyle.getAlignment());
		toCellStyle.setBorderBottom(fromCellStyle.getBorderBottom());
		toCellStyle.setBorderLeft(fromCellStyle.getBorderLeft());
		toCellStyle.setBorderRight(fromCellStyle.getBorderRight());
		toCellStyle.setBorderTop(fromCellStyle.getBorderTop());
		toCellStyle.setBottomBorderColor(fromCellStyle.getBottomBorderColor());
		toCellStyle.setDataFormat(fromCellStyle.getDataFormat());
		toCellStyle.setFillBackgroundColor(fromCellStyle.getFillBackgroundColor());
		toCellStyle.setFillForegroundColor(fromCellStyle.getFillForegroundColor());
		toCellStyle.setFillPattern(fromCellStyle.getFillPattern());
		toCellStyle.setHidden(fromCellStyle.getHidden());
		toCellStyle.setIndention(fromCellStyle.getIndention());
		toCellStyle.setLeftBorderColor(fromCellStyle.getLeftBorderColor());
		toCellStyle.setLocked(fromCellStyle.getLocked());
		toCellStyle.setRightBorderColor(fromCellStyle.getRightBorderColor());
		toCellStyle.setRotation(fromCellStyle.getRotation());
		toCellStyle.setTopBorderColor(fromCellStyle.getTopBorderColor());
		// cellStyle2.setUserStyleName(cellStyle.getUserStyleName());
		toCellStyle.setVerticalAlignment(fromCellStyle.getVerticalAlignment());
		toCellStyle.setWrapText(fromCellStyle.getWrapText());
	}

}
