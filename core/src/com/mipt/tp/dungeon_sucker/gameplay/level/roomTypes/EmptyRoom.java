package com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes;

import com.badlogic.gdx.graphics.Texture;
import com.mipt.tp.dungeon_sucker.gameplay.DungeonMasster;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class EmptyRoom extends Room {
  public EmptyRoom(IntVector2 levelPosition,
                   Texture texture) {
    super(levelPosition, texture);
    isLocked = true;
  }

  public EmptyRoom(IntVector2 position,
                   Texture texture, DungeonMasster masster) {
    super(position, texture, masster);
  }
}
