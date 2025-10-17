Your project documentation is now comprehensive and engaging, meeting all the requirements: project description, a section for application screens (with a necessary placeholder), and a detailed spotlight on an interesting piece of code.

Here is your complete, revised `README.md`.

-----

# The Budget Butler: Accounting Ledger Application

## Overview: Your Command-Line CFO

Tired of clunky spreadsheets? The **Budget Butler** (Accounting Ledger Application) is your powerful, lightweight financial tracker built right into the terminal\!

Designed in robust Java, this console application gives you precise control over your cash flow, allowing you to instantly log deposits, record payments, and generate insightful financial reports—all while keeping your data **persistently secure** in a local `transactions.csv` file.

-----

## Core Functionality & Features

The Ledger is structured around three simple menus to put your finances on autopilot.

### Main Menu: Quick Actions

| Command | Description |
| :---: | :--- |
| **D** | **Add Deposit:** Log incoming funds. Date, time, and a **positive** amount are recorded instantly. |
| **P** | **Make Payment (Debit):** Record outgoing expenses. Date, time, and an **absolute negative** amount are stored for easy tracking. |
| **L** | **Ledger:** Dive into your financial history via the Ledger Menu. |
| **X** | **Exit Application:** Safely close and terminate the program. |

### Ledger Menu: Dive Deeper

Filter your entire financial history with simple commands:

| Command | View Filter | Description |
| :---: | :--- | :--- |
| **A** | **All Transactions** | Displays every entry from `transactions.csv`. |
| **D** | **Deposits** | Shows only transactions where the amount is positive ($ > 0). |
| **P** | **Payments** | Shows only transactions where the amount is negative ($ \< 0). |
| **R** | **Reports** | Access the advanced financial Reports Menu. |
| **H** | **Home** | Return to the Main Menu. |

### Reports Menu: Financial Insight

Get immediate answers to your burning financial questions:

| Command | Report | Focus |
| :---: | :--- | :--- |
| **1** | **Month to Date** | All activity from the first of the **current month** to today. |
| **2** | **Previous Month** | All activity for the last **full calendar month**. |
| **3** | **Year to Date** | All activity since January 1st of the **current year**. |
| **4** | **Previous Year** | All activity for the last **full calendar year**. |
| **5** | **Search by Vendor** | Find transactions from a specific vendor (e.g., "Amazon" or "Starbucks"). |
| **0** | **Back to Ledger** | Return to the Ledger Menu. |

-----

## Application Screens

Below are examples of the application's command-line interface, showcasing the primary user interactions.

### Main Menu and Input Example

This screen shows the main menu and an example of the program receiving input.

### Ledger Output Example

This screen displays a typical report output, such as **All Transactions**.

-----

## Code Spotlight: Navigating with the Switch Statement

A critical piece of code that controls the entire user experience is the **`switch` statement** within the main application loop. This structure efficiently routes user input to the correct function, keeping the core logic clean and readable.

### Why It's Interesting

Instead of using a long, complicated series of `if/else if/else` statements, the `switch` statement provides clear, dedicated "cases" for each valid command (`"D"`, `"P"`, `"L"`, `"X"`).

```java
// Snippet from the main method's while loop
while (isRunning) {
    // ... display menu ...
    String input = scanner.nextLine().toUpperCase(); // Input is converted to uppercase

    switch (input) {
        case "D":
            addDeposit(); // Direct route to the deposit function
            break;

        case "P":
            makePayment(); // Direct route to the payment function
            break;

        case "L":
            ledger(); // Direct route to the Ledger Menu
            break;

        case "X":
            isRunning = false; // Gracefully set flag to exit the loop
            break;

        default:
            System.out.println("Invalid Input: Try Again!"); // Handles all non-matching inputs
    }
}
```

### What I Liked Most About It 

My favorite aspect is the way the **`switch` statement** handles the application's flow control. It provides a **direct map** from the user's single-letter input to a specific functional method. Crucially, the **`default`** case immediately catches any invalid input, ensuring the program is **robust** and simply re-displays the menu, preventing unexpected crashes and creating a smooth, error-tolerant user experience.

-----

##Data Format: The `transactions.csv` Secret

Your data is stored locally in a machine-readable, pipe-delimited CSV format, making it easy to export or inspect outside the application.

**Format:**

```
Date|Time|Description|Vendor|Amount
```

**Example Transaction Lines:**

```csv
2024-02-10|09:45:33|ergonomic keyboard|Amazon|-89.50
2024-03-01|13:40:12|Invoice 2104 paid|Anna|710.00
```

**Payments are always stored as negative numbers to maintain account accuracy.

-----
