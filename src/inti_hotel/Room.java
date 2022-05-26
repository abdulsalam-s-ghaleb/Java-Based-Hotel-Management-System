/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inti_hotel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rdmn5
 */
public class Room {

    private String roomID;
    private Date bookingDate;
    private Date check_In_Date;
    private Date check_Out_Date;
    private String roomType;
    private String other_Services;
    private String price;

    /*
public Room() {
super();
}
     */
    public Room() {

    }

    public Room(String roomId) {
        this.roomID = roomId;
    }

    public Room(String roomID, Date bookingDate, Date check_In_Date, Date check_Out_Date, String roomType, String other_Services, String price) {  // booked rooms
        super();
        this.roomID = roomID;
        this.bookingDate = bookingDate;
        this.check_In_Date = check_In_Date;
        this.check_Out_Date = check_Out_Date;
        this.roomType = roomType;
        this.other_Services = other_Services;
        this.price = price;
    }

    public Room(String roomID, String roomType, String price, String other_Services) { // unbooked room
        super();
        this.roomID = roomID;
        this.roomType = roomType;
        this.other_Services = other_Services;
        this.price = price;

    }

    public String getRoomId() {
        return roomID;
    }

    public void setRoomId(String roomId) {
        this.roomID = roomId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getCheck_In_Date() {
        return check_In_Date;
    }

    public void setCheck_In_Date(Date check_In_Date) {
        this.check_In_Date = check_In_Date;
    }

    public Date getCheck_Out_Date() {
        return check_Out_Date;
    }

    public void setCheck_Out_Date(Date check_Out_Date) {
        this.check_Out_Date = check_Out_Date;
    }

    public String getroomType() {
        return roomType;
    }

    public void setroomType(String roomType) {
        this.roomType = roomType;
    }

    public String getOther_Services() {
        return other_Services;
    }

    public void setOther_Services(String other_Services) {
        this.other_Services = other_Services;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    /*
    public boolean hasAC() {
        if (other_Services == "yes") {
            return true;
        } else {
            return false;
        }  

}*/
    public String toString() {
        SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yyyy");
        return getRoomId() + "," + ft.format(getBookingDate()) + "," + ft.format(getCheck_In_Date()) + "," + ft.format(getCheck_Out_Date()) + "," + getroomType() + "," + getPrice() + "," + getOther_Services();
    }

    public String toString2() {
        return getRoomId() + "," + getroomType() + "," + getPrice() + "," + getOther_Services();
    }

    public String[] GetRooms() // 
    {
        RoomList Rooms = new RoomList();
        try {

            BufferedReader br = new BufferedReader(new FileReader("Rooms.txt"));
            String read;
            while ((read = br.readLine()) != null) {
                Rooms.addToEnd(read);// add the data form the file to the list                
            }
            br.close();
        } catch (IOException ex) {
            System.out.println("Error6" + ex);
        }
        return Rooms.getItems();

    }

    public void AddNewRoom() throws IOException // add new room and save it to the file with help the roomlist to get the room which has been stored.
    {
        RoomList Rooms = new RoomList();
        try {

            BufferedReader br = new BufferedReader(new FileReader("Rooms.txt"));
            System.out.println("File read");
            String read;
            while ((read = br.readLine()) != null) {
                Rooms.addToEnd(read);// add the data form the file to the list                
            }
            br.close();
        } catch (FileNotFoundException ex) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("Rooms.txt"));//create file to write                    
                bw.close();
                System.out.println("File created");

            } catch (IOException ex1) {
                System.out.println("Error55" + ex);
            }
            System.out.println("Error6" + ex);
        }
        Rooms.addToEnd(toString2());// get the data from the class and Save it to the file
        //// write in the file  

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Rooms.txt"));
            System.out.println("File witering new");
            for (String ar : Rooms.getItems()) {
                bw.write(ar + "\n");
            }
            bw.close();
        } catch (IOException ex) {
            System.out.println("Error55" + ex);
        }

    }

    public String[] GetTypeOrPrice(String data) throws FileNotFoundException {
        RoomList typeOfRoom = new RoomList();
        try {

            BufferedReader br = new BufferedReader(new FileReader("RoomType.txt"));
            String read;
            while ((read = br.readLine()) != null) {
                typeOfRoom.addToEnd(read);// add the data form the file to the list
                //   System.out.println(read);
            }
            br.close();
        } catch (IOException ex) {
            System.out.println("Error7" + ex);
        }
        RoomList typeOfRoom2 = new RoomList();
        int linelog = 0;
        try {

            BufferedReader brr = new BufferedReader(new FileReader("RoomType.txt"));
            try {
                for (int i = 0; brr.readLine() != null; i++) {
                    linelog++;
                }
            } catch (IOException ex) {
                Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException e) {
        }
        String[] readed = new String[linelog];
        String[] type = new String[linelog]; // to srtore all the type of rooms
        String[] priceroom = new String[linelog]; // to store all the prices of the room
        try {
            BufferedReader read = new BufferedReader(new FileReader("RoomType.txt"));
            for (int i = 0; i < linelog; i++) {
                try {
                    readed[i] = read.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
                }
                String[] rString = readed[i].split(",");
                type[i] = rString[0];
                priceroom[i] = rString[1];
            }
        } catch (FileNotFoundException e) {
        }
        switch (data) {
            case "Type": {
                return type;
            }
            case "Price": {
                return priceroom;
            }

        }

        return null;
    }

    public long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) { // to get the diffrent between two dates
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);

    }

    public boolean delete_edit_line(String deleteString, int selet) { // to deleted and edit line from file 
        File inputFile = new File("Rooms.txt");
        File tempFile = new File("TempRooms.txt");
        String currentLine;
        boolean done = false;
        try {
            BufferedReader buffreader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter buffwriter = new BufferedWriter(new FileWriter(tempFile));
            while ((currentLine = buffreader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (selet == 0) {
                    if (trimmedLine.equals(deleteString)) {
                        continue;
                    }
                    buffwriter.write(currentLine + System.getProperty("line.separator"));
                } else if (selet == 1) {
                    if (trimmedLine.substring(0, 4).equals(deleteString.substring(0, 4))) {
                        buffwriter.write(deleteString + System.getProperty("line.separator"));
                    } else {
                        buffwriter.write(currentLine + System.getProperty("line.separator"));
                    }
                }
            }
            buffwriter.close();
            buffreader.close();
            inputFile.delete();
            done = tempFile.renameTo(inputFile);
            return done;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }
        return done;
    }

    public int binarySearch(String[] arr, String target) { // to found the element index in the array
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // note, compareTo if the result is 0 means true (-1 means less than )1 (means more than) 
            int result = target.compareTo(arr[mid]);

            // Check if target is present at mid 
            if (result == 0) {
                return mid;
            } // If target greater, ignore left half 
            else if (result > 0) {
                left = mid + 1;
            } // If target is smaller, ignore right half 
            else {
                right = mid - 1;
            }
        }

        return -1;
    }

}
