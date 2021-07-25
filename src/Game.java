import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Game {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final static String[][] field = {{"/", "1", "2", "3"}, {"a", " ", " ", " "}, {"b", " ", " ", " "}, {"c", " ", " ", " "}};
    private static String[][] GAME_FIELD = {{"/", "1", "2", "3"}, {"a", " ", " ", " "}, {"b", " ", " ", " "}, {"c", " ", " ", " "}};
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

        private void addCoord(String a) {
            if (a.equals("a1")) {
                GAME_FIELD[1][1] = this.mark;
            }
            if (a.equals("a2")) {
                GAME_FIELD[1][2] = this.mark;
            }
            if (a.equals("a3")) {
                GAME_FIELD[1][3] = this.mark;
            }
            if (a.equals("b1")) {
                GAME_FIELD[2][1] = this.mark;
            }
            if (a.equals("b2")) {
                GAME_FIELD[2][2] = this.mark;
            }
            if (a.equals("b3")) {
                GAME_FIELD[2][3] = this.mark;
            }
            if (a.equals("c1")) {
                GAME_FIELD[3][1] = this.mark;
            }
            if (a.equals("c2")) {
                GAME_FIELD[3][2] = this.mark;
            }
            if (a.equals("c3")) {
                GAME_FIELD[3][3] = this.mark;
            }
        }

        private boolean checkEndGame() {
            boolean gameNotEnd = true;
            String a1 = GAME_FIELD[1][1];
            String a2 = GAME_FIELD[1][2];
            String a3 = GAME_FIELD[1][3];
            String b1 = GAME_FIELD[2][1];
            String b2 = GAME_FIELD[2][2];
            String b3 = GAME_FIELD[2][3];
            String c1 = GAME_FIELD[3][1];
            String c2 = GAME_FIELD[3][2];
            String c3 = GAME_FIELD[3][3];

            if ((a1.equals(this.mark) && a2.equals(this.mark) && a3.equals(this.mark)) ||
                    (b1.equals(this.mark) && b2.equals(this.mark) && b3.equals(this.mark)) ||
                    (c1.equals(this.mark) && c2.equals(this.mark) && c3.equals(this.mark)) ||
                    (a1.equals(this.mark) && b1.equals(this.mark) && c1.equals(this.mark)) ||
                    (a2.equals(this.mark) && b2.equals(this.mark) && c2.equals(this.mark)) ||
                    (a3.equals(this.mark) && b3.equals(this.mark) && c3.equals(this.mark)) ||
                    (a1.equals(this.mark) && b2.equals(this.mark) && c3.equals(this.mark)) ||
                    (c1.equals(this.mark) && b2.equals(this.mark) && a3.equals(this.mark))) {
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
        refreshGamePole(field, GAME_FIELD, addedCord);
        printGamePole();
        while (true) {
            playerFirst.printMessageMoveOfPlayer();
            playerFirst.readConsole();
            printGamePole();
            if (!playerFirst.checkEndGame()) {
                System.out.println("Кряяя");
                break;
            }
            playerSecond.printMessageMoveOfPlayer();
            playerSecond.readConsole();
            printGamePole();
            if (!playerSecond.checkEndGame()) {
                System.out.println("Кряяя");
                break;
            }
        }
    }

    private static void printGamePole() {
        for (int i = 0; i < GAME_FIELD.length; i++) {  //идём по строкам
            for (int j = 0; j < GAME_FIELD[i].length; j++) {//идём по столбцам
                System.out.print(" " + GAME_FIELD[i][j] + " "); //вывод элемента
            }
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






























