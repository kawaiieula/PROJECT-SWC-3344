/**
 * PROGRAM DESCRIPTION: TO CREATE A GUI SYSTEM AND QUEUE FOR EZPESSO CAFE
 *
 * Nur ku safiyyah dan Salman Faris
 * 11 July 2024
 */


import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Main class that extends JFrame and implements ActionListener for handling events
public class EzpressoCafeManagementSystem extends JFrame implements ActionListener {

    // Declare UI components and data structures
    private JFrame RamlyCounterFrame;
    private JLabel countLabel;
    private int count, i = 0;
    private JButton showDataButton, addNewCustomerButton, nextQueueButton;
    JTextArea queueContent1, queueContent2, queueContent3, receiptContent1, receiptContent2, receiptContent3, recordContent;
    JButton paymentButton1, paymentButton2, paymentButton3, receiptButton1, receiptButton2, receiptButton3, recordButton;
    JScrollPane scrollPane;

    // Queues for handling customer orders at different counters
    Queue<CustomerOrderInformation> queueCounter1 = new LinkedList<>();
    Queue<CustomerOrderInformation> queueCounter2 = new LinkedList<>();
    Queue<CustomerOrderInformation> queueCounter3 = new LinkedList<>();

    // Stack to keep track of completed orders
    Stack<CustomerOrderInformation> completeStack = new Stack<>();

    // List to store customer order information
    ArrayList<CustomerOrderInformation> custOrderList = new ArrayList<>();
    CustomerOrderInformation custOrderItem, currentCheckoutCust;

    // Constructor to set up the UI and initialize components
    public EzpressoCafeManagementSystem() {
        RamlyCounterFrame = new JFrame();
        // Set up the frame
        RamlyCounterFrame.setTitle("Ezpresso Cafe Management System ");
        RamlyCounterFrame.setSize(800, 400);
        RamlyCounterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RamlyCounterFrame.setLocationRelativeTo(null);
        RamlyCounterFrame.setVisible(true);

        // Set layout
        RamlyCounterFrame.setLayout(new BorderLayout());

        // North Panel for buttons and count label
        JPanel northPanel = new JPanel();
        countLabel = new JLabel("Count: " + count);
        showDataButton = new JButton("Show Customer Data");
        addNewCustomerButton = new JButton("Read All Customer Information");
        nextQueueButton = new JButton("Next Customer");
        northPanel.add(countLabel);
        addNewCustomerButton.addActionListener(this);
        northPanel.add(addNewCustomerButton);
        showDataButton.addActionListener(this);
        northPanel.add(showDataButton);        
        nextQueueButton.addActionListener(this);
        northPanel.add(nextQueueButton);
        RamlyCounterFrame.add(northPanel, BorderLayout.NORTH);

        // Center Panel with counters
        JPanel centerPanel = new JPanel(new GridLayout(1, 3));
        RamlyCounterFrame.add(centerPanel, BorderLayout.CENTER);

        // Counters
        JPanel counter1 = createCounterPanel("Counter 1", null);
        JPanel counter2 = createCounterPanel("Counter 2", null);
        JPanel counter3 = createCounterPanel("Counter 3", null);
        centerPanel.add(counter1);
        centerPanel.add(counter2);
        centerPanel.add(counter3);

        // South Panel with record button
        JPanel southPanel = new JPanel();
        recordButton = new JButton("Record");
        recordButton.addActionListener(this);
        southPanel.add(recordButton);
        RamlyCounterFrame.add(southPanel, BorderLayout.SOUTH);

        RamlyCounterFrame.setVisible(true);

        // Text area for receipts
        receiptContent1 = new JTextArea();
        receiptContent1.setEditable(false); 
        queueContent1.setText(""); 

        receiptContent2 = new JTextArea();
        receiptContent2.setEditable(false); 
        queueContent2.setText(""); 

        receiptContent3 = new JTextArea();
        receiptContent3.setEditable(false); 
        queueContent3.setText(""); 

        // Text area for records
        recordContent = new JTextArea();
        recordContent.setEditable(false); 
        recordContent.setText("");
    }

