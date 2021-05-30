package OrderManagement;

import java.sql.Date;

public class Order {
    //declare variables
    private int id;
    private String invoiceNo;
    private int cusId;
    private int proId;
    private String proName;
    private double unPrice;
    private int qty;
    private double total;
    private Date date;
    private String status;

    //constructor
    public Order(int id, String invoiceNo, int cusId,int proId, String proName,   double unPrice, int qty, double total, Date date, String status) {
        this.id = id;
        this.invoiceNo = invoiceNo;
        this.cusId = cusId;
       this.proId = proId;
        this.proName = proName;
        this.unPrice = unPrice;
        this.qty = qty;
        this.total = total;
        this.date = date;
        this.status = status;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public double getUnPrice() {
        return unPrice;
    }

    public void setUnPrice(double unPrice) {
        this.unPrice = unPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
