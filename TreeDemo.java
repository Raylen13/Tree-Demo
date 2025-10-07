class Node{
   int value;
   Node left, right;
   
   public Node(int value){
      this.value = value;
      left = null;
      right = null;
   }

    /**
     * Returns a string containing the value of the node and nothing else.
     * @return A string containing the value of the node.
     */
    public String toString() {
        return ""+value;
    }

}

class BinarySearchTree{

   Node root;
   
   
   /**
     * Inserts the given value into the binary tree.
     * For the sake of proper recursion, the root of the tree must also be given as a parameter.
     * @param root The root node of the tree that will gain a new value.
     * @param value The value that will be added to the binary tree.
     * @return The node that has been added to the tree.
     */
    public Node insert(Node root, int value){

        // Checking if the root of the tree is null.
        // This check should only succeed if the root of the whole tree is null.
        if(root == null){
            this.root = new Node(value);
            return root;
        }

        // Value is less than current node.
        if(value < root.value){

            // Less than base case.
            if (root.left == null)
                root.left = new Node(value);

            // Less than recursive step.
            else
                insert(root.left, value);

        }

        // Value is greater than current node.
        else{

            // Greater than base case.
            if (root.right == null)
                root.right = new Node(value);

            // Greater than recursive step.
            else
                insert(root.right, value);
        }

        // Return statement. It is unclear why it is necessary for this method to return anything.
        return root;
    }
   
   
   
   /**
     * This recursive method traverses the binary tree and prints out a list of all values in "Pre-order"
     * @param root The node at which the traversal will start.
     */
    public void preOrderTraversal(Node root) {
        if (root==null)
            return;
        System.out.println(root+ ", ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

   
   
   /**
     * This recursive method traverses the binary tree and prints out a list of all values "In-order"
     * @param root The node at which the traversal will start.
     */
    public void inOrderTraversal(Node root) {
        if (root==null)
            return;
        inOrderTraversal(root.left);
        System.out.println(root+ ", ");
        inOrderTraversal(root.right);
    }
   
   
   
   /**
     * This recursive method traverses the binary tree and prints out a list of all values in "Post-order"
     * @param root The node at which the traversal will start.
     */
    public void postOrderTraversal(Node root) {
        if (root==null)
            return;
        inOrderTraversal(root.left);
        inOrderTraversal(root.right);
        System.out.println(root+ ", ");
    }
   
   
   
   /**
     * This recursive method will search the binary tree for a node with the same value as the key given.
     * @param root The node at which the search will start.
     * @param key The value that is being searched for.
     * @return True if a node with the specified value was found and false if not.
     */
    public boolean find(Node root, int key) {
        if (root==null)
            return false;
        if (root.value==key)
            return true;
        return ( find(root.left, key) || find(root.right, key) );
    }
   
   
   
   /**
     * This recursive method will return the node in the binary tree with the smallest value.
     * Also known as the left most node.
     * @param root The node at which the search will start.
     * @return The node with the smallest value in the binary tree.
     */
    public int getMin(Node root) {
        if (root.left==null)
            return root.value;
        return getMin(root.left);
    }
  
  
  
   /**
     * This recursive method will return the node in the binary tree with the largest value.
     * Also known as the right most node.
     * @param root The node at which the search will start.
     * @return The node with the largest value in the binary tree.
     */
    public int getMax(Node root) {
        if (root.right==null)
            return root.value;
        return getMax(root.right);
    }
   
   
   
   /*
   this method will not compile until getMax
   is implemented
   */
   public Node delete(Node root, int key){
      
      if(root == null){
         return root;
      }else if(key < root.value){
         root.left = delete(root.left, key);
      }else if(key > root.value){
         root.right = delete(root.right, key);
      }else{
         //node has been found
         if(root.left==null && root.right==null){
            //case #1: leaf node
            root = null;
         }else if(root.right == null){
            //case #2 : only left child
            root = root.left;
         }else if(root.left == null){
            //case #2 : only right child
            root = root.right;
         }else{
            //case #3 : 2 children
            root.value = getMax(root.left);
            root.left = delete(root.left, root.value);
         }
      }
      return root;  
   }
   
   
   
}



public class TreeDemo{
   public static void main(String[] args){

        BinarySearchTree t1  = new BinarySearchTree();
        t1.insert(t1.root, 24);
        t1.insert(t1.root, 80);
        t1.insert(t1.root, 18);
        t1.insert(t1.root, 9);
        t1.insert(t1.root, 90);
        t1.insert(t1.root, 22);

        System.out.print("pre-order :   ");
        t1.preOrderTraversal(t1.root);
        System.out.println();

        System.out.print("in-order :   ");
        t1.inOrderTraversal(t1.root);
        System.out.println();

        System.out.print("post-order :   ");
        t1.postOrderTraversal(t1.root);
        System.out.println();

        System.out.print("smallest node : " + t1.getMin(t1.root));
        System.out.println();

        System.out.print("largest node : " + t1.getMax(t1.root));
        System.out.println();

        t1.delete(t1.root, 22);
        System.out.print("in-order after deleting 22 :   ");
        t1.inOrderTraversal(t1.root);
        System.out.println();

        t1.delete(t1.root, 90);
        System.out.print("in-order after deleting 90 :   ");
        t1.inOrderTraversal(t1.root);
        System.out.println();
    }
}