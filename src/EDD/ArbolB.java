package EDD;

/**
 *
 * @author Oscar C
 */
public class ArbolB {

    NodoB root;
    int t = 5;

    public ArbolB(int t) {
        root = null;
        this.t = t;
    }

    private void insertData(NodoB node, int dato) {
        int index = node.size;
        for (int i = node.size - 1; i > -1; i--) {
            if (dato <= node.data[i]) {
                node.data[i + 1] = node.data[i];
                index = i;
            } else {
                break;
            }
        }
        node.data[index] = dato;
        node.size++;
    }

    private NodoB split(NodoB node, int key) {
        int mid = node.size / 2;
        NodoB node1 = new NodoB(t);
        NodoB node2 = new NodoB(t);
        NodoB parent = node.parent;

        for (int i = 0; i < mid; i++) {
            node1.data[i] = node.data[i];
            node1.children[i] = node.children[i];
            node1.size++;
        }
        node1.children[mid] = node.children[mid];

        int j = 0;
        for (int i = mid + 1; i < node.size; i++) {
            node2.data[j] = node.data[i];
            node2.children[j] = node.children[i];
            node2.size++;
            j++;
        }
        node2.children[mid] = node.children[node.size];

        node1.leaf = node.leaf;
        node2.leaf = node.leaf;

        if (parent == null) {
            int temp = node.data[mid];
            node.data = new int[2 * t - 1];
            node.data[0] = temp;
            node.size = 1;
            node.leaf = false;
            node.children[0] = node1;
            node.children[1] = node2;
            node1.parent = node;
            node2.parent = node;
            if (key > temp) {
                return node2;
            }
            return node1;
        }

        int index = parent.size;
        int data = node.data[mid];
        for (int i = parent.size - 1; i > -1; i--) {
            if (data < parent.data[i]) {
                parent.data[i + 1] = parent.data[i];
                parent.children[i + 2] = parent.children[i + 1];
                index = i;
            } else {
                break;
            }
        }

        parent.data[index] = data;
        parent.children[index] = node1;
        parent.children[index + 1] = node2;
        parent.size++;
        node1.parent = parent;
        node2.parent = parent;
        if (key > data) {
            return node2;
        }
        return node1;
    }

    public void insert(int dato) {
        if (root == null) {
            NodoB node = new NodoB(t);
            insertData(node, dato);
            root = node;
            return;
        }

        NodoB temp = root;
        while (!temp.leaf || (temp.size == 2 * t - 1 && temp.children[0] == null)) {
            if (temp.size == 2 * t - 1) {
                temp = split(temp, dato);
                continue;
            }
            if (dato < temp.data[0]) {
                temp = temp.children[0];
                continue;
            }
            if (dato > temp.data[temp.size - 1]) {
                temp = temp.children[temp.size];
                continue;
            }
            for (int i = 1; i < temp.size; i++) {
                if (dato < temp.data[i]) {
                    temp = temp.children[i];
                    break;
                }
            }
        }
        insertData(temp, dato);
    }

    public void display(NodoB node, int level) {
        if (node == null) {
            return;
        }
        System.out.print("Level : " + level + " " + "Data : ");
        for (int i = 0; i < node.size; i++) {
            System.out.print(node.data[i] + " ");
        }
        System.out.println();
        if (node.leaf) {
            return;
        }
        for (int i = 0; i < node.size + 1; i++) {
            display(node.children[i], level + 1);
        }
    }

    public void insertar(int dato) {
        // If tree is empty 
        if (root == null) {

            root = new NodoB(t, true);
            root.data[0] = dato;
            root.size = 1;
        } else {

            if (root.size == 2 * t - 1) {

                NodoB s = new NodoB(t, false);

                s.children[0] = root;

                s.split(0, root);

                int i = 0;
                if (s.data[0] < dato) {
                    i++;
                }
                s.children[i].insertNonFull(dato);

                // Cambia raiz
                root = s;
            } else {
                root.insertNonFull(dato);
            }
        }

    }

    public void eliminar(int dato) {
        if (root == null) {
            System.out.println("Arbol vacio ");
            return;
        }

        // Call the remove function for root 
        root.remove(dato);

        // If the root node has 0 keys, make its first child as the new root 
        //  if it has a child, otherwise set root as NULL 
        if (root.size == 0) {
            NodoB tmp = root;
            if (root.leaf) {
                root = null;
            } else {
                root = root.children[0];
            }

            // Free the old root 
            tmp = null;
        }
        return;
    }

    public void traverse() {
 
        if (root != null) root.transverse();
    
    }
    

}
