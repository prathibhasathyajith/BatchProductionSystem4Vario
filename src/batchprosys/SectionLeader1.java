/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchprosys;

import java.awt.Desktop;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.SingleSelectionModel;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;

public class SectionLeader1 extends javax.swing.JFrame {
    static ImageIcon qmark = new ImageIcon("/Users/prathibha/Desktop/JAVA/BatchProSys/src/batchprosys/messageIcons/qmark.png");
    static ImageIcon warning = new ImageIcon("/Users/prathibha/Desktop/JAVA/BatchProSys/src/batchprosys/messageIcons/warning.png");
    Dbops db=new Dbops();
    DBOperations dbOps=new DBOperations();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    /**
     * Creates new form SectionLeader
     */
    DBOperations dbops = new DBOperations();
    static ArrayList<Vector<Vector>> array;
    
    
    public SectionLeader1() {
        initComponents();
        db.flushNoti();
        db.flushNotification();
        this.setLocationRelativeTo(null);
        Date date1 = new Date();
        date.setText(dateFormat.format(date1));
        time.setText(timeFormat.format(date1));
        
        setUserName();
        loadTables();
        loadTables1();
        setOpandMc();
        setduration();
        setSection();
        
    }
    
    //Get the user names and set it in the GUI
    void setUserName(){
        String result = dbops.getName();
        userid.setText(result);
    }
    
    //Get the section ID and set it in the GUI
    void setSection(){
        String result = dbops.getSection();
        Section.setText(result);
    }
    
    
    
    
    ///////////////Work allocation
    static Vector<Object> columnNames;
    static Vector<Vector> OrderPlan;
    static Vector<JCheckBox> columnNames3;
    
    //Load the GUI Table with the manipulated data taken by the database and show 
    void loadTables(){
        columnNames=new Vector<Object>();
        //columnNames3=new Vector<JCheckBox>();
        columnNames.addElement("SAP_No");
        columnNames.addElement("Order_Article");
        columnNames.addElement("Order_Quantity");
        columnNames.addElement("Batch_Quantity");
        columnNames.addElement("Remain_Quantity");
        columnNames.addElement("Starting time");
        columnNames.addElement("End time");
        //columnNames.addElement("Completed Qty");
        //columnNames.addElement("Completed");
        
        array=dbOps.getOrderPlanforSWorkAllocation();
        
        tblPrework1.setModel(new DefaultTableModel(array.get(0),columnNames));
        tblAL.setModel(new DefaultTableModel(array.get(1),columnNames));
        tblWS.setModel(new DefaultTableModel(array.get(2),columnNames));
        tblQS.setModel(new DefaultTableModel(array.get(3),columnNames));
        tblTesting.setModel(new DefaultTableModel(array.get(4),columnNames));
        tblFinal.setModel(new DefaultTableModel(array.get(5),columnNames));
        tblPacking.setModel(new DefaultTableModel(array.get(6),columnNames));
    }
    
    //Final work scedule 
    static Vector<String> columnNames2;
    static Vector<Vector> OrderPlan1;
    
    
    void loadTables1(){
        columnNames2=new Vector<String>();
        columnNames2.addElement("SAP_No");
        columnNames2.addElement("Order_Article");
        columnNames2.addElement("Order_Quantity");
        columnNames2.addElement("Batch_Quantity");
        columnNames2.addElement("No_Of_Batches");

        OrderPlan1=dbOps.getOrderPlan();
        
        tablefinalreport.setModel(new DefaultTableModel(OrderPlan1,columnNames2));      
    }
    
    void setOpandMc(){
        String [] arr=dbOps.getsectiondetails();
        
        PWO.setText(arr[1]);
        ALO.setText(arr[2]);
        WSO.setText(arr[3]);
        QCO.setText(arr[4]);
        TO.setText(arr[5]);
        FO.setText(arr[6]);
        PO.setText(arr[7]);
        PWM.setText(arr[8]);
        ALM.setText(arr[9]);
        WSM.setText(arr[10]);
        QCM.setText(arr[11]);
        TM.setText(arr[12]);
        FM.setText(arr[13]);
        PM.setText(arr[14]);
    }
    
