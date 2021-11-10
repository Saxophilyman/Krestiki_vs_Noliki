import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Game {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static int Gor;
    static int Ver;
    int indexVerFromReadConsole;
    int indexGorFromReadConsole;
    private static String[][] workField; //= new String[3][3];
    private String[][] allDecorFullField = {{"/", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
            {"a"}, {"b"}, {"c"}, {"d"}, {"e"}, {"f"}, {"g"}, {"h"}, {"i"}, {"j"}};
    private static String[][] playingDecorField;
    private ArrayList<String> addedCoord = new ArrayList<>();
    private Player playerFirst;
    private Player playerSecond;

    Game(Player first, Player second) {
        playerFirst = first;
        playerSecond = second;
    }


    //Механика игры
    public void startGame() {
        createPlayFieldSize();
        createPlayDecorField();
        //checkPrintField (playingDecorField);
        printGameField();

        while (true) {
            playerFirst.printMessageMoveOfPlayer();
            readConsole(playerFirst);
            printGameField();
            if (!checkEndGame(playerFirst)) {
                System.out.println("Кряяя");
                break;
            }
            playerSecond.printMessageMoveOfPlayer();
            readConsole(playerSecond);
            printGameField();
            if (!checkEndGame(playerSecond)) {
                System.out.println("Кряяя");
                break;
            }
        }
    }


    //ШАМАНСТВО С УСТАНОВКОЙ РАЗМЕРОВ ПОЛЯ
    //Создание игрового поля
    private static void createPlayFieldSize() {
        System.out.println("Введите цифрами в консоль размеры поля, на котором хотите сыграть от 3 до 10 включительно.");
        Gor = getFieldSize("строк", "горизонталь");
        CheckGorVer(Gor, "Количество строк по горизонтали", "строк", "горизонталь");
        System.out.println("Истинный Gor " + Gor);
        Ver = getFieldSize("столбцов", "вертикаль");
        CheckGorVer(Ver, "Количество столбцов по вертикали", "столбцов", "вертикаль");
        System.out.println("Истинный Ver " + Ver);
        workField = new String[Gor][Ver];
    }

    //Чтение параметров и запуск метода по считыванию размеров поля
    private static int getFieldSize(String s1, String s2) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Количество " + s1 + " (" + s2 + ")" + ":");
        return readConcoleFieldSize();
    }

    //Считывание размеров поля
    private static int readConcoleFieldSize() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String readConsoleSizeT = null;
        int iVal = 0;
        try {
            readConsoleSizeT = reader.readLine();
        } catch (Exception e) {
            System.out.println("Что-то пошло не так, извините");
        }

        assert readConsoleSizeT != null;
//        if (!isInteger(readConsoleSizeT)) {
//            System.out.println("Вы где-то ошиблись, попробуйте ещё раз,."
//                    + "\nВведите ЦИФРАМИ в консоль размеры поля, на котором хотите сыграть");
//            readConcoleFieldSize();
//        }
//        else  iVal = Integer.parseInt(readConsoleSizeT);
        if (isInteger(readConsoleSizeT)) {
            iVal = Integer.parseInt(readConsoleSizeT);
            return iVal;
        } else {
            System.out.println("Вы определённо где-то ошиблись при вводе. "
                    + "\nМы ещё работаем над нашим проектом..." +
                    "\nА пока что введите любое число и выслушайте какой вы жопорук!");
            readConcoleFieldSize();
        }
        return iVal;
        //почему записывается первый раз??? а не "правильный"
    }

    //Проверка на число (введённое в консоль)
    public static boolean isInteger(String s) {
        if (s == null || s.equals("")) {
            return false;
        }

        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }

    //Запуск метода проверки диапазона поля от 3 до 10 и обработка неправильного значения
    private static void CheckGorVer(int GorVer, String text, String s1, String s2) {
        System.out.println("Получили " + GorVer);
        while (!isValid(GorVer)) {
            System.out.println("Отлично Блятъ! ");
            System.out.println("Мы же договаривались, что размеры поля должны быть от 3 до 10.");
            System.out.println("Давайте попробуем ещё раз.");
            System.out.println("Введите цифрами в консоль размеры поля, на котором хотите сыграть.");

            GorVer = getFieldSize(s1, s2);
        }
        System.out.println("Поздравляю! Хоть что-то вы можете сделать правильно"
                + "\n" + text + " равно " + GorVer);
        if (s1.equals("строк")){
            Gor = GorVer;
        }
        if (s1.equals("столбцов")){
            Ver = GorVer;
        }
        //return GorVer;
    }

    //Сама функция проверки диапазона считанного числа с консоли от 3 до 10
    public static boolean isValid(int x) {
        if (x > 2 && x < 11) {
            return true;
        }
        return false;
    }

    /*------------------------------------*/

    //Проверка печатаемого поля (без соединения с декором)
    static void checkPrintField(String[][] twoDimArray) {
        for (int i = 0; i < twoDimArray.length; i++) {  //идём по строкам twoDimArray.length
            for (int j = 0; j < twoDimArray[i].length; j++) {//идём по столбцам twoDimArray[i].length
                System.out.print(" " + twoDimArray[i][j] + " "); //вывод элемента
            }
            System.out.println();//перенос строки ради визуального сохранения табличной формы
        }
    }

    //Создание поля для декора
    private void createPlayDecorField() {
        playingDecorField = new String[Gor + 1][];
        playingDecorField[0] = new String[Ver + 1];
        //      System.out.println(Arrays.deepToString(playingDecorField));
        copyFromAllDecorFullFieldToPlayingDecorField(allDecorFullField, playingDecorField);
        //     System.out.println(Arrays.deepToString(playingDecorField));
    }

    //Копирование актуальных размеров поля-декора для текущей(запущенной в данном цикле) игры
    public static void copyFromAllDecorFullFieldToPlayingDecorField(String[][] allDecorFullField, String[][] playingDecorField) {
        for (int i = 0; i < playingDecorField[0].length; i++) {
            playingDecorField[0][i] = allDecorFullField[0][i];
        }
        for (int j = 1; j < playingDecorField.length; j++) {
            playingDecorField[j] = new String[1];
            playingDecorField[j][0] = allDecorFullField[j][0];
        }
    }

    //Печатаем всё поля для игры. Декор совместно с игровым полем
    public void printGameField() {
        System.out.println("Печатаем поле");
        int i = 0;

        for (int lineDecor = 0; lineDecor < playingDecorField[0].length; lineDecor++) {
            System.out.print(" " + playingDecorField[0][lineDecor] + " ");
        }
        System.out.println();
        for (int tabDecor = 1; tabDecor < playingDecorField.length; tabDecor++) {
            System.out.print(" " + playingDecorField[tabDecor][0] + " ");
            for (int j = 0; j < workField[0].length && i < workField.length; j++) {
                String currentLetter = workField[i][j];
                if (currentLetter == null) {
                    System.out.print(" " + " " + " ");
                } else {
                    System.out.print(" " + currentLetter + " "); //вывод элемента
                }
            }
            i++;
            System.out.println();//перенос строки ради визуального сохранения табличной формы
        }
    }

    //Читаем координаты с консоли для постановки крестика-нолика
    public void readConsole(Player player) {
        String readCrossZeroCord = null;
        try {
            readCrossZeroCord = reader.readLine();
        } catch (Exception e) {
            System.out.println("Что-то пошло не так, извините");
        }
        assert readCrossZeroCord != null;
        char[] firstAndSecondCoordReadCrossZeroCord = readCrossZeroCord.toCharArray();
        if (!(firstAndSecondCoordReadCrossZeroCord.length == 2)) {
            System.out.println("Извините, таких координат не существует, попробуйте ещё раз");
            readConsole(player);
        } else {
            String gorCoord = String.valueOf(firstAndSecondCoordReadCrossZeroCord[0]);
            String verCoord = String.valueOf(firstAndSecondCoordReadCrossZeroCord[1]);

            if (checkAddedCoordByIncludedFieldSize(gorCoord, verCoord)) {
                System.out.println("первый: " + gorCoord + " второй: " + verCoord);
                if (checkIfNotEqualsAddedCord(readCrossZeroCord)) {
                    System.out.println("Всё нормально, врубай! таких координат ещё не вводили");
                    addedCoord.add(readCrossZeroCord);
                    System.out.println("Записали введённые координаты для следующих проверок");
                    //addCoord(player, indexVer, indexGor);
                    workField[indexGorFromReadConsole - 1][indexVerFromReadConsole - 1] = player.getMark();
                } else {
                    readConsole(player);
                }
            } else {
                System.out.println("Извините, таких координат не существует, попробуйте ещё раз");
                readConsole(player);
            }
        }
    }

    //Определяем индексы введённых координат для workField
    private void indexGorVerByWorkField(int Ver, int Gor) {
        System.out.println("Индекс вертикали: " + Ver);
        indexVerFromReadConsole = Ver;
        System.out.println("Индекс горизонтали: " + Gor);
        indexGorFromReadConsole = Gor;
    }

    //Проверка заданных координат с размерами поля, заданными в текущей игре через декор-поле
    private boolean checkAddedCoordByIncludedFieldSize(String gorCoord, String verCoord) {
        boolean check = false;
//        String gorCoord = String.valueOf(GorVer[0]);
//        String verCoord = String.valueOf(GorVer[1]);
        for (int i = 1; i < playingDecorField[0].length; i++) {
            if (playingDecorField[0][i].equals(verCoord)) {
                //System.out.println("Индекс вертикали: " + i);
                for (int j = 1; j < playingDecorField.length; j++) {
                    if (playingDecorField[j][0].equals(gorCoord)) {
                        //System.out.println("Индекс горизонтали: " + j);
                        check = true;
                        indexGorVerByWorkField(i, j);
                        break;
                    }
                }
            }
        }
        return check;
    }

    //Проверка, были ли уже заданные данные координаты
    private boolean checkIfNotEqualsAddedCord(String x) {
        boolean check = true;
        for (int i = 0; i < addedCoord.size(); i++) {
            if (x.equals(addedCoord.get(i))) {
                System.out.println("Братюнь, эти координаты уже забиты! Разуй глаза и попробуй ещё разок.");
                check = false;
            }
        }
        return check;
    }

    //присвоить каждому символу свою координату - ПЕРЕФРАЗИРОВАТЬ
