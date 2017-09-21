package CodeLCD;


import java.util.Vector;
/**
 * Clase GeneradorNumeros se encarga de procesar cada entrada registrada
 * generando los n�meros de cada tama�o ingresado.
 * Recive la estructura que almacena todas las entradas registradas.
 * Imprime los n�meros ingresador del tama�o respectivo */
public class GeneradorNumeros {
	/** Estructrura que almacena todas las entradas ingresadas
		cada vector interno posee dos elementos el tama�o de la 
		fuente y el n�mero a representar.*/ 
	private Vector<Vector<String>> numeros;
	/** Cantidad de filas que posee la matriz de cada digito del n�mero. */
	private int filasTotales;
	/** Cantidad de columnas que posee la matriz de cada digito del n�mero. */
	private int columnasTotales;
	/** Variable que indica la cantidad de caractes horizontales de cada digito. */
	private int lineasHorizontales;
	/** Variable que indica la cantidad de caractes verticales de cada digito. */
	private int lineasVerticales;
	/**Variable que indica la cantidad de espacio entre los d�gitos. */
	private int espacioDigitos;
	
	/**
	 * Constructor de la clase GeneradorNumeros que recive como parametro la estructura que
	 * almecena los datos de las entradas ingresadas. */
	public GeneradorNumeros(Vector<Vector<String>> numeros , int espacioDigitos) {
		this.numeros = numeros;
		this.espacioDigitos = espacioDigitos;
	}
	/** El m�todo generar se encarga de recorrer la estructura que almacena los datos
	 * y llamar al constructor para formar el n�mero ingresado del tama�o solicitado. */
	public void generar() {
		ConstructorNumero cn = new ConstructorNumero();
		for(int i=0; i<numeros.size();i++) {
			String escalaFuente = numeros.get(i).get(0).toString(); //Tama�o de la fuente con la que contar� el n�mero
			String numero = numeros.get(i).get(1).toString(); //N�mero a representar del tama�o dado
			int size = Integer.parseInt(escalaFuente.trim());
			filasTotales = (2 * size) + 3; 
	        columnasTotales = size + 2; 
	        lineasHorizontales = columnasTotales - 2; 
	        lineasVerticales = (filasTotales/2) - 1; 
	        cn.matrizBase(filasTotales, columnasTotales, lineasHorizontales, lineasVerticales); 
	        cn.matrizDeImpresion((numero.length()*columnasTotales) + (numero.length() - 1) * espacioDigitos, espacioDigitos); 
			String[][] matrizImpresion = cn.baseDatos(escalaFuente, numero); // Construcci�n de la matriz de impresion que contiene todos los dijitos del n�mero a presentar
			imprimirResultado(matrizImpresion);
			System.out.println();
		}
		
	}
	
	/**
	 * EL m�todo imprimirResultado se encarga de recorrer la matriz de impresi�n
	 * que contiene todos los digitos del n�mero a representar.  */	
	public void imprimirResultado(String[][] matrizImpresion) {
		for(int i = 0; i<matrizImpresion.length; i++) {
			for(int j = 0; j<matrizImpresion[i].length; j++) {
				System.out.print(matrizImpresion[i][j]);
			}
			System.out.println();
		}
	}

	
}
