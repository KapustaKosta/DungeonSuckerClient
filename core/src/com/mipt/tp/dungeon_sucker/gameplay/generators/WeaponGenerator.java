package com.mipt.tp.dungeon_sucker.gameplay.generators;

import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.weaponGenerators.BowGenerator;
import com.mipt.tp.dungeon_sucker.gameplay.generators.weaponGenerators.ClubGenerator;
import com.mipt.tp.dungeon_sucker.gameplay.generators.weaponGenerators.DaggerGenerator;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.generators.weaponGenerators.SwordGenerator;

import java.util.Random;

public class WeaponGenerator {
  final int AmountOfWeaponTypesInGame = 4;
  // ШАНСЫ ВЫПАДЕНИЯ РЕДКОСТЕЙ:
  // Poor - 20%
  // Common - 45%
  // Uncommon - 20%
  // Rare - 10%
  // Epic - 4.5%
  // Legendary - 0.5%

  public Weapon generateWeapon(int level){
    int WhatToCreate = new Random().nextInt(AmountOfWeaponTypesInGame);
    ElementSet element = ElementSet.values()[new Random().nextInt(ElementSet.values().length)];
    RaritySet rarity = generateRarity();
    level = level + new Random().nextInt(3) - 1;
    switch(WhatToCreate){
      case 0: return new ClubGenerator().genetateClub(rarity, element, level);
      case 1: return new SwordGenerator().generateSword(rarity, element, level);
      case 2: return new BowGenerator().generateBow(rarity, element, level);
      case 3: return new DaggerGenerator().generateDagger(rarity, element, level);
    }
    throw new IndexOutOfBoundsException();
  }

  private RaritySet generateRarity() {
    int a = new Random().nextInt(200) + 1;
    if(a <= 40){
      return RaritySet.Poor;
    }else if(a <= 130){
      return RaritySet.Common;
    }else if(a <= 170){
      return RaritySet.Uncommon;
    }else if(a <= 190){
      return RaritySet.Rare;
    }else if(a <= 199){
      return RaritySet.Epic;
    }else{
      return RaritySet.Legendary;
    }
  }
}
