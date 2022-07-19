public class JugadorPorrazo extends Jugador {

    private MazoIngles mazo;

    /**
     * Constructor por omisión
     */
    public JugadorPorrazo() {
        this.mazo = new MazoIngles();
        this.nombre = "";
    }

    /**
     * Constructor por parámetros
     * @param nombre nombre del jugador
     */
    public JugadorPorrazo(String nombre) {
        this.mazo = new MazoIngles();
        this.nombre = nombre;
    }

    /**
     * Obtener el mazo del jugador
     * @return mazo del jugador
     */
    public Mazo getMazo() {
        return this.mazo;
    }

    public boolean recibirCarta(Cartas c) {
        return this.mazo.agregarAMazo(c);
    }
}
