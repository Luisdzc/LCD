package CodeLCD;


import java.util.Vector;
/**
 * Clase GeneradorNumeros se encarga de procesar cada entrada registrada
 * generando los números de cada tamaño ingresado.
 * Recive la estructura que almacena todas las entradas registradas.
 * Imprime los números ingresador del tamaño respectivo */
public class GeneradorNumeros {
	/** Estructrura que almacena todas las entradas ingresadas
		cada vector interno posee dos elementos el tamaño de la 
		fuente y el número a representar.*/ 
	private Vector<Vector<String>> numeros;
	/** Cantidad de filas que posee la matriz de cada digito del número. */
	private int filasTotales;
	/** Cantidad de columnas que posee la matriz de cada digito del número. */
	private int columnasTotales;
	/** Variable que indica la cantidad de caractes horizontales de cada digito. */
	private int lineasHorizontales;
	/** Variable que indica la cantidad de caractes verticales de cada digito. */
	private int lineasVerticales;
	/**Variable que indica la cantidad de espacio entre los dígitos. */
	private int espacioDigitos;
	
	/**
	 * Constructor de la clase GeneradorNumeros que recive como parametro la estructura que
	 * almecena los datos de las entradas ingresadas. */
	public GeneradorNumeros(Vector<Vector<String>> numeros , int espacioDigitos) {
		this.numeros = numeros;
		this.espacioDigitos = espacioDigitos;
	}
	/** El método generar se encarga de recorrer la estructura que almacena los datos
	 * y llamar al constructor para formar el número ingresado del tamaño solicitado. */
	public void generar() {
		ConstructorNumero cn = new ConstructorNumero();
		for(int i=0; i<numeros.size();i++) {
			String escalaFuente = numeros.get(i).get(0).toString(); //Tamaño de la fuente con la que contará el número
			String numero = numeros.get(i).get(1).toString(); //Número a representar del tamaño dado
			int size = Integer.parseInt(escalaFuente.trim());
			filasTotales = (2 * size) + 3; 
	        columnasTotales = size + 2; 
	        lineasHorizontales = columnasTotales - 2; 
	        lineasVerticales = (filasTotales/2) - 1; 
	        cn.matrizBase(filasTotales, columnasTotales, lineasHorizontales, lineasVerticales); 
	        cn.matrizDeImpresion((numero.length()*columnasTotales) + (numero.length() - 1) * espacioDigitos, espacioDigitos); 
			String[][] matrizImpresion = cn.baseDatos(escalaFuente, numero); // Construcción de la matriz de impresion que contiene todos los dijitos del número a presentar
			imprimirResultado(matrizImpresion);
			System.out.println();
		}
		
	}
	
	/**
	 * EL método imprimirResultado se encarga de recorrer la matriz de impresión
	 * que contiene todos los digitos del número a representar.  */	
	public void imprimirResultado(String[][] matrizImpresion) {
		for(int i = 0; i<matrizImpresion.length; i++) {
			for(int j = 0; j<matrizImpresion[i].length; j++) {
				System.out.print(matrizImpresion[i][j]);
			}
			System.out.println();
		}
	}

	
}
