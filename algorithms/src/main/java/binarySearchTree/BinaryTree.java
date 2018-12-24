package binarySearchTree;

// https://www.baeldung.com/java-binary-tree

public class BinaryTree {
    private Node root;

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            current = new Node(value);
        } else {
            if (value < current.getValue()) {
                current.setLeft(addRecursive(current.getLeft(), value));
            } else if (value > current.getValue()) {
                current.setRight(addRecursive(current.getRight(), value));
            } else {
                // value already exist
            }
        }
        return current;
    }

    public void add(int value) {
        root = addRecursive(root, value);
    }

}
