package logic;

import java.util.ArrayList;
import java.util.Scanner;

public class MarkStatus {
    //Принимает на вход текущие оценки
    //Принимает на вход четвертные оценки
    //Принимает на вход годовые оценки
    //Считает сумму к переводу по алгоритму

    ArrayList<Integer> currentGrades = new ArrayList<>(100);
    ArrayList<Integer> quarterGrades = new ArrayList<>();
    ArrayList<Integer> yearGrades = new ArrayList<>();
    ArrayList<Integer> test = new ArrayList<>();

    public int allSumForGrades;

    private final int EXCELLENT = 5;
    private final int GOOD = 4;
    private final int SATISFACTORY = 3;
    private final int BAD = 2;
    private final int VERY_BAD = 1;
    private final int ROWS = 5;
    private final int COLUMNS = 2;

    Scanner scan = new Scanner(System.in);

    public void setAllSumForGrades(int allSumForGrades) {
        this.allSumForGrades = allSumForGrades;
    }

    /**
     * Метод createMarkArray создает двумерную матрицу для массива оценок
     * , первая строка которой - школьные оценки от 1 до 5, вторая - денежные коэффициенты за каждую из них
     *
     * @return двумерную матрицу для массива оценок, первая строка которой - школьные оценки от 1 до 5
     */
    public int[][] createMarkArray() {

        int[][] manipulationsWithMarks = {{EXCELLENT, GOOD, SATISFACTORY, BAD, VERY_BAD}
                , {0, 0, 0, 0, 0}};
        return manipulationsWithMarks;
    }

    /**
     * метод setGradesByOne позволяет задавать оценки вручную по одной
     *
     * @return список оценок
     */

    /**
     * Есть проверка на то, что введенное значение со сканера - инт, иначе приложение будет крашиться
     * из-за неправильного ввода.
     *
     * @return
     */
    public ArrayList<Integer> setGradesByOne() {
        ArrayList<Integer> curGrades = this.currentGrades;
        System.out.println("Enter the school grades via enter!");
        int currentGrade;
        do {
            if (scan.hasNextInt()) {
                currentGrade = scan.nextInt();
                if (currentGrade != -1) {
                    if (1 <= currentGrade && currentGrade <= 5) {
                        curGrades.add(currentGrade);
                        System.out.println("Enter the next mark or -1 for exit from setting " +
                                "grades.");
                    } else {
                        System.out.println("Invalid value. Please enter the next mark from 1 to 5!");
                    }
                } else {
                    printList(curGrades);
                    break;
                }
            } else {
                // Can't read the input as int.
                // Read it rather as String, and display the error message
                String str = scan.nextLine();
                System.out.println(String.format("Invalid input: %s cannot be converted to an int.", str));
            }
        }
        while (scan.hasNextLine());

        return curGrades;
    }

    /**
     * Метод printList позволяет распечатать Arraylist
     *
     * @param arrForPrint
     */
    public void printList(ArrayList arrForPrint) {
        System.out.print("You have entered the following estimates: ");
        System.out.println(arrForPrint.toString());
    }

    /**
     * Метод printDoubleArray распечатывает матрицу
     *
     * @param arr матрица для распечатки
     */
    public void printMatrix(int[][] arr) {
        // распечатываем массив
        for (int i = 0; i < ROWS; i++) {  //идём по строкам
            for (int j = 0; j < COLUMNS; j++) {//идём по столбцам
                System.out.print(" " + arr[j][i] + " "); //вывод элемента
            }
            System.out.println();//перенос строки ради визуального сохранения табличной формы
        }
    }

    /**
     * Метод numberOfMarks позволяет увидеть кол-во одинаковых оценок
     *
     * @return двумерную матрицу для массива оценок, первая строка которой - школьные оценки от 1 до 5
     * , вторая - кол-во соответствующих оценок
     */
    public int[][] numberOfMarks(ArrayList<Integer> arrForCount) {

        int[][] countOfMarks = createMarkArray();

        for (int a : arrForCount) {
            if (a == EXCELLENT) {
                countOfMarks[1][0] += 1;
            } else if (a == GOOD) {
                countOfMarks[1][1] += 1;
            } else if (a == SATISFACTORY) {
                countOfMarks[1][2] += 1;
            } else if (a == BAD) {
                countOfMarks[1][3] += 1;
            } else {
                countOfMarks[1][4] += 1;
            }
        }
        printMatrix(countOfMarks);

        return countOfMarks;
    }

    /**
     * Метод setGradesCoefficient позволяет задать с консоли денежный коэффициент за каждую оценку.
     *
     * @return двумерную матрицу, первая строка которой - школьные оценки от 1 до 5
     * , вторая - денежный коэффициент за каждую из них
     */
    public int[][] setGradesCoefficient() {
        int[][] marksMoneyCoeff = createMarkArray();
        System.out.println("Enter the money coefficient for excellent mark (5): ");
        marksMoneyCoeff[1][0] = scan.nextInt();
        System.out.println("Enter the money coefficient for good mark (4): ");
        marksMoneyCoeff[1][1] = scan.nextInt();
        System.out.println("Enter the money coefficient for satisfactory mark (3): ");
        marksMoneyCoeff[1][2] = scan.nextInt();
        System.out.println("Enter the money coefficient for bad mark (2): ");
        marksMoneyCoeff[1][3] = scan.nextInt();
        System.out.println("Enter the money coefficient for very bad mark (1): ");
        marksMoneyCoeff[1][4] = scan.nextInt();

        printMatrix(marksMoneyCoeff);

        return marksMoneyCoeff;
    }

    /**
     * Метод getCurrentGradesCoefficient использует методы createMarkArray для того, чтобы создать двумерную матрицу,
     * которая будет заполнять созданный массив, используя матрицы
     * , созданные методами numberOfMarks и setGradesCoefficient
     * , первая строка которой будет заполнена школьными оценками от 1 до 5
     * , вторая - произведением кол-ва соответствующих оценок на денежный коэффициент соответствующей оценки.
     * <p>
     * Метод распечатает полученный массив и общую сумму потенциальной выплаты.
     *
     * @return allSumForGrades возвращает общую сумму выплат за оценки.
     */
    public Integer getCurrentGradesCoefficient() {
        int[][] a = numberOfMarks(setGradesByOne());
        int[][] b = setGradesCoefficient();
        int[][] getSumMoneyForGrades = createMarkArray();
        allSumForGrades = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; ++j) {
                if (j != 0) {
                    getSumMoneyForGrades[j][i] = a[j][i] * b[j][i];
                    allSumForGrades += getSumMoneyForGrades[j][i];
                } else getSumMoneyForGrades[j][i] = a[j][i];
            }
        }
        System.out.println("Your money coefficient for marks is: ");
        printMatrix(getSumMoneyForGrades);
        System.out.println("All sum is: " + allSumForGrades);
        setAllSumForGrades(allSumForGrades);

        return allSumForGrades;
    }

}
