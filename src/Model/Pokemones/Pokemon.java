package Model.Pokemones;
import Enums.ENombre;
import Enums.ETipo;
import Interfaces.ICapturar;
import Interfaces.IConvertirJSON;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.UUID;

public class Pokemon implements IConvertirJSON<Pokemon> {
    private UUID uuid;
    private ENombre nombre;
    private ETipo tipo;
    private int vidaRestante;
    private int vidaCompleta;
    private int ataque;
    private int defensa;

    private int ataqueEspecial;
    private int defensaEspecial;



    public Pokemon(ENombre nombre, ETipo tipo,int vidaRestante, int vidaCompleta, int ataque, int defensa) {
        this.uuid = UUID.randomUUID();
        this.nombre = nombre;
        this.tipo = tipo;
        this.vidaCompleta = vidaCompleta;
        this.vidaRestante = vidaRestante;
        this.ataque = ataque;
        this.defensa = defensa;

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
        this.nombre = ENombre.valueOf(nombre);
    }

    public UUID getUuid() {
        return uuid;
    }

    public ENombre getNombre() {
        return nombre;
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
        return "Pokemon{" +
                "uuid=" + uuid +
                ", nombre=" + nombre +
                ", tipo=" + tipo+
                ", vidaRestante=" + vidaRestante +
                ", vidaCompleta=" + vidaCompleta +
                ", ataque=" + ataque +
                ", defensa=" + defensa +
                ", ataqueEspecial=" + ataqueEspecial +
                ", defensaEspecial=" + defensaEspecial +
                '}';
    }

    @Override
    public JSONObject toJSONObject() {

        JSONObject object = new JSONObject();
        try {
            object.put("uuid", this.uuid.toString());
            object.put("nombre", this.nombre.name());
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
            generico.nombre = ENombre.valueOf(jsonObject.getString("nombre"));
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


}


