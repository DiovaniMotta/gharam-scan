package br.com.furb.graham.scan.algorithm;

import java.util.Comparator;

import br.com.furb.graham.scan.algorithm.GrahamScan.Point;

public class Comparador implements Comparator<Point> {

	private Point point;

	public Comparador(Point point) {
		super();
		this.point = point;
	}

	@Override
	public int compare(Point a, Point b) {
		if (a == b || a.equals(b))
			return 0;
		double thetaA = Math.atan2((long) a.y - point.y, (long) a.x - point.x);
		double thetaB = Math.atan2((long) b.y - point.y, (long) b.x - point.x);
		if (thetaA < thetaB)
			return -1;
		else if (thetaA > thetaB)
			return 1;
		else {
			double distanceA = Math.sqrt((((long) point.x - a.x) * ((long) point.x - a.x))
					+ (((long) point.y - a.y) * ((long) point.y - a.y)));
			double distanceB = Math.sqrt((((long) point.x - b.x) * ((long) point.x - b.x))
					+ (((long) point.y - b.y) * ((long) point.y - b.y)));
			if (distanceA < distanceB)
				return -1;
			else
				return 1;
		}
	}
}
