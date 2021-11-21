package POJO;

/**
 *
 * @author a20marcosgp
 */
public class Atico extends Piso {

    private float terraza;

    public Atico() {
    }

    public Atico(String referencia, char tipoPiso, String nombre, float cuotaFija, float litrosAguaCaliente, float pasosCalefaccion, float terraza) {
        super(referencia, tipoPiso, nombre, cuotaFija, litrosAguaCaliente, pasosCalefaccion);
        this.terraza = terraza;

    }

    public float getTerraza() {
        return terraza;
    }

    public void setTerraza(float terraza) {
        this.terraza = terraza;
    }
    
  /**Añado un método para calcular el recibo, que llamarés cuando 
     * se haga un alta o se modifique un registro
     */
    @Override
    public float totalRbo() {
        this.setTotalReciboComunidad(this.getCuotaFija() + this.getLitrosAguaCaliente() * 0.40f + this.getPasosCalefaccion() * 0.70f + this.getTerraza() * 0.45f);
        return this.getTotalReciboComunidad();
    }

    @Override
    public int getTamañoReal() {
        return this.getReferencia().length() * 2 + 2 + this.getNombre().length() + 4 + 4 + 4 + 4 + 2 + 4;
    }

}
