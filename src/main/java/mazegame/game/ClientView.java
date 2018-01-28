package mazegame.game;

import mazegame.util.Array;

public class ClientView {

    private Icon[][] icons;
    private boolean  isGameOver;

    public ClientView(Icon[][] icons,
            boolean isGameOver) {
        if (icons == null) {
            throw new NullPointerException("icons");
        }
        if (Array.hasNull(icons)) {
            throw new NullPointerException("map icons has nulls");
        }
        if (! Array.isRect(icons)) {
            throw new IllegalArgumentException(
                    "map is not rectangular");
        }
        this.icons = icons;
        this.isGameOver = isGameOver;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public Icon[][] getTopView() {
        return icons;
    }
}
