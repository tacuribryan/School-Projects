package todo;
//emilio

import java.sql.*;
import javax.swing.*;
import org.apache.derby.jdbc.*;

public class TDGUI extends javax.swing.JFrame {
    private Connection con;
    private ResultSet rs;
    private Statement stmt;
    private String dbURI = "jdbc:derby:todolist;create=true";
    private String[] lnames = new String[2];
    
    public TDGUI() {
        initComponents();
        setDBSystemDir();
        createTable();
        displayToDo1();
    }
    
    private void setDBSystemDir(){
        //Decide on the database location
        String userHomeDir = System.getProperty("user.home", ".");
        String systemDir = userHomeDir + "/todolist";
        //set the db system property
        System.setProperty("derby.system.home", systemDir);
    }
    
    private void createTable(){
        try{
            DriverManager.registerDriver(new EmbeddedDriver());
            con = DriverManager.getConnection(dbURI);
            String sql = "create table todolist ("
                    + "details VARCHAR(200), date VARCHAR(50), notes VARCHAR(200), complete BOOLEAN"
                    + ")";
            stmt = con.createStatement();
            stmt.execute(sql);
            String sql1 = "create table todolist2 ("
                    + "details VARCHAR(200), date VARCHAR(50), notes VARCHAR(200), complete BOOLEAN"
                    + ")";
            stmt = con.createStatement();
            stmt.execute(sql1);
        }catch(SQLException e){
            if(e.getErrorCode()!=30000){
                //Error code 30000: table already exists - not an error!
                JOptionPane.showMessageDialog(this,e.getMessage());
            }
        }
    }
    
    private void getResultSet1(){
        try{
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("select * from todolist");
            //ResultSet is scrollable and updateable
            rs.first();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this,e.getMessage());
        }
    }
    
