package LuisEduardoZordanMacagnan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;

public class LeitorDeArquivo {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String caminho = "src/LuisEduardoZordanMacagnan/banco.txt";
		System.out.println("1- Adicionar, 2- Ler todos");
		Integer esc = Integer.parseInt(scan.nextLine());
		escolha(esc, caminho);
	}
	
	public static void escolha (Integer esc, String caminho) {
		switch (esc) {
			case 1: {
				escreverArquivo(caminho);
				break;
			}
			case 2: {
				lerArquivo(caminho);
				break;
			}
			default:
				System.out.println("Escolha inválida");
		}
	}
	
	public static void escreverArquivo(String caminho) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try(BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
				FileWriter escritor = new FileWriter(caminho, true)) {
			System.out.println("Digite seu nome: ");
			String nome = leitor.readLine();
			
			System.out.println("Digite sua data de nascimento (dd/MM/yyyy): ");
			Date dataNascimento = new Date();
			try {
				dataNascimento = formato.parse(leitor.readLine());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			System.out.println("Digite seu telefone: ");
			String telefone = leitor.readLine();
			
			Calendar atual = Calendar.getInstance();
			Calendar nascimento = Calendar.getInstance();
			nascimento.setTime(dataNascimento);
			Integer idade = atual.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR);
			atual.add(Calendar.YEAR, -idade);
			if(nascimento.after(atual)) {
				idade--;
			}
			
			Boolean maiorIdade;
			if(idade>=18) {
				maiorIdade = true;
			}else {
				maiorIdade = false;
			}

			String info = nome+"\t"+formato.format(dataNascimento)+"\t"+idade+"\t"+maiorIdade+"\t"+telefone+"\n";
			escritor.write(info);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void lerArquivo(String caminho) {
		try(BufferedReader leitor = new BufferedReader(new FileReader(caminho))){
			String info;
			while((info = leitor.readLine())!=null) {
				System.out.println(info);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
