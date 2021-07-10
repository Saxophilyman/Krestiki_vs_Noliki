import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static String[][] GamePole =  {{" ","1","2","3"},{"a"," "," "," "},{"b"," "," "," "},{"c"," "," "," "}};

    public static void main(String[] args) {
	// надеюсь смысловое значение комментария всё ещё можно писать на русском
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//как можно сделать так, чтобы не писать ридер в двух местах
        System.out.println("Приветствую вас на экспериментальной площадке \"Будущий супер программист\"!." +
                        "\nХотите ли сыграть в игру \"Крестики-нолики\"?");

        //Можно ли создавать многомерные динамические массивы в Java???
        //ArrayList<String> Pole = new ArrayList<>();

        //Может большую часть программирования составляет не написание кода, а построение её логики


        Begining go = new Begining();
        go.Begin();


        //Integer [][] GamePole = {{0,1,2,3},{0,1,2,3},{0,1,2,3},{0,1,2,3}};
//{"_","_","_","_","_","_","_","_"}  "\u27FC"
//{"-","-","-","-","-","-","-","-"}  "\uD834\uDD16"
/*String[][] GamePole =  {{" "," |","1","|","2","|","3","|"},{"\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16"},
                        {"a"," |"," ","|"," ","|"," ","|"},{"\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16"},
                        {"b"," |"," ","|"," ","|"," ","|"},{"\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16",},
                        {"c"," |"," ","|"," ","|"," ","|"},{"\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16","\uD834\uDD16"}};*/
//Если брать за основу строки(чтобы не заморачиваться с символами), можно играться с Unicode и настроить поле по "красоте". Из всего что искал - вариант выше самый удобоваримый
//Логика возможно усложнится, но суть останется прежней. в целом для большей простоты оставлю без линий


        //может отдельно отвести приветственное слово в начале игры

        //нужен отдельный метод отрисовки поля после каждого введённого крестика-нолика
        //считывание и обработка введённых символов через консоль
        //проверка на завершение игры while (заполнено все поле/построен ряд из 3 одинаковых символов)
        printGamePole();
        System.out.println("Введите в консоль координаты для постановки крестика");
        //System.out.println(Arrays.deepToString(GamePole));
        try{
            String x = reader.readLine();}
        catch (Exception e){
            System.out.println("Что-то пошло не так, извините");
        }



    }

    public void superMessage(){
        System.out.println("Поздравляем ! Вы выиграли эту игру! Хотите сыграть ещё раз?");
    }
    public static void printGamePole(){
        for (int i = 0; i < GamePole.length; i++) {  //идём по строкам
            for (int j = 0; j < GamePole[i].length; j++) {//идём по столбцам
                System.out.print(" " + GamePole[i][j] + " "); //вывод элемента
            }
            System.out.println();//перенос строки ради визуального сохранения табличной формы
        }

    }
}
