package mazegame.core;

import mazegame.game.Icon;

public class Wall implements Tile {

    public boolean isWalkable() {
        return false;
    }

    public Icon getIcon() {
        return Icon.WALL;
    }
}
