package com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForEnemies;

import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills.DamageRandomEnemy;
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
    // public DamageRandomEnemy(Weapon weapon, int damage, String type, String element, boolean isMelee, double percentOfElementDamage, boolean isUsedByHostile)
    this.generateSkill(new DamageRandomEnemy(this, this.damage, "Point","None", isHostile, 0, this.holder.isHostile));
  }

  public void use(Room room) {
    System.out.println("using RatClaws");
    int index = new Random().nextInt(this.skills.length);
    if(index == 0){
      (skills[index]).use(room);
    }
  }
}
