/**
 * Created by YuGa on 12/23/15.
 */
public class Main {
    public static void main(String[] args) {
        Field field = new Field();
        Player player = new Player();
        player.getName();
        field.init();
        Field.generateShips();
        gameLoop(field, player);
    }

    private static void gameLoop(Field field, Player player) {
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
