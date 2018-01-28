package mazegame.core;

import mazegame.util.Direction;

public abstract class MobileEntity extends Entity {

    public MobileEntity(Place place) {
        super(place);
    }

    public boolean move(Direction dir) {
        Place dst = place.placeAt(dir);
        if (dst == null) {
            return false;
        }
        if (!dst.isWalkable()) {
            return false;
        }
        this.place = dst;
        return true;
    }
}
