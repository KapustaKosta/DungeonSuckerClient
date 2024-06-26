package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForBoth.ElementalResistances;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class FireResistance extends Artifact {
    public FireResistance() {
        super();
        this.id = 1;
        this.triggerableByBeingDamaged = true;
        this.weight = 5;
        this.effectiveness = 3;
        this.description = "Fire damage is twice less effective";
        this.name = "Fire Resistance";
    }

    public void triggerByBeingDamaged(Damage damage) {
        if (damage.element == ElementSet.Fire) {
            damage.elementDamage /= 2;
            damage.totalDamage = damage.defaultDamage + damage.elementDamage;
        }
    }
}
