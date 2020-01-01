package EDD;

import java.io.*;

/**
 *
 * @author Oscar C
 */
public class AVLTree {

    private NodeAVL root;

    public void clear() {
        this.root = null;
    }

    boolean isEmpty() {
        return root == null;
    }

    public void transverse() {
        if (isEmpty()) {
            inOrderTraversal(root);
        } else {

        }
    }

    private void inOrderTraversal(NodeAVL node) {
        if (node.getLeft() != null) {
            inOrderTraversal(node.getLeft());
        }

        System.out.println(node);

        if (node.getRight() != null) {
            inOrderTraversal(node.getRight());
        }
    }

    private int height(NodeAVL node) {
        if (node == null) {
            return -1;
        }
        return node.getHeight();
    }

    private int getBalance(NodeAVL node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }

    public NodeAVL rightRotation(NodeAVL node) {
        System.out.println("Rotacion a la derecha");

        NodeAVL tempLeft = node.getLeft();
        NodeAVL t = tempLeft.getRight();

        tempLeft.setRight(node);
        node.setLeft(t);

        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
        tempLeft.setHeight(Math.max(height(tempLeft.getLeft()), height(tempLeft.getRight())) + 1);

        return tempLeft;
    }

    public NodeAVL leftRotation(NodeAVL node) {
        System.out.println("Rotacion a la derecha");

        NodeAVL tempRight = node.getRight();
        NodeAVL t = tempRight.getLeft();

        tempRight.setLeft(node);
        node.setRight(t);

        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
        tempRight.setHeight(Math.max(height(tempRight.getLeft()), height(tempRight.getRight())) + 1);

        return tempRight;
    }

    public void insert(int dato) {
        root = insert(root, dato);

    }

    private NodeAVL insert(NodeAVL node, int dato) {
        if (node == null) {
            System.out.println("SIEMPRE NULL");
            return new NodeAVL(dato);
        }

        if (dato < node.getData()) {
            node.setLeft(insert(node.getLeft(), dato));
        } else {
            node.setRight(insert(node.getRight(), dato));
        }
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
        node = setViolation(dato, node);

        return node;

    }

    private NodeAVL setViolation(int dato, NodeAVL node) {
        int balance = getBalance(node);
        //caso 1 rotacion simple a la derecha (izquierda - izquierda)
        if (balance > 1 && dato < node.getLeft().getData()) {
            return rightRotation(node);
        }
        //case 2 reitacion simple a la izquierda (derecha - derecha)
        if (balance < -1 && dato > node.getRight().getData()) {
            return leftRotation(node);
        }
        //case 3 rotacion doble (derecha - izquierda)
        if (balance > 1 && dato > node.getLeft().getData()) {
            node.setLeft(leftRotation(node.getLeft()));
            return rightRotation(node);
        }

        //case 4 rotacion doble (izquierda - derecha)
        if (balance < -1 && dato < node.getRight().getData()) {
            node.setRight(rightRotation(node.getRight()));
            return leftRotation(node);
        }

        return node;
    }

    public void delete(int data) {
        if (root != null) {
            deleteN(data, root);
        }

    }

