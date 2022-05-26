/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inti_hotel;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import inti_hotel.Room;

/**
 *
 * @author NitroQ
 */
public class INTI_hotel_GUI extends javax.swing.JFrame {

    int mouseX, mouseY;
    String PressedTime = ""; // to save time when the user do action

    /**
     * Creates new form INTI_hotel_GUI
     */
    public INTI_hotel_GUI() {
        initComponents();
        Login log = new Login();
        log.createFile();
        setUnVisible(jp_Login); // set all JPanel to Unvisible
        labelsunEnabled();

    }

    public void setUnVisible(JPanel visJPanel) {

        JPanel[] jPanels = {jp_Login, jp_Wating_List, jp_Book, jp_ManageCustomer, jp_Available_Rooms, jp_Rooms, jp_ReservedRooms, jp_LoginRedcord, jp_Home};
        for (JPanel Panel : jPanels) {
            Panel.setVisible(false);
        }
        visJPanel.setVisible(true);

    }

    public void labelsunEnabled() {

        JLabel[] labels2 = {JLAvailableRoom, JLManageCust, JLBookRoom, JLloginRec, JLWaitingList, JLHome, JLReservedRooms, JLRooms, JLlogout};
        for (JLabel label : labels2) {
            label.setEnabled(false);
        }

    }
    public void labelSetUnVisible() {

        JLabel[] labels2 = {JLAvailableRoom, JLManageCust, JLBookRoom, JLloginRec, JLWaitingList, JLHome, JLReservedRooms, JLRooms, JLlogout};
        for (JLabel label : labels2) {
            label.setVisible(false);
        }

    }
     public void labelSetVisible() {

        JLabel[] labels2 = {JLAvailableRoom, JLManageCust, JLBookRoom, JLloginRec, JLWaitingList, JLHome, JLReservedRooms, JLRooms, JLlogout};
        for (JLabel label : labels2) {
            label.setVisible(true);
        }

    }

    public void labelsEnabled() {

        JLabel[] labels2 = {JLAvailableRoom, JLManageCust, JLBookRoom, JLloginRec, JLWaitingList, JLHome, JLReservedRooms, JLRooms, JLlogout};
        for (JLabel label : labels2) {
            label.setEnabled(true);
        }

    }

    public void setColorToBlue(JLabel Label) {

        JLabel[] labels = {JLAvailableRoom, JLManageCust, JLBookRoom, JLloginRec, JLWaitingList, JLHome, JLReservedRooms, JLRooms};

        for (JLabel label : labels) {
            label.setForeground(new java.awt.Color(240, 240, 240));
        }
        Label.setForeground(new java.awt.Color(102, 204, 255));

    }

    public boolean log_get_access() {
        Login log = new Login();
        //System.out.println("Main role >>>>>" +log.get_role(user_name.getText(), password.getText()));
        return log.get_role(user_name.getText(), password.getText());
    }

