import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        System.out.println("Приветствую вас на экспериментальной площадке \"Будущий супер программист\"!." +
                        "\nХотите ли сыграть в игру \"Крестики-нолики\"?");
        Begin();
    }

    public static void gameGoOn(){
        Game game = new Game();
        game.Playing();
        System.out.println( "Хотите сыграть ещё раз?");
        Begin();
    }

    public static void Begin(){
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ведите в консоль \"да\" или \"нет\"");
        String s = null;
        try {s = r.readLine();}
        catch (Exception e) {System.out.println("Что-то пошло не так, извините"); }

        assert s != null;
        if (!s.equals("да")&&!s.equals("нет")) {
            System.out.println("Упс, кто-то ввёл что-то не так"
                    +"\nПопробуйте ещё раз =^^");
            Begin();  }

        if (s.equals("да")) {System.out.println("Отлично! Ходит \"Первый игрок\"");
            gameGoOn();
        }

        if (s.equals("нет")) {System.out.println("Ну нет так нет");
            System.exit(0);}
    }
}




    /**


как можно сделать так, чтобы не писать ридер в двух местах
Можно ли создавать многомерные динамические массивы в Java???
ArrayList<String> Pole = new ArrayList<>();

***************************************************************************************************************************************************************
Integer [][] GamePole = {{0,1,2,3},{0,1,2,3},{0,1,2,3},{0,1,2,3}};
{"_","_","_","_","_","_","_","_"}  "\u27FC"
{"-","-","-","-","-","-","-","-"}  "\uD834\uDD16"
/*String[][] GamePole =  {{" "," |","1","|","2","|","3","|"},{"\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16"},
                        {"a"," |"," ","|"," ","|"," ","|"},{"\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16"},
                        {"b"," |"," ","|"," ","|"," ","|"},{"\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16",},
                        {"c"," |"," ","|"," ","|"," ","|"},{"\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16"}};
Если брать за основу строки(чтобы не заморачиваться с символами), можно играться с Unicode и настроить поле по "красоте". Из всего что искал - вариант выше самый удобоваримый
Логика возможно усложнится, но суть останется прежней. в целом для большей простоты оставлю без линий


**/
