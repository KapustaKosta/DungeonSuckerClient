package com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes;

import com.badlogic.gdx.graphics.Texture;
import com.mipt.tp.dungeon_sucker.gameplay.DungeonMasster;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class PeaceRoom extends Room {

  public PeaceRoom(IntVector2 levelPosition,
                   Texture texture) {
    super(levelPosition, texture);
  }

  public PeaceRoom(IntVector2 position,
                   Texture texture, DungeonMasster masster) {
    super(position, texture, masster);
  }
}
