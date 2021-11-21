package POJO;

/**
 *
 * @author a20marcosgp
 */
public abstract class Piso {

    private static final int tamMax = 140;

    private String referencia;
    private char tipoPiso;
    private String nombre;
    private float cuotaFija;
    private float litrosAguaCaliente;
    private float pasosCalefaccion;
    private float totalReciboComunidad;

    public Piso() {
    }

    public Piso(String referencia, char tipoPiso, String nombre, float cuotaFija, float litrosAguaCaliente, float pasosCalefaccion) {
        this.referencia = referencia;
        this.tipoPiso = tipoPiso;
        this.nombre = nombre;
        this.cuotaFija = cuotaFija;
        this.litrosAguaCaliente = litrosAguaCaliente;
        this.pasosCalefaccion = pasosCalefaccion;

    }

    public abstract float totalRbo();

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public char getTipoPiso() {
        return tipoPiso;
    }

    public void setTipoPiso(char tipoPiso) {
        this.tipoPiso = tipoPiso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getCuotaFija() {
        return cuotaFija;
    }

    public void setCuotaFija(float cuotaFija) {
        this.cuotaFija = cuotaFija;
    }

    public float getLitrosAguaCaliente() {
        return litrosAguaCaliente;
    }

    public void setLitrosAguaCaliente(float litrosAguaCaliente) {
        this.litrosAguaCaliente = litrosAguaCaliente;
    }

    public float getPasosCalefaccion() {
        return pasosCalefaccion;
    }

    public void setPasosCalefaccion(float pasosCalefaccion) {  
        this.pasosCalefaccion=pasosCalefaccion;
    }

    public float getTotalReciboComunidad() {
        return totalReciboComunidad;
    }

    public void setTotalReciboComunidad(float totalReciboComunidad) {
        this.totalReciboComunidad = totalReciboComunidad;
    }

    public int getTamañoMax() {
        return tamMax;
    }

    public int getTamañoReal() {
        return this.referencia.length() * 2 + 2 + this.nombre.length() + 4 + 4 + 4 + 4 + 2;
    }

}
