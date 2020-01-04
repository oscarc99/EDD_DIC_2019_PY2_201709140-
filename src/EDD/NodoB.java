package EDD;

/**
 *
 * @author Oscar C
 */
public class NodoB {

    int[] data;
    int t;
    NodoB[] children;
    NodoB parent;
    int size;
    boolean leaf;

    public NodoB(int t1, boolean leaf) {
        this.t = t1;
        this.leaf = leaf;

        this.data = new int[2 * t - 1];
        this.children = new NodoB[2 * t];

    }

    public NodoB(int t) {
        this.data = new int[2 * t - 1];
        this.children = new NodoB[2 * t];
        this.parent = null;
        this.leaf = true;
    }

    public int findKey(int k) {
        int idx = 0;
        while (idx < size && data[idx] < k) {
            ++idx;
        }
        return idx;
    }

    public void remove(int k) {
        int idx = findKey(k);

        if (idx < size && data[idx] == k) {

            if (leaf) {
                removeFromLeaf(idx);
            } else {
                removeFromNonLeaf(idx);
            }
        } else {

            if (leaf) {

                return;
            }

            boolean flag = ((idx == size) ? true : false);

            if (children[idx].size < t) {
                fill(idx);
            }

            if (flag && idx > size) {
                children[idx - 1].remove(k);
            } else {
                children[idx].remove(k);
            }
        }
        return;
    }

    public void removeFromLeaf(int k) {
        for (int i = k + 1; i < size; ++i) {
            data[i - 1] = data[i];
        }

        // Reduce the count of keys 
        size--;

        return;

    }

    public void removeFromNonLeaf(int index) {
        int k = data[index];

        if (children[index].size >= t) {
            int pred = getPred(index);
            data[index] = pred;
            children[index].remove(pred);
        } else if (children[index + 1].size >= t) {
            int succ = getSucc(index);
            data[index] = succ;
            children[index + 1].remove(succ);
        } else {
            merge(index);
            children[index].remove(k);
        }
        return;
    }

    public int getPred(int k) {
        NodoB cur = children[k];
        while (!cur.leaf) {
            cur = cur.children[cur.size];
        }

        // Return el ultimo de la hoja
        return cur.data[cur.size - 1];
    }

    public int getSucc(int k) {
        NodoB cur = children[k + 1];
        while (!cur.leaf) {
            cur = cur.children[0];
        }

        // Return el primero de la hoja
        return cur.data[0];
    }

    public void fill(int k) {
        if (k != 0 && children[k - 1].size >= t) {
            borrowFromPrev(k);
        } else if (k != size && children[k - 1].size >= t) {
            borrowFromNext(k);
        } else {
            if (k != size) {
                merge(k);
            } else {
                merge(k - 1);
            }
        }
        return;
    }

    public void borrowFromPrev(int k) {
        NodoB child = children[k];
        NodoB sibling = children[k - 1];

        for (int i = child.size - 1; i >= 0; --i) {
            child.data[i + 1] = child.data[i];
        }

        if (!child.leaf) {
            for (int i = child.size; i >= 0; --i) {
                child.children[i + 1] = child.children[i];
            }
        }

        child.data[0] = data[k - 1];

        if (!child.leaf) {
            child.children[0] = sibling.children[sibling.size];
        }

        data[k - 1] = sibling.data[sibling.size - 1];
        child.size += 1;
        sibling.size -= 1;

        return;
    }

    private void borrowFromNext(int k) {
        NodoB child = children[k];
        NodoB sibling = children[k + 1];
        child.data[(child.size)] = data[k];
        if (!(child.leaf)) {
            child.children[(child.size) + 1] = sibling.children[0];
        }
        data[k] = sibling.data[0];
        for (int i = 1; i < sibling.size; ++i) {
            sibling.data[i - 1] = sibling.data[i];
        }
        if (!sibling.leaf) {
            for (int i = 1; i <= sibling.size; ++i) {
                sibling.children[i - 1] = sibling.children[i];
            }
        }
        child.size += 1;
        sibling.size -= 1;

        return;
    }

    private void merge(int k) {
        NodoB child = children[k];
        NodoB sibling = children[k + 1];

        child.data[t - 1] = data[k];

        for (int i = 0; i < sibling.size; ++i) {
            child.data[i + t] = sibling.data[i];
        }

        if (!child.leaf) {
            for (int i = 0; i <= sibling.size; ++i) {
                child.children[i + t] = sibling.children[i];
            }
        }

        for (int i = k + 1; i < size; ++i) {
            data[i - 1] = data[i];
        }

        for (int i = k + 2; i <= size; ++i) {
            children[i - 1] = children[i];
        }
        // Updating the key count of child and the current node 
        child.size += sibling.size + 1;
        size--;

        sibling = null;
        return;

    }

    public void insertNonFull(int dato) {
        // Initialize index as index of rightmost element 
        int i = size - 1;

        // If this is a leaf node 
        if (leaf == true) {
            // The following loop does two things 
            // a) Finds the location of new key to be inserted 
            // b) Moves all greater keys to one place ahead 
            while (i >= 0 && data[i] > dato) {
                data[i + 1] = data[i];
                i--;
            }

            // Insert the new key at found location 
            data[i + 1] = dato;
            size += 1;
        } else // If this node is not leaf 
        {
            while (i >= 0 && data[i] > dato) {
                i--;
            }
            if (children[i + 1].size == 2 * t - 1) {
                split(i + 1, children[i + 1]);
                if (data[i + 1] < dato) {
                    i++;
                }

            }
            children[i + 1].insertNonFull(dato);
        }
    }

    public void split(int i, NodoB y) {
        NodoB z = new NodoB(y.t, y.leaf);
        z.size = t - 1;

        for (int j = 0; j < t - 1; j++) {
            z.data[j] = y.data[j + t];
        }

        if (y.leaf == false) {
            for (int j = 0; j < t; j++) {
                z.children[j] = y.children[j + t];
            }
        }

        y.size = t - 1;

        for (int j = size; j >= i + 1; j--) {
            children[j + 1] = children[j];
        }

        children[i + 1] = z;

        for (int j = size - 1; j >= i; j--) {
            data[j + 1] = data[j];
        }
        data[i] = y.data[t - 1];
        size += 1;
    }

    public void transverse() {
        int i;
        for (i = 0; i < size; i++) {

            if (leaf == false) {
                children[i].transverse();
            }
            System.out.print(" - " + data[i]);
        }
        if (leaf == false) {
            children[i].transverse();
        }
    }

    private NodoB buscar(int dato) {
        int i = 0;
        while (i < size && dato > data[i]) {
            i++;
        }
        if (data[i] == dato) 
        return this;
        
         if (leaf == true) 
        return null; 
         
         
         return children[i].buscar(dato);
    }
    
    
    

}
