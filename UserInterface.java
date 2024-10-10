import java.io.*;
import java.util.*;

public class UserInterface {
    public static void checkBalance(String dataFile, int customerLocation, int key) {
        Scanner sc = new Scanner(System.in);
        int option;
            switch (accountStates(dataFile, customerLocation, key)) {
                case "00":
                    System.out.println("You have no accounts. Try opening a new account.");
                    break;
                case "10":
                    System.out.println("You only have a 'Checking' account\n");
                    System.out.println("Balance for Checking Account\n---------------------");
                    System.out.println("Amount: $" + customerInfoGet(dataFile, customerLocation, 4, key));
                    break;
                case "01":
                    System.out.println("You only have a 'Saving' account\n");
                    System.out.println("Balance for Saving Account\n---------------------");
                    System.out.println("Amount: $" + customerInfoGet(dataFile, customerLocation, 6, key));
                    break;
                case "11":
                    System.out.print("[1] Checking Account\n[2] Saving Account\nEnter an option: ");
                    while (true)
                    {
                        try
                        {
                            System.out.print("Enter option: ");
                            option = sc.nextInt();
                            
                            if (!ATM.validOption(2, option))
                            {
                                System.out.println("Enter a valid option.");
                                continue;
                            }
                            
                            break;
                        }
                        catch (InputMismatchException e)
                        {
                            System.out.println("Enter a valid option.");
                            sc.nextLine();
                        }
                    }
                    
                    switch (option)
                    {
                        case 1:
                            System.out.println("You only have a 'Checking' account\n");
                            System.out.println("Balance for Checking Account\n---------------------");
                            System.out.println("Amount: $" + customerInfoGet(dataFile, customerLocation, 4, key));
                            break;
                        case 2:
                            System.out.println("You only have a 'Checking' account\n");
                            System.out.println("Balance for Checking Account\n---------------------");
                            System.out.println("Amount: $" + customerInfoGet(dataFile, customerLocation, 6, key));
                    }
            }
    }

    public static void deposit(String dataFile, int customerLocation, int key) {
        Scanner sc = new Scanner(System.in);
        double depositAmount;
        int option;
        
        switch (accountStates(dataFile, customerLocation, key)) {
            case "00":
                System.out.println("You have no accounts. Open an account in order to deposit money into that account.");
                break;
            case "10":
                System.out.println("You only have a 'Checking' account\n");
                while (true)
                {
                    try
                    {
                        System.out.print("Enter the amount to deposit: ");
                        depositAmount = sc.nextDouble();
                        break;
                    }
                    catch (InputMismatchException e)
                    {
                        System.out.println("Enter a numerical value.");
                        sc.nextLine();
                    }
                }
                addBalance(dataFile, customerLocation, 0, depositAmount, key);
                break;
            case "01":
                System.out.println("You only have a 'Saving' account\n");
                while (true)
                {
                    try
                    {
                        System.out.print("Enter the amount to deposit: ");
                        depositAmount = sc.nextDouble();
                        break;
                    }
                    catch (InputMismatchException e)
                    {
                        System.out.println("Enter a numerical value.");
                        sc.nextLine();
                    }
                }
                addBalance(dataFile, customerLocation, 1, depositAmount, key);
                break;
            case "11":
                System.out.println("[1] Checking Account\n[2] Saving Account: ");
                while (true)
                {
                    try
                    {
                        System.out.print("Enter option: ");
                        option = sc.nextInt();
                        
                        if (!ATM.validOption(2, option))
                        {
                            System.out.println("Enter a valid option.");
                            continue;
                        }
                        
                        break;
                    }
                    catch (InputMismatchException e)
                    {
                        System.out.println("Enter a valid option.");
                        sc.nextLine();
                    }
                }
                
                switch (option)
                {
                    case 1:
                        while (true)
                        {
                            try
                            {
                                System.out.print("Enter the amount to deposit: ");
                                depositAmount = sc.nextDouble();
                                break;
                            }
                            catch (InputMismatchException e)
                            {
                                System.out.println("Enter a numerical value.");
                                sc.nextLine();
                            }
                        }
                        addBalance(dataFile, customerLocation, 0, depositAmount, key);
                        break;
                    case 2:
                        while (true)
                        {
                            try
                            {
                                System.out.print("Enter the amount to deposit: ");
                                depositAmount = sc.nextDouble();
                                break;
                            }
                            catch (InputMismatchException e)
                            {
                                System.out.println("Enter a numerical value.");
                                sc.nextLine();
                            }
                        }
                        addBalance(dataFile, customerLocation, 1, depositAmount, key);
                }
        }
    }

