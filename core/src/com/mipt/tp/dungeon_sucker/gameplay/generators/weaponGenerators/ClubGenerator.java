package com.mipt.tp.dungeon_sucker.gameplay.generators.weaponGenerators;

import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForPlayer.Club;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ClubGenerator {
  static final int DEFAULT_DAMAGE = 3;
  public Club genetateClub(RaritySet rarity, ElementSet element, int level) {
    Club club = new Club(level * DEFAULT_DAMAGE, rarity.name() + " " +  element.name() + " club", rarity);
    return club;
  }
}
