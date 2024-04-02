package com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForEnemies;

import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills.NonControllableDamageOneEnemy;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.Random;

public class RatClaws extends Weapon {
  int damage;
  String name;

  public RatClaws(int damage, boolean isHostile) {
    super(1);
    this.damage = damage;
    this.name = "Claws of a rat";
    this.generateSkill(new NonControllableDamageOneEnemy(this, this.damage, isHostile, "Point"));
  }

  public void use(Room room) {
    System.out.println("using RatClaws");
    int index = new Random().nextInt(this.skills.length);
    if(index == 0){
      (skills[index]).use(room);
    }
  }
}
