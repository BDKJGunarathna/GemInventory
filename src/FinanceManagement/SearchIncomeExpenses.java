package FinanceManagement;

import java.time.LocalDate;

public class SearchIncomeExpenses {
    private Integer id;
    private String description;
    private String type;
    private LocalDate date;
    private Double amount;

    public SearchIncomeExpenses(Integer id, String description, String type, LocalDate date, Double amount) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.date = date;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDateString() {
        String dateString = date.toString();
        return dateString;
    }

    public Double getAmount() {
        return amount;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
