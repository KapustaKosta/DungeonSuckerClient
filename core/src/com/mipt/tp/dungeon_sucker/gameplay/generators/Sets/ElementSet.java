package com.mipt.tp.dungeon_sucker.gameplay.generators.Sets;

import com.badlogic.gdx.graphics.Texture;
import com.mipt.tp.dungeon_sucker.math.RandomNumGenerator;

public enum ElementSet {
    Fire, Freeze, Poison, Dark, None, Light;

    public static ElementSet getRandom() {
        int randomIndex = RandomNumGenerator.generateFromRange(0, 5);
        return getByID(randomIndex);
    }
    public static ElementSet getByID(int ID){
        switch (ID) {
            case 0: {
                return None;
            }
            case 1: {
                return Dark;
            }
            case 2: {
                return Fire;
            }
            case 3: {
                return Freeze;
            }
            case 4: {
                return Poison;
            }
            case 5: {
                return Light;
            }
        }
        return None;
    }

    public static Texture getTexture(ElementSet elementSet) {
        switch (elementSet) {
            //todo: Переписать названия текстур и перекинуть файлы
            case Fire: {
                return new Texture("fire-elemental.png");
            }
            case Freeze: {
                return new Texture("water-elemental.png");
            }
            case Poison: {
                return new Texture("nature-elemental.png");
            }
            case Dark: {
                return new Texture("stone-elemental.png");
            }
            case Light:
            case None:
                return new Texture("stone-elemental.png");
        }
        return null;
    }

    public static int getID(ElementSet elementSet) {
        switch (elementSet) {
            case None:
                return 0;
            case Dark:
                return 1;
            case Fire:
                return 2;
            case Freeze:
                return 3;
            case Poison:
                return 4;
            case Light:
                return 5;
        }
        return 0;
    }
}
