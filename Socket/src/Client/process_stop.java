package Client;

import javax.swing.*;
import java.io.IOException;

public class process_stop extends javax.swing.JFrame {
    public process_stop() {
        initComponents();
    }
    private void initComponents() {

        ProcID = new javax.swing.JTextField();
        butStopProc = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Stop A Process/Application");
        setPreferredSize(new java.awt.Dimension(455, 120));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        ProcID.setText("INPUT ID PROCESS");
        ProcID.setFont(new java.awt.Font("Arial", 1,10));
        butStopProc.setText("STOP");
        butStopProc.setFont(new java.awt.Font("Times New Roman", 1,11));
        butStopProc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        butStopProc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butStopProcActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(ProcID, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(butStopProc, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(ProcID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(butStopProc))
                                .addContainerGap(63, Short.MAX_VALUE))
        );

        pack();
    }
    private void butStopProcActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Program.out.writeUTF("STOPPROC");
            Program.out.flush();
            Program.out.writeUTF(ProcID.getText());
            Program.out.flush();

            String notif = Program.in.readUTF();
            JOptionPane.showMessageDialog(null, notif , "NOTE", JOptionPane.INFORMATION_MESSAGE);
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
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(process_stop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(process_stop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(process_stop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(process_stop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new process_stop().setVisible(true);
            }
        });
    }
    private javax.swing.JTextField ProcID;
    private javax.swing.JButton butStopProc;
}
