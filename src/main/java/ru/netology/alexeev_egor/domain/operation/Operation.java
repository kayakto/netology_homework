package ru.netology.alexeev_egor.domain.operation;

import ru.netology.alexeev_egor.domain.Customer;

public class Operation {
    int sum;
    String currency;
    String merchant;
    int id;
    Customer customer;


    public Operation(int sum, String currency, String merchant, int id) {
        this.sum = sum;
        this.currency = currency;
        this.merchant = merchant;
        this.id = id;
    }

    public Operation(int sum, String currency, String merchant, int id, Customer customer) {
        this.sum = sum;
        this.currency = currency;
        this.merchant = merchant;
        this.id = id;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "sum=" + sum +
                ", currency='" + currency + '\'' +
                ", merchant='" + merchant + '\'' +
                ", id=" + id +
                '}';
    }

    public Integer getCustomerId() {
        return customer.getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
