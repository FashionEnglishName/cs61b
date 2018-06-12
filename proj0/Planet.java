public class Planet{
	/** Its current x position */
	public double xxPos;

	/** Its current y position */
	public double yyPos;

	/** Its current velocity in the x direction */
	public double xxVel;

	/** Its current velocity in the y direction */
	public double yyVel;

	/** Its mass */
	public double mass;

	/** The name of the file that corresponds to the image that depicts that 
	  * the planet */
	public String imgFileName;

	public static final double G = 6.67e-11;


	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	/** calculate the distance between THIS planet and the INPUT planet */
	public double calcDistance (Planet p) {
		double xxDist2 = (p.xxPos - xxPos) * (p.xxPos - xxPos);
		double yyDist2 = (p.yyPos - yyPos) * (p.yyPos - yyPos);

		return Math.sqrt(xxDist2 + yyDist2);
	}

	public double calcForceExertedBy (Planet p) {
		double distance = calcDistance(p);

		return G * p.mass * mass / (distance * distance);
	}

	public double calcForceExertedByX (Planet p) {
		double cos = (p.xxPos - xxPos) / calcDistance(p);

		return calcForceExertedBy(p) * cos;
	}

	public double calcForceExertedByY (Planet p) {
		double sin = (p.yyPos - yyPos) / calcDistance(p);

		return calcForceExertedBy(p) * sin;
	}

	public double calcNetForceExertedByX (Planet[] p) {
		double NetForceX = 0.0;
		for (Planet planet : p) {
			// Planets cannot exert gravitational forces on themselves
			if(this.equals(planet)){
				continue;
			}
			NetForceX += this.calcForceExertedByX(planet);
		}

		return NetForceX;
	}

	public double calcNetForceExertedByY (Planet[] p) {
		double NetForceY = 0.0;
		for (Planet planet : p) {
			// Planets cannot exert gravitational forces on themselves
			if(this.equals(planet)){
				continue;
			}
			NetForceY += this.calcForceExertedByY(planet);
		}
		
		return NetForceY;
	}

	public void update (double dt, double fX, double fY) {
		double aX = fX / mass;
		double aY = fY / mass;

		xxVel += aX * dt;
		yyVel += aY * dt;

		xxPos += xxVel * dt;
		yyPos += yyVel * dt;
	}

	public void draw () {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
		StdDraw.show();
	}
}