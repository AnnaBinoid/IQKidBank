package view;

import data.Customer;
import data.MarkStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankAccount {


    private String customerName;
    private int customerID;
    Scanner scan = new Scanner(System.in);
    private static int amount = 0;
    Map<String, Customer> customerMap = new HashMap<String, Customer>();


    public BankAccount() {
    }


    public void enterByRegistration() {
        Customer customer;
        BankAccount bankAccount = new BankAccount();
        int choice;

        while (true) {
            System.out.println("IQKidBank welcomes you!");
            System.out.println("1. Registration. ");
            System.out.println("2. Enter with login. ");
            System.out.println("3. Exit.");
            choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter your firstname, please: ");
                    String firstName = scan.nextLine();
                    System.out.print("Enter your lastname, please : ");
                    String lastName = scan.nextLine();
                    System.out.print("Enter your address, please : ");
                    String address = scan.nextLine();
                    System.out.print("Enter your contact number, please : ");
                    String phone = scan.nextLine();
                    System.out.println("Set login, please : ");
                    String login = scan.next();
                    customerName = login;


                    while (bankAccount.customerMap.containsKey(login)) {
                        System.out.println("This login is already exists. Set again : ");
                        login = scan.next();
                        scan.nextLine();
                    }
                    System.out.println("Set a password:");
                    String password = scan.next();
                    scan.nextLine();

                    customer = new Customer(firstName, lastName, address, phone, login, password, customerID, new Date());
                    customerID = customerID + 1;
                    bankAccount.customerMap.put(login, customer);
                    System.out.println("Registration success! Please, choose the 2nd point and log in.");
                    break;

                case 2:
                    System.out.println("Enter login : ");
                    login = scan.next();
                    scan.nextLine();
                    System.out.println("Enter password : ");
                    password = scan.next();
                    scan.nextLine();
                    if (bankAccount.customerMap.containsKey(login)) {
                        customer = bankAccount.customerMap.get(login);
                        if (customer.getPassword().equals(password)) {
                            int option = 0;
                            System.out.printf("Welcome, %s!\nYour id is : %s. \n", customer.getUsername(), customerID);
                            showMenu();

                            while (option != 6) {
                                System.out.println("+++++++++++++++++++++++++++++++++");
                                System.out.print("Enter an option: ");
                                option = scan.nextInt();
                                if (option > 6) {
                                    System.out.println("Invalid operation! Please, try again! \nTo repeat the menu, enter 0.");
                                } else {
                                    System.out.println("+++++++++++++++++++++++++++++++++");

                                    switch (option) {
                                        case 0:
                                            showMenu();
                                            break;
                                        case 1:
                                            System.out.printf("Your balance is: %d.\n", customer.getBalance());
                                            break;

                                        case 2:
                                            System.out.print("Enter how much money do you want to deposit: ");
                                            while (!scan.hasNextInt()) {
                                                System.out.println("Invalid value. Enter again!");
                                                scan.nextLine();
                                            }
                                            amount = scan.nextInt();
                                            scan.nextLine();
                                            customer.deposit(amount, new Date());
                                            break;

                                        case 3:
                                            System.out.print("Enter how much money do you want to withdraw: ");
                                            while (!scan.hasNextInt()) {
                                                System.out.println("Invalid value. Enter again!");
                                                scan.nextLine();
                                            }
                                            amount = scan.nextInt();
                                            scan.nextLine();
                                            customer.withdraw(amount, new Date());
                                            break;

                                        case 4:
                                            for (String transactions : customer.getTransactions()) {
                                                System.out.println(transactions);
                                            }
                                            break;

                                        case 5:
                                            MarkStatus test = new MarkStatus();
                                            System.out.println(" ");
                                            int sumForGrades = test.getCurrentGradesCoefficient();
                                            ;
                                            System.out.printf("It was great!" +
                                                    "\nDo you want to transfer %d " +
                                                    "for another client of IQKidBank?\n" +
                                                    "Press 1 for yes, press 2 for no: ", sumForGrades);
                                            int thisChois = scan.nextInt();
                                            if (thisChois == 1) {
                                                System.out.println("Enter beneficiary login: ");
                                                login = scan.next();
                                                if (bankAccount.customerMap.containsKey(login)) {
                                                    Customer payee = bankAccount.customerMap.get(login);
                                                    if (customer.getBalance() >= sumForGrades) {
                                                        payee.deposit(sumForGrades, new Date());
                                                        customer.withdraw(sumForGrades, new Date());
                                                        System.out.printf("You have successfully transferred %d" +
                                                                " to the user %s!\n", sumForGrades, payee.getUsername());
                                                    } else {
                                                        System.out.println("You have not enough money.\n" +
                                                                "Please top up your account and try again");
                                                    }
                                                } else {
                                                    System.out.println("User with such login doesn't exist.");
                                                }
                                                break;
                                            }

                                            break;

                                        case 6:
                                            System.out.println("Thank you for using our service!");
                                            break;

                                        default:
                                            System.out.println("Invalid operation! Please, try again!\n");
                                            break;
                                    }
                                }
                            }
                        } else {
                            System.out.println("Wrong login/password.");
                        }
                    } else {
                        System.out.println("Wrong login/password.");
                    }
                    break;


                case 3:
                    System.out.println("Thank you for choosing IQKidBank!");
                    System.exit(1);
                    break;
                default:
                    System.out.println("Wrong choice! Try again!");
            }

        }
    }


//    /**
//     * Метод withdraw снимает указанную сумму со счета с провреками:
//     * снимаемая сумма больше нуля и меньше баланса.
//     *
//     * @param withdrawAmount - сумма для снятия.
//     */
//
//    public void withdraw(int withdrawAmount) {
//        if (withdrawAmount > 0 && withdrawAmount <= balance) {
//            balance = balance - withdrawAmount;
//            previousTransaction = -withdrawAmount;
//            System.out.printf("Success!Your account has been " +
//                    "withdrawn %d!\n", withdrawAmount);
//        } else {
//            System.out.println("You have no enough money." +
//                    "Please, check balance and try again " +
//                    "with another value.");
//        }
//    }
//
//    /**
//     * Метод getPreviousTransaction возвращает сообщение о том, что
//     * мы либо внесли сумму на счет, либо успешно сняли её.
//     */
//    public void getPreviousTransaction() {
//        if (previousTransaction > 0) {
//            System.out.println("Deposited: " + previousTransaction);
//        } else if (previousTransaction < 0) {
//            System.out.println("Withdraw: " + Math.abs(previousTransaction)); //используем асболют
//            // для корректного отображения, т.к. был использован оператор отрицательного вывода
//        } else {
//            System.out.println("The transaction was not found.");
//        }
//    }




    /**
     * Метод showMenu показывает меню доступных операций
     */
    public void showMenu() {
        System.out.printf("Please chose the operation:\n" +
                "0. Show menu.\n" +
                "1. Check your balance.\n" +
                "2. Deposit.\n" +
                "3. Withdrew.\n" +
                "4. Check 10 previous transactions.\n" +
                "5. Make mark calculation.\n" +
                "6. Exit.\n");
    }
}
