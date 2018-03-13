package game.object;

import java.io.Serializable;

public interface GameObject extends Serializable {

    void notify(Event event);

    int getXCoord();

    int getYCoord();

    char getSign();

    boolean isVisible();

    void nextStep();
}
