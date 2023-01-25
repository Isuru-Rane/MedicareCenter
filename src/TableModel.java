import javax.print.Doc;
import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;

public class TableModel extends AbstractTableModel {

    private String[] columNames= {"First Name","Last Name","Licence ID","Specialisation"};
    private LinkedList<Doctor> personLinkedList;


    public TableModel(LinkedList<Doctor> personLinkedList){
        this.personLinkedList = new LinkedList<>(personLinkedList);
    }

    @Override
    public int getRowCount() {
        return personLinkedList.size();
    }

    @Override
    public int getColumnCount() {
        return columNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;
        if(columnIndex == 0){
            temp = personLinkedList.get(rowIndex).getFirstName();
        } else if (columnIndex == 1 ) {
            temp = personLinkedList.get(rowIndex).getLastName();
        } else if (columnIndex == 2) {
            temp= personLinkedList.get(rowIndex).getLicenceId();
        } else if (columnIndex == 3) {
            temp= personLinkedList.get(rowIndex).getSpecialisation();
        }
        return temp;
    }

    @Override
    public String getColumnName(int column) {
        return columNames[column];
    }

    //    public String getColumName(int col){
//        return columNames[col];
//    }
}
