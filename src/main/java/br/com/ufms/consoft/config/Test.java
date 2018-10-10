package br.com.ufms.consoft.config;

public class Test {

	public static void main(String... args){
		
		String[] tempA = String.valueOf(10256).split("");
		String[] tempB = String.valueOf(512).split("");
		StringBuilder tempC = new StringBuilder();
		
		int maiorLenght = tempA.length > tempB.length ? tempA.length : tempB.length;
		
		int index = 0;
		
		for (; index < maiorLenght; index++) {
			if (tempA.length > index)
				tempC.append(tempA[index]);

			if (tempB.length > index)
				tempC.append(tempB[index]);
		}
		
		System.out.println(tempC);
		
	}
}
