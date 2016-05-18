package logic;

import java.util.Map;
import java.util.Vector;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;

public class Sommet {
String id;
BoolExpr bool;
Choix chx;
// vecteur des sommets
static Map<String,Sommet> carac_sommets;
static Vector <Sommet> table;

public Sommet(String id, Context ctx) {
	this.id = id;
	bool=ctx.mkBoolConst(id);
        chx=Choix.UNDETERMINED;
	carac_sommets.put(id, this);
        table.add(this);
}

}
