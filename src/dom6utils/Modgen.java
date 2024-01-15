package dom6utils;

public class Modgen {

	public static void main(String[] args) {
		for (int y = 1; y <= 1000; y++) {
			System.out.println("#land " + (y));
			System.out.println("#owner 5");
			System.out.println("#commander " + y);
			System.out.println("#additem \"Amulet of the Fish\"");
		}
		
	}
}