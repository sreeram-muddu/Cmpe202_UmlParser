import java.util.ArrayList;
import java.util.List;

import japa.parser.ast.type.ClassOrInterfaceType;

public class Data {
	
	 static List<String> classList = new ArrayList<String>();
	 static List<String> InterfaceList = new ArrayList<String>();
	 //static List<String> classList = new ArrayList<String>();
	 static List<ClassOrInterfaceType> classExtendsList=new ArrayList<ClassOrInterfaceType>();
	 static List<ClassOrInterfaceType> classImplementsList=new ArrayList<ClassOrInterfaceType>();
	 static StringBuilder relationships = new StringBuilder();
	 static String relationship_backup;
	 static StringBuilder plant_input = new StringBuilder();
}
