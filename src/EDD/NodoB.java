package EDD;

/**
 *
 * @author Oscar C
 */
public class NodoB {

    int keys[];
    int t;
    NodoB[] C;
    NodoB parent;
    int n;
    boolean leaf;

    public NodoB(int t1, boolean leaf) {
        this.t = t1;
        this.leaf = leaf;

        this.keys = new int[(2 * t)- 1];
        this.C = new NodoB[2 * t];

    }


    public int findKey(int k) {
        int idx = 0;
        while (idx < n && keys[idx] < k) {
            ++idx;
        }
        return idx;
    }

    public void remove(int k) {
        int idx = findKey(k);

        if (idx < n && keys[idx] == k) {

            if (leaf) {
                removeFromLeaf(idx);
            } else {
                removeFromNonLeaf(idx);
            }
        } else {

            if (leaf) {
                System.out.println(k+ "No es una hoja");
                return;
            }

            boolean flag = ((idx == n) ? true : false);

            if (C[idx].n < t) {
                fill(idx);
            }

            if (flag && idx > n) {
                C[idx - 1].remove(k);
            } else {
                C[idx].remove(k);
            }
        }
        return;
    }

    public void removeFromLeaf(int k) {
        for (int i = k + 1; i < n; ++i) {
            keys[i - 1] = keys[i];
        }

        // Reduce the count of keys 
        n--;

        return;

    }

    public void removeFromNonLeaf(int idx) {
        int k = keys[idx];

        if (C[idx].n >= t) {
            int pred = getPred(idx);
            keys[idx] = pred;
            C[idx].remove(pred);
        } else if (C[idx + 1].n >= t) {
            int succ = getSucc(idx);
            keys[idx] = succ;
            C[idx + 1].remove(succ);
        } else {
            merge(idx);
            C[idx].remove(k);
        }
        return;
    }

    public int getPred(int k) {
        NodoB cur = C[k];
        while (!cur.leaf) {
            cur = cur.C[cur.n];
        }

        // Return el ultimo de la hoja
        return cur.keys[cur.n - 1];
    }

    public int getSucc(int k) {
        NodoB cur = C[k + 1];
        while (!cur.leaf) {
            cur = cur.C[0];
        }

        // Return el primero de la hoja
        return cur.keys[0];
    }

    public void fill(int idx) {
        if (idx != 0 && C[idx - 1].n >= t) {
            borrowFromPrev(idx);
        } else if (idx != n && C[idx - 1].n >= t) {
            borrowFromNext(idx);
        } else {
            if (idx != n) {
                merge(idx);
            } else {
                merge(idx - 1);
            }
        }
        return;
    }

    public void borrowFromPrev(int idx) {
        NodoB child = C[idx];
        NodoB sibling = C[idx - 1];

        for (int i = child.n - 1; i >= 0; --i) {
            child.keys[i + 1] = child.keys[i];
        }

        if (!child.leaf) {
            for (int i = child.n; i >= 0; --i) {
                child.C[i + 1] = child.C[i];
            }
        }

        child.keys[0] = keys[idx - 1];

        if (!child.leaf) {
            child.C[0] = sibling.C[sibling.n];
        }

        keys[idx - 1] = sibling.keys[sibling.n - 1];
        child.n += 1;
        sibling.n -= 1;

        return;
    }

    private void borrowFromNext(int idx) {
        NodoB child = C[idx];
        NodoB sibling = C[idx + 1];
        child.keys[child.n] = keys[idx];
        if (!child.leaf) {
            child.C[child.n + 1] = sibling.C[0];
        }
        keys[idx] = sibling.keys[0];
        for (int i = 1; i < sibling.n; ++i) {
            sibling.keys[i - 1] = sibling.keys[i];
        }
        if (!sibling.leaf) {
            for (int i = 1; i <= sibling.n; ++i) {
                sibling.C[i - 1] = sibling.C[i];
            }
        }
        child.n += 1;
        sibling.n -= 1;

        return;
    }

    private void merge(int idx) {
        NodoB child = C[idx];
        NodoB sibling = C[idx + 1];

        child.keys[t - 1] = keys[idx];

        for (int i = 0; i < sibling.n; ++i) {
            child.keys[i + t] = sibling.keys[i];
        }

        if (!child.leaf) {
            for (int i = 0; i <= sibling.n; ++i) {
                child.C[i + t] = sibling.C[i];
            }
        }

        for (int i = idx + 1; i < n; ++i) {
            keys[i - 1] = keys[i];
        }

        for (int i = idx + 2; i <= n; ++i) {
            C[i - 1] = C[i];
        }
        // Updating the key count of child and the current node 
        child.n += sibling.n + 1;
        n--;

        sibling = null;
        return;

    }

    public void insertNonFull(int dato) {
        // Initialize index as index of rightmost element 
        int i = n - 1;

        // If this is a leaf node 
        if (leaf == true) {
            // The following loop does two things 
            // a) Finds the location of new key to be inserted 
            // b) Moves all greater keys to one place ahead 
            while (i >= 0 && keys[i] > dato) {
                keys[i + 1] = keys[i];
                i--;
            }

            // Insert the new key at found location 
            keys[i + 1] = dato;
            n += 1;
        } else // If this node is not leaf 
        {
            while (i >= 0 && keys[i] > dato) {
                i--;
            }
            if (C[i + 1].n == (2 * t) - 1) {
                splitChild(i + 1, C[i + 1]);
                if (keys[i + 1] < dato) {
                    i++;
                }

            }
            C[i + 1].insertNonFull(dato);
        }
    }

    public void splitChild(int i, NodoB y) {
        NodoB z = new NodoB(y.t, y.leaf);
        z.n = t - 1;

        for (int j = 0; j < t - 1; j++) {
            z.keys[j] = y.keys[j + t];
        }

        if (y.leaf == false) {
            for (int j = 0; j < t; j++) {
                z.C[j] = y.C[j + t];
            }
        }

        y.n = t - 1;

        for (int j = n; j >= i + 1; j--) {
            C[j + 1] = C[j];
        }

        C[i + 1] = z;

        for (int j = n - 1; j >= i; j--) {
            keys[j + 1] = keys[j];
        }
        keys[i] = y.keys[t - 1];
        n += 1;
    }

    public String transverse() {
        int i;
        String regreso = "";
        for (i = 0; i < n; i++) {

            if (leaf == false) {
                C[i].transverse();
            }
            System.out.print("-" + keys[i]);
            regreso += "-" + keys[i];
        }
        if (leaf == false) {
            C[i].transverse();
        }
        return regreso;
    }

    private NodoB buscar(int dato) {
        int i = 0;
        while (i < n && dato > keys[i]) {
            i++;
        }
        if (keys[i] == dato) {
            return this;
        }

        if (leaf == true) {
            return null;
        }

        return C[i].buscar(dato);
    }

    NodoB search(int k) {
        int i = 0;
        while (i < n && k > keys[i]) {
            i++;
        }

        // If the found key is equal to k, return this node 
        if (keys[i] == k) {
            return this;
        }

        // If key is not found here and this is a leaf node 
        if (leaf == true) {
            return null;
        }

        // Go to the appropriate child 
        return C[i].search(k);
    }

}
