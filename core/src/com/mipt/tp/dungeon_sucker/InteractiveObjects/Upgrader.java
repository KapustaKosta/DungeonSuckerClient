package com.mipt.tp.dungeon_sucker.InteractiveObjects;

import com.badlogic.gdx.graphics.Texture;
import com.mipt.tp.dungeon_sucker.gameplay.level.Level;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class Upgrader extends Entity {
    public Upgrader(IntVector2 position, Texture texture, Level level) {
        super(position, texture, level);
    }

    public Upgrader(int health, int weight, Room place, String name) {
        super(health, weight, place, name);
    }

    public void makeMove() {
        this.weight *= 2;
        this.master.level += 1;
    }
}
