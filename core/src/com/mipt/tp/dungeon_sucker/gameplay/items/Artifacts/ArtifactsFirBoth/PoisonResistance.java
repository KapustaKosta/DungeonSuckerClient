package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsFirBoth;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class PoisonResistance extends Artifact {
    public PoisonResistance() {
        super();
        this.triggerableByBeingDamaged = true;
        this.weight = 5;
        this.effectiveness = 3;
        this.description = "Poison damage is twice less effective";
        this.name = "Poison Resistance";
    }

    public void triggerByBeingDamaged(Damage damage) {
        if (damage.element == ElementSet.Poison) {
            damage.elementDamage /= 2;
            damage.totalDamage = damage.defaultDamage + damage.elementDamage;
        }
    }
}
