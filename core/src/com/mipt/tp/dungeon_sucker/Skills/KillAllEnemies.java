package com.mipt.tp.dungeon_sucker.Skills;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.Skill;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForEnemies.DeadlineIsComing;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

public class KillAllEnemies extends DamagingSkill {
  boolean isUsedByHostile;
  public KillAllEnemies(Weapon weapon, boolean isHostile) {
    super();
    this.weapon = weapon;
    this.isUsedByHostile = isHostile;
  }
  public void use(Room room){
    Entity[] enemies;
    if(this.isUsedByHostile){
      enemies = room.friendlyEntities;
    }else{
      enemies = room.hostileEntities;
    }
    for(int  i = 0; i < 10; ++i){
      for(int j = 0; j < enemies.length; ++j){
        if(enemies[j] != null && enemies[j].isAlive){
          enemies[j].dieWithoutTriggeringAnything();
        }
      }
    }
  }
}
