import java.util.*;
import java.io.*;

public class ATM {
    public static void main(String[] args) {

        // set the fileName
        String dataFile = "userdata.txt";
        final int KEY = 10; // EZ ENCRYPTION
        int customerLocation;

        while (true) {
            // login method with interface
            customerLocation = login(dataFile, KEY);
            userInterface(dataFile, customerLocation, KEY);
        }
    }

    public static int login(String dataFile, int key) {

        // create the variables
        Scanner sc = new Scanner(System.in);
        String customerNum, pin;
        int customerLocation;
        boolean loginSuccessful = false;

        do {
            do {
                // ask for the customer number
                System.out.print("Enter customer number: ");
                customerNum = sc.nextLine();

                // wait for a valid input
                if (!isValidNum(6, customerNum)) {
                    System.out.println("Customer number must consist only of 6 digits.");
                }
            } while (!isValidNum(6, customerNum));

            // check if the account is exsist
            if (!customerExists(dataFile, customerNum, key)) {

                // register with the customer number
                customerLocation = register(dataFile, customerNum, key);

                if (customerLocation != -1) {
                    loginSuccessful = true;
                }
            } else {
                // get the line of the information stored
                customerLocation = customerLocate(dataFile, customerNum, key);

                do {
                    // ask for the pin
                    System.out.print("Enter pin, or 'b' to go back: ");
                    pin = sc.nextLine();
                    pin = pin.toLowerCase();

                    // quit if it is b
                    if (pin.charAt(0) == 'b') {
                        break;

                        // check if it is valid and if it is matched with the profile
                    } else if (isValidNum(4, pin)) {
                        if (pinCheck(dataFile, customerLocation, pin, key)) {
                            loginSuccessful = true;
                            System.out.println("Login successful.");
                        } else {
                            System.out.println("Incorrect pin.");
                        }
                    } else {
                        System.out.println("Pin must consist only of 4 digits.");
                    }
                } while (!isValidNum(4, pin) || !loginSuccessful);
            }
        } while (!loginSuccessful);

        return customerLocation;
    }

