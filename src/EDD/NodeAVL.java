package EDD;

/**
 *
 * @author Oscar C
 */
public class NodeAVL {

    public NodeAVL(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public NodeAVL getLeft() {
        return left;
    }

    public void setLeft(NodeAVL left) {
        this.left = left;
    }

    public NodeAVL getRight() {
        return right;
    }

    public void setRight(NodeAVL right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    private int data;
    public NodeAVL left;
    public NodeAVL right;
    public int height;
    
    @Override
    public String toString(){
        return ""+ this.data;
    }
    
    
}
