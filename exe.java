

public class exe {

	public static void main(String args[]) {
		
		String arg="C:\\Users\\nacho\\Desktop\\Master\\Algoritmos evolutivos\\Segundo trabajo\\imagen1.txt";
		int MAX_ISTEPTS = 1000; 	//numero maximo de iteraciones
		int poblen = 256 ; 			// tamaño poblacion
		int nalleles=256; 			// numero de alleles por individuo
		
		
		Image imagen  = new Image(arg);
		Population pop = new Population(poblen, nalleles,imagen);
		
		
		for(int i=0;i<MAX_ISTEPTS;i++) {
			pop.next_step();
			//System.out.println(i);
			
		}
		
		imagen.orderImage(pop.getbestorder());
	}
}
