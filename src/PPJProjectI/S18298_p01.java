package PPJProjectI;

public class S18298_p01 {
    private enum FieldType {
        Empty,
        Mouse,
        Trail,
        Cheese
    }

    private static int fieldTypeToInteger(FieldType fieldType) {
        switch (fieldType) {
            case Mouse:
                return 2;
            case Cheese:
                return 1;
            case Trail:
                return 3;
            case Empty:
            default:
                return 0;
        }
    }

    public enum MoveType {
        Random,
        Nearest,
        Direct
    }

    public static void main(String[] args) {
        presentMove(MoveType.Random);
        //presentMove(MoveType.Nearest);
        //presentMove(MoveType.Direct);
    }

    private static void presentMove(MoveType moveType) {
        int[][] map = generateEmptyMap(23, 23);
        fillWithCheese(map, 10.0);
        addElementToMapInRandomPlace(map, FieldType.Mouse);

        boolean isCheeseAvailable;
        do {
            moveMouse(map, moveType);
            drawMap(map);
            isCheeseAvailable = getIsCheeseAvailable(map);
        } while (isCheeseAvailable);
    }

    private static int[][] generateEmptyMap(int sizeRow, int sizeColumn) {
        int[][] map = new int[sizeRow][sizeColumn];

        int currentRowLength = map.length;
        for (int currentRowIndex = 0; currentRowIndex < currentRowLength; currentRowIndex++) {

            int currentColumnLength = map[currentRowIndex].length;
            for (int currentColumnIndex = 0; currentColumnIndex < currentColumnLength; currentColumnIndex++) {
                map[currentRowIndex][currentColumnIndex] = fieldTypeToInteger(FieldType.Empty);
            }
        }

        return map;
    }


    private static void fillWithCheese(int[][] map, double percentOfCheese) {
        int totalNumberOfFields = calculateNumberOfFields(map);
        int numberOfCheeseOnMap = (int) (totalNumberOfFields * (percentOfCheese / 100));

        while (numberOfCheeseOnMap > 0) {
            addElementToMapInRandomPlace(map, FieldType.Cheese);

            numberOfCheeseOnMap--;
        }
    }

    private static int calculateNumberOfFields(int[][] map) {
        return map.length * map[0].length;
    }

    private static void addElementToMapInRandomPlace(int[][] map, FieldType element) {
        while (true) {
            int row = pickRandomRow(map);
            int column = pickRandomColumn(map);

            if (map[row][column] == fieldTypeToInteger(FieldType.Empty)) {
                map[row][column] = fieldTypeToInteger(element);
                return;
            }

            // TODO: Check if we can add element at all
        }
    }

    private static int pickRandomRow(int[][] map) {
        return (int) (Math.random() * map.length);
    }

    private static int pickRandomColumn(int[][] map) {
        return (int) (Math.random() * map[0].length);
    }

    private static void moveMouse(int[][] map, MoveType moveType) {
        switch (moveType) {
            case Direct:
                moveMouseDirect(map);
                break;
            case Nearest:
                moveMouseNearest(map);
                break;
            case Random:
            default:
                moveMouseRandom(map);
                break;
        }
    }

    private static void moveMouseNearest(int[][] map) {
        // Find current mouse position
        int oldMouseXPosition = findMouseX(map);
        int oldMouseYPosition = findMouseY(map);

        // Check mouse position +1 radius if there is a cheese
        // if found, go to it
        // check mouse position +2 radius if there is a cheese
        // if found, go to it
        // check mouse position +3 radius if there is a cheese
        // if found, go to it
        // check mouse position +4 radius if there is a cheese
        // if found, go to it

//        1 1 1
//        1 2 1
//        1 1 1
//
//        1 1 1 1 1
//        1       1
//        1   2   1
//        1       1
//        1 1 1 1 1


    }

    private static void moveMouseDirect(int[][] map) {
    }

