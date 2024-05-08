package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForBoth.Masteries;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.WeaponTypes;
import com.mipt.tp.dungeon_sucker.gameplay.items.Mastery;

public class SwordMastery extends Mastery {
    public SwordMastery() {
        super(WeaponTypes.sword);
        this.triggerableByBeingDamaged = false;
        this.weight = 5;
        this.effectiveness = 3;
        this.description = "You feel much better when using sword";
        this.name = "Sword mastery";
    }

    @Override
    public void triggerByBeingDamaged(Damage damage) {
    }
}
