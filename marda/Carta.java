public class Carta extends Cartas{

    private final int numero;
    private final String tipo;
    private final String imagen;

    /**
     * Constructor por parámetros de la clase Carta
     * @param numero Numero de la carta 
     * @param tipo Palo de la carta
     * @param imagen Path a la imagen de la carta
     */
    public Carta(int numero, String tipo, String imagen) {
        this.numero = numero;
        this.tipo = tipo;
        this.imagen = imagen;
    }

    /**
     * Devuelve el número de la carta
     * @return numero de la carta
     */
    public int getNumero() {
        return this.numero;
    }

    /**
     * Devuelve el tipo de la carta
     * @return tipo de la carta
     */
    public String getTipo() {
        return this.tipo;
    }

    /**
     * Devuelve el path de la imagen de la carta
     * @return path de la imagen de la carta
     */
    public String getImagen() {
        return this.imagen;
    }

    public boolean agregarAMazo(Cartas c) {
        return c.agregarAMazo(this);
    }

    public Cartas sacarDeMazo(Cartas c) {
        return c.sacarDeMazo(this);
    }
}
