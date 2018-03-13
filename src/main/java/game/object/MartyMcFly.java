package game.object;

import game.console.Position;

import java.util.Collections;
import java.util.Objects;

public class MartyMcFly extends AbstractGameCharacter {

    private static MartyMcFly martyMcFly;

    public static MartyMcFly getInstance(Position position, int mapWidth, int mapHeight) {
        if (Objects.isNull(martyMcFly)) {
            martyMcFly = new MartyMcFly(position, mapWidth, mapHeight);
        }

        return martyMcFly;
    }

    private MartyMcFly(Position position, int mapWidth, int mapHeight) {
        super(position, 'M', mapWidth, mapHeight, 10, Collections.emptyList());
    }

    @Override
    public String toString() {
        return "Marty McFly";
    }

    @Override
    public void notify(Event event) {
        super.notify(event);
        if (!(event.getGameObject() instanceof Almanac)) {
            return;
        }

        Almanac almanac = (Almanac)event.getGameObject();
        if (isCollided(almanac)) {
            things.add(almanac);
        }
    }

    @Override
    public boolean isEnemy(GameCharacter character) {
        return character instanceof Biff || character instanceof BiffsFriend;
    }
}
