/**
 * Program description: Develop a Java application to manage the operations of a restaurant, focusing on customer orders and billing processes.
 *                      The system will handle customer information,menu items, and transaction records, including payment calculations
 * Programmer:FIEZAH 
 * Date:11 July 2024
 */

public class CustomerOrderInformation {
    // Declaration of attributes
    private int customerId;        // Stores the customer ID
    private String customerName;   // Stores the customer name
    private int tableNumber;       // Stores the table number
    private int orderId;           // Stores the order ID
    private String itemsName;      // Stores the name of the item ordered
    private double itemPrice;      // Stores the price of the item
    private int quantity;          // Stores the quantity of the item ordered
    private String orderTime;      // Stores the time of the order

    // Constructor without parameters (default constructor)
    public CustomerOrderInformation() {
        customerId = 0;
        customerName = null;
        tableNumber = 0;
        orderId = 0;
        itemsName = null;
        itemPrice = 0.0;
        quantity = 0;
        orderTime = null;
    }

    // Constructor with parameters
    public CustomerOrderInformation(int custId, String custName, int tableNum, int orderID, String itemName, double itemTotalPrice, int itemQuantity, String orderTime) {
        customerId = custId;
        customerName = custName;
        tableNumber = tableNum;
        orderId = orderID;
        itemsName = itemName;
        itemPrice = itemTotalPrice;
        quantity = itemQuantity;
        orderTime = orderTime;
    }

    // Setter for customer ID
    public void setCustomerId(int custId) {
        customerId = custId;
    }

    // Setter for customer name
    public void setCustomerName(String custName) {
        customerName = custName;
    }

    // Setter for table number
    public void setTableNumber(int tableNum) {
        tableNumber = tableNum;
    }

    // Setter for order ID
    public void setOrderId(int orderID) {
        orderId = orderID;
    }

    // Setter for item name
    public void setItemsName(String itemName) {
        itemsName = itemName;
    }

    // Setter for item price
    public void setItemPrice(double itemTotalPrice) {
        itemPrice = itemTotalPrice;
    }

    // Setter for item quantity
    public void setQuantity(int itemQuantity) {
        quantity = itemQuantity;
    }

    // Setter for order time
    public void setOrdersTime(String orderTime) {
        this.orderTime = orderTime; // Corrected the parameter name to avoid shadowing the instance variable
    }

    // Setter for all attributes
    public void setAll(int custId, String custName, int tableNum, int orderID, String itemName, double itemTotalPrice, int itemQuantity, String orderTime) {
        customerId = custId;
        customerName = custName;
        tableNumber = tableNum;
        orderId = orderID;
        itemsName = itemName;
        itemPrice = itemTotalPrice;
        quantity = itemQuantity;
        this.orderTime = orderTime; // Corrected the parameter name to avoid shadowing the instance variable
    }

    // Getter for customer ID
    public int getCustId() {
        return customerId;
    }

    // Getter for customer name
    public String getCustName() {
        return customerName;
    }

    // Getter for table number
    public int getTableNum() {
        return tableNumber;
    }

    // Getter for order ID
    public int getOrderID() {
        return orderId;
    }

    // Getter for item name
    public String getItemName() {
        return itemsName;
    }

    // Getter for item price
    public double getItemTotalPrice() {
        return itemPrice;
    }

    // Getter for item quantity
    public int getItemQuantity() {
        return quantity;
    }

    // Getter for order time
    public String getOrderTime() {
        return orderTime;
    }

    // Method to get customer queue information
    public String getCustQueueInfo() {
        return "Customer ID: " + customerId + 
               "\nName: " + customerName + 
               "\nOrder ID: " + orderId +
               "\nQuantity: " + quantity +
               "\nPrice: " + (itemPrice * quantity) + "\n";
    }

    // Method to get receipt information
    public String getReceiptInfo(String counter) {
        String data = "\n==============================================" +
                      "\nEzpresso Cafe RECEIPT" +
                      "\nOrder Time: " + orderTime;
        if (counter != null) {
            data += "\nCounter: " + counter;
        }
        data += "\n==============================================" +
                "\nCustomer ID: " + customerId + 
                "\nName: " + customerName +
                "\nTable Number: " + tableNumber +
                "\nOrder details" +
                "\nItem Name: " + itemsName + 
                "\nItem Price Per Each: RM " + itemPrice + 
                "\nQuantity: " + quantity + 
                "\nTotal Amount: RM " + (itemPrice * quantity);
        return data;
    }

    // Override toString method to provide receipt information
    @Override
    public String toString() {
        String data = "==============================================" +
                      "\nEzpresso Cafe RECEIPT" +
                      "\nOrder Time: " + orderTime + 
                      "\n==============================================" +
                      "\nCustomer ID: " + customerId + 
                      "\nName: " + customerName +
                      "\nTable Number: " + tableNumber +
                      "\nOrder details" +
                      "\nItem Name: " + itemsName + 
                      "\nItem Price Per Each: RM " + itemPrice + 
                      "\nQuantity: " + quantity + 
                      "\nTotal Amount: RM " + (itemPrice * quantity) +
                      "\n==============================================\n";
        return data;
    }
}