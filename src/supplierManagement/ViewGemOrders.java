package supplierManagement;

import java.util.Date;

public class ViewGemOrders {
    private int p_OrderId;
    private int g_id;
    private int supp_id;
    private String g_description;
    private Date p_ordered_date;
    private double g_quantity;
    private Date expected_delivery_date;
    private String p_order_status;
    private String g_sup_name;

    public ViewGemOrders(int p_OrderId, java.sql.Date p_ordered_date, int supp_id, int g_id, String g_description, double g_quantity, String p_order_status, java.sql.Date expected_delivery_date, String g_sup_name) {
        this.p_OrderId = p_OrderId;
        this.g_id = g_id;
        this.supp_id = supp_id;
        this.g_description = g_description;
        this.p_ordered_date = p_ordered_date;
        this.g_quantity = g_quantity;
        this.expected_delivery_date = expected_delivery_date;
        this.p_order_status = p_order_status;
        this.g_sup_name = g_sup_name;
    }


    public double getG_quantity() {
        return g_quantity;
    }

    public void setG_quantity(double g_quantity) {
        this.g_quantity = g_quantity;
    }

    public int getP_OrderId() {
        return p_OrderId;
    }

    public void setP_OrderId(int p_OrderId) {
        this.p_OrderId = p_OrderId;
    }

    public int getG_id() {
        return g_id;
    }

    public void setG_id(int g_id) {
        this.g_id = g_id;
    }

    public int getSupp_id() {
        return supp_id;
    }

    public void setSupp_id(int supp_id) {
        this.supp_id = supp_id;
    }

    public String getG_description() {
        return g_description;
    }

    public void setG_description(String g_description) {
        this.g_description = g_description;
    }

    public Date getP_ordered_date() {
        return p_ordered_date;
    }

    public void setP_ordered_date(Date p_ordered_date) {
        this.p_ordered_date = p_ordered_date;
    }



    public Date getExpected_delivery_date() {
        return expected_delivery_date;
    }

    public void setExpected_delivery_date(Date expected_delivery_date) {
        this.expected_delivery_date = expected_delivery_date;
    }

    public String getP_order_status() {
        return p_order_status;
    }

    public void setP_order_status(String p_order_status) {
        this.p_order_status = p_order_status;
    }

    public String getG_sup_name() {
        return g_sup_name;
    }

    public void setG_sup_name(String g_sup_name) {
        this.g_sup_name = g_sup_name;
    }
}