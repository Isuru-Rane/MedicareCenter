import com.github.lgooddatepicker.components.TimePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.Properties;

class Interface extends JFrame {
    JFrame main_Frame = new JFrame();
    JPanel doctor_panel = new JPanel();
    JPanel patient_panel = new JPanel();
    JPanel consultation_panel = new JPanel();
    Border thickBorder = new LineBorder(Color.BLACK, 1);

    WestminsterSkinConsultationManager westminsterSkinConsultationManager;

    Patient currentPatient=new Patient();

    Patient newlyAddedPatient=new Patient();

    Patient existsPatient=new Patient();

    Consultation consultation=null;

    LinkedList<Doctor> doctorLinkedList=new LinkedList<>();

    Interface(WestminsterSkinConsultationManager manager){

        westminsterSkinConsultationManager=manager;

        main_Frame.setLayout(null);
        main_Frame.setTitle("Westminster Skin Consultation Manager");
        main_Frame.setSize(900,800);
        main_Frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        main_Frame.setLocationRelativeTo(null);
        main_Frame.setVisible(true);

        doctor_panel.setSize(900,800);
        doctor_panel.setBackground(Color.WHITE);
        doctor_panel.setLayout(null);
        doctor_panel.setVisible(false);

        patient_panel.setSize(900,800);
        patient_panel.setBackground(Color.WHITE);
        patient_panel.setLayout(null);
        patient_panel.setVisible(false);

        consultation_panel.setSize(900,800);
        consultation_panel.setBackground(Color.WHITE);
        consultation_panel.setLayout(null);
        consultation_panel.setVisible(false);


        doctor_panel.removeAll();
        setDoctorsToTable();


        JButton addConsultation = new JButton("Add Consultation");
        JButton viewConsultation = new JButton("View Consultation");

        JLabel dt_tbl_hdr = new JLabel("List Of Doctors");
        dt_tbl_hdr.setVisible(true);

        JTable docTable=new JTable();
        docTable.setShowVerticalLines(true);
        TableModel model = new TableModel(doctorLinkedList);

        docTable.setModel(model);
        docTable.setBorder(thickBorder);

        JScrollPane scrollPane = new JScrollPane(docTable);
        scrollPane.setSize(600,300);
        scrollPane.setLocation(150,80);
        scrollPane.setVisible(true);

        doctor_panel.add(scrollPane);

        dt_tbl_hdr.setFont(new Font("",1,35));
        dt_tbl_hdr.setSize(300,55);
        dt_tbl_hdr.setLocation(300,10);

        viewConsultation.setFont(new Font("",2,16));
        viewConsultation.setSize(200,55);
        viewConsultation.setLocation(60,600);
        viewConsultation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultation_panel.removeAll();
                doctor_panel.setVisible(false);
                consultation_panel.setVisible(true);

                JLabel consultHeader = new JLabel("Consultation Details");
                JButton consultBack = new JButton("Back");

                consultHeader.setFont(new Font("",1,30));
                consultHeader.setSize(500,55);
                consultHeader.setLocation(300,10);

                JTable consultationTable=new JTable();
                consultationTable.setShowVerticalLines(true);

                LinkedList<Consultation> allConsultations = westminsterSkinConsultationManager.getAllConsultations();
                ConsultationTableModel model = new ConsultationTableModel(allConsultations);

                consultationTable.setModel(model);
                consultationTable.setBorder(thickBorder);

                JScrollPane scrollPaneConsult = new JScrollPane(consultationTable);
                scrollPaneConsult.setSize(600,500);
                scrollPaneConsult.setLocation(150,80);
                scrollPaneConsult.setVisible(true);

                consultBack.setFont(new Font("",2,16));
                consultBack.setSize(100,40);
                consultBack.setLocation(50,700);
                consultBack.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        consultation_panel.setVisible(false);
                        doctor_panel.setVisible(true);
                    }
                });

                consultation_panel.add(consultHeader);
                consultation_panel.add(consultBack);
                consultation_panel.add(scrollPaneConsult);

            }
        });


        addConsultation.setFont(new Font("",2,16));
        addConsultation.setSize(200,55);
        addConsultation.setLocation(650,600);
        addConsultation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doctor_panel.setVisible(false);
                patient_panel.setVisible(true);

                newlyAddedPatient=null;
                existsPatient=null;
                JButton ap_book = new JButton("BOOK");
                JButton ap_back = new JButton("BACK");
                JButton save = new JButton("Save");
                JButton load = new JButton("Load");

                JLabel patient = new JLabel("Add Patient");
                JLabel ptFirstName = new JLabel("First Name ");
                JLabel ptLastName = new JLabel("Last Name ");
                JLabel ptDOB = new JLabel("Date Of Birth ");
                JLabel ptTelNo = new JLabel("Mobile Number ");
                JLabel ptID = new JLabel("Patient ID");
                JLabel ptNote = new JLabel("Note");
                JLabel dtID = new JLabel("Doctor ID");
                JLabel date = new JLabel("Date");
                JLabel inTime = new JLabel("In Time");
                JLabel outTime = new JLabel("Out Time");


                JTextArea ptFNameTxt = new JTextArea();
                JTextArea ptLNameTxt = new JTextArea();
                JTextArea ptDOBTxt = new JTextArea();
                JTextArea ptTelNoTxt = new JTextArea();
                JTextArea ptIDTxt = new JTextArea();
                JTextArea ptNoteTxt = new JTextArea();
                JComboBox<Person> dctName = new JComboBox<>();
                JTextArea dateTxt = new JTextArea();

                LinkedList<Person> allDoctors = westminsterSkinConsultationManager.getAllDoctors();
                for(Person doctor:allDoctors){
                    dctName.addItem(doctor);
                }

                patient.setFont(new Font("",1,35));
                patient.setSize(700,100);
                patient.setLocation(350,10);

                ptFirstName.setFont(new Font("",1,16));
                ptFirstName.setSize(150,55);
                ptFirstName.setLocation(80,120);


                ptFNameTxt.setFont(new Font("",2,16));
                ptFNameTxt.setBorder(thickBorder);
                ptFNameTxt.setSize(200,35);
                ptFNameTxt.setLocation(240,130);

                ptLastName.setFont(new Font("",1,16));
                ptLastName.setSize(150,55);
                ptLastName.setLocation(500,120);

                ptLNameTxt.setFont(new Font("",2,16));
                ptLNameTxt.setBorder(thickBorder);
                ptLNameTxt.setSize(200,35);
                ptLNameTxt.setLocation(630,130);

                ptDOB.setFont(new Font("",1,16));
                ptDOB.setSize(200,55);
                ptDOB.setLocation(80,180);

                ptDOBTxt.setFont(new Font("",2,16));
                ptDOBTxt.setBorder(thickBorder);
                ptDOBTxt.setSize(200,35);
                ptDOBTxt.setLocation(240,190);

                ptTelNo.setFont(new Font("",1,16));
                ptTelNo.setSize(200,55);
                ptTelNo.setLocation(500,180);

                ptTelNoTxt.setFont(new Font("",2,16));
                ptTelNoTxt.setBorder(thickBorder);
                ptTelNoTxt.setSize(200,35);
                ptTelNoTxt.setLocation(630,190);

                ptID.setFont(new Font("",1,16));
                ptID.setSize(200,55);
                ptID.setLocation(80,240);

                ptIDTxt.setFont(new Font("",2,16));
                ptIDTxt.setBorder(thickBorder);
                ptIDTxt.setSize(200,35);
                ptIDTxt.setLocation(240,250);

                save.setFont(new Font("",2,16));
                save.setSize(100,40);
                save.setLocation(500,250);
                save.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        try{
                            Patient newPatient=new Patient(ptFNameTxt.getText(),ptLNameTxt.getText(), LocalDate.parse(ptDOBTxt.getText(), DateTimeFormatter.ISO_DATE),Integer.parseInt(ptTelNoTxt.getText()),"PATIENT",ptIDTxt.getText());
                            if(westminsterSkinConsultationManager.addPatient(newPatient)){
                                newlyAddedPatient=newPatient;
                                currentPatient=newPatient;
                                JOptionPane.showMessageDialog(null,"Patient added !");
                            }
                        }catch (Exception ex){
                            JOptionPane.showMessageDialog(null,"Invalid input !");
                        }
                    }
                });

                load.setFont(new Font("",2,16));
                load.setSize(100,40);
                load.setLocation(650,250);
                load.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Patient patient = westminsterSkinConsultationManager.loadPatient(ptIDTxt.getText());
                        if(patient!=null){
                            existsPatient=patient;
                            currentPatient=patient;
                            ptFNameTxt.setText(patient.getFirstName());
                            ptLNameTxt.setText(patient.getLastName());
                            ptDOBTxt.setText(String.valueOf(patient.getDate()));
                            ptTelNoTxt.setText(String.valueOf(patient.getTel_no()));
                            ptIDTxt.setText(patient.getPatient_id());

                        }else {
                            JOptionPane.showMessageDialog(null,"No patient found !","Not Found !",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });


                dtID.setFont(new Font("",1,16));
                dtID.setSize(200,55);
                dtID.setLocation(80,320);

                dctName.setSize(200,35);
                dctName.setLocation(240,330);

                ptNote.setFont(new Font("",1,16));
                ptNote.setSize(200,55);
                ptNote.setLocation(80,380);

                ptNoteTxt.setFont(new Font("",2,16));
                ptNoteTxt.setBorder(thickBorder);
                ptNoteTxt.setSize(400,100);
                ptNoteTxt.setLocation(240,390);

                date.setFont(new Font("",1,16));
                date.setSize(200,55);
                date.setLocation(220,500);

                UtilDateModel dateModel=new UtilDateModel();
                dateModel.setDate(2022,12,28);
                Properties p = new Properties();
                p.put("text.today","Today");
                p.put("text.month","Month");
                p.put("text.year","Year");
                JDatePanelImpl jDatePanel=new JDatePanelImpl(dateModel,p);
                JDatePickerImpl datePicker=new JDatePickerImpl(jDatePanel,new DateLabelFormatter());
                datePicker.setSize(200,30);
                datePicker.setLocation(150,550);

                inTime.setFont(new Font("",1,16));
                inTime.setSize(200,55);
                inTime.setLocation(430,500);

                TimePicker inTimePicker=new TimePicker();
                inTimePicker.setSize(200,30);
                inTimePicker.setLocation(360,550);

                outTime.setFont(new Font("",1,16));
                outTime.setSize(200,55);
                outTime.setLocation(640,500);

                TimePicker outTimePicker=new TimePicker();
                outTimePicker.setSize(200,30);
                outTimePicker.setLocation(570,550);

                ap_back.setFont(new Font("",2,16));
                ap_back.setSize(100,40);
                ap_back.setLocation(40,720);
                ap_back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        patient_panel.setVisible(false);
                        doctor_panel.setVisible(true);
                    }
                });

                ap_book.setFont(new Font("",2,16));
                ap_book.setSize(100,40);
                ap_book.setLocation(780,720);
                ap_book.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        Doctor selectedItem = (Doctor) dctName.getSelectedItem();
                        if(inTimePicker.getText().equals("") || outTimePicker.getText().equals("") ||
                                selectedItem==null || datePicker.getModel().getValue()==null || currentPatient==null ||
                                Duration.between(inTimePicker.getTime(), outTimePicker.getTime()).isNegative()){

                            JOptionPane.showMessageDialog(null,"Enough Data");
                        }
                        else{
                            Date value = (Date) datePicker.getModel().getValue();
                            LocalDate localDate = value.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                            double cost=0;
                            long seconds = Duration.between(inTimePicker.getTime(), outTimePicker.getTime()).getSeconds();
                            int hours=0;

                            if(seconds%3600==0){
                                hours=(int)seconds/3600;
                            }else{
                                hours=(int)seconds/3600;
                                hours++;
                            }
                            if(newlyAddedPatient!=null){
                                cost=15*hours;
                            }else if(existsPatient!=null){
                                cost=25*hours;
                            }

                            consultation= westminsterSkinConsultationManager.addConsultation(currentPatient, selectedItem,
                                    localDate, inTimePicker.getTime(), outTimePicker.getTime(), ptNoteTxt.getText(),cost);
                            if(consultation==null){

                                JOptionPane.showMessageDialog(null,"No Doctors Available !");

                            }else{

                                Doctor firstDoctor1 = consultation.getFirstDoctor();
                                Doctor secondDoctor1 = consultation.getSecondDoctor();

                                if(firstDoctor1.getLicenceId()== secondDoctor1.getLicenceId()){

                                    JOptionPane.showMessageDialog(null,"Dr. "+firstDoctor1.getFirstName()+" "+firstDoctor1.getLastName()+" has been booked");

                                }else{


                                    JOptionPane.showMessageDialog(null,"Dr. "+firstDoctor1.getFirstName()+" "+firstDoctor1.getLastName()+" \n Is not available. You have allocated to"+"\n Dr."+secondDoctor1.getFirstName()+"  "+secondDoctor1.getLastName());

                                }

                                patient_panel.setVisible(false);
                                doctor_panel.setVisible(true);
                            }

                        }
                    }
                });



                patient_panel.add(patient);
                patient_panel.add(ptFirstName);
                patient_panel.add(ptLastName);
                patient_panel.add(ptDOB);
                patient_panel.add(ptTelNo);
                patient_panel.add(ptID);
                patient_panel.add(ptNote);
                patient_panel.add(dtID);
                patient_panel.add(date);
                patient_panel.add(inTime);
                patient_panel.add(outTime);



                patient_panel.add(ptFNameTxt);
                patient_panel.add(ptLNameTxt);
                patient_panel.add(ptDOBTxt);
                patient_panel.add(ptTelNoTxt);
                patient_panel.add(ptIDTxt);
                patient_panel.add(ptNoteTxt);
                patient_panel.add(dctName);
                patient_panel.add(dateTxt);
                patient_panel.add(datePicker);
                patient_panel.add(inTimePicker);
                patient_panel.add(outTimePicker);



                patient_panel.add(ap_back);
                patient_panel.add(ap_book);
                patient_panel.add(save);
                patient_panel.add(load);

            }
        });

        doctor_panel.add(addConsultation);
        doctor_panel.add(viewConsultation);
        doctor_panel.add(dt_tbl_hdr);
        doctor_panel.setVisible(true);
        main_Frame.add(patient_panel);
        main_Frame.add(consultation_panel);
        main_Frame.add(doctor_panel);


    }

    private void setDoctorsToTable() {
        LinkedList<Person> allDoctors = westminsterSkinConsultationManager.getAllDoctors();
        for(Person doc:allDoctors){
            Doctor doctor = (Doctor) doc;
            doctorLinkedList.add(doctor);
        }
    }
}
