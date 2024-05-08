package com.mipt.tp.dungeon_sucker.gameplay.items.ArtifactTypes;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;

public abstract class ResistanceToElement extends ArtifactTriggerableByBeingDamaged {
    protected final ElementSet RESISTANCE_TYPE;

    public ResistanceToElement(ElementSet resistanceType) {
        this.RESISTANCE_TYPE = resistanceType;
    }

    @Override
    public void triggerByBeingDamaged(Damage damage) {
        if (damage.element == this.RESISTANCE_TYPE) {
            damage.elementDamage /= 2;
            damage.totalDamage = damage.defaultDamage + damage.elementDamage;
        }
    }
}
