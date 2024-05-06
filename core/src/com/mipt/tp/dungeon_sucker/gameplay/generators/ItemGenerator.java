package com.mipt.tp.dungeon_sucker.gameplay.generators;

import com.mipt.tp.dungeon_sucker.gameplay.items.Item;

import java.util.Random;

public class ItemGenerator {
    public Item generateItem(int level) {
        int a = new Random().nextInt(5);
        if (a == 4) {
            return new WeaponGenerator().generateWeapon(level);
        }
        return new ArtifactGenerator().generateArtifact();
    }
}
