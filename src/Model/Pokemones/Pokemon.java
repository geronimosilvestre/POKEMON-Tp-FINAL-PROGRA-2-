package Model.Pokemones;
import Enums.ENombre;
import Enums.ETipo;
import Exceptions.existException;
import Interfaces.IBatalla;
import Interfaces.ICapturar;
import Interfaces.IConvertirJSON;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.UUID;

public class Pokemon implements IConvertirJSON<Pokemon>, IBatalla {
    private UUID uuid;
    private ENombre nombre;
    private ETipo tipo;
    private int vidaRestante;
    private int vidaCompleta;
    private int ataque;
    private int defensa;




    public Pokemon(ENombre pokemon) {
        this.uuid = UUID.randomUUID();
        this.nombre = pokemon;
        this.tipo = pokemon.getTipo();
        this.vidaCompleta = pokemon.getVidaCompleta();
        this.vidaRestante = pokemon.getVidaCompleta();
        this.ataque = pokemon.getAtaque();
        this.defensa = pokemon.getDefensa();

    }

    public Pokemon() {
        this.uuid = UUID.randomUUID();
        this.nombre = null;
        this.tipo = null;
        this.vidaRestante = 0;
        this.vidaCompleta = 0;
        this.ataque = 0;
        this.defensa = 0;

    }

    public Pokemon(String nombre)
    {
        this.uuid = UUID.randomUUID();
        this.nombre = ENombre.valueOf(nombre.toUpperCase());
    }

    public UUID getUuid() {
        return uuid;
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
        return "\n POKEMON: " + nombre +
                "\n id: " + uuid +
                "\n TIPO: " + tipo+
                "\n VIDA RESTANTE: " + vidaRestante +
                "\n VIDA COMPLETA: " + vidaCompleta +
                "\n ATAQUE: " + ataque +
                "\n DEFENSA: " + defensa +
                "\n }";
    }

    @Override
    public JSONObject toJSONObject() {

        JSONObject object = new JSONObject();
        try {
            object.put("uuid", this.uuid.toString());
            object.put("nombre", this.getNombre());
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
    public Pokemon fromJSON(JSONObject jsonObject) {
        Pokemon generico = new Pokemon();
        try {
            generico.uuid = UUID.fromString(jsonObject.getString("uuid"));
            generico.nombre = ENombre.valueOf(jsonObject.getString("nombre").toUpperCase());
            generico.tipo = ETipo.valueOf(jsonObject.getString("tipo"));
            generico.vidaRestante = jsonObject.getInt("vidaRestante");
            generico.vidaCompleta = jsonObject.getInt("vidaCompleta");
            generico.ataque = jsonObject.getInt("ataque");
            generico.defensa = jsonObject.getInt("defensa");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return generico;
    }

    @Override
    public double ataqueNormal() {
        return this.ataque * (Math.random());
    }
}


