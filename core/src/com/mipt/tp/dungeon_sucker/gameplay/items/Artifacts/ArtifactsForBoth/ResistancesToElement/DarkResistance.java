package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForBoth.ResistancesToElement;

import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.ArtifactTypes.ResistanceToElement;

public class DarkResistance extends ResistanceToElement {
    public DarkResistance() {
        super(ElementSet.Dark);
        this.triggerableByBeingDamaged = true;
        this.weight = 5;
        this.effectiveness = 3;
        this.description = "Dark damage is twice less effective";
        this.name = "Dark Resistance";
    }
}
