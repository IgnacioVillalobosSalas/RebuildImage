

import java.util.ArrayList;
import java.util.List;

public class Population {
	
	List<Individual> poblacion;
	Image imagen;
	
	
	public Population(int nindiv,int nalleles,Image imagen) {
		
		poblacion = new ArrayList<Individual>();
		this.imagen = imagen;
		
		for(int i=0;i<nindiv;i++) {
			poblacion.add(new Individual(nalleles,imagen));
		}
		compute();
	}
	
	public void compute() {
		
		for(Individual in : poblacion) {
			in.setfitness(imagen.evaluate(in.getorden()));
		}
	}
	
	public void next_step() {
		for(Individual in: poblacion) {
			in.cruce(imagen);
		}
	}
	
	public List getbestorder(){
		Individual aux = poblacion.get(0);
		
		for(Individual i: poblacion) {
			if(i.getfitness()<aux.getfitness()) {
				aux=i;
			}
		}
		System.out.println(aux.getfitness());
		return aux.getorden();
	}

}
