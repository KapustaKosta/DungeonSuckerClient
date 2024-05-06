package com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForEnemies;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.Skills.KillAllEnemies;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;

public class DeadlineIsComing extends Weapon {
    public DeadlineIsComing() {
        super(1);
        this.name = "MERGE REQUEST DENIED!";
    }

    public void getObtained(Entity holder) {
        super.getObtained(holder);
        this.generateSkillForCreature(new KillAllEnemies(this, this.holder.isHostile));
    }
}
