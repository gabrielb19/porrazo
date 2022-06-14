public class Carta {
    private final int numero;
    private final String tipo;

    public Carta(int numero, String tipo){
        this.numero = numero;
        this.tipo = tipo;
    }

    public int get_numero(){
        return this.numero;
    }

    public String get_tipo() {
        return this.tipo;
    }
}
