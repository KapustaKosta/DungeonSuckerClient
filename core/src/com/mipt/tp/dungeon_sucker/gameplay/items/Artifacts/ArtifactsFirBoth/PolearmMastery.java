package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsFirBoth;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.WeaponTypes;
import com.mipt.tp.dungeon_sucker.gameplay.items.Mastery;

public class PolearmMastery extends Mastery {
    public WeaponTypes upgradable = WeaponTypes.polearm;

    public PolearmMastery() {
        super();
        this.upgradable = WeaponTypes.polearm;
        this.triggerableByBeingDamaged = false;
        this.weight = 5;
        this.effectiveness = 3;
        this.description = "You feel much better when using polearm weapons";
        this.name = "Polearm mastery";
    }

    @Override
    public void triggerByBeingDamaged(Damage damage) {
        return;
    }
}
