package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForBoth.ResistancesToDamageType;

import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.ArtifactTypes.ResistanceToType;

public class SmashResistance extends ResistanceToType {
    public SmashResistance() {
        super(DamageTypeSet.Smash);
        this.triggerableByBeingDamaged = true;
        this.weight = 5;
        this.description = "Smashing attacks deal two times less damage";
        this.name = "Hard Shell";
    }
}
