package FinanceManagement;


import java.util.Date;

public class AssetsLiabilities {

    private Integer assetsliabilities_id;
    private String  assetsliabilities_description;
    private String assetsliabilities_type;
    private Date assetsliabilities_date;
    private Double assetsliabilities_amount;

    public AssetsLiabilities(Integer assetsliabilities_id, String assetsliabilities_description, String assetsliabilities_type, Date assetsliabilities_date, Double assetsliabilities_amount) {
        this.assetsliabilities_id = assetsliabilities_id;
        this.assetsliabilities_description = assetsliabilities_description;
        this.assetsliabilities_type = assetsliabilities_type;
        this.assetsliabilities_date = assetsliabilities_date;
        this.assetsliabilities_amount = assetsliabilities_amount;
    }

    public Integer getAssetsliabilities_id() {
        return assetsliabilities_id;
    }

    public String getAssetsliabilities_description() {
        return assetsliabilities_description;
    }

    public String getAssetsliabilities_type() {
        return assetsliabilities_type;
    }

    public Date getAssetsliabilities_date() {
        return assetsliabilities_date;
    }

    public Double getAssetsliabilities_amount() {
        return assetsliabilities_amount;
    }
}