import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import net.sourceforge.plantuml.SourceStringReader;



public class Umlparser {
	
	
	public static void getset()
	{
	int i , len=0;
	len=Data.variableList.size();
	for(i=0;i<len;i++)
		{
			String variable= Data.variableList.get(i).toString();
			//getting getters and setters.
			String getComp="get"+variable;	
			int get_index= Data.methodList.indexOf(getComp);
			String setComp="set"+variable;
			int set_index=Data.methodList.indexOf(setComp);
				if(get_index!=-1 && set_index!=-1)
					{
						String[] tokens_v=variable.split(":");
						Data.plant_input.replace(Data.plant_input.indexOf(tokens_v[0])-1,Data.plant_input.indexOf(tokens_v[0]), "+");
						String tokens_gm[]=Data.methodList.get(i).split(":");
						String tokens_sm[]=Data.methodList.get(i).split(":");
						int start,end;
						start=Data.plant_input.indexOf(tokens_gm[0]);
						end=Data.plant_input.indexOf("\n",start);
						Data.plant_input.delete(start-1, end+1);
						start=Data.plant_input.indexOf(tokens_sm[0]);
						end=Data.plant_input.indexOf("\n",start);
						Data.plant_input.delete(start-1, end+1);
					}
		
		}
	
	}
	public static void multiplicity()
	{
		
		Iterator it=Data.collectionList.iterator();
		String classname;
		while(it.hasNext())
		  {
			  classname=it.next().toString();
			  String[] tokens=classname.split(":");
			  if(Data.collectionList.contains(tokens[1]+tokens[0]))
			  	{
				    if(Data.InterfaceList.contains(tokens[1]))
				    {
				    	Data.associations.append("");
				    }
				    
				    else if(Data.InterfaceList.contains(tokens[0]))
				    {
				    	Data.associations.append("");
				    }
				    else
				    {
				    	Data.associations.append(tokens[0]+"]\"*\" -- \"*\"["+tokens[1]+"\n");
				    }
				    String s=tokens[0]+" -- "+tokens[1]+"\n";
				    if(Data.associations.indexOf(s)!=-1)
				    {
				    	Data.associations.replace(Data.associations.indexOf(s),Data.associations.indexOf(s)+s.length(), "");
				    }
			  	}
			  else
			  {
				  if(Data.InterfaceList.contains(tokens[1]))
				  {
					  Data.associations.append("");  
				  }
				  else if(Data.InterfaceList.contains(tokens[0]))
				  {
					  Data.associations.append("");
				  }
				  else
				  {
					  
					  Data.associations.append(tokens[0]+"\"*\" -- \"1\""+tokens[1]+"\n");
				  }
				
				  String s=tokens[0]+" -- "+tokens[1]+"\n";
				  if(Data.associations.indexOf(s)!=-1){
					  Data.associations.replace(Data.associations.indexOf(s), Data.associations.indexOf(s)+s.length(), "");
				  }
			  }
			  
			  
		  }
		
		
	}

	
	
	public static void association()
		{
			Iterator it=Data.varTypeList.iterator();
			String match=null,match_first,match_second,ass="";
			
			while(it.hasNext())
			{
				match=it.next().toString();
				if(Data.classList.indexOf(match)!=-1)
				{
					List<Integer>indexes=new ArrayList<Integer>();
					int i=0,len=Data.varTypeList.size();
					for(;i<len;i++)
					{
						if(Data.varTypeList.get(i)==match)
							{
								indexes.add(i);
							}
						
						
					}
					len=indexes.size();
					for(i=0;i<len;i++)
					{
						match_first=Data.varTypeList.get(indexes.get(i)).toString();
						match_second=Data.classVarList.get(indexes.get(i)).toString();
					    if(Data.InterfaceList.contains(match_first)&& !Data.InterfaceList.contains(match_second))
					    {
					    	ass = match_second+"\"uses\" -.-> "+match_first+"\n";
					    }
					    if(!Data.InterfaceList.contains(match_first)&&Data.InterfaceList.contains(match_second))
					    {
					    	ass = match_first+"\"uses\" -.-> "+match_second+"\n";
					    }
					    if(Data.InterfaceList.contains(match_first)&&Data.InterfaceList.contains(match_second))
					    {
					    	ass="";
					    }
					    if(!Data.InterfaceList.contains(match_first)&& !Data.InterfaceList.contains(match_second))
					    {
					    		if(Data.AssociationList.contains(match_first+":"+match_second))
					    				{
					    					String s=match_first+" -- "+match_second+"\n";
					    					
					    					if(Data.AssociationList.indexOf(s)!=-1)
					    					{
					    						Data.plant_input.replace(Data.AssociationList.indexOf(s), Data.AssociationList.indexOf(s)+s.length(),match_first+"\"1\" -- \"1\""+match_second+"\n");
					    					}
					    				}
					    		else
					    		{
					    			Data.AssociationList.add(match_second+":"+match_first);
					    			if(Data.constructorList.contains(match_second+":"+match_first))
					    			{
					    				ass="";
					    			}
					    			else
					    			{
					    				ass = match_second+" -- "+match_first+"\n";
					    			}
					    		}
					    }
					    if(Data.associations.indexOf(ass)==-1)
					    	Data.associations.append(ass);
					}
					
				}
				
				
				
			}
		}
	public static void main(String[] args) throws FileNotFoundException, ParseException {
		// TODO Auto-generated method stub
        //System.out.println("Hello");
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
            			 // ClassVisitor cv= new ClassVisitor();
            			  new ClassVisitor().visit(parse, null);
            		  }
            	  }
              }
		}
		System.out.println("After visiting everything");
		association();
		multiplicity();
		getset();
		
		
		/*plant_input.append("@startuml\n");
		plant_input.append("Class01 <|-- Class02\n");
		
		plant_input.append("Class03 *-- Class04\n");
		plant_input.append("@enduml");*/
		OutputStream image;
		try {
			image=new FileOutputStream(args[1]+".png");
			String input_constructor="@startuml\n";
			input_constructor=input_constructor+Data.plant_input;
			input_constructor=input_constructor+Data.relationships.toString();
			input_constructor=input_constructor+Data.associations.toString();
			input_constructor=input_constructor+"@enduml\n";
			System.out.println("This is the input");
			System.out.println(input_constructor);
			 SourceStringReader read = new SourceStringReader(input_constructor);
		       // Write the first image to "png"
		       String descriptioin = read.generateImage(image);
		       // Return a null string if no generation
		       image.close();
		       System.out.println("Please check output");
			/*SourceStringReader input = new SourceStringReader(plant_input.toString());
			input.generateImage(image);*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
