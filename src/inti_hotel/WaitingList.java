/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inti_hotel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author rdmn5
 */
public class WaitingList //this class has been created for the wating list by Utilizing a singly linked list
{

    public class sllNode // create a node class to be used in other methods  
    {

        String data;
        sllNode next;

        public sllNode(String data) {
            this.data = data;
            this.next = null;
        }
    }

    sllNode head;

    public WaitingList()// constracture
    {
    }

    public void enqueue(String data)// to insert new wating to the watinglist
    {
        sllNode n = new sllNode(data);
        if (head == null) {
            head = n;
        } else {
            sllNode current = head;
            while (current.next != null) {
                current = current.next;

            }
            current.next = n;
        }
    }

    public sllNode dequeue() { // delete At Start

        if (head != null)// check if there is data in the begining of the linkedlist
        {
            sllNode toDelete = head; // create new node and give the whole data
            head = head.next; // make the head start from the next verbale
            return toDelete;
        }
        return null;// if there is no data in the head at all   
    }

    public void delete(String data) { // delete specifc node
        // Store head node
        sllNode current = head, prev = null;
        // If head node itself holds the key to be deleted
        if (current != null && current.data == data) {
            head = current.next; // change the head
            return;
        }
        // Search for the key to be deleted, keep track of
        // the previous node as we need to change current.next
        while (current != null && current.data != data) {
            prev = current;
            current = current.next;
        }
        // If the key  was not present in linked list 
        if (current == null) {
            return;
        }
        // Unlink the node from linked list
        prev.next = current.next;
    }

    public boolean isEmpty() // to cehck if there is data or not , by checking the head
    {

        if (head == null) // check the head if has data or not, if has data it means the linked has data 
        {
            return true; // Empty
        } else {
            return false; // it's not Empty
        }
    }

    ///////////////////////////another methods//////////////////////////////
    public void addToStart(String data)// to add new one and store it before all of them in the head
    {
        sllNode n = new sllNode(data); // create new node and give the first data whcih i want to be the head
        n.next = head; // set the data to that new node start from the next 
        head = n; // give the head  the new data after the process has been done
    }

    public void addAfter(String insertAfter, String data) // add data after specific data
    {
        sllNode current = head;

        while (current != null) {
            if (current.data == insertAfter) {
                sllNode n = new sllNode(data);
                n.next = current.next;
                current.next = n;
                break;
            }
            current = current.next;
        }
    }

    public sllNode deleteAtEnd() // to delete the last item in the linked list
    {
        sllNode current = head;
        if (current == null || current.next == null) {
            head = null;
            return current;
        }
        sllNode nextNode = current.next;
        while (current.next != null) {
            if (nextNode.next == null) {
                current.next = null;
            }
            current = nextNode;
            nextNode = nextNode.next;
        }
        return current;
    }

    public sllNode deleteAfter(String data) {
        sllNode current = head; // store the current data
        sllNode toDelete = null;// create a tempreary node
        while (current != null) // check if ther is data 
        {
            if (current.data == data && current.next != null) // check the current data equal the data which i want to remove
            {
                toDelete = current.next; // 
                current.next = toDelete.next;
                break;
            }
            current = current.next;
        }
        return toDelete;
    }

    public int getCount() // to get the size of the linked list
    {
        sllNode current = head; // temporary 
        int count = 0;
        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }

    public String[] getItems() //to return all the data in array string 
    {
        sllNode node = head;
        String[] display = new String[getCount()];
        for (int index = 0; index < getCount(); index++) {
            display[index] = node.data;
            node = node.next;
        }

        return display;
    }

    public void show() // print whole the items
    {
        sllNode node = head;
        while (node != null && node.next != null) {
            System.out.println("show> " + node.data);
            node = node.next;
        }
        if (node != null) {
            System.out.println("show> " + node.data);
        }

    }

