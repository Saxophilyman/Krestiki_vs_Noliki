import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Begining {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    //String [] consoleCheck = {"a1", "a2", "a3", "b1", "b2", "b3", "c1", "c2", "c3"};
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

    public void readConsole(){
        String XO = null;
        try{
            XO = reader.readLine();}
        catch (Exception e){
            System.out.println("Что-то пошло не так, извините");
        }

        //проверка по системе координат
       // if (consoleCheck.contains(XO)){ }
        if (XO.matches("a1|a2|a3|b1|b2|b3|c1|c2|c3")){
            System.out.println("ok");}
        else {
            System.out.println("sorry");}


    }

}
