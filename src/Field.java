import java.util.Random;
/**
 * Created by YuGa on 12/23/15.
 */
//TODO Зробити перевірку кількості життів корабля і поміняти  світч
public class Field {
    static final int FIELD_SIZE = 10;
    static char[][] cells = new char[FIELD_SIZE][FIELD_SIZE];
    static final int NUMBER_OF_SHIPS = 10;
    static Ship[] ships = new Ship[NUMBER_OF_SHIPS];

    void init() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                cells[i][j] = '.';
            }
        }
    }

    public static void setShipOnField(Ship ship) {
        if (ship.isVertical) {
            for (int i = 0; i < ship.size; i++) {
                cells[ship.positionX][ship.positionY + i] = 'X';
            }
        } else {
            for (int i = 0; i < ship.size; i++) {
                cells[ship.positionX + i][ship.positionY] = 'X';
            }
        }
    }

    void doShoot(Shoot shoot) {
        //перенести в клас Shoot
        switch (cells[shoot.xCoord][shoot.yCoord]) {
            case '.':
                System.out.println("MISS");
                cells[shoot.xCoord][shoot.yCoord] = '*';
                break;
            case 'X':
                System.out.println("GOAL");
                cells[shoot.xCoord][shoot.yCoord] = '_';
                show();
                break;
            case '*':
                System.out.println("You already shoot here...");
                break;
            default:
                System.out.println("Error!");
        }
    }

    void show() {
        for (int i = 0; i < FIELD_SIZE; i++) {

            if (i == 0) {
                for (int k = 0; k < FIELD_SIZE; k++) {
                    System.out.print("\t" + k);
                }
                System.out.println();
            }
            System.out.print(i);
            for (int j = 0; j < FIELD_SIZE; j++) {
                System.out.print("\t" + cells[i][j]);
            }
            System.out.println();
        }
    }

    static boolean isShipPresent(int positionX, int positionY) {
        return cells[positionX][positionY] == 'X';
    }

    boolean isNotGameOver() {
        boolean isPresent = false;
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (cells[i][j] == 'X') isPresent = true;
            }
        }
        return isPresent;
    }

    public static boolean canSetShip(Ship ship) {
        if (ship.positionX < 0 || ship.positionY < 0 || FIELD_SIZE <= ship.positionX || FIELD_SIZE <= ship.positionY)
            return false;
        if (ship.isVertical && FIELD_SIZE <= ship.positionY + ship.size) return false;
        if (!ship.isVertical && FIELD_SIZE <= ship.positionX + ship.size) return false;
        int minX = Math.max( 0, ship.positionX - 1 );
        int minY = Math.max( 0, ship.positionY - 1 );
        int maxX = Math.min(FIELD_SIZE - 1, ship.positionX + 1 + (ship.isVertical ? 0 : ship.size));
        int maxY = Math.min(FIELD_SIZE - 1, ship.positionY + 1 + (ship.isVertical ? ship.size : 0));
        for ( int x = minX; x <= maxX; x++ ) {
            for ( int y = minY; y <= maxY; y++ ) {
                if ( isShipPresent( x, y ) ) return false;
            }
        }
        return true;
    }

    public static void generateShips() {
        //переписати щоб позиція корабля генерувалась в класі Ship
        Random rand = new Random();
        for (int i = 0; i < NUMBER_OF_SHIPS; i++) {
            boolean isShipPlaced = false;
            do {
                if (i == 0) ships[i] = new Ship(rand.nextInt(10), rand.nextInt(10), 4, rand.nextBoolean());
                if (i > 0 && i <= 2) ships[i] = new Ship(rand.nextInt(10), rand.nextInt(10), 3, rand.nextBoolean());
                if (i > 2 && i <= 5) ships[i] = new Ship(rand.nextInt(10), rand.nextInt(10), 2, rand.nextBoolean());
                if (i > 5) ships[i] = new Ship(rand.nextInt(10), rand.nextInt(10), 1, rand.nextBoolean());
                if (canSetShip(ships[i])) {
                    setShipOnField(ships[i]);
                    isShipPlaced = true;
                }
            } while (!isShipPlaced);
        }
    }
}

