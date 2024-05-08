package com.mipt.tp.dungeon_sucker.gameplay.items.ArtifactTypes;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

public abstract class ArtifactTriggerableByBeingDamaged extends Artifact {

    @Override
    public abstract void triggerByBeingDamaged(Damage damage);

    @Override
    public void triggerByDeath() {

    }

    @Override
    public void triggerByEnteringRoom(Room room) {

    }
}
