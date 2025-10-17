package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountingLedger {

//creating an array list for the transactions class
    public static ArrayList<Transactions> transList = new ArrayList<>();

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

    /*creating a buffered reader to read the file
    adding try and catch so that errors are caught
    and they don't end the program prematurely */
        try{
        /*reading each line from transactions.csv and then converting each line into usable data and
        then creating a transactions object from each transaction listed in the csv, and we're also storing
        it in the array list*/
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


                /*declaring the variable "t", its data type is "Transactions" and your assigning the value of "t"
                to a new "Transactions" object, passing in the arguments into the constructor method()*/
                Transactions t = new Transactions(date, time, description, vendor, amount);
                transList.add(t);
            }
        }catch(Exception e){
            System.out.println("Invalid: Can't Read File");
            System.exit(0);


        }
        //while loop uses isRunning to keep the menu running
        //assigning boolean true to keep the menu running
        boolean isRunning = true;
        while (isRunning) {

            System.out.println("""
                    Welcome to the Account Ledger!
                    
                    Enter D) Add Deposit
                    Enter P) Make Payment (Debit)
                    Enter L) Ledger
                    Enter X) Exit Application
                    """);

            String input = scanner.nextLine();

            switch (input) {
                case "D":
                    addDeposit();
                    break;

                case "P":
                    makePayment();
                    break;

                case "L":
                    ledger();
                    break;

                case "X":
                    isRunning = false;//boolean false so that it exits the app instead of re-displaying the menu
                    break;

                default:
                    System.out.println("Invalid Input: Try Again!");//when things go wrong, this keeps it going(re-displays the menu)

            }
        }
    }

    //prompt the user for deposit information (date|time|description|vendor|amount) and save it to the csv file
    //also save it to the array list "transList"

    public static void addDeposit(){

        System.out.println("How much would you like to add?: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("From what vendor/store/place?: " );
        String vendor = scanner.nextLine();

        System.out.println("Provide a description/item: ");
        String item = scanner.nextLine();

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        Transactions t = new Transactions(date, time, item, vendor, amount);

        transList.add(t);

        storeInCsvFile(t);
    }
    //store the data into the csv file
    public static void storeInCsvFile(Transactions t) {
        try {
            FileWriter writer = new FileWriter("transactions.csv", true);
            writer.write(t.toCsv() + "\n"); // Write one line and go to next
            writer.close();
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    //prompting the user for a payment
    public static void makePayment() {

        System.out.println("What is the payment amount?: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        amount = -Math.abs(amount);//absolute number

        System.out.println("Who or what did you pay (vendor/store/person)?: ");
        String vendor = scanner.nextLine();

        System.out.println("Provide a short description (ex: Rent, Groceries, etc): ");
        String description = scanner.nextLine();

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        Transactions t = new Transactions(date, time, description, vendor, amount);
        transList.add(t);
        storeInCsvFile(t);

        System.out.println("Payment recorded successfully!");
        System.out.println();

        System.out.println("Press Enter to return to the main menu ");
        scanner.nextLine();
    }
    public static void ledger() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("""
                         Ledger Menu
                    
                    Enter A) All Transactions
                    Enter D) Deposits
                    Enter P) Payments
                    Enter R) Reports
                    Enter H) Home
                    """);

            String input = scanner.nextLine().toUpperCase();

            switch (input) {
                case "A":
                    showAllTransactions();
                    break;

                case "D":
                    showDeposits();
                    break;

                case "P":
                    showPayments();
                    break;

                case "R":
                    showReports();
                    break;

                case "H":
                    isRunning = false; // exit ledger menu
                    break;

                default:
                    System.out.println("Invalid Input: Try Again!");
            }
        }
    }
    // Displays every transaction stored in the transList
    public static void showAllTransactions() {
        System.out.println("All Transactions:");

        // Loop through each transaction and print its CSV line
        for (Transactions t : transList) {
            System.out.println(t.toCsv());
        }
        // Wait for user before returning to menu
        System.out.println("Press Enter to return...");
        scanner.nextLine();
    }
    // Displays only deposit transactions (amount > 0)
    public static void showDeposits() {
        System.out.println("Deposits Only:");

        //Loops through all transactions, and shows the positive ones
        for (Transactions t : transList) {
            if (t.getAmount() > 0) {
                System.out.println(t.toCsv());
            }
        }
        System.out.println("Press Enter to return");
        scanner.nextLine();
    }
    // Displays only payment transactions (amount < 0)
    public static void showPayments() {
        System.out.println("Payments Only:");

        //Loops through all transactions, and shows the negative ones
        for (Transactions t : transList) {
            if (t.getAmount() < 0) {
                System.out.println(t.toCsv());
            }
        }
        System.out.println("Press Enter to return");
        scanner.nextLine();
    }
    public static void showReports() {
        boolean isRunning = true; // keeps the reports menu active

        while (isRunning) {
            // Display the list of report options
            System.out.println("""
                    
                          Reports Menu
                    
                    Enter 1) Month to Date
                    Enter 2) Previous Month
                    Enter 3) Year to Date
                    Enter 4) Previous Year
                    Enter 5) Search by Vendor
                    Enter 0) Back to Ledger
                    """);

            // Take user input
            String input = scanner.nextLine();

            // Handle user choice
            switch (input) {
                case "1":
                    monthToDate();
                    break;
                case "2":
                    previousMonth();
                    break;
                case "3":
                    yearToDate();
                    break;
                case "4":
                    previousYear();
                    break;
                case "5":
                    searchByVendor();
                    break;
                case "0":
                    isRunning = false; // exits back to Ledger menu
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        }
    }
    public static void monthToDate() {
        System.out.println("Month-to-Date Transactions:");

        // Get the current date
        LocalDate now = LocalDate.now();

        //for every transaction t in transList
        // Loop through each transaction in the list
        for (Transactions t : transList) {
            // Check if transaction month and year match the current month/year
            if (t.getDate().getMonth() == now.getMonth() && t.getDate().getYear() == now.getYear()) {
                System.out.println(t.toCsv()); // print transaction details
            }
        }
    }
    public static void previousMonth(){
        System.out.println("Previous-Month-Transactions");

        //get the current month
        LocalDate now = LocalDate.now();
        LocalDate prevMonth = now.minusMonths(1);


        for(Transactions t : transList){

            if(t.getDate().getMonth()== prevMonth.getMonth() && t.getDate().getYear() == prevMonth.getYear()){
                System.out.println(t.toCsv());
            }
        }
        System.out.println("Press Enter to return");
        scanner.nextLine();
    }
    // Shows all transactions from the current year
    public static void yearToDate() {
        System.out.println("Year-to-Date Transactions:");
        int year = LocalDate.now().getYear();
        for (Transactions t : transList) {
            if (t.getDate().getYear() == year) {
                System.out.println(t.toCsv());
            }
        }
        System.out.println("Press Enter to return");
        scanner.nextLine();
    }
    // Shows all transactions from the previous year
    public static void previousYear() {
        System.out.println("Previous Year Transactions:");
        int prevYear = LocalDate.now().getYear() - 1;
        for (Transactions t : transList) {
            if (t.getDate().getYear() == prevYear) {
                System.out.println(t.toCsv());
            }
        }
        System.out.println("Press Enter to return");
        scanner.nextLine();
    }
    // Lets the user search for transactions by vendor name
    public static void searchByVendor() {
        System.out.print("Enter vendor name to search: ");
        String vendorName = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (Transactions t : transList) {
            if (t.getVendor().toLowerCase().contains(vendorName)) {
                System.out.println(t.toCsv());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No transactions found for vendor: " + vendorName);
        }

        System.out.println("Press Enter to return");
        scanner.nextLine();
    }
}
