package net.ktds.bamcoding.application;

import java.util.Scanner;

/**
 * 랜덤 함수 정의 중...
 * 
 * @author 206-017
 *
 */
public class Random {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int result = 0;
		String key = "";

		while (true) {
			// z를 입력하면 종료
			if (key.equalsIgnoreCase("z")) {
				break;
			} else {
				result = (int) (Math.random() * 6) + 1;
				System.out.println(result);

			}
			// 아무 값이나 입력하세요.
			key = input.nextLine();

		}

	}
}
