package mazegame.core;

import mazegame.util.Bag;
import mazegame.util.grid.Grid;
import mazegame.util.grid.Step;
import mazegame.util.grid.Vertex;

import java.util.List;

public class DepthFirstAlgorithm extends GridAlgorithm {

    public DepthFirstAlgorithm(int mapRows, int mapCols) {
        super(mapRows, mapCols);
    }

    @Override
    public void algorithm(){
        int gridRows = mapDimToGridDim(mapRows);
        int gridCols = mapDimToGridDim(mapCols);
        grid = new Grid(gridRows, gridCols, false);
        int rows = grid.rows();
        int cols = grid.cols();
        Vertex current = grid.getVertex(rows-1, cols-1);
        applyDepthFirstAlgorithm(current);
    }

    private void applyDepthFirstAlgorithm(Vertex current) {
        IBag<Step> neighbours = new Bag<Step>(4);
        current.setVisited(true);

        List<Step> neighboursList = current.notConnectedNonVisitedNegihbours();
        for(int i=0;i<neighboursList.size();i++){
            neighbours.add(neighboursList.get(i));
        }

        while (! neighbours.isEmpty()) {
            Step step = neighbours.remove();
            Vertex destination = step.destination();
            if (destination.isVisited()) {
                continue;
            }
            step.setConnection(true);
            applyDepthFirstAlgorithm(destination);
        }
    }
}
