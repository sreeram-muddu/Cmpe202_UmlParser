import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import net.sourceforge.plantuml.SourceStringReader;



public class Uml {

	public static void main(String[] args) throws FileNotFoundException, ParseException {
		// TODO Auto-generated method stub
        System.out.println("Hello");
		CompilationUnit parse;
		FileInputStream in;
		StringBuilder plant_input=new StringBuilder();
		
		File file_folder= new File(args[0]);
		File[] files=file_folder.listFiles();
		File f;  
		int len= files.length;
		int i=0;
		for(i=0;i<len;i++)
		{
			f=files[i];
			//System.out.println("List File in source code "+f.getName());
			
              if(f.exists())
              {
            	  if(f.isFile())
            	  {
            		  if(f.getName().toString().endsWith(".java"))
            		  {
            			  
            			  
            			  System.out.println("Java file :"+f.getName());
            			  in=new FileInputStream(f);
            			  parse=JavaParser.parse(in);
            			  new ClassVisitor().visit(parse, null);
            		  }
            	  }
              }
		}
		
		
		
		/*plant_input.append("@startuml\n");
		plant_input.append("Class01 <|-- Class02\n");
		
		plant_input.append("Class03 *-- Class04\n");
		plant_input.append("@enduml");*/
		OutputStream image;
		try {
			image=new FileOutputStream("test.png");
			SourceStringReader input = new SourceStringReader(plant_input.toString());
			input.generateImage(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
