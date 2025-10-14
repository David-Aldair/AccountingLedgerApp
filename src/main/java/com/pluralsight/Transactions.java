package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

//this transactions class represents a transaction recorded
public class Transactions {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    //constructor initializes the value of the transaction variables
    public Transactions(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }
}
