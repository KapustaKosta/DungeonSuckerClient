package com.mipt.tp.dungeon_sucker.gameplay.level;

import com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes.ExitRoom;
import com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes.Spawn;

public class Map {
  public Room[][] rooms;
  public Spawn spawn;
  public ExitRoom exit;

  public Map(Room[][] rooms, Spawn spawn, ExitRoom exit) {
    this.rooms = rooms;
    this.spawn = spawn;
    this.exit = exit;
  }

  public boolean isRoomEmpty(int y, int x) {
    if (y < 0 || x < 0 || y >= rooms.length || x >= rooms[0].length) {
      return true;
    }
    return rooms[y][x].isLocked();
  }
}
