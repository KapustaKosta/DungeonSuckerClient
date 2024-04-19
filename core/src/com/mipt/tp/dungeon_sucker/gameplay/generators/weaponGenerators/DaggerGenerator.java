package com.mipt.tp.dungeon_sucker.gameplay.generators.weaponGenerators;

import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForPlayer.Dagger;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForPlayer.Sword;

public class DaggerGenerator {
  int DEFAULT_DAMAGE = 3;

  public Dagger generateDagger(RaritySet rarity, ElementSet element, int level) {
    Dagger dagger = new Dagger(level * DEFAULT_DAMAGE, rarity.name() + " " +  element.name() + " sword", rarity);
    return dagger;
  }
}
