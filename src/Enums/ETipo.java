package Enums;

public enum ETipo {
    FUEGO(50),
    PLANTA(40),
    ROCA(45),
    AGUA(50),
    ELECTRICO(45),
    HIELO(40);

    private final int poderBase;

    ETipo(int poderBase) {
        this.poderBase = poderBase;
    }

    public int getPoderBase() {
        return poderBase;
    }

    // Daño según tipo del atacante y del defensor
    public double calcularEfectividad(ETipo defensor) {
        switch (this) {
            case FUEGO:
                if (defensor == PLANTA) return 2.0;
                if (defensor == AGUA) return 0.5;
                break;

            case AGUA:
                if (defensor == ROCA) return 2.0;
                if (defensor == ELECTRICO) return 0.5;
                break;

            case PLANTA:
                if (defensor == AGUA) return 2.0;
                if (defensor == FUEGO) return 0.5;
                break;

            case ROCA:
                if (defensor == HIELO) return 2.0;
                if (defensor == PLANTA) return 0.5;
                break;

            case ELECTRICO:
                if (defensor == AGUA) return 2.0;
                if (defensor == ROCA) return 0.5;
                break;

            case HIELO:
                if (defensor == FUEGO) return 2.0;
                if (defensor == ROCA) return 0.5;
                break;
        }
        return 1.0;
    }
}


