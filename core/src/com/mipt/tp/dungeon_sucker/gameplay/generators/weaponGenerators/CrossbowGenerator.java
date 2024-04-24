package com.mipt.tp.dungeon_sucker.gameplay.generators.weaponGenerators;

import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.WeaponTraitsAdder;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForBoth.Crossbow;

public class CrossbowGenerator {
  int DEFAULT_DAMAGE = 3;

  public Crossbow generateCrossbow(RaritySet rarity, ElementSet element, int level) {
    String name = element.name();
    if (element.name().equals("None")) {
      name = "";
    }
    Crossbow crossbow = new Crossbow(level, DEFAULT_DAMAGE, rarity.name() + " " + name + " sword", rarity);
    WeaponTraitsAdder.addTrait(crossbow);
    return crossbow;
  }
}
