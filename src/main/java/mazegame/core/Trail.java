package mazegame.core;

import mazegame.util.Queue;
import mazegame.game.Update;

public interface Trail {
    void        add(Place p);
    Footprint[] getAll();
    void        update(Queue<Update> updates);
}
