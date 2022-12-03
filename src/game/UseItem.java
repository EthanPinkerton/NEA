package game;

public class UseItem {
    public static void bomb(Player player, Maze maze) {
        switch (player.getDirection()) {
            case 'w':
                maze.removeTile(player.getX(), player.getY() - 1);
                break;
            case 's':
                maze.removeTile(player.getX(), player.getY() + 1);
                break;
            case 'a':
                maze.removeTile(player.getX() - 1, player.getY());
                break;
            case 'd':
                maze.removeTile(player.getX() + 1, player.getY());
                break;
        }
    }

    public static void teleport(Player player, Maze maze) {
        switch (player.getDirection()) {
            case 'w':
                if (!maze.getTile(player.getX(), player.getY() - 2).equals("x")) {
                    player.addY(-2);
                }
                break;
            case 's':
                if (!maze.getTile(player.getX(), player.getY() + 2).equals("x")) {
                    player.addY(2);
                }
                break;
            case 'a':
                if (!maze.getTile(player.getX() - 2, player.getY()).equals("x")) {
                    player.addX(-2);
                }
                break;
            case 'd':
                if (!maze.getTile(player.getX() + 2, player.getY()).equals("x")) {
                    player.addX(2);
                }
                break;
        }
    }

    public static void speed(Player player) {
        player.setSpeed(0.1);
    }

    public static void sReturn(Player player) {
        player.setSpeed(0.05);
    }

    public static void projectile(Player player) {
        player.setPDelay(5);
    }

    public static void pReturn(Player player) {
        player.setPDelay(10);
    }
}
