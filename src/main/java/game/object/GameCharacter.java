package game.object;

import java.util.List;

/**
 * A human object in the game
 */
public interface GameCharacter extends GameObject {

    List<Thing> getThings();

    int getHealth();

    boolean isEnemy(GameCharacter gameCharacter);

    int getWeightOfAttack();

    void hitBy(GameCharacter character);

    void setControlled(boolean isControlled);
}
