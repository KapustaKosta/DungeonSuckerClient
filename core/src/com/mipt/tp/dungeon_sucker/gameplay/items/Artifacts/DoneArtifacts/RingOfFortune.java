package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts;

import com.mipt.tp.dungeon_sucker.gameplay.items.ArtifactTypes.NonTriggerableArtifact;

public class RingOfFortune extends NonTriggerableArtifact {
    public RingOfFortune() {
        super();
        this.description = "Makes you much more lucky. Definitely";   //not really
        this.effectiveness = 0; //not a useful line, just a reminder. IT IS USELESS!
        this.weight = 1; // so it's even harmful
        this.name = "Ring of Fortune; weight = " + this.weight;
    }
}
