package com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForEnemies;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills.DamageRandomEnemy;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;

public class BatClaws extends Weapon {
  int damage;

  public BatClaws(int damage) {
    super(1);
    this.dexterityScale = 0.3;
    this.strengthScale = 0.2;
    this.damage = damage;
    this.name = "Claws of a bat";
    // public DamageRandomEnemy(Weapon weapon, int damage, String type, String element, boolean isMelee, double percentOfElementDamage, boolean isUsedByHostile)
  }

  public void getObtained(Entity holder) {
    super.getObtained(holder);
    this.generateSkillForCreature(new DamageRandomEnemy(this, this.damage, DamageTypeSet.Point, ElementSet.None, true, 0, this.holder.isHostile));
  }
}
