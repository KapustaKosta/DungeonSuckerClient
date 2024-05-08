package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForBoth.ResistancesToElement;

import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.ArtifactTypes.ResistanceToElement;

public class LightResistance extends ResistanceToElement {
    public LightResistance() {
        super(ElementSet.Light);
        this.triggerableByBeingDamaged = true;
        this.weight = 5;
        this.effectiveness = 3;
        this.description = "Light damage is twice less effective";
        this.name = "Light Resistance";
    }
}
