package com.mipt.tp.dungeon_sucker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mipt.tp.dungeon_sucker.UI.texturePacks.RoomsTexturesPack;
import com.mipt.tp.dungeon_sucker.gameplay.Character;
import com.mipt.tp.dungeon_sucker.gameplay.DFSMapGenerator;
import com.mipt.tp.dungeon_sucker.gameplay.HauntedRoom;
import com.mipt.tp.dungeon_sucker.gameplay.Level;
import com.mipt.tp.dungeon_sucker.gameplay.MapGenerator;
import com.mipt.tp.dungeon_sucker.gameplay.Room;

public class DungeonSuckerGame extends ApplicationAdapter {

  private static final Color BACKGROUND = Color.valueOf("#222222");
  private Level level;
  private Character character;

  private void update() {
    character.getInput();
  }

  @Override
  public void create() {
    RoomsTexturesPack texturesPack = new RoomsTexturesPack();
    texturesPack.emptyRoomTexture = new Texture("emptyRoom.png");
    texturesPack.hauntedRoomTexture = new Texture("room.png");
    texturesPack.exitRoomTexture = new Texture("exitRoom.png");
    texturesPack.oasisTexture = new Texture("room.png");
    texturesPack.peaceRoomTexture = new Texture("room.png");
    texturesPack.spawnTexture = new Texture("spawn.png");
    texturesPack.shopTexture = new Texture("room.png");
    texturesPack.shopTexture = new Texture("room.png");
    MapGenerator mapGenerator = new DFSMapGenerator(texturesPack);

    this.level = new Level(mapGenerator);
    this.character = new Character(level.getMap().spawn.getPosition(), new Texture("character.png"), level);
  }

  @Override
  public void render() {
    this.update();
    ScreenUtils.clear(BACKGROUND);
    level.draw();
    character.draw();
  }
}
