import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.visitor.VoidVisitorAdapter;

public class VariableVisitor extends VoidVisitorAdapter {

	public void visit(FieldDeclaration n, Object object) {
		// TODO Auto-generated method stub
		System.out.println("helooooooo m in variable visit");
		int modifiers=n.getModifiers();
	     String type=n.getType().toString();
       System.out.println("modifiers is "+modifiers);	
       System.out.println("Type is "+type);
	     
	}

}
