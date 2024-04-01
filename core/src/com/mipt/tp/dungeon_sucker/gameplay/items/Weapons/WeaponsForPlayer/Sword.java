package com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForPlayer;

import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.Skills.PhysicallyDamageOneEntity;
import com.mipt.tp.dungeon_sucker.Skills.PhysicallyDamageOneEntityWithCrit;

public class Sword extends Weapon {
  int damage;

  public Sword(int damage, String name) {
    super(3);
    this.damage = damage;
    this.name = name;
    this.generateSkill(new PhysicallyDamageOneEntity(this, this.damage));
    this.generateSkill(new PhysicallyDamageOneEntityWithCrit(this, this.damage * 3 / 4, 1, 3, 2));
    this.generateSkill(new PhysicallyDamageOneEntity(this, this.damage));
    // Первый скилл - рубящий, второй - колющий. Добавлю, когда обсудим, какие типы урона что делают
    // Когда это обсудим, у всех скиллов надо будет добавить тип урона и вид урона (тип - колющий, вид - огненный)
  }
}
