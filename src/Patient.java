import java.time.LocalDate;

public class Patient extends Person {
    private String patient_id;

    public Patient() {

    }

    public Patient(String firstName, String lastName, LocalDate date, int tel_no, String role, String patient_id) {
        super(firstName, lastName, date, tel_no, role);
        this.patient_id = patient_id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;

    }

    @Override
    public String toString() {
        return super.getFirstName()+" "+super.getLastName();
    }
}
