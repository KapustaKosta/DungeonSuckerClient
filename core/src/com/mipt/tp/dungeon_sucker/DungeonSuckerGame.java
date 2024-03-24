package com.mipt.tp.dungeon_sucker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mipt.tp.dungeon_sucker.UI.texturePacks.RoomsTexturesPack;
import com.mipt.tp.dungeon_sucker.gameplay.DungeonMasster;
import com.mipt.tp.dungeon_sucker.gameplay.entities.Character;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.gameplay.level.logic.DFSMapGenerator;
import com.mipt.tp.dungeon_sucker.gameplay.level.Level;
import com.mipt.tp.dungeon_sucker.gameplay.level.logic.MapGenerator;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class DungeonSuckerGame extends ApplicationAdapter {

  private static final Color BACKGROUND = Color.valueOf("#222222");
  private Level level;
  private Character character;
  private Room room;

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
    IntVector2 characterPosition = new IntVector2(level.getMap().spawn.getPosition().x, level.getMap().spawn.getPosition().y);
    this.character = new Character(characterPosition, new Texture("character.png"), level, 1, 1);

    Texture texture = new Texture("room.png");
    room = new Room(new IntVector2(10, 10), texture, new DungeonMasster());
    FightTry.aboba();
  }

  @Override
  public void render() {
    this.update();
    ScreenUtils.clear(BACKGROUND);
    level.draw();
    character.draw();
  }
}
