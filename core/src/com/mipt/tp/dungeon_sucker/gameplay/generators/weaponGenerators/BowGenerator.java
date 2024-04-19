package com.mipt.tp.dungeon_sucker.gameplay.generators.weaponGenerators;

import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForPlayer.Bow;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForPlayer.Sword;

public class BowGenerator {
  int DEFAULT_DAMAGE = 3;

  public Bow generateBow(RaritySet rarity, ElementSet element, int level) {
    Bow bow = new Bow(level * DEFAULT_DAMAGE, rarity.name() + " " +  element.name() + " sword", rarity);
    return bow;
  }
}
