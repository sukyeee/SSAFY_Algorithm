package live.live0808;

public class SsafyStack<E> implements IStack<E> {

	private Node<E> top; // 더미노드 아님!!
	
	@Override
	public void push(E data) { // 첫노드 삽입 알고리즘
		top = new Node<E>(data, top);
	}

	@Override
	public E pop() {
		if(isEmpty()) {
			System.out.println("공백스택이어서 작업이 불가능합니다.");
			return null;
		}
		
		Node<E> popNode = top;
		top = popNode.link;
		popNode.link = null; //꼭 null처리 안해줘도 됨. 그냥 끊어준 것
		return popNode.data;
	
	}

	@Override
	public E peek() {
		if(isEmpty()) {
			System.out.println("공백스택이어서 작업이 불가능합니다.");
			return null;
		}
		
		return top.data;
	}

	@Override
	public boolean isEmpty() {
		return top == null;
	}

	@Override
	public int size() {
		int cnt = 0;
		for (Node<E> temp = top; temp != null ; temp = temp.link ) {
			++cnt;
		}
		
		return 0;
	}

	
}
