package logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
public class arc {
	public arc(String id2, String ini, String d, Context ctx, Boolean attack) {
		// TODO Auto-generated constructor stub
		id=id2;
		ori=ini;
		dest=d;
		actif=null;
		inactif=null;

	}
	
static BoolExpr active(arc utilise, Context ctx, Boolean actifoupas, Boolean attack) {
		// TODO Auto-generated method stub
	// TODO calcul de l'acivation ou pas (récursif)
	// ne gère pas les multi attaque /support 
	// initialisation et vérification de précalculation
	String ini;
	String d;
	String id2=utilise.id;
	/* partie inutile sans pointeurs
	 if (utilise.bool!=null){
		 if (actifoupas){
			 return utilise.actif;
		 }
		 else{
			 return utilise.inactif;
		 }
	 }*/
	ini=utilise.ori;
	d=utilise.dest;
	//TODO fin initialisation
	int i =0;
	int j =0;
	arc att = null;
	arc sup = null;
	for (arc temp : carac_appuis.values()){
		if (temp.dest.equals(id2)){
			sup=temp;
			j++;
		}
	}
	
	for (arc temp :carac_attaques.values()){
		if (temp.dest.equals(id2)){
			att=temp;
			i++;
		}
	}
	if (i==0 && j==0){
		utilise.actif=ctx.mkTrue();
		utilise.inactif=ctx.mkFalse();
	}
	else{
		if (i>0){
			if (j>0){
			//il existe les deux
				//arc att2=sup;
				//arc sup2=att;
				utilise.actif=ctx.mkAnd(
						ctx.mkAnd(Sommet.carac_sommets.get(sup.ori).bool,
								active(sup,ctx,true, false)),
						ctx.mkOr(ctx.mkNot(Sommet.carac_sommets.get(att.ori).bool),
								active(att,ctx,false, true)));

				utilise.inactif=ctx.mkOr(
						ctx.mkOr(ctx.mkNot(Sommet.carac_sommets.get(sup.ori).bool),
								active(sup,ctx,false, false)),
						ctx.mkAnd(Sommet.carac_sommets.get(att.ori).bool,
								active(att,ctx,true, true))
						);

			}
			else{
				// seul l'attaque existe
				utilise.actif=ctx.mkOr(ctx.mkNot(Sommet.carac_sommets.get(att.ori).bool),
								active(att,ctx,false, true));
				utilise.inactif=ctx.mkAnd(Sommet.carac_sommets.get(att.ori).bool,
								active(att,ctx,true, true));
			}
		}
		else{// i=0 seul le support existe
			if (j>0){
				utilise.actif=ctx.mkAnd(Sommet.carac_sommets.get(sup.ori).bool,
								active(sup,ctx,true, false));
				utilise.inactif=ctx.mkOr(ctx.mkNot(Sommet.carac_sommets.get(sup.ori).bool),
								active(sup,ctx,false, false));
			}				
			// i=0 j=0 déjà fait
		}
	}
	
		if (actifoupas){
			return utilise.actif;
		}
		else{
			return utilise.inactif;
		}
}
	public static Map<String, arc> carac_attaques;
    public static Map<String, arc> carac_appuis;

	BoolExpr bool;
	BoolExpr actif;
	BoolExpr inactif;
	public String id;
	public String ori;
	public String dest;
	public String toString(){
	return "("+id+","+ori+","+dest+")";
	}
}
