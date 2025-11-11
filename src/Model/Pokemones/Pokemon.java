package Model.Pokemones;
import Enums.ENombre;
import Enums.ETipo;
import Interfaces.IBatalla;
import Interfaces.IConvertirJSON;
import Model.Entidad;
import Model.Entrenador.Entrenador;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.UUID;

public final class  Pokemon extends Entidad implements IConvertirJSON<JSONObject,Pokemon>, IBatalla {
    private ENombre nombre;
    private ETipo tipo;
    private int vidaRestante;
    private int vidaCompleta;
    private int ataque;
    private int defensa;


//Constructor para crearlo Usando Enum

    public Pokemon(ENombre pokemon) {
        super();
        this.nombre = pokemon;
        this.tipo = pokemon.getTipo();
        this.vidaCompleta = pokemon.getVidaCompleta();
        this.vidaRestante = pokemon.getVidaCompleta();
        this.ataque = pokemon.getAtaque();
        this.defensa = pokemon.getDefensa();

    }

    public Pokemon() {
        this.nombre = null;
        this.tipo = null;
        this.vidaRestante = 0;
        this.vidaCompleta = 0;
        this.ataque = 0;
        this.defensa = 0;
    }

    //Constructor para crearlo a partir del nombre  solo con String

    public Pokemon(String nombre)
    {
        ENombre pokemon = ENombre.valueOf(nombre.toUpperCase());
        this.nombre = pokemon;
        this.tipo = pokemon.getTipo();
        this.vidaCompleta = pokemon.getVidaCompleta();
        this.vidaRestante = pokemon.getVidaCompleta();
        this.ataque = pokemon.getAtaque();
        this.defensa = pokemon.getDefensa();
    }

    //Constructor que resulta  util al deserializar el JSON
    public Pokemon(String nombre,UUID uuid)
    {
        super(uuid);
        ENombre pokemon = ENombre.valueOf(nombre.toUpperCase());
        this.nombre = pokemon;
        this.tipo = pokemon.getTipo();
        this.vidaCompleta = pokemon.getVidaCompleta();
        this.vidaRestante = pokemon.getVidaCompleta();
        this.ataque = pokemon.getAtaque();
        this.defensa = pokemon.getDefensa();
    }



    public String getNombre() {
        return nombre.getNombre();
    }

    public void setNombre(ENombre nombre) {
        this.nombre = nombre;
    }

    public ETipo getTipo() {
        return tipo;
    }

    public void setTipo(ETipo tipo) {
        this.tipo = tipo;
    }

    public int getVidaCompleta() {
        return vidaCompleta;
    }


    public int getVidaRestante() {
        return vidaRestante;
    }

    public void setVidaRestante(int vidaRestante) {
        this.vidaRestante = vidaRestante;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }


    //En el equals comparamos por nombre(Enum) porque no queremos dos enums que tengan el mismo nombre ya que se trataria del mismo pokemon
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pokemon pokemon)) return false;
        return nombre == pokemon.nombre;
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    @Override
    public String toString() {
        return "\n POKEMON: " + nombre.getNombre() +
                "\n id: " + super.getUuid() +
                "\n TIPO: " + tipo+
                "\n VIDA RESTANTE: " + vidaRestante +
                "\n VIDA COMPLETA: " + vidaCompleta +
                "\n ATAQUE: " + ataque +
                "\n DEFENSA: " + defensa +
                "\n ";
    }

    @Override
    public JSONObject toJSON() {

        JSONObject object = new JSONObject();
        try {
            object.put("uuid", super.getUuid().toString());
            object.put("nombre", this.nombre.getNombre());
            object.put("tipo", this.tipo.name());
            object.put("vidaRestante", this.vidaRestante);
            object.put("vidaCompleta", this.vidaCompleta);
            object.put("ataque",this.ataque);
            object.put("defensa",this.defensa);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public  Pokemon fromJSON(JSONObject jsonObject) throws JSONException {


            UUID uuid = UUID.fromString(jsonObject.getString("uuid"));
            ENombre nombre = ENombre.valueOf(jsonObject.getString("nombre").toUpperCase());

            Pokemon p = new Pokemon(nombre.getNombre(), uuid);

            p.setTipo(ETipo.valueOf(jsonObject.getString("tipo")));
            p.setVidaRestante(jsonObject.getInt("vidaRestante"));
            p.setAtaque(jsonObject.getInt("ataque"));
            p.setDefensa(jsonObject.getInt("defensa"));

            return p;


    }


    @Override
    public double ataqueNormal() {
        double variacion = 0.8 + (Math.random() * 0.4); // entre 0.8 y 1.2
        return this.ataque * variacion;
    }
}


