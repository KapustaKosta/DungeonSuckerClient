package com.mipt.tp.dungeon_sucker.gameplay.generators.weaponGenerators;

import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.WeaponTraitsAdder;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForBoth.Spear;

public class SpearGenerator {
  int DEFAULT_DAMAGE = 3;

  public Spear generateSpear(RaritySet rarity, ElementSet element, int level) {
    String name = element.name();
    if (element.name().equals("None")) {
      name = "";
    }
    Spear spear = new Spear(level, DEFAULT_DAMAGE, rarity.name() + " " + name + " dagger", rarity);
    WeaponTraitsAdder.addTrait(spear);
    return spear;
  }
}
