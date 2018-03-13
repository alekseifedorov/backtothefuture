package game.object;

import game.console.Position;

import java.util.Objects;

public class TimeMachine extends AbstractThing {

    private static TimeMachine timeMachine;

    public static TimeMachine getInstance(Position position) {
        if (Objects.isNull(timeMachine)) {
            timeMachine = new TimeMachine(position);
        }

        return timeMachine;
    }

    public TimeMachine(Position position) {
        super(position, 'T');
    }
}
