public class BinaryTree {
    public Node root;

    public BinaryTree() {
        root = null;
    }

    public void insert(String value) {
        root = insertRecursive(root, value);
    }

    public Node insertRecursive(Node node, String value) {
        if (node == null) {
            return new Node(value);
        }

        if (value.compareTo(node.value) < 0) {
            node.left = insertRecursive(node.left, value);
        } else {
            node.right = insertRecursive(node.right, value);
        }

        return node;
    }

    public void traverse() {
        traverseInOrder(root);
    }

    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(node.value + "\n");
            traverseInOrder(node.right);
        }
    }
    public boolean search(String value){
        return (searchRecursive(root, value) != null);
    }
    public Node searchRecursive(Node node, String value){
        if (node == null || node.value.equals(value)) {
            return node;
        }

        if (value.compareTo(node.value) < 0) {
            return searchRecursive(node.left, value);
        } else {
            return searchRecursive(node.right, value);
        }


    }
}
