package com.mipt.tp.dungeon_sucker.gameplay.generators;

import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForBoth.ElementalResistances.*;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForBoth.HardShell;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForBoth.MagicResistance;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForBoth.Masteries.*;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForBoth.PointResistance;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForBoth.ThornsOfRevenge;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts.FuryOfTheFallen;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts.IronChestplate;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts.Rings.*;

import java.util.Random;

public class ArtifactGenerator {
    static final int NUMBER_OF_ARTIFACTS = 19;

    public Artifact generateArtifact() {
        return generateArtifact(new Random().nextInt(NUMBER_OF_ARTIFACTS));
    }

    public static Artifact generateArtifact(int id) {
        switch (id) {
            case 0:
                return new DarkResistance();
            case 1:
                return new FireResistance();
            case 2:
                return new LightResistance();
            case 3:
                return new PoisonResistance();
            case 4:
                return new FreezeResistance();
            case 5:
                return new ElementResistance();
            case 6:
                return new HammerMastery();
            case 7:
                return new KnifeMastery();
            case 8:
                return new PolearmMastery();
            case 9:
                return new RangedMastery();
            case 10:
                return new SwordMastery();
            case 11:
                return new HardShell();
            case 12:
                return new MagicResistance();
            case 13:
                return new PointResistance();
            case 14:
                return new ThornsOfRevenge();
            case 15:
                return new HuntersRing();
            case 16:
                return new KnightsRing();
            case 17:
                return new PriestessRing();
            case 18:
                return new RingOfHealth();
            case 19:
                return new ScholarsRing();
            case 20:
                return new RingOfFortune();
            case 21:
                return new FuryOfTheFallen();
            case 22:
                return new IronChestplate();
        }
        throw new RuntimeException("You wasn't supposed to see that, contact us and we'll change some code with Artifact Generation");
    }
}
