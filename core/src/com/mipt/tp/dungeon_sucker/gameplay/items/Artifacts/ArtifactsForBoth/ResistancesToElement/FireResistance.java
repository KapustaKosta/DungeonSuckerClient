package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForBoth.ResistancesToElement;

import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.ArtifactTypes.ResistanceToElement;

public class FireResistance extends ResistanceToElement {
    public FireResistance() {
        super(ElementSet.Fire);
        this.triggerableByBeingDamaged = true;
        this.weight = 5;
        this.effectiveness = 3;
        this.description = "Fire damage is twice less effective";
        this.name = "Fire Resistance";
    }
}
