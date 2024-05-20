package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForBoth;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class HardShell extends Artifact {
    public HardShell() {
        super();
        this.id = 11;
        this.triggerableByBeingDamaged = true;
        this.weight = 5;
        this.description = "Smashing attacks deal two times less damage";
        this.name = "Hard Shell";
    }

    public void triggerByBeingDamaged(Damage damage) {
        if (damage.type == DamageTypeSet.Smash) {
            damage.totalDamage /= 2;
            damage.defaultDamage /= 2;
            damage.elementDamage /= 2;
        }
    }
}