    public static void withdraw(String dataFile, int customerLocation, int key) {
        Scanner sc = new Scanner(System.in);
        double withdrawAmount;
        int option;
        
        switch (accountStates(dataFile, customerLocation, key)) {
            case "00":
                System.out.println("You have no accounts. Open an account in order to withdraw money into that account.");
                break;
            case "10":
                System.out.println("You only have a 'Checking' account\n");
                while (true)
                {
                    try
                    {
                        System.out.print("Enter the amount to withdraw: ");
                        withdrawAmount = sc.nextDouble();
                        break;
                    }
                    catch (InputMismatchException e)
                    {
                        System.out.println("Enter a numerical value.");
                        sc.nextLine();
                    }
                }
                withdrawBalance(dataFile, customerLocation, 0, withdrawAmount, key);
                break;
            case "01":
                System.out.println("You only have a 'Saving' account\n");
                while (true)
                {
                    try
                    {
                        System.out.print("Enter the amount to withdraw: ");
                        withdrawAmount = sc.nextDouble();
                        break;
                    }
                    catch (InputMismatchException e)
                    {
                        System.out.println("Enter a numerical value.");
                        sc.nextLine();
                    }
                }
                withdrawBalance(dataFile, customerLocation, 1, withdrawAmount, key);
                break;
            case "11":
                System.out.println("[1] Checking Account\n[2] Saving Account: ");
                while (true)
                {
                    try
                    {
                        System.out.print("Enter option: ");
                        option = sc.nextInt();
                        
                        if (!ATM.validOption(2, option))
                        {
                            System.out.println("Enter a valid option.");
                            continue;
                        }
                        
                        break;
                    }
                    catch (InputMismatchException e)
                    {
                        System.out.println("Enter a valid option.");
                        sc.nextLine();
                    }
                }
                
                switch (option)
                {
                    case 1:
                        while (true)
                        {
                            try
                            {
                                System.out.print("Enter the amount to withdraw: ");
                                withdrawAmount = sc.nextDouble();
                                break;
                            }
                            catch (InputMismatchException e)
                            {
                                System.out.println("Enter a numerical value.");
                                sc.nextLine();
                            }
                        }
                        withdrawBalance(dataFile, customerLocation, 0, withdrawAmount, key);
                        break;
                    case 2:
                        while (true)
                        {
                            try
                            {
                                System.out.print("Enter the amount to withdraw: ");
                                withdrawAmount = sc.nextDouble();
                                break;
                            }
                            catch (InputMismatchException e)
                            {
                                System.out.println("Enter a numerical value.");
                                sc.nextLine();
                            }
                        }
                        withdrawBalance(dataFile, customerLocation, 1, withdrawAmount, key);
                }
        }
    }

