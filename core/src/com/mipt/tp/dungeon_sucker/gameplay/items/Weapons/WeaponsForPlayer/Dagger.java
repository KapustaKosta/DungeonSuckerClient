package com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForPlayer;

import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.DamageOneEntity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.DamageOneEntityWithCrit;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.DamageThreeEntities;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.Scanner;

public class Dagger extends Weapon {
  public Dagger(int damage, String name, RaritySet rarity) {
    super(3);
    this.power = damage;
    this.name = name;
    this.dexterityScale = 0.5;
    this.rarity = rarity;
    this.recountScales();
    this.weight = 5;
    // public DamageOneEntity(Weapon weapon, int damage, String type, String element, boolean isMelee, double percentOfElementDamage)
    //public DamageThreeEntities(Weapon weapon, int damage, String type, String element, boolean isMelee, double percentOfElementDamage, double firstCoefficient, double secondCoefficient, double thirdCoefficient) {
    this.generateSkill(new DamageOneEntity(this, this.power, DamageTypeSet.Point, this.element, true, 0.3));
    this.generateSkill(new DamageOneEntityWithCrit(this, this.power * 3 / 4, DamageTypeSet.Point, this.element, true, 0.25, 1, 3, 2));
    this.generateSkill(new DamageThreeEntities(this, this.power, DamageTypeSet.Slash, this.element, true, 0.3, 0.75, 0.5, 0.75));
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
      this.strengthScale = 1;
      this.power = this.power * 3 / 2;
      this.weight = this.weight * 3 / 2;
    }
  }

  public void use(Room room) {
    this.recount();
    if (room.isHaunted) {
      System.out.println("Choose your skill");
      for (int i = 0; i < this.skills.length; ++i) {
        System.out.println(i + 1 + ": " + skills[i].toString());
      }
    }
    Scanner in = new Scanner(System.in);
    int index = in.nextInt() - 1;
    System.out.println(this.skills[index].getClass());
    this.skills[index].use(room);
  }
}
