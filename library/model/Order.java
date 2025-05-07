package library.model;

import java.util.Date;

/**
 * Order
 * Represents a rental order placed by a customer.
 */
public class Order {
    private int orderId;       // PK
    private int customerId;    // FK → Customer.CustomerID
    private Date orderDate;
    private Date dueDate;
    private Date returnDate;
    private double totalFee;
    private String status;     // e.g., "Rented", "Returned"
    private int processedBy;   // FK → Staff.StaffID

    /**
     * @param orderId     unique ID
     * @param customerId  customer who placed it
     * @param orderDate   date placed
     * @param dueDate     expected return date
     * @param returnDate  actual return date
     * @param totalFee    total rental fee
     * @param status      order status
     * @param processedBy staff who processed
     */
    public Order(int orderId, int customerId, Date orderDate,
                 Date dueDate, Date returnDate,
                 double totalFee, String status, int processedBy) {
        this.orderId     = orderId;
        this.customerId  = customerId;
        this.orderDate   = orderDate;
        this.dueDate     = dueDate;
        this.returnDate  = returnDate;
        this.totalFee    = totalFee;
        this.status      = status;
        this.processedBy = processedBy;
    }

    public int getOrderId()       { return orderId; }
    public int getCustomerId()    { return customerId; }
    public Date getOrderDate()    { return orderDate; }
    public Date getDueDate()      { return dueDate; }
    public Date getReturnDate()   { return returnDate; }
    public double getTotalFee()   { return totalFee; }
    public String getStatus()     { return status; }
    public int getProcessedBy()   { return processedBy; }

    public void setOrderId(int id)            { this.orderId = id; }
    public void setCustomerId(int cid)        { this.customerId = cid; }
    public void setOrderDate(Date d)          { this.orderDate = d; }
    public void setDueDate(Date d)            { this.dueDate = d; }
    public void setReturnDate(Date d)         { this.returnDate = d; }
    public void setTotalFee(double f)         { this.totalFee = f; }
    public void setStatus(String s)           { this.status = s; }
    public void setProcessedBy(int sid)       { this.processedBy = sid; }
}
