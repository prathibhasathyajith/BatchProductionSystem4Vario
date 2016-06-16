/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchprosys;

import static batchprosys.PPlannerUT2.columnNames1;
import java.awt.Color;
import static java.awt.Color.black;
import static java.awt.Color.white;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author prathibha
 */
public class FinalReport extends javax.swing.JFrame {
    static ImageIcon error = new ImageIcon("/Users/prathibha/Desktop/JAVA/BatchProSys/src/batchprosys/messageIcons/error.png");
    static ImageIcon sucess = new ImageIcon("/Users/prathibha/Desktop/JAVA/BatchProSys/src/batchprosys/messageIcons/sucess.png");
    static ImageIcon warning = new ImageIcon("/Users/prathibha/Desktop/JAVA/BatchProSys/src/batchprosys/messageIcons/warning.png");
    static ImageIcon qmark = new ImageIcon("/Users/prathibha/Desktop/JAVA/BatchProSys/src/batchprosys/messageIcons/qmark.png");
    /**
     * Creates new form FinalReport
     */
    DBOperations dbOps=new DBOperations();
    DBoperationsFunction dbof=new DBoperationsFunction();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    
    public FinalReport() {
        initComponents();
        this.setLocationRelativeTo(null);
        setUserName();
        setOpandMc();
        setduration();
        Date date = new Date();
        jLabel2.setText(dateFormat.format(date));
        jLabel5.setText(timeFormat.format(date));
        
        loadTables();
        loadUtilization();
    }
    
