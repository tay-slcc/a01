package a01;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * Exercises to practice the use of stacks and queues.
 * 
 * @author CSIS 2420 Starter Code + [Your Name]
 */
public class StackQueueChallenges {

	
	/**
	 * Checks if the brackets in the input queue are balanced.
	 * A sequence is balanced if each opening bracket has a corresponding 
	 * closing bracket in the correct order.
	 * 
	 * Examples of balanced sequences:
	 * - Queue: [( { [ ] } )] => true
	 * - Queue: a{(B) {[]C}}d => true
	 * - Queue: empty queue => true
	 * 
	 * Examples of unbalanced sequences:
	 * - Queue: ([{ ] }) => false
	 * - Queue: [{ }]] () => false
	 * - Queue: { => false
	 * 
	 * @param q the input queue containing the characters of the expression
	 * @return true if the brackets are balanced, false otherwise
	 */
    public static boolean balanceBrackets(Queue<Character> q) {
        Stack<Character> stack = new Stack<>();

        while (!q.isEmpty()) {
            char c = q.dequeue();
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) return false;
                char open = stack.pop();
                if ((c == ')' && open != '(') ||
                    (c == ']' && open != '[') ||
                    (c == '}' && open != '{')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }


	/**
	 * Reverses the order of words in the input queue.
	 * Words are defined as any sequence of characters that does not include spaces.
	 * Words in the input queue can be separated by one or more spaces. However, 
	 * the Iterable returned separates the words in reverse order by single spaces.
	 * 
	 * Examples: 
	 * - Queue: [H e l l o   W o r l d] => [W o r l d   H e l l o]
	 * - Queue: [T o   b e   o r   n o t   t o   b e] => [b e   t o   n o t   o r   b e   T o]
	 * - Queue: [1   a b   2 . 2   C S 1 4 1 0] => [C S 1 4 1 0   2 . 2   a b   1]
	 * - Queue: [      ] => []
	 * - Queue: [] => []
	 * 
	 * @param q the input queue containing the characters of the sentence
	 * @return the queue with the order of words reversed
	 */
    public static Iterable<Character> reverseWords(Queue<Character> q) {
        Stack<Queue<Character>> stack = new Stack<>();
        Queue<Character> word = new Queue<>();

        while (!q.isEmpty()) {
            char c = q.dequeue();
            if (c != ' ') {
                word.enqueue(c);
            } else {
                if (!word.isEmpty()) {
                    stack.push(word);
                    word = new Queue<>();
                }
            }
        }

        if (!word.isEmpty()) {
            stack.push(word);
        }

        Queue<Character> result = new Queue<>();

        while (!stack.isEmpty()) {
            Queue<Character> w = stack.pop();
            while (!w.isEmpty()) {
                result.enqueue(w.dequeue());
            }
            if (!stack.isEmpty()) {
                result.enqueue(' ');
            }
        }

        return result;
    }
	
    /* * * * * * * * Test Client * * * * * * */
	
    public static void main(String[] args) {
        System.out.println("Testing balanceBrackets:");
        Queue<Character> q1 = new Queue<>();
        for (char c : "[( { [ ] } )]".toCharArray()) q1.enqueue(c);
        System.out.println(balanceBrackets(q1)); 

        Queue<Character> q2 = new Queue<>();
        for (char c : "([{ ] })".toCharArray()) q2.enqueue(c);
        System.out.println(balanceBrackets(q2)); 

        Queue<Character> q3 = new Queue<>();
        for (char c : "{".toCharArray()) q3.enqueue(c);
        System.out.println(balanceBrackets(q3)); 

        System.out.println("\nTesting reverseWords:");
        Queue<Character> q4 = new Queue<>();
        for (char c : "Hi there".toCharArray()) q4.enqueue(c);
        for (char c : reverseWords(q4)) System.out.print(c);
        System.out.println(); 

        Queue<Character> q5 = new Queue<>();
        for (char c : "howdy do dah".toCharArray()) q5.enqueue(c);
        for (char c : reverseWords(q5)) System.out.print(c);
        System.out.println(); 

        Queue<Character> q6 = new Queue<>();
        for (char c : "     ".toCharArray()) q6.enqueue(c);
        for (char c : reverseWords(q6)) System.out.print(c);
        System.out.println(); 
    }

}
