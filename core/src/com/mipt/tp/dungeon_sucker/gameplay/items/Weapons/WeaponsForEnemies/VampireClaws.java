package com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForEnemies;

import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills.DamageRandomEnemy;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.Creatures;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

public class VampireClaws extends Weapon {
  int damage;
  String name;

  public VampireClaws(int damage, boolean isHostile) {
    super(1);
    this.dexterityScale = 0.3;
    this.strengthScale = 0.2;
    this.damage = damage;
    this.name = "Claws of a rat";
    // public DamageRandomEnemy(Weapon weapon, int damage, String type, String element, boolean isMelee, double percentOfElementDamage, boolean isUsedByHostile)
    this.generateSkillForCreature(new DamageRandomEnemy(this, this.damage, DamageTypeSet.Point, ElementSet.None, isHostile, 0, this.holder.isHostile));
  }

  public void use(Room room) {
    this.holder.heal(this.power);
    super.use(room);
  }
}

