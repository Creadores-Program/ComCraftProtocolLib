package com.github.steveice10.comcraft.protocol.data.game;

public enum ActionType {
    JOIN(1),
    QUIT(2),
    MOVE(3),
    INVENTORY(4);

    private final int id;

    ActionType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ActionType fromId(int id) {
        for (ActionType type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown ActionType ID: " + id);
    }
}
