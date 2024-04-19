package com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForPlayer;

import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.DamageOneEntity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.DamageOneEntityWithCrit;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.DamageThreeEntities;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

public class Rapier extends Weapon {
  public Rapier(int damage, String name, RaritySet rarity) {
    super(3);
    this.power = damage;
    this.name = name;
    this.dexterityScale = 0.5;
    this.rarity = rarity;
    this.recountScales();
    this.weight = 5;
    // public DamageOneEntity(Weapon weapon, int damage, String type, String element, boolean isMelee, double percentOfElementDamage)
    //public DamageThreeEntities(Weapon weapon, int damage, String type, String element, boolean isMelee, double percentOfElementDamage, double firstCoefficient, double secondCoefficient, double thirdCoefficient) {
    this.generateSkill(new DamageOneEntity(this, this.power, DamageTypeSet.Point, this.element, true, 0.5));
    this.generateSkill(new DamageOneEntityWithCrit(this, this.power, 0.75, DamageTypeSet.Point, this.element, true, 0.5, 1, 3, 2));
    this.generateSkill(new DamageOneEntity(this, this.power, DamageTypeSet.Slash, this.element, true, 0.5));
  }

  private void recountScales() {
    if (this.rarity == RaritySet.Poor) {
      this.dexterityScale /= 1.25;
    }
    if (this.rarity == RaritySet.Uncommon) {
      this.dexterityScale *= 1.2;
    }
    if (this.rarity == RaritySet.Rare) {
      this.dexterityScale *= 1.4;
    }
    if (this.rarity == RaritySet.Epic) {
      this.dexterityScale *= 2;
    }
    if (this.rarity == RaritySet.Legendary) {
      this.dexterityScale *= 3;
      this.intellectScale = 1;
      this.power = this.power * 3 / 2;
      this.weight = this.weight * 3 / 2;
    }
  }

  public void use(Room room) {
    this.recount();
    this.recount();
    int index = getSkillIndex();
    System.out.println("Choose your skill");
    System.out.println(this.skills[index].getClass());
    this.skills[index].use(room);
  }
}
