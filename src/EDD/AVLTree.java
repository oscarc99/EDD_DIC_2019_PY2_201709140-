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

    public void borrar(int key) {
        root = deleteRec(root, key);

    }

    NodeAVL deleteRec(NodeAVL root, int key) {

        if (root == null) {
            return root;
        }

        if (key < root.getData()) {
            root.left = deleteRec(root.left, key);
        } else if (key > root.getData()) {
            root.right = deleteRec(root.right, key);
        } else {

            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.setData(minValue(root.right));

            // Delete the inorder successor 
            root.right = deleteRec(root.right, root.getData());
        }

        return root;
    }

    int minValue(NodeAVL root) {
        int minv = root.getData();
        while (root.left != null) {
            minv = root.left.getData();
            root = root.left;
        }
        return minv;
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
            deleteNode( root, data);
        }

    }

    private NodeAVL deleteN(int data, NodeAVL node) {
        if (node == null) {
            return null;
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

    private NodeAVL pre(NodeAVL node) {
        if (node.getRight() != null) {
            return pre(node.getRight());
        }
        return node;
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
        return Retorno + Raiz.getData() + "[label=\" " + Raiz.getData() + "   H: " + Raiz.getHeight() + " FE:  " + this.getBalance(Raiz) + " \"     ];\n";

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
//            Process pa = Runtime.getRuntime().exec("cmd /c src\\Imagenes\\AVLTree.png");
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

    private String pinta(int dato) {
        if (root == null) {
            return "\n\n";
        } else {
            return "\n  \n" + Graph(root) + "\n" + P(dato) + "\n";
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
            //Process pa = Runtime.getRuntime().exec("cmd /c src\\Imagenes\\AVLTree.png");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void pintar(int dato) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src\\Imagenes\\AVLTree.dot");

            pw = new PrintWriter(fichero);

            pw.println("digraph G { \n");
            pw.println("nodesep=0.8;\n");
            pw.println("ranksep=0.5;\n");
            pw.println(pinta(dato));
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
            //Process pa = Runtime.getRuntime().exec("cmd /c src\\Imagenes\\AVLTree.png");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void insertDes(int dato) {
        if (root == null) {
            report();
        } else {
            insertDes(root, dato);
            report();
            borrar(dato);
        }

    }

    private void insertDes(NodeAVL node, int dato) {
        NodeAVL nuevo = new NodeAVL(dato);
        if (dato < node.getData()) {
            if (node.getLeft() != null) {
                insertDes(node.getLeft(), dato);
            } else {
                node.setLeft(nuevo);
            }

        } else {
            if (node.getRight() != null) {
                insertDes(node.getRight(), dato);
            } else {
                node.setRight(nuevo);
            }
        }

    }

    private String G(int dato) {
        return G(root, dato);
    }

    private String P(int dato) {
        return P(root, dato);
    }

    private String G(NodeAVL Raiz, int dato) {
        String r = Raiz.getData() + "[label=\" " + Raiz.getData() + " H: " + Raiz.getHeight() + " FE:  " + this.getBalance(Raiz) + " \"  color =\"blue\"];\n";
        if (Raiz == null) {
            return "";
        } else {
            if (Raiz.getRight() != null) {
                if (dato > Raiz.getData()) {
                    r += G(Raiz.getRight(), dato);
                }
            }
            if (Raiz.getLeft() != null) {
                if (dato < Raiz.getData()) {
                    r += G(Raiz.getLeft(), dato);
                }
            }

        }
        return r;
    }

    private String P(NodeAVL Raiz, int dato) {
        String r = "";
        if (Raiz == null) {
            return "";
        } else if (Raiz.getData() == dato) {
            r = Raiz.getData() + "[label=\" " + Raiz.getData() + " H: " + Raiz.getHeight() + " FE:  " + this.getBalance(Raiz) + " \"  color =\"red\"];\n";
        } else {
            if (Raiz.getRight() != null) {
                if (dato > Raiz.getData()) {
                    r += P(Raiz.getRight(), dato);
                }
            }
            if (Raiz.getLeft() != null) {
                if (dato < Raiz.getData()) {
                    r += P(Raiz.getLeft(), dato);
                }
            }

        }
        return r;
    }

    public boolean search(int dato) {
        if (root == null) {
            return false;
        } else {
            return buscar(root, dato);
        }

    }

    public boolean buscar(NodeAVL raiz, int dato) {
        if (raiz == null) {
            //Entonces el arbol esta vacio
            return false;
            //else... es decir que por lo menos hay un nodo
        } else if (raiz.getData() == dato) {

            return true;
        } else if (dato < raiz.getData()) {        //Si el nombre en asccii es mas pequeño, nos vamos por la izquierda

            return buscar(raiz.getLeft(), dato);

        } else if (dato > raiz.getData()) {        //Si el nombre en asccii es mas pequeño, nos vamos por la izquierda

            return buscar(raiz.getRight(), dato);
        } else {      //tal nodo no existe
            return false;
        }

    }

    private NodeAVL deleteNode(NodeAVL root, int value) {
       

        if (root == null) {
            return root;
        }

        if (value < root.getData()) {
            root.left = deleteNode(root.left, value);
        } 
        else if (value > root.getData()) {
            root.right = deleteNode(root.right, value);
        } 
        else {
            
            if ((root.left == null) || (root.right == null)) {

                NodeAVL temp;
                if (root.left != null) {
                    temp = root.getLeft();
                } else {
                    temp = root.getRight();
                }

              
                if (temp == null) {
                    temp = root;
                    root = null;
                } else 
                {
                    root = temp; 
                }
                temp = null;
            } else {
               
                NodeAVL temp = minValueNode(root.right);

                
                root.setData(temp.getData());

                
                root.right = deleteNode(root.right, temp.getData());
            }
        }

        
        if (root == null) {
            return root;
        }


        root.height = Math.max(height(root.left), height(root.right)) + 1;

        
        int balance = getBalance(root);

        
        // Caso LL
        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }

        // Caso LR
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Caso RR
        if (balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }

        //  Caso RL
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    private NodeAVL minValueNode(NodeAVL node) {
        NodeAVL current = node;
        /* loop down to find the leftmost leaf */
        while (current.getLeft() != null) {
            current = current.left;
        }
        return current;
    }

    private NodeAVL rightRotate(NodeAVL y) {
        NodeAVL x = y.left;
        NodeAVL T2 = x.right;

        
        x.setRight(y);
        y.setLeft(T2);

        
        y.setHeight(  Math.max(height(y.left), height(y.right)) + 1);
        x.setHeight( Math.max(height(x.left), height(x.right)) + 1);

        
        return x;
    }

    private NodeAVL leftRotate(NodeAVL x) {
        NodeAVL y = x.getRight();
        NodeAVL T2 = y.getLeft();

        
        y.setLeft(x);
        x.setRight( T2);

        
        x.setHeight( Math.max(height(x.left), height(x.right)) + 1);
        y.setHeight ( Math.max(height(y.left), height(y.right)) + 1);

        
        return y;
    }

}
