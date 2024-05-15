package com.mipt.tp.dungeon_sucker.gameplay.generators.Sets;

import com.badlogic.gdx.graphics.Texture;
import com.mipt.tp.dungeon_sucker.UI.text.Text;
import com.mipt.tp.dungeon_sucker.math.RandomNumGenerator;

public enum ElementSet {
    Fire, Water, Nature, Stone, None;
    public static ElementSet getRandom()
    {
        int randomIndex = RandomNumGenerator.generateFromRange(0, 3);
        switch (randomIndex)
        {
            case 0:
            {
                return Fire;
            }
            case 1:
            {
                return Water;
            }
            case 2:
            {
                return Nature;
            }
            case 3:
            {
                return Stone;
            }
        }
        return None;
    }

    public static Texture getTexture(ElementSet elementSet)
    {
        switch (elementSet)
        {
            case Fire:
            {
                return new Texture("fire-elemental.png");
            }
            case Water:
            {
                return new Texture("water-elemental.png");
            }
            case Nature:
            {
                return new Texture("nature-elemental.png");
            }
            case Stone:
            {
                return new Texture("stone-elemental.png");
            }
        }
        return null;
    }
}
