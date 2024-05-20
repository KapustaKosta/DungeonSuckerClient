package com.mipt.tp.dungeon_sucker.gameplay.generators;

import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.weaponGenerators.*;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;

import java.util.Random;

public class WeaponGenerator {
    final int AmountOfWeaponTypesInGame = 8;
    // ШАНСЫ ВЫПАДЕНИЯ РЕДКОСТЕЙ:
    // Poor - 20%
    // Common - 45%
    // Uncommon - 20%
    // Rare - 10%
    // Epic - 4.5%
    // Legendary - 0.5%

    public Weapon generateWeapon(int level) {
        int WhatToCreate = new Random().nextInt(AmountOfWeaponTypesInGame);
        ElementSet element = ElementSet.values()[new Random().nextInt(ElementSet.values().length)];
        RaritySet rarity = generateRarityFromID(generateRarityID());
        level = level + new Random().nextInt(3) - 1;
        return generateWeapon(element, WhatToCreate, rarity, level);
    }

    public Weapon generateWeapon(ElementSet element, int WhatToCreate, RaritySet rarity, int level) {
        switch (WhatToCreate) {
            case 0:
                return new BowGenerator().generateBow(rarity, element, level);
            case 1:
                return new ClubGenerator().genetateClub(rarity, element, level);
            case 2:
                return new CrossbowGenerator().generateCrossbow(rarity, element, level);
            case 3:
                return new DaggerGenerator().generateDagger(rarity, element, level);
            case 4:
                return new GreatSwordGenerator().generateGreatSword(rarity, element, level);
            case 5:
                return new RapierGenerator().generateRapier(rarity, element, level);
            case 6:
                return new SpearGenerator().generateSpear(rarity, element, level);
            case 7:
                return new SwordGenerator().generateSword(rarity, element, level);

        }
        throw new IndexOutOfBoundsException();
    }

    private int generateRarityID() {
        int a = new Random().nextInt(200) + 1;
        if (a <= 40) {
            return 0;
        } else if (a <= 130) {
            return 1;
        } else if (a <= 170) {
            return 2;
        } else if (a <= 190) {
            return 3;
        } else if (a <= 199) {
            return 4;
        } else {
            return 5;
        }
    }

    private RaritySet generateRarityFromID(int ID) {
        switch (ID) {
            case 0:
                return RaritySet.Poor;
            case 1:
                return RaritySet.Common;
            case 2:
                return RaritySet.Uncommon;
            case 3:
                return RaritySet.Rare;
            case 4:
                return RaritySet.Epic;
            case 5:
                return RaritySet.Legendary;
        }
        return RaritySet.Legendary;
    }
}
