package game.console;

import game.object.*;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class holds game state
 */
public class GameModel implements Serializable {

    protected Set<GameObject> gameObjects = new HashSet<>();

    private int mapWidth;

    private int mapHeight;

    private int screenWidth;

    private int screenHeight;

    private GameCharacter hero;

    private transient char[][] screen;

    private transient String status;

    private transient Random random = new Random();

    public GameModel(int mapWidth, int mapHeight, int screenWidth, int screenHeight) {
        status = "";
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        initScreen();
    }

    void initScreen() {
        screen = new char[screenWidth][screenHeight];
    }

    public void addGameObjects(List<String> names) {
        names.stream().forEach(name -> addGameObject(name));
    }

    public void nextStep() {
        List<Thing> thingsInSacks = gameObjects.stream().filter(o -> o instanceof GameCharacter).map(o -> (GameCharacter) o)
                                               .flatMap(c -> c.getThings().stream()).collect(Collectors.toList());

        gameObjects.stream().forEach(current -> {
            current.nextStep();
            gameObjects.stream()
                       .filter(o -> o.isVisible())
                       .filter(o -> !thingsInSacks.contains(current))
                       .forEach(o -> o.notify(new Event(current)));
        });
    }

    public Position nextRandomPosition() {
        while (true) {
            Position pos = new Position(random.nextInt(mapWidth), random.nextInt(mapHeight));
            if (!gameObjects.stream().anyMatch(o -> o.getXCoord() == pos.getX() && o.getYCoord() == pos.getY())) {
                return pos;
            }
        }
    }

    public StringBuilder getScreen() {
        Stream.of(screen).forEach(arr -> Arrays.fill(arr, ' '));

        int xScreen = hero.getXCoord() - screen.length / 2;
        int yScreen = hero.getYCoord() - screen[0].length / 2;

        List<Thing> thingsInSacks = gameObjects.stream().filter(o -> o instanceof GameCharacter).map(o -> (GameCharacter) o)
                                               .flatMap(c -> c.getThings().stream()).collect(Collectors.toList());

        gameObjects.stream().filter(o -> o.getXCoord() >= xScreen && o.getXCoord() < xScreen + screen.length &&
                o.getYCoord() >= yScreen && o.getYCoord() < yScreen + screen[0].length)
                   .filter(o -> o.isVisible())
                   .filter(o -> !thingsInSacks.contains(o))
                   .forEach(o -> screen[o.getXCoord() - xScreen][o.getYCoord() - yScreen] = o.getSign());

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < screen.length; i++) {
            for (int j = 0; j < screen[0].length; j++) {
                if (i == 0 || i == screen.length - 1) {
                    result.append('o');
                } else {
                    if (j == 0) {
                        result.append('o');
                    }

                    result.append(screen[i][j]);

                    if (j == screen.length - 1) {
                        result.append('o');
                    }
                }
            }
            result.append('\n');
        }
        return result;
    }

    public GameObject addGameObject(String name) {
        GameObject result;
        Position pos = nextRandomPosition();
        switch (name) {
            case "Marty McFly":
                result = MartyMcFly.getInstance(pos, mapWidth, mapHeight);
                break;
            case "George McFly":
                result = GeorgeMcFly.getInstance(pos, mapWidth, mapHeight);
                break;
            case "Biff":
                result = Biff.getInstance(pos, mapWidth, mapHeight, Almanac.getInstance(pos));
                break;
            case "Biff's Friend":
                result = new BiffsFriend(pos, mapWidth, mapHeight);
                break;
            case "Time Machine":
                result = TimeMachine.getInstance(pos);
                break;
            case "Almanac":
                result = Almanac.getInstance(pos);
                break;
            default:
                throw new IllegalArgumentException("Unknown game object " + name);
        }

        gameObjects.add(result);
        return result;
    }

    public void setHero(String sign) {
        gameObjects.stream().filter(o -> o instanceof GameCharacter).map(o -> (GameCharacter)o).filter( o -> o.getSign() == sign.charAt(0)).findAny().ifPresent(this::setHero);
    }

    public void setHero(GameCharacter character) {
        hero = character;
        character.setControlled(true);
    }

    public GameCharacter getHero() {
        return hero;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
