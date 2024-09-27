import java.util.ArrayList;

public class Tree23 {
    private Tree23Node root;
    private int t;

    public Tree23(int t) {
        this.root = new Tree23Node(true);
        this.t = t;
    }
    public boolean search(String value) {
        return searchRecursive(root, value);
    }

    private boolean searchRecursive(Tree23Node node, String value) {
        if (node == null) {
            return false;
        }

        for (int i = 0; i < node.keys.size(); i++) {
            if (value.compareTo(node.keys.get(i)) == 0) {
                return true;
            } else if (value.compareTo(node.keys.get(i)) < 0) {
                if (node.children.size() > i) {
                    return searchRecursive(node.children.get(i), value);
                }
            }
        }

        if (node.children.size() > node.keys.size()) {
            return searchRecursive(node.children.get(node.keys.size()), value);
        }

        return false;
    }



    public void splitChild(Tree23Node x, int i) {
        Tree23Node y = x.children.get(i);
        Tree23Node z = new Tree23Node(y.leaf);
        x.children.add(i + 1, z);
        x.keys.add(i, y.keys.get(this.t - 1));
        z.keys = new ArrayList<>(y.keys.subList(this.t, 2 * this.t - 1));
        y.keys = new ArrayList<>(y.keys.subList(0, this.t - 1));

        if (!y.leaf) {
            z.children = new ArrayList<>(y.children.subList(this.t, 2 * this.t));
            y.children = new ArrayList<>(y.children.subList(0, this.t));
        }
    }

    public void insert(String k) {
        Tree23Node root = this.root;
        if (root.keys.size() == (2 * this.t) - 1) {
            Tree23Node newRoot = new Tree23Node();
            this.root = newRoot;
            newRoot.children.add(0, root);
            this.splitChild(newRoot, 0);
            this.insertNonFull(newRoot, k);
        } else {
            this.insertNonFull(root, k);
        }
    }

    public void insertNonFull(Tree23Node x, String k) {
        int i = x.keys.size() - 1;

        if (x.leaf) {
            x.keys.add(null);
            while (i >= 0 && k.compareTo(x.keys.get(i)) < 0) {
                x.keys.set(i + 1, x.keys.get(i));
                i--;
            }
            x.keys.set(i + 1, k);
        } else {
            while (i >= 0 && k.compareTo(x.keys.get(i)) < 0) {
                i--;
            }
            i++;
            if (x.children.get(i).keys.size() == (2 * this.t) - 1) {
                this.splitChild(x, i);
                if (k.compareTo(x.keys.get(i)) > 0) {
                    i++;
                }
            }
            this.insertNonFull(x.children.get(i), k);
        }
    }
}



