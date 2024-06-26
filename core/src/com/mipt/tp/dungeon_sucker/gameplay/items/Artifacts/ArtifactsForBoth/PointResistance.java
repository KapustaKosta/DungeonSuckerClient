package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForBoth;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class PointResistance extends Artifact {
    public PointResistance() {
        super();
        this.id = 13;
        this.triggerableByBeingDamaged = true;
        this.weight = 5;
        this.description = "Pointing attacks deal two times less damage";
        this.name = "Point Resistance";
    }

    public void triggerByBeingDamaged(Damage damage) {
        if (damage.type == DamageTypeSet.Point) {
            damage.totalDamage /= 2;
            damage.defaultDamage /= 2;
            damage.elementDamage /= 2;
        }
    }
}
