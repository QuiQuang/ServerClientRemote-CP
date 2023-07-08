package Client;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.IOException;

public class process extends javax.swing.JFrame {
    public process() {
        initComponents();
    }
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        procListView = new javax.swing.JTable();
        butView = new javax.swing.JButton();
        butStart = new javax.swing.JButton();
        butStop = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Running Processes");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        procListView.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null},
                        {null, null, null},
                },
                new String [] {
                        "NAME", "ID", "MEMORY"
                }
        ));
        procListView.setRowHeight(20);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for(int i=1;i<3;i++){
            procListView.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        }

        JTableHeader tableHeader = procListView.getTableHeader();
        tableHeader.setForeground(Color.black);
        tableHeader.setFont(new Font("Calibre", 1, 12 ));
        tableHeader.setAlignmentY(1);
        procListView.setFont(new Font("Arial",0,11));
        jScrollPane1.setViewportView(procListView);
        TableCellRenderer rendererFromHeader = procListView.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        butView.setText("VIEW");
        butView.setFont(new java.awt.Font("Times New Roman", 1,11));
        butView.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        butView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butViewActionPerformed(evt);
            }
        });

        butStart.setText("START");
        butStart.setFont(new java.awt.Font("Times New Roman", 1,11));
        butStart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        butStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butStartActionPerformed(evt);
            }
        });

        butStop.setText("STOP");
        butStop.setFont(new java.awt.Font("Times New Roman", 1,11));
        butStop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        butStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butStopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(butView, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(butStart, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(butStop, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(29, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(butView, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                                        .addComponent(butStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(butStop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
        );

        pack();
    }
    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        try {
            Program.out.writeUTF("QUIT");
            Program.out.flush();
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Something went wrong!" , "ERROR", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void butViewActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Program.out.writeUTF("VIEW");
            Program.out.flush();

            String app_name;
            String app_ID;
            String mem_use;

            DefaultTableModel dm = (DefaultTableModel)procListView.getModel();
            while(dm.getRowCount() > 0)
                dm.removeRow(0);
            while(true)
            {
                app_name = Program.in.readUTF();
                if("END".equals(app_name))
                    return;
                else
                {
                    app_ID = Program.in.readUTF();
                    mem_use = Program.in.readUTF();

                    DefaultTableModel model = (DefaultTableModel) procListView.getModel();
                    model.addRow(new Object[]{app_name, app_ID, mem_use});
                }
            }
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Something went wrong!" ,
                    "ERROR", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void butStartActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Program.out.writeUTF("START");
            Program.out.flush();

            process_start.main(new String[0]);
        }
        catch(IOException e) {
            JOptionPane.showMessageDialog(null, "Something went wrong!" ,
                    "ERROR", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void butStopActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Program.out.writeUTF("STOP");
            Program.out.flush();

            process_stop.main(new String[0]);
        }
        catch(IOException e) {
            JOptionPane.showMessageDialog(null, "Something went wrong!" ,
                    "ERROR", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(process.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(process.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(process.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(process.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new process().setVisible(true);
            }
        });
    }
    private javax.swing.JButton butStart;
    private javax.swing.JButton butStop;
    private javax.swing.JButton butView;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable procListView;
    // End of variables declaration//GEN-END:variables
}
