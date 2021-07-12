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
            System.out.println("ok");
            char[] strToArray = XO.toCharArray();
//            System.out.println(strToArray[0]);
//            System.out.println(strToArray[1]);

             GamePoleCoord(strToArray[0],strToArray[1]);
        }
        else {
            System.out.println("Извините, таких координат не существует, попробуйте ещё раз");
        readConsole();
        }
    }


    //мой спагетти код
    public static String[][] addCoord (String a){
        if (a.equals("a1")){Main.GamePole[1][1]= "X";        }
    return Main.GamePole;
    }

    //GamePole [1][1] = X;
    public static String GamePoleCoord(int a, int b) {
        String letters = "abc";
        String numbers = "123";
        /*if ((a > 7)|| (b>7)) return null; //если номер за пределами доски, возвращаем значение по умолчанию - null
        else */return (Character.toString(letters.charAt(a)) + numbers.charAt(b));
        /*charAt - метод, с помощью которого мы извлекаем из строки элемент под переданным номером,
         здесь - под номерами a и b. Character.toString - метод, который переводит полученный символ в строку*/
    }

}
