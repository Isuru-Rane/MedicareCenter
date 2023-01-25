import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {

    public static LinkedList<Person> personLinkedList;
    public static LinkedList<Consultation> appointmentList;

    public WestminsterSkinConsultationManager() {
        personLinkedList=new LinkedList<>();
        appointmentList=new LinkedList<>();
        try {
            printMenu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printMenu() throws IOException {
        System.out.println("Westminster Skin Consultation Center");
        loadDoctors();
        boolean bool = true;
        while(bool){
            Scanner input = new Scanner(System.in);
            System.out.print("""
                    1 : Add the Doctor
                    2 : Delete the Doctor
                    3 : Print the Doctor
                    4 : Save the File
                    5 : Run GUI
                    
                    Select :""");
            String menu = input.next();
            switch (menu){
                case "1":
                    addDoctor(input);
                    break;
                case "2":
                    deleteDoctor(input);
                    break;
                case "3":
                    printList();
                    break;
                case "4":
                    saveList();
                    break;
                case "5":
                    System.out.println("Exit");
                    bool= false;
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        }
    }

    /** ----------------- Adding Doctor -------------------**/
    public static void addDoctor(Scanner input) {
        boolean bool =true;
        while (bool) {
            try{
                System.out.print("Enter Doctor First Name :");
                String fName = input.next().toUpperCase();

                System.out.print("Enter Doctor Last Name :");
                String lName = input.next().toUpperCase();

                System.out.print("Enter Doctor Date of Birth[YYYY-MM-DD] :");
                String date=input.next();
                LocalDate dob = LocalDate.parse(date,DateTimeFormatter.ISO_DATE);

                System.out.print("Enter Doctor Telephone Number :");

/**------------- If contact numbers not equals 9 its invalid number ------------------**/
                int telNo = input.nextInt();
                if(String.valueOf(telNo).toCharArray().length!=9){
                    System.out.println("Invalid Contact Number");
                    continue;
                }

                System.out.print("Enter Doctor Licence ID :");
                int licenceId = input.nextInt();

                System.out.print("Enter Doctor Specialization :");
                String spec = input.next().toLowerCase();

                Doctor doctor=new Doctor(fName,lName,dob,telNo,"DOCTOR",licenceId,spec);


/** ---------------------can't add the doctor before you  added---------------------------  **/
                if(isDoctorExists(doctor)){
                    System.out.println("\nThis doctor is already exists.\n");

                }else{
                    if(personLinkedList.size()==10){
                        System.out.println("You have reached maximum doctor count ");
                        return;
                    }
                    personLinkedList.add(doctor);
                }

                boolean replyBool=true;
                while(replyBool){
                    System.out.print("Do you want to add another doctor(y/n): ");

                    String reply=input.next().toLowerCase();
                    if(reply.equals("y")){
                        replyBool=false;
                    }else if(reply.equals("n")){
                        replyBool=false;
                        bool=false;
                    } else{
                        System.out.println("Invalid Reply input\n");
                    }
                }

            }catch(Exception e){
                System.out.println("Invalid input, Please try again\n");
                continue;
            }
        }
    }


/**------------  Check the same doctor Licence ID ----------------------- **/
    public static boolean isDoctorExists(Doctor doctor){

        for(Person person:personLinkedList){
            if(person.getRole().equals("DOCTOR")){
                Doctor doc = (Doctor) person;
                if(doc.getLicenceId()== doctor.getLicenceId()){
                    return true;
                }
            }
        }
        return false;
    }

/**--------------------- Delete Doctor ---------------------- **/
    public static void deleteDoctor(Scanner input) {
        boolean bool=true;

        while(bool){
            try{
                System.out.print("Enter doctor's licence number: ");
                int id=Integer.parseInt(input.next());

                Doctor specificDoctor = getSpecificDoctor(id);

                if(specificDoctor!=null){
                    personLinkedList.remove(specificDoctor);
                    System.out.println(" ");
                    System.out.println("Dr."+specificDoctor +"  successfully removed");
                    System.out.println(" ");
                }else{
                    System.out.println("There is no doctor with this licence.\n");
                }

                boolean replyBool=true;
                while(replyBool){
                    System.out.print("Do you want to delete another doctor(y/n): ");
                    String reply=input.next().toLowerCase();
                    if(reply.equals("y")){
                        replyBool=false;
                    }else if(reply.equals("n")){
                        replyBool=false;
                        bool=false;
                    } else{
                        System.out.println("Invalid Reply input\n");
                    }
                }
            }catch (Exception e){
                System.out.println("Invalid input.");
                continue;
            }
        }
    }

/**----------Delete the Specific Doctor -------------- **/
    public static Doctor getSpecificDoctor(int id){

        for(Person person:personLinkedList){
            if(person.getRole().equals("DOCTOR")){
                Doctor doc = (Doctor) person;
                if(doc.getLicenceId()==id){
                    return doc;
                }
            }
        }
        return null;
    }


/** ----------------- Print the Doctor List --------------------------**/
    public static void printList() {

        personLinkedList.sort(Comparator.comparing(Person::getLastName));
        for(Person person:personLinkedList){
            if(person.getRole().equalsIgnoreCase("DOCTOR")){
                Doctor doc = (Doctor) person;
                System.out.println(doc.printDetails()+"\n");
            }
        }
    }


/**-----------------------Save the Doctor details to File--------------------- **/
    public static void saveList() throws IOException {
        FileWriter fileWriter = new FileWriter("doctors.txt");
        for (int i = 0; i < personLinkedList.size(); i++) {
            if(personLinkedList.get(i).getRole().equalsIgnoreCase("doctor")){
                Doctor doctor = (Doctor) personLinkedList.get(i);
                fileWriter.write(doctor.getFirstName()+","+doctor.getLastName()+","+doctor.getLicenceId()+","+doctor.getSpecialisation()+","+doctor.getTel_no()+","+doctor.getDate());
            }
            fileWriter.write("\n");
        }
        fileWriter.close();
        System.out.println("Data stored successfully");
    }

/**-----------------------Load the Doctor From File--------------------- **/
    public static void loadDoctors() throws FileNotFoundException {
        File file = new File("doctors.txt");
        Scanner data=new Scanner(file);
        while (data.hasNextLine()){
            String[] split = data.nextLine().split(",");
            Doctor doctor=new Doctor(split[0],split[1],LocalDate.parse(split[5],DateTimeFormatter.ISO_DATE),Integer.parseInt(split[4]),"DOCTOR",Integer.parseInt(split[2]),split[3]);
            boolean doctorExists = isDoctorExists(doctor);
            if(doctorExists){
                continue;
            }else{
                personLinkedList.add(doctor);
            }

        }
    }


/**-------------- Add Patient to LinkList -------------------**/
    @Override
    public boolean addPatient(Patient patient) {
        if(isPatientExists(patient.getPatient_id())==null){
            personLinkedList.add(patient);
            return true;
        }else{
            return false;
        }

    }

    public Patient loadPatient(String id){
        return isPatientExists(id);
    }


/**------------- Check the Patient Already Exists----------**/
    private Patient isPatientExists(String id){
        for(Person person:personLinkedList){
            if(person.getRole().equalsIgnoreCase("patient")){
                Patient patient = (Patient) person;
                if(patient.getPatient_id().equalsIgnoreCase(id)){
                    return patient;
                }
            }
        }

        return null;
    }

/**------------- Get Doctors From LinkList----------------**/
    public LinkedList<Person> getAllDoctors(){
        LinkedList<Person> doctors=new LinkedList<>();
        personLinkedList.sort(Comparator.comparing(Person::getLastName));
        for(Person person:personLinkedList){
            if(person.getRole().equalsIgnoreCase("DOCTOR")){
                doctors.add(person);
            }
        }
        return doctors;
    }

/**---------------Book the Consultation-----------------**/
    public Consultation addConsultation(Patient patient, Doctor doctor,LocalDate appointmentDate,LocalTime startTime,LocalTime endTime,String note,double cost){
        try{
            Consultation consultationSaved=null;
            Doctor availableDoctor=null;
            Doctor doctorAvailable = isDoctorAvailable(doctor, appointmentDate, startTime, endTime);
            if(doctorAvailable==null){

                LinkedList<Doctor> secondaryDoctorList=new LinkedList<>();
                for(Person person:personLinkedList){
                    if(person.getRole().equalsIgnoreCase("doctor")){
                        Doctor doc = (Doctor) person;
                        if(doc.getSpecialisation().equalsIgnoreCase(doctor.getSpecialisation())){
                            secondaryDoctorList.add(doc);
                        }
                    }
                }



                for(Doctor secDoc:secondaryDoctorList){
                    Doctor doctorAvailable1 = isDoctorAvailable(secDoc, appointmentDate, startTime, endTime);
                    if(doctorAvailable1!=null){
                        availableDoctor=doctorAvailable1;
                        break;
                    }
                }

                if(availableDoctor==null){
                    return null;
                }

                Consultation consultation=new Consultation(patient,doctor,availableDoctor,appointmentDate,note,cost,startTime,endTime);
                appointmentList.add(consultation);

                consultationSaved=consultation;

            }else{
                Consultation consultation=new Consultation(patient,doctor,doctor,appointmentDate,note,cost,startTime,endTime);
                appointmentList.add(consultation);

                consultationSaved=consultation;
            }

            return consultationSaved;
        }catch (Exception e){

            return null;
        }
    }


/**-----------Check the Available Doctor For Consultation --------------------**/
    private Doctor isDoctorAvailable(Doctor doctor,LocalDate appointmentDate,LocalTime startTime,LocalTime endTime){
        for(Consultation consultation:appointmentList){

            /**--------Check the Time and Date for specific Doctor----- ------**/

            if((consultation.getFirstDoctor().getLicenceId()==doctor.getLicenceId() || consultation.getSecondDoctor().getLicenceId()== doctor.getLicenceId()) && consultation.getAppointmentDate().isEqual(appointmentDate) &&
                    (consultation.getStartTime().equals(startTime)||(consultation.getStartTime().isBefore(startTime)&&consultation.getEndTime().isAfter(startTime)))){
                return null;
            }
        }

        return doctor;
    }

/**-----------Get the Appointment Details--------------------**/
    public LinkedList<Consultation> getAllConsultations(){
        return appointmentList;
    }


}
