package com.mipt.tp.dungeon_sucker.gameplay;

public class Map {
  public Room[][] rooms;
  public Spawn spawn;
  public ExitRoom exit;

  public Map(Room[][] rooms, Spawn spawn, ExitRoom exit) {
    this.rooms = rooms;
    this.spawn = spawn;
    this.exit = exit;
  }
}
