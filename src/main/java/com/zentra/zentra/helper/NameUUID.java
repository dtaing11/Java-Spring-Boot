package com.zentra.zentra.helper;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Setter
public class NameUUID {

    private String name;
    private UUID uuid;


    @Override
    public String toString() {
        return "NameUUID{name='" + name + "', uuid=" + uuid + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NameUUID other)) return false;
        return Objects.equals(name, other.name) && Objects.equals(uuid, other.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, uuid);
    }
}
