package supplierManagement;

import java.sql.Date;

public class ViewGemOrders {
    private int p_OrderId;
    private Date p_ordered_date;
    private int g_id;
    private String g_description;
    private double g_quantity;
    private int supp_id;
    private String g_sup_name;
    private Date expected_delivery_date;
    private String p_order_status;

    public ViewGemOrders(int p_OrderId, Date p_ordered_date, int g_id, String g_description, double g_quantity, int supp_id, String g_sup_name, Date expected_delivery_date, String p_order_status) {
        this.p_OrderId = p_OrderId;
        this.p_ordered_date = p_ordered_date;
        this.g_id = g_id;
        this.g_description = g_description;
        this.g_quantity = g_quantity;
        this.supp_id = supp_id;
        this.g_sup_name = g_sup_name;
        this.expected_delivery_date = expected_delivery_date;
        this.p_order_status = p_order_status;
    }

    public int getP_OrderId() {
        return p_OrderId;
    }

    public void setP_OrderId(int p_OrderId) {
        this.p_OrderId = p_OrderId;
    }

    public Date getP_ordered_date() {
        return p_ordered_date;
    }

    public void setP_ordered_date(Date p_ordered_date) {
        this.p_ordered_date = p_ordered_date;
    }

    public int getG_id() {
        return g_id;
    }

    public void setG_id(int g_id) {
        this.g_id = g_id;
    }

    public String getG_description() {
        return g_description;
    }

    public void setG_description(String g_description) {
        this.g_description = g_description;
    }

    public double getG_quantity() {
        return g_quantity;
    }

    public void setG_quantity(double g_quantity) {
        this.g_quantity = g_quantity;
    }

    public int getSupp_id() {
        return supp_id;
    }

    public void setSupp_id(int supp_id) {
        this.supp_id = supp_id;
    }

    public String getG_sup_name() {
        return g_sup_name;
    }

    public void setG_sup_name(String g_sup_name) {
        this.g_sup_name = g_sup_name;
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
}