package com.mipt.tp.dungeon_sucker.Skills.DamagingSkills;

import com.mipt.tp.dungeon_sucker.Skills.DamagingSkill;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.Scanner;

public class DamageOneEntity extends DamagingSkill {
  public DamageOneEntity(Weapon weapon, int damage, DamageTypeSet type, ElementSet element, boolean isMelee, double percentOfElementDamage) {
    this.weapon = weapon;
    this.damage = new Damage(this.weapon.holder, type, element, isMelee, percentOfElementDamage, damage);
    this.description = "Deals " + this.damage.totalDamage + " damage to enemy by your choice";
    this.type = type;
  }


  public void use(Room room) {
    Entity[] entities;
    if (room.isHaunted) {
      entities = room.hostileEntities;
    } else {
      return;
    }
    Scanner in = new Scanner(System.in);
    System.out.println("choose enemy to punch");
    for (int i = 0; i < (room).hostileEntities.length; ++i) {
      if (room.hostileEntities[i] != null) {
        if (room.hostileEntities[i].isAlive) {
          System.out.println(i + 1 + ": " + ((room.hostileEntities[i])).name + ": "
              + ((room).hostileEntities[i]).health + " hp");
        }
      }
    }
    int index = in.nextInt();
    while (!entities[Math.min(Math.max(index - 1, 0), entities.length - 1)].isAlive) {
      System.out.println("do not play with dead!");
      System.out.println("choose another one");
      index = in.nextInt();
    }
    index = Math.min(Math.max(index - 1, 0), entities.length - 1);
    entities[index].getDamaged(new Damage(this.damage, this.lastPower, this.power));
    super.use(room);
  }

  public String toString() {
    return "punch one enemy by your choice, dealing " + this.damage + " damage";
  }
}
