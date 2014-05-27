package com.github.rich;

interface F1 {
	void doIt1();
}

class F2 {
	void doIt2() {
	}
}

class MyFunctions {

	public MyFunctions(F1 f1) {
		System.out.println("F1 constructor");
		f1.doIt1();
	}

	public MyFunctions(F2 f2) {
		System.out.println("F2 constructor");
		f2.doIt2();
	}

	public MyFunctions(String s) {
		System.out.println(s);
	}
}

public class MyLambda {
	public static void main(String[] args) {
		F1 f1 = () -> {
		};
		new MyFunctions(f1);

		// new MyFunctions(() -> {}); // constructor is ambiguous
		new MyFunctions((F1) () -> {
			System.out.println("it's done.");
		}); // OK

		// target type of this expression must be a functional interface
		// new MyFunctions((F2)() -> {});
	}
}
