package com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkill;
import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.Skill;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

public class DamageAllEnemies extends DamagingSkill {
  boolean isUsedByHostile;

  public DamageAllEnemies(Weapon weapon, int damage, String type, String element, boolean isMelee, double percentOfElementDamage, boolean isUsedByHostile) {
    this.weapon = weapon;
    this.damage = new Damage(this.weapon.holder, type, element, isMelee, percentOfElementDamage, damage);
    this.isUsedByHostile = isUsedByHostile;
    this.description = "Deal " + this.damage + " to all enemies in room";
    this.type = type;
  }

  public void use(Room room) {
    Entity[] enemies;
    if (this.isUsedByHostile) {
      enemies = room.friendlyEntities;
    } else {
      enemies = room.hostileEntities;
    }
    for(int i = 0; i < enemies.length; ++i){
      new DamageNthEnemy(this.weapon, this.damage, this.isUsedByHostile, i).use(room);
    }
  }
}
