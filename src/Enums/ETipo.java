package Enums;

public enum ETipo {
    Bicho,
    Dragón,
    Eléctrico,
    Lucha,
    Fuego,
    Volador,
    Fantasma,
    Planta,
    Tierra,
    Hielo,
    Normal,
    Veneno,
    Psíquico,
    Roca,
    Agua;

    public double calcularEfectividad(ETipo defensor) {

        switch (this) {
            case Bicho:
                if (defensor == Planta || defensor == Psíquico) return 2.0;
                if (defensor == Fuego || defensor == Lucha || defensor == Volador || defensor == Fantasma) return 0.5;
                break;

            case Dragón:
                if (defensor == Dragón) return 2.0;
                break;

            case Eléctrico:
                if (defensor == Agua || defensor == Volador) return 2.0;
                if (defensor == Planta || defensor == Eléctrico || defensor == Dragón) return 0.5;
                if (defensor == Tierra) return 0.0;
                break;

            case Lucha:
                if (defensor == Normal || defensor == Roca || defensor == Hielo) return 2.0;
                if (defensor == Veneno || defensor == Volador || defensor == Psíquico || defensor == Bicho) return 0.5;
                if (defensor == Fantasma) return 0.0;
                break;

            case Fuego:
                if (defensor == Planta || defensor == Hielo || defensor == Bicho) return 2.0;
                if (defensor == Fuego || defensor == Agua || defensor == Roca || defensor == Dragón) return 0.5;
                break;

            case Volador:
                if (defensor == Planta || defensor == Lucha || defensor == Bicho) return 2.0;
                if (defensor == Eléctrico || defensor == Roca) return 0.5;
                break;

            case Fantasma:
                if (defensor == Fantasma) return 2.0;
                if (defensor == Normal || defensor == Psíquico) return 0.0;
                break;

            case Planta:
                if (defensor == Agua || defensor == Tierra || defensor == Roca) return 2.0;
                if (defensor == Fuego || defensor == Planta || defensor == Veneno || defensor == Volador || defensor == Bicho || defensor == Dragón) return 0.5;
                break;

            case Tierra:
                if (defensor == Fuego || defensor == Eléctrico || defensor == Veneno || defensor == Roca) return 2.0;
                if (defensor == Planta || defensor == Bicho) return 0.5;
                if (defensor == Volador) return 0.0;
                break;

            case Hielo:
                if (defensor == Planta || defensor == Tierra || defensor == Volador || defensor == Dragón) return 2.0;
                if (defensor == Fuego || defensor == Agua || defensor == Hielo) return 0.5;
                break;

            case Normal:
                if (defensor == Roca) return 0.5;
                if (defensor == Fantasma) return 0.0;
                break;

            case Veneno:
                if (defensor == Planta) return 2.0;
                if (defensor == Veneno || defensor == Tierra || defensor == Roca || defensor == Fantasma) return 0.5;
                break;

            case Psíquico:
                if (defensor == Lucha || defensor == Veneno) return 2.0;
                if (defensor == Psíquico) return 0.5;
                break;

            case Roca:
                if (defensor == Fuego || defensor == Hielo || defensor == Volador || defensor == Bicho) return 2.0;
                if (defensor == Lucha || defensor == Tierra) return 0.5;
                break;

            case Agua:
                if (defensor == Fuego || defensor == Tierra || defensor == Roca) return 2.0;
                if (defensor == Agua || defensor == Planta || defensor == Dragón) return 0.5;
                break;
        }

        //daño neutral
        return 1.0;
    }
}

