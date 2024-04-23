package dom6utils;

public class Modgen {

	public static void main(String[] args) {
		for (int y = 1; y <= 394; y++) {
			System.out.println("#land " + y);
			System.out.println("#owner 5");
			System.out.println("#commander " + (y));
			System.out.println("#additem \"Amulet of the Fish\"");
			System.out.println("#knownfeature " + (y + 1000));
		}
		
//		for (int y = 1072; y <= 1456; y++) {
//			System.out.println("#selectspell " + y);
//			System.out.println("#researchlevel 0");
//			System.out.println("#end");
//		}

	}
}