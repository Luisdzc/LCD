package CodeLCD;

import java.util.HashMap;
import java.util.Map;
/**
 * La clase CostructorNumero se encarga de construir cada digito
 * del número a representar.
 * Recibe el número a construir y el tamaño de la fuente con el que este contará
 * retorna la matriz de impresión con todos los digitos del número a generar.*/
public class ConstructorNumero {
	/** Caracter que representa el símbolo vertical para construir cada dígito. */
	private String caracter_V = "|";
	/** Caracter que representa el símbolo horizontal para construir cada dígito. */
	private String caracter_H = "-";
	/** Caracter que representa el símbolo de las partes vacias de cada dígito. */
	private String caracter_N = " ";
	/** Matriz que contiene los caracteres que forman el dígito 8. */
	private String[][] matrizDigitoBase;
	/** Matriz que contiene todos los dígitos del número a construir. */
	private String[][] matrizImpresion;
	/** Variable que indica la posición en la que inicia el último segmento horizontal del dígito. */
	private int segmentoH2;
	/** Variable que indica la posición en la que inicia el segmento horizontal en la mitad del dígito. */
	private int segmentoH1;
	/** Variable que indica la posición en la que inicia los segmentos verticales de la parte superior del dígito. */
	private int segmentoVi;
	/** Variable que indica la posición en la que inicia los segmentos verticales de la parte inferior del dígito. */
	private int segmentoVf;
	/** Cantidad de caracteres verticales que posee cada segmento vertical del dígito. */
	private int lineasVerticales;
	/** Cantidad de caracteres horizontales que posee cada segmento horizontal del dígito. */
	private int lineasHorizontales;
	/** Filas totales de la matriz propia de cada dígito del número. */
	private int filasTotales;
	/** Columnas totales de la matriz propia de cada dígito del número. */
	private int columnasTotales;
	/** 
	 * Varialbe que controla la posición en que se ubica el inicio de cada dígito
	 * dentro de la matriz de impresión. */		
	private int controlColumnasMatrizImpresion = 0;
	/**
	 * Estructura que contiene todos los números solicitados de distintos tamaños
	 * El mapa principal almacena las fuentes de los dígitos y el mapa interno alamacena
	 * la matriz de cada dígito construido. */	  
	private Map <String,Map <String,String[][]>> matricesNumeros = new HashMap<String, Map<String, String[][]>>();
	/** Variable que indica los espacios entre cada dígito de la matriz de impresión */
	private int espacioDigitos;
	
	
	
