package com.booleanuk.library.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public abstract class Item {
    private boolean available;

    public boolean isAvailable() {
        return available;
    }
}
