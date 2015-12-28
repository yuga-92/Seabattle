import java.util.Random;

/**
 * Created by YuGa on 12/23/15.
 */
public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        Field field = new Field();
        Player player = new Player();
        player.getName();
        field.init();
        final int NUMBEROFSHIPS =10;
        Ship [] ships = new Ship[NUMBEROFSHIPS];
        for (int i = 0; i < NUMBEROFSHIPS; i++) {
            boolean isShipSettedUp = false;
            do {
                if (i == 0) ships[i] = new Ship(rand.nextInt(10), rand.nextInt(10), 4, rand.nextBoolean());
                if (i > 0 && i <=2) ships[i] = new Ship(rand.nextInt(10), rand.nextInt(10), 3, rand.nextBoolean());
                if (i > 2 && i <= 5) ships[i] = new Ship(rand.nextInt(10), rand.nextInt(10), 2, rand.nextBoolean());
                if (i > 5) ships[i] = new Ship(rand.nextInt(10), rand.nextInt(10), 1, rand.nextBoolean());
                if (field.canSetShip(ships[i])) {
                    field.setShip(ships[i]);
                    isShipSettedUp =true;
               }
            } while(!isShipSettedUp);
        }
        System.out.println("Game start!");
        do {
            field.show();
            System.out.println("Where to shoot?");
            System.out.println("Enter column: ");
            int shootX = player.getShoot();
            System.out.println("Enter row: ");
            int shootY = player.getShoot();
            Shoot shoot = new Shoot(shootX,shootY);
            field.doShoot(shoot);
        } while (field.isNotGameOver());
    }
}
