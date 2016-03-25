import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.visitor.VoidVisitorAdapter;

public class ClassVisitor extends  VoidVisitorAdapter {

	 public void visit(ClassOrInterfaceDeclaration n, Object arg) {
		 String name=n.getName();
		 //System.out.println("Size of class being extended is "+n.getExtends().size());
		 Data.classExtendsList=n.getExtends();
		 if(Data.classExtendsList==null)
			 System.out.println("It is nulll");
		 int len;
		 int i=0;
		 if(Data.classExtendsList!=null)
		 {
			
		 len=Data.classExtendsList.size();
		 //System.out.println("Class "+name+" is being extended by ");
		 for(;i<len;i++)
		 {
			 
			// System.out.println(" "+Data.classExtendsList.get(i).toString());
			 Data.relationships.append(Data.classExtendsList.get(i).getName()+" ^- "+name+"\n");
		 }
	
		 }
		 Data.classImplementsList=n.getImplements();
		 if(Data.classImplementsList!=null)
		 {
			i=0; 
			 len=Data.classImplementsList.size();
			 for(;i<len;i++)
			 {
				 Data.relationships.append(Data.classImplementsList.get(i).getName()+" ^-.- "+name+"\n");
			 }
		 }
		 System.out.println("relation ships here below");
		 System.out.println(Data.relationships);
		 if(n.isInterface())
		 {
			 Data.InterfaceList.add(name);
			 Data.plant_input.append("interface "+name+"{\n");
		 }
		 else
		 {
			 Data.plant_input.append("class "+name+"{\n");
		 }
		 Data.classList.add(name);	
     	Data.relationship_backup =name;
     	
     	new VariableVisitor().visit(n, null);
    	
    	//to list all the methods inside the class
        new MethodVisitor().visit(n, null); 
        
        
        Data.plant_input.append("}\n");
	 }
	
}
