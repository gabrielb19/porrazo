public abstract class Cartas {
    /**
     * Constructor de la clase Cartas
     */
    public Cartas(){}

    /**
     * Agrega una carta a un mazo
     * @param c Carta a agregar al mazo o mazo al que se agregará la carta (depende si se llama desde la clase Cartas o Mazo)
     * @return Si carta fue agregada exitosamente o no
     */
    public abstract boolean agregarAMazo(Cartas c);
    /**
     * Saca una carta de un mazo
     * @param c Carta a sacar de un mazo (Clase Mazo) o carta mazo del que se sacará la carta (Clase Carta)
     * @return Carta sacada del mazo
     */
    public abstract Cartas sacarDeMazo(Cartas c);
}