    public static void closeAccount(String dataFile, int customerLocation, int key) {
        Scanner sc = new Scanner(System.in);
        int option = 0;
        
        switch (accountStates(dataFile, customerLocation, key)) {
            case "00":
                System.out.println("You do not have any accounts to close.");
                break;
            case "10":
                System.out.println("You are about to close your checking account.\n[1] Confirm.\n[2] Go back.");
                while (true)
                {
                    try
                    {
                        System.out.print("Enter option: ");
                        option = sc.nextInt();
                        
                        if (!ATM.validOption(2, option))
                        {
                            System.out.println("Enter a valid option.");
                            continue;
                        }
                        
                        break;
                    }
                    catch (InputMismatchException e)
                    {
                        System.out.println("Enter a valid option.");
                        sc.nextLine();
                    }
                }
                
                if (option == 1)
                {
                    System.out.println("Closed checking account. There was $" + customerInfoGet(dataFile, customerLocation, 4, key) + " left.");
                    writeLine(dataFile, customerLocation + 4, "0", key);
                    writeLine(dataFile, customerLocation + 5, "0.00", key);
                }
                break;
            case "01":
                System.out.println("You are about to close your saving account.\n[1] Confirm.\n[2] Go back.");
                while (true)
                {
                    try
                    {
                        System.out.print("Enter option: ");
                        option = sc.nextInt();
                        
                        if (!ATM.validOption(2, option))
                        {
                            System.out.println("Enter a valid option.");
                            continue;
                        }
                        
                        break;
                    }
                    catch (InputMismatchException e)
                    {
                        System.out.println("Enter a valid option.");
                        sc.nextLine();
                    }
                }
                
                if (option == 1)
                {
                    System.out.println("Closed saving account. There was $" + customerInfoGet(dataFile, customerLocation, 6, key) + " left.");
                    writeLine(dataFile, customerLocation + 6, "0", key);
                    writeLine(dataFile, customerLocation + 7, "0.00", key);
                }
                break;
            case "11":
                System.out.println("[1] Checking Account\n[2] Saving Account: ");
                while (true)
                {
                    try
                    {
                        System.out.print("Enter option: ");
                        option = sc.nextInt();
                        
                        if (!ATM.validOption(2, option))
                        {
                            System.out.println("Enter a valid option.");
                            continue;
                        }
                        
                        break;
                    }
                    catch (InputMismatchException e)
                    {
                        System.out.println("Enter a valid option.");
                        sc.nextLine();
                    }
                }
                
                switch (option)
                {
                    case 1:
                        System.out.println("Closed checking account. There was $" + customerInfoGet(dataFile, customerLocation, 4, key) + " left.");
                        writeLine(dataFile, customerLocation + 4, "0", key);
                        writeLine(dataFile, customerLocation + 5, "0.00", key);
                        break;
                    case 2:
                        System.out.println("Closed saving account. There was $" + customerInfoGet(dataFile, customerLocation, 6, key) + " left.");
                        writeLine(dataFile, customerLocation + 6, "0", key);
                        writeLine(dataFile, customerLocation + 7, "0.00", key);
                }
        }
    }

    public static void openAccount(String dataFile, int customerLocation, int key) {
        Scanner sc = new Scanner(System.in);
        int option = 0;
        
        switch (accountStates(dataFile, customerLocation, key)) {
            case "11":
                System.out.println("You do not have any accounts to open.");
                break;
            case "01":
                System.out.println("You are about to open your checking account.\n[1] Confirm.\n[2] Go back.");
                while (true)
                {
                    try
                    {
                        System.out.print("Enter option: ");
                        option = sc.nextInt();
                        
                        if (!ATM.validOption(2, option))
                        {
                            System.out.println("Enter a valid option.");
                            continue;
                        }
                        
                        break;
                    }
                    catch (InputMismatchException e)
                    {
                        System.out.println("Enter a valid option.");
                        sc.nextLine();
                    }
                }
                
                if (option == 1)
                {
                    System.out.println("Opened checking account.");
                    writeLine(dataFile, customerLocation + 4, "1", key);
                }
                break;
            case "10":
                System.out.println("You are about to open your saving account.\n[1] Confirm.\n[2] Go back.");
                while (true)
                {
                    try
                    {
                        System.out.print("Enter option: ");
                        option = sc.nextInt();
                        
                        if (!ATM.validOption(2, option))
                        {
                            System.out.println("Enter a valid option.");
                            continue;
                        }
                        
                        break;
                    }
                    catch (InputMismatchException e)
                    {
                        System.out.println("Enter a valid option.");
                        sc.nextLine();
                    }
                }
                
                if (option == 1)
                {
                    System.out.println("Opened saving account.");
                    writeLine(dataFile, customerLocation + 6, "1", key);
                }
                break;
            case "00":
                System.out.println("[1] Checking Account\n[2] Saving Account: ");
                while (true)
                {
                    try
                    {
                        System.out.print("Enter option: ");
                        option = sc.nextInt();
                        
                        if (!ATM.validOption(2, option))
                        {
                            System.out.println("Enter a valid option.");
                            continue;
                        }
                        
                        break;
                    }
                    catch (InputMismatchException e)
                    {
                        System.out.println("Enter a valid option.");
                        sc.nextLine();
                    }
                }
                
                switch (option)
                {
                    case 1:
                        System.out.println("Opened checking account.");
                        writeLine(dataFile, customerLocation + 4, "1", key);
                        break;
                    case 2:
                        System.out.println("Opened saving account.");
                        writeLine(dataFile, customerLocation + 6, "1", key);
                }
        }
    }

