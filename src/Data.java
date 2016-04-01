import java.util.ArrayList;
import java.util.List;

import japa.parser.ast.type.ClassOrInterfaceType;

public class Data {
	
	 static List<String> classList = new ArrayList<String>();
	 static List<String> InterfaceList = new ArrayList<String>();
	 //static List<String> classList = new ArrayList<String>();
	 static List<ClassOrInterfaceType> classExtendsList=new ArrayList<ClassOrInterfaceType>();
	 static List<String> collectionList = new ArrayList<String>();
	 static List<String> variableList = new ArrayList<String>();
	 static List<String> AssociationList = new ArrayList<String>();
	 static List<ClassOrInterfaceType> classImplementsList=new ArrayList<ClassOrInterfaceType>();
	 static StringBuilder relationships = new StringBuilder();
	 static StringBuilder associations = new StringBuilder();
	 static String relationship_backup;
	 
	 static StringBuilder plant_input = new StringBuilder();
	 static List<String> classVarList=new ArrayList<String>();;
	 static List<String> varTypeList=new ArrayList<String>();
	 static List<String> constructorList = new ArrayList<String>();
	 static List<String> methodList = new ArrayList<String>();
	 static List<String> methodsList = new ArrayList<String>();
}
