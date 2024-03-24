package com.mipt.tp.dungeon_sucker.gameplay.items;

import com.mipt.tp.dungeon_sucker.gameplay.Skill;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes.HauntedRoom;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Weapon {
  String rarity;
  int level;
  String element;
  int weight;
  protected String name;
  protected Skill[] skills;
  protected int amount = 0;
  public Weapon(int numberOfSkills){
    this.skills = new Skill[numberOfSkills];
  }
  public void useSkill(int index, Room room){
    this.skills[index].use(room);
  }

  protected void generateSkill(Skill skill) {
    this.skills[this.amount++] = skill;
  }

  public void use(Room place) {
    throw new NotImplementedException();
  }
  public void use(HauntedRoom room){
  }
}
