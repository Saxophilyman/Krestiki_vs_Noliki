import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Begining {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void Begin(){
        System.out.println("Ведите в консоль \"да\" или \"нет\"");
    String s = null;
        try{
        s = reader.readLine();}
        catch (Exception e){
        System.out.println("Что-то пошло не так, извините");
    }
        if (!s.equals("да")&&!s.equals("нет")) {
        System.out.println("Упс, кто-то ввёл что-то не так"
            +"\nПопробуйте ещё раз =^^");
            Begin();
        }

        if (s.equals("да")){
        System.out.println("Отлично! Ходит \"Первый игрок\"");}
        if (s.equals("нет")){
        System.out.println("Ну нет так нет");
            System.exit(0);
        }
    }

    public class Game(){


    }

}
