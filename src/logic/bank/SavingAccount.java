package logic.bank;

import java.util.Date;

/**
 * Метод интерфейса вызывается каждый раз при добавлении денег на текущий счет (полиморфизм)
 */
public interface SavingAccount {
    void deposit(int amount, Date date);
}