    public static void changePin(String dataFile, int customerLocation, int key) {
        Scanner sc = new Scanner(System.in);
        String pin, newPin = "";

        do {
            // ask for the pin
            System.out.print("Enter current pin to confirm, or 'b' to go back: ");
            pin = sc.nextLine();
            pin = pin.toLowerCase();

            // quit if it is b
            if (pin.charAt(0) == 'b') {
                break;
                // check if it is valid and if it is matched with the profile
            } else if (ATM.isValidNum(4, pin)) {
                if (ATM.pinCheck(dataFile, customerLocation, pin, key)) {
                    do {
                        // ask for the pin
                        System.out.print("Enter new pin, or 'b' to go back: ");
                        newPin = sc.nextLine();
                        newPin = newPin.toLowerCase();

                        // quit if it is b
                        if (newPin.charAt(0) == 'b') {
                            break;
                            // check if it is valid and if it is matched with the profile
                        } else if (ATM.isValidNum(4, newPin)) {
                            do {
                                // ask for the pin
                                System.out.print("Enter new pin to confirm, or 'b' to go back: ");
                                pin = sc.nextLine();
                                pin = pin.toLowerCase();

                                // quit if it is b
                                if (pin.charAt(0) == 'b') {
                                    break;
                                    // check if it is valid and if it is matched with the profile
                                } else if (pin.equals(newPin)) {
                                    System.out.printf("Pin changed, your new pin is %s.\n", newPin);
                                    writeLine(dataFile, customerLocation + 1, newPin, key);
                                    break;
                                } else {
                                    System.out.println("Incorrect pin.");
                                }
                            } while (!pin.equals(newPin));
                        } else {
                            System.out.println("Pin must consist only of 4 digits.");
                        }
                    } while (!ATM.isValidNum(4, newPin));
                } else {
                    System.out.println("Incorrect pin.");
                }
            } else {
                System.out.println("Pin must consist only of 4 digits.");
            }
        } while (!ATM.isValidNum(4, pin) || !ATM.pinCheck(dataFile, customerLocation, newPin, key));
    }

    public static String accountStates(String dataFile, int customerLocation, int key) {
        String states = "";

        if (customerInfoGet(dataFile, customerLocation, 3, key).equals("0")) {
            states += "0";
        } else {
            states += "1";
        }
        if (customerInfoGet(dataFile, customerLocation, 5, key).equals("0")) {
            states += "0";
        } else {
            states += "1";
        }

        return states;
    }

    public static String customerInfoGet(String dataFile, int customerLocation, int infoType, int key) {
        String info = "";

        try {
            BufferedReader in = new BufferedReader(new FileReader(dataFile));

            for (int i = 0; i < customerLocation + infoType; i++) {
                in.readLine();
            }

            info += in.readLine();

            in.close();
        } catch (IOException e) {
            System.out.println("customerInfoGet: Error accessing data file.");
        }

        return ATM.decrypt(info, key);
    }

