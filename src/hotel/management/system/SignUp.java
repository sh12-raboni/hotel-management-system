package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SignUp extends JFrame implements ActionListener {

    JTextField userName, password, conPass;
    JButton login, cancle, signUP;

    SignUp() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // User Field =======
        JLabel user = new JLabel("UserName");
        user.setBounds(40, 40, 100, 30);
        add(user);

        userName = new JTextField();
        userName.setBounds(150, 40, 150, 30);
        add(userName);

        // Password Field =======
        JLabel pass = new JLabel("Password");
        pass.setBounds(40, 80, 100, 30);
        add(pass);

        password = new JPasswordField();
        password.setBounds(150, 80, 150, 30);
        add(password);

        JLabel comfirmPass = new JLabel("Comfirm");
        comfirmPass.setBounds(40, 120, 100, 30);
        add(comfirmPass);

        conPass = new JPasswordField();
        conPass.setBounds(150, 120, 150, 30);
        add(conPass);

        // Button ===========
        login = new JButton("Login");
        login.setBounds(40, 180, 120, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        login.setFont(new Font("aril", Font.PLAIN, 18));
        add(login);

        cancle = new JButton("Cancle");
        cancle.setBounds(180, 180, 120, 30);
        cancle.setBackground(Color.BLACK);
        cancle.setForeground(Color.WHITE);
        cancle.addActionListener(this);
        cancle.setFont(new Font("aril", Font.PLAIN, 18));
        add(cancle);

        // bg image ======
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 10, 200, 200);
        add(image);

        setBounds(500, 200, 600, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            String user = userName.getText();
            String pass = password.getText();
            String conf = conPass.getText();
//            System.out.println(conf);
//            System.out.println(pass);

            // Check if the username already exists
            if (!pass.equals(conf)) {
                JOptionPane.showMessageDialog(null, "Password not matched. Please try again.");
//                System.out.println(pass);
            } else {
                try {
                    Conn c = new Conn();
                    String checkQuery = "SELECT * FROM login WHERE username = '" + user + "'";
                    ResultSet checkRs = c.s.executeQuery(checkQuery);

                    if (checkRs.next()) {
                        // Username already exists, show a message
                        JOptionPane.showMessageDialog(null, "Username already exists. Choose a different username.");
                    } else {
                        // Username is unique, insert data into the database
                        String insertQuery = "INSERT INTO login(username, password) VALUES ('" + user + "', '" + pass + "')";
                        int rowsAffected = c.s.executeUpdate(insertQuery);

                        if (rowsAffected > 0) {
                            // Registration successful, proceed to login
                            setVisible(false);
                            new Dashboard().setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error in registration. Please try again.");
                        }
                    }
                }catch (Exception e) {
                e.printStackTrace();
            }


            }

        } else if (ae.getSource() == cancle) {
            setVisible(false);
        } else if (ae.getSource() == signUP) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new SignUp();
    }

}
