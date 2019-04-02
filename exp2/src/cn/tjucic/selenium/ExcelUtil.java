package cn.tjucic.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	private int totalRows = 0;
    /** ������ */
    private int totalCells = 0;
    /** ������Ϣ */
    private String errorInfo;
    /** ���췽�� 
     * @return */
    public ExcelUtil() {
    }


    public int getTotalRows() {
        return totalRows;
    }
    public int getTotalCells() {
        return totalCells;
    }
    public String getErrorInfo() {
        return errorInfo;
    }

    public boolean validateExcel(String filePath) {
        /** ����ļ����Ƿ�Ϊ�ջ����Ƿ���Excel��ʽ���ļ� */
        if (filePath == null
                || !(WDWUtil.isExcel2003(filePath) || WDWUtil
                        .isExcel2007(filePath))) {
            errorInfo = "�ļ�������excel��ʽ";
            return false;
        }
        /** ����ļ��Ƿ���� */
        File file = new File(filePath);
        if (file == null || !file.exists()) {
        	System.out.println(filePath);
            errorInfo = "�ļ�������";
            return false;
        }
        return true;
    }

    public List<List<String>> read(String filePath) {
        List<List<String>> dataLst = new ArrayList<List<String>>();
        InputStream is = null;
        try {
            /** ��֤�ļ��Ƿ�Ϸ� */
            if (!validateExcel(filePath)) {
                System.out.println(errorInfo);
                return null;
            }
            /** �ж��ļ������ͣ���2003����2007 */
            boolean isExcel2003 = true;
            if (WDWUtil.isExcel2007(filePath)) {
                isExcel2003 = false;
            }
            /** ���ñ����ṩ�ĸ�������ȡ�ķ��� */
            File file = new File(filePath);
            is = new FileInputStream(file);
            dataLst = read(is, isExcel2003);
            is.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    is = null;
                    e.printStackTrace();
                }
            }
        }
        /** ��������ȡ�Ľ�� */
        return dataLst;
    }


    public List<List<String>> read(InputStream inputStream, boolean isExcel2003) {
        List<List<String>> dataLst = null;
        try {
            /** ���ݰ汾ѡ�񴴽�Workbook�ķ�ʽ */
            Workbook wb = null;
            if (isExcel2003) {
                wb = new HSSFWorkbook(inputStream);
            } else {
                wb = new XSSFWorkbook(inputStream);
            }
            dataLst = read(wb);
        } catch (IOException e) {
        
            e.printStackTrace();
        }
        return dataLst;
    }
    

    private List<List<String>> read(Workbook wb) {
        List<List<String>> dataLst = new ArrayList<List<String>>();
        /** �õ���һ��shell */
        Sheet sheet = wb.getSheetAt(0);
        /** �õ�Excel������ */
        this.totalRows = sheet.getPhysicalNumberOfRows();
        /** �õ�Excel������ */
        this.totalCells = 0;
        for(int i=0;i<this.totalRows;i++) {
        	if(this.totalCells<sheet.getRow(i).getPhysicalNumberOfCells()) {
        		this.totalCells = sheet.getRow(1).getPhysicalNumberOfCells();
        	}
        }
        /*if (this.totalRows >= 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(1).getPhysicalNumberOfCells();
            System.out.println(this.totalCells);
        }*/
        /** ѭ��Excel���� */
        for (int r = 0; r < this.totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            List<String> rowLst = new ArrayList<String>();
            /** ѭ��Excel���� */
            for (int c = 0; c < this.getTotalCells(); c++) {
                Cell cell = row.getCell(c);
                String cellValue = "";
                if (null != cell) {
                    // �������ж����ݵ�����
                    switch (cell.getCellType()) {
                    case HSSFCell.CELL_TYPE_NUMERIC: // ����
                    	DecimalFormat df = new DecimalFormat("#"); 
                        cellValue = df.format(cell.getNumericCellValue())+"";
                        break;
                    case HSSFCell.CELL_TYPE_STRING: // �ַ���
                        cellValue = cell.getStringCellValue();
                        break;
                    case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                        cellValue = cell.getBooleanCellValue() + "";
                        break;
                    case HSSFCell.CELL_TYPE_FORMULA: // ��ʽ
                        cellValue = cell.getCellFormula() + "";
                        break;
                    case HSSFCell.CELL_TYPE_BLANK: // ��ֵ
                        cellValue = "";
                        break;
                    case HSSFCell.CELL_TYPE_ERROR: // ����
                        cellValue = "�Ƿ��ַ�";
                        break;
                    default:
                        cellValue = "δ֪����";
                        break;
                    }
                }
                rowLst.add(cellValue);
            }
            /** �����r�еĵ�c�� */
            dataLst.add(rowLst);
        }
        return dataLst;
    }
    public static List<List<String>> getDataFromFile(String path) {
    	ExcelUtil poi = new ExcelUtil();
        List<List<String>> list = poi.read(path);
    	return list;
    }
    public static void main(String[] args) throws Exception {
        ExcelUtil poi = new ExcelUtil();
        // List<List<String>> list = poi.read("d:/aaa.xls");
        String path = System.getProperty("user.dir") + "/src/testFile/test.xlsx";
        List<List<String>> list = poi.read(path);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                System.out.print("��" + (i) + "��");
                List<String> cellList = list.get(i);
                for (int j = 0; j < cellList.size(); j++) {
                    // System.out.print("    ��" + (j + 1) + "��ֵ��");
                    System.out.print("  "+cellList.get(j));
                }
                System.out.println();
            }

        }

    }

}

class WDWUtil {
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}