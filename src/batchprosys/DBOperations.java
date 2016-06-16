/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchprosys;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JCheckBox;
import static org.hibernate.criterion.Expression.sql;


/**
 *
 * @author sumi
 */
public class DBOperations {
// get url from text file
// encript password fields
// dont show the text 
    String url = "jdbc:mysql://localhost:3306/batchproductionsystem?useUnicode=true&characterEncoding=utf-8";
    String username = "root";
    String password = "";
    private static String name;
    private static String email;
    private static String section;
    Connection con = null;
    Connection con1 = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private static String  UserID;
    private static String Password;
    
    
    //////// Using this method Admin add new users of the system. /////////
    public boolean addUser(User s) {
        try {
            System.out.println("p1*");
            con = (Connection) DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO user VALUES (?,?,?,?,?,?,?,?,?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            System.out.println("p2*");
            /*
            here set the new row.
            set releted data to the colunms
            taken from text fields.
            */
            pst.setString(1, s.getUserID());
            pst.setString(6, s.getUserType());
            pst.setString(2, s.getUsername());
            pst.setString(3, s.getPassword());
            pst.setString(7, s.getSection());
            pst.setInt(8, s.getAvailability());
            pst.setString(4, s.getInitialUsername());
            pst.setString(5, s.getInitialPassword());
            pst.setString(9, s.getEmail());
            //pst.setArray(i, null);
            System.out.println("p3*");
       
            pst.executeUpdate();
            
            System.out.println("p4");
            return true;

        } catch (Exception e) {
            System.out.println("exception --->" + e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    pst.close();
                }
            } catch (Exception e) {
            }

        }
    }
    
