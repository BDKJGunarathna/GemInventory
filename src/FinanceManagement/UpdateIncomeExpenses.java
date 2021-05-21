package FinanceManagement;

import java.util.Date;

public class UpdateIncomeExpenses {
    int id  ;
    String description ;
    String type ;
    Date date ;
    Double amount ;

    public UpdateIncomeExpenses(Integer id, String description, String type, Date date, Double amount) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.date = date;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}

