package com.mipt.tp.dungeon_sucker.InteractiveObjects;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures.Mimic;
import com.mipt.tp.dungeon_sucker.UI.Buttons.Button;
import com.mipt.tp.dungeon_sucker.UI.Buttons.ButtonsGroup;
import com.mipt.tp.dungeon_sucker.gameplay.Action;
import com.mipt.tp.dungeon_sucker.gameplay.generators.ItemGenerator;
import com.mipt.tp.dungeon_sucker.gameplay.items.Item;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Chest extends InteractiveObject {

    public LinkedList<Item> inventory;
    public Room room;
    private boolean isMimic = false;

    public Chest(int numberOfItems, Room room) {
        this.room = room;
        this.inventory = new LinkedList<>();
        for (int i = 0; i < numberOfItems; ++i) {
            this.inventory.addLast(new ItemGenerator().generateItem(this.room.level));
        }
        int willItBeMimic = new Random().nextInt(50);
        if (willItBeMimic == 0) {
            this.isMimic = true;
        }
        this.description = "chest, full of precious items (or not)\n Contains:\n";
    }

    public void add(Item item) {
        this.inventory.addLast(item);
        this.description = this.description + item.name + " \n";
    }

    public void extractItem(int index) {
        this.inventory.remove(index);
    }

    public void getInteracted(Character player, Action doAfterMove) {
        super.getInteracted(player);
        if (isMimic) {
            this.room.insert(new Mimic(this, true), true);
            this.room.chest = new Chest(this.inventory.size() + 2, this.room);
            return;
        }

        ButtonsGroup.getInstance().clear();
        ButtonsGroup.getInstance().addButton(new Button("Take something", args -> {
            ButtonsGroup.getInstance().clear();
            ButtonsGroup.getInstance().addButton(new Button("nothing, I'm just watching", args1 -> {
                doAfterMove.run();
            }));

            for (int k = 0; k < this.inventory.size(); ++k) {
                final int i = k;
                ButtonsGroup.getInstance().addButton(new Button(this.inventory.get(k).name, args1 -> {
                    int index = Math.min(i - 1, this.inventory.size() - 1);
                    this.inventory.get(index).getObtained(player);
                    this.extractItem(index);
                }));
            }
        }));

        ButtonsGroup.getInstance().addButton(new Button("Don't take", args -> {
            doAfterMove.run();
        }));
    }
}
