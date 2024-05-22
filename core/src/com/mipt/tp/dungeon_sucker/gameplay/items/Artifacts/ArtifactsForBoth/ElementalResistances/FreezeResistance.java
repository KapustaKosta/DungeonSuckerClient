package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForBoth.ElementalResistances;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class FreezeResistance extends Artifact {
    public FreezeResistance() {
        super();
        this.id = 4;
        this.triggerableByBeingDamaged = true;
        this.weight = 5;
        this.effectiveness = 3;
        this.description = "Fire damage is twice less effective";
        this.name = "Frost Resistance";
    }

    public void triggerByBeingDamaged(Damage damage) {
        if (damage.element == ElementSet.Freeze) {
            damage.elementDamage /= 2;
            damage.totalDamage = damage.defaultDamage + damage.elementDamage;
        }
    }
}
