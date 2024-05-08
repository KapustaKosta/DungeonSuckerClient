package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForBoth.ResistancesToDamageType;

import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.ArtifactTypes.ResistanceToType;

public class MagicResistance extends ResistanceToType {
    public MagicResistance() {
        super(DamageTypeSet.Magic);
        this.triggerableByBeingDamaged = true;
        this.weight = 5;
        this.description = "Magic attacks deal two times less damage";
        this.name = "Magic Resistance";
    }
}
