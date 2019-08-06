package com.business.core.entity;

import org.springframework.data.annotation.Id;

/**
 * 自增实体
 */
public abstract class IncIdEntity<T extends Number> {

    @Id
    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}