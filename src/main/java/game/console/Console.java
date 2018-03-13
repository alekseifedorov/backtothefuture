package game.console;

import game.object.AbstractGameCharacter;
import game.object.GameCharacter;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This class creates a screen and is responsible for game workflow
 */
public class Console {

    public static final String BACKTOTHEFUTURE_DAT = "backtothefuture.dat";

    private static Console console;

    GameModel gameModel;

    private boolean stopGame;

    private boolean demoMode;

    public static Console getConsole() {
        if (Objects.isNull(console)) {
            console = new Console();
        }

        return console;
    }

    private Console() {
        gameModel = new GameModel(20, 20, 20, 20);
        GameCharacter marty = (GameCharacter) gameModel.addGameObject("Marty McFly");
        gameModel.setHero(marty);
        gameModel.addGameObjects(Arrays.asList("George McFly", "Biff", "Time Machine", "Almanac", "Biff's Friend", "Biff's Friend"));
    }

    public void start() {
        while (!stopGame) {
            try {
                print();
                nextStep();
                if (gameModel.getHero().getHealth() <= 0) {
                    gameModel.setStatus("You lost");
                }
            } catch (Exception e) {
                gameModel.setStatus(e.getMessage());
            }
        }
    }

    public void print() throws IOException {
        clearScreen();
        GameCharacter hero = gameModel.getHero();
        System.out.println(gameModel.getScreen());
        System.out.println(String.format("You have in sack: %s", hero.getThings()));
        System.out.println(String.format("Health: %s", hero.getHealth()));
        System.out.println(String.format("X: %s, Y: %s", hero.getXCoord(), hero.getYCoord()));
        System.out.println(String.format("Status: %s", gameModel.getStatus()));
        System.out.println(String.format("You are : %s", gameModel.getHero()));
        if (!demoMode) {
            System.out.print(String.format("Type a command and press Enter (save,resume,quit,I'm <sign>,create <name>,up,down,left,right,demo) > "));
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            executeCommand(reader.readLine());
        } else {
            pause();
        }
    }

    public void executeCommand(String command) {
        try {
            AbstractGameCharacter hero = (AbstractGameCharacter) gameModel.getHero();
            String[] splitCommands = command.split(" ");
            switch (splitCommands[0]) {
                case "save":
                    save();
                    break;
                case "resume":
                    resume();
                    break;
                case "up":
                    hero.up();
                    break;
                case "down":
                    hero.down();
                    break;
                case "left":
                    hero.left();
                    break;
                case "right":
                    hero.right();
                    break;
                case "I'm":
                    if (splitCommands.length > 0) {
                        gameModel.setHero(splitCommands[1]);
                    }
                    break;
                case "create":
                    if (splitCommands.length > 0) {
                        gameModel.addGameObject(Stream.of(splitCommands).skip(1).collect(Collectors.joining(" ")));
                    }
                    break;

                case "demo":
                    demoMode = true;
                    break;
                case "":
                    break;
                case "quit":
                    stopGame = true;
                    break;
                default:
                    gameModel.setStatus("Unknown command");

            }
        } catch (Exception e) {
            gameModel.setStatus("Unexpected exception : " + e.getMessage());
        }
    }

    public void pause() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            gameModel.setStatus("Unexpected exception : " + e.getMessage());
        }
    }

    public void clearScreen() {
        IntStream.range(0, 100).forEach(i -> System.out.println());
    }

    public void nextStep() {
        gameModel.nextStep();
    }

    public void save() throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(BACKTOTHEFUTURE_DAT);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(gameModel);
        }
    }

    public void resume() throws IOException, ClassNotFoundException {
        try (FileInputStream fileIn = new FileInputStream(BACKTOTHEFUTURE_DAT);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            gameModel = (GameModel) in.readObject();
            gameModel.initScreen();
        }
    }

    // for test purposes
    static void clear() {
        console = null;
    }
}
