package logic;

import java.util.Date;

/**
 * Метод интерфейса вызывается каждый раз при добавлении денег на текущий счет (полиморфизм)
 */
public interface iMakeDeposite {
    void deposit(int amount, Date date);
}