    // Method to handle button click events
    public void actionPerformed(ActionEvent e) {   
        if (e.getSource() == showDataButton) {
            new ShowData();              
        } else if (e.getSource() == addNewCustomerButton) {
            count = 0;
            // Create a file reader to read the input file
            try (BufferedReader br = new BufferedReader(new FileReader("cafe.txt"))) {
                // Declare inData to read a line in input file
                String inData;
                while ((inData = br.readLine()) != null) {
                    // Tokenize the input line
                    StringTokenizer tokenizer = new StringTokenizer(inData, ",");   
                    int customerId = Integer.parseInt(tokenizer.nextToken());
                    String customerName = tokenizer.nextToken();
                    int tableNumber = Integer.parseInt(tokenizer.nextToken());
                    int orderId = Integer.parseInt(tokenizer.nextToken());
                    String itemsName = tokenizer.nextToken();
                    double itemPrice = Double.parseDouble(tokenizer.nextToken());
                    int quantity = Integer.parseInt(tokenizer.nextToken());
                    String orderTime = tokenizer.nextToken();

                    // Create a CustomerOrderInformation object and add to the list
                    custOrderItem = new CustomerOrderInformation(customerId, customerName, tableNumber, orderId, itemsName, itemPrice, quantity, orderTime);
                    custOrderList.add(custOrderItem);
                    count++;
                    countLabel.setText("Count: " + count);
                }
            } catch (IOException error) {
                error.printStackTrace();
            }         
        } else if (e.getSource() != addNewCustomerButton && e.getSource() != showDataButton && e.getSource() != recordButton) {
            if (e.getSource() == nextQueueButton) {
                // Handle next customer queueing logic
                if (queueCounter1.size() == 5 && queueCounter2.size() == 5 && queueCounter3.size() == 5) {
                    JOptionPane.showMessageDialog(null, "All counters are full. Please checkout the customers.", "Counter is full!", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Queueing logic for different counters
                    custOrderItem = custOrderList.get(i);
                    if ((((queueCounter2.size() < 5 || queueCounter1.size() < 5 ) && queueCounter3.size() == 5) && custOrderList.get(i + 1).getItemQuantity() <= 5) || ((queueCounter3.size() < 5 && (queueCounter2.size() == 5 || queueCounter1.size() == 5))  && custOrderList.get(i + 1).getItemQuantity() > 5)) { 
                        CustomerOrderInformation tempObj;
                        tempObj = custOrderList.get(i);
                        custOrderItem = custOrderList.get(i + 1);
                        custOrderList.set(i+1, tempObj);
                    }                        
                    if (custOrderItem.getItemQuantity() > 5) { 
                        if (queueCounter3.size() < 5) {
                            i++;
                            queueCounter3.add(custOrderItem);
                        }
                    } else if (queueCounter2.size() < queueCounter1.size() && queueCounter2.size() < 5 && custOrderItem.getItemQuantity() <= 5) {
                        i++;
                        queueCounter2.add(custOrderItem);
                    } else if (queueCounter1.size() < 5 && custOrderItem.getItemQuantity() <= 5) {
                        i++;
                        queueCounter1.add(custOrderItem);
                    }
                }
            } else if (e.getSource() == paymentButton1 || e.getSource() == paymentButton2 || e.getSource() == paymentButton3) {
                // Handle payment logic
                if (e.getSource() == paymentButton1) {    
                    currentCheckoutCust = queueCounter1.poll();
                    receiptContent1.append(currentCheckoutCust.getReceiptInfo("Counter 1"));
                } else if (e.getSource() == paymentButton2) {
                    currentCheckoutCust = queueCounter2.poll();
                    receiptContent2.append(currentCheckoutCust.getReceiptInfo("Counter 2"));
                } else if (e.getSource() == paymentButton3) {
                    currentCheckoutCust = queueCounter3.poll();
                    receiptContent3.append(currentCheckoutCust.getReceiptInfo("Counter 3"));
                }

                if (currentCheckoutCust != null) {
                    count--;
                    countLabel.setText("Count: " + count);
                    completeStack.push(currentCheckoutCust);
                } else {
                    JOptionPane.showMessageDialog(null, "Queue is empty", "No customer on queue!", JOptionPane.ERROR_MESSAGE); 
                }
            } else {                    
                if (e.getSource() == receiptButton1) {
                    scrollPane = new JScrollPane(receiptContent1);
                } else if (e.getSource() == receiptButton2) {
                    scrollPane = new JScrollPane(receiptContent2);
                } else {
                    scrollPane = new JScrollPane(receiptContent3);
                }
                scrollPane.setPreferredSize(new Dimension(350, 450));
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                JOptionPane.showMessageDialog(null, scrollPane, "Display ", JOptionPane.INFORMATION_MESSAGE);
            }
            setContentInTextArea(queueCounter1, queueCounter2, queueCounter3);
        } else {
            while (!completeStack.isEmpty()) {
                CustomerOrderInformation orderInfo = completeStack.pop();
                recordContent.append(orderInfo.toString());
            }
            scrollPane = new JScrollPane(recordContent);
            scrollPane.setPreferredSize(new Dimension(350, 450));
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            JOptionPane.showMessageDialog(null, scrollPane, "Display All Records", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Method to create a panel for each counter
    private JPanel createCounterPanel(String title, String data) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        JPanel buttonPanel = new JPanel();

        // Set up the panel for Counter 1
        if (title == "Counter 1") {
            queueContent1 = new JTextArea();
            queueContent1.setEditable(false);
            queueContent1.setText(data); 
            panel.add(new JScrollPane(queueContent1), BorderLayout.CENTER);
            paymentButton1 = new JButton("Checkout " + title);        
            receiptButton1 = new JButton("Receipt ");
            paymentButton1.addActionListener(this);
            receiptButton1.addActionListener(this);
            buttonPanel.add(paymentButton1);
            buttonPanel.add(receiptButton1);
        } else if (title == "Counter 2") {
            // Set up the panel for Counter 2
            queueContent2 = new JTextArea();
            queueContent2.setEditable(false); 
            queueContent2.setText(data); 
            panel.add(new JScrollPane(queueContent2), BorderLayout.CENTER);
            paymentButton2 = new JButton("Checkout " + title);        
            receiptButton2 = new JButton("Receipt ");
            paymentButton2.addActionListener(this);
            receiptButton2.addActionListener(this);
            buttonPanel.add(paymentButton2);
            buttonPanel.add(receiptButton2);
        } else {
            // Set up the panel for Counter 3
            queueContent3 = new JTextArea();
            queueContent3.setEditable(false); 
            queueContent3.setText(data); 
            panel.add(new JScrollPane(queueContent3), BorderLayout.CENTER);
            paymentButton3 = new JButton("Checkout " + title);        
            receiptButton3 = new JButton("Receipt ");
            paymentButton3.addActionListener(this);
            receiptButton3.addActionListener(this);
            buttonPanel.add(paymentButton3);
            buttonPanel.add(receiptButton3);
        }
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    // Method to set content in text areas for queues
    private void setContentInTextArea(Queue q1, Queue q2, Queue q3) {
        queueContent1.setText(null);
        queueContent2.setText(null);
        queueContent3.setText(null);

        queueContent1.append(
            "-------------------------------------------------\n"
            + "Queue Customer For Counter 1" +
            "\n-------------------------------------------------\n"); 

        queueContent2.append(
            "-------------------------------------------------\n"
            + "Queue Customer For Counter 2" +
            "\n-------------------------------------------------\n");

        queueContent3.append( 
            "-------------------------------------------------\n"
            + "Queue Customer For Counter 3" +
            "\n-------------------------------------------------\n"); 

        // Convert queues to lists for iteration
        ArrayList<CustomerOrderInformation> listQ1 = new ArrayList<>(q1);
        ArrayList<CustomerOrderInformation> listQ2 = new ArrayList<>(q2);
        ArrayList<CustomerOrderInformation> listQ3 = new ArrayList<>(q3);

        // Append queue information to respective text areas
        for (int j = 0; j < listQ1.size(); j++) {
            queueContent1.append("============== Customer " + (j + 1) + " =============\n");
            CustomerOrderInformation currentOrder = listQ1.get(j);
            queueContent1.append(currentOrder.getCustQueueInfo());
        }
        for (int j = 0; j < listQ2.size(); j++) {
            queueContent2.append("============== Customer " + (j + 1) + " ============\n");
            CustomerOrderInformation currentOrder = listQ2.get(j);
            queueContent2.append(currentOrder.getCustQueueInfo());
        }
        for (int j = 0; j < listQ3.size(); j++) {
            queueContent3.append("============== Customer " + (j + 1) + " =============\n");
            CustomerOrderInformation currentOrder = listQ3.get(j);
            queueContent3.append(currentOrder.getCustQueueInfo());
        }
    }
}