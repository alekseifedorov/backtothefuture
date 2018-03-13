package game.object;

public class Event {

    private GameObject gameObject;

    public Event(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public GameObject getGameObject() {
        return gameObject;
    }
}
