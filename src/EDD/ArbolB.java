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
//Pruebas
    NodoB search(int k){
         return (root == null)? null : root.search(k); 
    }
   
//Metodos finales

    public void insert(int k) {
        // If tree is empty 
        if (root == null) {

            root = new NodoB(t, true);
            root.keys[0] = k;
            root.n = 1;
        } else {
            if (root.n == ((2*t)- 1)) {

                NodoB s = new NodoB(t, false);

                s.C[0] = root;

                s.splitChild(0, root);

                int i = 0;
                if (s.keys[0] < k) {
                    i++;
                }
                s.C[i].insertNonFull(k);

                // Cambia raiz
                root = s;
            } else {
                root.insertNonFull(k);
            }
        }

    }

    public void remove(int dato) {
        if (root == null) {
            System.out.println("Arbol vacio ");
            return;
        }

        // Call the remove function for root 
        root.remove(dato);

        // If the root node has 0 keys, make its first child as the new root 
        //  if it has a child, otherwise set root as NULL 
        if (root.n == 0) {
            NodoB tmp = root;
            if (root.leaf) {
                root = null;
            } else {
                root = root.C[0];
            }

            // Free the old root 
            tmp = null;
        }
        return;
    }

    public String traverse() {

        if (root != null) {
            return root.transverse();
        }else{
            System.out.println("Esta mierda esta mal");
            return "Vacio";
        }

    }
    
    public int[] datos(){
        String btree = traverse();
        String[] arr = btree.split("-"); 
        int[] nuevo = new int[arr.length-1];
        for (int i = 1; i < arr.length; i++) {
            nuevo[i-1] =Integer.parseInt( arr[i]);
        }
        return nuevo;
        
    }
    
    
    

}
