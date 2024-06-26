package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts.Rings;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class PriestessRing extends Artifact {

    public PriestessRing() {
        super();
        this.id = 17;
        this.effectiveness = 5;
        this.description = "Makes its holder more faithful";
        this.weight = 1;
        this.name = "Priestess's ring; weight = " + this.weight;
    }

    @Override
    public void triggerByBeingDamaged(Damage damage) {

    }

    public void getObtained(Entity entity) {
        super.getObtained(entity);
        entity.faith += this.effectiveness;
        this.holder = entity;
        try {
            this.holder.recountWeapon();
        } catch (Exception ignored) {
        }
    }


    public void getLost() {
        this.holder.faith -= this.effectiveness;
        super.getLost();
    }
}
