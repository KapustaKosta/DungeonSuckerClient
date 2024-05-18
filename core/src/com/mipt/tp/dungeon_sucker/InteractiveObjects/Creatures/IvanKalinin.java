package com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures;

import com.badlogic.gdx.graphics.Texture;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creature;
import com.mipt.tp.dungeon_sucker.gameplay.Action;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForEnemies.DeadlineIsComing;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.Random;

public class IvanKalinin extends Creature {

    public IvanKalinin(Room place) {
        super(1000, 1000, 1, true, place, "IVAN KALININ");
        this.weapon = new DeadlineIsComing();
        this.weapon.getObtained(this);
        this.description = "Ultimate reviewer";
        this.texture = new Texture("ivan-kalinin.png");
        this.moveMessage = "YOUR CODESTYLE SUCKS!";
    }

    public void summon() {
        this.strength = Integer.MAX_VALUE;
        this.dexterity = Integer.MAX_VALUE;
        this.intellect = Integer.MAX_VALUE;
    }
}
