public class TestPlanet {
	public static void main(String[] args) {
		Planet p1 = new Planet(1.0e12, 2.0e11, 2.0, 2.0, 2.0e30, "123");
		Planet p2 = new Planet(2.3e12, 9.5e11, 3.0, 3.0, 6.0e26, "456");

		System.out.println(p1.calcForceExertedBy(p2));
	}
}