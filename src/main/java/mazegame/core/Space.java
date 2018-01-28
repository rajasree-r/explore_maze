package mazegame.core;

import mazegame.game.Icon;

public class Space implements Tile {

    public boolean isWalkable() {
        return true;
    }

    public Icon getIcon() {
        return Icon.EMPTY;
    }
}
