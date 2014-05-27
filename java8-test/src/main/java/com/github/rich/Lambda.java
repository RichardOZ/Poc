package com.github.rich;

/**
 * Hello world!
 *
 */
public class Lambda {
	public static void main(String[] args) {

		Runnable r1 = () -> System.out.println("Hello Lambda and Java8");
		new Thread(r1).start();

		// count from 0 to 9
		new Thread(() -> {
			int i = 0;
			while (i < 10) {
				 System.out.println(i++);
			}
		}).start();
	}
}
