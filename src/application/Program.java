package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import entities.Sale;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Entre o caminho do arquivo: ");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Sale> list = new ArrayList<>();

			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				Integer month = Integer.parseInt(fields[0]);
				Integer year = Integer.parseInt(fields[1]);
				String seller = fields[2];
				Integer items = Integer.parseInt(fields[3]);
				Double total = Double.parseDouble(fields[4]);
				list.add(new Sale(month, year, seller, items, total));
				line = br.readLine();

			}

			// Crie um conjunto (Set) para armazenar nomes únicos de vendedores
			Set<String> uniqueSellers = list.stream()
						.map(Sale::getSeller)
						.collect(Collectors.toSet());
						
		   Double totalSellers = list.stream()
				   		.filter(x -> x.getTotal() == x.getTotal())
				   		.map(x -> x.totalPrice())
				   		.reduce(0.0, (x,y) -> x + y);	
		   				
						
						
						System.out.println("Valor total vendido por Vendedor: ");
						
							uniqueSellers.forEach(System.out::println); 
							
							
						

		} catch (IOException e) {
			System.err.println("Erro: " + e.getMessage());
		}
		sc.close();
	}
}
