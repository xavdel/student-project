package arc;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String g = "(attack0to3,0,3,)";
		g=g.substring(1, g.length()-1);
		String[] t =g.split(",");
		System.out.println(t.toString());
		for (String temp : t){
			System.out.println(temp);
		}
	}

}
