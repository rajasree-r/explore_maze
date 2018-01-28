
/**
 * The GameSpecs are used by the ui to create a game.
 */

package mazegame.game;

import mazegame.core.Map;
import mazegame.core.End;
import mazegame.core.Hero;

public interface GameSpecs {
    Map generateMap();
    End generateEnd();
    Hero generateHero();
}
