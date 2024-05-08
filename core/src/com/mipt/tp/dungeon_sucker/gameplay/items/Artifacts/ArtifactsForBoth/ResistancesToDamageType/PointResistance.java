package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForBoth.ResistancesToDamageType;

import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.ArtifactTypes.ResistanceToType;

public class PointResistance extends ResistanceToType {
    public PointResistance() {
        super(DamageTypeSet.Point);
        this.triggerableByBeingDamaged = true;
        this.weight = 5;
        this.description = "Pointing attacks deal two times less damage";
        this.name = "Point Resistance";
    }
}
