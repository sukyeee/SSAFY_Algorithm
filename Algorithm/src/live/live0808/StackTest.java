package live.live0808;

public class StackTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 SsafyStack<Integer> stack = new  SsafyStack<>();
		 stack.push(1);
		 stack.push(2);
		 stack.push(3);
		 stack.push(4);

		 System.out.println(stack.peek());
		 System.out.println("size: " + stack.size());

		 stack.pop();
		 System.out.println(stack.peek());
		 stack.pop();
		 System.out.println("size: " + stack.size());

		 stack.pop();
		 stack.push(78);
		 System.out.println(stack.peek());

		 stack.pop();
		 System.out.println(stack.peek());
		 
		 
		 
		 
	}
	

}
