package com.mipt.tp.dungeon_sucker.gameplay.generators.weaponGenerators;

import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.WeaponTraitsAdder;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForBoth.Dagger;

public class DaggerGenerator {
    int DEFAULT_DAMAGE = 3;

    public Dagger generateDagger(RaritySet rarity, ElementSet element, int level) {
        String name = element.name();
        if (element.name().equals("None")) {
            name = "";
        }
        Dagger dagger = new Dagger(level, DEFAULT_DAMAGE, rarity.name() + " " + name + " dagger", rarity);
        WeaponTraitsAdder.addTrait(dagger);
        return dagger;
    }
}