    void setUserName(){
        
        //User user=new User();
        showusername.setText(DBOperations.getName());
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
    
    void setduration(){
        String [] arr=dbOps.getsectiondetails();
        
        if(Integer.parseInt(arr[0])==540){
            durationLable.setText("One Day");
        }
        else if(Integer.parseInt(arr[0])==1080){
            durationLable.setText("Two Day");
        }
        else if(Integer.parseInt(arr[0])==1620){
            durationLable.setText("Three Day");
        }
        else if(Integer.parseInt(arr[0])==2160){
            durationLable.setText("Four Day");
        }
        else if(Integer.parseInt(arr[0])==2700){
            durationLable.setText("Five Day");
        }
        else if(Integer.parseInt(arr[0])==3240){
            durationLable.setText("Six Day");
        }
        else if(Integer.parseInt(arr[0])==3780){
            durationLable.setText("One Week");
        }
        else durationLable.setText("no");
            
    }
    static Vector<String> columnNames1;
    static Vector<Vector> OrderPlan;
    
    static int [] orderArticleNo;
    void loadTables(){
        columnNames1=new Vector<String>();
        columnNames1.addElement("SAP_No");
        columnNames1.addElement("Order_Article");
        columnNames1.addElement("Order_Quantity");
        columnNames1.addElement("Batch_Quantity");
        columnNames1.addElement("No_Of_Batches");
       
        
        
        OrderPlan=dbOps.getOrderPlan();
        //get order artical values//
        orderArticleNo=new int[OrderPlan.size()];
        if(OrderPlan.size()!=0){
            for(int i=0;i<OrderPlan.size();i++)
            orderArticleNo[i]=Integer.parseInt((String) OrderPlan.get(i).get(1));
        }
        
        //System.out.println(OrderPlan.size());
        //System.out.println(columnNames1.get(0));
        
        tblFinalDetail.setModel(new DefaultTableModel(OrderPlan,columnNames1));
       
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        PWO = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        durationLable = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFinalDetail = new javax.swing.JTable();
        LabelPW = new javax.swing.JLabel();
        LabelAL = new javax.swing.JLabel();
        LabelWS = new javax.swing.JLabel();
        LabelQC = new javax.swing.JLabel();
        LabelT = new javax.swing.JLabel();
        LabelF = new javax.swing.JLabel();
        LabelP = new javax.swing.JLabel();
        ALO = new javax.swing.JLabel();
        WSO = new javax.swing.JLabel();
        QCO = new javax.swing.JLabel();
        TO = new javax.swing.JLabel();
        FO = new javax.swing.JLabel();
        PO = new javax.swing.JLabel();
        PWM = new javax.swing.JLabel();
        ALM = new javax.swing.JLabel();
        WSM = new javax.swing.JLabel();
        QCM = new javax.swing.JLabel();
        TM = new javax.swing.JLabel();
        FM = new javax.swing.JLabel();
        PM = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        workstation_Uti = new javax.swing.JPanel();
        panelutilization = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        showusername = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Batch Production System-Final Report");
        setPreferredSize(new java.awt.Dimension(1024, 728));
        setSize(new java.awt.Dimension(1024, 728));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Final Report", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Avenir", 0, 18))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(1024, 728));
        jPanel1.setSize(new java.awt.Dimension(1024, 728));

        jLabel7.setFont(new java.awt.Font("Helvetica", 1, 14)); // NOI18N
        jLabel7.setText("No of Operators:");

        jLabel8.setFont(new java.awt.Font("Helvetica", 1, 14)); // NOI18N
        jLabel8.setText("No of Machines :");

        PWO.setForeground(new java.awt.Color(0, 0, 204));
        PWO.setText("09");

        jButton1.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/batchprosys/Icons/save.png"))); // NOI18N
        jButton1.setText("Save Report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Helvetica", 1, 14)); // NOI18N
        jLabel11.setText("Time Duration   :");

        durationLable.setForeground(new java.awt.Color(0, 0, 204));
        durationLable.setText("2 day");

        tblFinalDetail.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblFinalDetail);

        LabelPW.setFont(new java.awt.Font("Helvetica", 1, 12)); // NOI18N
        LabelPW.setText("Pre Work");

        LabelAL.setFont(new java.awt.Font("Helvetica", 1, 12)); // NOI18N
        LabelAL.setText("Assembly Line");

        LabelWS.setFont(new java.awt.Font("Helvetica", 1, 12)); // NOI18N
        LabelWS.setText("Wave Soldering");

        LabelQC.setFont(new java.awt.Font("Helvetica", 1, 12)); // NOI18N
        LabelQC.setText("Quality Control");

        LabelT.setFont(new java.awt.Font("Helvetica", 1, 12)); // NOI18N
        LabelT.setText("Testing");

        LabelF.setFont(new java.awt.Font("Helvetica", 1, 12)); // NOI18N
        LabelF.setText("Final");

        LabelP.setFont(new java.awt.Font("Helvetica", 1, 12)); // NOI18N
        LabelP.setText("Packing");

        ALO.setForeground(new java.awt.Color(0, 0, 204));
        ALO.setText("09");

        WSO.setForeground(new java.awt.Color(0, 0, 204));
        WSO.setText("09");

        QCO.setForeground(new java.awt.Color(0, 0, 204));
        QCO.setText("09");

        TO.setForeground(new java.awt.Color(0, 0, 204));
        TO.setText("09");

        FO.setForeground(new java.awt.Color(0, 0, 204));
        FO.setText("09");

        PO.setForeground(new java.awt.Color(0, 0, 204));
        PO.setText("09");

        PWM.setForeground(new java.awt.Color(0, 0, 204));
        PWM.setText("09");

        ALM.setForeground(new java.awt.Color(0, 0, 204));
        ALM.setText("09");

        WSM.setForeground(new java.awt.Color(0, 0, 204));
        WSM.setText("09");

        QCM.setForeground(new java.awt.Color(0, 0, 204));
        QCM.setText("09");

        TM.setForeground(new java.awt.Color(0, 0, 204));
        TM.setText("09");

        FM.setForeground(new java.awt.Color(0, 0, 204));
        FM.setText("09");

        PM.setForeground(new java.awt.Color(0, 0, 204));
        PM.setText("09");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel8)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(PWM)
                                                    .addComponent(PWO))
                                                .addGap(78, 78, 78)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(ALM)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(WSM))
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(ALO)
                                                        .addGap(94, 94, 94)
                                                        .addComponent(WSO))))
                                            .addComponent(durationLable)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(LabelPW)
                                                .addGap(18, 18, 18)
                                                .addComponent(LabelAL)
                                                .addGap(18, 18, 18)
                                                .addComponent(LabelWS)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(LabelQC)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(LabelT))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(QCO)
                                                            .addComponent(QCM))
                                                        .addGap(69, 69, 69)
                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(TM)
                                                            .addComponent(TO, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(30, 30, 30)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(LabelF)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addGap(59, 59, 59)
                                                        .addComponent(LabelP))
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addGap(6, 6, 6)
                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(FM)
                                                            .addComponent(FO))
                                                        .addGap(54, 54, 54)
                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(PO, javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(PM, javax.swing.GroupLayout.Alignment.TRAILING))))))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(ALO)
                                    .addComponent(WSO)
                                    .addComponent(QCO)
                                    .addComponent(TO)
                                    .addComponent(FO)
                                    .addComponent(PO)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ALM)
                            .addComponent(WSM)
                            .addComponent(QCM)
                            .addComponent(TM)
                            .addComponent(PM)
                            .addComponent(FM)
                            .addComponent(PWM)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(durationLable))
                        .addGap(0, 5, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Final Details", jPanel2);

        workstation_Uti.setBackground(new java.awt.Color(229, 229, 229));
        workstation_Uti.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelutilization.setLayout(new java.awt.BorderLayout());
        workstation_Uti.add(panelutilization, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 930, 440));

        jTabbedPane1.addTab("Workstation Utilisation", workstation_Uti);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel1.setText("Date:");

        jLabel2.setText("01-01-2016");

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel3.setText("Time:");

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel4.setText("Production Planner:");

        jLabel5.setText("1.10 p.m.");

        jButton3.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/batchprosys/Icons/back.png"))); // NOI18N
        jButton3.setText("Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        showusername.setText("PP_0001");

        jButton2.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/batchprosys/Icons/ok.png"))); // NOI18N
        jButton2.setText("OK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(271, 271, 271)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showusername)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTabbedPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(showusername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addGap(87, 87, 87))
        );

        jLabel21.setFont(new java.awt.Font("Helvetica Neue", 0, 36)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(27, 78, 245));
        jLabel21.setText("VARIO");
        jLabel21.setSize(new java.awt.Dimension(70, 50));

        jLabel22.setFont(new java.awt.Font("Euphemia UCAS", 0, 36)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(153, 153, 153));
        jLabel22.setText("SYSTEMS");
        jLabel22.setSize(new java.awt.Dimension(70, 50));

        jLabel23.setFont(new java.awt.Font("Helvetica Neue", 0, 13)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 255));
        jLabel23.setText("ELECTRONICS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1012, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(330, 330, 330)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addContainerGap(337, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //dbOps.DeleteOrderPlan();
        //dbOps.Deletesectiondetails();
        JReportConnection jj=new JReportConnection();
        jj.generateReport("/Users/prathibha/Desktop/JAVA/BatchProSys/src/batchprosys/batchps.jasper");
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int p = JOptionPane.showConfirmDialog(null, "Do you really want to back to login screen?", "System Exit", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, qmark);
        if (p == 0) {
            Login2 L1 = new Login2();
            this.dispose();
            L1.setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //dbOps.DeleteOrderPlan();
        //dbOps.Deletesectiondetails();
        this.setVisible(false);
        ProductionPlanner PP2=new ProductionPlanner();
        PP2.setVisible(true);
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
            java.util.logging.Logger.getLogger(FinalReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FinalReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FinalReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FinalReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FinalReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ALM;
    private javax.swing.JLabel ALO;
    private javax.swing.JLabel FM;
    private javax.swing.JLabel FO;
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
    private javax.swing.JLabel QCM;
    private javax.swing.JLabel QCO;
    private javax.swing.JLabel TM;
    private javax.swing.JLabel TO;
    private javax.swing.JLabel WSM;
    private javax.swing.JLabel WSO;
    private javax.swing.JLabel durationLable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panelutilization;
    private javax.swing.JLabel showusername;
    private javax.swing.JTable tblFinalDetail;
    private javax.swing.JPanel workstation_Uti;
    // End of variables declaration//GEN-END:variables

    private void loadUtilization() {
        String arr[] = dbOps.getsectiondetails();
        
        double[] utilization = dbof.getAllUti(orderArticleNo,Integer.parseInt(arr[0]));
        DefaultCategoryDataset barchartdata=new DefaultCategoryDataset();
        //Stroke soild = new BasicStroke(80.0f);
        //barchartdata.setValue(0.0, "1", "prework");
        
        barchartdata.setValue(utilization[0], "presentage", "prework");
        barchartdata.setValue(utilization[1], "presentage", "assembly line");
        barchartdata.setValue(utilization[2], "presentage", "quality control");
        barchartdata.setValue(utilization[3], "presentage", "Wave Soldering");
        barchartdata.setValue(utilization[4], "presentage", "testing");
        barchartdata.setValue(utilization[5], "presentage", "final");
        barchartdata.setValue(utilization[6], "presentage", "packing");
        
        JFreeChart barchart=ChartFactory.createBarChart3D("workstation utilization", "work stations", "precentage", barchartdata, PlotOrientation.VERTICAL, false, true , false);
        CategoryPlot barchartz=barchart.getCategoryPlot();
        NumberAxis rangeAxis = (NumberAxis) barchartz.getRangeAxis();
        rangeAxis.setRange(0, 100);
        barchart.setBackgroundPaint(new Color(229, 229, 229));
        barchart.setBorderPaint(black);
        //barchart.setBackgroundImage(II);
        barchartz.setRangeGridlinePaint(white);
        ((BarRenderer)barchartz.getRenderer()).setBarPainter(new StandardBarPainter());

        BarRenderer r = (BarRenderer)barchart.getCategoryPlot().getRenderer();
        //r.setSeriesPaint(0, Color.blue);
        r.setSeriesPaint(0, Color.gray);
        r.setMaximumBarWidth(.06);
        
        
        
        ChartPanel barpanal=new ChartPanel(barchart);
        panelutilization.removeAll();
        panelutilization.add(barpanal);
        panelutilization.updateUI();
        panelutilization.validate();
        
        for(int x:orderArticleNo){
            System.out.println(x);
        }
    }
}