package game.object;

import game.console.Position;

public class AbstractGameObject implements GameObject {

    protected int xCoord;

    protected int yCoord;

    protected char sign;

    public AbstractGameObject(Position position, char sign) {
        this.xCoord = position.getX();
        this.yCoord = position.getY();
        this.sign = sign;
    }

    @Override
    public void notify(Event event) {

    }

    @Override
    public boolean isVisible() {
        return true;
    }

    @Override
    public int getXCoord() {
        return xCoord;
    }

    @Override
    public int getYCoord() {
        return yCoord;
    }

    @Override
    public char getSign() {
        return sign;
    }

    @Override
    public void nextStep() {
    }

    public boolean isCollided(GameObject object) {
        return Math.abs(xCoord - object.getXCoord()) <=1 && Math.abs(yCoord - object.getYCoord()) <=1;
    }
}
