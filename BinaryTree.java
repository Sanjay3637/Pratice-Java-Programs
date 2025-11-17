class Node 
{
    Integer val;
    Node l, r;

    public Node(Integer val) 
    {
        this.val = val;
        this.l = null;
        this.r = null;
    }
}

public class BinaryTree 
{

    public static Node insert(Node root, Integer val) 
    {
        if (root == null) 
            return new Node(val);
        if (val == null)
            return root;
        if (root.l == null) 
            root.l = new Node(val);
        else if (root.r == null) 
            root.r = new Node(val);
        else 
            insert(root.l, val);
        return root;
    }

    public static void inorder(Node root) 
    {
        if (root != null) 
        {
            inorder(root.l);
            System.out.print((root.val != null ? root.val : "null") + " ");
            inorder(root.r);
        }
    }

    public static void preorder(Node root) 
    {
        if (root != null) 
        {
            System.out.print((root.val != null ? root.val : "null") + " ");
            preorder(root.l);
            preorder(root.r);
        }
    }

    public static void postorder(Node root) 
    {
        if (root != null) 
        {
            postorder(root.l);
            postorder(root.r);
            System.out.print((root.val != null ? root.val : "null") + " ");
        }
    }

    public static void main(String[] args) 
    {
        Node root = null;
        root = insert(root, 1);
        root = insert(root, 2);
        root = insert(root, 4);
        root = insert(root, 5);
        root = insert(root, null);
        root = insert(root, null);
        root = insert(root, 3);
        root = insert(root, 6);
        root = insert(root, null);
        root = insert(root, null);
        root = insert(root, null);


        System.out.println("\nIn-order Traversal:");
        inorder(root);

        System.out.println("\nPre-order Traversal:");
        preorder(root);

        System.out.println("\nPost-order Traversal:");
        postorder(root);
    }
}







//          1
//         / \
//        2   3
//       /   /
//      4   6
//     /
//    5  