    public void waiting_List_table() {
        DefaultTableModel tmm = (DefaultTableModel) jTwating_List.getModel();
        tmm.setRowCount(0);
        WaitingList wating_List = new WaitingList();
        wating_List.read_from_file();

        for (String ar : wating_List.getItems()) {
            String[] subStrings = ar.split(",");
            Room diffdate = new Room();
            Date bookin = null;
            Date bookout = null;
            try {
                bookin = new SimpleDateFormat("MM/dd/yyyy").parse(subStrings[3]);
                bookout = new SimpleDateFormat("MM/dd/yyyy").parse(subStrings[4]);
            } catch (ParseException ex) {
                Logger.getLogger(INTI_hotel_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            long diffrentDate = diffdate.getDateDiff(bookin, bookout, TimeUnit.DAYS);
            Object obj[] = {subStrings[0], subStrings[1], subStrings[2], subStrings[3], subStrings[4], diffrentDate, subStrings[5], subStrings[6], subStrings[7].replace("-", ", ")};
            tmm.addRow(obj);
        }
    }

    public void reserved_List_table() {
        DefaultTableModel tmm = (DefaultTableModel) jTReservedList.getModel();
        tmm.setRowCount(0);
        ReservedList reservedList = new ReservedList();
        reservedList.read_from_file();
        for (String ar : reservedList.getItems()) {
            String[] subStrings = ar.split(",");
            Room diffdate = new Room();
            Date bookin = null;
            Date bookout = null;
            try {
                bookin = new SimpleDateFormat("MM/dd/yyyy").parse(subStrings[4]);
                bookout = new SimpleDateFormat("MM/dd/yyyy").parse(subStrings[5]);
            } catch (ParseException ex) {
                Logger.getLogger(INTI_hotel_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            long diffrentDate = diffdate.getDateDiff(bookin, bookout, TimeUnit.DAYS);
            Object obj[] = {subStrings[0], subStrings[1], subStrings[2], subStrings[3], subStrings[4], subStrings[5], diffrentDate, subStrings[6], subStrings[7], subStrings[8].replace("-", ", ")};
            tmm.addRow(obj);
        }
    }

    public void available_Rooms_table() {
        DefaultTableModel tmm = (DefaultTableModel) jTAvailable_Rooms.getModel();
        tmm.setRowCount(0);
        RoomList roomList = new RoomList();
        roomList.read_from_file();
        for (String ar : roomList.getItems()) {
            String[] subStrings = ar.split(",");
            tmm.addRow(subStrings);
        }
    }

    public void login_record_table() {
        DefaultTableModel tmm = (DefaultTableModel) jTlogin_record.getModel();
        tmm.setRowCount(0);

        Login log = new Login();

        for (String ar : log.get_login_record()) {
            String[] subStrings = ar.split(",");
            tmm.addRow(subStrings);
        }
    }

    public void Users_table() {
        DefaultTableModel tmm = (DefaultTableModel) jTUsers.getModel();
        tmm.setRowCount(0);

        try {
            Login log = new Login();

            for (String ar : log.getusers()) {
                String[] subStrings = ar.split(",");
                tmm.addRow(subStrings);
            }

        } catch (Exception e) {
            //  System.out.println("erorr605> " + e);
        }
        JTUserName.setText("");
        JTUserPass.setText("");
        jCUserRole.setSelectedIndex(1);

    }

    public void calculateBookRoom() {
        try {
            Room room = new Room();
            int calculate = 0; // to store the result after the calculation 
            String[] getPrices = room.GetTypeOrPrice("Price");
            int getselectedindex = jcbBookRoomType.getSelectedIndex();
            if (jcbBookRoomType.getSelectedIndex() >= 0) // check if there is data has been selected or not to avoid getting error 
            {

                calculate = Integer.parseInt(getPrices[getselectedindex]);
            }
            JCheckBox[] servicesCheckbox = {jCBWifi, jCBMassage, jCBAc, jCBkitchen, jCBAllow_smoke, jCBFood_beverage, jCBAllow_alcohol, jCBPets_room};
            for (JCheckBox addservices : servicesCheckbox) {
                if (addservices.isSelected()) {
                    calculate += 20;
                }
            }
            jLabelTotalPerDay.setText(String.valueOf(calculate));

            if (jDCCheckIN.getDate() != null && jDCCheckOut.getDate() != null) {
                long diffrentDate = room.getDateDiff(jDCCheckIN.getDate(), jDCCheckOut.getDate(), TimeUnit.DAYS);
                calculate = calculate * ((int) diffrentDate); // to convert long to int 

            }
            jLabelTotal.setText(String.valueOf(calculate));
        } catch (FileNotFoundException e) {
            System.out.print(e);
        }
    }

    public void bookRoom() {

        Date currentDate = new Date(); // get the current date 
        String selectedServices = null;// to store the selected services
        JCheckBox[] servicesCheckbox = {jCBWifi, jCBMassage, jCBAc, jCBkitchen, jCBAllow_smoke, jCBFood_beverage, jCBAllow_alcohol, jCBPets_room};
        for (JCheckBox addservices : servicesCheckbox) { // set all the selected service in one string to send the customer class
            if (addservices.isSelected()) {
                if (selectedServices == null) {

                    selectedServices = addservices.getText(); // if it's first services selected

                } else {
                    selectedServices += "-" + addservices.getText();// for the  other services selected
                }
            }
        }
        if (selectedServices == "")// if the customer dosn't selected any services
        {
            selectedServices = "NoServices";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy"); // to change the format of the date
        String bookingDate = formatter.format(currentDate);
        String checkInDtae = formatter.format(jDCCheckIN.getDate());
        String checkOutDate = formatter.format(jDCCheckOut.getDate());
        // check if the data has been entered seccussfly
        if (jcbBookRoomType.getSelectedIndex() > -1 && jDCCheckIN.getDate() != null && jDCCheckOut.getDate() != null && jLabelTotalPerDay.getText() != null && jLabelTotal.getText() != null) {// after checking if the customer has been entered eveything in the system
            Customer newBooking = new Customer(JLUser.getText(), jcbBookRoomType.getSelectedItem().toString(), bookingDate, checkInDtae, checkOutDate, jLabelTotalPerDay.getText(), jLabelTotal.getText(), selectedServices);
            WaitingList addCustomerToWaitingList = new WaitingList();
            addCustomerToWaitingList.read_from_file(); // to reload all wating file which was stored in the file to the list then add the new customer to the queue after them
            addCustomerToWaitingList.enqueue(newBooking.toString());// to add the customer to the waiting list 
            addCustomerToWaitingList.save_in_file();// to save the new customer to the file after has been inserting in the wating list
            JOptionPane.showMessageDialog(null, "Your booking has added to waiting list successfully");
        }
    }

    public void searchForRoom() {
        String word;
        String seleted;
        int selectin = 0;
        //ID, Type, Price, Services
        seleted = jCselectSearchRoom.getSelectedItem().toString();
        word = TFSearchRoom.getText();
        if (null != seleted) {
            switch (seleted) {
                case "ID":
                    selectin = 0;
                    break;
                case "Type":
                    selectin = 1;
                    break;
                case "Price":
                    selectin = 2;
                    break;
                case "Services":
                    selectin = 3;
                    break;
                default:
                    break;
            }
        }
        DefaultTableModel tmm = (DefaultTableModel) jTRooms.getModel();
        tmm.setRowCount(0);
        Room rm = new Room();
        for (String ar : rm.GetRooms()) {
            String[] subStrings = ar.split(",");
            if ((subStrings[selectin].matches("(?i).*" + word + "(.*)"))) {// to check if word that is search is match from file
                tmm.addRow(subStrings);
            }
        }
        JCheckBox[] servicesCheckbox = {jCWifi, jCMassage, jCAC, jCkitchen, jCAllowsmoke, jCfoodandbeverage, jCAllowAlcohol, jCPetsroom};

        JTRoomId.setText("");
        jCRoomType.setSelectedIndex(0);
        for (JCheckBox addservices : servicesCheckbox) { // set all the selected service to unchecked
            addservices.setSelected(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JP_Sidebar = new javax.swing.JPanel();
        JLAvailableRoom = new javax.swing.JLabel();
        JLManageCust = new javax.swing.JLabel();
        JLBookRoom = new javax.swing.JLabel();
        JLloginRec = new javax.swing.JLabel();
        JLWaitingList = new javax.swing.JLabel();
        JLTitle1 = new javax.swing.JLabel();
        JLHome = new javax.swing.JLabel();
        JLReservedRooms = new javax.swing.JLabel();
        JLRooms = new javax.swing.JLabel();
        JLlogout = new javax.swing.JLabel();
        JP_Header = new javax.swing.JPanel();
        JLUser = new javax.swing.JLabel();
        exit = new javax.swing.JLabel();
        JLTitle = new javax.swing.JLabel();
        jp_Login = new javax.swing.JPanel();
        JLHide = new javax.swing.JLabel();
        JBadd_product = new javax.swing.JButton();
        JLShow = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JLLogin = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        user_name = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        errorlogin = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jp_Wating_List = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTwating_List = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTCustName = new javax.swing.JTextField();
        jTRoomtype = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTCheckIn = new javax.swing.JTextField();
        jTCheckOut = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTPricePerDay = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTBookDate = new javax.swing.JTextField();
        jTDays = new javax.swing.JTextField();
        jTServices = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTTotalPrice = new javax.swing.JTextField();
        jp_Book = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabelTotalPerDay = new javax.swing.JLabel();
        jcbBookRoomType = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jCBPets_room = new javax.swing.JCheckBox();
        jCBWifi = new javax.swing.JCheckBox();
        jCBMassage = new javax.swing.JCheckBox();
        jCBAllow_smoke = new javax.swing.JCheckBox();
        jCBFood_beverage = new javax.swing.JCheckBox();
        jCBAc = new javax.swing.JCheckBox();
        jCBkitchen = new javax.swing.JCheckBox();
        jCBAllow_alcohol = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jDCCheckIN = new com.toedter.calendar.JDateChooser();
        jDCCheckOut = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        JBadd_product1 = new javax.swing.JButton();
        jLabelTotal = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jp_ManageCustomer = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTUsers = new javax.swing.JTable();
        JBSaveUser = new javax.swing.JButton();
        JBClearUser = new javax.swing.JButton();
        JTUserPass = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        jCUserRole = new javax.swing.JComboBox<>();
        jLabel82 = new javax.swing.JLabel();
        JTUserName = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        jp_Available_Rooms = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTAvailable_Rooms = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jp_Rooms = new javax.swing.JPanel();
        JBSearchRoom = new javax.swing.JButton();
        JBDeleteRoom = new javax.swing.JButton();
        jCselectSearchRoom = new javax.swing.JComboBox<>();
        jLabel72 = new javax.swing.JLabel();
        TFSearchRoom = new javax.swing.JTextField();
        JBInsertNewRoom = new javax.swing.JButton();
        JBSaveRoom = new javax.swing.JButton();
        JTRoomId = new javax.swing.JTextField();
        JTRoomPrice = new javax.swing.JTextField();
        JBClearRoom = new javax.swing.JButton();
        JBUpdateRoom = new javax.swing.JButton();
        jCPetsroom = new javax.swing.JCheckBox();
        jCMassage = new javax.swing.JCheckBox();
        jCAllowAlcohol = new javax.swing.JCheckBox();
        jCfoodandbeverage = new javax.swing.JCheckBox();
        jCkitchen = new javax.swing.JCheckBox();
        jCAllowsmoke = new javax.swing.JCheckBox();
        jCAC = new javax.swing.JCheckBox();
        jCWifi = new javax.swing.JCheckBox();
        jCRoomType = new javax.swing.JComboBox<>();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTRooms = new javax.swing.JTable();
        jp_ReservedRooms = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTReservedList = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTResCustName = new javax.swing.JTextField();
        jTResRoomID = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jTResCheckIn = new javax.swing.JTextField();
        jTResCheckOut = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTResPricePerDay = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jTResBookDate = new javax.swing.JTextField();
        jTResDays = new javax.swing.JTextField();
        jTResServices = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jTResTotalPrice = new javax.swing.JTextField();
        jTResRoomtype = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jp_Home = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jp_LoginRedcord = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTlogin_record = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JP_Sidebar.setBackground(new java.awt.Color(59, 59, 59));
        JP_Sidebar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                JP_SidebarMouseMoved(evt);
            }
        });
        JP_Sidebar.setLayout(null);

        JLAvailableRoom.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JLAvailableRoom.setForeground(new java.awt.Color(240, 240, 240));
        JLAvailableRoom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/signal.png"))); // NOI18N
        JLAvailableRoom.setText("Available Room");
        JLAvailableRoom.setToolTipText("Dashboard");
        JLAvailableRoom.setEnabled(false);
        JLAvailableRoom.setFocusCycleRoot(true);
        JLAvailableRoom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLAvailableRoomMouseClicked(evt);
            }
        });
        JP_Sidebar.add(JLAvailableRoom);
        JLAvailableRoom.setBounds(10, 150, 180, 40);

        JLManageCust.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JLManageCust.setForeground(new java.awt.Color(240, 240, 240));
        JLManageCust.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/group.png"))); // NOI18N
        JLManageCust.setText("Manage Customers ");
        JLManageCust.setToolTipText("Products");
        JLManageCust.setEnabled(false);
        JLManageCust.setPreferredSize(new java.awt.Dimension(113, 34));
        JLManageCust.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLManageCustMouseClicked(evt);
            }
        });
        JP_Sidebar.add(JLManageCust);
        JLManageCust.setBounds(10, 400, 180, 40);

        JLBookRoom.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JLBookRoom.setForeground(new java.awt.Color(240, 240, 240));
        JLBookRoom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/key.png"))); // NOI18N
        JLBookRoom.setText("Book Room");
        JLBookRoom.setToolTipText("Customers");
        JLBookRoom.setEnabled(false);
        JLBookRoom.setPreferredSize(new java.awt.Dimension(113, 34));
        JLBookRoom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLBookRoomMouseClicked(evt);
            }
        });
        JP_Sidebar.add(JLBookRoom);
        JLBookRoom.setBounds(10, 200, 180, 40);

        JLloginRec.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JLloginRec.setForeground(new java.awt.Color(240, 240, 240));
        JLloginRec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/enter.png"))); // NOI18N
        JLloginRec.setText("Login record");
        JLloginRec.setToolTipText("Supplier");
        JLloginRec.setEnabled(false);
        JLloginRec.setPreferredSize(new java.awt.Dimension(113, 34));
        JLloginRec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLloginRecMouseClicked(evt);
            }
        });
        JP_Sidebar.add(JLloginRec);
        JLloginRec.setBounds(10, 450, 180, 34);

        JLWaitingList.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JLWaitingList.setForeground(new java.awt.Color(240, 240, 240));
        JLWaitingList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stopwatch.png"))); // NOI18N
        JLWaitingList.setText("Wating List");
        JLWaitingList.setToolTipText("Dashboard");
        JLWaitingList.setEnabled(false);
        JLWaitingList.setFocusCycleRoot(true);
        JLWaitingList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLWaitingListMouseClicked(evt);
            }
        });
        JP_Sidebar.add(JLWaitingList);
        JLWaitingList.setBounds(10, 250, 180, 40);

        JLTitle1.setBackground(new java.awt.Color(255, 255, 255));
        JLTitle1.setFont(new java.awt.Font("Barlow Condensed", 1, 26)); // NOI18N
        JLTitle1.setForeground(new java.awt.Color(255, 255, 255));
        JLTitle1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/inti logo (Custom).png"))); // NOI18N
        JLTitle1.setText("INTI Hotel");
        JLTitle1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        JLTitle1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        JP_Sidebar.add(JLTitle1);
        JLTitle1.setBounds(0, 0, 240, 53);

        JLHome.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JLHome.setForeground(new java.awt.Color(240, 240, 240));
        JLHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/house.png"))); // NOI18N
        JLHome.setText("Home");
        JLHome.setToolTipText("Dashboard");
        JLHome.setEnabled(false);
        JLHome.setFocusCycleRoot(true);
        JLHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLHomeMouseClicked(evt);
            }
        });
        JP_Sidebar.add(JLHome);
        JLHome.setBounds(10, 100, 180, 40);

        JLReservedRooms.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JLReservedRooms.setForeground(new java.awt.Color(240, 240, 240));
        JLReservedRooms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/room.png"))); // NOI18N
        JLReservedRooms.setText("Reserved Rooms");
        JLReservedRooms.setToolTipText("Supplier");
        JLReservedRooms.setEnabled(false);
        JLReservedRooms.setPreferredSize(new java.awt.Dimension(113, 34));
        JLReservedRooms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLReservedRoomsMouseClicked(evt);
            }
        });
        JP_Sidebar.add(JLReservedRooms);
        JLReservedRooms.setBounds(10, 300, 180, 40);

        JLRooms.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JLRooms.setForeground(new java.awt.Color(240, 240, 240));
        JLRooms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hotel.png"))); // NOI18N
        JLRooms.setText("Rooms");
        JLRooms.setToolTipText("Dashboard");
        JLRooms.setEnabled(false);
        JLRooms.setFocusCycleRoot(true);
        JLRooms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLRoomsMouseClicked(evt);
            }
        });
        JP_Sidebar.add(JLRooms);
        JLRooms.setBounds(10, 350, 180, 40);

        JLlogout.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JLlogout.setForeground(new java.awt.Color(240, 240, 240));
        JLlogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        JLlogout.setText("Logout ");
        JLlogout.setToolTipText("Supplier");
        JLlogout.setEnabled(false);
        JLlogout.setPreferredSize(new java.awt.Dimension(113, 34));
        JLlogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLlogoutMouseClicked(evt);
            }
        });
        JP_Sidebar.add(JLlogout);
        JLlogout.setBounds(10, 500, 180, 34);

        getContentPane().add(JP_Sidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 650));

        JP_Header.setBackground(new java.awt.Color(102, 102, 102));
        JP_Header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                JP_HeaderMouseDragged(evt);
            }
        });
        JP_Header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JP_HeaderMousePressed(evt);
            }
        });

        JLUser.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        JLUser.setForeground(new java.awt.Color(255, 255, 255));
        JLUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user.png"))); // NOI18N
        JLUser.setToolTipText("User");
        JLUser.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        JLUser.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        exit.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        exit.setForeground(new java.awt.Color(240, 240, 240));
        exit.setText(" X");
        exit.setToolTipText("Exit");
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exitMousePressed(evt);
            }
        });

        JLTitle.setFont(new java.awt.Font("Barlow Condensed", 1, 40)); // NOI18N
        JLTitle.setForeground(new java.awt.Color(255, 255, 255));
        JLTitle.setText("Reservation");

        javax.swing.GroupLayout JP_HeaderLayout = new javax.swing.GroupLayout(JP_Header);
        JP_Header.setLayout(JP_HeaderLayout);
        JP_HeaderLayout.setHorizontalGroup(
            JP_HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_HeaderLayout.createSequentialGroup()
                .addContainerGap(571, Short.MAX_VALUE)
                .addComponent(JLTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(196, 196, 196)
                .addComponent(JLUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        JP_HeaderLayout.setVerticalGroup(
            JP_HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_HeaderLayout.createSequentialGroup()
                .addComponent(exit)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_HeaderLayout.createSequentialGroup()
                .addGap(0, 5, Short.MAX_VALUE)
                .addGroup(JP_HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JLTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLUser, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(JP_Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 50));

        jp_Login.setBackground(new java.awt.Color(236, 230, 225));
        jp_Login.setLayout(null);

        JLHide.setBackground(new java.awt.Color(0, 0, 0));
        JLHide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vision hide.png"))); // NOI18N
        JLHide.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JLHide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLHideMouseClicked(evt);
            }
        });
        jp_Login.add(JLHide);
        JLHide.setBounds(530, 300, 35, 35);

        JBadd_product.setBackground(new java.awt.Color(255, 255, 255));
        JBadd_product.setFont(new java.awt.Font("Source Sans Pro Black", 0, 18)); // NOI18N
        JBadd_product.setForeground(new java.awt.Color(255, 255, 255));
        JBadd_product.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/buttonpng.png"))); // NOI18N
        JBadd_product.setText("Login");
        JBadd_product.setToolTipText("");
        JBadd_product.setBorder(null);
        JBadd_product.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JBadd_product.setOpaque(false);
        JBadd_product.setPreferredSize(new java.awt.Dimension(148, 44));
        JBadd_product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBadd_productActionPerformed(evt);
            }
        });
        jp_Login.add(JBadd_product);
        JBadd_product.setBounds(360, 370, 148, 44);

        JLShow.setToolTipText("");
        JLShow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JLShow.setMaximumSize(new java.awt.Dimension(32, 23));
        JLShow.setMinimumSize(new java.awt.Dimension(32, 23));
        JLShow.setPreferredSize(new java.awt.Dimension(32, 23));
        JLShow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLShowMouseClicked(evt);
            }
        });
        jp_Login.add(JLShow);
        JLShow.setBounds(530, 300, 35, 35);

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Password");
        jLabel2.setToolTipText("Password");
        jp_Login.add(jLabel2);
        jLabel2.setBounds(300, 270, 78, 20);

        JLLogin.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        JLLogin.setForeground(new java.awt.Color(255, 255, 255));
        JLLogin.setText("LOGIN");
        jp_Login.add(JLLogin);
        JLLogin.setBounds(300, 120, 81, 29);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Username");
        jLabel1.setToolTipText("Username");
        jp_Login.add(jLabel1);
        jLabel1.setBounds(300, 170, 82, 20);

        user_name.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        user_name.setForeground(new java.awt.Color(255, 255, 255));
        user_name.setToolTipText("Username");
        user_name.setBorder(null);
        user_name.setCaretColor(new java.awt.Color(255, 255, 255));
        user_name.setOpaque(false);
        jp_Login.add(user_name);
        user_name.setBounds(300, 200, 230, 35);

        password.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        password.setForeground(new java.awt.Color(255, 255, 255));
        password.setToolTipText("Password");
        password.setBorder(null);
        password.setCaretColor(new java.awt.Color(255, 255, 255));
        password.setOpaque(false);
        jp_Login.add(password);
        password.setBounds(300, 300, 226, 35);

        errorlogin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errorlogin.setForeground(new java.awt.Color(255, 0, 0));
        jp_Login.add(errorlogin);
        errorlogin.setBounds(300, 350, 270, 20);

        jLabel15.setBackground(new java.awt.Color(85, 85, 85));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Login 3.png"))); // NOI18N
        jp_Login.add(jLabel15);
        jLabel15.setBounds(260, 110, 360, 340);

        getContentPane().add(jp_Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 910, 600));

        jp_Wating_List.setBackground(new java.awt.Color(236, 230, 225));
        jp_Wating_List.setLayout(null);

        jTwating_List.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer Name", "Room type", "Booked Date", "Check In Date", "Check out Dtae", "days", "Desired Price per day", "Total Desired Price ", "services"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTwating_List.getTableHeader().setReorderingAllowed(false);
        jTwating_List.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTwating_ListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTwating_List);
        if (jTwating_List.getColumnModel().getColumnCount() > 0) {
            jTwating_List.getColumnModel().getColumn(0).setResizable(false);
            jTwating_List.getColumnModel().getColumn(1).setResizable(false);
            jTwating_List.getColumnModel().getColumn(2).setResizable(false);
            jTwating_List.getColumnModel().getColumn(3).setResizable(false);
            jTwating_List.getColumnModel().getColumn(4).setResizable(false);
            jTwating_List.getColumnModel().getColumn(5).setResizable(false);
            jTwating_List.getColumnModel().getColumn(6).setResizable(false);
            jTwating_List.getColumnModel().getColumn(7).setResizable(false);
            jTwating_List.getColumnModel().getColumn(8).setResizable(false);
        }

        jp_Wating_List.add(jScrollPane1);
        jScrollPane1.setBounds(10, 310, 890, 280);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setText("Desired Price per day");
        jp_Wating_List.add(jLabel10);
        jLabel10.setBounds(460, 30, 253, 50);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setText("Customer Name");
        jp_Wating_List.add(jLabel11);
        jLabel11.setBounds(10, 30, 200, 50);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setText("Room type");
        jp_Wating_List.add(jLabel14);
        jLabel14.setBounds(10, 200, 130, 50);

        jTCustName.setEditable(false);
        jTCustName.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jp_Wating_List.add(jTCustName);
        jTCustName.setBounds(210, 40, 180, 30);

        jTRoomtype.setEditable(false);
        jTRoomtype.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jp_Wating_List.add(jTRoomtype);
        jTRoomtype.setBounds(210, 210, 180, 30);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel17.setText("Check Out Date");
        jp_Wating_List.add(jLabel17);
        jLabel17.setBounds(460, 150, 190, 50);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel19.setText("Services");
        jp_Wating_List.add(jLabel19);
        jLabel19.setBounds(20, 250, 110, 50);

        jTCheckIn.setEditable(false);
        jTCheckIn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jp_Wating_List.add(jTCheckIn);
        jTCheckIn.setBounds(710, 100, 180, 30);

        jTCheckOut.setEditable(false);
        jTCheckOut.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jTCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTCheckOutActionPerformed(evt);
            }
        });
        jp_Wating_List.add(jTCheckOut);
        jTCheckOut.setBounds(710, 160, 180, 30);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel20.setText("Check In Date");
        jp_Wating_List.add(jLabel20);
        jLabel20.setBounds(460, 90, 180, 50);

        jTPricePerDay.setEditable(false);
        jTPricePerDay.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jp_Wating_List.add(jTPricePerDay);
        jTPricePerDay.setBounds(720, 40, 170, 30);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel25.setText("Book Date");
        jp_Wating_List.add(jLabel25);
        jLabel25.setBounds(10, 90, 140, 50);

        jTBookDate.setEditable(false);
        jTBookDate.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jp_Wating_List.add(jTBookDate);
        jTBookDate.setBounds(210, 100, 180, 30);

        jTDays.setEditable(false);
        jTDays.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jp_Wating_List.add(jTDays);
        jTDays.setBounds(210, 160, 180, 30);

        jTServices.setEditable(false);
        jTServices.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jp_Wating_List.add(jTServices);
        jTServices.setBounds(200, 260, 670, 30);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel27.setText("Days");
        jp_Wating_List.add(jLabel27);
        jLabel27.setBounds(10, 150, 180, 50);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel28.setText("Total Desired Price");
        jp_Wating_List.add(jLabel28);
        jLabel28.setBounds(460, 200, 225, 50);

        jTTotalPrice.setEditable(false);
        jTTotalPrice.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jp_Wating_List.add(jTTotalPrice);
        jTTotalPrice.setBounds(710, 210, 180, 30);

        getContentPane().add(jp_Wating_List, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 910, 600));

        jp_Book.setBackground(new java.awt.Color(236, 230, 225));
        jp_Book.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jp_BookMouseEntered(evt);
            }
        });
        jp_Book.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel3.setText("Book a room");
        jp_Book.add(jLabel3);
        jLabel3.setBounds(310, 10, 280, 60);

        jLabelTotalPerDay.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jp_Book.add(jLabelTotalPerDay);
        jLabelTotalPerDay.setBounds(230, 370, 160, 30);

        jcbBookRoomType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jp_Book.add(jcbBookRoomType);
        jcbBookRoomType.setBounds(210, 110, 190, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("Type of room");
        jp_Book.add(jLabel5);
        jLabel5.setBounds(30, 90, 160, 60);

        jCBPets_room.setBackground(new java.awt.Color(236, 230, 225));
        jCBPets_room.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCBPets_room.setText("Pets room");
        jCBPets_room.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBPets_roomActionPerformed(evt);
            }
        });
        jp_Book.add(jCBPets_room);
        jCBPets_room.setBounds(620, 230, 97, 25);

        jCBWifi.setBackground(new java.awt.Color(236, 230, 225));
        jCBWifi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCBWifi.setText("Wifi");
        jp_Book.add(jCBWifi);
        jCBWifi.setBounds(210, 170, 51, 25);

        jCBMassage.setBackground(new java.awt.Color(236, 230, 225));
        jCBMassage.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCBMassage.setText("Massage");
        jp_Book.add(jCBMassage);
        jCBMassage.setBounds(350, 170, 85, 25);

        jCBAllow_smoke.setBackground(new java.awt.Color(236, 230, 225));
        jCBAllow_smoke.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCBAllow_smoke.setText("Allow smoke");
        jp_Book.add(jCBAllow_smoke);
        jCBAllow_smoke.setBounds(490, 170, 111, 25);

        jCBFood_beverage.setBackground(new java.awt.Color(236, 230, 225));
        jCBFood_beverage.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCBFood_beverage.setText("Food and beverage");
        jp_Book.add(jCBFood_beverage);
        jCBFood_beverage.setBounds(620, 170, 159, 25);

        jCBAc.setBackground(new java.awt.Color(236, 230, 225));
        jCBAc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCBAc.setText("AC");
        jp_Book.add(jCBAc);
        jCBAc.setBounds(210, 230, 45, 25);

        jCBkitchen.setBackground(new java.awt.Color(236, 230, 225));
        jCBkitchen.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCBkitchen.setText("kitchen");
        jp_Book.add(jCBkitchen);
        jCBkitchen.setBounds(350, 230, 75, 25);

        jCBAllow_alcohol.setBackground(new java.awt.Color(236, 230, 225));
        jCBAllow_alcohol.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCBAllow_alcohol.setText("Allow Alcohol");
        jCBAllow_alcohol.setToolTipText("");
        jp_Book.add(jCBAllow_alcohol);
        jCBAllow_alcohol.setBounds(490, 230, 120, 25);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("services");
        jp_Book.add(jLabel7);
        jLabel7.setBounds(40, 160, 110, 60);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setText("Price per day  RM");
        jp_Book.add(jLabel8);
        jLabel8.setBounds(30, 370, 200, 29);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("Check in Date");
        jp_Book.add(jLabel6);
        jLabel6.setBounds(30, 270, 160, 60);
        jp_Book.add(jDCCheckIN);
        jDCCheckIN.setBounds(200, 290, 220, 30);
        jp_Book.add(jDCCheckOut);
        jDCCheckOut.setBounds(620, 290, 240, 30);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel18.setText("Check out Date");
        jp_Book.add(jLabel18);
        jLabel18.setBounds(440, 270, 170, 60);

        JBadd_product1.setBackground(new java.awt.Color(255, 255, 255));
        JBadd_product1.setFont(new java.awt.Font("Source Sans Pro Black", 0, 18)); // NOI18N
        JBadd_product1.setForeground(new java.awt.Color(255, 255, 255));
        JBadd_product1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/buttonpng.png"))); // NOI18N
        JBadd_product1.setToolTipText("");
        JBadd_product1.setBorder(null);
        JBadd_product1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JBadd_product1.setLabel("Submit");
        JBadd_product1.setOpaque(false);
        JBadd_product1.setPreferredSize(new java.awt.Dimension(148, 44));
        JBadd_product1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBadd_product1ActionPerformed(evt);
            }
        });
        jp_Book.add(JBadd_product1);
        JBadd_product1.setBounds(370, 460, 148, 44);

        jLabelTotal.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jp_Book.add(jLabelTotal);
        jLabelTotal.setBounds(580, 370, 130, 30);

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel24.setText("Total    RM");
        jp_Book.add(jLabel24);
        jLabel24.setBounds(450, 370, 130, 29);

        getContentPane().add(jp_Book, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 910, 600));

        jp_ManageCustomer.setBackground(new java.awt.Color(236, 230, 225));
        jp_ManageCustomer.setLayout(null);

        jTUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Password", "Role"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTUsers.getTableHeader().setReorderingAllowed(false);
        jTUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTUsersMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTUsers);
        if (jTUsers.getColumnModel().getColumnCount() > 0) {
            jTUsers.getColumnModel().getColumn(0).setResizable(false);
            jTUsers.getColumnModel().getColumn(1).setResizable(false);
            jTUsers.getColumnModel().getColumn(2).setResizable(false);
        }

        jp_ManageCustomer.add(jScrollPane6);
        jScrollPane6.setBounds(10, 200, 890, 390);

        JBSaveUser.setBackground(new java.awt.Color(255, 255, 255));
        JBSaveUser.setFont(new java.awt.Font("Source Sans Pro Black", 0, 18)); // NOI18N
        JBSaveUser.setForeground(new java.awt.Color(255, 255, 255));
        JBSaveUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/buttonpng.png"))); // NOI18N
        JBSaveUser.setText("Save");
        JBSaveUser.setToolTipText("");
        JBSaveUser.setActionCommand("");
        JBSaveUser.setBorder(null);
        JBSaveUser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JBSaveUser.setOpaque(false);
        JBSaveUser.setPreferredSize(new java.awt.Dimension(148, 44));
        JBSaveUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBSaveUserActionPerformed(evt);
            }
        });
        jp_ManageCustomer.add(JBSaveUser);
        JBSaveUser.setBounds(570, 140, 148, 44);

        JBClearUser.setBackground(new java.awt.Color(255, 255, 255));
        JBClearUser.setFont(new java.awt.Font("Source Sans Pro Black", 0, 18)); // NOI18N
        JBClearUser.setForeground(new java.awt.Color(255, 255, 255));
        JBClearUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/buttonpng.png"))); // NOI18N
        JBClearUser.setText("Clear");
        JBClearUser.setToolTipText("");
        JBClearUser.setActionCommand("");
        JBClearUser.setBorder(null);
        JBClearUser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JBClearUser.setOpaque(false);
        JBClearUser.setPreferredSize(new java.awt.Dimension(148, 44));
        JBClearUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBClearUserActionPerformed(evt);
            }
        });
        jp_ManageCustomer.add(JBClearUser);
        JBClearUser.setBounds(730, 140, 148, 44);

        JTUserPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTUserPassActionPerformed(evt);
            }
        });
        jp_ManageCustomer.add(JTUserPass);
        JTUserPass.setBounds(400, 70, 200, 30);

        jLabel81.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel81.setText("Role");
        jp_ManageCustomer.add(jLabel81);
        jLabel81.setBounds(610, 70, 70, 30);

        jCUserRole.setEditable(true);
        jCUserRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "customer" }));
        jCUserRole.setSelectedIndex(1);
        jCUserRole.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCUserRoleItemStateChanged(evt);
            }
        });
        jp_ManageCustomer.add(jCUserRole);
        jCUserRole.setBounds(690, 70, 190, 30);

        jLabel82.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel82.setText("Password");
        jp_ManageCustomer.add(jLabel82);
        jLabel82.setBounds(300, 70, 110, 30);

        JTUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTUserNameActionPerformed(evt);
            }
        });
        jp_ManageCustomer.add(JTUserName);
        JTUserName.setBounds(110, 70, 180, 30);

        jLabel83.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel83.setText("Username");
        jp_ManageCustomer.add(jLabel83);
        jLabel83.setBounds(10, 70, 130, 30);

        getContentPane().add(jp_ManageCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 910, 600));

        jp_Available_Rooms.setBackground(new java.awt.Color(236, 230, 225));
        jp_Available_Rooms.setLayout(null);

        jTAvailable_Rooms.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Type", "Price", "services"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTAvailable_Rooms.getTableHeader().setReorderingAllowed(false);
        jTAvailable_Rooms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTAvailable_RoomsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTAvailable_Rooms);
        if (jTAvailable_Rooms.getColumnModel().getColumnCount() > 0) {
            jTAvailable_Rooms.getColumnModel().getColumn(0).setResizable(false);
            jTAvailable_Rooms.getColumnModel().getColumn(1).setResizable(false);
            jTAvailable_Rooms.getColumnModel().getColumn(2).setResizable(false);
            jTAvailable_Rooms.getColumnModel().getColumn(3).setResizable(false);
        }

        jp_Available_Rooms.add(jScrollPane2);
        jScrollPane2.setBounds(10, 150, 890, 440);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel21.setText("Price");
        jp_Available_Rooms.add(jLabel21);
        jLabel21.setBounds(20, 80, 100, 50);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel22.setText("Room ID");
        jp_Available_Rooms.add(jLabel22);
        jLabel22.setBounds(20, 20, 190, 50);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel23.setText("Room Type");
        jp_Available_Rooms.add(jLabel23);
        jLabel23.setBounds(380, 20, 150, 50);

        jTextField9.setEditable(false);
        jTextField9.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jp_Available_Rooms.add(jTextField9);
        jTextField9.setBounds(140, 30, 220, 30);

        jTextField10.setEditable(false);
        jTextField10.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jp_Available_Rooms.add(jTextField10);
        jTextField10.setBounds(530, 30, 200, 30);

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel26.setText("Services");
        jp_Available_Rooms.add(jLabel26);
        jLabel26.setBounds(380, 80, 110, 50);

        jTextField12.setEditable(false);
        jTextField12.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jp_Available_Rooms.add(jTextField12);
        jTextField12.setBounds(140, 90, 220, 30);

        jTextField13.setEditable(false);
        jTextField13.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jp_Available_Rooms.add(jTextField13);
        jTextField13.setBounds(500, 90, 380, 30);

        getContentPane().add(jp_Available_Rooms, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 910, 600));

        jp_Rooms.setBackground(new java.awt.Color(236, 230, 225));
        jp_Rooms.setLayout(null);

        JBSearchRoom.setBackground(new java.awt.Color(255, 255, 255));
        JBSearchRoom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/magnifier.png"))); // NOI18N
        JBSearchRoom.setBorder(null);
        JBSearchRoom.setOpaque(false);
        JBSearchRoom.setPreferredSize(new java.awt.Dimension(24, 24));
        JBSearchRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBSearchRoomActionPerformed(evt);
            }
        });
        jp_Rooms.add(JBSearchRoom);
        JBSearchRoom.setBounds(860, 10, 40, 40);

        JBDeleteRoom.setBackground(new java.awt.Color(255, 255, 255));
        JBDeleteRoom.setFont(new java.awt.Font("Source Sans Pro Black", 0, 18)); // NOI18N
        JBDeleteRoom.setForeground(new java.awt.Color(255, 255, 255));
        JBDeleteRoom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/buttonpng.png"))); // NOI18N
        JBDeleteRoom.setText("Delete");
        JBDeleteRoom.setToolTipText("");
        JBDeleteRoom.setBorder(null);
        JBDeleteRoom.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JBDeleteRoom.setOpaque(false);
        JBDeleteRoom.setPreferredSize(new java.awt.Dimension(148, 44));
        JBDeleteRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBDeleteRoomActionPerformed(evt);
            }
        });
        jp_Rooms.add(JBDeleteRoom);
        JBDeleteRoom.setBounds(740, 210, 148, 44);

        jCselectSearchRoom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Type", "Price", "Services" }));
        jCselectSearchRoom.setToolTipText("");
        jCselectSearchRoom.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 4, true));
        jp_Rooms.add(jCselectSearchRoom);
        jCselectSearchRoom.setBounds(250, 10, 190, 40);

        jLabel72.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/line.png"))); // NOI18N
        jp_Rooms.add(jLabel72);
        jLabel72.setBounds(450, 40, 410, 3);

        TFSearchRoom.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        TFSearchRoom.setToolTipText("Search");
        TFSearchRoom.setBorder(null);
        TFSearchRoom.setOpaque(false);
        TFSearchRoom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TFSearchRoomKeyReleased(evt);
            }
        });
        jp_Rooms.add(TFSearchRoom);
        TFSearchRoom.setBounds(450, 10, 410, 30);

        JBInsertNewRoom.setBackground(new java.awt.Color(255, 255, 255));
        JBInsertNewRoom.setFont(new java.awt.Font("Source Sans Pro Black", 0, 18)); // NOI18N
        JBInsertNewRoom.setForeground(new java.awt.Color(255, 255, 255));
        JBInsertNewRoom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/buttonpng.png"))); // NOI18N
        JBInsertNewRoom.setText("New Room");
        JBInsertNewRoom.setToolTipText("");
        JBInsertNewRoom.setBorder(null);
        JBInsertNewRoom.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JBInsertNewRoom.setOpaque(false);
        JBInsertNewRoom.setPreferredSize(new java.awt.Dimension(148, 44));
        JBInsertNewRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBInsertNewRoomActionPerformed(evt);
            }
        });
        jp_Rooms.add(JBInsertNewRoom);
        JBInsertNewRoom.setBounds(40, 10, 148, 44);

        JBSaveRoom.setBackground(new java.awt.Color(255, 255, 255));
        JBSaveRoom.setFont(new java.awt.Font("Source Sans Pro Black", 0, 18)); // NOI18N
        JBSaveRoom.setForeground(new java.awt.Color(255, 255, 255));
        JBSaveRoom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/buttonpng.png"))); // NOI18N
        JBSaveRoom.setText("Save");
        JBSaveRoom.setToolTipText("");
        JBSaveRoom.setActionCommand("");
        JBSaveRoom.setBorder(null);
        JBSaveRoom.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JBSaveRoom.setOpaque(false);
        JBSaveRoom.setPreferredSize(new java.awt.Dimension(148, 44));
        JBSaveRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBSaveRoomActionPerformed(evt);
            }
        });
        jp_Rooms.add(JBSaveRoom);
        JBSaveRoom.setBounds(260, 210, 148, 44);

        JTRoomId.setEditable(false);
        jp_Rooms.add(JTRoomId);
        JTRoomId.setBounds(50, 70, 130, 30);

        JTRoomPrice.setEditable(false);
        JTRoomPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTRoomPriceActionPerformed(evt);
            }
        });
        jp_Rooms.add(JTRoomPrice);
        JTRoomPrice.setBounds(620, 70, 150, 30);

        JBClearRoom.setBackground(new java.awt.Color(255, 255, 255));
        JBClearRoom.setFont(new java.awt.Font("Source Sans Pro Black", 0, 18)); // NOI18N
        JBClearRoom.setForeground(new java.awt.Color(255, 255, 255));
        JBClearRoom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/buttonpng.png"))); // NOI18N
        JBClearRoom.setText("Clear");
        JBClearRoom.setToolTipText("");
        JBClearRoom.setActionCommand("");
        JBClearRoom.setBorder(null);
        JBClearRoom.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JBClearRoom.setOpaque(false);
        JBClearRoom.setPreferredSize(new java.awt.Dimension(148, 44));
        JBClearRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBClearRoomActionPerformed(evt);
            }
        });
        jp_Rooms.add(JBClearRoom);
        JBClearRoom.setBounds(420, 210, 148, 44);

        JBUpdateRoom.setBackground(new java.awt.Color(255, 255, 255));
        JBUpdateRoom.setFont(new java.awt.Font("Source Sans Pro Black", 0, 18)); // NOI18N
        JBUpdateRoom.setForeground(new java.awt.Color(255, 255, 255));
        JBUpdateRoom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/buttonpng.png"))); // NOI18N
        JBUpdateRoom.setText("Edit");
        JBUpdateRoom.setToolTipText("");
        JBUpdateRoom.setActionCommand("");
        JBUpdateRoom.setBorder(null);
        JBUpdateRoom.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JBUpdateRoom.setOpaque(false);
        JBUpdateRoom.setPreferredSize(new java.awt.Dimension(148, 44));
        JBUpdateRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBUpdateRoomActionPerformed(evt);
            }
        });
        jp_Rooms.add(JBUpdateRoom);
        JBUpdateRoom.setBounds(580, 210, 148, 44);

        jCPetsroom.setBackground(new java.awt.Color(236, 230, 225));
        jCPetsroom.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCPetsroom.setText("Pets room");
        jp_Rooms.add(jCPetsroom);
        jCPetsroom.setBounds(540, 160, 120, 30);

        jCMassage.setBackground(new java.awt.Color(236, 230, 225));
        jCMassage.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCMassage.setText("Massage");
        jCMassage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCMassageActionPerformed(evt);
            }
        });
        jp_Rooms.add(jCMassage);
        jCMassage.setBounds(280, 120, 120, 30);

        jCAllowAlcohol.setBackground(new java.awt.Color(236, 230, 225));
        jCAllowAlcohol.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCAllowAlcohol.setText("Allow Alcohol");
        jp_Rooms.add(jCAllowAlcohol);
        jCAllowAlcohol.setBounds(410, 160, 120, 30);

        jCfoodandbeverage.setBackground(new java.awt.Color(236, 230, 225));
        jCfoodandbeverage.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCfoodandbeverage.setText("food and beverage");
        jp_Rooms.add(jCfoodandbeverage);
        jCfoodandbeverage.setBounds(540, 120, 160, 30);

        jCkitchen.setBackground(new java.awt.Color(236, 230, 225));
        jCkitchen.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCkitchen.setText("kitchen");
        jp_Rooms.add(jCkitchen);
        jCkitchen.setBounds(280, 160, 120, 30);

        jCAllowsmoke.setBackground(new java.awt.Color(236, 230, 225));
        jCAllowsmoke.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCAllowsmoke.setText("Allow smoke");
        jp_Rooms.add(jCAllowsmoke);
        jCAllowsmoke.setBounds(410, 120, 120, 30);

        jCAC.setBackground(new java.awt.Color(236, 230, 225));
        jCAC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCAC.setText("AC");
        jp_Rooms.add(jCAC);
        jCAC.setBounds(140, 160, 120, 30);

        jCWifi.setBackground(new java.awt.Color(236, 230, 225));
        jCWifi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCWifi.setText("Wifi");
        jCWifi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCWifiActionPerformed(evt);
            }
        });
        jp_Rooms.add(jCWifi);
        jCWifi.setBounds(140, 120, 120, 30);

        jCRoomType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCRoomType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCRoomTypeItemStateChanged(evt);
            }
        });
        jp_Rooms.add(jCRoomType);
        jCRoomType.setBounds(290, 70, 190, 30);

        jLabel77.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel77.setText("Services");
        jp_Rooms.add(jLabel77);
        jLabel77.setBounds(20, 140, 90, 30);

        jLabel78.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel78.setText("ID");
        jp_Rooms.add(jLabel78);
        jLabel78.setBounds(10, 70, 70, 30);

        jLabel79.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel79.setText("Price");
        jp_Rooms.add(jLabel79);
        jLabel79.setBounds(550, 70, 70, 30);

        jLabel80.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel80.setText("Type");
        jp_Rooms.add(jLabel80);
        jLabel80.setBounds(210, 70, 70, 30);

        jTRooms.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Type", "Price", "services"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTRooms.getTableHeader().setReorderingAllowed(false);
        jTRooms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTRoomsMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTRooms);
        if (jTRooms.getColumnModel().getColumnCount() > 0) {
            jTRooms.getColumnModel().getColumn(0).setResizable(false);
            jTRooms.getColumnModel().getColumn(1).setResizable(false);
            jTRooms.getColumnModel().getColumn(2).setResizable(false);
            jTRooms.getColumnModel().getColumn(3).setResizable(false);
            jTRooms.getColumnModel().getColumn(3).setHeaderValue("services");
        }

        jp_Rooms.add(jScrollPane4);
        jScrollPane4.setBounds(10, 260, 890, 330);

        getContentPane().add(jp_Rooms, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 910, 600));

        jp_ReservedRooms.setBackground(new java.awt.Color(236, 230, 225));
        jp_ReservedRooms.setLayout(null);

        jTReservedList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer Name", "Room ID", "Room type", "Booked Date", "Check In Date", "Check out Dtae", "days", "Desired Price per day", "Total Desired Price ", "services"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTReservedList.getTableHeader().setReorderingAllowed(false);
        jTReservedList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTReservedListMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTReservedList);
        if (jTReservedList.getColumnModel().getColumnCount() > 0) {
            jTReservedList.getColumnModel().getColumn(0).setResizable(false);
            jTReservedList.getColumnModel().getColumn(1).setResizable(false);
            jTReservedList.getColumnModel().getColumn(2).setResizable(false);
            jTReservedList.getColumnModel().getColumn(3).setResizable(false);
            jTReservedList.getColumnModel().getColumn(4).setResizable(false);
            jTReservedList.getColumnModel().getColumn(5).setResizable(false);
            jTReservedList.getColumnModel().getColumn(6).setResizable(false);
            jTReservedList.getColumnModel().getColumn(7).setResizable(false);
            jTReservedList.getColumnModel().getColumn(8).setResizable(false);
            jTReservedList.getColumnModel().getColumn(9).setResizable(false);
        }

        jp_ReservedRooms.add(jScrollPane5);
        jScrollPane5.setBounds(10, 330, 890, 260);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel12.setText("Desired Price per day");
        jp_ReservedRooms.add(jLabel12);
        jLabel12.setBounds(460, 10, 253, 50);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel13.setText("Customer Name");
        jp_ReservedRooms.add(jLabel13);
        jLabel13.setBounds(10, 10, 200, 50);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel16.setText("Room ID");
        jp_ReservedRooms.add(jLabel16);
        jLabel16.setBounds(10, 170, 130, 50);

        jTResCustName.setEditable(false);
        jTResCustName.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jTResCustName.setToolTipText("");
        jp_ReservedRooms.add(jTResCustName);
        jTResCustName.setBounds(210, 20, 180, 30);

        jTResRoomID.setEditable(false);
        jTResRoomID.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jp_ReservedRooms.add(jTResRoomID);
        jTResRoomID.setBounds(210, 180, 180, 30);

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel29.setText("Check Out Date");
        jp_ReservedRooms.add(jLabel29);
        jLabel29.setBounds(460, 130, 190, 50);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel31.setText("Services");
        jp_ReservedRooms.add(jLabel31);
        jLabel31.setBounds(10, 270, 110, 50);

        jTResCheckIn.setEditable(false);
        jTResCheckIn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jp_ReservedRooms.add(jTResCheckIn);
        jTResCheckIn.setBounds(710, 80, 180, 30);

        jTResCheckOut.setEditable(false);
        jTResCheckOut.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jTResCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTResCheckOutActionPerformed(evt);
            }
        });
        jp_ReservedRooms.add(jTResCheckOut);
        jTResCheckOut.setBounds(710, 140, 180, 30);

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel32.setText("Check In Date");
        jp_ReservedRooms.add(jLabel32);
        jLabel32.setBounds(460, 70, 180, 50);

        jTResPricePerDay.setEditable(false);
        jTResPricePerDay.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jp_ReservedRooms.add(jTResPricePerDay);
        jTResPricePerDay.setBounds(720, 20, 170, 30);

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel33.setText("Book Date");
        jp_ReservedRooms.add(jLabel33);
        jLabel33.setBounds(10, 60, 140, 50);

        jTResBookDate.setEditable(false);
        jTResBookDate.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jp_ReservedRooms.add(jTResBookDate);
        jTResBookDate.setBounds(210, 70, 180, 30);

        jTResDays.setEditable(false);
        jTResDays.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jp_ReservedRooms.add(jTResDays);
        jTResDays.setBounds(210, 130, 180, 30);

        jTResServices.setEditable(false);
        jTResServices.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jp_ReservedRooms.add(jTResServices);
        jTResServices.setBounds(210, 280, 670, 30);

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel39.setText("Days");
        jp_ReservedRooms.add(jLabel39);
        jLabel39.setBounds(10, 120, 180, 50);

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel40.setText("Total Desired Price");
        jp_ReservedRooms.add(jLabel40);
        jLabel40.setBounds(460, 180, 225, 50);

        jTResTotalPrice.setEditable(false);
        jTResTotalPrice.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jp_ReservedRooms.add(jTResTotalPrice);
        jTResTotalPrice.setBounds(710, 190, 180, 30);

        jTResRoomtype.setEditable(false);
        jTResRoomtype.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jp_ReservedRooms.add(jTResRoomtype);
        jTResRoomtype.setBounds(210, 230, 180, 30);

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel30.setText("Room type");
        jp_ReservedRooms.add(jLabel30);
        jLabel30.setBounds(10, 220, 130, 50);

        getContentPane().add(jp_ReservedRooms, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 910, 600));

        jp_Home.setBackground(new java.awt.Color(236, 230, 225));
        jp_Home.setLayout(null);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/welcome-hotel.png"))); // NOI18N
        jp_Home.add(jLabel4);
        jLabel4.setBounds(90, 20, 700, 540);

        getContentPane().add(jp_Home, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 910, 600));

        jp_LoginRedcord.setBackground(new java.awt.Color(236, 230, 225));
        jp_LoginRedcord.setLayout(null);

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel38.setText("Login Record");
        jp_LoginRedcord.add(jLabel38);
        jLabel38.setBounds(310, 20, 280, 60);

        jTlogin_record.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User name", "Password", "Time login ", "Role"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTlogin_record.getTableHeader().setReorderingAllowed(false);
        jTlogin_record.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTlogin_recordMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTlogin_record);
        if (jTlogin_record.getColumnModel().getColumnCount() > 0) {
            jTlogin_record.getColumnModel().getColumn(0).setResizable(false);
            jTlogin_record.getColumnModel().getColumn(1).setResizable(false);
            jTlogin_record.getColumnModel().getColumn(2).setResizable(false);
            jTlogin_record.getColumnModel().getColumn(3).setResizable(false);
        }

        jp_LoginRedcord.add(jScrollPane3);
        jScrollPane3.setBounds(10, 140, 890, 450);

        getContentPane().add(jp_LoginRedcord, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 910, 600));

        setSize(new java.awt.Dimension(1100, 650));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMousePressed
        // TODO add your handling code here:
        int selected = JOptionPane.showConfirmDialog(null, "Are you sure", "Alert", JOptionPane.YES_NO_OPTION);
        if (selected == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_exitMousePressed

    private void JP_HeaderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JP_HeaderMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        setLocation((x - mouseX), (y - mouseY));
    }//GEN-LAST:event_JP_HeaderMouseDragged

    private void JP_HeaderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JP_HeaderMousePressed
        // TODO add your handling code here:
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_JP_HeaderMousePressed

    private void JLHideMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLHideMouseClicked
        // TODO add your handling code here:
        password.setEchoChar((char) 0);
        ImageIcon imageUpdate = new ImageIcon(getClass().getResource("/images/vision show.png"));
        JLShow.setIcon(imageUpdate);
        JLShow.setVisible(true);
        JLShow.setEnabled(true);
        JLHide.setVisible(false);
        JLHide.setEnabled(false);
    }//GEN-LAST:event_JLHideMouseClicked

    private void JLShowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLShowMouseClicked
        // TODO add your handling code here:
        password.setEchoChar((char) 8226);
        JLShow.setVisible(false);
        JLShow.setEnabled(false);
        JLHide.setVisible(true);
        JLHide.setEnabled(true);
    }//GEN-LAST:event_JLShowMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        for (double i = 0.0; i <= 1.0; i = i + 0.1) {
            String value = i + "";
            float f = Float.valueOf(value);
            this.setOpacity(f);
            try {
                Thread.sleep(50);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_formWindowOpened

    private void JBadd_productActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBadd_productActionPerformed
        // TODO add your handling code here:
        boolean check;
        try {
            Login log = new Login();
            check = log.logic(user_name.getText(), password.getText());
            if (user_name.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username is Mandotory");
            } else if (password.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Password is Mandotory");
            } else {
                if (check == true) {
                    JOptionPane.showMessageDialog(null, "password matched");
                    log.login_record(user_name.getText(), password.getText(), new Date(), log_get_access());
                    if (log_get_access() == true) {
                        labelSetVisible();                        
                        
                    } else if (log_get_access() == false) {
                        labelSetUnVisible();
                        JLBookRoom.setVisible(true);
                        JLHome.setVisible(true);
                        JLAvailableRoom.setVisible(true);
                        JLWaitingList.setVisible(true);
                        JLlogout.setVisible(true);
                        
                    }

                    JLUser.setText(user_name.getText());
                    setUnVisible(jp_Home); // set all JPanel to Unvisible
                    labelsunEnabled();
                    JLBookRoom.setEnabled(true);
                    labelsEnabled();
                    setColorToBlue(JLHome);
                } else {
                    JOptionPane.showMessageDialog(null, "incorrect username/password");
                }

            }
        } catch (Exception e) {
            System.out.println("erorr3: " + e);
        }

    }//GEN-LAST:event_JBadd_productActionPerformed

    private void jTwating_ListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTwating_ListMouseClicked

        //Display selected row in JTextFileds
        jTCustName.setText("");
        jTRoomtype.setText("");
        jTBookDate.setText("");
        jTCheckIn.setText("");
        jTCheckOut.setText("");
        jTPricePerDay.setText("");
        jTDays.setText("");
        jTTotalPrice.setText("");
        jTServices.setText("");
        int i = jTwating_List.getSelectedRow();
        TableModel TM = jTwating_List.getModel();
        jTCustName.setText(TM.getValueAt(i, 0).toString());
        jTRoomtype.setText(TM.getValueAt(i, 1).toString());
        jTBookDate.setText(TM.getValueAt(i, 2).toString());
        jTCheckIn.setText(TM.getValueAt(i, 3).toString());
        jTCheckOut.setText(TM.getValueAt(i, 4).toString());
        jTDays.setText(TM.getValueAt(i, 5).toString());
        jTPricePerDay.setText(TM.getValueAt(i, 6).toString());
        jTTotalPrice.setText(TM.getValueAt(i, 7).toString());
        jTServices.setText(TM.getValueAt(i, 8).toString());

        // TODO add your handling code here:
    }//GEN-LAST:event_jTwating_ListMouseClicked

    private void jTAvailable_RoomsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTAvailable_RoomsMouseClicked
        // TODO add your handling code here:
        jTextField9.setText("");
        jTextField10.setText("");
        jTextField12.setText("");
        jTextField13.setText("");
        int i = jTAvailable_Rooms.getSelectedRow();
        TableModel TM = jTAvailable_Rooms.getModel();
        jTextField9.setText(TM.getValueAt(i, 0).toString());
        jTextField10.setText(TM.getValueAt(i, 1).toString());
        jTextField12.setText(TM.getValueAt(i, 2).toString());
        jTextField13.setText(TM.getValueAt(i, 3).toString());
    }//GEN-LAST:event_jTAvailable_RoomsMouseClicked

    private void JLBookRoomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLBookRoomMouseClicked
        // TODO add your handling code here:      
        if (JLUser.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Login first");
        } else {
            setUnVisible(jp_Book);
            setColorToBlue(JLBookRoom);
        }
