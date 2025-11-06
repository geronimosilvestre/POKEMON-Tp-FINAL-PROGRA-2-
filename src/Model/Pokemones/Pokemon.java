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
    private int vida;
    private int ataque;
    private int defensa;

    private int ataqueEspecial;
    private int defensaEspecial;



    public Pokemon(ENombre nombre, ETipo tipo,int vida, int ataque, int defensa) {
        this.uuid = UUID.randomUUID();
        this.nombre = nombre;
        this.tipo = tipo;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;

    }

    public Pokemon() {
        this.uuid = UUID.randomUUID();
        this.nombre = null;
        this.tipo = null;
        this.vida = 0;
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

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
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
                ", vida=" + vida +
                ", ataque=" + ataque +
                ", defensa=" + defensa +
                ", nombre=" + nombre +
                ", tipo=" + tipo +
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
            object.put("vida",this.vida);
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
            generico.nombre =ETipo.valueOf(jsonObject.getString("nombre");
            generico.vida = jsonObject.getInt("vida");
            generico.ataque = jsonObject.getInt("ataque");
            generico.defensa = jsonObject.getInt("defensa");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return generico;
    }
}


