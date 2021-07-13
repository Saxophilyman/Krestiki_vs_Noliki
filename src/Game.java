import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Game {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static String[][] GamePole =  {{" ","1","2","3"},{"a"," "," "," "},{"b"," "," "," "},{"c"," "," "," "}};
    Player Player_1 = new Player("Player_1", "X", "крестика");
    Player Player_2 = new Player("Player_2", "O", "нолика");

    public void Begin(){
        System.out.println("Ведите в консоль \"да\" или \"нет\"");
        String s = null;
        try {s = reader.readLine();}
        catch (Exception e) {System.out.println("Что-то пошло не так, извините"); }

        if (!s.equals("да")&&!s.equals("нет")) {
            System.out.println("Упс, кто-то ввёл что-то не так"
            +"\nПопробуйте ещё раз =^^");
            Begin();  }

        if (s.equals("да")) {System.out.println("Отлично! Ходит \"Первый игрок\"");}

        if (s.equals("нет")) {System.out.println("Ну нет так нет");
            System.exit(0);}
    }

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


        public void readConsole(){
        String XO = null;
        try {XO = reader.readLine();}
        catch (Exception e) {System.out.println("Что-то пошло не так, извините"); }

        //проверка по системе координат
       // if (consoleCheck.contains(XO)){ }
        if (XO.matches("a1|a2|a3|b1|b2|b3|c1|c2|c3")){
            System.out.println("ok");
            addCoord(XO);
        }
        else {
            System.out.println("Извините, таких координат не существует, попробуйте ещё раз");
        readConsole();
        }
    }

        public String[][] addCoord (String a){
            if (a.equals("a1")){GamePole[1][1]= this.mark;}
            if (a.equals("a2")){GamePole[1][2]= this.mark;}
            if (a.equals("a3")){GamePole[1][3]= this.mark;}
            if (a.equals("b1")){GamePole[2][1]= this.mark;}
            if (a.equals("b2")){GamePole[2][2]= this.mark;}
            if (a.equals("b3")){GamePole[2][3]= this.mark;}
            if (a.equals("c1")){GamePole[3][1]= this.mark;}
            if (a.equals("c2")){GamePole[3][2]= this.mark;}
            if (a.equals("c3")){GamePole[3][3]= this.mark;}
            return GamePole;
        }

    }

    public void Playing(){
        // сделать цикл через проверку checkEndGame
        // доделать checkEndGame
        // учесть в readConsole повтор координат
        // может быть вывести координаты отдельно
        while (true){
            printGamePole();
        Player_1.message();
        Player_1.readConsole();
        printGamePole();
        if (checkEndGame()){System.out.println("Player_1 Win!!!");}
        Player_2.message();
        Player_2.readConsole();
        printGamePole();
        if (checkEndGame()){System.out.println("Player_2 Win!!!");}

    }}


//    public void readConsole(){
//        String XO = null;
//        try {XO = reader.readLine();}
//        catch (Exception e) {System.out.println("Что-то пошло не так, извините"); }
//
//        //проверка по системе координат
//       // if (consoleCheck.contains(XO)){ }
//        if (XO.matches("a1|a2|a3|b1|b2|b3|c1|c2|c3")){
//            System.out.println("ok");
//            addCoord();
//        }
//        else {
//            System.out.println("Извините, таких координат не существует, попробуйте ещё раз");
//        readConsole();
//        }
//    }

//    public void Coord(){
//        String a1 = Main.GamePole[1][1];
//        String a2 = Main.GamePole[1][2];
//        String a3 = Main.GamePole[1][3];
//        String b1 = Main.GamePole[2][1];
//        String b2 = Main.GamePole[2][2];
//        String b3 = Main.GamePole[2][3];
//        String c1 = Main.GamePole[3][1];
//        String c2 = Main.GamePole[3][2];
//        String c3 = Main.GamePole[3][3];
//
//    }


    //мой спагетти код
    public static String[][] addCoord (String a){
        if (a.equals("a1")){GamePole[1][1]= "X";}
        if (a.equals("a2")){GamePole[1][2]= "X";}
        if (a.equals("a3")){GamePole[1][3]= "X";}
        if (a.equals("b1")){GamePole[2][1]= "X";}
        if (a.equals("b2")){GamePole[2][2]= "X";}
        if (a.equals("b3")){GamePole[2][3]= "X";}
        if (a.equals("c1")){GamePole[3][1]= "X";}
        if (a.equals("c2")){GamePole[3][2]= "X";}
        if (a.equals("c3")){GamePole[3][3]= "X";}
    return GamePole;
    }

    public static boolean checkEndGame() {
        boolean End = false;
        String a1 = GamePole[1][1];
        String a2 = GamePole[1][2];
        String a3 = GamePole[1][3];
        String b1 = GamePole[2][1];
        String b2 = GamePole[2][2];
        String b3 = GamePole[2][3];
        String c1 = GamePole[3][1];
        String c2 = GamePole[3][2];
        String c3 = GamePole[3][3];
        String nullPoint = " ";

        if (a1.equals("X") & a2.equals("X") & a3.equals("X")) {        }
        if (b1.equals("X") & b2.equals("X") & b3.equals("X")) {        }
        if (c1.equals("X") & c2.equals("X") & c3.equals("X")) {        }
        if (a1.equals("X") & b1.equals("X") & c1.equals("X")) {        }
        if (a2.equals("X") & b2.equals("X") & c2.equals("X")) {        }
        if (a3.equals("X") & b3.equals("X") & c3.equals("X")) {        }
        if (a1.equals("X") & b2.equals("X") & c3.equals("X")) {        }
        if (c1.equals("X") & b2.equals("X") & a3.equals("X")) {        }

        return false;// if (Main.GamePole.)){ System.out.println("Ничья. Ходов больше нет");}
    }
        public static void printGamePole() {
            for (int i = 0; i < GamePole.length; i++) {  //идём по строкам
                for (int j = 0; j < GamePole[i].length; j++) {//идём по столбцам
                    System.out.print(" " + GamePole[i][j] + " "); //вывод элемента
                }
                System.out.println();//перенос строки ради визуального сохранения табличной формы
            }
        }




}






























