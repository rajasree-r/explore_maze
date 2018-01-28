package mazegame.core;

import mazegame.game.Icon;

class Footprint extends Entity {

    Footprint(Place place) {
        super(place);
    }

    public Icon getIcon() {
        return Icon.FOOTPRINT;
    }
}
