package mazegame.core;

abstract class MapGeneratorAlgorithm implements MapGenerator {

    protected int mapRows;
    protected int mapCols;

    MapGeneratorAlgorithm(int mapRows, int mapCols) {
        if (mapRows < 1) {
            throw new IllegalArgumentException("mapRows < 1");
        }
        if (mapCols < 1) {
            throw new IllegalArgumentException("mapCols < 1");
        }
        this.mapRows = mapRows;
        this.mapCols = mapCols;
    }
}
