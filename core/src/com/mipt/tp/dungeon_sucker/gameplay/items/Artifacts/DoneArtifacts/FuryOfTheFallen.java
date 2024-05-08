package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts;

import com.mipt.tp.dungeon_sucker.gameplay.items.ArtifactTypes.ArtifactsTriggerableByDeath;

public class FuryOfTheFallen extends ArtifactsTriggerableByDeath {

    public FuryOfTheFallen() {
        super();
        this.triggerableByDeath = true;
        this.weight = 2;
        this.description = "Makes you respawn after you die, but only once";
        this.name = "Fury Of The Fallen; weight = " + this.weight;
    }

    public void triggerByDeath() {
        System.out.println("I, " + this.holder.name + " RESPAWN");
        if (!this.holder.isAlive) {
            this.holder.isAlive = true;
            this.holder.health = 50;
            this.mustBeRemoved = true;
        }
        // Можно изменить метод использования, но уже и так норм
    }
}
