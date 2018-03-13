package game.object;

import game.console.Position;

import java.util.Collections;
import java.util.Objects;

public class GeorgeMcFly extends AbstractGameCharacter {

    private static GeorgeMcFly georgeMcFly;

    public static GeorgeMcFly getInstance(Position position, int mapWidth, int mapHeight) {
        if (Objects.isNull(georgeMcFly)) {
            georgeMcFly = new GeorgeMcFly(position, mapWidth, mapHeight);
        }

        return georgeMcFly;
    }

    private GeorgeMcFly(Position position, int mapWidth, int mapHeight) {
        super(position, 'G', mapWidth, mapHeight, 100, Collections.emptyList());
    }

    @Override
    public String toString() {
        return "George McFly";
    }

    @Override
    public boolean isEnemy(GameCharacter character) {
        return character instanceof Biff || character instanceof BiffsFriend;
    }

}
