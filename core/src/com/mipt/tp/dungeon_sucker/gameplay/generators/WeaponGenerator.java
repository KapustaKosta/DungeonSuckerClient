package com.mipt.tp.dungeon_sucker.gameplay.generators;

import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.weaponGenerators.ClubGenerator;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;

import java.util.Random;

public class WeaponGenerator {
  final int AmountOfWeaponTypesInGame = 1;
  ElementSet elementSet = new ElementSet();
  RaritySet raritySet = new RaritySet();
  public Weapon generateWeapon(int level){
    int WhatToCreate = new Random().nextInt(AmountOfWeaponTypesInGame);
    String element = elementSet.generate();
    String rarity = raritySet.generate();
    level = level + new Random().nextInt(3) - 1;
    switch(WhatToCreate){
      case 0: return new ClubGenerator().genetateClub(rarity, element, level);
    }
    throw new IndexOutOfBoundsException();
  }
}
