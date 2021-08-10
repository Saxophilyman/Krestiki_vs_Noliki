import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final static String[][] GAME_FIELD = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};
    private static String[][] workField = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};
    private static String[][] decorField = {{"/", "1", "2", "3"}, {"a"}, {"b"}, {"c"}};
    private static ArrayList<String> addedCord = new ArrayList<>();
    Player playerFirst = new Player("Player_1", "X", "крестика");
    Player playerSecond = new Player("Player_2", "O", "нолика");

    public static class Player {
        private String name;
        private String mark;
        private String nameMark;

        private Player(String name, String mark, String nameMark) {
            this.name = name;
            this.mark = mark;
            this.nameMark = nameMark;
        }

        private void printMessageMoveOfPlayer() {
            System.out.println("Ходит " + this.name + "\nВведите в консоль координаты для постановки " + this.nameMark);

        }

        private boolean checkIfNotEqualsAddedCord(String x) {
            boolean check = true;
            for (int i = 0; i < addedCord.size(); i++) {
                if (x.equals(addedCord.get(i))) {
                    System.out.println("Братюнь, эти координаты уже забиты! Разуй глаза и попробуй ещё разок.");
                    check = false;
                }
            }
            return check;
        }

        private void readConsole() {
            String addXO = null;
            try {
                addXO = reader.readLine();
            } catch (Exception e) {
                System.out.println("Что-то пошло не так, извините");
            }
            assert addXO != null;
            if (addXO.matches("a1|a2|a3|b1|b2|b3|c1|c2|c3")) {
                System.out.println("ok");
                if (checkIfNotEqualsAddedCord(addXO)) {
                    addedCord.add(addXO);
                    addCoord(addXO);
                } else {
                    readConsole();
                }
            } else {
                System.out.println("Извините, таких координат не существует, попробуйте ещё раз");
                readConsole();
            }
        }
//разбить посимвольно и присвоить каждому символу свою координату

        private void addCoord(String a) {
           char [] firstAndSecondCoordReadConsole = a.toCharArray();
            int firstCoordWorkFieldVertical = firstAndSecondCoordReadConsole[0];
            int secondCoordWorkFieldHorizontal = firstAndSecondCoordReadConsole[1];
            if (firstCoordWorkFieldVertical == 97){
                firstCoordWorkFieldVertical = 0;
            }
            if (firstCoordWorkFieldVertical == 98){
                firstCoordWorkFieldVertical = 1;
            }
            if (firstCoordWorkFieldVertical == 99){
                firstCoordWorkFieldVertical = 2;
            }
            if (secondCoordWorkFieldHorizontal == 49){
                secondCoordWorkFieldHorizontal = 0;
            }
            if (secondCoordWorkFieldHorizontal == 50){
                secondCoordWorkFieldHorizontal = 1;
            }
            if (secondCoordWorkFieldHorizontal == 51){
                secondCoordWorkFieldHorizontal = 2;
            }

            workField[firstCoordWorkFieldVertical][secondCoordWorkFieldHorizontal] = this.mark;

        }

        private boolean checkEndGame() {
            boolean gameNotEnd = true;

            String a1 = workField[0][0];
            String a2 = workField[0][1];
            String a3 = workField[0][2];
            String b1 = workField[1][0];
            String b2 = workField[1][1];
            String b3 = workField[1][2];
            String c1 = workField[2][0];
            String c2 = workField[2][1];
            String c3 = workField[2][2];
                        if ((a1.equals(this.mark) && a2.equals(this.mark) && a3.equals(this.mark)) ||
                    (b1.equals(this.mark) && b2.equals(this.mark) && b3.equals(this.mark)) ||
                    (c1.equals(this.mark) && c2.equals(this.mark) && c3.equals(this.mark)) ||
                    (a1.equals(this.mark) && b1.equals(this.mark) && c1.equals(this.mark)) ||
                    (a2.equals(this.mark) && b2.equals(this.mark) && c2.equals(this.mark)) ||
                    (a3.equals(this.mark) && b3.equals(this.mark) && c3.equals(this.mark)) ||
                    (a1.equals(this.mark) && b2.equals(this.mark) && c3.equals(this.mark)) ||
                    (c1.equals(this.mark) && b2.equals(this.mark) && a3.equals(this.mark)))
            {
                System.out.println("Поздравляем! " + this.name + " Win!");
                gameNotEnd = false;
            } else if (addedCord.size() > 8) {
                System.out.println("Ничья. Ходов больше нет");
                gameNotEnd = false;
            }
            return gameNotEnd;
        }
    }

    public void playingGame() {
        refreshGamePole(GAME_FIELD, workField, addedCord);
        printAllGamePole();
        while (true) {
            playerFirst.printMessageMoveOfPlayer();
            playerFirst.readConsole();
            printAllGamePole();
            if (!playerFirst.checkEndGame()) {
                System.out.println("Кряяя");
                break;
            }
            playerSecond.printMessageMoveOfPlayer();
            playerSecond.readConsole();
            printAllGamePole();
            if (!playerSecond.checkEndGame()) {
                System.out.println("Кряяя");
                break;
            }
        }
    }


    public static void printAllGamePole() {
        int j =0;

        for (int lineDecor = 0; lineDecor < decorField.length; lineDecor++) {
            System.out.print(" " + decorField[0][lineDecor] + " ");}
        System.out.println();
        for (int tabDecor = 1; tabDecor < decorField.length; tabDecor++) {
            System.out.print(" " + decorField[tabDecor][0] + " ");
            for (int i = 0;  i < workField.length; i++) {  //идём по строкам
                System.out.print(" " + workField[j][i] + " "); //вывод элемента

            }
            j++;
            System.out.println();//перенос строки ради визуального сохранения табличной формы
        }
    }


    private static void refreshGamePole(String[][] aSource, String[][] aDestination, ArrayList<String> arr) {
        for (int i = 0; i < aSource.length; i++) {
            System.arraycopy(aSource[i], 0, aDestination[i], 0, aSource[i].length);
        }
        arr.clear();
    }

}
























