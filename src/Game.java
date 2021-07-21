import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Game {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public final static String[][] Pole =  {{"/","1","2","3"},{"a"," "," "," "},{"b"," "," "," "},{"c"," "," "," "}};
    public static String[][] GamePole =  {{"/","1","2","3"},{"a"," "," "," "},{"b"," "," "," "},{"c"," "," "," "}};
    public static ArrayList <String> addedCord = new ArrayList<>();
    Player Player_1 = new Player("Player_1", "X", "крестика");
    Player Player_2 = new Player("Player_2", "O", "нолика");

    public static class Player {
        String name;
        String mark;
        String nameMark;
        public Player(String name, String mark, String nameMark) {
            this.name = name;
            this.mark = mark;
            this.nameMark = nameMark;
        }

        public void message(){
            System.out.println("Ходит " + this.name + "\nВведите в консоль координаты для постановки " + this.nameMark);

        }

        public boolean notEqualsaddedCord (String x) {
            boolean B = true;
            for (int i = 0; i < addedCord.size(); i++) {
                if (x.equals(addedCord.get(i))) {
                    System.out.println("Братюнь, эти координаты уже забиты! Разуй глаза и попробуй ещё разок.");
                    B = false;
                }
            }
        return B;
        }

        public void readConsole(){
        String XO = null;
        try {XO = reader.readLine();}
        catch (Exception e) {System.out.println("Что-то пошло не так, извините"); }
            assert XO != null;
            if (XO.matches("a1|a2|a3|b1|b2|b3|c1|c2|c3")){
            System.out.println("ok");
                if (notEqualsaddedCord(XO)){
                    addedCord.add(XO);
                    addCoord(XO);}
                else {readConsole();}
            }
        else {
            System.out.println("Извините, таких координат не существует, попробуйте ещё раз");
        readConsole();}
        }

        public void addCoord (String a){
            if (a.equals("a1")){GamePole[1][1]= this.mark;}
            if (a.equals("a2")){GamePole[1][2]= this.mark;}
            if (a.equals("a3")){GamePole[1][3]= this.mark;}
            if (a.equals("b1")){GamePole[2][1]= this.mark;}
            if (a.equals("b2")){GamePole[2][2]= this.mark;}
            if (a.equals("b3")){GamePole[2][3]= this.mark;}
            if (a.equals("c1")){GamePole[3][1]= this.mark;}
            if (a.equals("c2")){GamePole[3][2]= this.mark;}
            if (a.equals("c3")){GamePole[3][3]= this.mark;}
        }

        public boolean checkEndGame() {
            boolean End = true;
            String a1 = GamePole[1][1];
            String a2 = GamePole[1][2];
            String a3 = GamePole[1][3];
            String b1 = GamePole[2][1];
            String b2 = GamePole[2][2];
            String b3 = GamePole[2][3];
            String c1 = GamePole[3][1];
            String c2 = GamePole[3][2];
            String c3 = GamePole[3][3];

            if ((a1.equals(this.mark) && a2.equals(this.mark) && a3.equals(this.mark))||
            (b1.equals(this.mark) && b2.equals(this.mark) && b3.equals(this.mark))||
            (c1.equals(this.mark) && c2.equals(this.mark) && c3.equals(this.mark)) ||
            (a1.equals(this.mark) && b1.equals(this.mark) && c1.equals(this.mark)) ||
            (a2.equals(this.mark) && b2.equals(this.mark) && c2.equals(this.mark)) ||
            (a3.equals(this.mark) && b3.equals(this.mark) && c3.equals(this.mark)) ||
            (a1.equals(this.mark) && b2.equals(this.mark) && c3.equals(this.mark)) ||
            (c1.equals(this.mark) && b2.equals(this.mark) && a3.equals(this.mark))){
                System.out.println("Поздравляем! " + this.name + " Win!");
                End = false;
            }

            else if (addedCord.size()>8){
                System.out.println("Ничья. Ходов больше нет");
                End = false;}
            return End;
        }
    }

    public void Playing(){
        refreshGamePole(Pole, GamePole, addedCord);
        printGamePole();
        while (true){
        Player_1.message();
        Player_1.readConsole();
        printGamePole();
        if (!Player_1.checkEndGame()){
            System.out.println("Кряяя");
            break;
            }
        Player_2.message();
        Player_2.readConsole();
        printGamePole();
        if (!Player_2.checkEndGame()){
            System.out.println("Кряяя");
            break;
            }
        }
    }

    public static void printGamePole() {
        for (int i = 0; i < GamePole.length; i++) {  //идём по строкам
            for (int j = 0; j < GamePole[i].length; j++) {//идём по столбцам
                System.out.print(" " + GamePole[i][j] + " "); //вывод элемента
            }
            System.out.println();//перенос строки ради визуального сохранения табличной формы
        }
    }

    public static void refreshGamePole(String[][] aSource, String[][] aDestination, ArrayList <String> arr) {
        for (int i = 0; i < aSource.length; i++) {
            System.arraycopy(aSource[i], 0, aDestination[i], 0, aSource[i].length);
        }
        arr.clear();
    }

}






























