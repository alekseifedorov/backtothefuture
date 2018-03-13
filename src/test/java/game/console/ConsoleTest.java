package game.console;


import game.object.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleTest {

    private Console console;

    @BeforeMethod
    public void setUp() throws Exception {
        Console.clear();
        console = Console.getConsole();

    }

    @Test
    public void shouldHaveAllMandatoryCharactersAndThings() {
        assertThat(console.gameModel.gameObjects).hasSize(7).hasOnlyElementsOfTypes(Biff.class, MartyMcFly.class, GeorgeMcFly.class, TimeMachine.class, Almanac.class, BiffsFriend.class);
    }

    @Test
    public void shouldSaveAndResume() throws IOException, ClassNotFoundException {
        // only one Marty and several Biff's friends
        console.gameModel.addGameObjects(Arrays.asList("Marty McFly", "Biff's Friend"));
        assertThat(console.gameModel.gameObjects).hasSize(8);
        console.save();

        // only one Time Machine and several Biff's friends
        console.gameModel.addGameObjects(Arrays.asList("Time Machine", "Biff's Friend", "Biff's Friend"));
        assertThat(console.gameModel.gameObjects).hasSize(10);

        console.resume();
        assertThat(console.gameModel.gameObjects).hasSize(8);
    }

    @Test
    public void shouldCreateCharacter() {
        assertThat(console.gameModel.gameObjects).hasSize(7);
        console.executeCommand("create Biff's Friend");
        console.executeCommand("create Biff's Friend");
        assertThat(console.gameModel.gameObjects).hasSize(9);
    }
}
