package com.mipt.tp.dungeon_sucker.gameplay.level;

import com.mipt.tp.dungeon_sucker.UI.Drawable;
import com.mipt.tp.dungeon_sucker.gameplay.level.logic.MapGenerator;
import com.mipt.tp.dungeon_sucker.math.IntVector2;
import com.mipt.tp.dungeon_sucker.math.RandomNumGenerator;

public class Level implements Drawable {

  private Map map;
  private final MapGenerator mapGenerator;
  private IntVector2 anchor = new IntVector2(0, 0);

  public Level(MapGenerator mapGenerator, int width, int height) {
    this.map = new Map(new Room[height][width], null, null);
    this.mapGenerator = mapGenerator;
    generateMap();
  }

  private void generateMap() {
    int targetRoomCount = RandomNumGenerator.generateFromRange(15, 42);
    map = mapGenerator.generate(map, targetRoomCount);
    Map.setInstance(map);
  }

  public Map getMap() {
    return map;
  }

  public boolean canIGoTo(int y, int x) {
    return !map.isRoomEmpty(y, x);
  }

  @Override
  public void drawInLibGDX() {
    for (int i = 0; i < map.rooms.length; i++) {
      for (int j = 0; j < map.rooms[0].length; j++) {
        map.rooms[i][j].drawWithAnchor(anchor);
      }
    }
  }

  @Override
  public void drawInConsole() {

  }

  public void setAnchor(IntVector2 anchor) {
    this.anchor = anchor;
  }
}
