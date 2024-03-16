package com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class PeaceRoom extends Room {

  public PeaceRoom(IntVector2 levelPosition,
                   Texture texture) {
    super(levelPosition, texture);
  }
}
