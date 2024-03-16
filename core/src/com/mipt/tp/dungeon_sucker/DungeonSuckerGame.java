package com.mipt.tp.dungeon_sucker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mipt.tp.dungeon_sucker.gameplay.DFSMapGenerator;
import com.mipt.tp.dungeon_sucker.gameplay.HauntedRoom;
import com.mipt.tp.dungeon_sucker.gameplay.Level;
import com.mipt.tp.dungeon_sucker.gameplay.MapGenerator;
import com.mipt.tp.dungeon_sucker.gameplay.Room;

public class DungeonSuckerGame extends ApplicationAdapter {

  private static final Color BACKGROUND = Color.valueOf("#222222");
  private Level level;

  @Override
  public void create() {
    Texture emptyRoom = new Texture("emptyRoom.png");
    Texture hauntedRoom = new Texture("room.png");
    Texture exitRoom = new Texture("exitRoom.png");
    Texture oasisRoom = new Texture("room.png");
    Texture peaceRoom = new Texture("room.png");
    Texture spawnRoom = new Texture("spawn.png");
    Texture shopRoom = new Texture("room.png");
    MapGenerator mapGenerator = new DFSMapGenerator(emptyRoom, hauntedRoom, exitRoom, oasisRoom,
        peaceRoom, spawnRoom, shopRoom);
    level = new Level(mapGenerator);
  }

  @Override
  public void render() {
    ScreenUtils.clear(BACKGROUND);
    level.draw();
  }
}
