/**
 * PROGRAM DESCRIPTION: TO CREATE A WELCOME PAGE FOR EZPRESSO CAFE
 *
 * Nur ku safiyyah
 * 11 JULY 2024
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class WelcomePage extends JFrame implements ActionListener
{
    JButton startButton;
    public WelcomePage()
    {
        //Create a tittle for cinema
        setTitle("Welcome to Ezpresso Cafe Management System");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //Create image icon logo
        ImageIcon logo = new ImageIcon("cafe.jpg");
        JLabel logoLabel = new JLabel(logo);
        //Create a Panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel);
        //Create Label
        JLabel label = new JLabel("Welcome to Ezpresso Cafe Management System",
                SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(label, BorderLayout.CENTER);
        //Create Start Button
        startButton = new JButton("Start");
        //Register to a listener
        startButton.addActionListener(this);
        panel.add(startButton, BorderLayout.SOUTH);
        startButton.setBounds(200, 250, 100, 40);
        panel.add(logoLabel);
        setVisible(true);
    }//end of constructor
    //method overriding
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == startButton) {
            dispose();
            new EzpressoCafeManagementSystem();
        }//end of if
    }//end of method
    
    public static void main(String[] args)
    {
        WelcomePage wp = new WelcomePage();
    }//end of main
}//end of class