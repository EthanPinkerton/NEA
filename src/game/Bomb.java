package game;

public class Bomb {
    public static void use(Player player,Maze maze){
        switch(player.getDirection()){
            case 'w':
                maze.removeTile(player.getX(), player.getY()-1);
                break;
            case 's':
                maze.removeTile(player.getX(), player.getY()+1);
                break;
            case 'a':
                maze.removeTile(player.getX()-1, player.getY());
                break;
            case 'd':
                maze.removeTile(player.getX()+1, player.getY());
                break;
        }
    }
}
