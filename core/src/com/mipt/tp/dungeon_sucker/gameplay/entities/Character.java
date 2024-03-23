package com.mipt.tp.dungeon_sucker.gameplay.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artefact;
import com.mipt.tp.dungeon_sucker.gameplay.items.Spell;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Level;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.math.IntVector2;
import java.util.Objects;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Character extends Entity {
    Artefact[] artefacts;
    Weapon weapon = null;
    Spell[] spells;
    int spellsAmount;
    int weaponsAmount;
    boolean isFighting;
    String name = "Hero #-1";
    public int weight = 3;
    public int health = 9999;

    public void getInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            if (level.canIGoTo(levelPosition.y + 1, levelPosition.x)) {
                levelPosition.y += 1;
                updateRealPosition();
            }

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            if (level.canIGoTo(levelPosition.y, levelPosition.x + 1)) {
                levelPosition.x += 1;
                updateRealPosition();
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            if (level.canIGoTo(levelPosition.y, levelPosition.x - 1)) {
                levelPosition.x -= 1;
                updateRealPosition();
            }

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            if (level.canIGoTo(levelPosition.y - 1, levelPosition.x)) {
                levelPosition.y -= 1;
                updateRealPosition();
            }
        }

    }

    public Character()
    {
        super(new IntVector2(), null, null);

    }

    public Character(IntVector2 position, Texture texture, Level level) {
        super(position, texture, level);
    }

    public void addWeapon(Weapon weapon) {
        if (this.weapon == null) {
            this.weapon = weapon;
        } else {
            this.chooseWeaponToGetRidOf();
            this.addWeapon(weapon);
        }
    }

    private void chooseMagicToGetRidOf() {
        throw  new NotImplementedException();
    }

    private void chooseWeaponToGetRidOf() {
    }

    public void makeMove() {
        if (this.isFighting) {
            this.attack();
        }
    }

    public void moveToRoom(Room room) {
        if (room.isHaunted) {
            room.insert(this, false);
            this.isFighting = true;
        }
        room.insert(this, false);
    }

    public void attack() {
        System.out.println(this.place.getClass());
        this.weapon.use(this.place);
    }

    public void die() {
        System.out.println("Oh no! I, " + this.name + " failed!");
        this.isAlive = false;
    }

    public void getDamaged(int damage, String type) {
        if (Objects.equals(type, "Magic")) {
            damage = Math.max(0, damage - magicalArmor);
        } else {
            damage = Math.max(0, damage - physicalArmor);
        }
        this.health -= damage;
        System.out.println(this.name + " got damaged. Current health: " + this.health);
        if (this.health <= 0) {
            this.die();
        }
    }
}
