package test;

class Foo {

	private int x;

	public Foo(int x) {
		this.x = x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}
}

public class Gamma {

	static Foo fooBar(Foo foo) {
		foo = new Foo(100); // 2000 // 3000
		return foo;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Foo foo = new Foo(300); // 주솟밧을 1000  foo -> 1000
		System.out.println(foo.getX() + "-");
		Foo fooFoo = fooBar(foo); // 1000 전달 -> fooFoo <- 2000
		System.out.println(foo.getX() + "-");
		System.out.println(fooFoo.getX() + "-");

		foo = fooBar(fooFoo); //foo <- 3000
		System.out.println(foo.getX() + "-");
		System.out.println(fooFoo.getX());
		
	}

}