//           put the roomtype to the checkbox
        Room typeOfRoom = new Room();
        String[] datas;
        jcbBookRoomType.removeAllItems(); // to remove the data from the combo box before inserting to it 
        try {
            datas = typeOfRoom.GetTypeOrPrice("Type");
            for (String roomtype : datas) {
                jcbBookRoomType.addItem(roomtype);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(INTI_hotel_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_JLBookRoomMouseClicked

    private void JLWaitingListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLWaitingListMouseClicked
        // TODO add your handling code here:
        if (JLUser.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Login first");
        } else {
            setUnVisible(jp_Wating_List);
            setColorToBlue(JLWaitingList);
            waiting_List_table();
            jTCustName.setText("");
            jTRoomtype.setText("");
            jTBookDate.setText("");
            jTCheckIn.setText("");
            jTCheckOut.setText("");
            jTPricePerDay.setText("");
            jTDays.setText("");
            jTTotalPrice.setText("");
            jTServices.setText("");

        }

    }//GEN-LAST:event_JLWaitingListMouseClicked

    private void JLHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLHomeMouseClicked
        // TODO add your handling code here:
        if (JLUser.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Login first");
        } else {
            setUnVisible(jp_Home);
            setColorToBlue(JLHome);
        }
    }//GEN-LAST:event_JLHomeMouseClicked

    private void JLRoomsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLRoomsMouseClicked
        // TODO add your handling code here:
        if (JLUser.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Login first");
        } else if (log_get_access() == false) {
            JOptionPane.showMessageDialog(null, "You are not admin");
        } else {
            setUnVisible(jp_Rooms);
            setColorToBlue(JLRooms);
            TFSearchRoom.setText("");
            searchForRoom();
            //           put the roomtype to the checkbox
            Room typeOfRoom = new Room();
            String[] datas;
            jCRoomType.removeAllItems(); // to remove the data from the combo box before inserting to it 
            try {
                datas = typeOfRoom.GetTypeOrPrice("Type");
                for (String roomtype : datas) {
                    jCRoomType.addItem(roomtype);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(INTI_hotel_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            JTRoomId.setText("");
            jCRoomType.setSelectedIndex(0);
            JCheckBox[] servicesCheckbox = {jCWifi, jCMassage, jCAC, jCkitchen, jCAllowsmoke, jCfoodandbeverage, jCAllowAlcohol, jCPetsroom};
            for (JCheckBox addservices : servicesCheckbox) { // set all the selected service to unchecked
                addservices.setSelected(false);
            }
        }
    }//GEN-LAST:event_JLRoomsMouseClicked

    private void JLAvailableRoomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLAvailableRoomMouseClicked
        // TODO add your handling code here:
        if (JLUser.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Login first");
        } else {
            setUnVisible(jp_Available_Rooms);
            setColorToBlue(JLAvailableRoom);
            available_Rooms_table();
            jTextField9.setText("");
            jTextField10.setText("");
            jTextField12.setText("");
            jTextField13.setText("");
        }
    }//GEN-LAST:event_JLAvailableRoomMouseClicked

    private void JLReservedRoomsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLReservedRoomsMouseClicked
        // TODO add your handling code here:
        if (JLUser.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Login first");
        } else if (log_get_access() == false) {
            JOptionPane.showMessageDialog(null, "You are not admin");
        } else {
            setUnVisible(jp_ReservedRooms);
            setColorToBlue(JLReservedRooms);
            reserved_List_table();
            jTResCustName.setText("");
            jTResRoomID.setText("");
            jTResRoomtype.setText("");
            jTResBookDate.setText("");
            jTResCheckIn.setText("");
            jTResCheckOut.setText("");
            jTResDays.setText("");
            jTResPricePerDay.setText("");
            jTResTotalPrice.setText("");
            jTResServices.setText("");
        }
    }//GEN-LAST:event_JLReservedRoomsMouseClicked

    private void JLManageCustMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLManageCustMouseClicked
        // TODO add your handling code here:
        if (JLUser.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Login first");
        } else if (log_get_access() == false) {
            JOptionPane.showMessageDialog(null, "You are not admin");
        } else {
            setUnVisible(jp_ManageCustomer);
            setColorToBlue(JLManageCust);
            Users_table();
            JTUserName.setText("");
            JTUserPass.setText("");
            jCUserRole.setSelectedIndex(1);
        }
    }//GEN-LAST:event_JLManageCustMouseClicked

    private void JLloginRecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLloginRecMouseClicked
        // TODO add your handling code here:
        if (JLUser.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Login first");
        } else if (log_get_access() == false) {
            JOptionPane.showMessageDialog(null, "You are not admin");
        } else {
            setUnVisible(jp_LoginRedcord);
            setColorToBlue(JLloginRec);
            login_record_table();
        }
    }//GEN-LAST:event_JLloginRecMouseClicked

    private void jTCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTCheckOutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTCheckOutActionPerformed

    private void jTlogin_recordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTlogin_recordMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTlogin_recordMouseClicked

    private void JLlogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLlogoutMouseClicked
        // TODO add your handling code here:         
        if (JLUser.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Login first");
        } else {
             labelSetVisible();
            JLUser.setText("");
            user_name.setText("");
            password.setText("");
            setUnVisible(jp_Login);
            labelsunEnabled();
        }

    }//GEN-LAST:event_JLlogoutMouseClicked

    private void JTRoomPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTRoomPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTRoomPriceActionPerformed

    private void JBInsertNewRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBInsertNewRoomActionPerformed
        // TODO add your handling code here:
        JTRoomId.setText("");
        TFSearchRoom.setText("");
        jCRoomType.setSelectedIndex(0);
        JCheckBox[] servicesCheckbox = {jCWifi, jCMassage, jCAC, jCkitchen, jCAllowsmoke, jCfoodandbeverage, jCAllowAlcohol, jCPetsroom};
        for (JCheckBox addservices : servicesCheckbox) { // set all the selected service to unchecked
            addservices.setSelected(false);
        }
        Room rm = new Room();
        int lastid = 0;
        for (String ar : rm.GetRooms()) {
            String[] subStrings = ar.split(",");
            lastid = Integer.valueOf(subStrings[0].replace("R", ""));
        }
        lastid = lastid + 1;
        JTRoomId.setText(String.valueOf("R" + lastid));


    }//GEN-LAST:event_JBInsertNewRoomActionPerformed

    private void jCWifiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCWifiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCWifiActionPerformed

    private void jTRoomsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTRoomsMouseClicked
        // TODO add your handling code here:
        int i = jTRooms.getSelectedRow();
        TableModel TM = jTRooms.getModel();
        JTRoomId.setText(TM.getValueAt(i, 0).toString());
        jCRoomType.setSelectedItem(TM.getValueAt(i, 1).toString());
        JTRoomPrice.setText(TM.getValueAt(i, 2).toString());
        String[] checkbooxs = TM.getValueAt(i, 3).toString().split("-");
        JCheckBox[] servicesCheckbox = {jCWifi, jCMassage, jCAC, jCkitchen, jCAllowsmoke, jCfoodandbeverage, jCAllowAlcohol, jCPetsroom};
        for (JCheckBox addservices : servicesCheckbox) {
            addservices.setSelected(false);
        }
        for (String cheBox : checkbooxs) {
            for (JCheckBox addservices : servicesCheckbox) {
                if (cheBox.equalsIgnoreCase(addservices.getText())) {
                    addservices.setSelected(true);
                }
            }
        }
    }//GEN-LAST:event_jTRoomsMouseClicked

    private void JBadd_product1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBadd_product1ActionPerformed
        // TODO add your handling code here:
        bookRoom();
    }//GEN-LAST:event_JBadd_product1ActionPerformed

    private void jCBPets_roomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBPets_roomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBPets_roomActionPerformed

    private void jp_BookMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_BookMouseEntered
        // TODO add your handling code here:
        calculateBookRoom();
    }//GEN-LAST:event_jp_BookMouseEntered

    private void JBSearchRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBSearchRoomActionPerformed
        // TODO add your handling code here:        
        searchForRoom();
    }//GEN-LAST:event_JBSearchRoomActionPerformed

    private void TFSearchRoomKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFSearchRoomKeyReleased
        // TODO add your handling code here:
        searchForRoom();
    }//GEN-LAST:event_TFSearchRoomKeyReleased

    private void jTReservedListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTReservedListMouseClicked
        // TODO add your handling code here:
        int i = jTReservedList.getSelectedRow();
        TableModel TM = jTReservedList.getModel();
        jTResCustName.setText(TM.getValueAt(i, 0).toString());
        jTResRoomID.setText(TM.getValueAt(i, 1).toString());
        jTResRoomtype.setText(TM.getValueAt(i, 2).toString());
        jTResBookDate.setText(TM.getValueAt(i, 3).toString());
        jTResCheckIn.setText(TM.getValueAt(i, 4).toString());
        jTResCheckOut.setText(TM.getValueAt(i, 5).toString());
        jTResDays.setText(TM.getValueAt(i, 6).toString());
        jTResPricePerDay.setText(TM.getValueAt(i, 7).toString());
        jTResTotalPrice.setText(TM.getValueAt(i, 8).toString());
        jTResServices.setText(TM.getValueAt(i, 9).toString());
    }//GEN-LAST:event_jTReservedListMouseClicked

    private void jTResCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTResCheckOutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTResCheckOutActionPerformed

    private void JBSaveRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBSaveRoomActionPerformed
        // TODO add your handling code here:        
        //calculateBookRoom();
        String selectedServices = null;// to store the selected services
        JCheckBox[] servicesCheckbox = {jCWifi, jCMassage, jCAC, jCkitchen, jCAllowsmoke, jCfoodandbeverage, jCAllowAlcohol, jCPetsroom};

        int calculate = Integer.valueOf(JTRoomPrice.getText());
        for (JCheckBox addservices : servicesCheckbox) { // set all the selected service in one string to send the
            // customer class
            if (addservices.isSelected()) {
                if (selectedServices == null) {
                    selectedServices = addservices.getText(); // if it's first services selected
                } else {
                    selectedServices += "-" + addservices.getText();// for the other services selected
                }
                if (addservices.isSelected()) {
                    calculate += 20;
                }
            }
        }
        if (selectedServices == "")// if the customer dosn't selected any services
        {
            selectedServices = "NoServices";
        }
        Room newroom = new Room(JTRoomId.getText(), jCRoomType.getSelectedItem().toString(), String.valueOf(calculate), selectedServices);
        try {
            newroom.AddNewRoom();
            RoomList rl = new RoomList();
            rl.read_from_file();
            rl.addToEnd(newroom.toString2());
            rl.save_in_file();
            JOptionPane.showMessageDialog(null, "You add new room successfully");
        } catch (Exception e) {
            //TODO: handle exception
        }

        searchForRoom();
        JTRoomId.setText("");
        jCRoomType.setSelectedIndex(0);
        for (JCheckBox addservices : servicesCheckbox) { // set all the selected service to unchecked
            addservices.setSelected(false);
        }
    }//GEN-LAST:event_JBSaveRoomActionPerformed

    private void jCRoomTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCRoomTypeItemStateChanged
        // TODO add your handling code here:
        Room rm = new Room();
        int calculate = 0; // to store the result after the calculation
        String[] getPrices = null;
        try {
            getPrices = rm.GetTypeOrPrice("Price");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(INTI_hotel_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        int getselectedindex = jCRoomType.getSelectedIndex();
        if (jCRoomType.getSelectedIndex() >= 0) // check if there is data has been selected or not to avoid getting                                                    
        {
            calculate = Integer.parseInt(getPrices[getselectedindex]);
        }
        JTRoomPrice.setText(String.valueOf(calculate));

    }//GEN-LAST:event_jCRoomTypeItemStateChanged

    private void JBClearRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBClearRoomActionPerformed
        // TODO add your handling code here:
        JTRoomId.setText("");
        TFSearchRoom.setText("");
        jCRoomType.setSelectedIndex(0);
        JCheckBox[] servicesCheckbox = {jCWifi, jCMassage, jCAC, jCkitchen, jCAllowsmoke, jCfoodandbeverage, jCAllowAlcohol, jCPetsroom};
        for (JCheckBox addservices : servicesCheckbox) { // set all the selected service to unchecked
            addservices.setSelected(false);
        }
    }//GEN-LAST:event_JBClearRoomActionPerformed

    private void JBDeleteRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBDeleteRoomActionPerformed
        // TODO add your handling code here:
        int i = jTRooms.getSelectedRow();
        TableModel TM = jTRooms.getModel();
        Room rm = new Room(TM.getValueAt(i, 0).toString(), TM.getValueAt(i, 1).toString(), TM.getValueAt(i, 2).toString(), TM.getValueAt(i, 3).toString());
        rm.delete_edit_line(rm.toString2(), 0);
        searchForRoom();
    }//GEN-LAST:event_JBDeleteRoomActionPerformed

    private void jCMassageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCMassageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCMassageActionPerformed

    private void JBUpdateRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBUpdateRoomActionPerformed
        // TODO add your handling code here:
        String selectedServices = null;// to store the selected services
        JCheckBox[] servicesCheckbox = {jCWifi, jCMassage, jCAC, jCkitchen, jCAllowsmoke, jCfoodandbeverage, jCAllowAlcohol, jCPetsroom};
        Room rm = new Room();
        int getprice = 0; // to store the result after the calculation
        String[] getPrices = null;
        try {
            getPrices = rm.GetTypeOrPrice("Price");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(INTI_hotel_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        int getselectedindex = jCRoomType.getSelectedIndex();
        if (jCRoomType.getSelectedIndex() >= 0) // check if there is data has been selected or not to avoid getting                                                    
        {
            getprice = Integer.parseInt(getPrices[getselectedindex]);
        }
        JTRoomPrice.setText(String.valueOf(getprice));

        int calculate = Integer.valueOf(JTRoomPrice.getText());
        for (JCheckBox addservices : servicesCheckbox) { // set all the selected service in one string to send the
            // customer class
            if (addservices.isSelected()) {
                if (selectedServices == null) {
                    selectedServices = addservices.getText(); // if it's first services selected
                } else {
                    selectedServices += "-" + addservices.getText();// for the other services selected
                }
                if (addservices.isSelected()) {
                    calculate += 20;
                }
            }
        }
        if (selectedServices == "")// if the customer dosn't selected any services
        {
            selectedServices = "NoServices";
        }
        Room edit = new Room(JTRoomId.getText(), jCRoomType.getSelectedItem().toString(), String.valueOf(calculate), selectedServices);
        // System.out.println("edit value>> "+edit.toString2() );
        edit.delete_edit_line(edit.toString2(), 1);
        searchForRoom();
    }//GEN-LAST:event_JBUpdateRoomActionPerformed

    private void jTUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTUsersMouseClicked
        // TODO add your handling code here:
        int i = jTUsers.getSelectedRow();
        TableModel TM = jTUsers.getModel();
        JTUserName.setText(TM.getValueAt(i, 0).toString());
        JTUserPass.setText(TM.getValueAt(i, 1).toString());
        jCUserRole.setSelectedItem(TM.getValueAt(i, 2).toString());
    }//GEN-LAST:event_jTUsersMouseClicked

    private void JBSaveUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBSaveUserActionPerformed
        // TODO add your handling code here:
        Login addnewLogin = new Login();
        addnewLogin.adduser(JTUserName.getText(), JTUserPass.getText(), jCUserRole.getSelectedItem().toString());
        Users_table();
    }//GEN-LAST:event_JBSaveUserActionPerformed

    private void JBClearUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBClearUserActionPerformed
        // TODO add your handling code here:
        JTUserName.setText("");
        JTUserPass.setText("");
        jCUserRole.setSelectedIndex(1);
    }//GEN-LAST:event_JBClearUserActionPerformed

    private void JTUserPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTUserPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTUserPassActionPerformed

    private void jCUserRoleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCUserRoleItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jCUserRoleItemStateChanged

    private void JTUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTUserNameActionPerformed

    private void JP_SidebarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JP_SidebarMouseMoved
        // TODO add your handling code here:
        WaitingList list1 = new WaitingList();
        list1.setRoomForWatingCustomer();
    }//GEN-LAST:event_JP_SidebarMouseMoved

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(INTI_hotel_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(INTI_hotel_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(INTI_hotel_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(INTI_hotel_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new INTI_hotel_GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBClearRoom;
    private javax.swing.JButton JBClearUser;
    private javax.swing.JButton JBDeleteRoom;
    private javax.swing.JButton JBInsertNewRoom;
    private javax.swing.JButton JBSaveRoom;
    private javax.swing.JButton JBSaveUser;
    private javax.swing.JButton JBSearchRoom;
    private javax.swing.JButton JBUpdateRoom;
    private javax.swing.JButton JBadd_product;
    private javax.swing.JButton JBadd_product1;
    private javax.swing.JLabel JLAvailableRoom;
    private javax.swing.JLabel JLBookRoom;
    private javax.swing.JLabel JLHide;
    private javax.swing.JLabel JLHome;
    private javax.swing.JLabel JLLogin;
    private javax.swing.JLabel JLManageCust;
    private javax.swing.JLabel JLReservedRooms;
    private javax.swing.JLabel JLRooms;
    private javax.swing.JLabel JLShow;
    private javax.swing.JLabel JLTitle;
    private javax.swing.JLabel JLTitle1;
    private javax.swing.JLabel JLUser;
    private javax.swing.JLabel JLWaitingList;
    private javax.swing.JLabel JLloginRec;
    private javax.swing.JLabel JLlogout;
    private javax.swing.JPanel JP_Header;
    private javax.swing.JPanel JP_Sidebar;
    private javax.swing.JTextField JTRoomId;
    private javax.swing.JTextField JTRoomPrice;
    private javax.swing.JTextField JTUserName;
    private javax.swing.JTextField JTUserPass;
    private javax.swing.JTextField TFSearchRoom;
    private javax.swing.JLabel errorlogin;
    private javax.swing.JLabel exit;
    private javax.swing.JCheckBox jCAC;
    private javax.swing.JCheckBox jCAllowAlcohol;
    private javax.swing.JCheckBox jCAllowsmoke;
    private javax.swing.JCheckBox jCBAc;
    private javax.swing.JCheckBox jCBAllow_alcohol;
    private javax.swing.JCheckBox jCBAllow_smoke;
    private javax.swing.JCheckBox jCBFood_beverage;
    private javax.swing.JCheckBox jCBMassage;
    private javax.swing.JCheckBox jCBPets_room;
    private javax.swing.JCheckBox jCBWifi;
    private javax.swing.JCheckBox jCBkitchen;
    private javax.swing.JCheckBox jCMassage;
    private javax.swing.JCheckBox jCPetsroom;
    private javax.swing.JComboBox<String> jCRoomType;
    private javax.swing.JComboBox<String> jCUserRole;
    private javax.swing.JCheckBox jCWifi;
    private javax.swing.JCheckBox jCfoodandbeverage;
    private javax.swing.JCheckBox jCkitchen;
    private javax.swing.JComboBox<String> jCselectSearchRoom;
    private com.toedter.calendar.JDateChooser jDCCheckIN;
    private com.toedter.calendar.JDateChooser jDCCheckOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabelTotal;
    private javax.swing.JLabel jLabelTotalPerDay;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTAvailable_Rooms;
    private javax.swing.JTextField jTBookDate;
    private javax.swing.JTextField jTCheckIn;
    private javax.swing.JTextField jTCheckOut;
    private javax.swing.JTextField jTCustName;
    private javax.swing.JTextField jTDays;
    private javax.swing.JTextField jTPricePerDay;
    private javax.swing.JTextField jTResBookDate;
    private javax.swing.JTextField jTResCheckIn;
    private javax.swing.JTextField jTResCheckOut;
    private javax.swing.JTextField jTResCustName;
    private javax.swing.JTextField jTResDays;
    private javax.swing.JTextField jTResPricePerDay;
    private javax.swing.JTextField jTResRoomID;
    private javax.swing.JTextField jTResRoomtype;
    private javax.swing.JTextField jTResServices;
    private javax.swing.JTextField jTResTotalPrice;
    private javax.swing.JTable jTReservedList;
    private javax.swing.JTable jTRooms;
    private javax.swing.JTextField jTRoomtype;
    private javax.swing.JTextField jTServices;
    private javax.swing.JTextField jTTotalPrice;
    private javax.swing.JTable jTUsers;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTable jTlogin_record;
    private javax.swing.JTable jTwating_List;
    private javax.swing.JComboBox<String> jcbBookRoomType;
    private javax.swing.JPanel jp_Available_Rooms;
    private javax.swing.JPanel jp_Book;
    private javax.swing.JPanel jp_Home;
    private javax.swing.JPanel jp_Login;
    private javax.swing.JPanel jp_LoginRedcord;
    private javax.swing.JPanel jp_ManageCustomer;
    private javax.swing.JPanel jp_ReservedRooms;
    private javax.swing.JPanel jp_Rooms;
    private javax.swing.JPanel jp_Wating_List;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField user_name;
    // End of variables declaration//GEN-END:variables
}
