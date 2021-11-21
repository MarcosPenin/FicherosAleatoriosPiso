package POJO;

/**
 *
 * @author a20marcosgp
 */
public class Duplex extends Piso {

    private float cuotaExtra;

    public Duplex() {
    }

    public Duplex(String referencia, char tipoPiso, String nombre, float cuotaFija, float litrosAguaCaliente, float pasosCalefaccion, float cuotaExtra) {
        super(referencia, tipoPiso, nombre, cuotaFija, litrosAguaCaliente, pasosCalefaccion);
        this.cuotaExtra = cuotaExtra;

    }

    public float getCuotaExtra() {
        return cuotaExtra;
    }

    public void setCuotaExtra(float cuotaExtra) {
        this.cuotaExtra = cuotaExtra;
    }

    /**Añado un método para calcular el recibo, que llamarés cuando 
     * se haga un alta o se modifique un registro
     */
    
    @Override
    public float totalRbo() {
        this.setTotalReciboComunidad(this.getCuotaFija()+ (this.getLitrosAguaCaliente() * 0.40f) +(this.getPasosCalefaccion()*0.70f)
                + this.getCuotaExtra());
        return this.getTotalReciboComunidad();
    }


    @Override
    public int getTamañoReal() {
        return this.getReferencia().length() * 2 + 2 + this.getNombre().length() + 4 + 4 + 4 + 4 + 2 + 4;
    }

}
