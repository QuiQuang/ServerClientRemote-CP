package Client;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class screenshot extends javax.swing.JFrame {
    public screenshot() {
        initComponents();
    }
    private void initComponents() {

        pictureBox = new javax.swing.JLabel();
        butTake = new javax.swing.JButton();
        butSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Screenshot");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pictureBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        butTake.setText("CAPTURE");
        butTake.setFont(new java.awt.Font("Arial", 1,11));
        butTake.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        butTake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butTakeActionPerformed(evt);
            }
        });

        butSave.setText("SAVE");
        butSave.setFont(new java.awt.Font("Arial", 1,11));
        butSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        butSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(pictureBox, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(50, 50, 50)
                                                .addComponent(butTake, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(butSave, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(pictureBox, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(butTake, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                                        .addComponent(butSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(43, Short.MAX_VALUE))
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
    public void takeScreenshot(){
        try {
            Program.out.writeUTF("TAKE");
            Program.out.flush();

            int size = Program.in.readInt();
            byte[] imageAr = new byte[size];

            Program.in.readFully(imageAr);

            BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
            Image img = image.getScaledInstance(pictureBox.getWidth(), pictureBox.getHeight(),
                    Image.SCALE_SMOOTH);
            pictureBox.setIcon(new ImageIcon(img));

        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Something went wrong!" ,
                    "ERROR", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void butTakeActionPerformed(java.awt.event.ActionEvent evt) {
        takeScreenshot();
    }
    private void butSaveActionPerformed(java.awt.event.ActionEvent evt) {
        JFrame parentFrame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save");
        int userSelection = fileChooser.showSaveDialog(parentFrame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            BufferedImage img = new BufferedImage(pictureBox.getWidth(), pictureBox.getHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = img.createGraphics();
            pictureBox.printAll(g2d);
            g2d.dispose();

            try {
                ImageIO.write(img, "png", fileToSave);
            }
            catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error download image." ,
                        "ERROR", JOptionPane.INFORMATION_MESSAGE);
            }
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
            java.util.logging.Logger.getLogger(screenshot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(screenshot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(screenshot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(screenshot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new screenshot().setVisible(true);
            }
        });
    }
    private javax.swing.JButton butSave;
    private javax.swing.JButton butTake;
    private javax.swing.JLabel pictureBox;
}
