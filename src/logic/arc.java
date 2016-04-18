package logic;

public class arc {
	public arc(int id2, int ini, int d) {
		// TODO Auto-generated constructor stub
		id=id2;
		ori=ini;
		dest=d;
	}
	int id;
	public int ori;
	public int dest;
	public String toString(){
	return "("+id+","+ori+","+dest+")";
	}
}
