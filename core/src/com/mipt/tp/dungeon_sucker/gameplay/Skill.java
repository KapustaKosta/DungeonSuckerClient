package com.mipt.tp.dungeon_sucker.gameplay;

import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes.HauntedRoom;

public class Skill {
  protected String description;
  public void use(Room room){
  }
  public void use(HauntedRoom room){
    System.out.println("FUCK");}
}
