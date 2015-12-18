package com.machine.topic.model;

import com.machine.topic.common.Constants;

/**
 * Created by rishabh.tripathi on 12/3/15.
 */
public class EntityMatch {
    private Entity entity;
    private Constants.EntityType type;
    private float relevence;

    public EntityMatch(Entity entity, Constants.EntityType type, float relevence) {
        this.entity = entity;
        this.type = type;
        this.relevence = relevence;
    }

    public EntityMatch() {
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Constants.EntityType getType() {
        return type;
    }

    public void setType(Constants.EntityType type) {
        this.type = type;
    }

    public float getRelevence() {
        return relevence;
    }

    public void setRelevence(float relevence) {
        this.relevence = relevence;
    }
}
