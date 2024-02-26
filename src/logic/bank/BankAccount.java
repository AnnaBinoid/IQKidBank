package logic.bank;

import data.MarkStatus;

import java.awt.*;
import java.util.Scanner;

public class BankAccount {

    private int balance;
    private int previousTransaction;
    private String customerName;
    private String customerID;
    Scanner scan = new Scanner(System.in);
    // создаем конструктор
    public BankAccount(String cName, String cID) {
        this.customerName = cName;
        this.customerID = cID;
        this.balance = 0;
    }

    /**
     * Метод выводит актуальный баланс.
     */
    public void checkBalance (){
        System.out.printf("Balance is: %d.", balance);
    }

    /**
     * Метод deposit пополняет счет с проверкой, что
     * сумма депозита больше 0.
     * @param depositAmount - сумма, на которую нужно пополнить счет.
     */
    public void deposit (int depositAmount){
        if(depositAmount > 0) {
            balance += depositAmount;
            previousTransaction = depositAmount;
            System.out.printf("Success!You have topped up " +
                    "your account on %d.\n", depositAmount);
        }
        else {
            System.out.println("Deposit value <= 0. Please, enter " +
                    "another value.");
        }
    }

    /**
     * Метод withdraw снимает указанную сумму со счета с провреками:
     * снимаемая сумма больше нуля и меньше баланса.
     * @param withdrawAmount - сумма для снятия.
     */

    public void withdraw(int withdrawAmount){
        if (withdrawAmount > 0 && withdrawAmount <= balance){
            balance = balance - withdrawAmount;
            previousTransaction = -withdrawAmount;
            System.out.printf("Success!Your account has been " +
                    "withdrawn %d!\n", withdrawAmount);
        }
        else {
            System.out.println("You have no enough money." +
                    "Please, check balance and try again " +
                    "with another value.");
        }
    }

    /**
     * Метод getPreviousTransaction возвращает сообщение о том, что
     * мы либо внесли сумму на счет, либо успешно сняли её.
     */
    public void getPreviousTransaction(){
        if(previousTransaction > 0){
            System.out.println("Deposited: " + previousTransaction);
        }
        else if(previousTransaction < 0) {
            System.out.println("Withdraw: " + Math.abs(previousTransaction)); //используем асболют
            // для корректного отображения, т.к. был использован оператор отрицательного вывода
        }
        else {
            System.out.println("The transaction was not found.");
        }
    }


    /**
     * Метод getMarkCoefficien создает экземпляр класса Markstatus() для
     * ввода оценок, коэффициентов и расчета финальной суммы выплаты.
     */
    public void getMarkСoefficient () {
        MarkStatus test = new MarkStatus();
        System.out.println(" ");
        test.getCurrentGradesCoefficient();
    }


    /**
     * Метод showMenu показывает меню доступных операций
     */
    public void showMenu() {

        int option = 0;

        System.out.printf("Welcome, %s!\nYour id is : %s. \nPlease, " +
                "chose the operation:\n" +
                "0. Show menu.\n" +
                "1. Check your balance.\n" +
                "2. Deposit.\n" +
                "3. Withdrew.\n" +
                "4. Check previous transaction.\n" +
                "5. Make mark calculation.\n" +
                "6. Exit.\n", customerName, customerID);

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
                        System.out.println("\n");
                        break;
                    case 1:
                        checkBalance();
                        System.out.println("\n");
                        break;

                    case 2:
                        System.out.print("Enter how much money do you want to deposit: ");
                        int depAmount = scan.nextInt();
                        deposit(depAmount);
                        break;

                    case 3:
                        System.out.print("Enter how much money do you want to withdraw: ");
                        int withAmount = scan.nextInt();
                        withdraw(withAmount);
                        break;

                    case 4:
                        getPreviousTransaction();
                        System.out.println("\n");
                        break;

                    case 5:
                        getMarkСoefficient();

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
    }



}
