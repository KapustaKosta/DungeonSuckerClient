package com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes;

import com.badlogic.gdx.graphics.Texture;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class ExitRoom extends Room {
    public ExitRoom(IntVector2 levelPosition, Texture texture) {
        super(levelPosition, texture);
    }
}
