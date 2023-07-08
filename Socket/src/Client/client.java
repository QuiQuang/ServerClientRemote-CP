package Client;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class client extends javax.swing.JFrame {
    private JPanel panel1;
    public client() {
        initComponents();
    }
    private void initComponents() {

        IPAddr = new javax.swing.JTextField();
        butConnect = new javax.swing.JButton();
        butProcess = new javax.swing.JButton();
        butApplication = new javax.swing.JButton();
        butKeypress = new javax.swing.JButton();
        butScreen = new javax.swing.JButton();
        butShutdown = new javax.swing.JButton();
        butExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main - Client");

        setPreferredSize(new java.awt.Dimension(500, 400));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        IPAddr.setText("ENTER IP HERE !!!");
        IPAddr.setFont(new java.awt.Font("Arial", 1, 13));
        IPAddr.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
        IPAddr.setForeground(Color.BLACK);

        butConnect.setText("CONNECT");
        butConnect.setFont(new java.awt.Font("Arial", 1, 13));
        butConnect.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        butConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butConnectActionPerformed(evt);
            }
        });

        butProcess.setText("RUNNING PROCESSES");
        butProcess.setFont(new java.awt.Font("Arial", 1, 11));
        butProcess.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        butProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butProcessActionPerformed(evt);
            }
        });

        butApplication.setText("RUNNING APPLICATIONS");
        butApplication.setFont(new java.awt.Font("Arial", 1, 11));
        butApplication.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        butApplication.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butApplicationActionPerformed(evt);
            }
        });

        butKeypress.setText("HOOK");
        butKeypress.setFont(new java.awt.Font("Arial", 1, 11));
        butKeypress.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        butKeypress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butKeypressActionPerformed(evt);
            }
        });

        butScreen.setText("SCREENSHOT");
        butScreen.setFont(new java.awt.Font("Arial", 1, 11));
        butScreen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        butScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butScreenActionPerformed(evt);
            }
        });

        butShutdown.setText("TURN OFF");
        butShutdown.setFont(new java.awt.Font("Arial", 1, 11));
        butShutdown.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        butShutdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butShutdownActionPerformed(evt);
            }
        });

        butExit.setText("EXIT");
        butExit.setFont(new java.awt.Font("Arial", 1, 11));
        butExit.setToolTipText("");
        butExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        butExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(butScreen, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(butApplication, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(butShutdown, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(butExit, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(butKeypress, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addComponent(butProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(IPAddr, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(butConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(137, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(IPAddr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(butConnect))
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(butProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(butScreen, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(92, 92, 92)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(butShutdown, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(butExit, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addComponent(butKeypress, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(butApplication, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(154, Short.MAX_VALUE))
        );

        pack();
    }

    private String receiveSignal() {
        String signal;
        try {
            signal = Program.in.readUTF();
        } catch (IOException e) {
            signal = "QUIT";
        }
        return signal;
    }

    private void sendSignal(String signal) {
        try {
            Program.out.writeUTF(signal);
            Program.out.flush();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Something went wrong!",
                    "ERROR", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void butConnectActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Program.Client = new Socket(IPAddr.getText(), 1234);

            JOptionPane.showMessageDialog(null, "Successfully connect to Server.",
                    "NOTE", JOptionPane.INFORMATION_MESSAGE);

            Program.in = new DataInputStream(Program.Client.getInputStream());
            Program.out = new DataOutputStream(Program.Client.getOutputStream());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error connect to Server.",
                    "ERROR", JOptionPane.INFORMATION_MESSAGE);
            Program.Client = null;
        }
    }
    private void butProcessActionPerformed(java.awt.event.ActionEvent evt) {
        if (Program.Client == null) {
            JOptionPane.showMessageDialog(null, "Server is not connected.",
                    "NOTE", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String signal = "PROCESS";
        sendSignal(signal);
        process.main(new String[0]);
    }
    private void butApplicationActionPerformed(java.awt.event.ActionEvent evt) {
        if (Program.Client == null) {
            JOptionPane.showMessageDialog(null, "Server is not connected.",
                    "NOTE", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String signal = "APPLICATION";
        sendSignal(signal);
        application.main(new String[0]);
    }
    private void butScreenActionPerformed(java.awt.event.ActionEvent evt) {
        if (Program.Client == null) {
            JOptionPane.showMessageDialog(null, "Server is not connected.",
                    "NOTE", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String signal = "SCREENSHOT";
        sendSignal(signal);
        screenshot.main(new String[0]);
    }
    private void butKeypressActionPerformed(java.awt.event.ActionEvent evt) {
        if (Program.Client == null) {
            JOptionPane.showMessageDialog(null, "Server is not connected.",
                    "NOTE", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String signal = "KEYLOG";
        sendSignal(signal);
        keylog.main(new String[0]);
    }
    private void butShutdownActionPerformed(java.awt.event.ActionEvent evt) {
        if (Program.Client == null) {
            JOptionPane.showMessageDialog(null, "Server is not connected.",
                    "NOTE", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String signal = "SHUTDOWN";
        sendSignal(signal);
        Program.Client = null;
    }
    private void butExitActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        if (Program.Client != null) {
            String signal = "QUIT";
            sendSignal(signal);
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
            java.util.logging.Logger.getLogger(client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new client().setVisible(true);
            }
        });
    }
    private javax.swing.JTextField IPAddr;
    private JButton button1;
    private JButton button3;
    private JButton button4;
    private JButton button6;
    private JButton button2;
    private javax.swing.JButton butApplication;
    private javax.swing.JButton butConnect;
    private javax.swing.JButton butExit;
    private javax.swing.JButton butKeypress;
    private javax.swing.JButton butProcess;
    private javax.swing.JButton butScreen;
    private javax.swing.JButton butShutdown;
    // End of variables declaration//GEN-END:variables
}
