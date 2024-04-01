package com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForPlayer;

import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.Skills.PhysicallyDamageOneEntity;
import com.mipt.tp.dungeon_sucker.Skills.PhysicallyDamageThreeEntities;

import java.util.Scanner;

public class Club extends Weapon {
  int damage;

  public Club(int damage, String name) {
    super(3);
    this.damage = damage;
    this.name = name;
    this.generateSkill(new PhysicallyDamageOneEntity(this, this.damage));
    this.generateSkill(new PhysicallyDamageThreeEntities(this,this.damage, 0.5, 1, 0.5));
    this.generateSkill(new PhysicallyDamageThreeEntities(this,this.damage, 0.75, 0.5, 0.75));
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
