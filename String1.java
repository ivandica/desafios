package com.br.desafio.idwall;

import java.util.Scanner;

public class String1 {
	
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		String line = null;
		int qtdePalav = 0; 
		
		while (sc.hasNext())
	    {
			line = sc.nextLine();
			if (line.length() == 0)
    			break;
			
			for (String word : line.split(" "))
			{
				char[] carac = word.toCharArray();
				qtdePalav += carac.length;
				
				if (qtdePalav > 40) {
					System.out.println();
					qtdePalav = carac.length;
				}
				
				qtdePalav += 1;
				System.out.print(word + " ");
			}
        }
	}
}