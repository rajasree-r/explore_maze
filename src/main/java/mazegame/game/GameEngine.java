/**
 * Maze Game Engine is responsible for creating the Maze game by using GameSpecs.
 * The game will accept the hero movements invoked by the ui and pass them to the maze.
 * The game will also provide the ui with the ClientView,
 * which is all the ui needs to renderize the game status to the player.
 */

package mazegame.game;

import java.util.EmptyStackException;
import java.util.Stack;

import mazegame.core.Maze;
import mazegame.core.Map;
import mazegame.core.End;
import mazegame.core.Hero;
import mazegame.util.Direction;
import mazegame.util.Queue;


public class GameEngine {

    private Maze maze;
    private Stack<Direction> undo;

    public GameEngine(GameSpecs spec) {
        Map map = spec.generateMap();
        End end = spec.generateEnd();
        Hero hero = spec.generateHero();
        this.maze = new Maze(map, end, hero);
        undo = new Stack<Direction>();
    }

    private static Direction reverse(Direction d) {
        switch (d) {
            case NORTH:
                return Direction.SOUTH;
            case SOUTH:
                return Direction.NORTH;
            case WEST:
                return Direction.EAST;
            case EAST:
                return Direction.WEST;
            default:
                throw new UnsupportedOperationException(
                        "Unsupported Direction: " + d);
        }
    }

    public boolean moveHero(Direction dir) {
        boolean ok = maze.moveHero(dir);
        if (ok) {
            undo.push(reverse(dir));
        }
        return ok;
    }

    public ClientView getClientView() {
        return maze.getClientView();
    }

    public Queue<Update> getUpdates() {
        return maze.getUpdates();
    }

    public boolean isGameOver() {
        return maze.isGameOver();
    }

    public boolean undo() {
        Direction d;
        try {
            d = undo.pop();
        } catch (EmptyStackException ex) {
            return false;
        }
        maze.moveHero(d);
        return true;
    }
}
