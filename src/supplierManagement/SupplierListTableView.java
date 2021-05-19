package supplierManagement;

import javafx.scene.control.Button;

public class SupplierListTableView {
    private int supplierID;
    private String s_name;
    private String s_phone;
    private String s_email;
    private String s_website;
    private String s_description;
    private String s_address;
    private String supp_gemstoneTypes;
    private String s_country;






    public SupplierListTableView(int supplierID, String s_name, String s_phone, String s_email, String s_website, String s_description, String s_address, String supp_gemstoneTypes, String s_country) {
        this.supplierID = supplierID;
        this.s_name = s_name;
        this.s_phone = s_phone;
        this.s_email = s_email;
        this.s_website = s_website;
        this.s_description = s_description;
        this.s_address = s_address;
        this.supp_gemstoneTypes = supp_gemstoneTypes;
        this.s_country = s_country;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_phone() {
        return s_phone;
    }

    public void setS_phone(String s_phone) {
        this.s_phone = s_phone;
    }

    public String getS_email() {
        return s_email;
    }

    public void setS_email(String s_email) {
        this.s_email = s_email;
    }

    public String getS_website() {
        return s_website;
    }

    public void setS_website(String s_website) {
        this.s_website = s_website;
    }

    public String getS_description() {
        return s_description;
    }

    public void setS_description(String s_description) {
        this.s_description = s_description;
    }

    public String getS_address() {
        return s_address;
    }

    public void setS_address(String s_address) {
        this.s_address = s_address;
    }

    public String getSupp_gemstoneTypes() {
        return supp_gemstoneTypes;
    }

    public void setSupp_gemstoneTypes(String supp_gemstoneTypes) {
        this.supp_gemstoneTypes = supp_gemstoneTypes;
    }

    public String getS_country() {
        return s_country;
    }

    public void setS_country(String s_country) {
        this.s_country = s_country;
    }
}
