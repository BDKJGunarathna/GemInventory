package Salesmanagement;

public class sellers {

    String idSellers;
    String sellerName;
    int no_of_sales;
    String telephone;
    float total_amount;
    float sales_comission;




    public sellers(String idSellers, String sellerName, int no_of_sales, String telephone, float total_amount, float sales_comission) {

        this.idSellers = idSellers;
        this.sellerName = sellerName;
        this.no_of_sales = no_of_sales;
        this.telephone = telephone;
        this.total_amount = total_amount;
        this.sales_comission = sales_comission;
    }

    public int getNo_Of_sales() {
        return no_of_sales;
    }

    public String getId() {
        return idSellers;
    }

    public String getName() {
        return sellerName;
    }

    public String getTp() {
        return telephone;
    }

    public float getTot_amount() {
        return total_amount;
    }

    public float getCom() {
        return sales_comission;
    }

    public void setNo_Of_sales(int no_Of_sales) {
        no_of_sales = no_Of_sales;
    }

    public void setId(String id) {
        this.idSellers = id;
    }

    public void setName(String name) {
        this.sellerName = name;
    }

    public void setTp(String tp) {
        this.telephone = tp;
    }

    public void setTot_amount(float tot_amount) {
        this.total_amount = tot_amount;
    }

    public void setCom(float com) {
        this.sales_comission = com;
    }
}

