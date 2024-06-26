package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts.Rings;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class RingOfHealth extends Artifact {

    public RingOfHealth() {
        super();
        this.id = 18;
        this.effectiveness = 10;
        this.description = "Makes its holder more survivable";
        this.weight = 1;
        this.name = "Ring of Health; weight = " + this.weight;
    }

    @Override
    public void triggerByBeingDamaged(Damage damage) {

    }

    public void getObtained(Entity entity) {
        super.getObtained(entity);
        this.holder.maxHealth += this.effectiveness;
        this.holder.health += this.effectiveness;
        try {
            this.holder.recountWeapon();
        } catch (Exception ignored) {
        }
    }


    public void getLost() {
        this.holder.decreaseMaxHP(this.effectiveness);
        super.getLost();
    }
}
