import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;

public class ConsultationTableModel extends AbstractTableModel {
    private String[] columNames= {"Doctor Name","Patient Name","Cost","Start Time"};
    private LinkedList<Consultation> personLinkedList;


    public ConsultationTableModel(LinkedList<Consultation> personLinkedList){
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
            if(personLinkedList.get(rowIndex).getFirstDoctor().getLicenceId()==personLinkedList.get(rowIndex).getSecondDoctor().getLicenceId()){
                temp = personLinkedList.get(rowIndex).getFirstDoctor().getFirstName();
            }else{
                temp = personLinkedList.get(rowIndex).getSecondDoctor().getFirstName();
            }
        } else if (columnIndex == 1 ) {
            temp = personLinkedList.get(rowIndex).getPatient();
        } else if (columnIndex == 2) {
            temp= personLinkedList.get(rowIndex).getCost();
        } else if (columnIndex == 3) {
            temp= personLinkedList.get(rowIndex).getStartTime();
        }
        return temp;
    }

    @Override
    public String getColumnName(int column) {
        return columNames[column];
    }
}


