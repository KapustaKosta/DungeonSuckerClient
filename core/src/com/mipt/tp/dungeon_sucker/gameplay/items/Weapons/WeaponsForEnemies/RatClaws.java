package com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForEnemies;

import com.mipt.tp.dungeon_sucker.Skills.NonControllableSkills.NonControllablePhysicallyDamageOneEnemy;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes.HauntedRoom;

import java.util.Random;

public class RatClaws extends Weapon {
  int damage;
  String name;

  public RatClaws(int damage, boolean isHostile) {
    super(1);
    this.damage = damage;
    this.name = "Claws of a rat";
    this.generateSkill(new NonControllablePhysicallyDamageOneEnemy(this, this.damage, isHostile));
  }

  public void use(HauntedRoom room) {
    System.out.println("using RatClaws");
    int index = new Random().nextInt(this.skills.length);
    if(index == 0){
      (skills[index]).use(room);
    }
  }
}
