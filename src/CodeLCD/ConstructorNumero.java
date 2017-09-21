package CodeLCD;

import java.util.HashMap;
import java.util.Map;
/**
 * La clase CostructorNumero se encarga de construir cada digito
 * del n�mero a representar.
 * Recibe el n�mero a construir y el tama�o de la fuente con el que este contar�
 * retorna la matriz de impresi�n con todos los digitos del n�mero a generar.*/
public class ConstructorNumero {
	/** Caracter que representa el s�mbolo vertical para construir cada d�gito. */
	private String caracter_V = "|";
	/** Caracter que representa el s�mbolo horizontal para construir cada d�gito. */
	private String caracter_H = "-";
	/** Caracter que representa el s�mbolo de las partes vacias de cada d�gito. */
	private String caracter_N = " ";
	/** Matriz que contiene los caracteres que forman el d�gito 8. */
	private String[][] matrizDigitoBase;
	/** Matriz que contiene todos los d�gitos del n�mero a construir. */
	private String[][] matrizImpresion;
	/** Variable que indica la posici�n en la que inicia el �ltimo segmento horizontal del d�gito. */
	private int segmentoH2;
	/** Variable que indica la posici�n en la que inicia el segmento horizontal en la mitad del d�gito. */
	private int segmentoH1;
	/** Variable que indica la posici�n en la que inicia los segmentos verticales de la parte superior del d�gito. */
	private int segmentoVi;
	/** Variable que indica la posici�n en la que inicia los segmentos verticales de la parte inferior del d�gito. */
	private int segmentoVf;
	/** Cantidad de caracteres verticales que posee cada segmento vertical del d�gito. */
	private int lineasVerticales;
	/** Cantidad de caracteres horizontales que posee cada segmento horizontal del d�gito. */
	private int lineasHorizontales;
	/** Filas totales de la matriz propia de cada d�gito del n�mero. */
	private int filasTotales;
	/** Columnas totales de la matriz propia de cada d�gito del n�mero. */
	private int columnasTotales;
	/** 
	 * Varialbe que controla la posici�n en que se ubica el inicio de cada d�gito
	 * dentro de la matriz de impresi�n. */		
	private int controlColumnasMatrizImpresion = 0;
	/**
	 * Estructura que contiene todos los n�meros solicitados de distintos tama�os
	 * El mapa principal almacena las fuentes de los d�gitos y el mapa interno alamacena
	 * la matriz de cada d�gito construido. */	  
	private Map <String,Map <String,String[][]>> matricesNumeros = new HashMap<String, Map<String, String[][]>>();
	/** Variable que indica los espacios entre cada d�gito de la matriz de impresi�n */
	private int espacioDigitos;
	
	
	
