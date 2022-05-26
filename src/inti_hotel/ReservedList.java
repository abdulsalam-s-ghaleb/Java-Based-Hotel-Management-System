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
public class ReservedList {
    
    
    //this class has been created for the room list  by Utilizing a Doubly  linked list 

    DllNode head;

    class DllNode {

        String data;
        DllNode prev;
        DllNode next;

        public DllNode(String data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    public ReservedList() {
    }

    public void addToEnd(String data) {
        DllNode node = new DllNode(data);
        if (head == null) {
            head = node;
        } else {
            DllNode current = head;
            while (current.next != null) // keep checking till get the a empty node to se the new data
            {

                current = current.next;  // skip to the next to check another node

            }

            current.next = node; // 
            node.prev = current; // set the pervios node to cennect with them

        }

    }

    DllNode deleteLast() // to delete the last node in the list 
    {
        DllNode toDelete = head; // refrence the object which i want to delete
        if (head == null || head.next == null)// if it is empty , there is nothing to do. just return empty
        {
            head = null;
            return toDelete;
        }
        while (toDelete.next != null) // cehck statring from the second node till reached to the last node 
        {
            toDelete = toDelete.next; // go to the next 
        }
        return toDelete.prev.next = null; // delete the last node by removing 
        //the link from the next and previos and return it.
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

    //////////////////another methods///////////////////
    DllNode deleteAtStart() {
        DllNode toDelete = head; // create refrence variable
        if (head == null || head.next == null) // check if the head is empty or not
        {
            head = null; // means no data to just return null
            return toDelete;
        }
        /////it will come here if there is data in the head 
        head = head.next;// make the next node after the head become the head
        head.prev = null; // clear the previos link becuse no more previos will before the head
        return toDelete; // after finish the process - just return the new data with deletion of the first one 

    }

    DllNode deleteAfter(String data) {
        DllNode toDelete = head;// create refrence variable
        for (; toDelete != null; toDelete = toDelete.next) // loop over the list till find the not which i want to delete
        {
            if (toDelete.data == data) // check if the data which i want to delete is same the data which is refrenced 
            {
                toDelete = toDelete.next;
                break;
            }
        }
        if (toDelete != null) {
            if (toDelete.next != null) {
                toDelete.next.prev = toDelete.prev;
            }

            toDelete.prev.next = toDelete.next;

        }
        return toDelete; // return the new linked list with delete the specefic data from it 
    }

    public void delete(String key) { // to delete specifc node

        DllNode current = head; // create refrence variable
        while (current != null) {
            while (current.data != key) { // check if the current node equal the node which want to be deleted
                current = current.next; // is not equal so go to the next node ,to check it again by the loop

            }
            deleteAfter(current.prev.data); // call the method which has been created in this class to delet node by given the previos node
            break; // to break the node after finish, to avoid stackover flow
        }
    }



    public void addToStart(String data) {

        DllNode node = new DllNode(data);
        if (head == null) {
            head = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }

    }

    public int getCount() // to get the size of the linked list
    {
        DllNode current = head; // temporary 
        int count = 0;
        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }

    public String[] getItems() //to return all the data in array string 
    {
        DllNode node = head;
        String[] display = new String[getCount()];
        for (int index = 0; index < getCount(); index++) {
            display[index] = node.data;
            node = node.next;
        }

        return display;
    }

    public void show() // print whole the items
    {
        DllNode node = head;
        while (node != null && node.next != null) {
            System.out.println(node.data);
            node = node.next;
        }
        if (node != null) {
            System.out.println(node.data);
        }

    }
    
    
       public void save_in_file() // to save the reserved rooms to be booked  by the customer 
    {
       try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("ReservedList.txt"));            
            for (String ar : getItems()) {
                bw.write(ar + "\n");
            }
            bw.close();
        } catch (IOException ex) {
            System.out.println("Error88"+ex);
        }
    }
     

       
     public void read_from_file() // to get the data from the file and restore it the list
    {
         try {
             head=null; // clean the list 
            BufferedReader br = new BufferedReader(new FileReader("ReservedList.txt"));
            String read;
            while ((read = br.readLine())!= null) {  
                addToEnd(read);// add the data form the file to the list
                //System.out.println(read);
            }
            br.close();
        } catch (IOException ex) {
             System.out.println("Error99"+ex);
        }
    }
     

    
}
