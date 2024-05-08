package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForBoth.ResistancesToElement;

import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.ArtifactTypes.ResistanceToElement;

public class PoisonResistance extends ResistanceToElement {
    public PoisonResistance() {
        super(ElementSet.Poison);
        this.triggerableByBeingDamaged = true;
        this.weight = 5;
        this.effectiveness = 3;
        this.description = "Poison damage is twice less effective";
        this.name = "Poison Resistance";
    }
}