    ////// this method is used to check whether given user_ID and password are correct.
    int checkUsernamePassword(String userID,String password){
        try {
            con=DriverManager.getConnection(url, this.username, this.password); //make the db conncection
            String quary="SELECT User_ID,Password FROM user";
            pst=(PreparedStatement) con.prepareStatement(quary);
            rs=pst.executeQuery();
            /*
            following lines check the string 1 and string 2 match to the passed parameters.
            then return the integer.
            1 returns if both are matched to the given userID and password.
            0 returns miss matching
            2 returns if there are run time exceptions
            */
            while(rs.next()){
                if(rs.getString(1).equals(userID) & rs.getString(2).equals(password)){
                    UserID=userID;
                    Password=password;
                    return 1;
                }
            }return 0;
            
        } catch (SQLException ex) {
            System.out.println(ex);
            return 2;
        }finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }     
    }
    
    ////// this is worked for checking valid email address is given. ////////
    /*
    pass the string taken form txtEmail text field.
    check correcness and return boolean value.
    */
    public static boolean isEmailCorrect(String email){
        Pattern p=Pattern.compile("[a-z]+@[a-z]+\\.com");
        Matcher m=p.matcher(email);
        return m.matches();
    }
    
    ArrayList<User> getUser(){
        try {
            ArrayList<User> list=new ArrayList<User>();
            con=DriverManager.getConnection(url, username, password);
            String quary="SELECT * FROM user ";
            pst=(PreparedStatement) con.prepareStatement(quary);
            rs=pst.executeQuery();
            
            while(rs.next()){
                User user=new User();
                
                user.setUserID(rs.getString(1));
                user.setUserType(rs.getString(6));
                user.setUsername(rs.getString(2));
                user.setAvailability(rs.getInt(8));
                user.setSection(rs.getString(7));
                list.add(user);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }   
    }
    
    /////// 
    public boolean updateUser(User user){
        try {
            con=DriverManager.getConnection(url, username, password);
            String quary="UPDATE user SET Designation='"+user.getUserType()+"',Section='"+user.getSection()+"' WHERE User_ID='"+user.getUserID()+"'";
            pst=(PreparedStatement) con.prepareStatement(quary);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }   
    }
    
    public boolean updateUserAvailability(User user){
        try {
            con=DriverManager.getConnection(url, username, password);
            String quary="UPDATE user SET Availability='"+user.getAvailability()+"' WHERE User_ID='"+user.getUserID()+"'";
            pst=(PreparedStatement) con.prepareStatement(quary);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }   
    }
    /*ArrayList<User> getUserSL(){
        try {
            ArrayList<User> list=new ArrayList<User>();
            con=DriverManager.getConnection(url, username, password);
            String quary="SELECT * FROM user WHERE usertype='Section Leader'";
            pst=(PreparedStatement) con.prepareStatement(quary);
            rs=pst.executeQuery();
            
            while(rs.next()){
                User user=new User();
                user.setUserID(rs.getString(3));
                user.setUsername(rs.getString(1));
                user.setSection(rs.getString(5));
                list.add(user);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }   
    }*/

    int checkUserAvailability(String userID){
        try {
            con=DriverManager.getConnection(url, this.username, this.password);
            String quary="SELECT Availability FROM user WHERE User_Id='"+userID+"'";
            pst=(PreparedStatement) con.prepareStatement(quary);
            rs=pst.executeQuery();
            rs.next();
            if(rs.getInt(1)==1){
                return 1;
            }else{
                return 0;
            }
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
            return 2;
        }finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }     
    }
    
    
    int OpenInterface(String userID) {
        try {
            con = DriverManager.getConnection(url, this.username, this.password);
            String quary = "SELECT Designation FROM user WHERE User_Id='" + userID + "'";
            pst = (PreparedStatement) con.prepareStatement(quary);
            rs = pst.executeQuery();
            rs.next();
            if (null != rs.getString(1)) switch (rs.getString(1)) {
                case "Admin":
                    return 1;
                case "Production Manager":
                    return 2;
                case "Production Planner":
                    return 3;
                case "Section Leader":
                    return 4;
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            return 2;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return 0;
    }
    
    
    public static String getName(){
        return name;
    
    }
    public static String getEmail(){
        return email;
    
    }
    public static String getSection(){
        return section;
    
    }
    
    
    void getUserName(String userID){
        
        try {
            //System.out.println("adv");
            con=DriverManager.getConnection(url, this.username, this.password);
            String quary="SELECT User_Name,Email,Section FROM user WHERE User_Id='"+userID+"'";
            pst=(PreparedStatement) con.prepareStatement(quary);
            rs=pst.executeQuery();
            rs.next();
           
            name=rs.getString(1);
            email=rs.getString(2);
            section=rs.getString(3);
            
        } catch (SQLException ex) {
            System.out.println(ex);
            
        }finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }     
       
    }
    
    public Vector<Vector> getSectionWiseTime(){
        try {
            con=DriverManager.getConnection(url, username, password);
            //User currentUser=this.getCurrentUser();
            String quary="SELECT * FROM Section_Wise_Time ";
            pst=(PreparedStatement) con.prepareStatement(quary);
            rs=pst.executeQuery();
            
            Vector<Vector> table=new Vector<Vector>();
            while(rs.next()){
                Vector<Object> row=new Vector<Object>();
                row.addElement(rs.getString(1));
                row.addElement(rs.getFloat(2));
                row.addElement(rs.getFloat(3));
                row.addElement(rs.getFloat(4));
                row.addElement(rs.getFloat(5));
                row.addElement(rs.getFloat(6));
                row.addElement(rs.getFloat(7));
                row.addElement(rs.getFloat(8));
                
                table.add(row);
            }
            return table;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }   
    }
    
    
    public Vector<Vector> getSetupTime(){
        try {
            con=DriverManager.getConnection(url, username, password);
            //User currentUser=this.getCurrentUser();
            String quary="SELECT * FROM Setup_Time ";
            pst=(PreparedStatement) con.prepareStatement(quary);
            rs=pst.executeQuery();
            
            Vector<Vector> table=new Vector<Vector>();
            while(rs.next()){
                Vector<Object> row=new Vector<Object>();
                row.addElement(rs.getString(1));
                row.addElement(rs.getFloat(2));
                row.addElement(rs.getFloat(3));
                row.addElement(rs.getFloat(4));
                row.addElement(rs.getFloat(5));
                row.addElement(rs.getFloat(6));
                row.addElement(rs.getFloat(7));
                row.addElement(rs.getFloat(8));
                
                table.add(row);
            }
            return table;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }   
    }
    
    
    String[] getRowFromSetupTime(String tableRow){
        
        
         String[] Array=new String[7];
        try {
            con=DriverManager.getConnection(url, this.username, this.password);
            String quary="SELECT * FROM Setup_Time WHERE Order_Article='"+tableRow+"'";
            pst=(PreparedStatement) con.prepareStatement(quary);
            rs=pst.executeQuery();
            
            if(rs.next()){
                
                
                Array[0] = rs.getString("Pre_Work");
                Array[1] = rs.getString("Assembly_Line");
                Array[2] = rs.getString("Wave_Soldering");
                Array[3] = rs.getString("Quality_Control");
                Array[4] = rs.getString("Testing");
                Array[5] = rs.getString("Final");
                Array[6] = rs.getString("Packing");
               
                
                
            };
            
            return Array;
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
            return  null;
        }finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }     
        
    }
    
    String[] getRowFromSectionWiseTime(String tableRow){
        
        
         String[] Array=new String[7];
        try {
            con=DriverManager.getConnection(url, this.username, this.password);
            String quary="SELECT * FROM Section_Wise_Time WHERE Order_Article='"+tableRow+"'";
            pst=(PreparedStatement) con.prepareStatement(quary);
            rs=pst.executeQuery();
            
            if(rs.next()){
                
                
                Array[0] = rs.getString("Pre_Work");
                Array[1] = rs.getString("Assembly_Line");
                Array[2] = rs.getString("Wave_Soldering");
                Array[3] = rs.getString("Quality_Control");
                Array[4] = rs.getString("Testing");
                Array[5] = rs.getString("Final");
                Array[6] = rs.getString("Packing");
               
                
                
            };
            
            return Array;
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
            return  null;
        }finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }     
        
    }
    
    
    public boolean updateTimeSWT(Timings T){
        try {
            con=DriverManager.getConnection(url, username, password);
            String quary="UPDATE Section_Wise_Time SET Pre_Work='"+T.getPreWork()+"',Assembly_Line='"+T.getAssemblyLine()+"',Wave_Soldering='"+T.getWaveSoldering()+"',Quality_Control='"+T.getQualityControl()+"',Testing='"+T.getTesting()+"',Final='"+T.getFinal()+"',Packing='"+T.getPacking()+"' WHERE Order_Article='"+T.getOrderArticle()+"'";
            pst=(PreparedStatement) con.prepareStatement(quary);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }   
    }
    
    public boolean updateTimeST(Timings T){
        try {
            con=DriverManager.getConnection(url, username, password);
            String quary="UPDATE Setup_Time SET Pre_Work='"+T.getPreWork()+"',Assembly_Line='"+T.getAssemblyLine()+"',Wave_Soldering='"+T.getWaveSoldering()+"',Quality_Control='"+T.getQualityControl()+"',Testing='"+T.getTesting()+"',Final='"+T.getFinal()+"',Packing='"+T.getPacking()+"' WHERE Order_Article='"+T.getOrderArticle()+"'";
            pst=(PreparedStatement) con.prepareStatement(quary);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }   
    }
    
    public boolean InsertTimeST(Timings T) {//insert student data into the database
        try {
            System.out.println("p1");
            con = (Connection) DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO Setup_Time VALUES (?,?,?,?,?,?,?,?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            System.out.println("p2");
            
            pst.setString(1, T.getOrderArticle());
            pst.setString(2, T.getPreWork());
            pst.setString(3, T.getAssemblyLine());
            pst.setString(4, T.getWaveSoldering());
            pst.setString(5, T.getQualityControl());
            pst.setString(6, T.getTesting());
            pst.setString(7, T.getFinal());
            pst.setString(8,T.getPacking());
           
            //pst.setArray(i, null);
            System.out.println("p3");
       
            pst.executeUpdate();
            
            System.out.println("p4");
            return true;

        } catch (Exception e) {
            System.out.println("exception --->" + e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    pst.close();
                }
            } catch (Exception e) {
            }

        }
    }
    
    
    public boolean InsertTimeSWT(Timings T) {//insert student data into the database
        try {
            System.out.println("p1");
            con = (Connection) DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO Section_Wise_Time VALUES (?,?,?,?,?,?,?,?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            System.out.println("p2");
            
            pst.setString(1, T.getOrderArticle());
            pst.setString(2, T.getPreWork());
            pst.setString(3, T.getAssemblyLine());
            pst.setString(4, T.getWaveSoldering());
            pst.setString(5, T.getQualityControl());
            pst.setString(6, T.getTesting());
            pst.setString(7, T.getFinal());
            pst.setString(8,T.getPacking());
           
            //pst.setArray(i, null);
            System.out.println("p3");
       
            pst.executeUpdate();
            
            System.out.println("p4");
            return true;

        } catch (Exception e) {
            System.out.println("exception --->" + e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    pst.close();
                }
            } catch (Exception e) {
            }

        }
    }
    
    public void InsertToNotifynew(String order,int id,Date[] rowData) {//insert student data into the database
        try {
            //System.out.println("p1");
            java.sql.Timestamp timestamp;
            Calendar c = Calendar.getInstance();
            
            con1 = (Connection) DriverManager.getConnection(url, username, password);
            String query3 = "INSERT INTO Notifynew VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst3 = (PreparedStatement) con1.prepareStatement(query3);
                
                pst3.setString(1,order);
                pst3.setInt(2,id);
                pst3.setTimestamp(3, new java.sql.Timestamp(rowData[0].getTime()));
                pst3.setTimestamp(4, new java.sql.Timestamp(rowData[1].getTime()));
                pst3.setInt(5, 0);
                pst3.setTimestamp(6, new java.sql.Timestamp(rowData[2].getTime()));
                pst3.setTimestamp(7, new java.sql.Timestamp(rowData[3].getTime()));
                pst3.setInt(8, 0);
                pst3.setTimestamp(9, new java.sql.Timestamp(rowData[4].getTime()));
                pst3.setTimestamp(10, new java.sql.Timestamp(rowData[5].getTime()));
                pst3.setInt(11, 0);
                pst3.setTimestamp(12, new java.sql.Timestamp(rowData[6].getTime()));
                pst3.setTimestamp(13, new java.sql.Timestamp(rowData[7].getTime()));
                pst3.setInt(14, 0);
                pst3.setTimestamp(15, new java.sql.Timestamp(rowData[8].getTime()));
                pst3.setTimestamp(16, new java.sql.Timestamp(rowData[9].getTime()));
                pst3.setInt(17, 0);
                pst3.setTimestamp(18, new java.sql.Timestamp(rowData[10].getTime()));
                pst3.setTimestamp(19, new java.sql.Timestamp(rowData[11].getTime()));
                pst3.setInt(20, 0);
                pst3.setTimestamp(21, new java.sql.Timestamp(rowData[12].getTime()));
                pst3.setTimestamp(22, new java.sql.Timestamp(rowData[13].getTime()));
                pst3.setInt(23, 0);
                pst3.executeUpdate();

        } catch (Exception e) {
            System.out.println("exception --->" + e);
            
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    pst.close();
                }
            } catch (Exception e) {
            }

        }
    }
    
    public boolean DeleteTimeST(Timings T){
        
        try {
            //System.out.println("adv");
            con=DriverManager.getConnection(url, this.username, this.password);
            String quary="DELETE FROM Setup_Time WHERE Order_Article='"+T.getOrderArticle()+"'";
            pst=(PreparedStatement) con.prepareStatement(quary);
            pst.executeUpdate();
            
           
            return true;
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
            
        }finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }     
        return false;
       
    }
    
    public boolean DeleteTimeSWT(Timings T){
        
        try {
            //System.out.println("adv");
            con=DriverManager.getConnection(url, this.username, this.password);
            String quary="DELETE FROM Section_Wise_Time WHERE Order_Article='"+T.getOrderArticle()+"'";
            pst=(PreparedStatement) con.prepareStatement(quary);
            pst.executeUpdate();
            //rs.next();
           
            return true;
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
            
        }finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }     
        return false;
       
        }
    
    public boolean addOrderPP(ArrayList<ArrayList<String>> table) {//insert student data into the database
        try {
            System.out.println("add user-p1");
            
            System.out.println("add user-p2");
            for (int i = 0; i < table.size(); i++) {
                con = (Connection) DriverManager.getConnection(url, username, password);
                String query = "INSERT INTO Order_Plan (Oreder_Article,SAP_No,Order_Quantity)VALUES (?,?,?)";
                pst = (PreparedStatement) con.prepareStatement(query);
                
                pst.setString(1, table.get(i).get(1));
                pst.setString(2, table.get(i).get(0));
                pst.setString(3, table.get(i).get(2));
                //pst.setString(4, table.get(i).get(3));
                
               
                pst.executeUpdate();
            }
            
            //pst.setArray(i, null);
        System.out.println("add user-p3");
       
            
            
            System.out.println("add user-p4");
            return true;

        } catch (Exception e) {
            System.out.println("exception --->" + e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    pst.close();
                }
            } catch (Exception e) {
            }

        }
    }
    
    public boolean addBatchQuantity(int[] batchq,int[] articleno){//insert student data into the database
        try {
            System.out.println("q-p1");
            
            System.out.println("q-p2");
            for (int i = 0; i < articleno.length; i++) {
                con = (Connection) DriverManager.getConnection(url, username, password);
                String quary="UPDATE Order_Plan SET Batch_Quantity='"+batchq[i]+"' WHERE Oreder_Article='"+articleno[i]+"'";
                pst = (PreparedStatement) con.prepareStatement(quary);
                pst.executeUpdate();
                
            }
            
            
            System.out.println("q-p3");
       
            
            
            System.out.println("q-p4");
            return true;

        } catch (Exception e) {
            System.out.println("exception --->" + e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    pst.close();
                }
            } catch (Exception e) {
            }

        }
    }
    
    public boolean addNoOfBathches(int[] noofbatches,int[] articleno){//insert student data into the database
        try {
            System.out.println("q-p1");
            
            System.out.println("q-p2");
            for (int i = 0; i < articleno.length; i++) {
                con = (Connection) DriverManager.getConnection(url, username, password);
                String quary="UPDATE Order_Plan SET No_Of_Batches='"+noofbatches[i]+"' WHERE Oreder_Article='"+articleno[i]+"'";
                pst = (PreparedStatement) con.prepareStatement(quary);
                pst.executeUpdate();
                
            }
            
            
            System.out.println("q-p3");
       
            
            
            System.out.println("q-p4");
            return true;

        } catch (Exception e) {
            System.out.println("exception --->" + e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    pst.close();
                }
            } catch (Exception e) {
            }

        }
    }
    
    public Vector<Vector> getOrderPlan(){
        try {
            con=DriverManager.getConnection(url, username, password);
            //User currentUser=this.getCurrentUser();
            String quary="SELECT * FROM Order_Plan ";
            pst=(PreparedStatement) con.prepareStatement(quary);
            rs=pst.executeQuery();
            
            Vector<Vector> table=new Vector<Vector>();
            while(rs.next()){
                Vector<Object> row=new Vector<Object>();
                row.addElement(rs.getString(2));
                row.addElement(rs.getString(1));
                row.addElement(rs.getInt(3));
                row.addElement(rs.getInt(4));
                row.addElement(rs.getInt(5));
                
                table.add(row);
            }
            return table;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }   
    }
    
    public ArrayList<Vector<Vector>> getOrderPlanforSWorkAllocation() {
        try {
            con=DriverManager.getConnection(url, username, password);
            //User currentUser=this.getCurrentUser();
            String quary="SELECT * FROM Order_Plan ";
            pst=(PreparedStatement) con.prepareStatement(quary);
            rs=pst.executeQuery();
            ArrayList<Vector<Vector>> array=new ArrayList<Vector<Vector>>();
            Vector<Vector> pretable=new Vector<Vector>();
            Vector<Vector> altable=new Vector<Vector>();
            Vector<Vector> wstable=new Vector<Vector>();
            Vector<Vector> qctable=new Vector<Vector>();
            Vector<Vector> tesingtable=new Vector<Vector>();
            Vector<Vector> finaltable=new Vector<Vector>();
            Vector<Vector> packingtable=new Vector<Vector>();
            
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");            
            
            String myTime = "2016-01-11 07:30";
            //start1 = df.parse(myTime);
            Date start1=df.parse(myTime),start2=df.parse(myTime),start3=df.parse(myTime),start4=df.parse(myTime),start5=df.parse(myTime),start6=df.parse(myTime),start7=df.parse(myTime),end1=df.parse(myTime),end2 = df.parse(myTime),end3=df.parse(myTime),end4=df.parse(myTime),end5=df.parse(myTime),end6=df.parse(myTime),end7=df.parse(myTime);
            
            
            
            Vector<Vector<Object>> orderPlan=new Vector<Vector<Object>>();
            
            while(rs.next()){
                Vector<Object> row=new Vector<Object>();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getInt(3));
                row.add(rs.getInt(4));
                row.add(rs.getInt(3));//remain
                orderPlan.add(row);
            }
            int numOfCompleteArticle=0;
            int count=0;
            //System.out.println(orderPlan.size());
            
            while(numOfCompleteArticle < orderPlan.size()){
                numOfCompleteArticle=0;
                Date[] rowData=new Date[14];//Data store for use insert query
                String order="";
                for (Vector<Object> row : orderPlan) {
                    count += 1;
                    if((int)row.get(4)>0){
                        row.set(4, (int)row.get(4)-(int)row.get(3));
                        //System.out.println(row.get(2)+" - "+(int)row.get(4));
                        
                        String query2 = "SELECT * FROM setup_time WHERE Order_Article=" + row.get(0);
                        PreparedStatement pst1 = (PreparedStatement) con.prepareStatement(query2);
                        ResultSet rs1 = pst1.executeQuery();

                        String query3 = "SELECT * FROM section_wise_time WHERE Order_Article=" + row.get(0);
                        PreparedStatement pst2 = (PreparedStatement) con.prepareStatement(query3);
                        ResultSet rs2 = pst2.executeQuery();
                        rs1.next();
                        rs2.next();
                        
                        //row1.addElement(rs2);
                        Vector<Object> row1 = new Vector<Object>();
                        
                        row1.addElement(row.get(1));
                        row1.addElement(row.get(0));
                        row1.addElement(row.get(2));
                        row1.addElement(row.get(3));
                        //System.out.print(row.get(0).toString());
                        order=row.get(0).toString();
                        rowData[0]=start1;
                        
                        
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(start1);
                        //System.out.println(rs1.getDouble(2)+" "+rs2.getDouble(2)+" "+row.get(3));
                        if(orderPlan.size()>=count){
                            cal.add(Calendar.SECOND, (int) ((rs1.getDouble(2) + rs2.getDouble(2) *  (int)row.get(3)) * 60));
                        }else{
                            cal.add(Calendar.SECOND, (int) ((rs2.getDouble(2) *  (int)row.get(3)) * 60));
                        }
                        
                        
                        end1 = cal.getTime();
                        rowData[1]=end1;
                        
                        cal.setTime(start1);
                        if (rs2.getDouble(2) <= rs2.getDouble(3)) {
                            cal.add(Calendar.SECOND, (int) ((rs2.getDouble(2)) * 60));
                            start2 = cal.getTime();
                        } else {
                            cal.setTime(end1);
                            //int gap=-1*(rs2.getDouble(3)*rs.getDouble(4))+;
                            cal.add(Calendar.SECOND, -1 * (int) ((rs2.getDouble(3) * (int) row.get(3)) * 60));
                            cal.add(Calendar.SECOND, (int) ((rs2.getDouble(3)) * 60));
                            start2 = cal.getTime();
                        }
                        rowData[2]=start2;
                        row1.addElement(row.get(4));
                        row1.addElement(df.format(start1));
                        row1.addElement(df.format(end1));
                        //row1.addElement(new JCheckBox());
                        
                        pretable.addElement(row1);
                        start1 = end1;

                        Vector<Object> row2 = new Vector<Object>();
                        row2.addElement(row.get(1));
                        row2.addElement(row.get(0));
                        row2.addElement(row.get(2));
                        row2.addElement(row.get(3));

                        //start=end;
                        if (count > 1 && !start2.after(end2)) {
                            start2 = end2;
                        }
                        cal.setTime(start2);
                        if(orderPlan.size()>=count){
                            cal.add(Calendar.SECOND, (int) ((rs1.getDouble(3) + rs2.getDouble(3) * (int)row.get(3)) * 60));
                        }else{
                            cal.add(Calendar.SECOND, (int) ((rs2.getDouble(3) * (int)row.get(3)) * 60));
                        }
                        
                        end2 = cal.getTime();
                        rowData[3]=end2;
                        
                        cal.setTime(start2);
                        if (rs2.getDouble(3) <= rs2.getDouble(4)) {
                            cal.add(Calendar.SECOND, (int) ((rs2.getDouble(3)) * 60));
                            start3 = cal.getTime();
                        } else {
                            cal.setTime(end2);
                            //int gap=-1*(rs2.getDouble(3)*rs.getDouble(4))+;
                            cal.add(Calendar.SECOND, -1 * (int) ((rs2.getDouble(4) * (int) row.get(3)) * 60));
                            cal.add(Calendar.SECOND, (int) ((rs2.getDouble(4)) * 60));
                            start3 = cal.getTime();
                        }
                        rowData[4]=start3;
                        /*cal.add(Calendar.MINUTE, (int)(rs1.getDouble(3)+rs2.getDouble(3)*rs.getDouble(4)));
                         end2=cal.getTime();
                         cal.setTime(start2);
                         cal.add(Calendar.MINUTE, (int)(rs2.getDouble(3)));
                         start3=cal.getTime();*/
                        row2.addElement(row.get(4));
                        row2.addElement(df.format(start2));
                        row2.addElement(df.format(end2));
                        //row2.addElement(new JCheckBox());
                        //row2.addElement(false);
                        altable.addElement(row2);
                        //System.out.println(row1);

                        Vector<Object> row3 = new Vector<Object>();
                        row3.addElement(row.get(1));
                        row3.addElement(row.get(0));
                        row3.addElement(row.get(2));
                        row3.addElement(row.get(3));

                        //start=end;
                        if (count > 1 && !start3.after(end3)) {
                            start3 = end3;
                        }
                        cal.setTime(start3);
                        if(orderPlan.size()>=count){
                            cal.add(Calendar.SECOND, (int) ((rs1.getDouble(4) + rs2.getDouble(4) * (int) row.get(3)) * 60));
                        }else{
                            cal.add(Calendar.SECOND, (int) ((rs2.getDouble(4) * (int) row.get(3)) * 60));
                        }
                        
                        end3 = cal.getTime();
                        rowData[5]=end3;
                        
                        cal.setTime(start3);
                        if (rs2.getDouble(4) <= rs2.getDouble(5)) {
                            cal.add(Calendar.SECOND, (int) ((rs2.getDouble(4)) * 60));
                            start4 = cal.getTime();
                        } else {
                            cal.setTime(end3);
                            //int gap=-1*(rs2.getDouble(3)*rs.getDouble(4))+;
                            cal.add(Calendar.SECOND, -1 * (int) ((rs2.getDouble(5) * (int) row.get(3)) * 60));
                            cal.add(Calendar.SECOND, (int) ((rs2.getDouble(5)) * 60));
                            start4 = cal.getTime();
                        }
                        rowData[6]=start4;
                        /*
                         cal.add(Calendar.MINUTE, (int)(rs1.getDouble(4)+rs2.getDouble(4)*rs.getDouble(4)));
                         end3=cal.getTime();
                         cal.setTime(start3);
                         cal.add(Calendar.MINUTE, (int)(rs2.getDouble(4)));
                         start4=cal.getTime();*/
                        row3.addElement(row.get(4));
                        row3.addElement(df.format(start3));
                        row3.addElement(df.format(end3));
                        //row3.addElement(new JCheckBox());
                        wstable.addElement(row3);
                        //System.out.println(row1);

                        Vector<Object> row4 = new Vector<Object>();
                        row4.addElement(row.get(1));
                        row4.addElement(row.get(0));
                        row4.addElement(row.get(2));
                        row4.addElement(row.get(3));

                        //start=end;
                        if (count > 1 && !start4.after(end4)) {
                            
                            start4 = end4;
                            //System.out.println(start4);
                        }
                        cal.setTime(start4);
                        if(orderPlan.size()>=count){
                            cal.add(Calendar.SECOND, (int) ((rs1.getDouble(5) + rs2.getDouble(5) * (int) row.get(3)) * 60));
                        }else{
                            cal.add(Calendar.SECOND, (int) ((rs2.getDouble(5) * (int) row.get(3)) * 60));
                        }
                        
                        end4 = cal.getTime();
                        rowData[7]=end4;
                        
                        cal.setTime(start4);
                        if (rs2.getDouble(5) <= rs2.getDouble(6)) {
                            cal.add(Calendar.SECOND, (int) ((rs2.getDouble(5)) * 60));
                            start5 = cal.getTime();
                        } else {
                            cal.setTime(end4);
                            //int gap=-1*(rs2.getDouble(3)*rs.getDouble(4))+;
                            cal.add(Calendar.SECOND, -1 * (int) ((rs2.getDouble(6) * (int) row.get(3)) * 60));
                            cal.add(Calendar.SECOND, (int) ((rs2.getDouble(6)) * 60));
                            start5 = cal.getTime();
                        }
                        
                        rowData[8]=start5;
                        /*
                         cal.add(Calendar.MINUTE, (int)(rs1.getDouble(5)+rs2.getDouble(5)*rs.getDouble(4)));
                         end4=cal.getTime();
                         cal.setTime(start4);
                         cal.add(Calendar.MINUTE, (int)(rs2.getDouble(5)));
                         start5=cal.getTime();*/
                        row4.addElement(row.get(4));
                        row4.addElement(df.format(start4));
                        row4.addElement(df.format(end4));
                        //row4.addElement(new JCheckBox());
                        qctable.addElement(row4);
                        //System.out.println(row1);

                        Vector<Object> row5 = new Vector<Object>();
                        row5.addElement(row.get(1));
                        row5.addElement(row.get(0));
                        row5.addElement(row.get(2));
                        row5.addElement(row.get(3));

                        //start=end;
                        if (count > 1 && !start5.after(end5)) {
                            start5 = end5;
                        }
                        cal.setTime(start5);
                        if(orderPlan.size()>=count){
                            cal.add(Calendar.SECOND, (int) ((rs1.getDouble(6) + rs2.getDouble(6) * (int) row.get(3)) * 60));
                        }else{
                            cal.add(Calendar.SECOND, (int) ((rs2.getDouble(6) * (int) row.get(3)) * 60));
                        }
                        
                        end5 = cal.getTime();
                        rowData[9]=end5;
                        
                        cal.setTime(start5);
                        if (rs2.getDouble(6) <= rs2.getDouble(7)) {
                            cal.add(Calendar.SECOND, (int) ((rs2.getDouble(6)) * 60));
                            start6 = cal.getTime();
                        } else {
                            cal.setTime(end5);
                            //int gap=-1*(rs2.getDouble(3)*rs.getDouble(4))+;
                            cal.add(Calendar.SECOND, -1 * (int) ((rs2.getDouble(7) * (int) row.get(3)) * 60));
                            cal.add(Calendar.SECOND, (int) ((rs2.getDouble(7)) * 60));
                            start6 = cal.getTime();
                        }
                        rowData[10]=start6;
                        /*
                         cal.add(Calendar.MINUTE, (int)(rs1.getDouble(6)+rs2.getDouble(6)*rs.getDouble(4)));
                         end5=cal.getTime();
                         cal.setTime(start5);
                         cal.add(Calendar.MINUTE, (int)(rs2.getDouble(6)));
                         start6=cal.getTime();*/
                        row5.addElement(row.get(4));
                        row5.addElement(df.format(start5));
                        row5.addElement(df.format(end5));
                        //row5.addElement(new JCheckBox());
                        tesingtable.addElement(row5);
                        //System.out.println(row1);

                        Vector<Object> row6 = new Vector<Object>();
                        row6.addElement(row.get(1));
                        row6.addElement(row.get(0));
                        row6.addElement(row.get(2));
                        row6.addElement(row.get(3));

                        //start=end;
                        if (count > 1 && !start6.after(end6)) {
                            start6 = end6;
                        }
                        cal.setTime(start6);
                        if(orderPlan.size()>=count){
                            cal.add(Calendar.SECOND, (int) ((rs1.getDouble(7) + rs2.getDouble(7) * (int) row.get(3)) * 60));
                        }else{
                            cal.add(Calendar.SECOND, (int) ((rs2.getDouble(7) * (int) row.get(3)) * 60));
                        }
                        
                        end6 = cal.getTime();
                        rowData[11]=end6;
                        
                        cal.setTime(start6);
                        if (rs2.getDouble(7) <= rs2.getDouble(8)) {
                            cal.add(Calendar.SECOND, (int) ((rs2.getDouble(7)) * 60));
                            start7 = cal.getTime();
                        } else {
                            cal.setTime(end6);
                            //int gap=-1*(rs2.getDouble(3)*rs.getDouble(4))+;
                            cal.add(Calendar.SECOND, -1 * (int) ((rs2.getDouble(8) * (int) row.get(3)) * 60));
                            cal.add(Calendar.SECOND, (int) ((rs2.getDouble(8)) * 60));
                            start7 = cal.getTime();
                        }
                        rowData[12]=start7;
                        /*
                         cal.add(Calendar.MINUTE, (int)(rs1.getDouble(7)+rs2.getDouble(7)*rs.getDouble(4)));
                         end6=cal.getTime();
                         cal.setTime(start6);
                         cal.add(Calendar.MINUTE, (int)(rs2.getDouble(7)));
                         start7=cal.getTime();*/
                        row6.addElement(row.get(4));
                        row6.addElement(df.format(start6));
                        row6.addElement(df.format(end6));
                        //row6.addElement(new JCheckBox());
                        finaltable.addElement(row6);
                        //System.out.println(row1);

                        Vector<Object> row7 = new Vector<Object>();
                        row7.addElement(row.get(1));
                        row7.addElement(row.get(0));
                        row7.addElement(row.get(2));
                        row7.addElement(row.get(3));

                        //start=end;
                        if (count > 1 && !start7.after(end7)) {
                            start7 = end7;
                        }
                        cal.setTime(start7);
                        if(orderPlan.size()>=count){
                            cal.add(Calendar.SECOND, (int) ((rs1.getDouble(8) + rs2.getDouble(8) *(int)row.get(3)) * 60));
                        }else{
                            cal.add(Calendar.SECOND, (int) ((rs2.getDouble(8) *(int)row.get(3)) * 60));
                        }
                        
                        end7 = cal.getTime();
                        rowData[13]=end7;

                        /*cal.setTime(start6);
                         if(rs2.getDouble(7)<=rs2.getDouble(8)){
                         cal.add(Calendar.SECOND, (int)((rs2.getDouble(7))*60));
                         start7=cal.getTime();
                         }else{
                         cal.setTime(end6);
                         //int gap=-1*(rs2.getDouble(3)*rs.getDouble(4))+;
                         cal.add(Calendar.SECOND,-1*(int)((rs2.getDouble(8)*rs.getDouble(4))*60));
                         cal.add(Calendar.SECOND, (int)((rs2.getDouble(8))*60));
                         start7=cal.getTime();
                         }
                
                         cal.add(Calendar.MINUTE, (int)(rs1.getDouble(8)+rs2.getDouble(8)*rs.getDouble(4)));
                         end7=cal.getTime();
                         cal.setTime(start7);
                         cal.add(Calendar.MINUTE, (int)(rs2.getDouble(8)));
                         //start7=cal.getTime();*/
                        row7.addElement(row.get(4));
                        row7.addElement(df.format(start7));
                        row7.addElement(df.format(end7));
                        //row7.addElement(new JCheckBox());
                        packingtable.addElement(row7);
                        
                    }else{
                        numOfCompleteArticle +=1;
                    }
                    //System.out.println(numOfCompleteArticle);
                    //System.out.println("insert");
                    InsertToNotifynew(order,count,rowData);
                    //System.out.println("insert end");
                }
                
                
                //+new java.sql.Date(rowData[1].getTime())+","+0+","+new java.sql.Date(rowData[2].getTime())+","+new java.sql.Date(rowData[3].getTime())+","+0+")";
                //
            }
            System.out.println("out for");
            array.add(pretable);
            array.add(altable);
            array.add(wstable);
            array.add(qctable);
            array.add(tesingtable);
            array.add(finaltable);
            array.add(packingtable);
            return array;
                  
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
        finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }   
    }
    
    public boolean InsertTimeDurationAndMechines(Section_Details D) {// insert data to the the database
        try {
            //System.out.println("p1");
            con = (Connection) DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO section_details VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            //System.out.println("p2");
            pst.setInt(1,D.getId());
            pst.setInt(2, D.getDuration());
            pst.setInt(3, D.getOpw());
            pst.setInt(4, D.getOal());
            pst.setInt(5, D.getOws());
            pst.setInt(6, D.getOqc());
            pst.setInt(7, D.getOtesting());
            pst.setInt(8, D.getOFinal());
            pst.setInt(9, D.getOpacking());
            pst.setInt(10, D.getMpw());
            pst.setInt(11, D.getMal());
            pst.setInt(12, D.getMws());
            pst.setInt(13, D.getMqc());
            pst.setInt(14, D.getMtesting());
            pst.setInt(15, D.getMFinal());
            pst.setInt(16, D.getMpacking());
           
            //pst.setArray(i, null);
            System.out.println("p3");
       
            pst.executeUpdate();
            
            System.out.println("p4");
            return true;

        } catch (Exception e) {
            System.out.println("exception --->" + e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    pst.close();
                }
            } catch (Exception e) {
            }

        }
    }
    public static String getUserID() {
        return UserID;
    }
    
    public boolean updateUserProfile(User user) {
        try {
            System.out.println("hadunaganima-"+user.getUsername());
            System.out.println("userid-"+user.getUserID());
            con = DriverManager.getConnection(url, username, password);
            String quary = "UPDATE user SET User_Name='" + user.getUsername() + "',Password='" + user.getPassword() + "',Email='" + user.getEmail() + "' WHERE User_ID='" + user.getUserID() + "'";
            
            pst = (PreparedStatement) con.prepareStatement(quary);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        } finally {

            try {
                if (pst != null) {
                    pst.close();
                }

                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }
    
    public static String getPassword() {
        return Password;
    }
    
    public boolean DeleteOrderPlan(){
        
        try {
            //System.out.println("adv");
            con=DriverManager.getConnection(url, this.username, this.password);
            String quary="DELETE FROM Order_Plan";
            pst=(PreparedStatement) con.prepareStatement(quary);
            pst.executeUpdate();
            
           
            return true;
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
            
        }finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }     
        return false;
       
    }
    
    
    public boolean Deletesectiondetails(){
        
        try {
            //System.out.println("adv");
            con=DriverManager.getConnection(url, this.username, this.password);
            String quary="DELETE FROM section_details";
            pst=(PreparedStatement) con.prepareStatement(quary);
            pst.executeUpdate();
            
           
            return true;
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
            
        }finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }     
        return false;
       
    }
    
    String[] getsectiondetails(){
        
        
         String[] Array=new String[15];
        try {
            con=DriverManager.getConnection(url, this.username, this.password);
            String quary="SELECT * FROM section_details ";
            pst=(PreparedStatement) con.prepareStatement(quary);
            rs=pst.executeQuery();
            
            if(rs.next()){
                
                
                Array[0] = rs.getString("duration");
                Array[1] = rs.getString("opw");
                Array[2] = rs.getString("oal");
                Array[3] = rs.getString("ows");
                Array[4] = rs.getString("oqc");
                Array[5] = rs.getString("otesting");
                Array[6] = rs.getString("ofinal");
                Array[7] = rs.getString("opacking");
                Array[8] = rs.getString("mpw");
                Array[9] = rs.getString("mal");
                Array[10] = rs.getString("mws");
                Array[11] = rs.getString("mqc");
                Array[12] = rs.getString("mtesting");
                Array[13] = rs.getString("mfinal");
                Array[14] = rs.getString("mpacking");
                
               
                
                
            };
            
            return Array;
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
            return  null;
        }finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }     
        
    }
    
    String[] getSectionWiseTimeOrder_Article(){
        
        
         String[] Array=new String[150];
        try {
            con=DriverManager.getConnection(url, this.username, this.password);
            String quary="SELECT Order_Article FROM section_wise_time ";
            pst=(PreparedStatement) con.prepareStatement(quary);
            rs=pst.executeQuery();
            int i=0;
            while(rs.next()){
                Array[i] = rs.getString(1);
                i++;
            };
            //for(String x:Array){
            //    System.out.println(x);
            //}
            return Array;
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
            return  null;
        }finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }     
        
    }
}

