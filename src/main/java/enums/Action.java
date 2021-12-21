package enums;

public enum Action {

    RIGHT(0, "right", "Игрок передвинулся направо"),
    LEFT(1, "left", "Игрок передвинулся налево"),
    UP(2, "up", "Игрок передвинулся вверх"),
    DOWN(3, "down", "Игрок передвинулся вниз"),
    BOMB(4, "bomb", "Игрок поставил бомбу");

    private final int code;
    private final String title;
    private final String description;

    Action(int code, String title, String description) {
        this.code = code;
        this.title = title;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
