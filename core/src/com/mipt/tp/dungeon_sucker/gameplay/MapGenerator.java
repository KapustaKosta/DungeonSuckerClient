package com.mipt.tp.dungeon_sucker.gameplay;

import com.badlogic.gdx.graphics.Texture;
import com.mipt.tp.dungeon_sucker.UI.texturePacks.RoomsTexturesPack;

public abstract class MapGenerator {
  protected RoomsTexturesPack roomsTexturesPack;

  public MapGenerator(RoomsTexturesPack roomsTexturesPack) {
    this.roomsTexturesPack = roomsTexturesPack;
  }

  public abstract Map generate(Map map, int targetRoomsCount);
}
