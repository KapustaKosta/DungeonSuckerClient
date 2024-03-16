package com.mipt.tp.dungeon_sucker.gameplay;

import com.badlogic.gdx.graphics.Texture;

public abstract class MapGenerator {
  // Todo: вынести эти поля в отдельную структуру
  protected Texture emptyRoomTexture;
  protected Texture hauntedRoomTexture;
  protected Texture exitRoomTexture;
  protected Texture oasisTexture;
  protected Texture peaceRoomTexture;
  protected Texture spawnTexture;
  protected Texture shopTexture;

  public MapGenerator(Texture emptyRoomTexture, Texture hauntedRoomTexture, Texture exitRoomTexture,
      Texture oasisTexture, Texture peaceRoomTexture, Texture spawnTexture, Texture shopTexture) {
    this.emptyRoomTexture = emptyRoomTexture;
    this.hauntedRoomTexture = hauntedRoomTexture;
    this.exitRoomTexture = exitRoomTexture;
    this.oasisTexture = oasisTexture;
    this.peaceRoomTexture = peaceRoomTexture;
    this.spawnTexture = spawnTexture;
    this.shopTexture = shopTexture;
  }

  public abstract Map generate(Map map, int targetRoomsCount);
}
