package com.horbatenko.testtask.model;

import lombok.Data;

import java.util.Objects;

@Data
public abstract class AbstractBaseEntity implements HasId {

    protected Integer id;

    protected AbstractBaseEntity() {
    }

    protected AbstractBaseEntity(Integer id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return String.format("Entity %s (%s)", getClass().getName(), id);
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractBaseEntity that = (AbstractBaseEntity) obj;
        return id != null && id.equals(that.id);
    }

}
