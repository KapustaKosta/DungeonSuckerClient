package com.mipt.tp.dungeon_sucker.gameplay;

import com.mipt.tp.dungeon_sucker.UI.Drawable;
import com.mipt.tp.dungeon_sucker.math.RandomNumGenerator;

public class Level implements Drawable {
  private Map map;
  private final MapGenerator mapGenerator;

  public Level(MapGenerator mapGenerator)
  {
    this.map = new Map(new Room[25][42], null, null);
    this.mapGenerator = mapGenerator;
    generateMap();
  }

  private void generateMap()
  {
    int targetRoomCount = RandomNumGenerator.generateFromRange(15, 25);
    map = mapGenerator.generate(map, targetRoomCount);
  }

  @Override
  public void draw() {
    for(int i = 0 ; i < map.rooms.length ; i++)
    {
      for (int j = 0 ; j < map.rooms[0].length ; j++)
      {
        map.rooms[i][j].draw();
      }
    }
  }
}