    public static void userInterface(String dataFile, int customerLocation, int key) {
        Scanner sc = new Scanner(System.in);
        String firstName = "";
        String lastName = "";
        int option = 0;
        boolean isLogOut = false;

        try {
            BufferedReader in = new BufferedReader(new FileReader(dataFile));

            for (int i = 0; i < customerLocation + 1; i++) {
                in.readLine();
            }

            firstName = decrypt(in.readLine(), key);
            lastName = decrypt(in.readLine(), key);

            in.close();
        } catch (IOException e) {
            System.out.println("userInterface: Error accessing data file.");
        }

        System.out.printf("Welcome %s %s.\n", firstName, lastName);
        System.out.println("[1] Check account balance(s).");
        System.out.println("[2] Deposit.");
        System.out.println("[3] Withdraw.");
        System.out.println("[4] Close an account.");
        System.out.println("[5] Open an account.");
        System.out.println("[6] Change pin.");
        System.out.println("[7] Help menu.");
        System.out.println("[8] Log out.");
        do {
            do {
                option = 0;
                
                try {
                    System.out.print("Enter a UI option: ");
                    option = sc.nextInt();

                    if (!validOption(8, option)) {
                        System.out.println("Please input an appropriate option. Enter [7] for help.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please input an appropriate option. Enter [7] for help.");
                    sc.nextLine();
                }
            } while (!validOption(8, option));

            switch (option) {
                case 1:
                    UserInterface.checkBalance(dataFile, customerLocation, key);
                    break;
                case 2:
                    UserInterface.deposit(dataFile, customerLocation, key);
                    break;
                case 3:
                    UserInterface.withdraw(dataFile, customerLocation, key);
                    break;
                case 4:
                    UserInterface.closeAccount(dataFile, customerLocation, key);
                    break;
                case 5:
                    UserInterface.openAccount(dataFile, customerLocation, key);
                    break;
                case 6:
                    UserInterface.changePin(dataFile, customerLocation, key);
                    break;
                case 7:
                    UserInterface.helpMenu();
                    break;
                case 8:
                    isLogOut = true;
            }
        } while (!isLogOut);
    }

    // check if the number is valid depending the length
    public static boolean isValidNum(int length, String input) {
        boolean validLength = false;
        boolean validInt = true;

        // check the length
        if (input.length() == length) {
            validLength = true;
        }
        try {
            // check if it is numbers
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            validInt = false;
        }

        return validLength && validInt;
    }

    // find the location of the customer number
    public static int customerLocate(String dataFile, String customerNum, int key) {
        int location = 0;
        String lineIn;

        try {
            BufferedReader in = new BufferedReader(new FileReader(dataFile));

            lineIn = in.readLine();

            if (lineIn != null) {
                location++;

                do {
                    if (lineIn.equals(encrypt(customerNum, key))) {
                        break;
                    }

                    for (int i = 0; i < 7; i++) {
                        in.readLine();
                        location++;
                    }

                    lineIn = in.readLine();
                    location++;
                } while (lineIn != null);
            }

            in.close();
        } catch (IOException e) {
            System.out.println("customerLocate: Error accessing data file.");
        }

        return location;
    }

    public static boolean customerExists(String dataFile, String customerNum, int key) {
        boolean exists = false;
        String lineIn;

        try {
            File myObj = new File(dataFile);
            myObj.createNewFile();
            BufferedReader in = new BufferedReader(new FileReader(dataFile));

            lineIn = in.readLine();

            if (lineIn != null) {
                while (lineIn != null) {
                    if (lineIn.equals(encrypt(customerNum, key))) {
                        exists = true;
                        break;
                    }

                    for (int i = 0; i < 7; i++) {
                        in.readLine();
                    }

                    lineIn = in.readLine();
                }
            }

            in.close();
        } catch (IOException e) {
            System.out.println("customerExists: Error accessing data file.");
        }

        return exists;
    }

    public static boolean pinCheck(String dataFile, int customerLocation, String pin, int key) {
        return pin.equals(UserInterface.customerInfoGet(dataFile, customerLocation, 0, key));
    }

    public static boolean validOption(int optionAmount, int option) {
        return option >= 1 && option <= optionAmount;
    }

    public static int register(String dataFile, String customerNum, int key) {
        Scanner sc = new Scanner(System.in);
        int option = 0;
        int customerLocation = -1;
        String firstName, lastName, pin;

        System.out.printf("The customer number %s does not exist.\n", customerNum);
        do {
            try {
                System.out.printf("[1] Create account with customer number %s.\n", customerNum);
                System.out.println("[2] Go back.");
                option = sc.nextInt();

                if (!validOption(2, option)) {
                    System.out.println("Please input an appropriate option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please input an appropriate option.");
                sc.nextLine();
            }
        } while (!validOption(2, option));

        if (option == 1) {
            option = 0;

            sc.nextLine();

            while (true) {
                System.out.print("Enter first name: ");
                firstName = sc.nextLine();
                if (firstName.length() < 20) {
                    break;
                }
                System.out.println("Enter a valid name.");
            }
            while (true) {
                System.out.print("Enter last name: ");
                lastName = sc.nextLine();
                if (lastName.length() < 20) {
                    break;
                }
                System.out.println("Enter a valid name.");
            }
            
            do {
                pin = String.format("%04d", (int) (10000 * Math.random()));
                System.out.printf("%s will be your new pin.\n", pin);

                do {
                    try {
                        System.out.println("[1] Confirm pin.");
                        System.out.println("[2] Generate new pin.");
                        option = sc.nextInt();

                        if (!validOption(2, option)) {
                            System.out.println("Please input an appropriate option.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Please input an appropriate option.");
                        sc.nextLine();
                    }
                } while (!validOption(2, option));

                if (option == 1) {
                    break;
                }
            } while (true);

            try {
                BufferedReader in = new BufferedReader(new FileReader(dataFile));
                BufferedWriter out = new BufferedWriter(new FileWriter(dataFile, true));

                if (in.readLine() != null) {
                    out.newLine();
                }

                out.write(encrypt(customerNum, key));
                out.newLine();
                out.write(encrypt(pin, key));
                out.newLine();
                out.write(encrypt(firstName, key));
                out.newLine();
                out.write(encrypt(lastName, key));
                for (int i = 0; i < 2; i++) {
                    out.newLine();
                    out.write(encrypt("0", key));
                    out.newLine();
                    out.write(encrypt("0.00", key));
                }

                in.close();
                out.close();
            } catch (IOException e) {
                System.out.println("Error accessing data file.");
            }

            customerLocation = customerLocate(dataFile, customerNum, key);
        }

        return customerLocation;
    }
    
    public static String encrypt(String str, int key)
    {
        String[] encryptSpace = new String[key + 1];
        encryptSpace[0] = str;
        
        for (int i = 1; i < key + 1; i++)
        {
            encryptSpace[i] = Base64.getEncoder().encodeToString(encryptSpace[i - 1].getBytes());
        }
        
        return encryptSpace[key];
    }
    
    public static String decrypt(String str, int key)
    {
        String[] decryptSpace = new String[key + 1];
        decryptSpace[0] = str;
        
        for (int i = 1; i < key + 1; i++)
        {
            decryptSpace[i] = new String(Base64.getDecoder().decode(decryptSpace[i - 1]));
        }
        
        return decryptSpace[key];
    }
}