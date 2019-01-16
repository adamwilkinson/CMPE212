
public class Tester {

	public static void main(String[] args) throws IllegalPizza {
		LineItem higher = new LineItem(2, new Pizza("small", "double", "none", "single"));		
		System.out.println(higher.getCost());
		
		LineItem lower = new LineItem(3, new Pizza("small", "double", "none", "none"));
		
		System.out.println(lower.getCost());
		System.out.println(higher.getCost());
		System.out.println(higher.compareTo(lower));
		System.out.println(higher.getNumber());
		System.out.println(lower.getNumber());
	}

}
