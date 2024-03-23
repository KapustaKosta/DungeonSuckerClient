package com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mipt.tp.dungeon_sucker.gameplay.DungeonMasster;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class Shop extends PeaceRoom {

  public Shop(IntVector2 levelPosition, Texture texture) {
    super(levelPosition, texture);
  }

  public Shop(IntVector2 position, Texture texture, DungeonMasster masster) {
    super(position, texture, masster);
  }
}
