package br.com.furb.graham.scan.algorithm;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import br.com.furb.graham.scan.algorithm.GrahamScan.Point;

public class MainApp {

	private static Logger LOGGER = Logger.getLogger(MainApp.class.getName());

	public static void main(String[] args) {
		try {
			FileProcess fileProcess = new FileProcess();
			String path = new File("../grahan-scan/data/data (teste 1 - 10pts).in").getCanonicalPath();
			LOGGER.info("Iniciando leitura do arquivo de entrada: data (teste 1 - 10pts).in");
			List<Point> points = fileProcess.readInput(path);
			for (Point point : points)
				LOGGER.info("x:" + point.x + ",y:" + point.y);
			GrahamScan grahamScan = new GrahamScan();
			LOGGER.info("Aplicando algoritmo de Graham Scan...");
			List<Point> lists = grahamScan.process(points);
			for (Point point : lists)
				LOGGER.info("x:" + point.x + ",y:" + point.y);
			LOGGER.info("Gravando resultado do processamento no arquivo de saída: data (teste 1 - 10pts).out");
			path = new File("../grahan-scan/data/data (teste 1 - 10pts).out").getCanonicalPath();
			fileProcess.writeOutput(path, points);
			
			path = new File("../grahan-scan/data/data (teste 2 - 10pts).in").getCanonicalPath();
			LOGGER.info("Iniciando leitura do arquivo de entrada: data (teste 2 - 10pts).in");
			points = fileProcess.readInput(path);
			for (Point point : points)
				LOGGER.info("x:" + point.x + ",y:" + point.y);
			LOGGER.info("Aplicando algoritmo de Graham Scan...");
			lists = grahamScan.process(points);
			for (Point point : lists)
				LOGGER.info("x:" + point.x + ",y:" + point.y);
			LOGGER.info("Gravando resultado do processamento no arquivo de saída: data (teste 2 - 10pts).out");
			path = new File("../grahan-scan/data/data (teste 2 - 10pts).out").getCanonicalPath();
			fileProcess.writeOutput(path, points);
			
			path = new File("../grahan-scan/data/data (teste 3 - 10pts).in").getCanonicalPath();
			LOGGER.info("Iniciando leitura do arquivo de entrada: data (teste 3 - 10pts).in");
			points = fileProcess.readInput(path);
			for (Point point : points)
				LOGGER.info("x:" + point.x + ",y:" + point.y);
			LOGGER.info("Aplicando algoritmo de Graham Scan...");
			lists = grahamScan.process(points);
			for (Point point : lists)
				LOGGER.info("x:" + point.x + ",y:" + point.y);
			LOGGER.info("Gravando resultado do processamento no arquivo de saída: data (teste 3 - 10pts).out");
			path = new File("../grahan-scan/data/data (teste 3 - 10pts).out").getCanonicalPath();
			fileProcess.writeOutput(path, points);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
