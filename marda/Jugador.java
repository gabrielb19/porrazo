
public abstract class Jugador {
    protected String nombre;
    
    /**
     * Constructor por omisión
     */
    public Jugador() {
        this.nombre = "";
    }

    /**
     * Constructor por parámetros
     * @param nombre nombre del jugador
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtener el nombre del jugador
     * @return nombre del jugador
     */
    public String getNombre() {
        return this.nombre;
    }
    
    /**
     * Asignar nombre al jugador
     * @param nombre Nuevo nombre del jugador
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Usuario recibe una carta a alguno de sus mazos
     * @param c Carta a recibir
     * @return Si la carta fue recibida correctamente
     */
    public abstract boolean recibirCarta(Cartas c);

    /**
     * Jugador desecha una carta de alguno de sus mazos
     * @param index índice de la carta a desechar
     * @param m Mazo del cual se desecha la carta
     * @return Carta desechada
     */
    public Cartas desecharCarta(int index, Mazo m) {
        return m.sacarDeMazo(m.cartaEnPosicion(index));
    }

    /**
     * Jugador desecha una carta de alguno de sus mazos
     * @param carta Carta a desechar
     * @param m Mazo del que se desechará la carta
     * @return Carta desechada
     */
    public Cartas desecharCarta(Cartas carta, Mazo m) {
        return m.sacarDeMazo(carta);
    }
}