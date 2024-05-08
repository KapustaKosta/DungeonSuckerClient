package com.mipt.tp.dungeon_sucker.InteractiveObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.mipt.tp.dungeon_sucker.UI.Buttons.Button;
import com.mipt.tp.dungeon_sucker.UI.Buttons.ButtonsGroup;
import com.mipt.tp.dungeon_sucker.gameplay.Action;
import com.mipt.tp.dungeon_sucker.gameplay.DungeonMasster;
import com.mipt.tp.dungeon_sucker.gameplay.items.Item;
import com.mipt.tp.dungeon_sucker.gameplay.level.Level;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

import java.util.Random;
import java.util.Scanner;

public class Character extends Entity {
    String name = "Hero #-1";
    private int baseHealth;

    public void getInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            if (location.canIGoTo(levelPosition.y + 1, levelPosition.x)) {
                levelPosition.y += 1;
                updateRealPosition();
            }

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            if (location.canIGoTo(levelPosition.y, levelPosition.x + 1)) {
                levelPosition.x += 1;
                updateRealPosition();
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            if (location.canIGoTo(levelPosition.y, levelPosition.x - 1)) {
                levelPosition.x -= 1;
                updateRealPosition();
            }

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            if (location.canIGoTo(levelPosition.y - 1, levelPosition.x)) {
                levelPosition.y -= 1;
                updateRealPosition();
            }
        }

    }

    public Character() {
        super(new IntVector2(), null, null);
        this.isCharacter = true;
    }

    public Character(int weight, int health, String name, DungeonMasster DM) {
        super(health, weight, new Room(new IntVector2(), new Texture("room.png"), new Creature[0], DM), name);
        this.isCharacter = true;
        this.weight = weight;
        this.health = health;
        this.maxHealth = health;
        this.baseHealth = health;
        this.name = name;
        this.isFighting = false;
    }

    public Character(IntVector2 position, Texture texture, Level level, int health, int weight) {
        super(position, texture, level);
        this.isCharacter = true;
        this.health = health;
        this.isFighting = false;
        this.weight = weight;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void chooseItemToLeave() {
        // todo: Костя, это тоже на тебе, ибо я не знаю, как это будет выглядеть. Перепиши с нуля, аналогично, на все могу ответить.
        // Это с точки зрения бэкенда просто, я добавил все нужные методы. Просто считай инфу и сделай, что хочешь
        int index = 0;
        System.out.println("Choose item to leave (it'll wait you in a rooms chest)");
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
        this.place.addItemToChest(item);
        item.getLost();
        this.weapon.recount();
    }


    public void makeMove() {
        super.makeFictionalMove();
        if (this.isFighting) {
            int i = askIfWantsToEscape();

            ButtonsGroup.getInstance().clear();
            ButtonsGroup.getInstance().addButton(new Button("escape", args -> this.tryToEscape()));
            ButtonsGroup.getInstance().addButton(new Button("escape", args -> this.attack()));

//      if (i == 1) {
//        this.tryToEscape();
//      } else {
//        this.attack();
//      }
        } else {
            int i = askWhatToDoWhenNotFighting();
            if (i == 1) {
                this.attack();
            } else if (i == 2) {
                this.interractWithChest();
            } else {
                this.askToChangeRoom();
            }
        }
        super.makeMove();
    }

    private int askWhatToDoWhenNotFighting() {
        Scanner in = new Scanner(System.in);
        System.out.println("what you wanna do?");
        System.out.println("1 - use some weaponSkill, 2 - open room's chest, 3 - move to another room");
        return in.nextInt();
    }

    private int askIfWantsToEscape() {
        ButtonsGroup.getInstance().addButton(new Button("YES", new Action() {
            @Override
            public void run(int[] args) {

            }
        }));

        Scanner in = new Scanner(System.in);
        System.out.println("wanna try to escape?");
        System.out.println("1 - YES; 0 - NO");
        return in.nextInt();
    }

    public void pushResult(int result) {

    }

    private void interractWithChest() {
        this.place.chest.getInteracted(this);
    }

    private void tryToEscape() {
        int effect = new Random().nextInt(2);
        if (effect == 1) {
            this.askToChangeRoom();
        }
        this.weapon.recount();
    }

    private void askToChangeRoom() {
        //TODO: Костя, сам это пиши, я хз, как ты это реализуешь. Это достаточно просто с точки зрения бэкенда, мне проще объяснить, как это работает, чем понять, как ты это делаешь
    }

    public void moveToRoom(Room room) {
        if (room.isHaunted) {
            room.insert(this, false);
            this.isFighting = true;
        } else {
            room.insert(this, false);
        }
        this.weapon.recount();
    }

    public void levelUp() {
        int a = new Random().nextInt(6);
        switch (a) {
            case 0:
                this.obtainVigor(1);
            case 1:
                this.obtainCarrying(1);
            case 2:
                this.obtainStrength(1);
            case 3:
                this.obtainDexterity(1);
            case 4:
                this.obtainIntellect(1);
            case 5:
                this.obtainFaith(1);
        }
        this.weapon.recount();
    }

    private void obtainFaith(int i) {
        this.faith += i;
    }

    private void obtainIntellect(int i) {
        this.intellect += i;
    }

    private void obtainDexterity(int i) {
        this.dexterity += 1;
    }

    private void obtainStrength(int i) {
        this.strength += 1;
    }

    private void obtainCarrying(int i) {
        this.carrying += 1;
    }

    private void obtainVigor(int i) {
        this.vigor += 1;
        int prevHealth = this.maxHealth;
        this.maxHealth = this.baseHealth * this.vigor;
        this.health = this.health * this.maxHealth / prevHealth;
    }

    public void attack() {
        System.out.println(this.place.getClass());
        this.weapon.use(this.place);
        this.weapon.recount();
    }

    public void die() {
        System.out.println("Oh no! I, " + this.name + " failed!");
        super.die();
    }

    public void obtainExp(int experience) {
        this.experience += experience;
        while (this.experience >= this.experienceToNextLevel) {
            this.experience -= this.experienceToNextLevel;
            this.experienceToNextLevel *= 2;
            this.levelUp();
        }
    }
}
