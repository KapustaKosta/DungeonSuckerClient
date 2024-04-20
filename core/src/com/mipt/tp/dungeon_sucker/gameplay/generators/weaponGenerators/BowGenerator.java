package com.mipt.tp.dungeon_sucker.gameplay.generators.weaponGenerators;

import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.WeaponTraitsAdder;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForPlayer.Bow;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForPlayer.Sword;

public class BowGenerator {
  int DEFAULT_DAMAGE = 3;

  public Bow generateBow(RaritySet rarity, ElementSet element, int level) {
    String name = element.name();
    if (element.name().equals("None")) {
      name = "";
    }
    Bow bow = new Bow(level, DEFAULT_DAMAGE, rarity.name() + " " + name + " sword", rarity);
    WeaponTraitsAdder.addTrait(bow);
    return bow;
  }
}
