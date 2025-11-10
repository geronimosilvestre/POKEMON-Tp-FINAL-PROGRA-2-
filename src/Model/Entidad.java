package Model;

import java.util.Objects;
import java.util.UUID;

public abstract class Entidad {
    private UUID uuid;

    public Entidad() {
        uuid = UUID.randomUUID();
    }


    protected Entidad(UUID uuid) {
        this.uuid = uuid;
    }


    public UUID getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Entidad entidad)) return false;
        return Objects.equals(uuid, entidad.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }

    @Override
    public String toString() {
        return "Entidad{" +
                "uuid=" + uuid +
                '}';
    }
}
