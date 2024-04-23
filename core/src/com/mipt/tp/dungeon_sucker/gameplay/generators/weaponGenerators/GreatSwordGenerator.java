package com.mipt.tp.dungeon_sucker.gameplay.generators.weaponGenerators;

import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.WeaponTraitsAdder;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForBoth.GreatSword;

public class GreatSwordGenerator {
  int DEFAULT_DAMAGE = 5;

  public GreatSword generateGreatSword(RaritySet rarity, ElementSet element, int level) {
    String name = element.name();
    if (element.name().equals("None")) {
      name = "";
    }
    GreatSword greatSword = new GreatSword(level, DEFAULT_DAMAGE, rarity.name() + " " + name + " dagger", rarity);
    WeaponTraitsAdder.addTrait(greatSword);
    return greatSword;
  }
}
