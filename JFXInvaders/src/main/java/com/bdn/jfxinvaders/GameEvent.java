package com.bdn.jfxinvaders;

import javafx.event.Event;
import javafx.event.EventType;

public class GameEvent extends Event {

    public GameEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }


    public static final EventType<GameEvent> GAME_OVER =
            new EventType<>(ANY, "GAME_OVER");
}
