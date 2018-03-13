package game.object;

import game.console.Position;

import java.util.Objects;

public class Almanac extends AbstractThing {

    private static Almanac almanac;

    public static Almanac getInstance(Position position) {
        if (Objects.isNull(almanac)) {
            almanac = new Almanac(position);
        }

        return almanac;
    }

    public Almanac(Position position) {
        super(position, 'A');
    }

    public void dropBy(Biff biff) {
        xCoord = biff.xCoord;
        yCoord = biff.yCoord;
    }

    @Override
    public String toString() {
        return "Almanac";
    }
}
