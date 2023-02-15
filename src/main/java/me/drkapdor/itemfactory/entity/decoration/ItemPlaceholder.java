package me.drkapdor.itemfactory.entity.decoration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.util.function.Supplier;

@AllArgsConstructor
@Getter
public class ItemPlaceholder {

    private final String trigger;
    private final String value;

    public String replace(String input) {
        return input.replace(toString(), value);
    }

    @Override
    public String toString() {
        return "{" + trigger + "}";
    }

}