    //Set the time durations for all the order articles 
    void setduration(){
        String [] arr=dbOps.getsectiondetails();
        System.out.println(arr[0]);
        
        //Time periods selected by the production planners GUI
        if (arr[0] != null) {
            if (Integer.parseInt(arr[0]) == 540) {
                durationLable.setText("One Day");
            } else if (Integer.parseInt(arr[0]) == 1080) {
                durationLable.setText("Two Day");
            } else if (Integer.parseInt(arr[0]) == 1620) {
                durationLable.setText("Three Day");
            } else if (Integer.parseInt(arr[0]) == 2160) {
                durationLable.setText("Four Day");
            } else if (Integer.parseInt(arr[0]) == 2700) {
                durationLable.setText("Five Day");
            } else if (Integer.parseInt(arr[0]) == 3240) {
                durationLable.setText("Six Day");
            } else if (Integer.parseInt(arr[0]) == 3780) {
                durationLable.setText("One Week");
            } else {
                durationLable.setText("no");
            }
        } else {
            durationLable.setText("Not given yet");
            pleaseW.setText("* Work in process");
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

        jScrollPane3 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablefinalreport = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        userid = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        durationLable = new javax.swing.JLabel();
        PWM = new javax.swing.JLabel();
        PWO = new javax.swing.JLabel();
        LabelPW = new javax.swing.JLabel();
        LabelAL = new javax.swing.JLabel();
        ALO = new javax.swing.JLabel();
        ALM = new javax.swing.JLabel();
        WSM = new javax.swing.JLabel();
        WSO = new javax.swing.JLabel();
        LabelWS = new javax.swing.JLabel();
        LabelQC = new javax.swing.JLabel();
        QCO = new javax.swing.JLabel();
        QCM = new javax.swing.JLabel();
        TM = new javax.swing.JLabel();
        TO = new javax.swing.JLabel();
        LabelT = new javax.swing.JLabel();
        LabelF = new javax.swing.JLabel();
        FO = new javax.swing.JLabel();
        FM = new javax.swing.JLabel();
        PM = new javax.swing.JLabel();
        PO = new javax.swing.JLabel();
        LabelP = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        Section = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        tblPrework = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblPrework1 = new javax.swing.JTable();
        pwCheck = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        tblPrework2 = new javax.swing.JTabbedPane();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tblAL = new javax.swing.JTable();
        ALCheck = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        tblPrework4 = new javax.swing.JTabbedPane();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        tblWS = new javax.swing.JTable();
        WSCheck = new javax.swing.JCheckBox();
        jButton4 = new javax.swing.JButton();
        tblPrework6 = new javax.swing.JTabbedPane();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        tblQS = new javax.swing.JTable();
        QCCheck = new javax.swing.JCheckBox();
        jButton6 = new javax.swing.JButton();
        tblPrework8 = new javax.swing.JTabbedPane();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        tblTesting = new javax.swing.JTable();
        TestCheck = new javax.swing.JCheckBox();
        jButton7 = new javax.swing.JButton();
        tblPrework10 = new javax.swing.JTabbedPane();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        tblFinal = new javax.swing.JTable();
        FinalCheck = new javax.swing.JCheckBox();
        jButton8 = new javax.swing.JButton();
        tblPrework12 = new javax.swing.JTabbedPane();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jScrollPane21 = new javax.swing.JScrollPane();
        tblPacking = new javax.swing.JTable();
        PackingCheck = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        pleaseW = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        jScrollPane3.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Batch Production System- Section Leader");
        setPreferredSize(new java.awt.Dimension(1024, 728));
        setSize(new java.awt.Dimension(1024, 728));

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1024, 768));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(1024, 768));

        tablefinalreport.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        tablefinalreport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "SAP_No", "Order_Article", "Order_Quantity", "Batch_Quantity", "No_Of_Batches"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablefinalreport);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel1.setText("Date:");

        date.setText("01-01-2016");

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel3.setText("Time:");

        time.setText("07:30 a.m");

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel4.setText("Section-Leader:");

        userid.setText("PP_0001");

        jLabel7.setFont(new java.awt.Font("Helvetica", 1, 14)); // NOI18N
        jLabel7.setText("No of Operators:");

        jLabel8.setFont(new java.awt.Font("Helvetica", 1, 14)); // NOI18N
        jLabel8.setText("No of Machines :");

        jLabel11.setFont(new java.awt.Font("Helvetica", 1, 14)); // NOI18N
        jLabel11.setText("Time Duration   :");

        durationLable.setForeground(new java.awt.Color(0, 0, 204));
        durationLable.setText("2 day");

        PWM.setForeground(new java.awt.Color(0, 0, 204));
        PWM.setText("00");

        PWO.setForeground(new java.awt.Color(0, 0, 204));
        PWO.setText("00");

        LabelPW.setFont(new java.awt.Font("Helvetica", 1, 12)); // NOI18N
        LabelPW.setText("Pre Work");

        LabelAL.setFont(new java.awt.Font("Helvetica", 1, 12)); // NOI18N
        LabelAL.setText("Assembly Line");

        ALO.setForeground(new java.awt.Color(0, 0, 204));
        ALO.setText("00");

        ALM.setForeground(new java.awt.Color(0, 0, 204));
        ALM.setText("00");

        WSM.setForeground(new java.awt.Color(0, 0, 204));
        WSM.setText("00");

        WSO.setForeground(new java.awt.Color(0, 0, 204));
        WSO.setText("00");

        LabelWS.setFont(new java.awt.Font("Helvetica", 1, 12)); // NOI18N
        LabelWS.setText("Wave Soldering");

        LabelQC.setFont(new java.awt.Font("Helvetica", 1, 12)); // NOI18N
        LabelQC.setText("Quality Control");

        QCO.setForeground(new java.awt.Color(0, 0, 204));
        QCO.setText("00");

        QCM.setForeground(new java.awt.Color(0, 0, 204));
        QCM.setText("00");

        TM.setForeground(new java.awt.Color(0, 0, 204));
        TM.setText("00");

        TO.setForeground(new java.awt.Color(0, 0, 204));
        TO.setText("00");

        LabelT.setFont(new java.awt.Font("Helvetica", 1, 12)); // NOI18N
        LabelT.setText("Testing");

        LabelF.setFont(new java.awt.Font("Helvetica", 1, 12)); // NOI18N
        LabelF.setText("Final");

        FO.setForeground(new java.awt.Color(0, 0, 204));
        FO.setText("00");

        FM.setForeground(new java.awt.Color(0, 0, 204));
        FM.setText("00");

        PM.setForeground(new java.awt.Color(0, 0, 204));
        PM.setText("00");

        PO.setForeground(new java.awt.Color(0, 0, 204));
        PO.setText("00");

        LabelP.setFont(new java.awt.Font("Helvetica", 1, 12)); // NOI18N
        LabelP.setText("Packing");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel2.setText("Section:");

        Section.setText("pre_work");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(date)
                        .addGap(56, 56, 56)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(time)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userid)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Section)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel8)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(37, 37, 37)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(PWM)
                                                            .addComponent(PWO))
                                                        .addGap(78, 78, 78)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(ALM)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(WSM))
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(ALO)
                                                                .addGap(94, 94, 94)
                                                                .addComponent(WSO))))
                                                    .addComponent(durationLable)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(LabelPW)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(LabelAL)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(LabelWS)
                                                        .addGap(18, 18, 18)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(LabelQC)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(LabelT))
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addComponent(QCO)
                                                                    .addComponent(QCM))
                                                                .addGap(69, 69, 69)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(TM)
                                                                    .addComponent(TO, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                        .addGap(30, 30, 30)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(LabelF)
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(59, 59, 59)
                                                                .addComponent(LabelP))
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addComponent(FM)
                                                                    .addComponent(FO))
                                                                .addGap(54, 54, 54)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(PO, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addComponent(PM, javax.swing.GroupLayout.Alignment.TRAILING))))))))))
                                .addGap(0, 161, Short.MAX_VALUE))
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(date)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(time)
                    .addComponent(userid)
                    .addComponent(jLabel2)
                    .addComponent(Section))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(ALO)
                            .addComponent(WSO)
                            .addComponent(QCO)
                            .addComponent(TO)
                            .addComponent(FO)
                            .addComponent(PO)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LabelPW)
                            .addComponent(LabelAL)
                            .addComponent(LabelWS)
                            .addComponent(LabelQC)
                            .addComponent(LabelT)
                            .addComponent(LabelF)
                            .addComponent(LabelP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PWO)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ALM)
                    .addComponent(WSM)
                    .addComponent(QCM)
                    .addComponent(TM)
                    .addComponent(PM)
                    .addComponent(FM)
                    .addComponent(PWM)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(durationLable))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Final Report", jPanel1);

        jPanel2.setPreferredSize(new java.awt.Dimension(1024, 768));

        tblPrework.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tblPrework1.setBackground(new java.awt.Color(244, 244, 244));
        tblPrework1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblPrework1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "SAP_No", "Order_Article", "Order_Quantity", "Batch_Quantity", "Starting time", "End time", "Completed Qty"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane15.setViewportView(tblPrework1);

        pwCheck.setText("Completed");
        pwCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pwCheckActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 945, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(959, 959, 959))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(pwCheck)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pwCheck)
                .addGap(12, 12, 12)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tblPrework.addTab("Work Schedule", jPanel3);

        jButton1.setText("Gantt");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        tblPrework.addTab("Gantt", jButton1);

        jTabbedPane2.addTab("PreWork", tblPrework);

        tblPrework2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tblAL.setBackground(new java.awt.Color(244, 244, 244));
        tblAL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblAL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "SAP_No", "Order_Article", "Order_Quantity", "Batch_Quantity", "Starting time", "End time", "Completed Qty"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane16.setViewportView(tblAL);

        ALCheck.setText("Completed");
        ALCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ALCheckActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 945, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(959, 959, 959))))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(ALCheck)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ALCheck)
                .addGap(12, 12, 12)
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tblPrework2.addTab("Work Schedule", jPanel17);

        jButton2.setText("Gantt");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        tblPrework2.addTab("Gantt", jButton2);

        jTabbedPane2.addTab("AL", tblPrework2);

        tblPrework4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tblWS.setBackground(new java.awt.Color(244, 244, 244));
        tblWS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblWS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "SAP_No", "Order_Article", "Order_Quantity", "Batch_Quantity", "Starting time", "End time", "Completed Qty"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane17.setViewportView(tblWS);

        WSCheck.setText("Completed");
        WSCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WSCheckActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 945, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(959, 959, 959))))
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(WSCheck)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(WSCheck)
                .addGap(12, 12, 12)
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tblPrework4.addTab("Work Schedule", jPanel19);

        jButton4.setText("Gantt");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        tblPrework4.addTab("Gantt", jButton4);

        jTabbedPane2.addTab("WS", tblPrework4);

        tblPrework6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tblQS.setBackground(new java.awt.Color(244, 244, 244));
        tblQS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblQS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "SAP_No", "Order_Article", "Order_Quantity", "Batch_Quantity", "Starting time", "End time", "Completed Qty"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane18.setViewportView(tblQS);

        QCCheck.setText("Completed");
        QCCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QCCheckActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 945, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(959, 959, 959))))
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(QCCheck)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(QCCheck)
                .addGap(12, 12, 12)
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tblPrework6.addTab("Work Schedule", jPanel21);

        jButton6.setText("Gantt");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        tblPrework6.addTab("Gantt", jButton6);

        jTabbedPane2.addTab("QS", tblPrework6);

        tblPrework8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tblTesting.setBackground(new java.awt.Color(244, 244, 244));
        tblTesting.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblTesting.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "SAP_No", "Order_Article", "Order_Quantity", "Batch_Quantity", "Starting time", "End time", "Completed Qty"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane19.setViewportView(tblTesting);

        TestCheck.setText("Completed");
        TestCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TestCheckActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 945, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(959, 959, 959))))
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(TestCheck)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TestCheck)
                .addGap(12, 12, 12)
                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tblPrework8.addTab("Work Schedule", jPanel23);

        jButton7.setText("Gantt");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        tblPrework8.addTab("Gantt", jButton7);

        jTabbedPane2.addTab("Testing", tblPrework8);

        tblPrework10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tblFinal.setBackground(new java.awt.Color(244, 244, 244));
        tblFinal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblFinal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "SAP_No", "Order_Article", "Order_Quantity", "Batch_Quantity", "Starting time", "End time", "Completed Qty"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane20.setViewportView(tblFinal);

        FinalCheck.setText("Completed");
        FinalCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinalCheckActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 945, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(959, 959, 959))))
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(FinalCheck)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(FinalCheck)
                .addGap(12, 12, 12)
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tblPrework10.addTab("Work Schedule", jPanel25);

        jButton8.setText("Gantt");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        tblPrework10.addTab("Gantt", jButton8);

        jTabbedPane2.addTab("Final", tblPrework10);

        tblPrework12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tblPacking.setBackground(new java.awt.Color(244, 244, 244));
        tblPacking.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblPacking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "SAP_No", "Order_Article", "Order_Quantity", "Batch_Quantity", "Starting time", "End time", "Completed Qty"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane21.setViewportView(tblPacking);

        PackingCheck.setText("Completed");
        PackingCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PackingCheckActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 945, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(959, 959, 959))))
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(PackingCheck)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PackingCheck)
                .addGap(12, 12, 12)
                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tblPrework12.addTab("Work Schedule", jPanel27);

        jButton3.setText("Gantt");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        tblPrework12.addTab("Gantt", jButton3);

        jTabbedPane2.addTab("Packing", tblPrework12);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 997, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 532, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sectional Work Allocation", jPanel2);

        jLabel21.setFont(new java.awt.Font("Helvetica", 0, 36)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(27, 78, 245));
        jLabel21.setText("VARIO");
        jLabel21.setSize(new java.awt.Dimension(70, 50));

        jLabel37.setFont(new java.awt.Font("Helvetica", 0, 36)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(153, 153, 153));
        jLabel37.setText("SYSTEMS");
        jLabel37.setSize(new java.awt.Dimension(70, 50));

        jLabel38.setFont(new java.awt.Font("Helvetica", 0, 13)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 255));
        jLabel38.setText("ELECTRONICS");

        jButton5.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/batchprosys/Icons/back.png"))); // NOI18N
        jButton5.setText("Back");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        pleaseW.setForeground(new java.awt.Color(255, 0, 0));

        jMenu1.setText("File");

        jMenuItem1.setText("Profile Details");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Logout");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        jMenuItem3.setText("User Manual");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("About");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        jMenuItem4.setText("About");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 323, Short.MAX_VALUE)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel38)
                        .addGap(307, 307, 307))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(pleaseW, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addComponent(pleaseW))
                .addGap(54, 54, 54))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Select the dataset from the database for show in the gantt chart using vectors
    //Pre work section
    public IntervalCategoryDataset createDataset1() {
        final TaskSeries s1 = new TaskSeries("Scheduled");
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        HashMap<String, Integer> map = db.getDistinctOrderArticle();
        Vector<Vector> vec = db.loadtableforgantt();

        for (Vector<String> row : vec) {
            int term=map.get(row.get(0).toString());
            map.put(row.get(0).toString(), term+1);
            
            try {
                s1.add(new Task(row.get(0).toString()+"-"+term,new SimpleTimePeriod(df.parse(row.get(1).toString()),df.parse(row.get(2).toString()))));          
            } catch (Exception ex) {
                
            }
        }
        final TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);
        return collection;
    }
    
    //Gantt chart data set for the Assembly line work centre
    public IntervalCategoryDataset createDataset2() {
        final TaskSeries s1 = new TaskSeries("Scheduled");
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        HashMap<String, Integer> map = db.getDistinctOrderArticle();
        Vector<Vector> vec = db.loadtableforgantt();

        for (Vector<String> row : vec) {
            int term=map.get(row.get(0).toString());
            map.put(row.get(0).toString(), term+1);
            
            try {
                s1.add(new Task(row.get(0).toString()+"-"+term,new SimpleTimePeriod(df.parse(row.get(3).toString()),df.parse(row.get(4).toString()))));          
            } catch (Exception ex) {
                
            }
        }
        final TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);
        return collection;
    }
    //Gantt chart data set for the Wave solder work centre
    public IntervalCategoryDataset createDataset3() {
        final TaskSeries s1 = new TaskSeries("Scheduled");
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        HashMap<String, Integer> map = db.getDistinctOrderArticle();
        Vector<Vector> vec = db.loadtableforgantt();

        for (Vector<String> row : vec) {
            int term=map.get(row.get(0).toString());
            map.put(row.get(0).toString(), term+1);
            
            try {
                s1.add(new Task(row.get(0).toString()+"-"+term,new SimpleTimePeriod(df.parse(row.get(5).toString()),df.parse(row.get(6).toString()))));          
            } catch (Exception ex) {
                
            }
        }
        final TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);
        return collection;
    }
    //Gantt chart data set for the Quality Control work centre
    public IntervalCategoryDataset createDataset4() {
        final TaskSeries s1 = new TaskSeries("Scheduled");
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        HashMap<String, Integer> map = db.getDistinctOrderArticle();
        Vector<Vector> vec = db.loadtableforgantt();

        for (Vector<String> row : vec) {
            int term=map.get(row.get(0).toString());
            map.put(row.get(0).toString(), term+1);
            
            try {
                s1.add(new Task(row.get(0).toString()+"-"+term,new SimpleTimePeriod(df.parse(row.get(7).toString()),df.parse(row.get(8).toString()))));          
            } catch (Exception ex) {
                
            }
        }
        final TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);
        return collection;
    }
    //Gantt chart data set for the Testing work centre
    public IntervalCategoryDataset createDataset5() {
        final TaskSeries s1 = new TaskSeries("Scheduled");
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        HashMap<String, Integer> map = db.getDistinctOrderArticle();
        Vector<Vector> vec = db.loadtableforgantt();

        for (Vector<String> row : vec) {
            int term=map.get(row.get(0).toString());
            map.put(row.get(0).toString(), term+1);
            
            try {
                s1.add(new Task(row.get(0).toString()+"-"+term,new SimpleTimePeriod(df.parse(row.get(9).toString()),df.parse(row.get(10).toString()))));          
            } catch (Exception ex) {
                
            }
        }
        final TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);
        return collection;
    }
    //Gantt chart data set for the Final work centre
    public IntervalCategoryDataset createDataset6() {
        final TaskSeries s1 = new TaskSeries("Scheduled");
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        HashMap<String, Integer> map = db.getDistinctOrderArticle();
        Vector<Vector> vec = db.loadtableforgantt();

        for (Vector<String> row : vec) {
            int term=map.get(row.get(0).toString());
            map.put(row.get(0).toString(), term+1);
            
            try {
                s1.add(new Task(row.get(0).toString()+"-"+term,new SimpleTimePeriod(df.parse(row.get(11).toString()),df.parse(row.get(12).toString()))));          
            } catch (Exception ex) {
                
            }
        }
        final TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);
        return collection;
    }
    //Gantt chart data set for the Packing work centre
    public IntervalCategoryDataset createDataset7() {
        final TaskSeries s1 = new TaskSeries("Scheduled");
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        HashMap<String, Integer> map = db.getDistinctOrderArticle();
        Vector<Vector> vec = db.loadtableforgantt();

        for (Vector<String> row : vec) {
            int term=map.get(row.get(0).toString());
            map.put(row.get(0).toString(), term+1);
            
            try {
                s1.add(new Task(row.get(0).toString()+"-"+term,new SimpleTimePeriod(df.parse(row.get(13).toString()),df.parse(row.get(14).toString()))));          
            } catch (Exception ex) {
                
            }
        }
        final TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);
        return collection;
    }
    
    //Buttons used for the gantt and other
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        int p = JOptionPane.showConfirmDialog(null, "Do you really want to back to login screen?", "Back to login", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE, qmark);
        if (p == 0) {
            Login2 L1 = new Login2();
            this.dispose();
            L1.setVisible(true);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        profileSectionLeader2 psl2 =new profileSectionLeader2();
        psl2.setVisible(true);
        psl2.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                setUserName();
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        //System.exit(0);
        int p = JOptionPane.showConfirmDialog(null, "Do you really want to Logout?", "Logout", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, warning);
        if (p == 0) {
            Login2 L1 = new Login2();
            this.dispose();
            L1.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
         if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File("/Users/prathibha/Desktop/JAVA/BatchProSys/src/batchprosys/PDFusermanual/SLeader.pdf");
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                // no application registered for PDFs
            }
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        About ab=new About();
        ab.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void PackingCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PackingCheckActionPerformed
        // check complete or not the order
        if (PackingCheck.isSelected()) {
            if (tblPacking.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Select a row on table!");
            } else {
                if (db.pwComplete(array.get(0).get(tblPacking.getSelectedRow()).get(1).toString())) {
                    JOptionPane.showMessageDialog(this, "Order Article " + db.pwComplete(array.get(0).get(tblPacking.getSelectedRow()).get(1).toString()) + " Completed in Packing!");
                } else {
                    JOptionPane.showMessageDialog(this, "Error occured in Packing !");
                }
            }
        }
    }//GEN-LAST:event_PackingCheckActionPerformed

    private void pwCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pwCheckActionPerformed
        // check complete or not the order
        if (pwCheck.isSelected()) {
            if (tblPrework1.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Select a row on table!");
            } else {
                if (db.pwComplete(array.get(0).get(tblPrework1.getSelectedRow()).get(1).toString())) {
                    JOptionPane.showMessageDialog(this, "Order Article " + db.pwComplete(array.get(0).get(tblPrework1.getSelectedRow()).get(1).toString()) + " Completed in Pre Work!");
                } else {
                    JOptionPane.showMessageDialog(this, "Error occured in pwComplete!");
                }
            }
        }
    }//GEN-LAST:event_pwCheckActionPerformed

    private void ALCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ALCheckActionPerformed
        // check complete or not the order
        if (ALCheck.isSelected()) {
            if (tblAL.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Select a row on table!");
            } else {
                if (db.pwComplete(array.get(0).get(tblAL.getSelectedRow()).get(1).toString())) {
                    JOptionPane.showMessageDialog(this, "Order Article " + db.pwComplete(array.get(0).get(tblAL.getSelectedRow()).get(1).toString()) + " Completed in Assembly line!");
                } else {
                    JOptionPane.showMessageDialog(this, "Error occured in ALComplete!");
                }
            }
        }
    }//GEN-LAST:event_ALCheckActionPerformed

    private void WSCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WSCheckActionPerformed
        // check complete or not the order
        if (WSCheck.isSelected()) {
            if (tblWS.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Select a row on table!");
            } else {
                if (db.pwComplete(array.get(0).get(tblWS.getSelectedRow()).get(1).toString())) {
                    JOptionPane.showMessageDialog(this, "Order Article " + db.pwComplete(array.get(0).get(tblWS.getSelectedRow()).get(1).toString()) + " Completed in Wave Soldering!");
                } else {
                    JOptionPane.showMessageDialog(this, "Error occured in WSComplete!");
                }
            }
        }
    }//GEN-LAST:event_WSCheckActionPerformed

    private void QCCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QCCheckActionPerformed
        // check complete or not the order
        if (QCCheck.isSelected()) {
            if (tblQS.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Select a row on table!");
            } else {
                if (db.pwComplete(array.get(0).get(tblQS.getSelectedRow()).get(1).toString())) {
                    JOptionPane.showMessageDialog(this, "Order Article " + db.pwComplete(array.get(0).get(tblQS.getSelectedRow()).get(1).toString()) + " Completed in Quality Check!");
                } else {
                    JOptionPane.showMessageDialog(this, "Error occured in QCComplete!");
                }
            }
        }
    }//GEN-LAST:event_QCCheckActionPerformed

    private void TestCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TestCheckActionPerformed
        // check complete or not the order
        if (TestCheck.isSelected()) {
            if (tblTesting.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Select a row on table!");
            } else {
                if (db.pwComplete(array.get(0).get(tblTesting.getSelectedRow()).get(1).toString())) {
                    JOptionPane.showMessageDialog(this, "Order Article " + db.pwComplete(array.get(0).get(tblTesting.getSelectedRow()).get(1).toString()) + " Completed in Testing!");
                } else {
                    JOptionPane.showMessageDialog(this, "Error occured in TestComplete!");
                }
            }
        }
    }//GEN-LAST:event_TestCheckActionPerformed

    private void FinalCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinalCheckActionPerformed

        if (FinalCheck.isSelected()) {
            if (tblFinal.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Select a row on table!");
            } else {
                if (db.pwComplete(array.get(0).get(tblFinal.getSelectedRow()).get(1).toString())) {
                    JOptionPane.showMessageDialog(this, "Order Article " + db.pwComplete(array.get(0).get(tblFinal.getSelectedRow()).get(1).toString()) + " Completed in Final!");
                } else {
                    JOptionPane.showMessageDialog(this, "Error occured in Final Complete!");
                }
            }
        }
    }//GEN-LAST:event_FinalCheckActionPerformed

    
    //Gantt chart buttons
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFreeChart chart = ChartFactory.createGanttChart("Sectional Work Schedule Gantt Chart", "Order Article", "Timeline", createDataset1(), true, true, true);
        ChartFrame frame = new ChartFrame("Gantt Chart", chart);
        frame.setVisible(true);
        frame.setSize(1280, 5000);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        JFreeChart chart = ChartFactory.createGanttChart("Sectional Work Schedule Gantt Chart", "Order Article", "Timeline", createDataset2(), true, true, true);
        ChartFrame frame = new ChartFrame("Gantt Chart", chart);
        frame.setVisible(true);
        frame.setSize(1280, 5000);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        JFreeChart chart = ChartFactory.createGanttChart("Sectional Work Schedule Gantt Chart", "Order Article", "Timeline", createDataset3(), true, true, true);
        ChartFrame frame = new ChartFrame("Gantt Chart", chart);
        frame.setVisible(true);
        frame.setSize(1280, 5000);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        JFreeChart chart = ChartFactory.createGanttChart("Sectional Work Schedule Gantt Chart", "Order Article", "Timeline", createDataset4(), true, true, true);
        ChartFrame frame = new ChartFrame("Gantt Chart", chart);
        frame.setVisible(true);
        frame.setSize(1280, 5000);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        JFreeChart chart = ChartFactory.createGanttChart("Sectional Work Schedule Gantt Chart", "Order Article", "Timeline", createDataset5(), true, true, true);
        ChartFrame frame = new ChartFrame("Gantt Chart", chart);
        frame.setVisible(true);
        frame.setSize(1280, 5000);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        JFreeChart chart = ChartFactory.createGanttChart("Sectional Work Schedule Gantt Chart", "Order Article", "Timeline", createDataset6(), true, true, true);
        ChartFrame frame = new ChartFrame("Gantt Chart", chart);
        frame.setVisible(true);
        frame.setSize(1280, 5000);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        JFreeChart chart = ChartFactory.createGanttChart("Sectional Work Schedule Gantt Chart", "Order Article", "Timeline", createDataset7(), true, true, true);
        ChartFrame frame = new ChartFrame("Gantt Chart", chart);
        frame.setVisible(true);
        frame.setSize(1280, 5000);
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(SectionLeader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SectionLeader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SectionLeader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SectionLeader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SectionLeader1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox ALCheck;
    private javax.swing.JLabel ALM;
    private javax.swing.JLabel ALO;
    private javax.swing.JLabel FM;
    private javax.swing.JLabel FO;
    private javax.swing.JCheckBox FinalCheck;
    private javax.swing.JLabel LabelAL;
    private javax.swing.JLabel LabelF;
    private javax.swing.JLabel LabelP;
    private javax.swing.JLabel LabelPW;
    private javax.swing.JLabel LabelQC;
    private javax.swing.JLabel LabelT;
    private javax.swing.JLabel LabelWS;
    private javax.swing.JLabel PM;
    private javax.swing.JLabel PO;
    private javax.swing.JLabel PWM;
    private javax.swing.JLabel PWO;
    private javax.swing.JCheckBox PackingCheck;
    private javax.swing.JCheckBox QCCheck;
    private javax.swing.JLabel QCM;
    private javax.swing.JLabel QCO;
    private javax.swing.JLabel Section;
    private javax.swing.JLabel TM;
    private javax.swing.JLabel TO;
    private javax.swing.JCheckBox TestCheck;
    private javax.swing.JCheckBox WSCheck;
    private javax.swing.JLabel WSM;
    private javax.swing.JLabel WSO;
    private javax.swing.JLabel date;
    private javax.swing.JLabel durationLable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel pleaseW;
    private javax.swing.JCheckBox pwCheck;
    private javax.swing.JTable tablefinalreport;
    private javax.swing.JTable tblAL;
    private javax.swing.JTable tblFinal;
    private javax.swing.JTable tblPacking;
    private javax.swing.JTabbedPane tblPrework;
    private javax.swing.JTable tblPrework1;
    private javax.swing.JTabbedPane tblPrework10;
    private javax.swing.JTabbedPane tblPrework12;
    private javax.swing.JTabbedPane tblPrework2;
    private javax.swing.JTabbedPane tblPrework4;
    private javax.swing.JTabbedPane tblPrework6;
    private javax.swing.JTabbedPane tblPrework8;
    private javax.swing.JTable tblQS;
    private javax.swing.JTable tblTesting;
    private javax.swing.JTable tblWS;
    private javax.swing.JLabel time;
    private javax.swing.JLabel userid;
    // End of variables declaration//GEN-END:variables
}
