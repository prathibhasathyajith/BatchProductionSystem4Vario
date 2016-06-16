/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchprosys;

/**
 *
 * @author MihiranPriyanja
 */
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
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JCheckBox;
import static org.hibernate.criterion.Expression.sql;

public class Dbops {

    static String url = "jdbc:mysql://localhost:3306/batchproductionsystem?useUnicode=true&characterEncoding=utf-8";
    static String username = "root";
    static String password = "";
    private static String name;
    static Connection con = null;
    //static Connection con1 = null;
    //static Connection con2 = null;
    static PreparedStatement pst = null;
    static PreparedStatement pst1 = null;
    static PreparedStatement pst2 = null;
    static ResultSet rs = null;
    static ResultSet rs1 = null;
    //private static String UserID;
    //private static String Password;
    //flush the notifinew table after generate the sectional work allocation
    public boolean flushNoti() {//insert student data into the database
        try {
            con = (Connection) DriverManager.getConnection(url, username, password);
            String query = "TRUNCATE TABLE notifynew";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.executeUpdate();

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
    //
    public boolean pwComplete(String order) {//insert student data into the database
        try {
            con = (Connection) DriverManager.getConnection(url, username, password);
            String query = "UPDATE notifynew SET PW_complete=1 WHERE Order_article='"+order+"'";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.executeUpdate();
            
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
    //flush the notification table after generate the sectional work allocation
    public boolean flushNotification() {//insert student data into the database
        try {
            con = (Connection) DriverManager.getConnection(url, username, password);
            String query = "DELETE  FROM notification WHERE NOT id=1";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.executeUpdate();

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
    // return the arry containing not completed orders of prework section
    public static ArrayList<String> pwnotify() {
        try {
            con = DriverManager.getConnection(url, username, password);
            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());

            String query = "SELECT Order_article,PW_end,id FROM notifynew WHERE PW_end < '" + now + "' AND PW_complete=0";
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            //Vector<Vector> table = new Vector<Vector>();
            SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
            ArrayList<String> array = new ArrayList<String>();
            while (rs.next()) {
                array.add("The Order article " + rs.getString(1) + " was not completed at the scheduled time " + sf.format(rs.getTimestamp(2)) + " by the section Pre work");

                String query1 = "UPDATE notifynew SET PW_complete=1 WHERE id=" + rs.getInt(3);
                pst1 = (PreparedStatement) con.prepareStatement(query1);
                pst1.executeUpdate();

                String query2 = "INSERT INTO notification VALUES(null,'" + rs.getString(1) + "','Pre Work','" + rs.getTimestamp(2) + "')";
                pst2 = (PreparedStatement) con.prepareStatement(query2);
                pst2.executeUpdate();
            }
            return array;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
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
    // return the arry containing not completed orders of AL section
    public static ArrayList<String> alnotify() {
        try {
            con = DriverManager.getConnection(url, username, password);
            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());

            String query = "SELECT Order_article,AL_end,id FROM notifynew WHERE AL_end < '" + now + "' AND AL_complete=0";
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            //Vector<Vector> table = new Vector<Vector>();
            SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
            ArrayList<String> array = new ArrayList<String>();
            while (rs.next()) {
                array.add("The Order article " + rs.getString(1) + " was not completed at the scheduled time " + sf.format(rs.getTimestamp(2)) + " by the section Assembly Line");

                String query1 = "UPDATE notifynew SET AL_complete=1 WHERE id=" + rs.getInt(3);
                pst1 = (PreparedStatement) con.prepareStatement(query1);
                pst1.executeUpdate();

                String query2 = "INSERT INTO notification VALUES(null,'" + rs.getString(1) + "','Assembly Line','" + rs.getTimestamp(2) + "')";
                pst2 = (PreparedStatement) con.prepareStatement(query2);
                pst2.executeUpdate();
            }
            return array;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
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
    // return the arry containing not completed orders of ws section
    public static ArrayList<String> wsnotify() {
        try {
            con = DriverManager.getConnection(url, username, password);
            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());

            String query = "SELECT Order_article,WS_end,id FROM notifynew WHERE WS_end < '" + now + "' AND WS_complete=0";
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            //Vector<Vector> table = new Vector<Vector>();
            SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
            ArrayList<String> array = new ArrayList<String>();
            while (rs.next()) {
                array.add("The Order article " + rs.getString(1) + " was not completed at the scheduled time " + sf.format(rs.getTimestamp(2)) + " by the section Wave Soldering");

                String query1 = "UPDATE notifynew SET WS_complete=1 WHERE id=" + rs.getInt(3);
                pst1 = (PreparedStatement) con.prepareStatement(query1);
                pst1.executeUpdate();

                String query2 = "INSERT INTO notification VALUES(null,'" + rs.getString(1) + "','Wave Soldering','" + rs.getTimestamp(2) + "')";
                pst2 = (PreparedStatement) con.prepareStatement(query2);
                pst2.executeUpdate();
            }
            return array;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
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
    // return the arry containing not completed orders of qc section
    public static ArrayList<String> QCnotify() {
        try {
            con = DriverManager.getConnection(url, username, password);
            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());

            String query = "SELECT Order_article,QC_end,id FROM notifynew WHERE QC_end < '" + now + "' AND QC_complete=0";
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            //Vector<Vector> table = new Vector<Vector>();
            SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
            ArrayList<String> array = new ArrayList<String>();
            while (rs.next()) {
            array.add("The Order article " + rs.getString(1) + " was not completed at the scheduled time " + sf.format(rs.getTimestamp(2)) + " by the section Quality Checking");


                String query1 = "UPDATE notifynew SET QC_complete=1 WHERE id=" + rs.getInt(3);
                pst1 = (PreparedStatement) con.prepareStatement(query1);
                pst1.executeUpdate();

                String query2 = "INSERT INTO notification VALUES(null,'" + rs.getString(1) + "','Quality Checking','" + rs.getTimestamp(2) + "')";
                pst2 = (PreparedStatement) con.prepareStatement(query2);
                pst2.executeUpdate();
            }
            return array;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
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
    // return the arry containing not completed orders of testing section
    public static ArrayList<String> testnotify() {
        try {
            con = DriverManager.getConnection(url, username, password);
            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());

            String query = "SELECT Order_article,Test_end,id FROM notifynew WHERE Test_end < '" + now + "' AND Test_complete=0";
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            //Vector<Vector> table = new Vector<Vector>();
            SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
            ArrayList<String> array = new ArrayList<String>();
            while (rs.next()) {
                array.add("The Order article " + rs.getString(1) + " was not completed at the scheduled time " + sf.format(rs.getTimestamp(2)) + " by the section Test");


                String query1 = "UPDATE notifynew SET Test_complete=1 WHERE id=" + rs.getInt(3);
                pst1 = (PreparedStatement) con.prepareStatement(query1);
                pst1.executeUpdate();

                String query2 = "INSERT INTO notification VALUES(null,'" + rs.getString(1) + "','Test','" + rs.getTimestamp(2) + "')";
                pst2 = (PreparedStatement) con.prepareStatement(query2);
                pst2.executeUpdate();
            }
            return array;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
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
    // return the arry containing not completed orders of final section
    public static ArrayList<String> finalnotify() {
        try {
            con = DriverManager.getConnection(url, username, password);
            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());

            String query = "SELECT Order_article,Final_end,id FROM notifynew WHERE Final_end < '" + now + "' AND Final_complete=0";
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            //Vector<Vector> table = new Vector<Vector>();
            SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
            ArrayList<String> array = new ArrayList<String>();
            while (rs.next()) {
                                array.add("The Order article " + rs.getString(1) + " was not completed at the scheduled time " + sf.format(rs.getTimestamp(2)) + " by the section Final");


                String query1 = "UPDATE notifynew SET Final_complete=1 WHERE id=" + rs.getInt(3);
                pst1 = (PreparedStatement) con.prepareStatement(query1);
                pst1.executeUpdate();

                String query2 = "INSERT INTO notification VALUES(null,'" + rs.getString(1) + "','Final','" + rs.getTimestamp(2) + "')";
                pst2 = (PreparedStatement) con.prepareStatement(query2);
                pst2.executeUpdate();
            }
            return array;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
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
    // return the arry containing not completed orders of packing section
    public static ArrayList<String> packingnotify() {
        try {
            con = DriverManager.getConnection(url, username, password);
            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());

            String query = "SELECT Order_article,Packing_end,id FROM notifynew WHERE Packing_end < '" + now + "' AND Packing_complete=0";
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            //Vector<Vector> table = new Vector<Vector>();
            SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
            ArrayList<String> array = new ArrayList<String>();
            while (rs.next()) {
                array.add("The Order article " + rs.getString(1) + " was not completed at the scheduled time " + sf.format(rs.getTimestamp(2)) + " by the section Packing");


                String query1 = "UPDATE notifynew SET Packing_complete=1 WHERE id=" + rs.getInt(3);
                pst1 = (PreparedStatement) con.prepareStatement(query1);
                pst1.executeUpdate();

                String query2 = "INSERT INTO notification VALUES(null,'" + rs.getString(1) + "','Packing','" + rs.getTimestamp(2) + "')";
                pst2 = (PreparedStatement) con.prepareStatement(query2);
                pst2.executeUpdate();
            }
            return array;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
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
    //pass the data to notification interface
    static Vector<Vector> getNotiTable() {
        try {
            con = DriverManager.getConnection(url, username, password);
            String quary = "SELECT * FROM notification";
            pst = (PreparedStatement) con.prepareStatement(quary);
            rs = pst.executeQuery();
            Vector<Vector> table = new Vector<Vector>();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sf1 = new SimpleDateFormat("HH:mm");
            Date today = new Date();
            String d = sf.format(today);
            rs.next();
            while (rs.next()) {
                Vector<Object> row = new Vector<Object>();
                row.add(d);
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                java.sql.Timestamp ts = rs.getTimestamp(4);
                row.add(sf1.format(ts));

                table.add(row);
            }
            return table;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
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
    
    public Vector<Vector> loadtableforgantt(){
        try {
            //System.out.println("fkhjjhvvj");
            SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            con=DriverManager.getConnection(url, this.username, this.password);
            String quary="SELECT * FROM notifynew";
            pst=(PreparedStatement) con.prepareStatement(quary);
            rs=pst.executeQuery();
            
            Vector<Vector> table = new Vector<Vector>();  
            
            int count=0;
            while(rs.next() && count<10){
                count++;
                Vector<String> row = new Vector<String>();
                //System.out.println(row);
                
                row.add(rs.getString(1));
                
                row.add(df.format(rs.getTimestamp(3)));
                
                row.add(df.format(rs.getTimestamp(4)));
                System.out.println("ab");
                row.add(df.format(rs.getTimestamp(6)));
                System.out.println("aaaaa");
                row.add(df.format(rs.getTimestamp(7)));
                
                row.add(df.format(rs.getTimestamp(9)));
                row.add(df.format(rs.getTimestamp(10)));
                
                row.add(df.format(rs.getTimestamp(12)));
                row.add(df.format(rs.getTimestamp(13)));
                
                row.add(df.format(rs.getTimestamp(15)));
                row.add(df.format(rs.getTimestamp(16)));
                
                row.add(df.format(rs.getTimestamp(18)));
                row.add(df.format(rs.getTimestamp(19)));
                
                row.add(df.format(rs.getTimestamp(21)));
                row.add(df.format(rs.getTimestamp(22)));
                
                //System.out.println(df.format(rs.getTimestamp(6)));
                System.out.println(df.format(rs.getTimestamp(3)));
                
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
    
    public HashMap<String,Integer> getDistinctOrderArticle(){
        try {
            con=DriverManager.getConnection(url, this.username, this.password);
            String quary="SELECT Distinct Order_article FROM notifynew";
            pst=(PreparedStatement) con.prepareStatement(quary);
            rs=pst.executeQuery();
            
            HashMap<String,Integer> map = new HashMap<String,Integer>();
            while(rs.next()){
                map.put(rs.getString(1),1);
            }
            return map;
            
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


}
