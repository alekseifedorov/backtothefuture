package game.object;

import game.console.Position;

public abstract class AbstractThing extends AbstractGameObject implements Thing {

    public AbstractThing(Position position, char sign) {
        super(position, sign);
    }


    public void notify(Event event) {

    }

}
