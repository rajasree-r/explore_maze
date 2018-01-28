package mazegame.game;

import mazegame.core.*;

public class GameSpecsDepthFirst implements GameSpecs {

    private Map map;
    private End end;
    private Hero hero;

    public GameSpecsDepthFirst(int rows, int cols) {
        if (rows < 1) {
            throw new IllegalArgumentException("rows < 1");
        }
        if (cols < 1) {
            throw new IllegalArgumentException("columns < 1");
        }
        if (rows % 2 == 0) {
            throw new IllegalArgumentException(
                    "Random maps require an odd number of rows");
        }
        if (cols % 2 == 0) {
            throw new IllegalArgumentException(
                    "Random maps require an odd number of columns");
        }
        map = new DepthFirstAlgorithm(rows, cols).generateMap();
        hero = new Hero(new Place(0, 0, map));
        end = new End(new Place(rows - 1, cols - 1, map));
    }

    public Map generateMap() {
        return map;
    }

    public End generateEnd() {
        return end;
    }

    public Hero generateHero() {
        return hero;
    }

}
