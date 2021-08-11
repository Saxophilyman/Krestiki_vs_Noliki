public class Player extends Game{
        private String name;
        private String mark;
        private String nameMark;

        Player(String name, String mark, String nameMark) {
            this.name = name;
            this.mark = mark;
            this.nameMark = nameMark;
        }

        void printMessageMoveOfPlayer() {
            System.out.println("Ходит " + this.name + "\nВведите в консоль координаты для постановки " + this.nameMark);

        }

    }

