package data;

import logic.bank.SavingAccount;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

public class Customer extends Person implements SavingAccount {
    private String username;

    private String password;

    private int balance;

    private int id;


    private ArrayList<String> transactions = new ArrayList<>(10);

    public Customer(String firstName, String lastName, String address, String phone, String username
            , String password, int id, Date date) {
        super(firstName, lastName, address, phone);
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.id = id;
        addTransaction(String.format("Initial deposit - "
                + balance
                + " at %1$td.%1$tm.%1$ty %tT", date, date));
    }

    private void addTransaction(String message) {

        transactions.add(0, message);
        if (transactions.size() > 10) {
            transactions.remove(10);
            transactions.trimToSize();
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }

    @Override
    public void deposit(int amount, Date date) {

        balance += amount;
        addTransaction(String.format(amount + " credited to your account. Balance - "
                + balance +
                " at %1$td.%1$tm.%1$ty %tT", date, date));
    }

    public void withdraw(int amount, Date date) {

        if (balance >= amount) {
            balance -= amount;
            addTransaction(String.format(amount
                    + " withdrawed from your account. Balance - "
                    + balance
                    + " at %1$td.%1$tm.%1$ty %tT", date, date));
        } else {
            System.out.println("You have not enough money.\nPlease top up your account and try again.");
        }
    }


    @Override
    public String toString() {
        return "Customer{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", transactions=" + transactions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (Double.compare(customer.getBalance(), getBalance()) != 0) return false;
        if (getUsername() != null ? !getUsername().equals(customer.getUsername()) : customer.getUsername() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(customer.getPassword()) : customer.getPassword() != null)
            return false;
        return getTransactions() != null ? getTransactions().equals(customer.getTransactions()) : customer.getTransactions() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getUsername() != null ? getUsername().hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        temp = Double.doubleToLongBits(getBalance());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getTransactions() != null ? getTransactions().hashCode() : 0);
        return result;
    }
}
