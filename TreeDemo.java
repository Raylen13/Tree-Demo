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
        System.out.print(root+ ", ");
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
        System.out.print(root+ ", ");
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
        System.out.print(root+ ", ");
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

/**
     * This method will traverse the entire binary tree to find its height.
     *
     * @return The height of the binary tree, with an empty tree having a height of 0.
     */
    public int height() {

        if (root == null)
            return 0;

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);

        Node currentNode;
        int lengthOfCurrentLevel;
        int totalHeight = 0;

        while (!queue.isEmpty()) {

            totalHeight++;

            lengthOfCurrentLevel = queue.size();

            for (int i = 0; i < lengthOfCurrentLevel; i++) {

                currentNode = queue.removeFirst();

                if (currentNode.left != null)
                    queue.add(currentNode.left);

                if (currentNode.right != null)
                    queue.add(currentNode.right);

            }

        }

        return totalHeight;

    }

    /**
     * This method creates a string representing the binary tree in a pyramid form.
     * This is the remade, better version.
     *
     * @return A string representing the binary tree in a pyramid format.
     */
    public String toTreeString() {

        if (root == null)
            return "No Tree";

        int height = this.height();

        /*
        This codes calculates the amount of spaces needed in between each number on each level of the tree
        and stores it in distances[].
        For example, distances[0] represents the amount of empty spaces that need to exist between each node
        at the very bottom of the tree, which is 1 space.
        Meanwhile, distances[1] will be the amount of spaces in between each node on the second level from
        the bottom, which is 5 spaces.
         */

        int[] distances = new int[height];
        distances[0] = 1;
        int prevDif = 2;

        for (int i = 1; i < distances.length; i++) {
            distances[i] = distances[i - 1] + (prevDif * 2);
            prevDif = distances[i] - distances[i - 1];
        }

        /*
        Here we start building the string.
        The process used is very similar to the one in the height method.
        We add all nodes to a queue, and level by level, we add each node's
        value to the string as well as adding empty spaces to keep everything
        orderly.
         */

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);

        Node currentNode;
        int lengthOfCurrentLevel;

        StringBuilder alpha = new StringBuilder();

        for (int currentLevel = 0; currentLevel < height; currentLevel++) {

            // We don't want to start the string with a \n, but we want to add one every other level.
            if (!alpha.isEmpty())
                alpha.append("\n");

            // The node closest to the left side of the screen only needs half the offset.
            alpha.append(spaces((distances[distances.length - currentLevel - 1]) / 2));

            lengthOfCurrentLevel = queue.size();

            for (int i = 0; i < lengthOfCurrentLevel; i++) {

                currentNode = queue.removeFirst();

                if (currentNode != null) {

                    /*
                    If are on a valid node, we add the value of the node to the pyramid and
                    some empty space after.
                    The amount of empty spaces was calculated earlier in the distances array.
                    We use the spaces() helper method to convert the number in the distances
                    array into a usable string we can append.
                     */
                    alpha.append(intTo3Char(currentNode.value));
                    alpha.append(spaces(distances[distances.length - currentLevel - 1]));

                    // We add the currents node's children to the queue, weather or not they are null.
                    queue.add(currentNode.left);
                    queue.add(currentNode.right);

                }

                else {

                    /*
                    If the node we are on is null, we still have to add some spacers
                    to maintain the pyramid shape.
                    Then we add the normal amount of spaces that appear in between nodes
                    and add more nulls to the queue.
                     */
                    alpha.append(intTo3Char(-1));
                    alpha.append(spaces(distances[distances.length - currentLevel - 1]));
                    queue.add(null);
                    queue.add(null);

                }

            }

        }

        return alpha.toString();

    }

    /**
     * A helper method for the toTreeString() method that when given a length,
     * will build a string made entirely of spaces that is the desired length.
     * @param amount The desired length of the output string.
     * @return A string made entirely of spaces of the given length.
     */
    private String spaces(int amount) {
        StringBuilder beta = new StringBuilder();
        for (int i = 0; i < amount; i++) {
            beta.append(" ");
        }
        return beta.toString();
    }

    /**
     * A helper method for the toTreeString() method that when given an integer,
     * returns a string containing the integer and is exactly three characters long.
     * Uses the "spacer" variable to fill the empty space around a number.
     * This method can not handle negative numbers or numbers bigger than 999.
     *
     * @param num The number being converted into a string. Must be between 0 and 999 inclusive.
     * @return A string representing the given number as a 3 character long string.
     */
    private String intTo3Char(int num) {
        StringBuilder gama = new StringBuilder();
        if (num > -1 && num < 10) {
            gama.append(spacer);
            gama.append(num);
            gama.append(spacer);
        } else if (num > 9 && num < 100) {
            gama.append(spacer);
            gama.append(num);
        } else if (num > 99 && num < 1000) {
            gama.append(num);
        } else {
            gama.append(spacer);
            gama.append(spacer);
            gama.append(spacer);
        }
        return gama.toString();
    }

    /**
     * This character is used to clean up the output of the toTreeString()
     * method by making it clear how much space each number takes up.
     */
    private static final char spacer = '.';   
   
   
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

        System.out.println("tree string : ");
        System.out.println(t1.toTreeString());
        System.out.println();

        t1.delete(t1.root, 18);
        System.out.println("after deleting 18 :   ");
        System.out.println(t1.toTreeString());
        System.out.println();

        t1.delete(t1.root, 90);
        System.out.println("after deleting 90 :   ");
        System.out.println(t1.toTreeString());
        System.out.println();
    }
}