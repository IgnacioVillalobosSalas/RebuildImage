

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Individual {

	List<Integer> orden;
	double fitness;
	Image image;

	public Individual(int nalleles, Image imagen) {

		orden = new ArrayList<Integer>();
		this.image = imagen;
		

		Random r = new Random();
		int inicio = r.nextInt(255);
		orden.add(inicio);
		for(int i=0;i<255;i++) {
			orden.add(image.bestnextrow(orden));
		}
		
	
	}

	public void setfitness(double fitness) {
		this.fitness = fitness;
	}

	public double getfitness() {
		return this.fitness;
	}

	public List getorden() {
		return orden;
	}
	
	public void cruce(Image imagen) {
		
		Random r = new Random();
		
		List<Integer> aux = new ArrayList<Integer>();
		int splitpoint = r.nextInt(155);
		for(int i=splitpoint;i<255;i++) {
			aux.add(orden.get(i));
		}
		for(int i=0;i<splitpoint;i++) {
			aux.add(orden.get(i));
		}
		
		
		
		permuta(imagen,orden);
	}
	

	public void permuta(Image imagen,List aux) {
		Random r = new Random();
		List<Integer> temp = new ArrayList<>();

		temp.addAll(aux);
		int cambio = r.nextInt(254);
		//if (r.nextDouble() < 0.1)Collections.swap(temp, cambio+1,cambio);
		

		double fitness_nuevo = imagen.evaluate(temp);
		// System.out.println(fitness_nuevo + " " + fitness);
		if (fitness_nuevo > fitness) {
			orden = new ArrayList<>();
			orden.addAll(temp);
			this.setfitness(fitness_nuevo);

		}

	}

}
