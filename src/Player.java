public class Player {
        private String name;
        private String mark;
        private String nameMark;

        Player(String name, String mark, String nameMark) {
            this.name = name;
            this.mark = mark;
            this.nameMark = nameMark;
        }

    public String getMark() {
        return mark;
    }

    public String getNameMark() {
        return nameMark;
    }

    void printMessageMoveOfPlayer() {
            System.out.println("Ходит " + this.name + "\nВведите в консоль координаты для постановки " + this.nameMark);

        }

    }

