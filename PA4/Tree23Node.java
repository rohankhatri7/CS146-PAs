import java.util.ArrayList;

public class Tree23Node {
    public ArrayList<String> keys = new ArrayList<>();
    public ArrayList<Tree23Node> children = new ArrayList<>();
    public boolean leaf;

    public Tree23Node() {
        this.leaf = false;
    }

    public Tree23Node(boolean leaf) {
        this.leaf = leaf;
    }
}
