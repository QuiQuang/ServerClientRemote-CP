package Server;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.util.Optional;

public class server extends javax.swing.JFrame {
    public server() {
        initComponents();
    }
    private void initComponents() {

        butOpenServer = new Button();
        label1 = new Label();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main - Server");
        setPreferredSize(new Dimension(275, 140));
        setResizable(false);

        butOpenServer.setCursor(new Cursor(Cursor.HAND_CURSOR));

        butOpenServer.setLabel("ACTIVE");
        butOpenServer.setFont(new java.awt.Font("Arial", 1, 12));
        butOpenServer.setPreferredSize(new Dimension(100, 40));
        butOpenServer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                butOpenServerActionPerformed(evt);
            }
        });


        String txt;
        try{
            InetAddress IP= InetAddress.getLocalHost();
            txt = "IP : " + IP.getHostAddress();
        }catch (Exception e){
            txt = "Not found!";
        }

        label1.setAlignment(1);
        label1.setText(txt);
        label1.setFont(new java.awt.Font("Times New Roman", 1, 13));
        /*label1.setText("Nhấn nút dưới đây để mở kết nối");*/

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(73, 73, 73)
                                                .addComponent(butOpenServer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(36, 36, 36)
                                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(49, Short.MAX_VALUE)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(butOpenServer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(200, 200, 200))
        );

        pack();
    }
    private String receiveSignal(){
        String signal;
        try {
            signal = Program.in.readUTF();
        }
        catch (IOException e) {
            signal = "QUIT";
        }
        return signal;
    }
    private void sendSignal(String signal){
        try {
            Program.out.writeUTF(signal);
            Program.out.flush();
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "Error connect to client." , "Lỗi", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void process(){
        try {
            String signal_proc;
            Process p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe /fo csv /nh");

            while(true)
            {
                //nhan tin hieu tu client
                signal_proc = receiveSignal();
                switch(signal_proc){
                    case "VIEW":
                    {
                        //lay lai danh sach cac process dang chay
                        p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe /fo csv /nh");

                        //gui cho client thong tin tung process
                        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                        String line;
                        while((line = input.readLine())!=null){
                            String[] info = line.split("\",");
                            //gui ten cua process
                            sendSignal(info[0].substring(1));
                            //gui id cua process
                            sendSignal(info[1].substring(1));
                            //gui memory usage cua process
                            sendSignal(info[4].substring(1).replaceFirst(".$",""));
                        }
                        //gui tin hieu bao cho client la da het danh sach process
                        sendSignal("END");
                    }
                    break;
                    case "START":
                    {
                        boolean quit = false;
                        while(!quit)
                        {
                            //nhan tin hieu tu client
                            String signal_start = receiveSignal();
                            switch(signal_start){
                                case "STARTPROC":
                                {
                                    //lay ten process tu client
                                    String proc_name = receiveSignal();

                                    if(!"".equals(proc_name) && !"QUIT".equals(proc_name))
                                    {
                                        try
                                        {
                                            Process process = new ProcessBuilder(proc_name + ".exe").start();
                                            sendSignal("Sucessfully opend " + proc_name + ".");
                                        }
                                        catch(IOException e)
                                        {
                                            sendSignal("Error cannot open " + proc_name + ".");
                                        }
                                    }
                                    else
                                    {
                                        sendSignal("No process to open.");
                                    }
                                }
                                break;
                                case "QUIT":
                                    quit = true;
                                    break;
                            }
                        }
                    }
                    break;
                    case "STOP":
                    {
                        boolean quit = false;
                        while(!quit)
                        {
                            //nhan tin hieu tu client
                            String signal_stop = receiveSignal();
                            switch(signal_stop){
                                case "STOPPROC":
                                {
                                    //lay ID tu client
                                    String proc_id = receiveSignal();

                                    if(!"".equals(proc_id) && !"QUIT".equals(proc_id))
                                    {
                                        try
                                        {
                                            long pid = Long.parseLong(proc_id);
                                            Optional<ProcessHandle> optionalProcessHandle = ProcessHandle.of(pid);
                                            optionalProcessHandle.ifPresent(processHandle -> processHandle.destroy());
                                            sendSignal("Successfully stopped process.");
                                        }
                                        catch (NumberFormatException e)
                                        {
                                            sendSignal("Error to stop process.");
                                        }
                                    }
                                    else
                                    {
                                        sendSignal("Please enter ID.");
                                    }
                                }
                                break;
                                case "QUIT":
                                    quit = true;
                                    break;
                            }
                        }
                    }
                    break;
                    case "QUIT":
                        return;
                }
            }
        }
        catch(IOException e) {
            JOptionPane.showMessageDialog(null, "Something went wrong!" , "Lỗi", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void application(){
        try {
            String signal_app;
            //lay danh sach cac process dang chay
            Process p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe /fo csv /nh");

            while(true)
            {
                signal_app = receiveSignal();
                switch(signal_app){
                    case "VIEW":
                    {
                        //lay lai danh sach cac process dang chay
                        p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe /v /fo csv /nh");

                        //gui cho client thong tin tung process
                        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                        String line;
                        while((line = input.readLine())!=null){
                            String[] info = line.split("\",");
                            //check xem process do co window title khong -> check co phai application khong
                            if(!"N/A".equals(info[8].substring(1).replaceFirst(".$","")))
                            {
                                //gui ten cua process
                                sendSignal(info[0].substring(1));
                                //gui id cua process
                                sendSignal(info[1].substring(1));
                                //gui memory usage cua process
                                sendSignal(info[4].substring(1));
                            }
                        }
                        //gui tin hieu bao cho client la da het danh sach process
                        sendSignal("END");
                    }
                    break;
                    case "START":
                    {
                        boolean quit = false;
                        while(!quit)
                        {
                            //nhan tin hieu tu client
                            String signal_start = receiveSignal();
                            switch(signal_start){
                                case "STARTPROC":
                                {
                                    //lay ten application tu client
                                    String app_name = receiveSignal();

                                    if(!"".equals(app_name) && !"QUIT".equals(app_name))
                                    {
                                        try
                                        {
                                            Process process = new ProcessBuilder(app_name + ".exe").start();
                                            sendSignal("Successfully run app " + app_name + ".");
                                        }
                                        catch(IOException e)
                                        {
                                            sendSignal("Error to run " + app_name + ".");
                                        }
                                    }
                                    else
                                    {
                                        sendSignal("No app to run.");
                                    }
                                }
                                break;
                                case "QUIT":
                                    quit = true;
                                    break;
                            }
                        }
                    }
                    break;
                    case "STOP":
                    {
                        boolean quit = false;
                        while(!quit)
                        {
                            //nhan tin hieu tu client
                            String signal_stop = receiveSignal();
                            switch(signal_stop){
                                case "STOPPROC":
                                {
                                    //lay ID tu client
                                    String proc_id = receiveSignal();

                                    if(!"".equals(proc_id) && !"QUIT".equals(proc_id))
                                    {
                                        try
                                        {
                                            long pid = Long.parseLong(proc_id);
                                            Optional<ProcessHandle> optionalProcessHandle = ProcessHandle.of(pid);
                                            optionalProcessHandle.ifPresent(processHandle -> processHandle.destroy());
                                            sendSignal("Successfully stop app.");
                                        }
                                        catch (NumberFormatException e)
                                        {
                                            sendSignal("Error to stop app.");
                                        }
                                    }
                                    else
                                    {
                                        sendSignal("No app to run.");
                                    }
                                }
                                break;
                                case "QUIT":
                                    quit = true;
                                    break;
                            }
                        }
                    }
                    break;
                    case "QUIT":
                        return;
                }
            }

        }
        catch(IOException e) {
            JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra" , "Lỗi", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void screenshot(){
        try {
            String signal_ss;

            while(true)
            {
                signal_ss = receiveSignal();
                switch(signal_ss){
                    case "TAKE":
                    {
                        //chup man hinh
                        BufferedImage screen = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ImageIO.write(screen,"png",baos);

                        int size = baos.size();
                        Program.out.writeInt(size);
                        Program.out.flush();
                        Program.out.write(baos.toByteArray());
                        Program.out.flush();
                    }
                    break;
                    case "QUIT":
                        return;
                }
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Something went wrong!" , "Lỗi", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void keylog(){
        Keylogger thread = new Keylogger();
        thread.start();
        Boolean unhook = true;

        String signal_key;
        while(true) {
            signal_key = receiveSignal();
            switch(signal_key){
                case "HOOK": {
                    unhook = false;
                    try {
                        PrintWriter out = new PrintWriter(Keylogger.file.toString());
                        out.print("");
                        out.close();
                    }
                    catch (FileNotFoundException ex) {
                    }
                }
                break;
                case "UNHOOK":
                    unhook = true;
                    break;
                case "PRINT": {
                    if(!unhook) {
                        try {
                            String str = Files.readString(Keylogger.file);
                            if("".equals(str))
                                str = "\0";
                            sendSignal(str);

                            PrintWriter out = new PrintWriter(Keylogger.file.toString());
                            out.print("");
                            out.close();
                        }
                        catch (Exception ex) {
                        }
                    }
                    else {
                        sendSignal("\0");
                    }
                }
                break;
                case "QUIT":
                    return;
            }
        }

    }
    private void shutdown(){
        try {
            Runtime.getRuntime().exec("shutdown.exe -s -t 0");
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Something went wrong!" , "Lỗi", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void butOpenServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butOpenServerActionPerformed
        try {
            Program.Server = new ServerSocket(1234);
            JOptionPane.showMessageDialog(null, "Succesfully active server.", "NOTE", JOptionPane.INFORMATION_MESSAGE);
            Program.Client = Program.Server.accept();
            JOptionPane.showMessageDialog(null, "Successfully connect to Client.", "NOTE", JOptionPane.INFORMATION_MESSAGE);
            Program.in = new DataInputStream(Program.Client.getInputStream());
            Program.out = new DataOutputStream(Program.Client.getOutputStream());

            String signal;
            while(true)
            {
                signal = receiveSignal();
                switch(signal){
                    case "PROCESS":
                        process();
                        break;
                    case "APPLICATION":
                        application();
                        break;
                    case "SCREENSHOT":
                        screenshot();
                        break;
                    case "KEYLOG":
                        keylog();
                        break;
                    case "SHUTDOWN":
                        shutdown();
                        break;
                    case "QUIT":
                    {
                        Program.Client.close();
                        Program.Server.close();
                        return;
                    }
                }
            }

        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Something wen wrong!" , "ERROR", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_butOpenServerActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new server().setVisible(true);
            }
        });
    }

    private java.awt.Button butOpenServer;
    private java.awt.Label label1;
}