//    private void getResultSet2(){
//        try{
//            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
//            rs = stmt.executeQuery("select * from todolist2");
//            //ResultSet is scrollable and updateable
//            rs.first();
//        }catch(SQLException e){
//            JOptionPane.showMessageDialog(this,e.getMessage());
//        }
//    }
//    
    private void displayResults(){
        try{
            txtf_task1.setText(rs.getString("details"));
            txtf_date1.setText(rs.getString("date"));
            txta_notes1.setText(rs.getString("notes"));
            chk_comp.setSelected(rs.getBoolean("complete"));
        }catch(SQLException e){
            if(e.getErrorCode() == 20000){
                txtf_task1.setText("");
                txtf_date1.setText("");
                txta_notes1.setText("");
                chk_comp.setSelected(false);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_sidebar = new javax.swing.JPanel();
        btn_newt = new javax.swing.JButton();
        btn_modt = new javax.swing.JButton();
        btn_delt = new javax.swing.JButton();
        btn_current = new javax.swing.JButton();
        comb_lpicker = new javax.swing.JComboBox<>();
        btn_newl = new javax.swing.JButton();
        btn_dell = new javax.swing.JButton();
        btn_det = new javax.swing.JButton();
        pnl_content = new javax.swing.JPanel();
        pnl_welcome = new javax.swing.JPanel();
        scrpn_dir = new javax.swing.JScrollPane();
        jtp_dir = new javax.swing.JTextPane();
        pnl_genform = new javax.swing.JPanel();
        pnl_title = new javax.swing.JPanel();
        lbl_title = new javax.swing.JLabel();
        pnl_fields = new javax.swing.JPanel();
        lbl_task = new javax.swing.JLabel();
        txtf_task = new javax.swing.JTextField();
        lbl_date = new javax.swing.JLabel();
        txtf_date = new javax.swing.JTextField();
        lbl_notes = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txta_notes = new javax.swing.JTextArea();
        pnl_ins = new javax.swing.JPanel();
        btn_cancel = new javax.swing.JButton();
        btn_ins = new javax.swing.JButton();
        pnl_dispres = new javax.swing.JPanel();
        pnl_title3 = new javax.swing.JPanel();
        lbl_title1 = new javax.swing.JLabel();
        pnl_fields1 = new javax.swing.JPanel();
        lbl_task1 = new javax.swing.JLabel();
        txtf_task1 = new javax.swing.JTextField();
        lbl_date1 = new javax.swing.JLabel();
        txtf_date1 = new javax.swing.JTextField();
        lbl_notes1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txta_notes1 = new javax.swing.JTextArea();
        pnl_ins1 = new javax.swing.JPanel();
        btn_cancel1 = new javax.swing.JButton();
        btn_prev = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        chk_comp = new javax.swing.JCheckBox();
        pnl_list1 = new javax.swing.JPanel();
        pnl_title1 = new javax.swing.JPanel();
        lbl_titlel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_results = new javax.swing.JTable();
        pnl_list2 = new javax.swing.JPanel();
        pnl_title2 = new javax.swing.JPanel();
        lbl_titlel2 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_results1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnl_sidebar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn_newt.setText("new task");
        btn_newt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newtActionPerformed(evt);
            }
        });

        btn_modt.setText("modify task");
        btn_modt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modtActionPerformed(evt);
            }
        });

        btn_delt.setText("delete task");
        btn_delt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deltActionPerformed(evt);
            }
        });

        btn_current.setText("current list");
        btn_current.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_currentActionPerformed(evt);
            }
        });

        comb_lpicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comb_lpickerActionPerformed(evt);
            }
        });

        btn_newl.setText("new list");
        btn_newl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newlActionPerformed(evt);
            }
        });

        btn_dell.setText("delete list");

        btn_det.setText("show details");
        btn_det.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_detActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_sidebarLayout = new javax.swing.GroupLayout(pnl_sidebar);
        pnl_sidebar.setLayout(pnl_sidebarLayout);
        pnl_sidebarLayout.setHorizontalGroup(
            pnl_sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_sidebarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_newt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_modt, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                    .addComponent(btn_delt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_current, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comb_lpicker, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_newl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_dell, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_det, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnl_sidebarLayout.setVerticalGroup(
            pnl_sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_sidebarLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(comb_lpicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_newl)
                .addGap(5, 5, 5)
                .addComponent(btn_dell)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_current)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_det)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_newt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_modt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_delt)
                .addContainerGap())
        );

        pnl_content.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnl_content.setLayout(new java.awt.CardLayout());

        pnl_welcome.setLayout(new java.awt.BorderLayout());

        jtp_dir.setEditable(false);
        jtp_dir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtp_dir.setContentType("text/html"); // NOI18N
        jtp_dir.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtp_dir.setText("<html>\r\n  <head>\r\n\r<style>\nbody{ font-family: Tahoma, serif, sans-serif; }\ntable{ font-family: Tahoma, serif, sans-serif; margin: 2px}\nh2{ text-align: center; }\n#wrapper{ margin: 0 auto; padding: 0px 10px 10px 10px; text-align: center; }\n#mid{text-align: center; font-weight: bold; font-size: 11px; }\n</style>\n  </head>\r\n  <body>\r\n<h2>Directions:</h2>\n<div id=\"wrapper\">\n<table border=\"1\" width=\"100%\">\n<thead>\n<tr>\n<td id=\"mid\" colspan=\"2\">List Functions</td>\n</tr>\n</thead>\n<tr>\n<th width=\"25%\">name</th>\n<th>function</th>\n</tr>\n<tr>\n<td>new list</td>\n<td>creates a new to do list.</td>\n</tr>\n<tr>\n<td>delete list</td>\n<td>deletes selected list.</td>\n</tr>\n</table>\n\n<table border=\"1\" width=\"100%\">\n<thead>\n<tr>\n<td id=\"mid\" colspan=\"2\">Task Functions</td>\n</tr>\n</thead>\n<tr>\n<th width=\"25%\">name</th>\n<th>function</th>\n</tr>\n<tr>\n<td>new task</td>\n<td>displays a form to input task information.</td>\n</tr>\n<tr>\n<td>add task</td>\n<td>adds task information to selected list.</td>\n</tr>\n<tr>\n<td>modify task</td>\n<td>allows modification of the current task.</td>\n</tr>\n<tr>\n<td>delete task</td>\n<td>deletes the selected task.</td>\n</tr>\n</table>\n</div>\n  </body>\r\n</html>\r");
        jtp_dir.setOpaque(false);
        scrpn_dir.setViewportView(jtp_dir);

        pnl_welcome.add(scrpn_dir, java.awt.BorderLayout.CENTER);

        pnl_content.add(pnl_welcome, "card5");

        pnl_genform.setLayout(new java.awt.BorderLayout());

        lbl_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_title.setText("Enter task details:");

        javax.swing.GroupLayout pnl_titleLayout = new javax.swing.GroupLayout(pnl_title);
        pnl_title.setLayout(pnl_titleLayout);
        pnl_titleLayout.setHorizontalGroup(
            pnl_titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_titleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_title, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnl_titleLayout.setVerticalGroup(
            pnl_titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_titleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_title, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnl_genform.add(pnl_title, java.awt.BorderLayout.PAGE_START);

        lbl_task.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_task.setText("Task:");

        lbl_date.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_date.setText("Date:");

        lbl_notes.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_notes.setText("Notes:");

        txta_notes.setColumns(20);
        txta_notes.setRows(5);
        jScrollPane1.setViewportView(txta_notes);

        javax.swing.GroupLayout pnl_fieldsLayout = new javax.swing.GroupLayout(pnl_fields);
        pnl_fields.setLayout(pnl_fieldsLayout);
        pnl_fieldsLayout.setHorizontalGroup(
            pnl_fieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_fieldsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_fieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_notes)
                    .addComponent(lbl_date)
                    .addComponent(lbl_task))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_fieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtf_task)
                    .addComponent(txtf_date)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnl_fieldsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lbl_date, lbl_notes, lbl_task});

        pnl_fieldsLayout.setVerticalGroup(
            pnl_fieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_fieldsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_fieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_task)
                    .addComponent(txtf_task, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_fieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_date)
                    .addComponent(txtf_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_fieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_fieldsLayout.createSequentialGroup()
                        .addComponent(lbl_notes)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnl_genform.add(pnl_fields, java.awt.BorderLayout.CENTER);

        btn_cancel.setText("cancel");

        btn_ins.setText("insert");
        btn_ins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_insLayout = new javax.swing.GroupLayout(pnl_ins);
        pnl_ins.setLayout(pnl_insLayout);
        pnl_insLayout.setHorizontalGroup(
            pnl_insLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_insLayout.createSequentialGroup()
                .addContainerGap(312, Short.MAX_VALUE)
                .addComponent(btn_ins)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cancel)
                .addContainerGap())
        );
        pnl_insLayout.setVerticalGroup(
            pnl_insLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_insLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_insLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cancel)
                    .addComponent(btn_ins))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnl_genform.add(pnl_ins, java.awt.BorderLayout.PAGE_END);

        pnl_content.add(pnl_genform, "card2");

        pnl_dispres.setLayout(new java.awt.BorderLayout());

        lbl_title1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_title1.setText("Enter task details:");

        javax.swing.GroupLayout pnl_title3Layout = new javax.swing.GroupLayout(pnl_title3);
        pnl_title3.setLayout(pnl_title3Layout);
        pnl_title3Layout.setHorizontalGroup(
            pnl_title3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_title3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_title1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnl_title3Layout.setVerticalGroup(
            pnl_title3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_title3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_title1, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnl_dispres.add(pnl_title3, java.awt.BorderLayout.PAGE_START);

        lbl_task1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_task1.setText("Task:");

        lbl_date1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_date1.setText("Date:");

        lbl_notes1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_notes1.setText("Notes:");

        txta_notes1.setColumns(20);
        txta_notes1.setRows(5);
        jScrollPane4.setViewportView(txta_notes1);

        javax.swing.GroupLayout pnl_fields1Layout = new javax.swing.GroupLayout(pnl_fields1);
        pnl_fields1.setLayout(pnl_fields1Layout);
        pnl_fields1Layout.setHorizontalGroup(
            pnl_fields1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_fields1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_fields1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_notes1)
                    .addComponent(lbl_date1)
                    .addComponent(lbl_task1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_fields1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtf_task1)
                    .addComponent(txtf_date1)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnl_fields1Layout.setVerticalGroup(
            pnl_fields1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_fields1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_fields1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_task1)
                    .addComponent(txtf_task1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_fields1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_date1)
                    .addComponent(txtf_date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_fields1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_fields1Layout.createSequentialGroup()
                        .addComponent(lbl_notes1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnl_dispres.add(pnl_fields1, java.awt.BorderLayout.CENTER);

        btn_cancel1.setText("cancel");

        btn_prev.setText("previous");
        btn_prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_prevActionPerformed(evt);
            }
        });

        btn_next.setText("next");
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });

        chk_comp.setText("completed?");

        javax.swing.GroupLayout pnl_ins1Layout = new javax.swing.GroupLayout(pnl_ins1);
        pnl_ins1.setLayout(pnl_ins1Layout);
        pnl_ins1Layout.setHorizontalGroup(
            pnl_ins1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_ins1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chk_comp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addComponent(btn_next)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_prev)
                .addGap(71, 71, 71)
                .addComponent(btn_cancel1)
                .addContainerGap())
        );
        pnl_ins1Layout.setVerticalGroup(
            pnl_ins1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ins1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_ins1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cancel1)
                    .addComponent(btn_prev)
                    .addComponent(btn_next)
                    .addComponent(chk_comp))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnl_dispres.add(pnl_ins1, java.awt.BorderLayout.PAGE_END);

        pnl_content.add(pnl_dispres, "card2");

        pnl_list1.setLayout(new java.awt.BorderLayout());

        lbl_titlel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnl_title1Layout = new javax.swing.GroupLayout(pnl_title1);
        pnl_title1.setLayout(pnl_title1Layout);
        pnl_title1Layout.setHorizontalGroup(
            pnl_title1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_title1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_titlel1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnl_title1Layout.setVerticalGroup(
            pnl_title1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_title1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_titlel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnl_list1.add(pnl_title1, java.awt.BorderLayout.PAGE_START);

        tbl_results.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tbl_results);

        pnl_list1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        pnl_content.add(pnl_list1, "card3");

        pnl_list2.setLayout(new java.awt.BorderLayout());

        lbl_titlel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnl_title2Layout = new javax.swing.GroupLayout(pnl_title2);
        pnl_title2.setLayout(pnl_title2Layout);
        pnl_title2Layout.setHorizontalGroup(
            pnl_title2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_title2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_titlel2, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnl_title2Layout.setVerticalGroup(
            pnl_title2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_title2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_titlel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnl_list2.add(pnl_title2, java.awt.BorderLayout.PAGE_START);

        tbl_results1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tbl_results1);

        pnl_list2.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        pnl_content.add(pnl_list2, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnl_sidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_content, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnl_content, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_newtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newtActionPerformed
        if(comb_lpicker.getSelectedIndex() == 0){
//            try{
//                getResultSet1();
//                rs.moveToInsertRow();
//            }catch(SQLException e){
//                JOptionPane.showMessageDialog(this, e.getMessage());
//            }
//        }else{
//            try{
//                getResultSet2();
//                rs.moveToInsertRow();
//            }catch(SQLException e){
//                JOptionPane.showMessageDialog(this, e.getMessage());
//            }
//        }
            pnl_genform.setVisible(true);
            pnl_dispres.setVisible(false);
            pnl_list1.setVisible(false);
            pnl_welcome.setVisible(false);
            pnl_list2.setVisible(false);
            pack();
        try{
            rs.moveToInsertRow();
            displayResults();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        }
    }//GEN-LAST:event_btn_newtActionPerformed

    private void btn_insActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insActionPerformed
        try{
            AddTask task = new AddTask(txtf_task.getText(), txtf_date.getText(), txta_notes.getText(), false);
            rs.updateString("details", task.getTask());
            rs.updateString("date", task.getDate());
            rs.updateString("notes", task.getNotes());
            rs.updateBoolean("complete", task.isCompleted());
            rs.insertRow();
            rs.close();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("select * from todolist");
            rs.first();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }finally{
            pnl_genform.setVisible(false);
            pnl_dispres.setVisible(false);
            pnl_list1.setVisible(true);
            pnl_list2.setVisible(false);
            pnl_welcome.setVisible(false);
            txtf_task.setText("");
            txtf_date.setText("");
            txta_notes.setText("");
            repaint();
            ExecuteThread eThread = new ExecuteThread();
            eThread.start();
        }
    }//GEN-LAST:event_btn_insActionPerformed

    private void btn_currentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_currentActionPerformed
        pnl_genform.setVisible(false);
        pnl_dispres.setVisible(false);
        pnl_list1.setVisible(true);
        pnl_list2.setVisible(false);
        pnl_welcome.setVisible(false);
        ExecuteThread eThread = new ExecuteThread();
        eThread.start();
    }//GEN-LAST:event_btn_currentActionPerformed

    private void btn_deltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deltActionPerformed
        try{
            int dialogResult = JOptionPane.showConfirmDialog(this, "Delete Record?","Warning",JOptionPane.YES_NO_OPTION);
            if(dialogResult == 0){
                rs.first();
                int currentRow = rs.getRow() - 1;
                if(currentRow == 0)
                    currentRow = 1;
                rs.deleteRow();
                rs.close();
                con.setAutoCommit(false);
                rs = stmt.executeQuery("select * from todolist");
                rs.absolute(currentRow);
                displayResults();
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        finally{
           ExecuteThread eThread = new ExecuteThread();
           eThread.start(); 
        }
    }//GEN-LAST:event_btn_deltActionPerformed

    private void btn_modtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modtActionPerformed
        try{
            AddTask task = new AddTask(txtf_task1.getText(), txtf_date1.getText(), txta_notes1.getText(), chk_comp.isSelected());
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs.first();
            rs.updateString("details", task.getTask());
            rs.updateString("date", task.getDate());
            rs.updateString("notes", task.getNotes());
            rs.updateBoolean("complete", task.isCompleted());
            rs.updateRow();
            //int currentRow = rs.getRow();
            rs.close();
            con.setAutoCommit(false);
            rs = stmt.executeQuery("select * from todolist");
            //rs.absolute(currentRow);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }finally{
             ExecuteThread eThread = new ExecuteThread();
           eThread.start(); 
        }
    }//GEN-LAST:event_btn_modtActionPerformed

    private void btn_newlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newlActionPerformed
        boolean decision = true;
        while(decision){
            lnames[0] = JOptionPane.showInputDialog(this, "Set a name for the first list:");
            comb_lpicker.addItem(LetterManipulation.toTitleCase(lnames[0]));
            lbl_titlel1.setText(LetterManipulation.toTitleCase(lnames[0]));
            pnl_genform.setVisible(false);
            pnl_dispres.setVisible(false);
            pnl_list1.setVisible(false);
            pnl_list2.setVisible(false);
            pnl_welcome.setVisible(false);
            comb_lpicker.setSelectedIndex(0);
            int dec = JOptionPane.showConfirmDialog(this, "Would you like to add another list?");
            if(dec == JOptionPane.YES_OPTION){
                lnames[1] = JOptionPane.showInputDialog(this, "Set a name for the second list:");
                comb_lpicker.addItem(LetterManipulation.toTitleCase(lnames[1]));
                lbl_titlel2.setText(LetterManipulation.toTitleCase(lnames[1]));
                pnl_genform.setVisible(false);
                pnl_dispres.setVisible(false);
                pnl_list1.setVisible(false);
                pnl_list2.setVisible(false);
                pnl_welcome.setVisible(false);
                comb_lpicker.setSelectedIndex(1);
            }
            decision = false;
        }
        comb_lpicker.setModel(new DefaultComboBoxModel(lnames));
    }//GEN-LAST:event_btn_newlActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        try{
            if(rs.next()){
                displayResults();
            }else{
                rs.first();
                displayResults();
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        chk_comp.setSelected(false);
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_prevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prevActionPerformed
        try{
            if(rs.previous()){
                displayResults();
            }else{
                rs.first();
                displayResults();
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        chk_comp.setSelected(false);
    }//GEN-LAST:event_btn_prevActionPerformed

    private void btn_detActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_detActionPerformed
        pnl_welcome.setVisible(false);
        pnl_genform.setVisible(false);
        pnl_list1.setVisible(false);
        pnl_list2.setVisible(false);
        pnl_dispres.setVisible(true);
        displayResults();
        
    }//GEN-LAST:event_btn_detActionPerformed

    private void comb_lpickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comb_lpickerActionPerformed
        if(comb_lpicker.getSelectedIndex() == 0){
            pnl_welcome.setVisible(false);
            pnl_genform.setVisible(false);
            pnl_list1.setVisible(true);
            pnl_list2.setVisible(false);
            pnl_dispres.setVisible(false);
        } else if(comb_lpicker.getSelectedIndex() == 1){
            pnl_welcome.setVisible(false);
            pnl_genform.setVisible(false);
            pnl_list1.setVisible(false);
            pnl_list2.setVisible(true);
            pnl_dispres.setVisible(false);
        }
    }//GEN-LAST:event_comb_lpickerActionPerformed
    
    public void displayToDo1(){
        try{
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String sqlTest = "select * from todolist";
            ResultSet rs = stmt.executeQuery(sqlTest);
            tbl_results.setModel(new ResultSetTableModel(rs));
            rs.first();  
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
//    public void displayToDo2(){
//        try{
//            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
//            String sqlTest = "select * from todolist2";
//            ResultSet rs = stmt.executeQuery(sqlTest);
//            tbl_results1.setModel(new ResultSetTableModel(rs));
//            rs.first();  
//            
//        }catch(Exception e){
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
//    }
    
    class ExecuteThread extends Thread{
        @Override
        public void run(){
            displayToDo1();
        }
    }
    
    
    
    public static void main(String args[]) {
        /* Set the Windows look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TDGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TDGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TDGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TDGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TDGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_cancel1;
    private javax.swing.JButton btn_current;
    private javax.swing.JButton btn_dell;
    private javax.swing.JButton btn_delt;
    private javax.swing.JButton btn_det;
    private javax.swing.JButton btn_ins;
    private javax.swing.JButton btn_modt;
    private javax.swing.JButton btn_newl;
    private javax.swing.JButton btn_newt;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_prev;
    private javax.swing.JCheckBox chk_comp;
    private javax.swing.JComboBox<String> comb_lpicker;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextPane jtp_dir;
    private javax.swing.JLabel lbl_date;
    private javax.swing.JLabel lbl_date1;
    private javax.swing.JLabel lbl_notes;
    private javax.swing.JLabel lbl_notes1;
    private javax.swing.JLabel lbl_task;
    private javax.swing.JLabel lbl_task1;
    private javax.swing.JLabel lbl_title;
    private javax.swing.JLabel lbl_title1;
    private javax.swing.JLabel lbl_titlel1;
    private javax.swing.JLabel lbl_titlel2;
    private javax.swing.JPanel pnl_content;
    private javax.swing.JPanel pnl_dispres;
    private javax.swing.JPanel pnl_fields;
    private javax.swing.JPanel pnl_fields1;
    private javax.swing.JPanel pnl_genform;
    private javax.swing.JPanel pnl_ins;
    private javax.swing.JPanel pnl_ins1;
    private javax.swing.JPanel pnl_list1;
    private javax.swing.JPanel pnl_list2;
    private javax.swing.JPanel pnl_sidebar;
    private javax.swing.JPanel pnl_title;
    private javax.swing.JPanel pnl_title1;
    private javax.swing.JPanel pnl_title2;
    private javax.swing.JPanel pnl_title3;
    private javax.swing.JPanel pnl_welcome;
    private javax.swing.JScrollPane scrpn_dir;
    private javax.swing.JTable tbl_results;
    private javax.swing.JTable tbl_results1;
    private javax.swing.JTextArea txta_notes;
    private javax.swing.JTextArea txta_notes1;
    private javax.swing.JTextField txtf_date;
    private javax.swing.JTextField txtf_date1;
    private javax.swing.JTextField txtf_task;
    private javax.swing.JTextField txtf_task1;
    // End of variables declaration//GEN-END:variables
}
