package com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForEnemies;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills.DamageOneRandomEnemyWithCrit;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills.DamageRandomEnemy;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;

public class MimicClaws extends Weapon {

  int damage;

  public MimicClaws(int damage) {
    super(3);
    this.dexterityScale = 0.3;
    this.strengthScale = 0.5;
    this.damage = damage;
    this.name = "Claws of a mimic";
  }

  public void getObtained(Entity holder) {
    super.getObtained(holder);
    this.generateSkillForCreature(
        new DamageRandomEnemy(this, this.damage, DamageTypeSet.Point, ElementSet.None, true, 0,
            this.holder.isHostile));
    this.generateSkillForCreature(
        new DamageRandomEnemy(this, this.damage, DamageTypeSet.Point, ElementSet.None, true, 0,
            this.holder.isHostile));
    this.generateSkillForCreature(
        new DamageOneRandomEnemyWithCrit(this, this.damage, 0.5, DamageTypeSet.Point,
            ElementSet.None, true, 0, 1, 2, 6, this.holder.isHostile));
  }
}