    private static void moveMouseRandom(int[][] map) {
        // Find current mouse position
        int oldMouseXPosition = findMouseX(map);
        int oldMouseYPosition = findMouseY(map);

        // Initialize values
        int newMouseXPosition;
        int newMouseYPosition;
        do {
            // Move to be made, where 0 is "no move", -1 negative direction move and +1 positive direction move
            int moveX = 0;
            int moveY = 0;

            // Search for a move which is NOT 0,0 (Which is an actual 'move')
            while (moveX == 0 && moveY == 0) {
                // Find move from 0..2, then shift to -1..1
                moveX = ((int) (Math.random() * 3)) - 1;
                moveY = ((int) (Math.random() * 3)) - 1;
            }

            // Set possible new position for mouse
            newMouseXPosition = oldMouseXPosition + moveX;
            newMouseYPosition = oldMouseYPosition + moveY;
            // Verify if the new mouse position is within bounds; if not - create new one
        }
        while ((newMouseXPosition < 0 || newMouseXPosition >= map.length) || (newMouseYPosition < 0 || newMouseYPosition >= map[0].length));

        // Set new mouse position
        map[newMouseXPosition][newMouseYPosition] = fieldTypeToInteger(FieldType.Mouse);
        // And set trail
        map[oldMouseXPosition][oldMouseYPosition] = fieldTypeToInteger(FieldType.Trail);
    }

    private static int findMouseX(int[][] map) {
        int currentRowLength = map.length;
        for (int currentRowIndex = 0; currentRowIndex < currentRowLength; currentRowIndex++) {

            int currentColumnLength = map[currentRowIndex].length;
            for (int currentColumnIndex = 0; currentColumnIndex < currentColumnLength; currentColumnIndex++) {
                if (map[currentRowIndex][currentColumnIndex] == fieldTypeToInteger(FieldType.Mouse)) {
                    return currentRowIndex;
                }
            }
        }

        // Should never happen
        return -1;
    }

    private static int findMouseY(int[][] map) {
        int currentRowLength = map.length;
        for (int currentRowIndex = 0; currentRowIndex < currentRowLength; currentRowIndex++) {

            int currentColumnLength = map[currentRowIndex].length;
            for (int currentColumnIndex = 0; currentColumnIndex < currentColumnLength; currentColumnIndex++) {
                if (map[currentRowIndex][currentColumnIndex] == fieldTypeToInteger(FieldType.Mouse)) {
                    return currentColumnIndex;
                }
            }
        }

        // Should never happen
        return -1;
    }

    private static void drawMap(int[][] map) {
        drawLine(map.length);

        int currentRowLength = map.length;
        for (int currentRowIndex = 0; currentRowIndex < currentRowLength; currentRowIndex++) {

            int currentColumnLength = map[currentRowIndex].length;
            for (int currentColumnIndex = 0; currentColumnIndex < currentColumnLength; currentColumnIndex++) {
                int element = map[currentRowIndex][currentColumnIndex];
                if (element == fieldTypeToInteger(FieldType.Mouse)) {
                    System.out.print(element);
                } else if (element == fieldTypeToInteger(FieldType.Trail)) {
                    System.out.print(element);
                } else if (element == fieldTypeToInteger(FieldType.Cheese)) {
                    System.out.print(element);
                } else {
                    System.out.print(element);
                }
            }

            System.out.println();
        }

        drawLine(map.length);
    }

    private static void drawLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("---");
        }
        System.out.println();
    }

    private static boolean getIsCheeseAvailable(int[][] map) {
        int currentRowLength = map.length;
        for (int currentRowIndex = 0; currentRowIndex < currentRowLength; currentRowIndex++) {

            int currentColumnLength = map[currentRowIndex].length;
            for (int currentColumnIndex = 0; currentColumnIndex < currentColumnLength; currentColumnIndex++) {
                if (map[currentRowIndex][currentColumnIndex] == fieldTypeToInteger(FieldType.Cheese)) {
                    return true;
                }
            }
        }

        return false;
    }
}


