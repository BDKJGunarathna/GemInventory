package FinanceManagement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class IncomeExpenses {
    private final Integer id;
    private final StringProperty description;
    private final String type;
    private final Date date;
    private final Double amount;

    public IncomeExpenses(Integer id, String description, String type, Date date, Double amount) {
        this.id = id;
        this.description = new SimpleStringProperty(description);
        this.type = type;
        this.date = date;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public StringProperty getDescription() {
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


}
