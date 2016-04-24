package logic;

import com.microsoft.z3.*;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.omg.PortableInterceptor.ACTIVE;

import java.util.Map.Entry;

public class test {

    static int i;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		i=0;
		Context ctx = new Context();
	    // vecteur des sommets
	     Sommet.carac_sommets =new HashMap<String, Sommet>();
	    // vecteur des arcs d'appui
	     arc.carac_appuis = new HashMap<String, arc>();
	    // vecteur des arcs d'attaque
	     arc.carac_attaques = new HashMap<String, arc>();
	     String f;
	     if (args.length ==0){
	    	  f = "/home/ad31/eclipse/java-neon4/eclipse/logic/src/logic/Graph2-pourXavier.gfx";
	     }else{
	    	  f=args[0];
	     }
	     try{

				InputStream ips=new FileInputStream(f); 
				InputStreamReader ipsr=new InputStreamReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				String ligne;
				String g;
				while ((ligne=br.readLine())!=null){
					System.out.println(ligne);
					if (ligne.equals("ARG:")){
						while ((ligne=br.readLine())!=null&&(!(ligne.equals(".")))){
							g=ligne.substring(1, ligne.length()-1);
							String[] t =g.split(",");
							Sommet.carac_sommets.put(t[0],new Sommet(t[0],ctx));
						}
					}
					if (ligne.equals("ATT:")){
						while ((ligne=br.readLine())!=null&&(!(ligne.equals(".")))){
							g=ligne.substring(1, ligne.length()-1);
							String[] t =g.split(",");
							arc.carac_attaques.put(t[0],new arc(t[0],t[1], t[2], ctx, true));
						}
						
					}

					if (ligne.equals("APP:")){
						while ((ligne=br.readLine())!=null&&(!(ligne.equals(".")))){
							g=ligne.substring(1, ligne.length()-1);
							String[] t =g.split(",");
							arc.carac_appuis.put(t[0],new arc(t[0],t[1], t[2], ctx, false));
						}
					}
				}
				br.close(); 
			}		
			catch (Exception e){
				System.out.println(e.toString());
			}

	     System.out.println(Sommet.carac_sommets);
	     System.out.println(arc.carac_attaques);
	     System.out.println(arc.carac_appuis);
	     Solver s=ctx.mkSolver();
	     
	     
	for (arc temp :arc.carac_attaques.values()){
		BoolExpr bool;
		if (!(temp.ori.contains("to")||temp.dest.contains("to"))){
		// (Ak et a) -> Inb avec k=(a,b)
		// (resolution INb si une relation est de la forme (x,b) existe dans les attaques)
		// or k est une attaque et k est de la forme k=(a,b)
		bool=ctx.mkImplies(ctx.mkAnd(arc.active(temp, ctx, true, true), Sommet.carac_sommets.get(temp.ori).bool), 
		ctx.mkNot(Sommet.carac_sommets.get(temp.dest).bool));
		System.out.println("Support");
		System.out.println(temp);
		System.out.println(bool);
		s.add(bool);
		}
	}
	
	
	for (arc temp : arc.carac_appuis.values()){
		BoolExpr bool;
		if (!(temp.ori.contains("to")||temp.dest.contains("to"))){
		
		//Idem que l'attaque pour Pra
		bool=ctx.mkImplies(ctx.mkAnd(arc.active(temp, ctx, true, false),Sommet.carac_sommets.get(temp.dest).bool),
		Sommet.carac_sommets.get(temp.ori).bool);
		System.out.println("Attaque");
		System.out.println(temp);
		System.out.println(bool);
		s.add(bool);
		}
	}
	
	
	System.out.println("Conversion s3");
	System.out.println(s.toString());
	System.out.println("Résultat : "+s.check());
	System.out.println("Une solution");
	System.out.println(s.getModel());
	}
}
