package Week5Task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BSTNode {
    int data;
    BSTNode left, right;

    public BSTNode(int data) {
        this.data = data;
        left = right = null;
    }
}
class BST {
    private BSTNode root;
    public BST() {
        root = null;
    }
    public void insert(int data) {
        root = insertRec(root, data);
    }
    private BSTNode insertRec(BSTNode root, int data) {
        if (root == null) {
            root = new BSTNode(data);
            return root;
        }
        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }
        return root;
    }
    public void changeRoot(int newRootData) {
        BSTNode newRoot = findNode(root, newRootData);
        if (newRoot == null) {
            System.out.println("Node with value " + newRootData + " not found. Inserting it as the new root.");
            insert(newRootData);
            newRoot = findNode(root, newRootData);
        }

        List<Integer> allNodes = new ArrayList<>();
        collectNodes(root, allNodes);

        root = newRoot;
        root.left = null;
        root.right = null;

        for (int node : allNodes) {
            insert(node);
        }

        System.out.println("BST Structure after changing root to " + newRootData + ":");
        printTree();
    }

    private BSTNode findNode(BSTNode node, int data) {
        if (node == null) return null;
        if (node.data == data) return node;
        if (data < node.data) return findNode(node.left, data);
        return findNode(node.right, data);
    }

    private void collectNodes(BSTNode node, List<Integer> allNodes) {
        if (node == null) return;
        collectNodes(node.left, allNodes);
        allNodes.add(node.data);
        collectNodes(node.right, allNodes);
    }

    public void printTree() {
        List<List<String>> lines = new ArrayList<>();
        List<BSTNode> level = new ArrayList<>();
        List<BSTNode> next = new ArrayList<>();

        level.add(root);
        int nn = 1;
        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<>();

            nn = 0;
            for (BSTNode n : level) {
                if (n == null) {
                    line.add(null);
                    next.add(null);
                    next.add(null);
                } else {
                    String aa = "(" + n.data + ")";
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.left);
                    next.add(n.right);
                    if (n.left != null) nn++;
                    if (n.right != null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;

            lines.add(line);

            List<BSTNode> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perPiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perPiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = '└';
                        }
                    }
                    System.out.print(c);

                    if (line.get(j) == null) {
                        for (int k = 0; k < perPiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }
            for (int j = 0; j < line.size(); j++) {
                String f = line.get(j);
                if (f == null) f = "";
                int gap1 = (int) Math.ceil(perPiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perPiece / 2f - f.length() / 2f);

                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            perPiece /= 2;
        }
    }
}

public class BSTApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BST bst = new BST();
        System.out.println("Enter node values one by one (type 'exit' to stop):");
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Insert a new node");
            System.out.println("2. Change root node");
            System.out.println("3. Print BST");
            System.out.println("4. Exit");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            switch (input) {
                case "1":
                    System.out.println("Enter node value to insert:");
                    String nodeInput = scanner.nextLine().trim();
                    try {
                        int node = Integer.parseInt(nodeInput);
                        bst.insert(node);
                        System.out.println("BST Structure after insertion of " + node + ":");
                        bst.printTree();
                    } catch (NumberFormatException exception) {
                        System.out.println("Invalid input. Please enter an integer value.");
                    }
                    break;
                case "2":
                    System.out.println("Enter the new root node value:");
                    String rootInput = scanner.nextLine().trim();
                    try {
                        int newRoot = Integer.parseInt(rootInput);
                        bst.changeRoot(newRoot);
                    } catch (NumberFormatException exception) {
                        System.out.println("Invalid input. Please enter an integer value.");
                    }
                    break;
                case "3":
                    bst.printTree();
                    break;
                case "4":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
}
