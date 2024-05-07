package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForEnemies;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures.Slime;
import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class Split extends Artifact {
    Slime slimeHolder;

    public Split() {
        super();
        this.triggerableByDeath = true;
        this.weight = 3;
        this.effectiveness = 2;
        this.description = "upon death summons more Slimes smaller than its holder";
        this.name = "Split";
    }

    public void getObtained(Slime slime) {
        super.getObtained(slime);
        this.slimeHolder = slime;
    }

    @Override
    public void triggerByBeingDamaged(Damage damage) {
    }

    public void triggerByDeath() {
        if (this.slimeHolder.slimeLevel > 0) {
            for (int i = 0; i < this.effectiveness; ++i) {
                this.slimeHolder.place.insert(new Slime(this.slimeHolder.isHostile,
                        this.slimeHolder.place, "slime", this.slimeHolder.slimeLevel - 1), this.slimeHolder.isHostile);
            }
        }
    }
}
