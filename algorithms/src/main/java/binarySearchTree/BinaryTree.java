package binarySearchTree;

// https://www.baeldung.com/java-binary-tree

public class BinaryTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

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

    public void preOrderTraversal(Node root, StringBuilder sb) {
        if (root != null) {
            sb.append(root.getValue());
            preOrderTraversal(root.getLeft(), sb);
            preOrderTraversal(root.getRight(), sb);
        }
    }

    private boolean containsNodeRecursive(Node current, int value) {
        if (current == null) {
            return false;
        }
        if (current.getValue() == value) {
            return true;
        }
        return current.getValue() > value ? containsNodeRecursive(current.getLeft(), value) : containsNodeRecursive(current.getRight(), value);
    }

    public boolean containsNode (int value) {
        return containsNodeRecursive(root, value);
    }

    private Node deleteRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }
        if (current.getValue() == value) {
            // a node has no children
            if (current.getLeft() == null && current.getRight() == null) {
                return null;
            }
            // a node has exactly one child
            if (current.getLeft() == null) {
                return current.getRight();
            }
            if (current.getRight() == null) {
                return current.getLeft();
            }
            // a node has two children
            int smallestValue = findSmallestValue(current.getRight());
            current.setValue(smallestValue);
            current.setRight(deleteRecursive(current.getRight(), smallestValue));
            return current;
        }
        if (current.getValue() > value) {
            current.setLeft(deleteRecursive(current.getLeft(), value));
        }
        if (current.getValue() < value) {
            current.setRight(deleteRecursive(current.getRight(), value));
        }
        return current;
    }

    private int findSmallestValue(Node root) {
        return root.getLeft() == null ? root.getValue() : findSmallestValue(root.getLeft());
    }

    public void delete(int value) {
        this.root = deleteRecursive(this.root, value);
    }
}
