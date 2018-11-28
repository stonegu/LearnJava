package binarySearchTree;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BinaryTreeTest {
    @DataProvider(name = "mockTree")
    public Object[][] getMockTree() {
    /*
                   6
               4      8
            3   5   7   9
    */
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.add(6);
        binaryTree.add(4);
        binaryTree.add(8);
        binaryTree.add(3);
        binaryTree.add(5);
        binaryTree.add(7);
        binaryTree.add(9);

        return new Object[][] {
                {
                        binaryTree
                }
        };
    }

    @Test(dataProvider = "mockTree")
    public void testCreateTree(BinaryTree binaryTree) {

        StringBuilder sb = new StringBuilder();
        binaryTree.preOrderTraversal(binaryTree.getRoot(), sb);

        assertThat(binaryTree).isNotNull();
        assertThat(sb.toString()).isEqualTo("6435879");
    }

    @Test(dataProvider = "mockTree")
    public void testContainsNode(BinaryTree binaryTree) {
        assertThat(binaryTree.containsNode(6)).isEqualTo(true);
        assertThat(binaryTree.containsNode(4)).isEqualTo(true);
        assertThat(binaryTree.containsNode(3)).isEqualTo(true);
        assertThat(binaryTree.containsNode(5)).isEqualTo(true);
        assertThat(binaryTree.containsNode(8)).isEqualTo(true);
        assertThat(binaryTree.containsNode(7)).isEqualTo(true);
        assertThat(binaryTree.containsNode(9)).isEqualTo(true);
        assertThat(binaryTree.containsNode(1)).isEqualTo(false);
    }

    @Test(dataProvider = "mockTree")
    public void testDeleteNode(BinaryTree binaryTree) {
        binaryTree.delete(3);
        StringBuilder sb = new StringBuilder();
        binaryTree.preOrderTraversal(binaryTree.getRoot(), sb);
        assertThat(sb.toString()).isEqualTo("645879");

        binaryTree.delete(4);
        sb = new StringBuilder();
        binaryTree.preOrderTraversal(binaryTree.getRoot(), sb);
        assertThat(sb.toString()).isEqualTo("65879");

        binaryTree.delete(6);
        sb = new StringBuilder();
        binaryTree.preOrderTraversal(binaryTree.getRoot(), sb);
        assertThat(sb.toString()).isEqualTo("7589");

    }



}