	/**
	 * El m�todo matrizBase construye la matriz del d�gito 8 llenando con los segmentos respectivos,
	 * a partir del d�gito 8 se pueden generar los dem�s d�gitos requeridos.
	 * Recibe la cantidad de filas que tendr� la matriz de cada d�gito,
	 * la cantidad de columnas que tendr� la matriz de cada d�gito,
	 * la cantidad de caracteres horizontales que tendr� cada segmento horizontal del d�gito y
	 * la cantidad de caracteres verticales que tendr� cada segmento vertical del d�gito
	 * Retorna la matriz con los caracateres que forman el d�gito 8 del respectivo tama�o. */
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
		// Se inicializan los segmentos horizontales de la matriz del n�mero 8 
		for(int i = 1; i<=lineasHorizontales; i++) {
			matrizDigitoBase[0][i] = caracter_H; 
			matrizDigitoBase[segmentoH1][i] = caracter_H; 			
			matrizDigitoBase[segmentoH2][i] = caracter_H;
		}
		// Se inicializa segmentos verticales de la matriz del n�mero 8
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
	 * El m�todo se encarga de generar cada d�gito del n�mero ingresado, adem�s,
	 * de almacenar los d�gitos construidos en la estructura matricesNumeros.
	 * Recibe el tama�o de la fuente que tendr� cada d�gito del n�mero y
	 * recibe el n�mero a representar.
	 * Este m�todo permite generar una base de datos con los d�gitos ya construidos para en caso dado recuperar
	 * d�gito requerido y no construirlo de nuevo. */
	public String[][] baseDatos(String escalaFuente, String numbers){
		if(numbers.length()==0) {
			return matrizImpresion;
		}
		if(matricesNumeros.isEmpty()) { //Se verifica si la base de datos no tiene ning�n registro
			int digito = Integer.parseInt(numbers.charAt(0) + "");
			String llaveNumero = String.valueOf(digito); //Llave del mapa de matrices 
			Map<String, String[][]> mNumero = new HashMap<String, String[][]>(); //Mapa que almacena las matrizes de cada d�gito
			String [][] matrizNumero = construirNumeros(digito); //Matriz del n�mero a representar
			agregarDigitoImpresion(matrizNumero);
			mNumero.put(llaveNumero, matrizNumero);
			matricesNumeros.put(escalaFuente, mNumero);
			String nu = numbers.substring(1); //se elimina el digito construido del n�mero a representar
			return baseDatos(escalaFuente, nu);
			
		}
			if(matricesNumeros.containsKey(escalaFuente)) { //Se verifica si la base de datos ya posee d�gitos de el tama�o recibido
				String numeroArepresentar = numbers.charAt(0) + "";
				int digito = Integer.parseInt(numeroArepresentar);
				if(matricesNumeros.get(escalaFuente).containsKey(numeroArepresentar)) { // Se verifica si la matriz del d�gito ya fue construida
					agregarDigitoImpresion(matricesNumeros.get(escalaFuente).get(numeroArepresentar));
					String siguienteDigito = numbers.substring(1);
					return baseDatos(escalaFuente, siguienteDigito);
				}				
				String [][] matrizNumero = construirNumeros(digito);
				agregarDigitoImpresion(matrizNumero);
				String llaveNumero = String.valueOf(digito); //llave de la matriz del n�mero solicitado
				matricesNumeros.get(escalaFuente).put(llaveNumero, matrizNumero);
				String siguienteDigito = numbers.substring(1); //se elimina el digito construido del n�mero a representar
				return baseDatos(escalaFuente, siguienteDigito);
				
			}
			String numeroArepresentar = numbers.charAt(0) + "";
			int digito = Integer.parseInt(numeroArepresentar);
			String [][] matrizNumero = construirNumeros(digito);
			agregarDigitoImpresion(matrizNumero);
			String llaveNumero = String.valueOf(digito); //llave de la matriz del n�mero solicitado
			Map<String, String[][]> mNumero = new HashMap<String, String[][]>();
			mNumero.put(llaveNumero, matrizNumero);
			matricesNumeros.put(escalaFuente, mNumero);
			String siguienteDigito = numbers.substring(1); //se elimina el digito construido del n�mero a representar
			return baseDatos(escalaFuente, siguienteDigito);

		
		
	}
	/** 
	 * El m�todo construirNumeros se encarga de generar cada d�gito seg�n el tama�o dado,
	 * cada d�gito se construye a partir de la matriz base (d�gito 8), se eliminan los segmentos
	 * correspondientes para formar el d�gito solicitado.
	 * Recibe el d�gito a construir.
	 * Retorna la matriz con los caracteres que forman el d�gito solicitado */
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
	 * EL m�todo copiarMatriz se encarga de igualar la matriz base (digito 8) con la
	 * matriz del d�gito solicitado, y asi no alterar la matriz base.*/
	public static String[][] copiarMatriz(String[][] matrizN) {
	    int length = matrizN.length;
	    String[][] copia = new String[length][matrizN[0].length];
	    for (int i = 0; i < length; i++) {
	        System.arraycopy(matrizN[i], 0, copia[i], 0, matrizN[i].length);
	    }
	    return copia;
	}
	/**
	 * El m�todo matrizDeImpresion se encarga de inicializar la matriz que contendr�
	 * todos los d�gitos del n�mero a representar.
	 * Recive la cantidad de columnas que tendr� la matriz de impresi�n y el espacio entre d�gitos.
	 * Las columnas son determinadas por la cantidad de d�gitos del n�mero y 2 espacios extra entre cada d�gito. */
	public void matrizDeImpresion(int columnasMatrizImpresi�n, int espacioD) {
		controlColumnasMatrizImpresion = 0;
		espacioDigitos = espacioD;
		matrizImpresion = new String[filasTotales][columnasMatrizImpresi�n];
		for(int i = 0; i<filasTotales;i++) {
			for(int j = 0; j<columnasMatrizImpresi�n;j++) {
				matrizImpresion[i][j] = " ";
			}
		}
	}
	/**
	 * El m�todo agregarDigitoImpresion a�ade las matrices de cada d�gito a
	 * la matriz de impresion la cual contendra todos los d�gitos del n�mero solicitado.
	 * Recibe la matriz del d�gito a a�adir. */
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
