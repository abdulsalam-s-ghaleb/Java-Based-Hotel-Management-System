/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inti_hotel;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;


/**
 *
 * @author NitroQ
 */
public class Login {

    private int line;
    private String filename = "Users.txt";
    private String userN, pass;
   

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUserN() {
        return userN;
    }

    public void setUserN(String userN) {
        this.userN = userN;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void increcsLine() {
        this.line++;
    }

    public void createFile() {
        try {
            FileReader fread = new FileReader(filename);//this to check if file can be read and exists
            FileReader freadlogrecord = new FileReader("login_record.txt");//this to check if file can be read and exists

            System.out.println("file exists!");
        } catch (FileNotFoundException ex) {
            try {
                FileWriter fwriter = new FileWriter(filename);//create file to write 
                FileWriter fwriterlogrecord = new FileWriter("login_record.txt");//create file to write 
                System.out.println("File created");
                //add new users
                adduser("ali", "123", "admin");
                adduser("modammed", "12345", "customer");
                adduser("khaled", "a123", "admin");
                adduser("radman", "123", "admin");
                adduser("salam", "123", "admin");
                adduser("ss", "123", "customer");
            } catch (IOException ex1) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

    }

    public void adduser(String username, String password, String role) {
         countLines();
        try {
            RandomAccessFile ranaccfile = new RandomAccessFile(filename, "rw");
            for (int i = 0; i < getLine(); i++) {
                ranaccfile.readLine();
            }

            if (getLine() > 0) {
                ranaccfile.writeBytes("\r\n");
                ranaccfile.writeBytes("\r\n");
            }
            ranaccfile.writeBytes("Username:" + username + "\r\n");
            ranaccfile.writeBytes("Password:" + encrypt(password) + "\r\n");
            ranaccfile.writeBytes("Role:" + role + "\r\n");
            
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public boolean logic(String usr, String pswd) {
        try {
            RandomAccessFile raf = new RandomAccessFile(filename, "rw");
            boolean value = false;
            countLines();
            //System.out.println(getLine());
            for (int i = 0; i < getLine(); i += 5) {
//                System.out.println("count " + i);

                userN = raf.readLine().substring(9);
                pass = raf.readLine().substring(9);
                String role = raf.readLine().substring(5);
                String encryptpass = encrypt(pswd);
//                System.out.println("userN ==" + userN+ "  pass ==" +pass + " role "+role);
//                 System.out.println(" pass ==>>>" +encryptpass);
                if (usr.equals(userN) && encryptpass.equals(pass)) {
                    value = true;
                    break;
                } else if (i == (getLine() - 5)) {
                    value = false;
                    break;
                }
                for (int k = 1; k <= 2; k++) {
                    raf.readLine();
                }

            }
            return value;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String[] getusers() {
        try {
            RandomAccessFile raf = new RandomAccessFile(filename, "rw");

            countLines();
            //System.out.println(getLine());
            int index = 0;
            String[] value = new String[getLine()];
            for (int i = 0; i < getLine(); i += 5) {
//                System.out.println("count " + i);

                String userfile = raf.readLine().substring(9);
                String passfile = raf.readLine().substring(9);
                String rolefile = raf.readLine().substring(5);

//                 System.out.println("userN ==" + userN+ "  pass ==" +pass + " role "+role);
//                 System.out.println(" pass ==>>>" +hashpass);
                if (!userfile.equals("") && !passfile.equals("") && !rolefile.equals("")) {
                    value[index] = userfile + "," + passfile + "," + rolefile;
//                    System.out.println("here1  " + value[index]);
                } else if (i == (getLine() - 5)) {                   
                    break;
                }
                for (int k = 1; k <= 2; k++) {
                    raf.readLine();
                }
                index++;
            }            
            return value;            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean get_role(String usr, String pswd) {

        boolean value = false;
        try {
            RandomAccessFile raccFile = new RandomAccessFile(filename, "rw");

            countLines();
            for (int i = 0; i < getLine(); i += 5) {
//                System.out.println("countrole>> " + i);

                String userr = raccFile.readLine().substring(9);
                String passww = raccFile.readLine().substring(9);
                String role = raccFile.readLine().substring(5);
                pswd = encrypt(pswd);
                //System.out.println(" role "+role);
                if (usr.equals(userr) && pswd.equals(passww) && role.equals("admin")) {
                    value = true;
                    // System.out.println("here1  "+value);
                    break;
                } else if (usr.equals(userr) && pswd.equals(passww) && role.equals("customer")) {
                    value = false;
                    break;
                } else if (i == (getLine() - 5)) {
                    value = false;
                    break;
                }

                for (int k = 1; k <= 2; k++) {
                    raccFile.readLine();
                }
            }
            System.out.println(value);
            return value;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }

    public void countLines() {
        try {
            setLine(0);
            RandomAccessFile ranaccfile = new RandomAccessFile(filename, "rw");
            for (int i = 0; ranaccfile.readLine() != null; i++) {
                increcsLine();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void login_record(String username, String password, Date logintime, boolean role) {
        try {
            int linelog = 0;
            RandomAccessFile ranaccfile = new RandomAccessFile("login_record.txt", "rw");
            for (int i = 0; ranaccfile.readLine() != null; i++) {
                linelog++;
            }
//            for (int i = 0; i < linelog; i++) {
//                ranaccfile.readLine();
//            } 
            String roles = "";
            if (role == true) {
                roles = "admin";
            } else {
                roles = "customer";
            }

            ranaccfile.writeBytes(username + "," + encrypt(password) + "," + roles + "," + logintime + "\n");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String[] get_login_record() {
        try {
            int linelog = 0;
            RandomAccessFile ranaccfile = new RandomAccessFile("login_record.txt", "rw");
            for (int i = 0; ranaccfile.readLine() != null; i++) {
                linelog++;

            }
            String[] data = new String[linelog];
            RandomAccessFile read = new RandomAccessFile("login_record.txt", "rw");
            for (int i = 0; i < linelog; i++) {
                data[i] = read.readLine();
            }

            return data;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String encrypt(String passToHash) {
        String hashPassword = null;
        try {
            MessageDigest messd = MessageDigest.getInstance("SHA-1"); //encrpt passwords to sha-1         
            byte[] bytes = messd.digest(passToHash.getBytes());
            StringBuilder sbuild = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sbuild.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashPassword = sbuild.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashPassword;
    }


}
