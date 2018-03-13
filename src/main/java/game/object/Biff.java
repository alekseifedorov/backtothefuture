package game.object;

import game.console.Position;

import java.util.Arrays;
import java.util.Objects;

public class Biff extends AbstractGameCharacter {

    private static Biff biff;

    public static Biff getInstance(Position position, int mapWidth, int mapHeight, Thing almanac) {
        if (Objects.isNull(biff)) {
            biff = new Biff(position, mapWidth, mapHeight, almanac);
        }

        return biff;
    }

    private Biff(Position position, int mapWidth, int mapHeight, Thing almanac) {
        super(position, 'B', mapWidth, mapHeight, 30, Arrays.asList(almanac));
    }

    @Override
    public String toString() {
        return "Biff";
    }

    @Override
    public void hitBy(GameCharacter character) {
        super.hitBy(character);

        if (health <= 0) {
            things.stream().filter(t -> t instanceof Almanac).findAny().ifPresent(almanac -> {
                ((Almanac) almanac).dropBy(this);
                things.remove(almanac);
            });
        }
    }

    @Override
    public boolean isEnemy(GameCharacter character) {
        return character instanceof MartyMcFly || character instanceof GeorgeMcFly;
    }

}
