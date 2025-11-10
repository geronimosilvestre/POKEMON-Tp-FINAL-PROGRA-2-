package Menu;

import Model.Entrenador.Entrenador;
import Model.Pokemones.Pokemon;

public class Menu {

    public static String mostrarResultadoAtaque(Pokemon atacante, Pokemon defensor, double efectividad, int damage) {
        StringBuilder sb = new StringBuilder();
        sb.append(atacante.getNombre()).append(" atacó a ").append(defensor.getNombre()).append("\n")
                .append("Tipo atacante: ").append(atacante.getTipo()).append(" → Tipo defensor: ").append(defensor.getTipo()).append("\n")
                .append("Efectividad: x").append(efectividad).append("\n")
                .append("Daño causado: ").append(damage).append("\n")
                .append("❤ Vida restante de ").append(defensor.getNombre()).append(": ").append(defensor.getVidaRestante()).append("\n");

        return sb.toString();
    }



    public static void menuPrincipal(){
        System.out.println("\n===== MENÚ PRINCIPAL =====");
        System.out.println("1. Equipos");
        System.out.println("2. Pokédex");
        System.out.println("3. Batallar");
        System.out.println("0. Salir");
        System.out.print("Elegí una opción: ");
    }

    public static void menuEquipos(){
        System.out.println("\n===== MENÚ EQUIPOS =====");
        System.out.println("1. Agregar peleador");
        System.out.println("2. Ver peleadores y sus Pokémon");
        System.out.println("3. Importar ambos equipos desde JSON ");
        System.out.println("0. Volver");
        System.out.print("Opción: ");
    }

    public static void menuMochila(){
        System.out.println("\n--- Menú Mochila ---");
        System.out.println("1. Rellenar / completar automáticamente");
        System.out.println("2. Rellenar manualmente");
        System.out.println("3. Ver mochila");
        System.out.println("4. Eliminar Pokémon");
        System.out.println("5. Guardar peleador y volver");
        System.out.print("Opción: ");
    } //Menu mochila existe dentro de menuEquipos



    public static void menuBatalla() {
        System.out.println("\n===== MENÚ BATALLA =====");
        System.out.println("1. Iniciar batalla");
        System.out.println("2. Guardar batalla previa en JSON");
        System.out.println("0. Volver");
        System.out.print("Opción: ");
    }










}


