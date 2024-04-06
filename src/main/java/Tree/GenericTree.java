package Tree;

import java.util.Stack;

public class GenericTree {

    public static void main(String[] args) {
        int[] arr = {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1, -1, 90, -1, -1, 40, 100, -1, -1, -1};
        Stack<Node> st = new Stack<>();
        Node r = null;
        for (int j : arr) {
            if (j == -1) {
                st.pop();
            } else {
                Node temp = new Node();
                temp.data = j;
                if (st.empty()) {
                    r = temp;
                } else {
                    st.peek().children.add(temp);
                }
                st.push(temp);
            }
        }
      //  display(r);
        int size = sizeOfTree(r);
        System.out.println("Tree Size is: " + size);
        int maxSize = maxOfTrees(r);
        System.out.println("Max of the tree: " + maxSize);

        int height = heightOfTree(r);
        System.out.println("Height of tree: "+height);
        travversal(r);
    }

    private static void travversal(Node root) {
    // euler's left on the way deep in the recursion, Node's pre area.
        System.out.println("Node pre "+root.data);
        for (Node child:root.children){
            System.out.println("Edge pre "+root.data+ " -- "+child.data );
            travversal(child);
            System.out.println("Edge post "+root.data +" -- "+child.data);
        }
     // euler's right on the way to out of the recursion
        System.out.println("Node post "+root.data);
    }

    private static int heightOfTree(Node r) {
        int height = -1;
        for (Node child: r.children){
            int h = heightOfTree(child);
            height = Math.max(h,height);
        }
        height+=1;
        return height;
    }

    private static int maxOfTrees(Node r) {
        int height = Integer.MIN_VALUE;
        for (Node child : r.children) {
            int max1 = maxOfTrees(child);
            height = Math.max(max1, height);
        }

        height = Math.max(height, r.data);
        return height;
    }

    private static int sizeOfTree(Node r) {
        int size = 0;

        for (Node child : r.children) {
            int i = sizeOfTree(child);
            size += i;
        }
        size = size + 1;
        return size;
    }

    private static void display(Node node) {
        StringBuilder str = new StringBuilder(node.data + " ");
        for (Node child : node.children) {
            str.append(child.data).append(",");
        }
        System.out.println(str + ".");
        for (Node child : node.children) {
            display(child);
        }
    }


}
