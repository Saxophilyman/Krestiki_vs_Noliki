import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Game {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;
    private static final int WIN_CHECK_SAME_MARK = 3;
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
            if (!checkGameNotEnd(playerFirst)) {
                break;
            }
            playerSecond.printMessageMoveOfPlayer();
            readCoordinateFromConsole(playerSecond);
            printGameField();
            if (!checkGameNotEnd(playerSecond)) {
                break;
            }
        }
    }

    private static void printMessage(String message) {
        System.out.println(message);
    }

    //ШАМАНСТВО С УСТАНОВКОЙ РАЗМЕРОВ ПОЛЯ
    //Создание игрового поля
    private void createPlayFieldSize() {
        System.out.println("Введите цифрами в консоль размеры поля, на котором хотите сыграть от 3 до 10 включительно.");
        horizontalFieldSize = getFieldSize("строк", "горизонталь");
        verticalFieldSize = getFieldSize("столбцов", "вертикаль");
        workField = new String[horizontalFieldSize][verticalFieldSize];
    }

    //Чтение параметров и запуск метода по считыванию размеров поля
    private static int getFieldSize(String indicatingOfLinesOrColumns, String indicatingOfHorizontalOrVertical) {
        printMessage("Количество " + indicatingOfLinesOrColumns + " (" + indicatingOfHorizontalOrVertical + ")" + ":");
        return readConcoleFieldSize(indicatingOfLinesOrColumns);
    }

    //Считывание размеров поля
    private static int readConcoleFieldSize(String indicatingOfLinesOrColumns) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String readConsoleSize = null;
        int turnNumberOfFieldSize = 0;
        try {
            readConsoleSize = reader.readLine();
        } catch (Exception e) {
            System.out.println("Что-то пошло не так, извините");
        }

        assert readConsoleSize != null;
        if (isInteger(readConsoleSize)) {
            turnNumberOfFieldSize = Integer.parseInt(readConsoleSize);
            if (fieldSizeDiapazonIsValid(turnNumberOfFieldSize)) {
                System.out.println("Поздравляю! Вы всё сделали правильно");
                return turnNumberOfFieldSize;
            } else {
                System.out.println("Сэр, минуточку! Мы же договаривались, что размеры поля должны быть от 3 до 10." +
                        "\n" + "Давайте попробуем ещё раз." +
                        "\n" + "Введите цифрами в консоль количество " + indicatingOfLinesOrColumns + ".");
                return readConcoleFieldSize(indicatingOfLinesOrColumns);
            }
        } else {
            System.out.println("Вы определённо где-то ошиблись при вводе. " + "\nПопробуйте ещё раз.");
            return readConcoleFieldSize(indicatingOfLinesOrColumns);

        }
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

    //Сама функция проверки диапазона считанного числа с консоли от 3 до 10
    public static boolean fieldSizeDiapazonIsValid(int size) {
        return size >= MIN_FIELD_SIZE && size <= MAX_FIELD_SIZE;
    }

    /*------------------------------------*/
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
        indexVerticalFromReadConsole = Ver;
        indexGorizontalFromReadConsole = Gor;
    }

    //Проверка заданных координат с размерами поля, заданными в текущей игре через декор-поле
    private boolean checkAddedCoordByIncludedFieldSize(String gorizontalCoord, String verticalCoord) {
        boolean check = false;
        for (int checkVerticalCoord = 1; checkVerticalCoord < playingDecorField[0].length; checkVerticalCoord++) {
            if (playingDecorField[0][checkVerticalCoord].equals(verticalCoord)) {
                for (int checkGorizontalCoord = 1; checkGorizontalCoord < playingDecorField.length; checkGorizontalCoord++) {
                    if (playingDecorField[checkGorizontalCoord][0].equals(gorizontalCoord)) {
                        check = true;
                        indexGorizontalOrVerticalByWorkField(checkVerticalCoord, checkGorizontalCoord);
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
        if (addedCoord.contains(readCrossZeroCordFromConsole)) {
            System.out.println("Братюнь, эти координаты уже забиты! Разуй глаза и попробуй ещё разок.");
            check = false;
        }
        return check;
    }


    //Проверка на окончание игры "три в ряд". Как смог придумать.
    boolean checkEndGame(Player player) {
        boolean gameNotEnd = true;
        String mark = player.getMark();

        for (int indexOfLines = 0; indexOfLines < workField.length; indexOfLines++)
            for (int indexOfColumns = 0; indexOfColumns < workField[indexOfLines].length; indexOfColumns++) {
                if (indexOfColumns < workField[indexOfLines].length - 2) {
                    if ((mark.equals(workField[indexOfLines][indexOfColumns])) &&
                            (mark.equals(workField[indexOfLines][indexOfColumns + 1])) &&
                            (mark.equals(workField[indexOfLines][indexOfColumns + 2]))) {
                        System.out.println("Поздравляем! " + player.getNameMark() + " Win!");
                        return false;
                    }
                }
                if (indexOfLines < workField.length - 2) {
                    if ((mark.equals(workField[indexOfLines][indexOfColumns])) &&
                            (mark.equals(workField[indexOfLines + 1][indexOfColumns])) &&
                            (mark.equals(workField[indexOfLines + 2][indexOfColumns]))) {
                        System.out.println("Поздравляем! " + player.getNameMark() + " Win!");
                        return false;
                    }
                }
                if (indexOfColumns < workField[indexOfLines].length - 2 && indexOfLines < workField.length - 2) {
                    if (((mark.equals(workField[indexOfLines][indexOfColumns])) &&
                            (mark.equals(workField[indexOfLines + 1][indexOfColumns + 1])) &&
                            (mark.equals(workField[indexOfLines + 2][indexOfColumns + 2]))) ||
                            ((mark.equals(workField[indexOfLines][workField[indexOfLines].length - indexOfColumns - 1])) &&
                                    (mark.equals(workField[indexOfLines + 1][workField[indexOfLines].length - indexOfColumns - 2])) &&
                                    (mark.equals(workField[indexOfLines + 2][workField[indexOfLines].length - indexOfColumns - 3])))
                    ) {
                        System.out.println("Поздравляем! " + player.getNameMark() + " Win!");
                        return false;
                    }

                } else if (addedCoord.size() > horizontalFieldSize * verticalFieldSize - 1) {
                    System.out.println("Ничья. Ходов больше нет");
                    return false;
                }
            }
        return gameNotEnd;
    }

    //Проверка на окончание игры "три в ряд" вызовом нескольких методов.
    private boolean checkGameNotEnd(Player player) {
        if (checkEndGameByLines(player) || checkEndGameByColumns(player) ||
                checkEndGameDiagonalFromLeftUpToRightDown(player) || checkEndGameDiagonalFromLeftDownToRightUp(player)) {
            System.out.println("Поздравляем! " + player.getNameMark() + " Win!");
            return false;
        }
        return true;
    }

    //Проверка "три в ряд" по горизонтали.
    private boolean checkEndGameByLines(Player player) {
        int countSamePlayerMark;
        for (int indexOfLines = 0; indexOfLines < workField.length; indexOfLines++) {
            countSamePlayerMark = 0;
            for (int indexOfColumns = 0; indexOfColumns < workField[indexOfLines].length; indexOfColumns++) {
                if (player.getMark().equals(workField[indexOfLines][indexOfColumns])) {
                    countSamePlayerMark++;
                    if (checkThreeSameMarkInOneLine(countSamePlayerMark)) {
                        return true;
                    }
                } else {
                    countSamePlayerMark = 0;
                }
            }
        }
        return false;
    }

    //Проверка "три в ряд" по вертикали.
    private boolean checkEndGameByColumns(Player player) {
        int countSamePlayerMark;
        for (int indexOfColumns = 0; indexOfColumns < workField[0].length; indexOfColumns++) {
            countSamePlayerMark = 0;
            for (int indexOfLines = 0; indexOfLines < workField.length; indexOfLines++) {
                if (player.getMark().equals(workField[indexOfLines][indexOfColumns])) {
                    countSamePlayerMark++;
                    if (checkThreeSameMarkInOneLine(countSamePlayerMark)) {
                        return true;
                    }
                } else {
                    countSamePlayerMark = 0;
                }
            }
        }
        return false;
    }

    //Проверка "три в ряд" по направлению с левого верхнего угла к правому нижнему.
    private boolean checkEndGameDiagonalFromLeftUpToRightDown(Player player) {
        int countSamePlayerMark;
        for (int indexOfLines = 0; indexOfLines < workField.length; indexOfLines++)
            for (int indexOfColumns = 0; indexOfColumns < workField[0].length; indexOfColumns++) {
                countSamePlayerMark = 0;
                for (int indexOfDiagonalLines = indexOfLines, indexOfDiagonalColumns = indexOfColumns;
                     indexOfDiagonalLines < workField.length && indexOfDiagonalColumns < workField[0].length; indexOfDiagonalColumns++, indexOfDiagonalLines++) {
//                    try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
//                    System.out.println(workField[indexOfDiagonalLines][indexOfDiagonalColumns]);
//                    try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
//                    System.out.println("[" + indexOfDiagonalLines + "][" + indexOfDiagonalColumns + "]");
                    if (player.getMark().equals(workField[indexOfDiagonalLines][indexOfDiagonalColumns])) {
                        countSamePlayerMark++;
                        if (checkThreeSameMarkInOneLine(countSamePlayerMark)) {
                            return true;
                        }
                    } else {
                        countSamePlayerMark = 0;
                    }
                }
            }
        return false;
    }

    //Проверка "три в ряд" по направлению с левого нижнего угла к правому верхнему.
    private boolean checkEndGameDiagonalFromLeftDownToRightUp(Player player) {
        int countSamePlayerMark;
        for (int indexOfLines = workField.length - 1; indexOfLines >= 0; indexOfLines--)
            for (int indexOfColumns = 0; indexOfColumns < workField[0].length; indexOfColumns++) {
                countSamePlayerMark = 0;
                for (int indexOfDiagonalLines = indexOfLines, indexOfDiagonalColumns = indexOfColumns;
                     indexOfDiagonalLines >= 0 && indexOfDiagonalColumns < workField[0].length; indexOfDiagonalColumns++, indexOfDiagonalLines--) {
                    if (player.getMark().equals(workField[indexOfDiagonalLines][indexOfDiagonalColumns])) {
                        countSamePlayerMark++;
                        if (checkThreeSameMarkInOneLine(countSamePlayerMark)) {
                            return true;
                        }
                    } else {
                        countSamePlayerMark = 0;
                    }
                }
            }
        return false;
    }

    //Проверка счётчика на совпадение подряд 3х символов.
    private boolean checkThreeSameMarkInOneLine(int samePlayerMark) {
        if (samePlayerMark == WIN_CHECK_SAME_MARK) {
            return true;
        }
        return false;
    }
}


/**
 * Обход массива по диагонали слева направо через 2 двойных цикла
 * (сначала диагональ по нулевой горизотали, а затем по нулевой вертикали)
 * зы. проверок меньше, но код объёмнее.
 *
 * private boolean checkEndGameDiagonalFromLeftUpToRightDown(Player player) {
 * int countSamePlayerMark = 0;
 * //    for (int indexOfLines = 0; indexOfLines < workField.length; indexOfLines++)
 * for (int indexOfColumns = 0, indexOfLines = 0;
 * indexOfLines < workField.length && indexOfColumns < workField[0].length; indexOfColumns++)
 * for (int indexOfDiagonalLines = indexOfLines, indexOfDiagonalColumns = indexOfColumns;
 * indexOfDiagonalLines < workField.length && indexOfDiagonalColumns < workField[0].length;
 * indexOfDiagonalColumns++, indexOfDiagonalLines++) {
 * if (player.getMark().equals(workField[indexOfDiagonalLines][indexOfDiagonalColumns])) {
 * countSamePlayerMark++;
 * if (checkThreeSameMarkInOneLine(countSamePlayerMark)) {
 * return true;
 * }
 * } else {
 * countSamePlayerMark = 0;
 * }
 * }
 * <p>
 * for (int indexOfColumns = 0, indexOfLines = 0;
 * indexOfLines < workField.length && indexOfColumns < workField[0].length; indexOfLines++)
 * for (int indexOfDiagonalLines = indexOfLines, indexOfDiagonalColumns = indexOfColumns;
 * indexOfDiagonalLines < workField.length && indexOfDiagonalColumns < workField[0].length;
 * indexOfDiagonalColumns++, indexOfDiagonalLines++) {
 * <p>
 * //                    try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
 * //
 * //                    System.out.println(workField[indexOfDiagonalLines][indexOfDiagonalColumns]);
 * //
 * //                    try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
 * //
 * //                    System.out.println("[" + indexOfDiagonalLines + "][" + indexOfDiagonalColumns + "]");
 * <p>
 * if (player.getMark().equals(workField[indexOfDiagonalLines][indexOfDiagonalColumns])) {
 * countSamePlayerMark++;
 * if (checkThreeSameMarkInOneLine(countSamePlayerMark)) {
 * return true;
 * }
 * } else {
 * countSamePlayerMark = 0;
 * }
 * <p>
 * }
 * return false;
 * }
 **/
/**
 private boolean checkEndGameDiagonalFromLeftUpToRightDown(Player player) {
 int countSamePlayerMark = 0;
 //    for (int indexOfLines = 0; indexOfLines < workField.length; indexOfLines++)
 for (int indexOfColumns = 0, indexOfLines = 0;
 indexOfLines < workField.length && indexOfColumns < workField[0].length; indexOfColumns++)
 for (int indexOfDiagonalLines = indexOfLines, indexOfDiagonalColumns = indexOfColumns;
 indexOfDiagonalLines < workField.length && indexOfDiagonalColumns < workField[0].length;
 indexOfDiagonalColumns++, indexOfDiagonalLines++) {
 if (player.getMark().equals(workField[indexOfDiagonalLines][indexOfDiagonalColumns])) {
 countSamePlayerMark++;
 if (checkThreeSameMarkInOneLine(countSamePlayerMark)) {
 return true;
 }
 } else {
 countSamePlayerMark = 0;
 }
 }

 for (int indexOfColumns = 0, indexOfLines = 0;
 indexOfLines < workField.length && indexOfColumns < workField[0].length; indexOfLines++)
 for (int indexOfDiagonalLines = indexOfLines, indexOfDiagonalColumns = indexOfColumns;
 indexOfDiagonalLines < workField.length && indexOfDiagonalColumns < workField[0].length;
 indexOfDiagonalColumns++, indexOfDiagonalLines++) {

 //                    try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
 //
 //                    System.out.println(workField[indexOfDiagonalLines][indexOfDiagonalColumns]);
 //
 //                    try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
 //
 //                    System.out.println("[" + indexOfDiagonalLines + "][" + indexOfDiagonalColumns + "]");

 if (player.getMark().equals(workField[indexOfDiagonalLines][indexOfDiagonalColumns])) {
 countSamePlayerMark++;
 if (checkThreeSameMarkInOneLine(countSamePlayerMark)) {
 return true;
 }
 } else {
 countSamePlayerMark = 0;
 }

 }
 return false;
 }
 **/



