    public static void addBalance(String dataFile, int customerLocation, int account, double amount, int key) {
        double oldBalance;
        double newAmount;
        
        if (amount <= 0)
        {
            System.out.println("Deposit amount must be positive.");
        }
        else
        {
            // if it is a checking account
            if (account == 0) {
                oldBalance = Double.parseDouble(customerInfoGet(dataFile, customerLocation, 4, key));
                // add the new amount to the array
                newAmount = (double) Math.round((oldBalance + amount) * 100) / 100;
                writeLine(dataFile, customerLocation + 5, Double.toString(newAmount), key);
                System.out.println("Your new balance is $" + newAmount);
            } else if (account == 1) { // if it is a saving account
                oldBalance = Double.parseDouble(customerInfoGet(dataFile, customerLocation, 6, key));
                newAmount = (double) Math.round((oldBalance + amount) * 100) / 100;
                writeLine(dataFile, customerLocation + 7, Double.toString(newAmount), key);
                System.out.println("Your new balance is $" + newAmount);
            }
        }
    }

    public static void withdrawBalance(String dataFile, int customerLocation, int account, double amount, int key) {
        double oldBalance;
        double newAmount;
        
        if (amount <= 0)
        {
            System.out.println("Withdrawal amount must be positive.");
        }
        else
        {
            // if it is a checking account
            if (account == 0) {
                oldBalance = Double.parseDouble(customerInfoGet(dataFile, customerLocation, 4, key));
                // add the new amount to the array
                newAmount = (double) Math.round((oldBalance - amount) * 100) / 100;
                if (newAmount < 0) {
                    System.out.println("You don't have enough amount in the bank to perform this operation");
                    System.out.printf("Your balance is $%.2f\n", oldBalance);
                } else {
                    System.out.println("Your new balance is $" + newAmount);
                    writeLine(dataFile, customerLocation + 5, Double.toString(newAmount), key);
                }
            } else if (account == 1) { // if it is a saving account
                oldBalance = Double.parseDouble(customerInfoGet(dataFile, customerLocation, 6, key));
                newAmount = (double) Math.round((oldBalance - amount) * 100) / 100;
                if (newAmount < 0) {
                    System.out.println("You don't have enough amount in the bank to perform this operation");
                    System.out.printf("Your balance is $%.2f\n", oldBalance);
                } else {
                    System.out.println("Your new balance is $" + newAmount);
                    writeLine(dataFile, customerLocation + 7, Double.toString(newAmount), key);
                }
            }
        }
    }

    public static void helpMenu() {
        System.out.println("[1] Check account balance(s).");
        System.out.println("[2] Deposit.");
        System.out.println("[3] Withdraw.");
        System.out.println("[4] Close an account.");
        System.out.println("[5] Open an account.");
        System.out.println("[6] Change pin.");
        System.out.println("[7] Help menu.");
        System.out.println("[8] Log out.");
    }

    public static void writeLine(String dataFile, int lineNum, String line, int key) {
        String lineIn;
        int lineAmount = 0;
        String[] lines;

        try {
            BufferedReader in = new BufferedReader(new FileReader(dataFile));

            do {
                lineIn = in.readLine();

                if (lineIn != null) {
                    lineAmount++;
                }
            } while (lineIn != null);

            in.close();
        } catch (IOException e) {
            System.out.println("writeLine: Error accessing data file.");
        }

        lines = new String[lineAmount];

        try {
            BufferedReader in = new BufferedReader(new FileReader(dataFile));

            for (int i = 0; i < lineAmount; i++) {
                lines[i] = in.readLine();
            }

            in.close();
        } catch (IOException e) {
            System.out.println("writeLine: Error accessing data file.");
        }

        lines[lineNum - 1] = ATM.encrypt(line, key);

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(dataFile, false));

            for (int i = 0; i < lineAmount; i++) {
                out.write(lines[i]);

                if (i != lineAmount - 1) {
                    out.newLine();
                }
            }

            out.close();
        } catch (IOException e) {
            System.out.println("writeLine: Error accessing data file.");
        }
    }
}