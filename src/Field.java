/**
 * Created by YuGa on 12/23/15.
 */
public class Field {
    final int FIELDSIZE = 10;

    char[][] cells = new char[FIELDSIZE][FIELDSIZE];
    Ship ship;

    void init() {
        for (int i = 0; i < FIELDSIZE; i++) {
            for (int j = 0; j < FIELDSIZE; j++) {
                cells[i][j] = '.';
            }
        }
    }

    void setShip(Ship ship) {
        this.ship = ship;
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
        for (int i = 0; i < FIELDSIZE; i++) {

            if (i == 0) {
                for (int k = 0; k < FIELDSIZE; k++) {
                    System.out.print("\t" + k);
                }
                System.out.println();
            }
            System.out.print(i);
            for (int j = 0; j < FIELDSIZE; j++) {
                System.out.print("\t" + cells[i][j]);
            }
            System.out.println();
        }
    }

    boolean isShipPresent(int positionX, int positionY) {
        return cells[positionX][positionY] == 'X';
    }

    boolean isNotGameOver() {
        boolean isPresent = false;
        for (int i = 0; i < FIELDSIZE; i++) {
            for (int j = 0; j < FIELDSIZE; j++) {
                if (cells[i][j] == 'X') isPresent = true;
            }
        }
        return isPresent;
    }
    public boolean canSetShip( Ship ship ) {
        // проверяем, что наш корабль попадает в поле
        if ( ship.positionX < 0 || ship.positionY < 0 || FIELDSIZE <= ship.positionX || FIELDSIZE <= ship.positionY ) return false;
        if ( ship.isVertical && FIELDSIZE <= ship.positionY + ship.size ) return false;
        if ( !ship.isVertical && FIELDSIZE <= ship.positionX + ship.size ) return false;

        // проверяем, что в зоне вокруг корабля никого нет
        // обрезаем зону
        int minX = Math.max( 0, ship.positionX - 1 );
        int minY = Math.max( 0, ship.positionY - 1 );
        int maxX = Math.min( FIELDSIZE - 1, ship.positionX + 1 + (ship.isVertical ? 0 : ship.size) );
        int maxY = Math.min( FIELDSIZE - 1, ship.positionY + 1 + (ship.isVertical ? ship.size : 0) );

        // сама проверка
        for ( int x = minX; x <= maxX; x++ ) {
            for ( int y = minY; y <= maxY; y++ ) {
                if ( isShipPresent( x, y ) ) return false;
            }
        }
        return true;
    }
}

