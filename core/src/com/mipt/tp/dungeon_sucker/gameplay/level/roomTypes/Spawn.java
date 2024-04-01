package com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes;

import com.mipt.tp.dungeon_sucker.gameplay.DungeonMasster;
import com.badlogic.gdx.graphics.Texture;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class Spawn extends PeaceRoom {

  public Spawn(IntVector2 levelPosition, Texture texture) {
    super(levelPosition, texture);
  }

  public Spawn(IntVector2 position, Texture texture, DungeonMasster masster) {
    super(position, texture, masster);
  }
}