    public void save_in_file() // store the data from the list to the file 
    {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("WaitingList.txt"));
            for (String ar : getItems()) {
                bw.write(ar + "\n");
            }
            bw.close();
        } catch (IOException ex) {
            System.out.println("Error100" + ex);
        }
    }

    public void read_from_file() // read from the file and store it in the list
    {
        try {
            head = null; // clean the list 
            BufferedReader br = new BufferedReader(new FileReader("WaitingList.txt"));
            String read;
            while ((read = br.readLine()) != null) {
                enqueue(read);// add the data form the file to the list
                //System.out.println(read);
            }
            br.close();
        } catch (IOException ex) {
            System.out.println("Error110" + ex);
        }
    }
    // public void setRoomForWatingCustomer(String Watinglist, String RoomList) {

    public void setRoomForWatingCustomer() {
        RoomList roomList1 = new RoomList();
        roomList1.read_from_file(); // load the data from the file to the list to use it 
        String[] availableRooms = roomList1.getItems();
        ///////////////////////////
        read_from_file();// get the wating lsit data to the current list 
        ////////
        WaitingList wltemp = new WaitingList(); //temprary use
        boolean gotRoomSuccessfully = false;
        int wlcount = getCount();

        if (isEmpty() == false && roomList1.isEmpty() == false)//check if the waiting list and roomlist is not empty
        {

            for (int waitingIndex = 0; waitingIndex < wlcount; waitingIndex++) // check for evey customer in wating list
            {
                RoomList save_others_RoomList = new RoomList();
                RoomList rltemp = new RoomList(); // temparary roomlist for check data
                rltemp.read_from_file();// get all the rooms from the roomlist file to the roomlist to check it 
                String[] roomLisatData = rltemp.getItems(); // get the data
                int roomCheckedIndex = 0;
                int avarooms = rltemp.getCount();
                for (int roomIndex = 0; roomIndex < avarooms; roomIndex++) // check for evey room in room list
                {
                    gotRoomSuccessfully = false;
                    String[] wlsubStrings = head.data.split(",");
                    String wlcustomerName = wlsubStrings[0];
                    String wlroomType = wlsubStrings[1];
                    String wlbookingDate = wlsubStrings[2];
                    String wlcheckInDate = wlsubStrings[3];
                    String wlcheckOutDate = wlsubStrings[4];
                    String wldesiredPrice = wlsubStrings[5];
                    String totalDesiredPrice = wlsubStrings[6];
                    String wlservices = wlsubStrings[7];
                    String[] rlsubStrings = roomLisatData[roomIndex].split(",");
                    String rlroomid = rlsubStrings[0];
                    String rlservices = rlsubStrings[3];
                    String rlroomtype = rlsubStrings[1];
                    String rlPrice = rlsubStrings[2];

                    if ((wlroomType.equals(rlroomtype)) && (wldesiredPrice.equals(rlPrice)) && checkServiceInclude(wlservices, rlservices)) {// customer requirment matched! 
                        System.out.println("test------ customer match");
                        // add to reservelist
                        ReservedList reserved = new ReservedList();
                        reserved.read_from_file();
                        reserved.addToEnd(wlcustomerName + "," + rlroomid + "," + wlroomType + "," + wlbookingDate + "," + wlcheckInDate + "," + wlcheckOutDate + "," + wldesiredPrice + "," + totalDesiredPrice + "," + rlservices);
                        reserved.save_in_file();

                        dequeue(); // remove customer from the current queue
                        gotRoomSuccessfully = true;
                        roomCheckedIndex++;
                        break;
                    } else {
                        save_others_RoomList.addToEnd(roomLisatData[roomIndex]);// readdit to the room list becasue it's not assign or match 
                        roomCheckedIndex++;
//                        System.out.println("test------dosn't found" + roomIndex);
                    }

                } // end the loop for the room
                if (gotRoomSuccessfully != true) {

                    wltemp.enqueue(head.data);// add it to the temp queue

                    dequeue(); // remove it from the current queue

                }

                if (roomCheckedIndex != rltemp.getCount())// to add the remainig room to the list after the break; stop the loop if the room is found
                {
                    int countlist = (rltemp.getCount());

                    for (int index = roomCheckedIndex; index < countlist; index++) {
                        save_others_RoomList.addToEnd(roomLisatData[index]);

                    }

                    save_others_RoomList.save_in_file(); // update the roomlist in the file after remove the assigned room from it 
                    save_others_RoomList.clear();

                }
            }
            wltemp.save_in_file(); // to update the data in the file  // ***make valid check if the rl and wl is empty
        }
    }

    public boolean checkServiceInclude(String wlservices, String rlservices) {//check if the services is matched

        boolean match = false;
        try {
            String[] subStrings = wlservices.split("-"); //split the service which come from the file
            for (int servics = 0; servics < subStrings.length; servics++) {
                if (rlservices.matches("(?i).*" + subStrings[servics] + "(.*)")) {
                    match = true;
                    break;
                }

            }

        } catch (Exception e) { // if the customer select only one service

            if (rlservices.matches("(?i).*" + wlservices + "(.*)")) {
                match = true;

            }
            else
            match = false;

        }
        return match;

    }

}
