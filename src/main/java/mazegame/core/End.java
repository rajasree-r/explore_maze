package mazegame.core;

import mazegame.game.Icon;

public class End extends Entity {

    public End(Place place) {
        super(place);
    }

    public Icon getIcon() {
        return Icon.END;
    }
}
