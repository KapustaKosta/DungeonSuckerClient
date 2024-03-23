package com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mipt.tp.dungeon_sucker.gameplay.DungeonMasster;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class Oasis extends PeaceRoom {
  public Oasis(IntVector2 levelPosition, Texture texture) {
    super(levelPosition, texture);
  }

  public Oasis(IntVector2 position, Texture texture, DungeonMasster masster) {
    super(position, texture, masster);
  }
}
