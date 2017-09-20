package com.br.desafio.idwall;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class String2 {

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		String line = null;
		
		while (sc.hasNext())
	    {
			int qtdePalav = -1; 
			int qtdeEspaco = -1; 
			List<String> lineNew = new ArrayList<String>();
			
			line = sc.nextLine();
			if (line.length() == 0)
    			break;
			
			for (String word : line.split(" "))
			{
				char[] carac = word.toCharArray();
				
				if (qtdePalav + carac.length + 1 > 40) {
					int quoc = (40 - qtdePalav) / qtdeEspaco;
					int rest = (40 - qtdePalav) % qtdeEspaco;
					for (String linha : lineNew) {
						if (linha == " ") {
							 for (int i = 0; i < quoc; i++)
							 {
								 linha = linha + " ";
							 }
							 if (rest > 0) {
								 linha = linha + " ";
								 rest--;
							 }
						}
						System.out.print(linha);
					}
					qtdePalav = -1;
					qtdeEspaco = -1;
					lineNew.clear();
					System.out.println();
				} 
				
				qtdePalav += carac.length + 1;
				qtdeEspaco++;
				lineNew.add(word);
				lineNew.add(" ");
			}
			
			int quoc = (40 - qtdePalav) / qtdeEspaco;
			int rest = (40 - qtdePalav) % qtdeEspaco;
			for (String linha : lineNew) {
				if (linha == " ") {
					 for (int i = 0; i < quoc; i++)
					 {
						 linha = linha + " ";
					 }
					 if (rest > 0) {
						 linha = linha + " ";
						 rest--;
					 }
				}
				System.out.print(linha);
			}
        }
	}
}