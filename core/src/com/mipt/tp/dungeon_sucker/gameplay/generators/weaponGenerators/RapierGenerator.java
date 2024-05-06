package com.mipt.tp.dungeon_sucker.gameplay.generators.weaponGenerators;

import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.WeaponTraitsAdder;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForBoth.Rapier;

public class RapierGenerator {
    int DEFAULT_DAMAGE = 3;

    public Rapier generateRapier(RaritySet rarity, ElementSet element, int level) {
        String name = element.name();
        if (element.name().equals("None")) {
            name = "";
        }
        Rapier rapier = new Rapier(level, DEFAULT_DAMAGE, rarity.name() + " " + name + " rapier", rarity);
        WeaponTraitsAdder.addTrait(rapier);
        return rapier;
    }
}
