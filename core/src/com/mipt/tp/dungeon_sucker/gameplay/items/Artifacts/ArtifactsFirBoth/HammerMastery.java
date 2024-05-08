package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsFirBoth;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.WeaponTypes;
import com.mipt.tp.dungeon_sucker.gameplay.items.Mastery;

public class HammerMastery extends Mastery {
    public HammerMastery() {
        super();
        this.upgradable = WeaponTypes.hammer;
        this.triggerableByBeingDamaged = false;
        this.weight = 5;
        this.effectiveness = 3;
        this.description = "You feel much better when using hammers";
        this.name = "Hammer mastery";
    }

    @Override
    public void triggerByBeingDamaged(Damage damage) {
        return;
    }
}
