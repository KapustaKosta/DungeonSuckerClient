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
    this.generateSkill(new DamageOneEntity(this, this.damage, "Slash"));
    this.generateSkill(new DamageOneEntityWithCrit(this, this.damage * 3 / 4, 1, 3, 2, "Slash"));
    this.generateSkill(new DamageOneEntity(this, this.damage, "Point"));
    // Первый скилл - рубящий, второй - колющий. Добавлю, когда обсудим, какие типы урона что делают
    // Когда это обсудим, у всех скиллов надо будет добавить тип урона и вид урона (тип - колющий, вид - огненный)
  }
}
