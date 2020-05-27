package todo;
import javax.swing.table.*;
import java.sql.*;
import java.util.*;

public class ResultSetTableModel extends DefaultTableModel{
    
    public ResultSetTableModel(ResultSet rs) throws Exception{
        super();
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        Vector columnNames = new Vector(colCount);
        for(int i=1; i<=colCount; i++){
            columnNames.addElement(rsmd.getColumnName(i));
        }
        Vector tableData = new Vector();
        Vector rowData;
        while(rs.next()){
            rowData = new Vector(colCount);
            for(int i=1; i<=colCount; i++){
                rowData.addElement(rs.getString(i));
            }
            tableData.addElement(rowData);
        }
        setDataVector(tableData, columnNames);
    }
}