//    private void addCoord(Player player, int gorCoord, int verCoord) {
//        workField[verCoord-1][gorCoord-1] = player.getMark();
//    }

    //Проверка на окончание игры "три в ряд". Как смог придумать.
    boolean checkEndGame(Player player) {
        boolean gameNotEnd = true;


        for (int idemPoStrokam = 0; idemPoStrokam < workField.length; idemPoStrokam++)
            for (int idemPoStolbcam = 0; idemPoStolbcam < workField[idemPoStrokam].length; idemPoStolbcam++) {
                if (idemPoStolbcam < workField[idemPoStrokam].length - 2) {
                    if ((player.getMark().equals(workField[idemPoStrokam][idemPoStolbcam])) &&
                            (player.getMark().equals(workField[idemPoStrokam][idemPoStolbcam + 1])) &&
                            (player.getMark().equals(workField[idemPoStrokam][idemPoStolbcam + 2]))) {
                        System.out.println("Поздравляем! " + player.getNameMark() + " Win!");
                        gameNotEnd = false;
                    }
                }
                if (idemPoStrokam < workField.length - 2) {
                    if ((player.getMark().equals(workField[idemPoStrokam][idemPoStolbcam])) &&
                            (player.getMark().equals(workField[idemPoStrokam + 1][idemPoStolbcam])) &&
                            (player.getMark().equals(workField[idemPoStrokam + 2][idemPoStolbcam]))) {
                        System.out.println("Поздравляем! " + player.getNameMark() + " Win!");
                        gameNotEnd = false;
                    }
                }
                if (idemPoStolbcam < workField[idemPoStrokam].length - 2 && idemPoStrokam < workField.length - 2) {
                    if (((player.getMark().equals(workField[idemPoStrokam][idemPoStolbcam])) &&
                            (player.getMark().equals(workField[idemPoStrokam + 1][idemPoStolbcam + 1])) &&
                            (player.getMark().equals(workField[idemPoStrokam + 2][idemPoStolbcam + 2]))) ||
                            ((player.getMark().equals(workField[idemPoStrokam][workField[idemPoStrokam].length - idemPoStolbcam - 1])) &&
                                    (player.getMark().equals(workField[idemPoStrokam + 1][workField[idemPoStrokam].length - idemPoStolbcam - 2])) &&
                                    (player.getMark().equals(workField[idemPoStrokam + 2][workField[idemPoStrokam].length - idemPoStolbcam - 3])))
                    ) {
                        System.out.println("Поздравляем! " + player.getNameMark() + " Win!");
                        gameNotEnd = false;
                    }

                } else if (addedCoord.size() > Gor * Ver - 1) {
                    System.out.println("Ничья. Ходов больше нет");
                    gameNotEnd = false;
                }
            }
        return gameNotEnd;
    }

    //Обновление поля. В данной версии не требуется
//    private static void refreshGamePole(String[][] aSource, String[][] aDestination, ArrayList<String> arr) {
//        for (int i = 0; i < aSource.length; i++) {
//            System.arraycopy(aSource[i], 0, aDestination[i], 0, aSource[i].length);
//        }
//        arr.clear();
//    }

}
























