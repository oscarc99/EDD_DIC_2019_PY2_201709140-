package EDD;

/**
 *
 * @author Oscar C
 */
public class NodeV {
    
    String v1;
    String v2;

    public String getV1() {
        return v1;
    }

    public void setV1(String v1) {
        this.v1 = v1;
    }

    public void setV2(String v2) {
        this.v2 = v2;
    }

    public void setNext(NodeV next) {
        this.next = next;
    }

    public void setBefore(NodeV before) {
        this.before = before;
    }

    public String getV2() {
        return v2;
    }

    public NodeV getNext() {
        return next;
    }

    public NodeV getBefore() {
        return before;
    }
    private NodeV next;
    private NodeV before;

    public NodeV(String dato, String d) {
        this.v1 = dato;
        this.v2 = d;
        next = null;
        before = null;
    }

   
}
