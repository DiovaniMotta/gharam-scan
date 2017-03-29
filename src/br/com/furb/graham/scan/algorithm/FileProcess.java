package br.com.furb.graham.scan.algorithm;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.furb.graham.scan.algorithm.GrahamScan.Point;

/**
 * 
 * @author Diovani Bernardi da Motta
 * @see 27/03/2017 Classe que tem por objetivo efetuar a manipulação dos
 *      arquivos usados como entrada e saída de dados do processamento do
 *      algoritmo
 */
public class FileProcess {

	/**
	 * Método que tem a responsabilidade de efetuar a leitura dos dados contidos
	 * em um arquivo de entrada
	 * 
	 * @param path
	 *            o caminho absoluto do arquivo que será processado na entrada
	 * @return uma lista de objetos do tipo Point lidos do arqivo
	 * @throws IOException
	 */
	public List<Point> readInput(String path) throws IOException {
		List<Point> points = new ArrayList<>();
		if (path == null || path.isEmpty())
			throw new FileNotFoundException("O caminho para a leitura do arquivo deve ser definido");
		FileReader arq = new FileReader(path);
		Scanner scanner = new Scanner(arq);
		boolean firtLine = true;
		while (scanner.hasNext()) {
			if (!firtLine) {
				Point point = new Point();
				point.x = scanner.nextInt();
				point.y = scanner.nextInt();
				points.add(point);
			} else {
				scanner.next();
			}
			firtLine = false;
		}
		scanner.close();
		arq.close();
		return points;
	}

	/**
	 * Método que tem a responsabilidade de escrever em arquivo o resultado do
	 * processamento do arquivo
	 * 
	 * @param path
	 *            o caminho completo do arquivo de saída
	 * @param points
	 *            a lista de pontos processados e que serão registrados no
	 *            arquivo
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public void writeOutput(String path, List<Point> points)
			throws FileNotFoundException, UnsupportedEncodingException {
		if (path == null || path.isEmpty())
			throw new FileNotFoundException("O caminho para a leitura do arquivo deve ser definido");
		PrintWriter writer = new PrintWriter(path, "UTF-8");
		writer.println(points.size());
		for (Point point : points)
			writer.println(point.x + " " + point.y);
		writer.close();
	}
}
