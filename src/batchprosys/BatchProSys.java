/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchprosys;

import batchprosys.Login;
import static batchprosys.PPlannerUT2.currentUser;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author prathibha
 */
public class BatchProSys {
    
    static String currentUser = "";
    Dbops dbOps = new Dbops();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*
        int arry[]={9011481,9013080,9012074,9013502,9013710};
        BatchFunction BP=new BatchFunction();
        DBoperationsFunction DBO=new DBoperationsFunction();
        
        BP.FinalFunction(arry, 540, 100);
        for(int x:BP.FinalFunction(arry, 540, 100)){
            System.out.println(x);
        }
        
        
        
        
        int a=BP.AvailableTime(540,100);
        System.out.println("a-"+a);
        double b = DBO.getAllraisiti(arry, a);
        System.out.println("b-"+b);
        double c=BP.avarageSetupTime(arry);
        //System.out.println("c-");
        double d=BP.L(c, b);
        int[] BQ= BP.batchQuantity(d, arry);
        
        
        System.out.println("c-"+c);
        System.out.println("L-"+d);
        for(int x:BQ){
            System.out.println(x);
        }
        //for(double x:DBO.getAllUti(arry, a)){
        //    System.out.println("Uti-"+x);
        //}
        //System.out.println("Stop");
        //int x=DBO.batchQuantity(90, 9850003);
        //System.out.println(x);
        //int b=BP.rai(a, a)
        
                
        //EmailInfo EI=new EmailInfo();
        //String arr[]=EI.getEmailInfo("pplanner");
        //System.out.println("Mr "+ arr[1]+"\n"+"your userid:"+arr[0]+"\n"+"your password:"+arr[2]+"\nEmail:"+arr[3]);
        
        //Email_ForgotPass EF=new Email_ForgotPass(arr[3], arr[1], arr[2], arr[0]);
        //Login interface//
        */
        /*Date start1;
        String myTime = "07:30";
            SimpleDateFormat df = new SimpleDateFormat("HH:mm");
            start1 = df.parse(myTime);
        Calendar cal = Calendar.getInstance();
                cal.setTime(start1);
                cal.add(Calendar.MINUTE, "81"
                );
        */
        Login2 l1=new Login2();
        l1.setVisible(true);
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        //Date date = new Date();
        //System.out.println(dateFormat.format(date));
        //System.out.println(timeFormat.format(date));
               
        //Calendar cal = Calendar.getInstance();
        //System.out.println(dateFormat.format(cal.getTime()));
       //String arr[]=EI.getUser();
       //int x=EI.SerachUserID("pplaner");
        //System.out.println(x);
        //MainFrame1 MF=new MainFrame1();
        //MF.setVisible(true);
        //production planner interface//
        //ProductionPlanner PP=new ProductionPlanner();
        //PP.setVisible(true);
        //Final report//
        //FinalReport FN=new FinalReport();
        //FN.setVisible(true);
        //Section leader interface//
        //SectionLeader SL=new SectionLeader();
        //SL.setVisible(true);
        //Production Manager interface//
        //PManager PM=new PManager();
        //PM.setVisible(true);
        //Admin Interface
        //AdminInterface AI=new AdminInterface();
        //AI.setVisible(true);
        //PPlannerDB PPDB=new PPlannerDB();
        //PPDB.setVisible(true);
       
        
        //MainFrame1 MF=new MainFrame1();
        //MF.setVisible(true);
        //production planner interface//
        //ProductionPlanner PP=new ProductionPlanner();
        //PP.setVisible(true);
        //Final report//
        //FinalReport FN=new FinalReport();
        //FN.setVisible(true);
        //Section leader interface//
        //SectionLeader SL=new SectionLeader();
        //SL.setVisible(true);
        //Production Manager interface//
        //PManager PM=new PManager();
        //PM.setVisible(true);
        //Admin Interface
        //AdminInterface AI=new AdminInterface();
        //AI.setVisible(true);
        //PPlannerDB PPDB=new PPlannerDB();
        //PPDB.setVisible(true);
        
        //---- run theread and get the pop up messages
        Thread one = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        //System.out.println("currentUser-"+currentUser);
                        if (currentUser != "") { 
                            //System.out.println("t1");
                            ArrayList<String> array = Dbops.pwnotify();
                            //System.out.println("t11");
                            if (array != null) {
                                if (array.size() > 0) {
                                    NotificationPopup popupWindow = new NotificationPopup();
                                    popupWindow.popupMessage(array.toString());
                                }
                            }
                            //System.out.println("t2");
                            ArrayList<String> array1 = Dbops.alnotify();
                            if (array != null) {
                                if (array.size() > 0) {
                                    NotificationPopup popupWindow = new NotificationPopup();
                                    popupWindow.popupMessage(array.toString());
                                }
                            }
                            //System.out.println("t3");
                            ArrayList<String> array2 = Dbops.wsnotify();
                            if (array != null) {
                                if (array.size() > 0) {
                                    NotificationPopup popupWindow = new NotificationPopup();
                                    popupWindow.popupMessage(array2.toString());
                                }
                            }
                            //System.out.println("t4");
                            ArrayList<String> array3 = Dbops.QCnotify();
                            if (array != null) {
                                if (array.size() > 0) {
                                    NotificationPopup popupWindow = new NotificationPopup();
                                    popupWindow.popupMessage(array3.toString());
                                }
                            }
                            //System.out.println("t5");
                            ArrayList<String> array4 = Dbops.testnotify();
                            if (array != null) {
                                if (array.size() > 0) {
                                    NotificationPopup popupWindow = new NotificationPopup();
                                    popupWindow.popupMessage(array4.toString());
                                }
                            }
                            //System.out.println("t6");
                            ArrayList<String> array5 = Dbops.finalnotify();
                            if (array != null) {
                                if (array.size() > 0) {
                                    NotificationPopup popupWindow = new NotificationPopup();
                                    popupWindow.popupMessage(array5.toString());
                                }
                            }
                            //System.out.println("t7");
                            ArrayList<String> array6 = Dbops.packingnotify();
                            if (array != null) {
                                if (array.size() > 0) {
                                    NotificationPopup popupWindow = new NotificationPopup();
                                    popupWindow.popupMessage(array6.toString());
                                }
                            }
                            
                            
                        }
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        System.out.println(ex);
                    }
                }
            }
        };
        one.start();
    }
    
}
