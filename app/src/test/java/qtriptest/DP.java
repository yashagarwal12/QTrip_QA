package qtriptest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class DP {
    // TODO: use correct annotation to connect the Data Provider with your Test Cases
    
    public Object[][] dpMethod(String sheet) throws IOException{
        int rowIndex = 0;
        int cellIndex = 0;
        List<List> outputList = new ArrayList<List>();

        FileInputStream excelFile = new FileInputStream(new File(
                "/home/crio-user/workspace/yashagarwal12081999-ME_QTRIP_QA_V2/app/src/test/resources/DatasetsforQTrip.xlsx"));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet selectedSheet = workbook.getSheet(sheet);
        Iterator<Row> iterator = selectedSheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            List<String> innerList = new ArrayList<String>();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if(rowIndex > 0 && cellIndex > 0) {
                    if (cell.getCellType() == CellType.STRING) {
                        innerList.add(cell.getStringCellValue());
                    } else if (cell.getCellType() == CellType.NUMERIC) {
                        innerList.add(String.valueOf(cell.getNumericCellValue()));
                    }
                }
                cellIndex = cellIndex + 1;
            }
            rowIndex = rowIndex + 1;
            cellIndex = 0;
            if (innerList.size() > 0)
                outputList.add(innerList);

        }

        excelFile.close();

        String[][] stringArray = outputList.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        return stringArray;

    }

    @DataProvider(name="qtripData")
    public Object[][] TestCase01() throws IOException{
         return dpMethod("TestCase01");
    }
}
