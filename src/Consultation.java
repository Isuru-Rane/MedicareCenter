import java.time.LocalDate;
import java.time.LocalTime;

public class Consultation {

    private Patient patient;
    private Doctor firstDoctor;
    private Doctor secondDoctor;
    private LocalDate appointmentDate;
    private String note;
    private double cost;
    private LocalTime startTime;
    private LocalTime endTime;

    public Consultation() {
    }

    public Consultation(Patient patient, Doctor firstDoctor, Doctor secondDoctor, LocalDate appointmentDate, String note, double cost, LocalTime startTime, LocalTime endTime) {
        this.patient = patient;
        this.firstDoctor = firstDoctor;
        this.secondDoctor = secondDoctor;
        this.appointmentDate = appointmentDate;
        this.note = note;
        this.cost = cost;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getFirstDoctor() {
        return firstDoctor;
    }

    public void setFirstDoctor(Doctor firstDoctor) {
        this.firstDoctor = firstDoctor;
    }

    public Doctor getSecondDoctor() {
        return secondDoctor;
    }

    public void setSecondDoctor(Doctor secondDoctor) {
        this.secondDoctor = secondDoctor;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return cost+" "+firstDoctor.getFirstName()+" "+secondDoctor.getFirstName()+" "+startTime.toString()+" "+endTime.toString()+" "+appointmentDate.toString();
    }
}
