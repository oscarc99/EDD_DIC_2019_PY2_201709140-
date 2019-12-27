package EDD;

/**
 *
 * @author Oscar C
 */
public class NodeB {

    private NodeB[] PagIzq = null;
    private NodeB[] PagDer = null;
    private int Clave = 0;
    private int Contenido = 0;

    public boolean EsHoja() {
        if (PagIzq == null && PagDer == null) {
            return true;
        } else {
            return false;
        }
    }

    public String NodoGrafico() {
        return Contenido + "[label=\"" + Contenido + "\"]";
    }

    public NodeB(int Clave, int Contenido) {
        this.Clave = Clave;
        this.Contenido = Contenido;
    }

    public NodeB[] getPagIzq() {
        return PagIzq;
    }

    public void setPagIzq(NodeB[] PagIzq) {
        this.PagIzq = PagIzq;
    }

    public NodeB[] getPagDer() {
        return PagDer;
    }

    public void setPagDer(NodeB[] PagDer) {
        this.PagDer = PagDer;
    }

    public int getClave() {
        return Clave;
    }

    public void setClave(int Clave) {
        this.Clave = Clave;
    }

    public int getContenido() {
        return Contenido;
    }

    public void setContenido(int Contenido) {
        this.Contenido = Contenido;
    }

}
