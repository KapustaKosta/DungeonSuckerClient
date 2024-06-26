package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts.Rings;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class KnightsRing extends Artifact {

    public KnightsRing() {
        super();
        this.id = 16;
        this.effectiveness = 5;
        this.description = "Makes its holder more strong";
        this.weight = 1;
        this.name = "Knight's ring; weight = " + this.weight;
    }


    @Override
    public void triggerByBeingDamaged(Damage damage) {

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
