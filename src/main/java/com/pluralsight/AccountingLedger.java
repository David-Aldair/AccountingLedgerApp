package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class AccountingLedger {

//creating an array list for the transactions class
    private static ArrayList<Transactions> transList = new ArrayList<Transactions>();

    public static void main(String[] args) {

    /*creating a buffered reader to read the file
    adding try and catch so that errors are caught
    and they don't end the program prematurely */
        try{
            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
            String input;

        //while loop to split the transaction
            while((input = reader.readLine()) != null){
                String[] storage = input.split("\\|");

                LocalDate date = LocalDate.parse(storage[0]);
                LocalTime time = LocalTime.parse(storage[1]);
                String description = storage[2];
                String vendor = storage[3];
                double amount = Double.parseDouble(storage[4]);


                Transactions t = new Transactions(date, time, description, vendor, amount);
                transList.add(t);
            }
        }catch(Exception e){
            System.out.println("Invalid: Can't Read File");
            System.exit(0);


        }
    }




}