    private NodeAVL deleteN(int data, NodeAVL node) {
        if (data == node.getData() && node.getLeft() == null && node.getRight()==null){
            return null;
        }
            
        if (node == null) {
            return node;
        }
        if (data < node.getData()) {
            node.setLeft(deleteN(data, node.getLeft()));
        } else if (data > node.getData()) {
            node.setRight(deleteN(data, node.getRight()));
        } else {

            if (node.getLeft() == null && node.getLeft() == null) {
                return null;
            }
            if (node.getLeft() == null) {
                NodeAVL temp = node.getRight();
                node = null;
                return temp;
            } else if (node.getRight() == null) {
                NodeAVL temp = node.getLeft();
                node = null;
                return temp;
            }

            NodeAVL temp = getPredecessor(node.getLeft());

            node.setData(temp.getData());
            node.setLeft(deleteN(temp.getData(), node.getLeft()));
        }
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())));
        return setDeletion(node);
    }

    private NodeAVL getPredecessor(NodeAVL node) {
        NodeAVL pre = node;
        while (pre.getRight() != null) {
            pre = pre.getRight();
        }
        return pre;
    }

    private NodeAVL setDeletion(NodeAVL node) {
        int balance = getBalance(node);
        if (balance > 1) {
            if (getBalance(node.getLeft()) < 0) {
                node.setLeft(leftRotation(node.getLeft()));
            }
            return rightRotation(node);
        }

        if (balance < -1) {
            if (getBalance(node.getRight()) < 0) {
                node.setRight(rightRotation(node.getRight()));
            }
            return leftRotation(node);
        }
        return node;
    }

    private String Graph(NodeAVL Raiz) {

        String Retorno = "";
        if (Raiz == null) {
            return Retorno;
        }

        if (Raiz.getLeft() != null) {
            Retorno += Graph(Raiz.getLeft());
            Retorno += Raiz.getData() + "->" + Raiz.getLeft().getData() + "; \n";
        }
        if (Raiz.getRight() != null) {
            Retorno += Graph(Raiz.getRight());
            Retorno += Raiz.getData() + "->" + Raiz.getRight().getData() + "; \n";
        }
        return Retorno + Raiz.getData() + "[label=\" " + Raiz.getData() + " Altura: " + Raiz.getHeight() + "\" ];\n";

    }

    private String graficar() {
        if (root == null) {
            return "\n\n";
        } else {
            return "\n  \n" + Graph(root) + "\n";
        }
    }

    public void report() {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src\\Imagenes\\AVLTree.dot");

            pw = new PrintWriter(fichero);

            pw.println("digraph G { \n");
            pw.println("nodesep=0.8;\n");
            pw.println("ranksep=0.5;\n");
            pw.println(graficar());
            pw.println("}\n");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        try {
            Process p = Runtime.getRuntime().exec("cmd /c dot.exe -Tpng src\\Imagenes\\AVLTree.dot -o src\\Imagenes\\AVLTree.png");
            Process pa = Runtime.getRuntime().exec("cmd /c src\\Imagenes\\AVLTree.png");
        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }

    private String graficar(int dato) {
        if (root == null) {
            return "\n\n";
        } else {
            return "\n  \n" + Graph(root) + "\n" + G(dato) + "\n";
        }
    }

    public void buscar(int dato) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src\\Imagenes\\AVLTree.dot");

            pw = new PrintWriter(fichero);

            pw.println("digraph G { \n");
            pw.println("nodesep=0.8;\n");
            pw.println("ranksep=0.5;\n");
            pw.println(graficar(dato));
            pw.println("}\n");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        try {
            Process p = Runtime.getRuntime().exec("cmd /c dot.exe -Tpng src\\Imagenes\\AVLTree.dot -o src\\Imagenes\\AVLTree.png");
            Process pa = Runtime.getRuntime().exec("cmd /c src\\Imagenes\\AVLTree.png");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void insertDes(int dato) {
        root = insertDes(root, dato);
        report();
        delete(dato);
    }

    private NodeAVL insertDes(NodeAVL node, int dato) {
        if (node == null) {
            System.out.println("SIEMPRE NULL");
            return new NodeAVL(dato);
        }

        if (dato < node.getData()) {
            node.setLeft(insert(node.getLeft(), dato));
        } else {
            node.setRight(insert(node.getRight(), dato));
        }
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
        //node = setViolation(dato, node);

        return node;
    }

    private String G(int dato){
        return G(root, dato);
    }
    
    
    private String G(NodeAVL Raiz, int dato) {
        String r =  Raiz.getData() + "[label=\" " + Raiz.getData() + " Altura: " + Raiz.getHeight() + "  \"  color =\"blue\"];\n";
        if (Raiz == null) {
            return "";
        } else {
            if (Raiz.getRight() != null) {
                if(dato > Raiz.getData()   ){
                    r += G(Raiz.getRight(), dato);
                }
            }
            if (Raiz.getLeft() != null) {
                if(dato < Raiz.getData()){
                    r += G(Raiz.getLeft(), dato);
                }
            }
            
            
        }
        return r;
    }

}
