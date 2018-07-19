package papaa;

public class c1 {
	protected int nb;
	private static int INDEX = 1;
	

	public c1() {
		this.nb = INDEX;
		INDEX++;
	}
	
	public void print() {
		System.out.println(nb);
	}
	
	
	
	

}
