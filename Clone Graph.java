// Time Complexity : O(N + E)
// N = number of nodes, E = number of edges
// We visit each node and edge exactly once.

// Space Complexity : O(N)
// We use a HashMap to store N cloned nodes.
// Also, the recursive call stack may go up to O(N) in the worst case (deepest traversal).

// Did this code successfully run on Leetcode : Yes

// Any problem you faced while coding this :
// Initially forgot to call the dfs(node) inside the cloneGraph() method,
// which caused the cloning to not happen. Once that was fixed, it worked as expected.
class Solution {

    // HashMap to keep track of original node -> cloned node mapping
    HashMap<Node, Node> map;

    // Main method to clone the graph
    public Node cloneGraph(Node node) {
        // Base case: if the input node is null, return null
        if (node == null)
            return null;

        // Initialize the map
        this.map = new HashMap<>();

        // Start DFS traversal and cloning
        dfs(node);

        // Return the clone of the starting node
        return map.get(node);
    }

    // Recursive DFS method
    private void dfs(Node node) {
        // If this node is already cloned, return
        if (map.containsKey(node))
            return;

        // Create a clone of the current node
        Node newNode = new Node(node.val);

        // Save this clone to the map
        map.put(node, newNode);

        // Iterate over the neighbors
        for (Node neigh : node.neighbors) {
            dfs(neigh); // Recursively clone neighbor if not already done
            map.get(node).neighbors.add(map.get(neigh)); // Add cloned neighbor to current node’s clone
        }
    }
}


// Time Complexity : O(N + E)
// N = number of nodes, E = number of edges
// Each node and edge is visited once in the BFS traversal.

// Space Complexity : O(N)
// We use a HashMap to store the mapping for each node (N entries)
// and a queue for BFS traversal that may hold up to N nodes.

// Did this code successfully run on Leetcode : Yes

// Any problem you faced while coding this :
// Initially used `push()` instead of `add()` for the Queue, and
// accidentally added the neighbor to itself. Fixed both issues.
class Solution {

    // HashMap to store original node -> cloned node mapping
    HashMap<Node, Node> map;

    public Node cloneGraph(Node node) {
        // Base case: if input node is null, return null
        if (node == null)
            return null;

        this.map = new HashMap<>();
        Queue<Node> q = new LinkedList<>();

        // Create the clone of the input node and enqueue the original node
        Node copyNode = new Node(node.val);
        map.put(node, copyNode);
        q.add(node);

        // Perform BFS
        while (!q.isEmpty()) {
            Node curr = q.poll();

            // Traverse all neighbors
            for (Node neigh : curr.neighbors) {
                if (!map.containsKey(neigh)) {
                    // Clone the neighbor if it hasn't been cloned yet
                    map.put(neigh, new Node(neigh.val));
                    q.add(neigh);
                }

                // Link the clone of the neighbor to the clone of the current node
                map.get(curr).neighbors.add(map.get(neigh));
            }
        }

        // Return the clone of the starting node
        return copyNode;
    }
}
