package com.mipt.tp.dungeon_sucker.gameplay.items;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.WeaponTypes;

public class Mastery extends Artifact {
    public Mastery(WeaponTypes weaponTypes) {
        super();
        this.upgradable = weaponTypes;
    }

    @Override
    public void triggerByBeingDamaged(Damage damage) {
    }
}
