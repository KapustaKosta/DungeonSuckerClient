package com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForPlayer;

import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.DamageOneEntity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.DamageThreeEntities;

import java.util.Scanner;

public class Club extends Weapon {
  int damage;
  static int SKILLS_COUNT = 3;

  public Club(int damage, String name) {
    super(SKILLS_COUNT);
    this.damage = damage;
    this.name = name;
    this.generateSkill(new DamageOneEntity(this, this.damage, "Smash", this.element, true, 0.3));
    this.generateSkill(new DamageThreeEntities(this, this.damage, "Smash", this.element, true, 0.3, 0.5, 1, 0.5));
    this.generateSkill(new DamageThreeEntities(this, this.damage, "Smash", this.element, true, 0.3, 0.75, 0.5, 0.75));
  }

  public void use(Room room) {
    if (room.isHaunted) {
      System.out.println("Choose your skill");
      for (int i = 0; i < this.skills.length; ++i) {
        System.out.println(i + 1 + ": " + skills[i].toString());
      }
    }
    Scanner in = new Scanner(System.in);
    int index = in.nextInt() - 1;
    System.out.println(this.skills[index].getClass());
    this.skills[index].use(room);
  }
}
