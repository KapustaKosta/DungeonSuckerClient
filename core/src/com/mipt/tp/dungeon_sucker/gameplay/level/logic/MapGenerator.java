package com.mipt.tp.dungeon_sucker.gameplay.level.logic;

import com.mipt.tp.dungeon_sucker.UI.texturePacks.RoomsTexturesPack;
import com.mipt.tp.dungeon_sucker.gameplay.level.Map;

public abstract class MapGenerator {
  protected RoomsTexturesPack roomsTexturesPack;

  public MapGenerator(RoomsTexturesPack roomsTexturesPack) {
    this.roomsTexturesPack = roomsTexturesPack;
  }

  public abstract Map generate(Map map, int targetRoomsCount);
}
