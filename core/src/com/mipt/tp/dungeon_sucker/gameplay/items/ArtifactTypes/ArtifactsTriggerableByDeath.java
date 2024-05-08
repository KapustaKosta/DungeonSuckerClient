package com.mipt.tp.dungeon_sucker.gameplay.items.ArtifactTypes;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

public abstract class ArtifactsTriggerableByDeath extends Artifact {
    @Override
    public void triggerByBeingDamaged(Damage damage) {

    }

    @Override
    public void triggerByEnteringRoom(Room room) {

    }

    @Override
    public abstract void triggerByDeath();
}
