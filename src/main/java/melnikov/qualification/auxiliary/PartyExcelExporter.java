package melnikov.qualification.auxiliary;

import melnikov.qualification.entity.Party;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

import static org.apache.poi.ss.util.CellUtil.createCell;

public class PartyExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Party> partyList;


    public PartyExcelExporter(List<Party> partyList) {
        this.partyList = partyList;
        workbook = new XSSFWorkbook();
    }
    private void writeHeaderLine(){
        sheet= workbook.createSheet("parties");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Master", style);
        createCell(row,1,"players", style);




    }
}
