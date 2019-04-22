package PPJProjectI;

public class S18298_p01 {
    public static void main(String[] args) {
        int map[][] = new int[23][23];
        fillmapwith0(map);
        showtab(map);
        cheesedistribute(map);
        showtab(map);
        mouseplacement(map);
        showtab(map);
        cheesecounter(map);
        System.out.println(cheesecounter(map));
        mouselocation(map);



    }

    private static void fillmapwith0(int map[][]) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = 0;
            }
        }
    }


    private static void showtab(int map[][]) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println("");

        }
        System.out.println(" ");


    }


    private static void cheesedistribute(int map[][]) {

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                int distributionmodifier = (int) (Math.random() * 5);
                int randomI = (int) (Math.random() * 23);
                int randomII = (int) (Math.random() * 23);
                if (distributionmodifier == 1) {
                    map[randomI][randomII] = 1;
                }
            }

        }


    }

    private static int cheesecounter(int map[][]) {
        int cheesecount = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 1) {
                    cheesecount++;
                }
            }

        }
        return cheesecount;
    }


    private static void mouseplacement(int map[][]) {
        int randomI = (int) (Math.random() * 23);
        int randomII = (int) (Math.random() * 23);
        map[randomI][randomII] = 2;
    }

    public static  void mouselocation(int map[][]) {
        int mouseaxisX = 0;
        int mouseasixY = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 2) {
                    mouseaxisX = i;
                    mouseasixY = j;
                }
            }

        }
        System.out.println("mysz znajduje sie w wierszu " + (mouseaxisX + 1) + " i kolumnie " + (mouseasixY + 1));
    }


    public static boolean mousemoveup (int map[][], int mouseaxisX, int mouseaxisY) {

        if (mouseaxisX == 0) {
            return false;
        } else {
            map[mouseaxisX][mouseaxisY] = 3; // tam gdzie znajduje się  myszy zmieniamy na 3
            map[mouseaxisX - 1][mouseaxisY] = 2; // stawiamy mysz na nowe pole
            return true;
        }
    }
}

