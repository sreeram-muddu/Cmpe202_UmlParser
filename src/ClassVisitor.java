import japa.parser.ast.body.ClassOrInterfaceDeclaration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.ModifierSet;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.type.ClassOrInterfaceType;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import net.sourceforge.plantuml.SourceStringReader;
public class ClassVisitor extends  VoidVisitorAdapter {

	 @SuppressWarnings("unchecked")
	public void visit(ClassOrInterfaceDeclaration n, Object arg) {
		 String name=n.getName().toString();
		 System.out.println(" Just visiting Class "+name);
		 //System.out.println("Size of class being extended is "+n.getExtends().size());
		 Data.classExtendsList=n.getExtends();
		// if(Data.classExtendsList==null)
			// System.out.println("It is nulll");
		 int len;
		 int i=0;
		 if(Data.classExtendsList!=null)
		 {
			
		 len=Data.classExtendsList.size();
		 //System.out.println("Class "+name+" is being extended by ");
		 for(;i<len;i++)
		 {
			 
			// System.out.println(" "+Data.classExtendsList.get(i).toString());
			 Data.relationships.append(Data.classExtendsList.get(i).getName().toString()+" ^- "+name+"\n");
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
		 //System.out.println("relation ships here below");
		 //System.out.println(Data.relationships);
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
     	//System.out.println("Calling variable visitor.........");
     	//new VariableVisitor().visit(n, null);
     	
     	new VariableVisitor().visit(n, arg);
     	new MethodVisitor().visit(n, null); 
        //System.out.println("done with variable visitor");
        Data.plant_input.append("}\n");
        System.out.println("At end of Class visitor ");
        System.out.println("---------------------------------------------------------------------");
    	System.out.println("plant input is "+Data.plant_input);
    	System.out.println("---------------------------------------------------------------------");
	 }
	    static class MethodVisitor extends VoidVisitorAdapter<Object> {
	        private static String methodName = "";

	        
	        @Override
	        public void visit(ConstructorDeclaration n, Object arg) 
	        {
	        	System.out.println("First visit method in MethodVisitor");
	        String constructor_name=n.getName().toString();
	        int modifiers=n.getModifiers();
	        if(ModifierSet.isPrivate(modifiers)){
        		Data.plant_input.append("-");
        	}
        	if(ModifierSet.isPublic(modifiers)){
    			Data.plant_input.append("+");
        	}
        	if(ModifierSet.isProtected(modifiers)){
    			Data.plant_input.append("#");
        	}
	        Data.plant_input.append(n.getName().toString());
	        List<Parameter> p = n.getParameters();
	        int i=0,len;
	        if(p!=null)
	        {
	        	len=p.size();
	        	Data.plant_input.append("(");
	        while(i<len)
	        {
	        	
	        Parameter param=p.get(i);
	        Data.plant_input.append(param.getId().toString()+" : "+param.getType().toString());
	        	Data.classVarList.add(Data.relationship_backup);
	        	Data.varTypeList.add(param.getType().toString());
	        	Data.constructorList.add(Data.relationship_backup+":"+param.getType().toString());
	        	
	        	
	        	i=i+1;
	        }
	        Data.plant_input.append(")");
	        }
	        else
	        	Data.plant_input.append("()");
	      /*  if(p==null)
	        	 Data.plant_input.append("()\n");*/
	        
	        System.out.println("At end of Constructor  visitor ");
	        System.out.println("---------------------------------------------------------------------");
        	System.out.println("plant input is "+Data.plant_input);
        	System.out.println("---------------------------------------------------------------------");
        	Data.plant_input.append("\n");
	        }
	        
	        
	        @Override
	        public void visit(MethodDeclaration n, Object arg) 
	        {
	        	
	        	System.out.println("Second visit method in MethodVisitor");
	        	String method_name=n.getName();
		        int modifiers=n.getModifiers();
		        if(ModifierSet.isPrivate(modifiers)){
	        		Data.plant_input.append("-");
	        	}
	        	if(ModifierSet.isPublic(modifiers)){
	    			Data.plant_input.append("+");
	        	}
	            if(ModifierSet.isProtected(modifiers)){
	    			Data.plant_input.append("#");
	        	}
		        Data.plant_input.append(n.getName().toString());
		        List<Parameter> p = n.getParameters();
		        int i=0,len;
		        if(p!=null)
		        {
		        len=p.size();
		        Data.plant_input.append("(");
		        while(i<len)
		        {
		        	Parameter param=p.get(i);
		        	   Data.plant_input.append(param.getId().toString()+" : "+param.getType().toString());
			        	Data.classVarList.add(Data.relationship_backup);
			        	Data.varTypeList.add(param.getType().toString());
			        	//Data.methodList.add(Data.relationship_backup+":"+param.getType().toString());
		         i=i+1;       	
		        }
		        Data.plant_input.append(")");
		        
		        }
		        else
		        	Data.plant_input.append("()");
		        
		        System.out.println("At end of second Method visitor 2");
		        
		        	
		        Data.plant_input.append(" : "+ n.getType()+"\n");
	        	Data.methodsList.add(n.getName().toLowerCase()+":"+Data.relationship_backup);
	        	Data.methodList.add(n.getName().toString()+":"+Data.relationship_backup);
	        	System.out.println("---------------------------------------------------------------------");
	        	System.out.println("plant input is "+Data.plant_input);
	        	System.out.println("---------------------------------------------------------------------");
	        }

	    }
	     static class VariableVisitor extends VoidVisitorAdapter<Object> {
	        @Override
	        public void visit(FieldDeclaration n, Object arg) {
	        	System.out.println("Variable visitor ");
	        	String data_type=n.getType().toString();
	        	int modifiers=n.getModifiers();
	        	if(ModifierSet.isPrivate(modifiers)){
            		Data.plant_input.append("-");
            	}
	        	if(ModifierSet.isPublic(modifiers)){
        			Data.plant_input.append("+");
            	}
	        	if(ModifierSet.isProtected(modifiers)){
        			Data.plant_input.append("#");
            	}
        		
	        	else if(modifiers==0)
        		{
        			
        		}
        		else
        		{
        			
        	     Iterator<VariableDeclarator> i= n.getVariables().iterator();
        			
        	     while(i.hasNext())
        			{
        				
        	    	 VariableDeclarator v=i.next();
        	    	 
        	    	 	Data.plant_input.append(v.getId().toString());
        	    	 	Data.variableList.add(v.getId().toString()+" : "+Data.relationship_backup);
        				if(data_type.startsWith("Collection<")){
        				Data.collectionList.add(data_type.substring(11,data_type.length()-1)+":"+Data.relationship_backup);
        				Data.plant_input.delete(Data.plant_input.length()-(v.getId().toString().length())-1, Data.plant_input.length());
        				}
        				else if(data_type.equalsIgnoreCase(v.getId().toString())){
        					Data.plant_input.delete(Data.plant_input.length()-(v.getId().toString().length())-1, Data.plant_input.length());
        				}
        				else
        				Data.plant_input.append(" : "+data_type+"\n");
        			}
        	     Data.classVarList.add(Data.relationship_backup);
         		Data.varTypeList.add(n.getType().toString());
        			
        			
        			
        			
        		}
	        	System.out.println("At end of variable visitor ");
	        	System.out.println("---------------------------------------------------------------------");
	        	System.out.println("plant input is "+Data.plant_input);
	        	System.out.println("---------------------------------------------------------------------");
	        }
	
	    }
}