
package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    
    JTextField userName,password;
    JButton login, cancle, signUP;
    
    Login(){
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
        
        // Button ===========
         login = new JButton("Login");
        login.setBounds(40,150,120,30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        login.setFont(new Font("aril", Font.PLAIN, 18));
        add(login);
        
        cancle = new JButton("Cancle");
        cancle.setBounds(180,150,120,30);
        cancle.setBackground(Color.BLACK);
        cancle.setForeground(Color.WHITE);
        cancle.addActionListener(this);
        cancle.setFont(new Font("aril", Font.PLAIN, 18));
        add(cancle);
        
        signUP = new JButton("Sign Up");
        signUP.setBounds(110,200,120,30);
        signUP.setBackground(Color.BLACK);
        signUP.setForeground(Color.WHITE);
        signUP.addActionListener(this);
        signUP.setFont(new Font("aril", Font.PLAIN, 18));
        add(signUP);
        
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
    
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == login){
            String user = userName.getText();
            String pass = password.getText();
            try{
//                Conn c = new Conn();
                String query = "select * from login where username= '"+user+"' and password = '"+pass+"'";
                ResultSet rs = c.s.executeQuery(query);
                if(rs.next()){
                    setVisible(false);
//                    new Dashboard();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
                
                
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else if(ae.getSource() == cancle){
            setVisible(false);
        }else if(ae.getSource() == signUP){
            setVisible(false);
            new SignUp().setVisible(true);
        }
    }
    
    public static void main(String[] args){
        new Login();
    }
    
}
