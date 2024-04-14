package com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForPlayer;

import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.DamageOneEntity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.DamageOneEntityWithCrit;

public class Sword extends Weapon {
  int damage;

  public Sword(int damage, String name) {
    super(3);
    this.damage = damage;
    this.name = name;
    // public DamageOneEntity(Weapon weapon, int damage, String type, String element, boolean isMelee, double percentOfElementDamage)
    // public DamageOneEntityWithCrit(Weapon weapon, int damage, String type, String element, boolean isMelee, double percentOfElementDamage, int numerator, int divider, double multiplier) {
    this.generateSkill(new DamageOneEntity(this, this.damage, "Slash", this.element, true, 0.25));
    this.generateSkill(new DamageOneEntityWithCrit(this, this.damage * 3 / 4, "Slash", this.element, true, 0.25, 1, 3, 2));
    this.generateSkill(new DamageOneEntity(this, this.damage, "Point", this.element, true, 0.5));
    // Первый скилл - рубящий, второй - колющий. Добавлю, когда обсудим, какие типы урона что делают
  }
}
