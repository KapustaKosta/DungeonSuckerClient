package InteractiveObjects;

import com.mipt.tp.dungeon_sucker.gameplay.DungeonMasster;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.mipt.tp.dungeon_sucker.gameplay.items.Item;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Level;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes.HauntedRoom;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

import java.util.Random;
import java.util.Scanner;

public class Character extends Entity {
  Weapon weapon = null;
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

  public void addItem(Item item) {
    this.items.add(item);
  }

  public void chooseItemToLeave() {
    int index = 0;
    System.out.println("Choose item to leave (it'll wait you in a rooms chest)"); // not implemented yet, it'll disappear
    Scanner in = new Scanner(System.in);
    int chosen = -1;
    for (int i = 0; i < this.items.size() - 5; i = index) {
      for (int j = 0; j < 5; ++j) {
        System.out.printf((j + 1) + " - " + this.items.get(index).name);
        ++index;
        System.out.println();
      }
      String input = in.nextLine();
      try {
        chosen = Integer.parseInt(input) + i - 1;
        break;
      } catch (Exception ignored) {
        continue;
      }
    }
    if (chosen > -1) {
      for (; index < this.items.size(); ++index) {
        System.out.printf((index % 5 + 1) + " - " + this.items.get(index).name);
        System.out.println();
      }
      String input = in.nextLine();
      try {
        chosen = Integer.parseInt(input) + (this.items.size() - 1) / 5 * 5;
      } catch (Exception ignored) {
        System.out.println("You weren't supposed to type that");
      }
    }
    Item item = items.get(chosen);
    item.holder = null;
    this.place.addItemToChest(item);
    this.items.remove(chosen);
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
    if (effect == 1) {
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
