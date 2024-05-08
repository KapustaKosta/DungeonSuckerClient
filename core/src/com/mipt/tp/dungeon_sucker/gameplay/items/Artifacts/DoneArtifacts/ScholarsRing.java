package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.items.ArtifactTypes.NonTriggerableArtifact;

public class ScholarsRing extends NonTriggerableArtifact {

    public ScholarsRing() {
        super();
        this.description = "Makes its holder more intelligent";
        this.effectiveness = 5;
        this.weight = 1;
        this.name = "Scholar's ring; weight = " + this.weight;
    }

    public void getObtained(Entity entity) {
        super.getObtained(entity);
        entity.intellect += this.effectiveness;
        this.holder = entity;
        try {
            this.holder.recountWeapon();
        } catch (Exception ignored) {
        }
    }


    public void getLost() {
        this.holder.intellect -= this.effectiveness;
        super.getLost();
    }
}
