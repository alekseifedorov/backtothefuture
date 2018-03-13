package game.object;

import game.console.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AbstractGameCharacter extends AbstractGameObject implements GameCharacter {

    protected List<Thing> things = new ArrayList();

    protected int health;

    private int experience;

    private int weightOfAttack;

    private int mapWidth;

    private int mapHeight;

    private Random random = new Random();

    private boolean isControlled;

    public AbstractGameCharacter(Position position, char sign, int mapWidth, int mapHeight, int weightOfAttack, List<Thing> things) {
        super(position, sign);
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.weightOfAttack = weightOfAttack;
        health = 100;
        experience = 0;
        this.things.addAll(things);
    }

    public void setControlled(boolean isControlled) {
        this.isControlled = isControlled;
    }

    @Override
    public boolean isEnemy(GameCharacter gameCharacter) {
        return false;
    }

    @Override
    public boolean isVisible() {
        return health > 0;
    }

    @Override
    public int getWeightOfAttack() {
        return weightOfAttack;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public List getThings() {
        return things;
    }

    @Override
    public void nextStep() {
        if (isControlled) {
            return;
        }

        switch (random.nextInt(4)) {
            case 0:
                xCoord = xCoord < mapWidth ? xCoord + 1 : xCoord;
                break;
            case 1:
                xCoord = xCoord > 0 ? xCoord - 1 : 0;
                break;
            case 2:
                yCoord = yCoord < mapHeight ? yCoord + 1 : yCoord;
                break;
            case 3:
                yCoord = yCoord > 0 ? yCoord - 1 : 0;
                break;
        }
    }

    public void up() {
        xCoord = xCoord > 0 ? xCoord - 1 : 0;
    }

    public void down() {
        xCoord = xCoord < mapWidth ? xCoord + 1 : xCoord;

    }

    public void left() {
        yCoord = yCoord > 0 ? yCoord - 1 : 0;
    }

    public void right() {
        yCoord = yCoord < mapHeight ? yCoord + 1 : yCoord;
    }

    @Override
    public void hitBy(GameCharacter character) {
        if (health <= 0) {
            return;
        }
        health -= character.getWeightOfAttack();
        experience++;
    }

    @Override
    public void notify(Event event) {
        if (!(event.getGameObject() instanceof GameCharacter)) {
            return;
        }

        GameCharacter character = (GameCharacter) event.getGameObject();
        if (isEnemy(character) && Math.abs(character.getXCoord() - this.getXCoord()) <= 1
                && Math.abs(character.getYCoord() - this.getYCoord()) <= 1) {
            character.hitBy(this);
        }
    }

}
