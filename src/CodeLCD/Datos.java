package CodeLCD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;
/**
 * La clase Datos se encarga de recibir los datos
 * que son ingresdos por teclado y almacenarlos para
 * despu�s procesarlos */
public class Datos {
	
	public static void main(String[] args) {
		int size=1;
		int espacioDigitos = 0;
		/** 
		 * Estructrura que almacena todas las entradas ingresadas
		 * cada vector interno posee dos elementos el tama�o de la
		 * fuente y el n�mero a representar. */		   
		Vector<Vector<String>> numeros = new Vector<Vector<String>>(); 
		
		System.out.println("Digite el tama�o de fuente (1 - 10) seguido de , y luego el n�mero a representar - (2,1234)");
		System.out.println("para salir digite 0,0");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //Buffer de lectura de datos
		/*Ciclo para leer y almacenar los datos ingresados por teclado en un vector de vector
		 *  que contiene el tama�o y el n�mero a representar */
		while(size!=0) { //Mientras el tama�o del n�mero a representar sea mayor a cero se leen las entradas
			
			try {
				String dato = br.readLine(); //Linea de entrada
				StringTokenizer token = new StringTokenizer(dato,",");
				size = Integer.parseInt(token.nextToken().toString().trim());
				if(size != 0) {
					if(size <= 10) { //se verifica el tama�o de la fuente dentro del limite establecido
						Vector <String> datos = new Vector<String>();
						String numero = (token.nextToken("\n").toString().substring(1).trim()); //Numero a representar del tama�o dado
						String escalaFuente = String.valueOf(size);// Tama�o de la fuente que tendra el n�mero ingresado
						datos.addElement(escalaFuente);
						datos.addElement(numero);
						numeros.addElement(datos);
					}
					else {
						System.out.println("Tama�o de fuente incorrecto intente de nuevo");
					}	
				}
				
			} catch (IOException evento) {
				evento.printStackTrace();
			}
		}
		System.out.println("Indique el espacio entre cada digitio de (0-5)");
		try {
			espacioDigitos = Integer.parseInt(br.readLine());
		} catch (NumberFormatException evento) {
			evento.printStackTrace();
		} catch (IOException evento) {
			evento.printStackTrace();
		}
		if(espacioDigitos < 0 || espacioDigitos >5) {
			System.out.println("El espacio de digitos debe estar entre 0 y 5");
			System.out.println("Indique el espacio entre cada digitio de (0-5)");
			try {
				espacioDigitos = Integer.parseInt(br.readLine());
			} catch (NumberFormatException | IOException evento) {
				evento.printStackTrace();
			}
		}
		GeneradorNumeros gn = new GeneradorNumeros(numeros, espacioDigitos);
		gn.generar();
		
	}
}
