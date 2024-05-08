package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForBoth.Masteries;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.WeaponTypes;
import com.mipt.tp.dungeon_sucker.gameplay.items.Mastery;

public class PolearmMastery extends Mastery {
    public PolearmMastery() {
        super(WeaponTypes.polearm);
        this.triggerableByBeingDamaged = false;
        this.weight = 5;
        this.effectiveness = 3;
        this.description = "You feel much better when using polearm weapons";
        this.name = "Polearm mastery";
    }

    @Override
    public void triggerByBeingDamaged(Damage damage) {
    }
}
