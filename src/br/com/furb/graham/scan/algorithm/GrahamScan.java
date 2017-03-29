package br.com.furb.graham.scan.algorithm;

import java.util.*;

/**
 * 
 * @author Diovani Bernardi da Motta
 * @see 24/03/2017 Classe que tem a responsabilidade de implementar o algoritmo
 *      de processamento Envolt�rio Convexo para o tratamento de imagem Grahan
 *      Scan
 */
public class GrahamScan {

	public static class Point {
		public int x;
		public int y;
	}

	protected static enum Tipo {
		DIREITA, ESQUERDA, COLLINEAR
	}

	protected Tipo vetorial(Point a, Point b, Point c) {
		long crossProduct = (((long) b.x - a.x) * ((long) c.y - a.y)) - (((long) b.y - a.y) * ((long) c.x - a.x));
		if (crossProduct > 0) {
			return Tipo.ESQUERDA;
		} else if (crossProduct < 0) {
			return Tipo.DIREITA;
		} else {
			return Tipo.COLLINEAR;
		}
	}

	/**
	 * M�todo que tem a responsabilidade de verificar se os pontos processados
	 * na imagem s�o colineares
	 * 
	 * @param points
	 *            uma lista de pontos(coordenadas x e y) existentes na imagem
	 * @return falso caso nao seja colinear e verdadeiro caso seja
	 * 
	 */
	protected boolean isColinear(List<Point> points) {
		if (points.size() < 2)
			return true;
		final Point a = points.get(0);
		final Point b = points.get(1);
		for (int i = 2; i < points.size(); i++) {
			Point c = points.get(i);
			if (vetorial(a, b, c) != Tipo.COLLINEAR)
				return false;
		}
		return true;
	}

	/**
	 * M�todo que representa o n�clo do processamento do algoritmo Graham Scan.
	 * Ele � respons�vel por receber uma lista de pontos demarcados na imagem e
	 * efetuar o processamento e aplica��o do algoritmo
	 * 
	 * @param points
	 *            uma lista de pontos identificados em uma imagem e que ser�o
	 *            processados
	 * @return uma lista de pontos nos quais ser�o aplicadas as tecnicas de
	 *         Envoltoria Convexa
	 * @throws IllegalArgumentException
	 */
	public List<Point> process(List<Point> points) throws IllegalArgumentException {
		List<Point> sorted = new ArrayList<Point>(getOrdenar(points));
		if (sorted.size() < 3)
			throw new IllegalArgumentException("S� pode criar um envolt�rio convexo de 3 ou mais pontos �nicos");
		if (isColinear(sorted))
			throw new IllegalArgumentException("N�o pode criar um envolt�rio convexo a partir de pontos colineares");
		Stack<Point> stack = new Stack<Point>();
		stack.push(sorted.get(0));
		stack.push(sorted.get(1));
		for (int i = 2; i < sorted.size(); i++) {
			Point head = sorted.get(i);
			Point middle = stack.pop();
			Point tail = stack.peek();
			Tipo tipo = vetorial(tail, middle, head);
			switch (tipo) {
			case ESQUERDA:
				stack.push(middle);
				stack.push(head);
				break;
			case DIREITA:
				i--;
				break;
			case COLLINEAR:
				stack.push(head);
				break;
			}
		}
		stack.push(sorted.get(0));
		return new ArrayList<Point>(stack);
	}

	/**
	 * Este m�todo tem a responsabilidade de obter da extremidade inferior da
	 * imagem
	 * 
	 * @param points
	 *            a lista de pontos demarcados na imagem
	 * @return o ponto que fica na extremidade inferior da imagem
	 */
	protected Point getPontoInferior(List<Point> points) {
		Point lowest = points.get(0);
		for (int i = 1; i < points.size(); i++) {
			Point temp = points.get(i);
			if (temp.y < lowest.y || (temp.y == lowest.y && temp.x < lowest.x))
				lowest = temp;
		}
		return lowest;
	}

	/**
	 * M�todo que tem a responsabilidade de ordenar os pontos recebidos como par�metro de entrada na imagem
	 * @param points a lista de pontos
	 * @return uma cole��o de pontos de forma ordenada
	 */
	protected Set<Point> getOrdenar(List<Point> points) {
		final Point lowest = getPontoInferior(points);
		TreeSet<Point> set = new TreeSet<Point>(new Comparador(lowest));
		set.addAll(points);
		return set;
	}
}
