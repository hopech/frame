package com.sicilon.frame.sutils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelUtil {
	private static HSSFWorkbook wb;

	private static CellStyle titleStyle; // 标题行样式
	private static Font titleFont; // 标题行字体
	private static CellStyle separateLineStyle; // 单独行样式
	private static Font separateLineFont; // 单独行字体
	private static CellStyle headStyle; // 表头行样式
	private static Font headFont; // 表头行字体
	private static CellStyle contentStyle; // 内容行样式
	private static Font contentFont; // 内容行字体

	/**
	 * 导出文件
	 * 
	 * @param setInfo
	 * @param outputExcelFileName
	 * @return
	 * @throws IOException
	 */
	public static boolean export2File(ExcelExportData setInfo, String outputExcelFileName) throws Exception {

		boolean result = false;
		File file = new File(outputExcelFileName);
		File parent = file.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}

		if (file.exists()) {
			result = file.delete();
		}
		FileOutputStream fs = new FileOutputStream(file);
		fs.write(export2ByteArray(setInfo));
		fs.flush();
		fs.close();
		result = true;
		return result;
	}

	/**
	 * 导出到byte数组
	 * 
	 * @param setInfo
	 * @return
	 * @throws Exception
	 */
	public static byte[] export2ByteArray(ExcelExportData setInfo) throws Exception {
		return export2Stream(setInfo).toByteArray();
	}

	public static byte[] export2ByteArray(ExcelExportData setInfo, Integer index) throws Exception {
		return export2Stream(setInfo, index).toByteArray();
	}

	/**
	 * 导出到流
	 * 
	 * @param setInfo
	 * @return
	 * @throws Exception
	 */
	public static ByteArrayOutputStream export2Stream(ExcelExportData setInfo) throws Exception {
		init();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		Set<Entry<String, List<?>>> set = setInfo.getDataMap().entrySet();
		String[] sheetNames = new String[setInfo.getDataMap().size()];
		int sheetNameNum = 0;
		for (Entry<String, List<?>> entry : set) {
			sheetNames[sheetNameNum] = entry.getKey();
			sheetNameNum++;
		}
		HSSFSheet[] sheets = getSheets(setInfo.getDataMap().size(), sheetNames);
		int sheetNum = 0;
		for (Entry<String, List<?>> entry : set) {
			// Sheet
			List<?> objs = entry.getValue();

			// 标题行
			//createTableTitleRow(setInfo, sheets, sheetNum);

			// 独立行
			//createTableSeparateRow(setInfo, sheets, sheetNum);

			// 表头
			creatTableHeadRow(setInfo, sheets, sheetNum);

			// 表体
			String[] fieldNames = setInfo.getFieldNames().get(sheetNum);

			int rowNum = 3;
			for (Object obj : objs) {
				HSSFRow contentRow = sheets[sheetNum].createRow(rowNum);
				contentRow.setHeight((short) 300);
				HSSFCell[] cells = getCells(contentRow, setInfo.getFieldNames().get(sheetNum).length);
				// 设置序号列值，除去标题行和独立行
				int separateRows = setInfo.getSeparateLines() == null ? 0 : setInfo.getSeparateLines().length;
				cells[0].setCellValue(contentRow.getRowNum() - separateRows - 1);
				int cellNum = 1; // 去掉一列序号，因此从1开始
				if (fieldNames != null) {
					for (int num = 0; num < fieldNames.length; num++) {
						Method method = obj.getClass().getMethod("get" + StringUtils.capitalize(fieldNames[num]));
						Object value = method.invoke(obj);
						if (value != null && value instanceof Double) {
							cells[cellNum].setCellValue(round((Double) value, 2, BigDecimal.ROUND_HALF_UP));
						} else {
							cells[cellNum].setCellValue(value == null ? "" : value.toString());
						}

						cellNum++;
					}
				}
				rowNum++;
			}
			adjustColumnSize(sheets, sheetNum, fieldNames); // 自动调整列宽
			sheetNum++;
		}
		wb.write(outputStream);
		return outputStream;
	}

	/**
	 * 导出到流
	 * 
	 * @param setInfo
	 * @return
	 * @throws Exception
	 */
	public static ByteArrayOutputStream export2Stream(ExcelExportData setInfo, int index) throws Exception {
		init();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		Set<Entry<String, List<?>>> set = setInfo.getDataMap().entrySet();
		String[] sheetNames = new String[setInfo.getDataMap().size()];
		int sheetNameNum = 0;
		for (Entry<String, List<?>> entry : set) {
			sheetNames[sheetNameNum] = entry.getKey();
			sheetNameNum++;
		}
		HSSFSheet[] sheets = getSheets(setInfo.getDataMap().size(), sheetNames);
		int sheetNum = 0;
		for (Entry<String, List<?>> entry : set) {
			// Sheet
			List<?> objs = entry.getValue();

			// 标题行
			createTableTitleRow(setInfo, sheets, sheetNum);

			// 独立行
			// createTableSeparateRow(setInfo, sheets, sheetNum);

			// 表头
			creatTableHeadRow(setInfo, sheets, sheetNum, 1);

			// 表体
			String[] fieldNames = setInfo.getFieldNames().get(sheetNum);

			int rowNum = 2;
			if (objs != null && objs.size() > 0) {
				for (Object obj : objs) {
					HSSFRow contentRow = sheets[sheetNum].createRow(rowNum);
					contentRow.setHeight((short) 300);
					HSSFCell[] cells = getCells(contentRow, setInfo.getFieldNames().get(sheetNum).length);
					// 设置序号列值，除去标题行和独立行
					// int separateRows = setInfo.getSeparateLines() == null ? 0
					// : setInfo.getSeparateLines().length;
					// 序号开始值
					cells[0].setCellValue(contentRow.getRowNum() - 1);
					// 列
					int cellNum = 1; // 去掉一列序号，因此从1开始
					if (fieldNames != null) {
						for (int num = 0; num < fieldNames.length; num++) {
							Method method = obj.getClass().getMethod("get" + StringUtils.capitalize(fieldNames[num]));
							Object value = method.invoke(obj);
							if (value != null && value instanceof Double) {
								cells[cellNum].setCellValue(round((Double) value, index, BigDecimal.ROUND_HALF_UP));
							} else {
								cells[cellNum].setCellValue(value == null ? "" : value.toString());
							}

							cellNum++;
						}
					}
					rowNum++;
				}
			}
			adjustColumnSize(sheets, sheetNum, fieldNames); // 自动调整列宽
			sheetNum++;

		}
		wb.write(outputStream);
		return outputStream;
	}

	/**
	 * @Description: 初始化
	 */
	private static void init() {
		wb = new HSSFWorkbook();

		titleFont = wb.createFont();
		titleStyle = wb.createCellStyle();
		separateLineStyle = wb.createCellStyle();
		separateLineFont = wb.createFont();
		headStyle = wb.createCellStyle();
		headFont = wb.createFont();
		contentStyle = wb.createCellStyle();
		contentFont = wb.createFont();

		initTitleCellStyle();
		initTitleFont();
		initSeparateRowCellStyle();
		initSeparateRowFont();
		initHeadCellStyle();
		initHeadFont();
		initContentCellStyle();
		initContentFont();
	}

	/**
	 * @Description: 自动调整列宽
	 */
	private static void adjustColumnSize(HSSFSheet[] sheets, int sheetNum, String[] fieldNames) {
		for (int i = 0; i < fieldNames.length + 1; i++) {
			sheets[sheetNum].autoSizeColumn(i, true);
		}
	}

	/**
	 * @Description: 创建标题行(需合并单元格)
	 */
	private static void createTableTitleRow(ExcelExportData setInfo, HSSFSheet[] sheets, int sheetNum) {
		CellRangeAddress titleRange = new CellRangeAddress(0, 0, 0, setInfo.getFieldNames().get(sheetNum).length);
		sheets[sheetNum].addMergedRegion(titleRange);
		HSSFRow titleRow = sheets[sheetNum].createRow(0);
		titleRow.setHeight((short) 800);
		HSSFCell titleCell = titleRow.createCell(0);
		titleCell.setCellStyle(titleStyle);
		titleCell.setCellValue(setInfo.getTitles()[sheetNum]);
	}

	/**
	 * @Description: 创建单独的行(需合并单元格)
	 */
	private static void createTableSeparateRow(ExcelExportData setInfo, HSSFSheet[] sheets, int sheetNum) {
		CellRangeAddress range = new CellRangeAddress(1, 1, 0, setInfo.getFieldNames().get(sheetNum).length);
		String[] separateLines = setInfo.getSeparateLines();
		if (separateLines != null && separateLines.length > 0) {
			for (int i = 0; i < separateLines.length; i++) {
				sheets[sheetNum].addMergedRegion(range);
				HSSFRow separateRow = sheets[sheetNum].createRow(1);
				separateRow.setHeight((short) 450);
				HSSFCell separateCell = separateRow.createCell(0);
				separateCell.setCellStyle(separateLineStyle);
				separateCell.setCellValue(separateLines[i]);
			}
		}
	}

	/**
	 * @Description: 创建表头行(需合并单元格)
	 */
	private static void creatTableHeadRow(ExcelExportData setInfo, HSSFSheet[] sheets, int sheetNum) {
		// 表头
		HSSFRow headRow = sheets[sheetNum].createRow(2);
		headRow.setHeight((short) 350);
		// 序号列
		HSSFCell snCell = headRow.createCell(0);
		snCell.setCellStyle(headStyle);
		snCell.setCellValue("序号");
		// 列头名称
		for (int num = 1, len = setInfo.getColumnNames().get(sheetNum).length; num <= len; num++) {
			HSSFCell headCell = headRow.createCell(num);
			headCell.setCellStyle(headStyle);
			headCell.setCellValue(setInfo.getColumnNames().get(sheetNum)[num - 1]);
		}
	}

	private static void creatTableHeadRow(ExcelExportData setInfo, HSSFSheet[] sheets, int sheetNum, int row) {
		// 表头
		HSSFRow headRow = sheets[sheetNum].createRow(row);
		headRow.setHeight((short) 350);
		// 序号列
		HSSFCell snCell = headRow.createCell(0);
		snCell.setCellStyle(headStyle);
		snCell.setCellValue("序号");
		// 列头名称
		for (int num = 1, len = setInfo.getColumnNames().get(sheetNum).length; num <= len; num++) {
			HSSFCell headCell = headRow.createCell(num);
			headCell.setCellStyle(headStyle);
			headCell.setCellValue(setInfo.getColumnNames().get(sheetNum)[num - 1]);
		}
	}

	/**
	 * @Description: 创建所有的Sheet
	 */
	private static HSSFSheet[] getSheets(int num, String[] names) {
		HSSFSheet[] sheets = new HSSFSheet[num];
		for (int i = 0; i < num; i++) {
			sheets[i] = wb.createSheet(names[i]);
		}
		return sheets;
	}

	/**
	 * @Description: 创建内容行的每一列(附加一列序号)
	 */
	private static HSSFCell[] getCells(HSSFRow contentRow, int num) {
		HSSFCell[] cells = new HSSFCell[num + 1];

		for (int i = 0, len = cells.length; i < len; i++) {
			cells[i] = contentRow.createCell(i);
			cells[i].setCellStyle(contentStyle);
		}

		return cells;
	}

	/**
	 * @Description: 初始化标题行样式
	 */
	private static void initTitleCellStyle() {
		titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
		titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		titleStyle.setFont(titleFont);
		titleStyle.setFillBackgroundColor(IndexedColors.SKY_BLUE.index);
	}

	/**
	 * @Description: 初始化独立行样式
	 */
	private static void initSeparateRowCellStyle() {
		separateLineStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		separateLineStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		separateLineStyle.setFont(separateLineFont);
		separateLineStyle.setFillBackgroundColor(IndexedColors.BLACK.index);
	}

	/**
	 * @Description: 初始化表头行样式
	 */
	private static void initHeadCellStyle() {
		headStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		headStyle.setFont(headFont);
		headStyle.setFillBackgroundColor(IndexedColors.YELLOW.index);
		headStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
		headStyle.setBorderBottom(CellStyle.BORDER_THIN);
		headStyle.setBorderLeft(CellStyle.BORDER_THIN);
		headStyle.setBorderRight(CellStyle.BORDER_THIN);
		headStyle.setTopBorderColor(IndexedColors.BLACK.index);
		headStyle.setBottomBorderColor(IndexedColors.BLACK.index);
		headStyle.setLeftBorderColor(IndexedColors.BLACK.index);
		headStyle.setRightBorderColor(IndexedColors.BLACK.index);
	}

	/**
	 * @Description: 初始化内容行样式
	 */
	private static void initContentCellStyle() {
		contentStyle.setAlignment(CellStyle.ALIGN_CENTER);
		contentStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		contentStyle.setFont(contentFont);
		contentStyle.setBorderTop(CellStyle.BORDER_THIN);
		contentStyle.setBorderBottom(CellStyle.BORDER_THIN);
		contentStyle.setBorderLeft(CellStyle.BORDER_THIN);
		contentStyle.setBorderRight(CellStyle.BORDER_THIN);
		contentStyle.setTopBorderColor(IndexedColors.BLACK.index);
		contentStyle.setBottomBorderColor(IndexedColors.BLACK.index);
		contentStyle.setLeftBorderColor(IndexedColors.BLACK.index);
		contentStyle.setRightBorderColor(IndexedColors.BLACK.index);
		contentStyle.setWrapText(false); // 单元格内不允许字段换行
	}

	/**
	 * @Description: 初始化标题行字体
	 */
	private static void initTitleFont() {
		titleFont.setFontName("华文楷体");
		titleFont.setFontHeightInPoints((short) 20);
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		titleFont.setCharSet(Font.DEFAULT_CHARSET);
		titleFont.setColor(IndexedColors.BLACK.index);
	}

	/**
	 * @Description: 初始化独立行字体
	 */
	private static void initSeparateRowFont() {
		separateLineFont.setFontName("隶书");
		separateLineFont.setFontHeightInPoints((short) 10);
		separateLineFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		separateLineFont.setCharSet(Font.DEFAULT_CHARSET);
		separateLineFont.setColor(IndexedColors.BLACK.index);
	}

	/**
	 * @Description: 初始化表头行字体
	 */
	private static void initHeadFont() {
		headFont.setFontName("宋体");
		headFont.setFontHeightInPoints((short) 10);
		headFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		headFont.setCharSet(Font.DEFAULT_CHARSET);
		headFont.setColor(IndexedColors.BLACK.index);
	}

	/**
	 * @Description: 初始化内容行字体
	 */
	private static void initContentFont() {
		contentFont.setFontName("宋体");
		contentFont.setFontHeightInPoints((short) 10);
		contentFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		contentFont.setCharSet(Font.DEFAULT_CHARSET);
		contentFont.setColor(IndexedColors.BLACK.index);
	}

	/**
	 * 对double数据进行取精度.
	 * 
	 * @param value
	 *            double数据.
	 * @param scale
	 *            精度位数(保留的小数位数).
	 * @param roundingMode
	 *            精度取值方式.
	 * @return 精度计算后的数据.
	 */
	public static String round(Double value, int scale, int roundingMode) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(scale, roundingMode);
		return bd.toString();
	}
	
	

	/**
	 * Excel导出数据类
	 * 
	 * @author jimmy
	 *
	 */
	public static class ExcelExportData {

		/**
		 * 导出数据 key:String 表示每个Sheet的名称 value:List<?> 表示每个Sheet里的所有数据行
		 */
		private LinkedHashMap<String, List<?>> dataMap;

		/**
		 * 每个Sheet里的顶部大标题
		 */
		private String[] titles;

		/**
		 * 单个sheet里的数据列标题
		 */
		private List<String[]> columnNames;

		/**
		 * 单个sheet里每行数据的列对应的对象属性名称
		 */
		private List<String[]> fieldNames;

		/**
		 * 独立行
		 */
		private String[] separateLines;

		public List<String[]> getFieldNames() {
			return fieldNames;
		}

		public void setFieldNames(List<String[]> fieldNames) {
			this.fieldNames = fieldNames;
		}

		public String[] getTitles() {
			return titles;
		}

		public void setTitles(String[] titles) {
			this.titles = titles;
		}

		public List<String[]> getColumnNames() {
			return columnNames;
		}

		public void setColumnNames(List<String[]> columnNames) {
			this.columnNames = columnNames;
		}

		public LinkedHashMap<String, List<?>> getDataMap() {
			return dataMap;
		}

		public void setDataMap(LinkedHashMap<String, List<?>> dataMap) {
			this.dataMap = dataMap;
		}

		public String[] getSeparateLines() {
			return separateLines;
		}

		public void setSeparateLines(String[] separateLines) {
			this.separateLines = separateLines;
		}

	}
}