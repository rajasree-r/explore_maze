package mazegame;

import mazegame.game.GameSpecs;
import mazegame.game.GameSpecsDepthFirst;
import mazegame.ui.MazeUI;

public class MazeLauncher {

    private static final int DEFAULT_ROWS = 27;
    private static final int DEFAULT_COLUMNS = 37;

    public static void main(String args[]) {

        int rows = -1;
        int columns = -1;

        if(args.length == 2) {
            try {
                rows = getValue(args[0]);
                columns = getValue(args[1]);
            }catch (NumberFormatException e){
                System.out.println(e.toString());
            }
        }

        if(rows == -1 || columns == -1){
            rows = DEFAULT_ROWS;
            columns = DEFAULT_COLUMNS;
        }

        GameSpecs gameSpecs = new GameSpecsDepthFirst(rows, columns);
        new Thread(new MazeUI(gameSpecs)).start();
    }

    private static int getValue(String argument) throws NumberFormatException{
        if(argument == null){
            return -1;
        }

        int value = Integer.valueOf(argument);
        if(value % 2 == 0){
            throw new IllegalArgumentException("Row & Column should be Odd number");
        }
        return value;
    }
}
