package mazegame.core;

import mazegame.util.Direction;
import mazegame.util.grid.Grid;
import mazegame.util.grid.Vertex;

abstract class GridAlgorithm extends MapGeneratorAlgorithm {

    protected abstract void algorithm();

    private static final Space space = new Space();

    protected Grid grid;

    GridAlgorithm(int mapRows, int mapCols) {
        super(mapRows, mapCols);
        algorithm();
    }

    static protected int mapDimToGridDim(int m) {
        return roundUpIntegerDivision(m, 2);
    }

    static protected int GridIndexToMapIndex(int g) {
        return (2*g);
    }

    static private int roundUpIntegerDivision(int num, int div) {
        return (num + div - 1) / div;
    }

    public Map generateMap() {
        if (grid == null) {
            throw new NullPointerException("grid");
        }
        Tile[][] tiles = createCrosses();
        tiles = turnLinksIntoSpaces(tiles);
        return new Map(tiles);
    }

    private Tile[][] createCrosses() {
        Tile[][] tiles = new Tile[mapRows][mapCols];
        Space s = new Space();
        Wall w = new Wall();
        for (int r=0; r<mapRows; r++) {
            for (int c=0; c<mapCols; c++) {
                if (r % 2 == 0 && c % 2 == 0) {
                    tiles[r][c] = s;
                } else {
                    tiles[r][c] = w;
                }
            }
        }
        return tiles;
    }

    private Tile[][] turnLinksIntoSpaces(Tile[][] tiles) {
        int gridRows = grid.rows();
        int gridCols = grid.cols();
        Vertex current;

        // for all rows but the last...
        for (int r=0; r<gridRows-1; r++) {
            // for all columns but the last...
            for (int c=0; c<gridCols-1; c++) {
                // set south and east tiles
                current = grid.getVertex(r, c);
                carveSpaceFromEdge(tiles, current, Direction.SOUTH);
                carveSpaceFromEdge(tiles, current, Direction.EAST);
            }
            // for the last column set only the south tile
            current = grid.getVertex(r, gridCols-1);
            carveSpaceFromEdge(tiles, current, Direction.SOUTH);
        }

        // for the last row, set only the east tiles
        for (int c=0; c<gridCols-1; c++) {
            // set south and east tiles
            current = grid.getVertex(gridRows-1, c);
            carveSpaceFromEdge(tiles, current, Direction.EAST);
        }
        return tiles;
    }

    private void carveSpaceFromEdge(
            Tile[][] tiles, Vertex vertex, Direction dir) {
        int gridRow = vertex.row();
        int gridCol = vertex.col();
        int mapRow = GridIndexToMapIndex(gridRow);
        int mapCol = GridIndexToMapIndex(gridCol);
        if (vertex.isConnected(dir)) {
            switch (dir) {
                case NORTH:
                    tiles[mapRow-1][mapCol] = space;
                    break;
                case SOUTH:
                    tiles[mapRow+1][mapCol] = space;
                    break;
                case EAST:
                    tiles[mapRow][mapCol+1] = space;
                    break;
                case WEST:
                    tiles[mapRow][mapCol-1] = space;
                    break;
                default:
                    throw new UnsupportedOperationException(
                            dir.toString());
            }
        }
    }
}
