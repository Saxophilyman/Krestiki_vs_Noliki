import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Begining extends Player_1 {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
        if (a.equals("a1")){Main.GamePole[1][1]= "X";}
        if (a.equals("a2")){Main.GamePole[1][2]= "X";}
        if (a.equals("a3")){Main.GamePole[1][3]= "X";}
        if (a.equals("b1")){Main.GamePole[2][1]= "X";}
        if (a.equals("b2")){Main.GamePole[2][2]= "X";}
        if (a.equals("b3")){Main.GamePole[2][3]= "X";}
        if (a.equals("c1")){Main.GamePole[3][1]= "X";}
        if (a.equals("c2")){Main.GamePole[3][2]= "X";}
        if (a.equals("c3")){Main.GamePole[3][3]= "X";}
    return Main.GamePole;
    }

    public static void checkEndGame(){
        String a1 = Main.GamePole[1][1];
        String a2 = Main.GamePole[1][2];
        String a3 = Main.GamePole[1][3];
        String b1 = Main.GamePole[2][1];
        String b2 = Main.GamePole[2][2];
        String b3 = Main.GamePole[2][3];
        String c1 = Main.GamePole[3][1];
        String c2 = Main.GamePole[3][2];
        String c3 = Main.GamePole[3][3];
        String nullPoint = " ";

        if(a1.equals("X") & a2.equals("X") & a3.equals("X")){ System.out.println("Win");}
        if(b1.equals("X") & b2.equals("X") & b3.equals("X")){ System.out.println("Win");}
        if(c1.equals("X") & c2.equals("X") & c3.equals("X")){ System.out.println("Win");}
        if(a1.equals("X") & b1.equals("X") & c1.equals("X")){ System.out.println("Win");}
        if(a2.equals("X") & b2.equals("X") & c2.equals("X")){ System.out.println("Win");}
        if(a3.equals("X") & b3.equals("X") & c3.equals("X")){ System.out.println("Win");}

        if(a1.equals("X") & b2.equals("X") & c3.equals("X")){ System.out.println("Win");}
        if(c1.equals("X") & b2.equals("X") & a3.equals("X")){ System.out.println("Win");}

        if (Main.GamePole.)){ System.out.println("Ничья. Ходов больше нет");}
    }
}






























