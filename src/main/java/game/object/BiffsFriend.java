package game.object;

import game.console.Position;

import java.util.Collections;

public class BiffsFriend extends AbstractGameCharacter {

    public BiffsFriend(Position position, int mapWidth, int mapHeight) {
        super(position, 'F', mapWidth, mapHeight, 30, Collections.emptyList());
    }

    @Override
    public boolean isEnemy(GameCharacter character) {
        return character instanceof MartyMcFly || character instanceof GeorgeMcFly;
    }

    @Override
    public String toString() {
        return "Biff's Friend";
    }
}
