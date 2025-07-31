// Approach: BFS (Breadth-First Search)
// Use BFS to explore all possible strings formed by removing one parenthesis at a time.
// For each level in the BFS:
// Stop further removals when a valid string is found (shortest solution).
// Use a HashSet to avoid processing duplicate strings.
// Return all valid strings from the first level where valid parentheses expressions are found.
// Time and Space Complexity
// Time Complexity: O(2^n) in the worst case (generating all substrings by removing one character at a time), but pruning via early flag stop reduces this.
// Space Complexity: O(n * 2^n) for storing substrings in the queue and set.

class Solution {

    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        HashSet<String> set = new HashSet<>(); // To avoid revisiting same string
        Queue<String> q = new LinkedList<>();
        q.add(s);
        set.add(s);

        boolean flag = false; // Becomes true when a valid expression is found at current level

        while (!q.isEmpty() && !flag) {
            int size = q.size(); // Process all nodes at the current level

            for (int i = 0; i < size; ++i) {
                String curr = q.poll();

                // Check if current string is valid
                if (isValid(curr)) {
                    result.add(curr);
                    flag = true; // Found valid string, do not go to deeper levels
                }

                // Generate next level only if no valid found yet
                if (!flag) {
                    for (int k = 0; k < curr.length(); ++k) {
                        char c = curr.charAt(k);
                        if (c != '(' && c != ')') continue;

                        // Remove one parenthesis at position k
                        String newStr = curr.substring(0, k) + curr.substring(k + 1);
                        if (!set.contains(newStr)) {
                            q.add(newStr);
                            set.add(newStr);
                        }
                    }
                }
            }
        }

        return result;
    }

    // Helper to check if a string has balanced parentheses
    private boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(') count++;
            if (c == ')') count--;
            if (count < 0) return false; // Too many closing parens
        }
        return count == 0; // True if all parentheses are balanced
    }
}
