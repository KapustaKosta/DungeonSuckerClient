package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForBoth.Masteries;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.WeaponTypes;
import com.mipt.tp.dungeon_sucker.gameplay.items.Mastery;

public class KnifeMastery extends Mastery {
    public KnifeMastery() {
        super();
        this.id = 7;
        this.upgradable = WeaponTypes.knife;
        this.triggerableByBeingDamaged = false;
        this.weight = 5;
        this.effectiveness = 3;
        this.description = "You feel much better when using knifes and daggers";
        this.name = "Knife mastery";
    }

    @Override
    public void triggerByBeingDamaged(Damage damage) {
        return;
    }
}
