package com.mipt.tp.dungeon_sucker.gameplay.generators.weaponGenerators;

import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.WeaponTraitsAdder;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForPlayer.Club;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForPlayer.Sword;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SwordGenerator {
  int DEFAULT_DAMAGE = 3;

  public Sword generateSword(RaritySet rarity, ElementSet element, int level) {
    String name = element.name();
    if (element.name().equals("None")) {
      name = "";
    }
    Sword sword = new Sword(level, DEFAULT_DAMAGE, rarity.name() + " " + name + " sword", rarity);
    WeaponTraitsAdder.addTrait(sword);
    return sword;
  }
}
