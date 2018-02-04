

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

public class Image {

	private int[][] imagen;

	public Image(String imagepath) {
		imagen = new int[256][256];

		loadImage(imagepath);
	}

	public void orderImage(List<Integer> orden) {
		try {
			
			
			
			BufferedWriter bw = new BufferedWriter(new FileWriter("imagen2.txt"));

			for (int row=255;row>0;row--) {
				for (int j = 0; j < imagen[orden.get(row)].length; j++) { // número de columnas de cada fila
					bw.write(imagen[orden.get(row)][j] + " ");
				}
				bw.write("\n");
			}
			bw.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public double evaluate(List<Integer> or) {
		int aux = 0;
		int fitness = 0;

		for (int i = 1; i < 254; i++) {
			for (int j = 1; j < 254; j++) {
				aux = imagen[i][j];
				fitness += Math.abs(aux - imagen[or.get(i + 1)][j]) + Math.abs(aux - imagen[or.get(i - 1)][j])+ 
						Math.abs(aux - imagen[or.get(i - 1)][j-1])+ Math.abs(aux - imagen[or.get(i - 1)][j+1])+ 
						Math.abs(aux - imagen[or.get(i + 1)][j-1])+ Math.abs(aux - imagen[or.get(i + 1)][j+1]);
			}
		}

		return fitness;
	}

	public int distacerows(int fila1, int fila2) {
		int distancia = 0;

		for (int i = 0; i < 255; i++) {
			distancia += Math.abs(imagen[fila1][i] - imagen[fila2][i]);
		}

		return distancia;
	}

	public int bestnextrow(List orden) {
		
		int best = this.distacerows(0,255);
		int row=0;

		for(int i=0;i<255;i++) {
			if(!orden.contains(i)) {
				int distance = this.distacerows(i,(Integer)orden.get(orden.size()-1));
				if(distance<best) {
					best=distance;
					row=i;
				}
			}
			
		}
		
		return row;
	}

	public int closestTo(List<Integer> or) {

		int n = 0;

		or.add(0);
		double temp = this.evaluate(or);
		or.remove(or.size() - 1);
		double aux = temp;
		for (int i = 1; i < 254; i++) {

			if (!or.contains(i)) {
				or.add(i);
				aux = this.evaluate(or);
				or.remove(or.size() - 1);
			}

			if (aux < temp) {
				temp = aux;
				n = i;
			}

		}

		return n;

	}

	private void saveImage() {

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("imagen2.txt"));

			for (int i = 0; i < imagen.length; i++) { // número de filas
				for (int j = 0; j < imagen[i].length; j++) { // número de columnas de cada fila
					bw.write(imagen[i][j] + " ");
				}
				bw.write("\n");
				;
			}
			bw.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private void loadImage(String imagepath) {
		File archivo = new File(imagepath);
		FileReader fr;
		try {
			fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);

			String linea;
			int f = 0;
			int c = 0;
			while ((linea = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(linea);
				while (st.hasMoreTokens()) {
					imagen[f][c] = Integer.valueOf(st.nextToken());
					c++;

				}
				c = 0;
				f++;

			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