	/**
	 * El método matrizBase construye la matriz del dígito 8 llenando con los segmentos respectivos,
	 * a partir del dígito 8 se pueden generar los demás dígitos requeridos.
	 * Recibe la cantidad de filas que tendrá la matriz de cada dígito,
	 * la cantidad de columnas que tendrá la matriz de cada dígito,
	 * la cantidad de caracteres horizontales que tendrá cada segmento horizontal del dígito y
	 * la cantidad de caracteres verticales que tendrá cada segmento vertical del dígito
	 * Retorna la matriz con los caracateres que forman el dígito 8 del respectivo tamaño. */
	public void matrizBase(int filasTotalesB,int columnasTotalesB, int lineasHorizontalesB, int lineasVerticalesB) {
		matrizDigitoBase = new String[filasTotalesB][columnasTotalesB];
		lineasVerticales = lineasVerticalesB;
		lineasHorizontales = lineasHorizontalesB;
		filasTotales = filasTotalesB;
		columnasTotales = columnasTotalesB;
		for(int i = 0; i<filasTotales;i++) {
			for(int j = 0; j<columnasTotales;j++) {
				matrizDigitoBase[i][j] = " ";
			}
		}		    
		segmentoH2 = lineasVerticales + lineasVerticales + 2;
		segmentoH1 = lineasVerticales + 1;														
		segmentoVi = lineasHorizontales + 1;
		segmentoVf = lineasVerticales + 2;
		// Se inicializan los segmentos horizontales de la matriz del número 8 
		for(int i = 1; i<=lineasHorizontales; i++) {
			matrizDigitoBase[0][i] = caracter_H; 
			matrizDigitoBase[segmentoH1][i] = caracter_H; 			
			matrizDigitoBase[segmentoH2][i] = caracter_H;
		}
		// Se inicializa segmentos verticales de la matriz del número 8
		for(int j = 1; j<=lineasVerticales; j++) {			
			matrizDigitoBase[j][0] = caracter_V;
			matrizDigitoBase[j][segmentoVi] = caracter_V;
			matrizDigitoBase[segmentoVf][0] = caracter_V;
			matrizDigitoBase[segmentoVf][segmentoVi] = caracter_V;
			segmentoVf++;
		}
		segmentoVf = lineasVerticales + 2;                               
	}
	/** 
	 * El método se encarga de generar cada dígito del número ingresado, además,
	 * de almacenar los dígitos construidos en la estructura matricesNumeros.
	 * Recibe el tamaño de la fuente que tendrá cada dígito del número y
	 * recibe el número a representar.
	 * Este método permite generar una base de datos con los dígitos ya construidos para en caso dado recuperar
	 * dígito requerido y no construirlo de nuevo. */
	public String[][] baseDatos(String escalaFuente, String numbers){
		if(numbers.length()==0) {
			return matrizImpresion;
		}
		if(matricesNumeros.isEmpty()) { //Se verifica si la base de datos no tiene ningún registro
			int digito = Integer.parseInt(numbers.charAt(0) + "");
			String llaveNumero = String.valueOf(digito); //Llave del mapa de matrices 
			Map<String, String[][]> mNumero = new HashMap<String, String[][]>(); //Mapa que almacena las matrizes de cada dígito
			String [][] matrizNumero = construirNumeros(digito); //Matriz del número a representar
			agregarDigitoImpresion(matrizNumero);
			mNumero.put(llaveNumero, matrizNumero);
			matricesNumeros.put(escalaFuente, mNumero);
			String nu = numbers.substring(1); //se elimina el digito construido del número a representar
			return baseDatos(escalaFuente, nu);
			
		}
			if(matricesNumeros.containsKey(escalaFuente)) { //Se verifica si la base de datos ya posee dígitos de el tamaño recibido
				String numeroArepresentar = numbers.charAt(0) + "";
				int digito = Integer.parseInt(numeroArepresentar);
				if(matricesNumeros.get(escalaFuente).containsKey(numeroArepresentar)) { // Se verifica si la matriz del dígito ya fue construida
					agregarDigitoImpresion(matricesNumeros.get(escalaFuente).get(numeroArepresentar));
					String siguienteDigito = numbers.substring(1);
					return baseDatos(escalaFuente, siguienteDigito);
				}				
				String [][] matrizNumero = construirNumeros(digito);
				agregarDigitoImpresion(matrizNumero);
				String llaveNumero = String.valueOf(digito); //llave de la matriz del número solicitado
				matricesNumeros.get(escalaFuente).put(llaveNumero, matrizNumero);
				String siguienteDigito = numbers.substring(1); //se elimina el digito construido del número a representar
				return baseDatos(escalaFuente, siguienteDigito);
				
			}
			String numeroArepresentar = numbers.charAt(0) + "";
			int digito = Integer.parseInt(numeroArepresentar);
			String [][] matrizNumero = construirNumeros(digito);
			agregarDigitoImpresion(matrizNumero);
			String llaveNumero = String.valueOf(digito); //llave de la matriz del número solicitado
			Map<String, String[][]> mNumero = new HashMap<String, String[][]>();
			mNumero.put(llaveNumero, matrizNumero);
			matricesNumeros.put(escalaFuente, mNumero);
			String siguienteDigito = numbers.substring(1); //se elimina el digito construido del número a representar
			return baseDatos(escalaFuente, siguienteDigito);

		
		
	}
	/** 
	 * El método construirNumeros se encarga de generar cada dígito según el tamaño dado,
	 * cada dígito se construye a partir de la matriz base (dígito 8), se eliminan los segmentos
	 * correspondientes para formar el dígito solicitado.
	 * Recibe el dígito a construir.
	 * Retorna la matriz con los caracteres que forman el dígito solicitado */
	public String[][] construirNumeros(int digito){

		switch (digito) {
			case 1:
				String[][] matriz1 = copiarMatriz(matrizDigitoBase);
				for(int i = 1; i<=lineasHorizontales; i++) {
					matriz1[0][i] = caracter_N ;					
					matriz1[segmentoH1][i] = caracter_N;
					matriz1[segmentoH1][i+1] = caracter_V;
					matriz1[segmentoH2][i] = caracter_N;
				}
				for(int j = 1; j<=lineasVerticales; j++) {
					matriz1[j][0] = caracter_N;
					matriz1[segmentoVf][0] = caracter_N;
					segmentoVf++;
				}
				segmentoVf = lineasVerticales + 2;
				return matriz1;
				
			case 2:
				String[][] matriz2 = copiarMatriz(matrizDigitoBase);
				for(int j = 1; j<=lineasVerticales; j++) {
					matriz2[j][0] = caracter_N;
					matriz2[segmentoVf][segmentoVi] = caracter_N;
					segmentoVf++;
				}
				segmentoVf = lineasVerticales + 2;
				return matriz2;
				
			case 3:
				String[][] matriz3 = copiarMatriz(matrizDigitoBase);
				for(int j = 1; j<=lineasVerticales; j++) {
					matriz3[j][0] = caracter_N;
					matriz3[segmentoVf][0] = caracter_N;
					segmentoVf++;
				}
				segmentoVf = lineasVerticales + 2;
				return matriz3;
				
			case 4:
				String[][] matriz4 =  copiarMatriz(matrizDigitoBase);;
				for(int i = 1; i<=lineasHorizontales; i++) {
					matriz4[0][i] = caracter_N ;
					matriz4[segmentoH1][segmentoVi] = caracter_V;
					matriz4[segmentoH2][i] = caracter_N;
				}
				for(int j = 1; j<=lineasVerticales; j++) {
					matriz4[segmentoVf][0] = caracter_N;
					segmentoVf++;
				}
				segmentoVf = lineasVerticales + 2;
				return matriz4;
				
			case 5:
				String[][] matriz5 = copiarMatriz(matrizDigitoBase);
				for(int j = 1; j<=lineasVerticales; j++) {
					matriz5[j][segmentoVi] = caracter_N;
					matriz5[segmentoVf][0] = caracter_N;
					segmentoVf++;
				}
				segmentoVf = lineasVerticales + 2;
				return matriz5;
				
			case 6:
				String[][] matriz6 = copiarMatriz(matrizDigitoBase);
				for(int j = 1; j<=lineasVerticales; j++) {
					matriz6[j][segmentoVi] = caracter_N;
				}
				return matriz6;
			
			case 7:	
				String[][] matriz7 = copiarMatriz(matrizDigitoBase);
				for(int i = 1; i<=lineasHorizontales; i++) {					
					matriz7[segmentoH1][i] = caracter_N;
					matriz7[segmentoH1][i+1] = caracter_V;
					matriz7[segmentoH2][i] = caracter_N;
				}
				for(int j = 1; j<=lineasVerticales; j++) {
					matriz7[j][0] = caracter_N;
					matriz7[segmentoVf][0] = caracter_N;
					segmentoVf++;
				}
				segmentoVf = lineasVerticales + 2;
				return matriz7;
				
			case 8:
				return matrizDigitoBase;
				
			case 9:
				String[][] matriz9 = copiarMatriz(matrizDigitoBase);
				for(int j = 1; j<=lineasVerticales; j++) {
					matriz9[segmentoVf][0] = caracter_N;
					segmentoVf++;
				}
				segmentoVf = lineasVerticales + 2;
				return matriz9;	
				
			case 0:
				String[][] matriz0 = copiarMatriz(matrizDigitoBase);
				for(int i = 1; i<=lineasHorizontales; i++) {	
					matriz0[segmentoH1][i] = caracter_N;
					matriz0[segmentoH1][0] = caracter_V;
					matriz0[segmentoH1][segmentoVi] = caracter_V;
				}
				return matriz0;
			default:
			break;
		}
		return null;
	}
	
