package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.items.ArtifactTypes.NonTriggerableArtifact;

public class KnightsRing extends NonTriggerableArtifact {

    public KnightsRing() {
        super();
        this.effectiveness = 5;
        this.description = "Makes its holder more strong";
        this.weight = 1;
        this.name = "Knight's ring; weight = " + this.weight;
    }

    public void getObtained(Entity entity) {
        super.getObtained(entity);
        entity.strength += this.effectiveness;
        this.holder = entity;
        try {
            this.holder.recountWeapon();
        } catch (Exception ignored) {
        }
    }


    public void getLost() {
        this.holder.strength -= this.effectiveness;
        super.getLost();
    }
}
