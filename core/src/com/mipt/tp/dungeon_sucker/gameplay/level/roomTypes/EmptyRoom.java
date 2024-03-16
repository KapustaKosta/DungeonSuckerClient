package com.mipt.tp.dungeon_sucker.gameplay.level.rooms;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

public class EmptyRoom extends Room {

  public EmptyRoom(Vector2 position,
      Texture texture) {
    super(position, texture);
  }
}
