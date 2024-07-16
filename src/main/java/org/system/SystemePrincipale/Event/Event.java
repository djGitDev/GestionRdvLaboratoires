package org.system.SystemePrincipale.Event;

import com.google.gson.JsonObject;

public abstract class Event {
    public enum Type {
        RDV,
        RESULTAT_EXAMEN,
    }

    protected Type type;

    public Event(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

}
