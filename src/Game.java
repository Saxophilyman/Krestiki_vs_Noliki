import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Game {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final String[][] GAME_FIELD = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};
    private String[][] workField = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};
    private String[][] decorField = {{"/", "1", "2", "3"}, {"a"}, {"b"}, {"c"}};
    private ArrayList<String> addedCord = new ArrayList<>();
    private Player playerFirst;
    private Player playerSecond;

    Game (Player first, Player second) {
        playerFirst = first;
        playerSecond = second;
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

    public void readConsole(Player player) {
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
                addCoord(addXO, player);
            } else {
                readConsole(player);
            }
        } else {
            System.out.println("Извините, таких координат не существует, попробуйте ещё раз");
            readConsole(player);
        }
    }
//разбить посимвольно и присвоить каждому символу свою координату

    private void addCoord(String a, Player player) {
        char [] firstAndSecondCoordReadConsole = a.toCharArray();
        int firstCoordWorkFieldVertical = firstAndSecondCoordReadConsole[0];
        int secondCoordWorkFieldHorizontal = firstAndSecondCoordReadConsole[1];
        if (firstCoordWorkFieldVertical == 'a'){
            firstCoordWorkFieldVertical = 0;
        }
        if (firstCoordWorkFieldVertical == 'b'){
            firstCoordWorkFieldVertical = 1;
        }
        if (firstCoordWorkFieldVertical == 'c'){
            firstCoordWorkFieldVertical = 2;
        }
        if (secondCoordWorkFieldHorizontal == '1'){
            secondCoordWorkFieldHorizontal = 0;
        }
        if (secondCoordWorkFieldHorizontal == '2'){
            secondCoordWorkFieldHorizontal = 1;
        }
        if (secondCoordWorkFieldHorizontal == '3'){
            secondCoordWorkFieldHorizontal = 2;
        }

        workField[firstCoordWorkFieldVertical][secondCoordWorkFieldHorizontal] = player.getMark();

    }

    boolean checkEndGame(Player player) {
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
        if ((a1.equals(player.getMark()) && a2.equals(player.getMark()) && a3.equals(player.getMark())) ||
                (b1.equals(player.getMark()) && b2.equals(player.getMark()) && b3.equals(player.getMark())) ||
                (c1.equals(player.getMark()) && c2.equals(player.getMark()) && c3.equals(player.getMark())) ||
                (a1.equals(player.getMark()) && b1.equals(player.getMark()) && c1.equals(player.getMark())) ||
                (a2.equals(player.getMark()) && b2.equals(player.getMark()) && c2.equals(player.getMark())) ||
                (a3.equals(player.getMark()) && b3.equals(player.getMark()) && c3.equals(player.getMark())) ||
                (a1.equals(player.getMark()) && b2.equals(player.getMark()) && c3.equals(player.getMark())) ||
                (c1.equals(player.getMark()) && b2.equals(player.getMark()) && a3.equals(player.getMark())))
        {
            System.out.println("Поздравляем! " + player.getNameMark() + " Win!");
            gameNotEnd = false;
        } else if (addedCord.size() > 8) {
            System.out.println("Ничья. Ходов больше нет");
            gameNotEnd = false;
        }
        return gameNotEnd;
    }


    public void startGame() {
        refreshGamePole(GAME_FIELD, workField, addedCord);
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


    public void printGameField() {
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
























