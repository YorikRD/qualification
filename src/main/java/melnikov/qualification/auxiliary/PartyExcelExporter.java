package melnikov.qualification.auxiliary;

import melnikov.qualification.entity.Party;
import melnikov.qualification.exception.JoinedQualificationExeption;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PartyExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Party> partyList;


    public PartyExcelExporter(List<Party> partyList) {
        this.partyList = partyList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("parties");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Master", style);
        createCell(row, 1, "title", style);
        createCell(row, 2, "description", style);
        createCell(row, 3, "players", style);
        createCell(row, 4, "meetings", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Party party : partyList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, party.getMaster().toString(), style);
            createCell(row, columnCount++, party.getTitle(), style);
            createCell(row, columnCount++, party.getDescription(), style);
            createCell(row, columnCount++, party.getPlayers().toString(), style);
            createCell(row, columnCount++, party.getMeetings().toString(), style);
        }
    }

    public String export (String fileName){
        writeHeaderLine();
        writeDataLines();
        String fileRoute = "src/main/java/melnikov/qualification/auxiliary/savedtables/"+fileName+".xlsx";
        try(FileOutputStream outputStream = new FileOutputStream(fileRoute)) {
            workbook.write(outputStream);
        } catch (IOException e) {
          throw  new JoinedQualificationExeption(e.getMessage());
        }

        return fileRoute;
    }
}
