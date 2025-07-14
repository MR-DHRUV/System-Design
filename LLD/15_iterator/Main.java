import java.util.Stack;

// Iterator interface
interface Iterator<T> {

    boolean hasNext();

    T next();
}

// Iterable Collection interface
interface IterableCollection<T> {

    Iterator<T> createIterator();
}

class BST implements IterableCollection<Integer> {

    private class Node {
        int value;
        Node left, right;

        Node(int value) {
            this.value = value;
            left = right = null;
        }
    }

    private Node root;

    private int height(Node root)
    {
        if(root == null)
            return 0;

        return 1+Math.max(height(root.left), height(root.right));
    }

    private Node insertRec(Node root, int value) {
        if (root == null)
            return new Node(value);

        if (root.value > value)
            root.left = insertRec(root.left, value);
        else if (root.value < value)
            root.right = insertRec(root.right, value);

        return root;
    }
    
    private Node deleteRec(Node root, int value) {
        if (root == null)
            return null;

        if (root.value > value)
            root.left = deleteRec(root.left, value);
        else if (root.value < value)
            root.right = deleteRec(root.right, value);
        else {
            // base case
            if (root.left == null && root.right == null)
                return null;

            if (height(root.left) > height(root.right)) {
                // inorder pred
                Node pred = root.left;
                while (pred.right != null)
                    pred = pred.right;

                root.value = pred.value;
                deleteRec(root.left, pred.value);
            } else {
                Node succ = root.right;
                while (succ.left != null) {
                    succ = succ.left;
                }

                root.value = succ.value;
                deleteRec(root.right, succ.value);
            }
        }

        return root;
    }

    private Node inorderRec(Node root) {
        if (root == null)
            return null;

        inorderRec(root.left);
        System.out.print(root.value + " ");
        inorderRec(root.right);

        return root;
    }

    public BST() {
        root = null;
    }

    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    public void insert(int value) {
        root = insertRec(root, value);
    }

    public void delete(int value) {
        root = deleteRec(root, value);
    }

    @Override
    public Iterator<Integer> createIterator()
    {
        return new BSTIterator(root);
    }

    private class BSTIterator implements Iterator<Integer> {
        private Node curr;
        Stack<Node> st;

        public BSTIterator(Node root) {
            curr = root;
            st = new Stack<>();
        }

        @Override
        public boolean hasNext() {
            return !st.isEmpty() || curr != null;
        }

        @Override
        public Integer next() {
            if(!hasNext())
                return -1;

            while(curr != null)
            {
                st.push(curr);
                curr = curr.left;
            }

            curr = st.pop();

            int res = curr.value;
            curr = curr.right;
            
            return res;
        }
    }
};

public class Main {
    public static void main(String[] args) {
        BST bst = new BST();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(3);
        bst.insert(7);
        bst.insert(12);
        bst.insert(18);

        Iterator<Integer> iterator = bst.createIterator();
        
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }
}