package Enums;

public enum ETipo {
    FUEGO(50),
    PLANTA(40),
    ROCA(45),
    AGUA(50),
    ELECTRICO(45);

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
                if (defensor == AGUA || defensor == ROCA) return 0.5;
                break;

            case PLANTA:
                if (defensor == AGUA || defensor == ROCA) return 2.0;
                if (defensor == FUEGO) return 0.5;
                break;

            case ROCA:
                if (defensor == FUEGO || defensor == ELECTRICO) return 2.0;
                if (defensor == AGUA || defensor == PLANTA) return 0.5;
                break;

            case AGUA:
                if (defensor == FUEGO || defensor == ROCA) return 2.0;
                if (defensor == PLANTA || defensor == AGUA) return 0.5;
                break;

            case ELECTRICO:
                if (defensor == AGUA) return 2.0;
                if (defensor == ROCA || defensor == ELECTRICO) return 0.5;
                break;
        }
        return 1.0; // daño neutro
    }
}

