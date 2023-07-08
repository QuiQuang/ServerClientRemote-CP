package Client;

import javax.swing.*;
import java.io.IOException;

public class process_start extends javax.swing.JFrame {
    public process_start() {
        initComponents();
    }
    private void initComponents() {

        Procname = new javax.swing.JTextField();
        butStartProc = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Start A Process/Application");
        setPreferredSize(new java.awt.Dimension(455, 120));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        Procname.setText("INPUT NAME PROCESS");
        Procname.setFont(new java.awt.Font("Arial", 1,10));
        Procname.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        butStartProc.setText("START");
        butStartProc.setFont(new java.awt.Font("Times New Roman", 1,11));
        butStartProc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        butStartProc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butStartProcActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(Procname, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(butStartProc, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Procname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(butStartProc))
                                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }
    private void butStartProcActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Program.out.writeUTF("STARTPROC");
            Program.out.flush();
            Program.out.writeUTF(Procname.getText());
            Program.out.flush();

            String notif = Program.in.readUTF();
            JOptionPane.showMessageDialog(null, notif ,
                    "NOTE", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Something went wrong!" ,
                    "ERROR", JOptionPane.INFORMATION_MESSAGE);
        }
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

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new process_start().setVisible(true);
            }
        });
    }
    private javax.swing.JTextField Procname;
    private javax.swing.JButton butStartProc;
}
