public class Carta extends Cartas{
    private final int numero;
    private final String tipo;
    private final String imagen;

    public Carta(int numero, String tipo, String imagen) {
        this.numero = numero;
        this.tipo = tipo;
        this.imagen = imagen;
    }

    public int get_numero() {
        return this.numero;
    }

    public String get_tipo() {
        return this.tipo;
    }

    public String get_imagen() {
        return this.imagen;
    }

    public boolean agregarAMazo(Cartas c) {
        return c.agregarAMazo(this);
    }

    public Cartas sacarDeMazo(Cartas c) {
        return c.sacarDeMazo(this);
    }
}
