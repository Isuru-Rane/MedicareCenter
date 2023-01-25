import java.time.LocalDate;

public class Doctor extends Person {
    private int licenceId;
    private String specialisation;


    public Doctor() {
    }

    public Doctor(String firstName, String lastName, LocalDate date , int tel_no,String role, int licenceId , String specialisation){
        super(firstName ,lastName, date , tel_no,role);
        this.licenceId=licenceId;
        this.specialisation=specialisation;
    }


    public int getLicenceId() {
        return licenceId;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public String printDetails(){
        String data="Name : "+super.getFirstName()+" "+super.getLastName()+" - "+getLicenceId()+"\n"+"Telephone No: "+super.getTel_no()+"\n"+
                "Date Of Birth: "+super.getDate()+"\n"+"Specialisation: "+getSpecialisation();
        return data;
    }

    @Override
    public String toString() {
        return super.getFirstName()+" "+super.getLastName();
    }
}
