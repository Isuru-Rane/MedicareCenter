import java.time.LocalDate;

public class Person {
    private String firstName;
    private String lastName;
    private LocalDate date;
    private int tel_no;

    private String role;

    public Person() {
    }

    public Person(String firstName, String lastName, LocalDate date, int tel_no, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
        this.tel_no = tel_no;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTel_no() {
        return tel_no;
    }

    public void setTel_no(int tel_no) {
        this.tel_no = tel_no;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
