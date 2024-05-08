package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForBoth.ResistancesToElement;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.items.ArtifactTypes.ResistanceToElement;

public class ElementResistance extends ResistanceToElement {
    public ElementResistance() {
        super(null);
        this.triggerableByBeingDamaged = true;
        this.weight = 5;
        this.effectiveness = 3;
        this.description = "All elemental damage is twice less effective";
        this.name = "Elemental Resistance";
    }

    public void triggerByBeingDamaged(Damage damage) {
        damage.elementDamage /= 2;
        damage.totalDamage = damage.defaultDamage + damage.elementDamage;
    }
}
