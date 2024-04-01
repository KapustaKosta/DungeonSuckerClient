package InteractiveObjects;

import com.mipt.tp.dungeon_sucker.gameplay.DungeonMasster;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;
import com.mipt.tp.dungeon_sucker.gameplay.items.Spell;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Level;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes.HauntedRoom;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

import java.util.Random;
import java.util.Scanner;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Character extends Entity {
  Artifact[] artifacts;
  Weapon weapon = null;
  Spell[] spells;
  int spellsAmount;
  int weaponsAmount;
  boolean isFighting;
  String name = "Hero #-1";
  public int weight;
  public int health;

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

  public Character() {
    super(new IntVector2(), null, null);
  }

  public Character(int weight, int health, String name, DungeonMasster DM) {
    super(health, weight, new HauntedRoom(new IntVector2(), new Texture("room.png"), new Creature[0], DM), name);
    this.weight = weight;
    this.health = health;
    this.name = name;
  }

  public Character(IntVector2 position, Texture texture, Level level, int health, int weight) {
    super(position, texture, level);
    this.health = health;
    this.weight = weight;
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
    throw new NotImplementedException();
  }

  private void chooseWeaponToGetRidOf() {
    throw new NotImplementedException();
  }

  public void makeMove() {
    if (this.isFighting) {
      Scanner in = new Scanner(System.in);
      System.out.println("wanna try to escape?");
      System.out.println("1 - YES; 0 - NO");
      int i = in.nextInt();
      if (i == 1) {
        this.escape();
      } else {
        this.attack();
      }
    }
  }

  private void escape() {
    int effect = new Random().nextInt(2);
    if(effect == 1){
      this.moveToRoom(new Room(new IntVector2(), new Texture("room.png"), this.master));
    }
  }

  public void moveToRoom(Room room) {
    if (room.isHaunted) {
      room.insert(this, false);
      this.isFighting = true;
    } else {
      room.insert(this, false);
    }
  }

  public void attack() {
    System.out.println(this.place.getClass());
    this.weapon.use(this.place);
  }

  public void die() {
    System.out.println("Oh no! I, " + this.name + " failed!");
    super.die();
  }
}
