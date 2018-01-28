// This is the main class of the game.
//
// Its most simple implementation holds a map, an end and a hero and
// take care of moving the hero around and geenrating the ClientView
// (the data structure that clients consume to render the maze to the
// player).

package mazegame.core;

import mazegame.game.ClientView;
import mazegame.game.Icon;
import mazegame.game.Update;
import mazegame.util.Direction;
import mazegame.util.Queue;
import mazegame.util.LinkedQueue;

public class Maze {

    private Map map;
    private End end;
    private Hero hero;
    private Trail trail;
    private Queue<Update> updates;

    public Maze(Map map, End end, Hero hero) {
        if (map == null) {
            throw new NullPointerException("map");
        }
        if (end == null) {
            throw new NullPointerException("end");
        }
        if (hero == null) {
            throw new NullPointerException("hero");
        }

        this.map = map;
        this.end = end;
        this.hero = hero;

        this.trail = new TrailArray(3);

        updates = new LinkedQueue<Update>();
    }

    public boolean moveHero(Direction dir) {
        // forget old updates
        updates = new LinkedQueue<Update>();

        Place old = hero.getPlace();
        boolean lastMoveOk = hero.move(dir);
        if (lastMoveOk) {
            // update to remove old hero
            Tile oldHeroTile =
                map.getTile(old.getRow(), old.getCol());
            Update oldHero = new Update(old, oldHeroTile.getIcon());
            updates.enqueue(oldHero);

            // add old position to trail
            trail.add(old);

            // update trail changes
            trail.update(updates);

            // update to add new hero
            Update newHero =
                new Update(hero.getPlace(), hero.getIcon());
            updates.enqueue(newHero);
        }
        return lastMoveOk;
    }

    private Icon[][] addEntityToIcons(Entity e, Icon[][] icons) {
        Place place = e.getPlace();
        icons[place.getRow()][place.getCol()] = e.getIcon();
        return icons;
    }

    public ClientView getClientView() {
        // get floor map
        Icon[][] icons = map.getIcons();
        // add end
        icons = addEntityToIcons(end, icons);
        // add trail
        Footprint[] tracks = trail.getAll();
        for (int i=0; i<tracks.length; i++) {
            icons = addEntityToIcons(tracks[i], icons);
        }
        // add hero
        icons = addEntityToIcons(hero, icons);
        // isGameOver
        boolean isGameOver = isGameOver();
        return new ClientView(icons, isGameOver);
    }

    public boolean isGameOver() {
        return hero.getPlace().equals(end.getPlace());
    }

    public Queue<Update> getUpdates() {
        return updates;
    }
}
