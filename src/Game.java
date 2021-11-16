import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Game {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;
    private int horizontalFieldSize;
    private int verticalFieldSize;
    int indexVerticalFromReadConsole;
    int indexGorizontalFromReadConsole;
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
        printGameField();
        while (true) {
            playerFirst.printMessageMoveOfPlayer();
            readCoordinateFromConsole(playerFirst);
            printGameField();
            if (!checkEndGame(playerFirst)) {
                System.out.println("Кряяя");
                break;
            }
            playerSecond.printMessageMoveOfPlayer();
            readCoordinateFromConsole(playerSecond);
            printGameField();
            if (!checkEndGame(playerSecond)) {
                System.out.println("Кряяя");
                break;
            }
        }
    }


    //ШАМАНСТВО С УСТАНОВКОЙ РАЗМЕРОВ ПОЛЯ
    //Создание игрового поля
    private void createPlayFieldSize() {
        System.out.println("Введите цифрами в консоль размеры поля, на котором хотите сыграть от 3 до 10 включительно.");
        horizontalFieldSize = getFieldSize("строк", "горизонталь");
        checkInputFieldSize(horizontalFieldSize, "Количество строк по горизонтали", "строк", "горизонталь");
        System.out.println("Истинный Gor " + horizontalFieldSize);
        verticalFieldSize = getFieldSize("столбцов", "вертикаль");
        checkInputFieldSize(verticalFieldSize, "Количество столбцов по вертикали", "столбцов", "вертикаль");
        System.out.println("Истинный Ver " + verticalFieldSize);
        workField = new String[horizontalFieldSize][verticalFieldSize];
    }

    //Чтение параметров и запуск метода по считыванию размеров поля
    private static int getFieldSize(String indicatingOfLinesOrColumns, String indicatingOfHorizontalOrVertical) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Количество " + indicatingOfLinesOrColumns + " (" + indicatingOfHorizontalOrVertical + ")" + ":");
        return readConcoleFieldSize();
    }

    //Считывание размеров поля
    private static int readConcoleFieldSize() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String readConsoleSizeFromConsole = null;
        int iVal = 0;
        try {
            readConsoleSizeFromConsole = reader.readLine();
        } catch (Exception e) {
            System.out.println("Что-то пошло не так, извините");
        }

        assert readConsoleSizeFromConsole != null;
        if (isInteger(readConsoleSizeFromConsole)) {
            iVal = Integer.parseInt(readConsoleSizeFromConsole);
            return iVal;
        } else {
            System.out.println("Вы определённо где-то ошиблись при вводе. "
                    + "\nМы ещё работаем над нашим проектом..." +
                    "\nА пока что введите любое число и выслушайте какой вы жопорук!");
            //return
                    readConcoleFieldSize();
        }
        return iVal;
        //почему записывается первый раз??? а не "правильный"
    }

    //Проверка на число (введённое в консоль)
    public static boolean isInteger(String readConsoleSizeFromConsole) {
        if (readConsoleSizeFromConsole == null || readConsoleSizeFromConsole.equals("")) {
            return false;
        }
        try {
            Integer.parseInt(readConsoleSizeFromConsole);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }

    //Запуск метода проверки диапазона поля от 3 до 10 и обработка неправильного значения
    private void checkInputFieldSize(int valueOfHorizontalOrVertical, String messageIndicatingForHorizontalOrVertical,
                                     String indicatingOfLinesOrColumns, String indicatingOfHorizontalOrVertical) {
        System.out.println("Получили " + valueOfHorizontalOrVertical);
        while (!isValid(valueOfHorizontalOrVertical)) {
            System.out.println("Отлично Блятъ! " +
                    "\n" + "Мы же договаривались, что размеры поля должны быть от 3 до 10." +
                    "\n" + "Давайте попробуем ещё раз." +
                    "\n" +  "Введите цифрами в консоль размеры поля, на котором хотите сыграть.");

            valueOfHorizontalOrVertical = getFieldSize(indicatingOfLinesOrColumns, indicatingOfHorizontalOrVertical);
        }
        System.out.println("Поздравляю! Хоть что-то вы можете сделать правильно"
                + "\n" + messageIndicatingForHorizontalOrVertical + " равно " + valueOfHorizontalOrVertical);
        if (indicatingOfLinesOrColumns.equals("строк")){
            horizontalFieldSize = valueOfHorizontalOrVertical;
        }
        if (indicatingOfLinesOrColumns.equals("столбцов")){
            verticalFieldSize = valueOfHorizontalOrVertical;
        }
    }

    //Сама функция проверки диапазона считанного числа с консоли от 3 до 10
    public static boolean isValid(int size) {
        return  size >= MIN_FIELD_SIZE && size <= MAX_FIELD_SIZE;
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
        playingDecorField = new String[horizontalFieldSize + 1][];
        playingDecorField[0] = new String[verticalFieldSize + 1];
        copyFromAllDecorFullFieldToPlayingDecorField(allDecorFullField, playingDecorField);
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
    public void readCoordinateFromConsole(Player player) {
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
            readCoordinateFromConsole(player);
        } else {
            String gorizontalCoord = String.valueOf(firstAndSecondCoordReadCrossZeroCord[0]);
            String verticalCoord = String.valueOf(firstAndSecondCoordReadCrossZeroCord[1]);

            if (checkAddedCoordByIncludedFieldSize(gorizontalCoord, verticalCoord)) {
                System.out.println("первый: " + gorizontalCoord + " второй: " + verticalCoord);
                if (checkIfNotEqualsAddedCord(readCrossZeroCord)) {
                    System.out.println("Всё нормально, врубай! таких координат ещё не вводили");
                    addedCoord.add(readCrossZeroCord);
                    workField[indexGorizontalFromReadConsole - 1][indexVerticalFromReadConsole - 1] = player.getMark();
                } else {
                    readCoordinateFromConsole(player);
                }
            } else {
                System.out.println("Извините, таких координат не существует, попробуйте ещё раз");
                readCoordinateFromConsole(player);
            }
        }
    }

    //Определяем индексы введённых координат для workField
    private void indexGorizontalOrVerticalByWorkField(int Ver, int Gor) {
        System.out.println("Индекс вертикали: " + Ver);
        indexVerticalFromReadConsole = Ver;
        System.out.println("Индекс горизонтали: " + Gor);
        indexGorizontalFromReadConsole = Gor;
    }

    //Проверка заданных координат с размерами поля, заданными в текущей игре через декор-поле
    private boolean checkAddedCoordByIncludedFieldSize(String gorizontalCoord, String verticalCoord) {
        boolean check = false;
        for (int i = 1; i < playingDecorField[0].length; i++) {
            if (playingDecorField[0][i].equals(verticalCoord)) {
                for (int j = 1; j < playingDecorField.length; j++) {
                    if (playingDecorField[j][0].equals(gorizontalCoord)) {
                        check = true;
                        indexGorizontalOrVerticalByWorkField(i, j);
                        break;
                    }
                }
            }
        }
        return check;
    }

    //Проверка, были ли уже заданные данные координаты
    private boolean checkIfNotEqualsAddedCord(String readCrossZeroCordFromConsole) {
        boolean check = true;
        if (addedCoord.contains(readCrossZeroCordFromConsole)){
            System.out.println("Братюнь, эти координаты уже забиты! Разуй глаза и попробуй ещё разок.");
            check = false;
        }
        return check;
//        for (int i = 0; i < addedCoord.size(); i++) {
//            if (readCrossZeroCordFromConsole.equals(addedCoord.get(i))) {
//                System.out.println("Братюнь, эти координаты уже забиты! Разуй глаза и попробуй ещё разок.");
//                check = false;
//            }
//        }
    }


    //Проверка на окончание игры "три в ряд". Как смог придумать.
    boolean checkEndGame(Player player) {
        boolean gameNotEnd = true;
        String mark = player.getMark();

        for (int goByIndexOfLines = 0; goByIndexOfLines < workField.length; goByIndexOfLines++)
            for (int goByIndexOfColumns = 0; goByIndexOfColumns < workField[goByIndexOfLines].length; goByIndexOfColumns++) {
                if (goByIndexOfColumns < workField[goByIndexOfLines].length - 2) {
                    if ((mark.equals(workField[goByIndexOfLines][goByIndexOfColumns])) &&
                            (mark.equals(workField[goByIndexOfLines][goByIndexOfColumns + 1])) &&
                            (mark.equals(workField[goByIndexOfLines][goByIndexOfColumns + 2]))) {
                        System.out.println("Поздравляем! " + player.getNameMark() + " Win!");
                        return false;
                        //gameNotEnd = false;
                    }
                }
                if (goByIndexOfLines < workField.length - 2) {
                    if ((mark.equals(workField[goByIndexOfLines][goByIndexOfColumns])) &&
                            (mark.equals(workField[goByIndexOfLines + 1][goByIndexOfColumns])) &&
                            (mark.equals(workField[goByIndexOfLines + 2][goByIndexOfColumns]))) {
                        System.out.println("Поздравляем! " + player.getNameMark() + " Win!");
                        return false;
                        //gameNotEnd = false;
                    }
                }
                if (goByIndexOfColumns < workField[goByIndexOfLines].length - 2 && goByIndexOfLines < workField.length - 2) {
                    if (((mark.equals(workField[goByIndexOfLines][goByIndexOfColumns])) &&
                            (mark.equals(workField[goByIndexOfLines + 1][goByIndexOfColumns + 1])) &&
                            (mark.equals(workField[goByIndexOfLines + 2][goByIndexOfColumns + 2]))) ||
                            ((mark.equals(workField[goByIndexOfLines][workField[goByIndexOfLines].length - goByIndexOfColumns - 1])) &&
                                    (mark.equals(workField[goByIndexOfLines + 1][workField[goByIndexOfLines].length - goByIndexOfColumns - 2])) &&
                                    (mark.equals(workField[goByIndexOfLines + 2][workField[goByIndexOfLines].length - goByIndexOfColumns - 3])))
                    ) {
                        System.out.println("Поздравляем! " + player.getNameMark() + " Win!");
                        return false;
                        //gameNotEnd = false;
                    }

                } else if (addedCoord.size() > horizontalFieldSize * verticalFieldSize - 1) {
                    System.out.println("Ничья. Ходов больше нет");
                    return false;
                    //gameNotEnd = false;
                }
            }
        return gameNotEnd;
    }

}


/**Я конечно понимаю, что в теории код более понятным должен выходить для чтения, но не могу отвязаться от мысли:
 * А не дохуя ли длинные названия методов и переменных получаются, это нормально или тут есть ещё какая-то своя особеннность?
 * И в некотором смысле с непривычки становится ещё запутаннее, т.к. методы схожие и всё об одном и том же
 * Ну или называть их более корректно-осмысленными предстоит ещё научиться
 * хотя ведь понятно же, что если "программа" про крестики нолики гор - это горизонталь, разве не так?
 *
 * **/





















