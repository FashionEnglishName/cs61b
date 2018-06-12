public class NBody {
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		double time = 0.0;
		String filename = args[2];

		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		int length = planets.length;


		StdDraw.enableDoubleBuffering();

		StdDraw.setScale(0 - radius, radius);

		StdDraw.clear();

		while(time != T) {
			double[] xForces = new double[length];
			double[] yForces = new double[length];

			for(int i = 0; i < length; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			for(int i = 0; i < length; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0, 0, "images/starfield.jpg");
			for(Planet p : planets) {
				p.draw();
			}

			StdDraw.show();
			StdDraw.pause(10);

			time += dt;
		}

		
		
	}

	public static double readRadius (String path) {
		In in = new In(path);

		int n = in.readInt();
		double radius = in.readDouble();

		return radius;
	} 

	public static Planet[] readPlanets (String path) {
		In in = new In(path);

		int n = in.readInt();
		double radius = in.readDouble();

		Planet[] planets = new Planet[n];


		for(int i = 0; i < n; i ++) {
			// planet.xxPos = in.readDouble();
			// planet.yyPos = in.readDouble();
			// planet.xxVel = in.readDouble();
			// planet.yyVel = in.readDouble();
			// planet.mass = in.readDouble();
			// planet.imgFileName = in.readString();
			planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
		}		

		return planets;
	}
}