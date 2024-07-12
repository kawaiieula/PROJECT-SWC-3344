/**
 * PROGRAM DESCRIPTION: TO CREATE A SHOW DATA FOR THE EZPRESSO CAFE MANAGEMENT SYSTEM
 *
 * Nur ku safiyyah
 * 11 July 2024
 */


import java.io.*;
import javax.swing.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowData extends JFrame implements ActionListener {
    private JButton closeWindowButton;
    private JFrame frame;

    public ShowData() {
        // Create the frame
        frame = new JFrame("\n+++++++++++++++" + "Display customer order" + "\n+++++++++++++++");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500); // Set the size of the frame
        frame.setLocationRelativeTo(null);

        // Read data from the file
        String data = "";
        try (BufferedReader br = new BufferedReader(new FileReader("cafe.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {

                StringTokenizer tokenizer = new StringTokenizer(line, ",");   
                int customerId = Integer.parseInt(tokenizer.nextToken());
                String customerName = tokenizer.nextToken();
                int tableNumber = Integer.parseInt(tokenizer.nextToken());
                int orderId = Integer.parseInt(tokenizer.nextToken());
                String itemsName = tokenizer.nextToken();
                double itemPrice = Double.parseDouble(tokenizer.nextToken());
                int quantity = Integer.parseInt(tokenizer.nextToken());
                String ordersTime = tokenizer.nextToken();
                String formattedData = "\n======================================\n" + "Customer ID: " + customerId + "\n" +
                    "Customer Name: " + customerName + "\n" +
                    "Table No.: " + tableNumber + "\n" +
                    "Order ID: " + orderId + "\n" +
                    "Products: " + itemsName + "\n" +
                    "Price: " + itemPrice + "\n" +
                    "Quantity: " + quantity + "\n" +
                    "Order Date & Time: " + ordersTime +"\n" +
                    "\n======================================\n";

                data+=formattedData;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a text area to display the data
        JTextArea textArea = new JTextArea(data.toString());
        textArea.setEditable(false); // Make the text area read-only

        // Add the text area to a scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea);
        // Set the scroll pane to use vertical scrollbar
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Create a panel with a BorderLayout
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER); // Add text area scroll pane to the center

        // Create a panel for the button
        JPanel panelButton = new JPanel();
        closeWindowButton = new JButton("Close");
        closeWindowButton.addActionListener(this);
        panelButton.add(closeWindowButton);

        // Add the button panel to the bottom of the frame
        panel.add(panelButton, BorderLayout.SOUTH);

        // Add the main panel to the frame
        frame.add(panel);

        // Make the frame visible
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {  
        if (e.getSource() == closeWindowButton) {
            // Close the window
            frame.dispose();
        }
    }
}