	/** 
	 * EL método copiarMatriz se encarga de igualar la matriz base (digito 8) con la
	 * matriz del dígito solicitado, y asi no alterar la matriz base.*/
	public static String[][] copiarMatriz(String[][] matrizN) {
	    int length = matrizN.length;
	    String[][] copia = new String[length][matrizN[0].length];
	    for (int i = 0; i < length; i++) {
	        System.arraycopy(matrizN[i], 0, copia[i], 0, matrizN[i].length);
	    }
	    return copia;
	}
	/**
	 * El método matrizDeImpresion se encarga de inicializar la matriz que contendrá
	 * todos los dígitos del número a representar.
	 * Recive la cantidad de columnas que tendrá la matriz de impresión y el espacio entre dígitos.
	 * Las columnas son determinadas por la cantidad de dígitos del número y 2 espacios extra entre cada dígito. */
	public void matrizDeImpresion(int columnasMatrizImpresión, int espacioD) {
		controlColumnasMatrizImpresion = 0;
		espacioDigitos = espacioD;
		matrizImpresion = new String[filasTotales][columnasMatrizImpresión];
		for(int i = 0; i<filasTotales;i++) {
			for(int j = 0; j<columnasMatrizImpresión;j++) {
				matrizImpresion[i][j] = " ";
			}
		}
	}
	/**
	 * El método agregarDigitoImpresion añade las matrices de cada dígito a
	 * la matriz de impresion la cual contendra todos los dígitos del número solicitado.
	 * Recibe la matriz del dígito a añadir. */
	public void agregarDigitoImpresion(String[][] digito) {
		int k = controlColumnasMatrizImpresion;
		for(int i = 0; i < filasTotales; i++) {
			k=controlColumnasMatrizImpresion;
			for(int j = 0; j<columnasTotales; j++) {
				matrizImpresion[i][k] = digito[i][j];
				k++;
			}
		}
		controlColumnasMatrizImpresion+=columnasTotales + espacioDigitos;
	}
}
