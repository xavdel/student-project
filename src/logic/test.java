package logic;

import com.microsoft.z3.*;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class test {

    static int i;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		i=0;
		Context ctx = new Context();
		//ctx.mkBoolSort();
		//System.out.println(ctx.getBoolSort());
	    // vecteur des sommets
	     Vector carac_sommets = new Vector(0);
	    // vecteur des tags des sommets
	     Vector carac_TAGsommets = new Vector(0);
	    // vecteur des arcs d'appui
	     Vector<arc> carac_appuis = new Vector(0);
	    // vecteur des arcs d'attaque
	     Vector<arc> carac_attaques = new Vector<arc>();	
	     
	     String f = "/home/ad31/workspace2/logic/src/logic/Ex1_Comp_Ext.gfx";
	     ArrayList<String> sommet = new ArrayList<String>();
	     Map<String, arc> att = new HashMap<>();
	     Map<String, arc> app = new HashMap<>();
	     try{
				InputStream ips=new FileInputStream(f); 
				InputStreamReader ipsr=new InputStreamReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				String ligne;
				while ((ligne=br.readLine())!=null){
					System.out.println(ligne);
					if (ligne.equals("ARG:")){
						while ((ligne=br.readLine())!=null&&(!(ligne.equals(".")))){
							System.out.println(ligne.substring(1, 2));
							sommet.add(ligne);
						}
						System.out.println("Trc");
					}
					if (ligne.equals("ATT:")){
						while ((ligne=br.readLine())!=null&&(!(ligne.equals(".")))){
							System.out.println(ligne.substring(1, 2));
						}
						System.out.println("Trc");
					}
					
					if (ligne.equals("APP:")){
						while ((ligne=br.readLine())!=null&&(!(ligne.equals(".")))){
							System.out.println(ligne.substring(1, 2));
						}
						System.out.println("Trc");
					}
				}
				br.close(); 
			}		
			catch (Exception e){
				System.out.println(e.toString());
			}
	     
	    //System.out.println(trx);
	     
	     
	     
	     
	     
	     ajoutsommet2(carac_sommets, 0);
	     ajoutsommet2(carac_sommets, 1);
	     ajoutsommet2(carac_sommets, 2);
	     ajoutarca(carac_sommets,10,1,2);
	     System.out.println(carac_attaques);
	     for (arc temp: carac_attaques ){
	    	 if (carac_sommets.contains(temp.ori)&&carac_sommets.contains(temp.dest))
	    	 System.out.println("(A"+temp+"^"+temp.ori+")->In"+temp.dest);
	     }
	     
	     for (arc temp: carac_appuis ){
	    	 if (carac_sommets.contains(temp.ori)&&carac_sommets.contains(temp.dest))
	    	 System.out.println("(A"+temp+"^"+temp.dest+")->Pr"+temp.ori);
	     }
	     
	     	     for (arc temp: carac_attaques ){
	    	 if (carac_sommets.contains(temp.ori)&&carac_sommets.contains(temp.dest))
	    	 System.out.println("In"+temp.ori+"-> non "+temp.ori);
	     }
	     	     
	     for (arc temp: carac_appuis ){
	    	 if (carac_sommets.contains(temp.ori)&&carac_sommets.contains(temp.dest))
	    	 System.out.println("Pr"+temp.dest+"-> "+temp.dest);
	     }
	     
	     // Truc

}
	
	private static void ajoutsommet2(Vector carac_sommets, int i) {
		// TODO Auto-generated method stub
		carac_sommets.add(i);
	}
	
	private static void ajoutarca(Vector carac_appuis,int id, int ini, int dest) {
		// TODO Auto-generated method stub
		carac_appuis.add(new arc(id,ini,dest));
	}
	
	private static void ajoutarcatt(Vector carac_attaques,int id, int ini, int d) {
		// TODO Auto-generated method stub
		carac_attaques.add(new arc(id,ini,d));
	}
	

	public static void ajoutsommet(Context ctx){
		BoolExpr a = ctx.mkFalse();
		BoolExpr b = ctx.mkBoolConst("> 0.0 0.0");
		BoolExpr c = ctx.mkAnd(a,b);
		Solver solver = ctx.mkSolver();
		//solver.add(a);
		solver.add(b);
		//solver.add(c);
		solver.add(ctx.mkOr(a,b));
		
		System.out.println(solver.check().name());
		for (BoolExpr d : solver.getAssertions()){
			System.out.println(d.toString());
		}
				}


}
