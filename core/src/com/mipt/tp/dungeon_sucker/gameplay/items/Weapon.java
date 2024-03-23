package com.mipt.tp.dungeon_sucker.gameplay.items;

import com.mipt.tp.dungeon_sucker.gameplay.Skill;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes.HauntedRoom;

public class Weapon {
  int weight;
  protected String name;
  protected Skill[] skills;
  protected int amount = 0;
  public Weapon(int numberOdSkills){
    this.skills = new Skill[numberOdSkills];
  }
  public void useSkill(int index, Room room){
    this.skills[index].use(room);
  }

  protected void generateSkill(Skill skill) {
    this.skills[this.amount++] = skill;
  }

  public void use(Room place) {
    System.out.println("FUCK");
  }
  public void use(HauntedRoom room){
  }
}
