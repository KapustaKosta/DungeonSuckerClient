package com.mipt.tp.dungeon_sucker.gameplay.items.ArtifactTypes;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;

public abstract class ResistanceToType extends ArtifactTriggerableByBeingDamaged {
    protected final DamageTypeSet RESISTANCE_TYPE;

    public ResistanceToType(DamageTypeSet resistanceType) {
        this.RESISTANCE_TYPE = resistanceType;
    }

    @Override
    public void triggerByBeingDamaged(Damage damage) {
        if (damage.type == this.RESISTANCE_TYPE) {
            damage.totalDamage /= 2;
            damage.defaultDamage /= 2;
            damage.elementDamage /= 2;
        }
    }
}
