package com.mipt.tp.dungeon_sucker.Skills.DamagingSkills;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkill;
import com.mipt.tp.dungeon_sucker.gameplay.Action;
import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

public class DamageOneEntity extends DamagingSkill {

  public DamageOneEntity(Weapon weapon, int damage, DamageTypeSet type, ElementSet element,
      boolean isMelee, double percentOfElementDamage) {
    this.weapon = weapon;
    this.damage = new Damage(this.weapon.holder, type, element, isMelee, percentOfElementDamage,
        damage);
    this.description = "Deals " + this.damage.totalDamage + " damage to enemy by your choice";
    this.type = type;
  }


  // Todo: дублируемый код убрать
  public void use(Room room, Action doAfterUse) {
    Entity[] entities;
    if (room.isHaunted) {
      entities = room.hostileEntities;
    } else {
      return;
    }
    chooseVictimToAttackUntilPredicate(room,
        args -> {
          int index = args[0];
          index = Math.min(Math.max(index, 0), entities.length - 1);
          if (entities[index] != null) {
            entities[index].getDamaged(new Damage(this.damage, this.lastPower, this.power));
          }
          super.use(room, doAfterUse);
        },
        value -> !(entities[Math.min(Math.max(value, 0), entities.length - 1)] == null
            || !entities[Math.min(Math.max(value, 0), entities.length - 1)].isAlive));
  }

  public String toString() {
    return "punch one enemy by your choice, dealing " + this.damage + " damage";
  }
}
