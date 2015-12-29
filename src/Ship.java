/**
 * Created by YuGa on 12/23/15.
 */
//TODO зробити позіцію зв'язану з сайз, яка буде від нього залежати і не вийде за границі поля
//а кінцева позиція = початкова+розмір

public class Ship {
    int positionY;
    int positionX;
    int size;
    boolean isVertical;
    int health;

    public Ship(int positionX, int positionY, int size, boolean isVertical) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.size = size;
        this.isVertical = isVertical;
        this.health = size;
    }

    public static void generateShipPosition() {

    }

    public int decreaseShipHealth() {
        return --health;
    }


}



