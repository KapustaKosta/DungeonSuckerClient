package com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkill;
import com.mipt.tp.dungeon_sucker.gameplay.Skill;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

public class DamageAllEnemies extends DamagingSkill {
  boolean isUsedByHostile;

  public DamageAllEnemies(Weapon weapon, int damage, boolean isUsedByHostile, String type) {
    this.weapon = weapon;
    this.damage = damage;
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
      new DamageNthEnemy(this.weapon, this.damage, this.isUsedByHostile, i, this.type).use(room);
    }
  }
}
