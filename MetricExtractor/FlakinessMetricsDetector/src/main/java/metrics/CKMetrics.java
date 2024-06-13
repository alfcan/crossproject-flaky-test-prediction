package metrics;

import bean.*;
import computation.FileUtility;

import java.io.IOException;
import java.util.Collection;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CKMetrics {

	public static int getELOC(ClassBean cb){
		return cb.getTextContent().split("\r\n|\r|\n").length;
	}

	public static int getLOC(ClassBean cb){
		
		try {
			String content = FileUtility.readFile(cb.getPathToClass());
			return content.split("\r\n|\r|\n").length;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public static int getMcCabeMetric(ClassBean cb) {

		int WMC = 0;

		Vector<MethodBean> methods = (Vector<MethodBean>) cb.getMethods();
		for(MethodBean m: methods){
			WMC+=getMcCabeCycloComplexity(m);
		}

		return WMC;

	}

	public static int getHalsteadVocabulary(ClassBean cb) {
		return cb.getTextContent().split(" ").length;
	}

	public static int getHalsteadLenght(ClassBean cb) {
		String tokens[] = cb.getTextContent().split(" ");
		Vector<String> distinct = new Vector<String>();
		
		for(String token:tokens) {
			if(! distinct.contains(token)) {
				distinct.add(token);
			}
		}
		
		return distinct.size();
	}
	
	public static double getHalsteadVolume(ClassBean cb) {
		return getHalsteadLenght(cb) * Math.log(getHalsteadVocabulary(cb));
	}
	
	// TODO: add conceptual metrics. 
	
	public static int getDIT(ClassBean cb, Vector<ClassBean> System, int inizialization){

		int DIT = inizialization;

		if(DIT == 3) 
			return DIT;
		else {
			if(cb.getSuperclass() != null) {
				DIT++;
				for(ClassBean cb2: System){
					if(cb2.getName().equals(cb.getSuperclass())){
						getDIT(cb2, System, DIT);
					}
				}
			} else {
				return DIT;
			}
		}
		return DIT;

	}

	public static double getAssertionDensity(MethodBean mb){
		double assertionDensity = 0.0;
		double size = mb.getTextContent().split("\n").length;

		assertionDensity = mb.getAssertions() / size;

		return assertionDensity;
	}


	public static int getNOC(ClassBean cb, Vector<ClassBean> System){

		int NOC = 0;

		for(ClassBean c: System){
			if(c.getSuperclass() != null && c.getSuperclass().equals(cb.getName())){
				NOC++;
			}
		}

		return NOC;

	}

	public static int getRFC(ClassBean cb){
		int RFC = 0;

		Vector<MethodBean> methods = (Vector<MethodBean>) cb.getMethods();	
		RFC+=methods.size();
		
		for(MethodBean m: methods){
			RFC+=m.getMethodCalls().size();
		}

		return RFC;
	}

	public static int getCBO(ClassBean cb){
		Vector<String> imports = (Vector<String>) cb.getImports();

		return imports.size();
	}

	public static int getMPC(ClassBean cb){
		int invocations=0;
		
		for(MethodBean methodBean: cb.getMethods()) {
			invocations+=methodBean.getMethodCalls().size();
		}
		
		return invocations;
	}
	
	public static int getIFC(ClassBean cb){
		int invocations=0;
		int parameters=0;
		
		for(MethodBean methodBean: cb.getMethods()) {
			for(MethodBean call: methodBean.getMethodCalls()) {
				invocations++;
				parameters+=call.getParameters().size();
			}
		}
		
		return invocations/parameters;
	}
	
	public static int getDAC(ClassBean cb){
		int dac=0;
		for(InstanceVariableBean attribute: cb.getInstanceVariables()) {
			if(! attribute.getType().equals(cb.getName())) {
				dac++;
			}
		}
		
		return dac;
	}
	
	public static int getDAC2(ClassBean cb){
		Vector<String> types = new Vector<String>();
		
		for(InstanceVariableBean attribute: cb.getInstanceVariables()) {
			if(! attribute.getType().equals(cb.getName())) {
				if(! types.contains(attribute.getType())) {
					types.add(attribute.getType());
				}
			}
		}
		
		return types.size();
	}
	
	public static int getLCOM1(ClassBean cb){
		int notShare = 0;

		Vector<MethodBean> methods = (Vector<MethodBean>) cb.getMethods();
		for(int i=0; i<methods.size(); i++) {
			for (int j=i+1; j<methods.size(); j++){
				if(! shareAnInstanceVariable(methods.elementAt(i), methods.elementAt(j))){
					notShare++;
				}
			}
		}

		return notShare;
	}

	public static int getLCOM2(ClassBean cb){

		int share = 0;
		int notShare = 0;

		Vector<MethodBean> methods = (Vector<MethodBean>) cb.getMethods();
		for(int i=0; i<methods.size(); i++) {
			for (int j=i+1; j<methods.size(); j++){
				if(shareAnInstanceVariable(methods.elementAt(i), methods.elementAt(j))){
					share++;
				} else {
					notShare++;
				}
			}
		}

		if(share > notShare){
			return 0;
		} else {
			return (notShare-share);
		}
	}

	public static int getLCOM3(ClassBean cb){
		int share = 0;
		
		Vector<MethodBean> methods = (Vector<MethodBean>) cb.getMethods();
		for(int i=0; i<methods.size(); i++) {
			for (int j=i+1; j<methods.size(); j++){
				if(shareAnInstanceVariable(methods.elementAt(i), methods.elementAt(j))){
					share++;
				}
			}
		}

		return share;
	}
	
	public static int getLCOM4(ClassBean cb){
		int share = 0;
		
		Vector<MethodBean> methods = (Vector<MethodBean>) cb.getMethods();
		for(int i=0; i<methods.size(); i++) {
			for (int j=i+1; j<methods.size(); j++){
				if( shareAnInstanceVariable(methods.elementAt(i), methods.elementAt(j)) || existDependencyBetween(methods.elementAt(i), methods.elementAt(j))){
					share++;
				}
			}
		}

		return share;
	}
	
	public static int getConnectivity(ClassBean cb) {
		int vertices = cb.getMethods().size();
		int edges = 0;
		int connectivity = 0;
		
		Vector<MethodBean> methods = (Vector<MethodBean>) cb.getMethods();
		for(int i=0; i<methods.size(); i++) {
			for (int j=i+1; j<methods.size(); j++){
				if( shareAnInstanceVariable(methods.elementAt(i), methods.elementAt(j)) || existDependencyBetween(methods.elementAt(i), methods.elementAt(j))){
					edges++;
				}
			}
		}
		
		connectivity = 2 * ( (edges - (vertices-1)) / ((vertices-1) * (vertices-2)) );
		
		return connectivity;
	}


	public static int getLCOM5(ClassBean cb) {
		int numberOfMethods = cb.getMethods().size();
		int numberOfAttributes = cb.getInstanceVariables().size();
		int lcom5 = 0;
		int numberOfAttributesReferenced = 0;
		for (MethodBean method : cb.getMethods()) {
			numberOfAttributesReferenced += method.getUsedInstanceVariables().size();
		}
		if(numberOfAttributes<2)
			return 0;
		lcom5 = (numberOfMethods - (numberOfAttributesReferenced / numberOfAttributes))
				/ (numberOfAttributes - 1);
		return lcom5;
	}


	public static int getCoh(ClassBean cb) {
		int numberOfMethods = cb.getMethods().size();
		int numberOfAttributes = cb.getInstanceVariables().size();
		int coh=0;
		
		int numberOfAttributesReferenced = 0;
		
		for(MethodBean method: cb.getMethods()) {
			numberOfAttributesReferenced += method.getUsedInstanceVariables().size();
		}
		
		coh = numberOfAttributesReferenced / (numberOfMethods*numberOfAttributes); 
		
		return coh;
	}
	
	// Percentage of public methods connected
	// A method pair(m1,m2) is connected if (i) m1 and m2 reference the same attributes, or (ii) m1 and m2 do not share attributes but exist a method call between m1 and m2.
	public static int getTCC(ClassBean cb) {
		int totalPublicMethods = 0;
		int connectedPublicMethodPairs = 0;
		
		Vector<MethodBean> methods = (Vector<MethodBean>) cb.getMethods();
		for(int i=0; i<methods.size(); i++) {
			if(methods.elementAt(i).getVisibility() == 2) { // code 2 means public method
				totalPublicMethods++;
				
				for (int j=i+1; j<methods.size(); j++) {
					if(methods.elementAt(j).getVisibility() == 2) {
						
						if( shareAnInstanceVariable(methods.elementAt(i), methods.elementAt(j)) ) {
							connectedPublicMethodPairs++;
						} else {
							if(existDependencyBetween(methods.elementAt(i), methods.elementAt(j))) {
								connectedPublicMethodPairs++;
							}
						}
					}
				}	
			}		
		}
		
		return (connectedPublicMethodPairs/totalPublicMethods);
	}
	
	public static int getLCC(ClassBean cb) {
		int totalPublicMethods = 0;
		int connectedPublicMethodPairs = 0;
		
		Vector<MethodBean> methods = (Vector<MethodBean>) cb.getMethods();
		for(int i=0; i<methods.size(); i++) {
			if(methods.elementAt(i).getVisibility() == 2) { // code 2 means public method
				totalPublicMethods++;
				
				for (int j=i+1; j<methods.size(); j++) {
					if(methods.elementAt(j).getVisibility() == 2) {
						if( shareAnInstanceVariable(methods.elementAt(i), methods.elementAt(j)) ) {
							connectedPublicMethodPairs++;
						} else {
							
							if(existDependencyBetween(methods.elementAt(i), methods.elementAt(j))) {
								connectedPublicMethodPairs++;
							} else {
								
								for(MethodBean callM1: methods.elementAt(i).getMethodCalls()) {
									for(MethodBean callM2: methods.elementAt(j).getMethodCalls()) {
										if(callM1.getName().equals(callM2.getName()) && (callM1.getParameters().size() == callM2.getParameters().size()) ) {
											connectedPublicMethodPairs++;
										}
									}
								}
							}
						}
					}
				}	
			}		
		}
		
		return (connectedPublicMethodPairs/totalPublicMethods);
	}
	
	// 
	public static int getICH(ClassBean cb) {
		int invocationsInClass=0;
		int parametersInClass=0;
	
		for(MethodBean methodBean: cb.getMethods()) {
			
			for(MethodBean call: methodBean.getMethodCalls()) {
				if(call.getBelongingClass().getName().equals(methodBean.getBelongingClass().getName())) {
					invocationsInClass++;
					parametersInClass+=call.getParameters().size();
				}
			}
		}
		
		if(parametersInClass>0)
			return invocationsInClass/parametersInClass;
		else return invocationsInClass;
	}
	
	public static int getWMC(ClassBean cb){
		return cb.getMethods().size();
	}

	public static int getNOA(ClassBean cb){
		return cb.getInstanceVariables().size();
	}
	
	public static int getNOPA(ClassBean cb) {
		int publicVariable=0;

		Collection<InstanceVariableBean> variables = cb.getInstanceVariables();

		for(InstanceVariableBean variable: variables) {
			if(variable.getVisibility().equals("public"))
				publicVariable++;
		}

		return publicVariable;
	}
	
	public static int getNOPrivateA(ClassBean cb) {
		int privateVariable=0;

		Collection<InstanceVariableBean> variables = cb.getInstanceVariables();

		for(InstanceVariableBean variable: variables) {
			if(variable.getVisibility().equals("private"))
				privateVariable++;
		}

		return privateVariable;
	}

	//Number of operations added by a subclass
	public static int getNOA(ClassBean cb, Vector<ClassBean> System){

		int NOA = 0;

		for(ClassBean c: System){
			if(c.getName().equals(cb.getSuperclass())){
				Vector<MethodBean> subClassMethods = (Vector<MethodBean>) cb.getMethods();
				Vector<MethodBean> superClassMethods = (Vector<MethodBean>) c.getMethods();
				for(MethodBean m: subClassMethods){
					if(!superClassMethods.contains(m)){
						NOA++;
					}
				}
				break;
			}
		}

		return NOA;
	}


	//Number of operations overridden by a subclass
	public static int getNOO(ClassBean cb, Vector<ClassBean> System){

		int NOO = 0;

		if(cb.getSuperclass() != null){

			for(ClassBean c: System){
				if(c.getName().equals(cb.getSuperclass())){
					Vector<MethodBean> subClassMethods = (Vector<MethodBean>) cb.getMethods();
					Vector<MethodBean> superClassMethods = (Vector<MethodBean>) c.getMethods();
					for(MethodBean m: subClassMethods){
						if(superClassMethods.contains(m)){
							NOO++;
						}
					}
					break;
				}
			}
		}

		return NOO;
	}

	public static double computeMediumIntraConnectivity(PackageBean pPackage) {
		double packAllLinks = Math.pow(pPackage.getClasses().size(), 2);
		double packIntraConnectivity=0.0;

		for(ClassBean eClass: pPackage.getClasses()) {
			for(ClassBean current: pPackage.getClasses()) {
				if(eClass!=current) {
					if(existsDependence(eClass, current)) {
						packIntraConnectivity++;
					}
				}
			}
		}

		return packIntraConnectivity/packAllLinks;
	}

	public static double computeMediumInterConnectivity(PackageBean pPackage, Collection<PackageBean> pPackages) {
		double sumInterConnectivities=0.0;	

		for(ClassBean eClass: pPackage.getClasses()) {
			for(PackageBean currentPack: pPackages) {
				double packsInterConnectivity=0.0;
				double packsAllLinks = 2 * pPackage.getClasses().size() * currentPack.getClasses().size();

				if(pPackage!=currentPack) {
					for(ClassBean currentClass: currentPack.getClasses()) {
						if(existsDependence(eClass, currentClass)) {
							packsInterConnectivity++;
						}
					}
				}
				sumInterConnectivities+=((packsInterConnectivity)/packsAllLinks);
			}
		}

		return ((1.0/(pPackages.size()*(pPackages.size()-1))) * sumInterConnectivities);
	}

	public static double computeMediumIntraConnectivity(Collection<PackageBean> pClusters) { 
		double sumIntraConnectivities=0.0;	
		for(PackageBean pack: pClusters) {
			double packAllLinks = Math.pow(pack.getClasses().size(), 2);
			double packIntraConnectivity=0.0;
			for(ClassBean eClass: pack.getClasses()) {
				for(ClassBean current: pack.getClasses()) {
					if(eClass!=current) {
						if(existsDependence(eClass, current)) {
							packIntraConnectivity++;
						}
					}
				}
			}
			sumIntraConnectivities+=packIntraConnectivity/packAllLinks;
		}

		return ((1.0/pClusters.size()) * sumIntraConnectivities);
	}

	public static double computeMediumInterConnectivity(Collection<PackageBean> pClusters) {
		double sumInterConnectivities=0.0;	

		for(PackageBean pack: pClusters) {
			for(ClassBean eClass: pack.getClasses()) {
				for(PackageBean currentPack: pClusters) {
					double packsInterConnectivity=0.0;
					double packsAllLinks = 2 * pack.getClasses().size() * currentPack.getClasses().size();
					if(pack!=currentPack) {
						for(ClassBean currentClass: currentPack.getClasses()) {
							if(existsDependence(eClass, currentClass)) {
								packsInterConnectivity++;
							}
						}
					}
					sumInterConnectivities+=((packsInterConnectivity)/packsAllLinks);
				}
			}

		}

		return ((1.0/(pClusters.size()*(pClusters.size()-1))) * sumInterConnectivities);
	}


	public static int getMcCabeCycloComplexity(MethodBean mb){

		int mcCabe = 0;
		String code = mb.getTextContent();


		String regex = "return";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(code);


		if(matcher.find()){
			mcCabe++;
		}


		regex = "if";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(code);


		if(matcher.find()){
			mcCabe++;
		}

		regex = "else";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(code);


		if(matcher.find()){
			mcCabe++;
		}


		regex = "case";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(code);


		if(matcher.find()){
			mcCabe++;
		}

		regex = "for";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(code);


		if(matcher.find()){
			mcCabe++;
		}

		regex = "while";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(code);


		if(matcher.find()){
			mcCabe++;
		}

		regex = "break";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(code);


		if(matcher.find()){
			mcCabe++;
		}

		regex = "&&";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(code);


		if(matcher.find()){
			mcCabe++;
		}

		regex = "||";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(code);

		if(matcher.find()){
			mcCabe++;
		}

		regex = "catch";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(code);


		if(matcher.find()){
			mcCabe++;
		}

		regex = "throw";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(code);


		if(matcher.find()){
			mcCabe++;
		}


		return mcCabe;
	}

	private static boolean existsDependence(ClassBean pClass1, ClassBean pClass2) {
		for(MethodBean methodClass1: pClass1.getMethods()) {
			for(MethodBean call: methodClass1.getMethodCalls()) {

				for(MethodBean methodClass2: pClass2.getMethods()) {
					if(call.getName().equals(methodClass2.getName())) 
						return true;
				}
			}
		}

		for(MethodBean methodClass2: pClass2.getMethods()) {
			for(MethodBean call: methodClass2.getMethodCalls()) {

				for(MethodBean methodClass1: pClass1.getMethods()) {
					if(call.getName().equals(methodClass1.getName())) 
						return true;
				}
			}
		}

		return false;
	}
	
	private static boolean existDependencyBetween(MethodBean m1, MethodBean m2){

		for(MethodBean call: m1.getMethodCalls()) {
			if( (call.getName().equals(m2.getName())) && (call.getParameters().size() == m2.getParameters().size()) ) {
				return true;
			}
		}
		
		for(MethodBean call: m2.getMethodCalls()) {
			if( (call.getName().equals(m1.getName())) && (call.getParameters().size() == m1.getParameters().size()) ) {
				return true;
			}
		}
		
		return false;
	}

	private static boolean shareAnInstanceVariable(MethodBean m1, MethodBean m2){

		Vector<InstanceVariableBean> m1Variables = (Vector<InstanceVariableBean>) m1.getUsedInstanceVariables();
		Vector<InstanceVariableBean> m2Variables = (Vector<InstanceVariableBean>) m2.getUsedInstanceVariables();

		for(InstanceVariableBean i: m1Variables){
			if(m2Variables.contains(i)){
				return true;
			}
		}

		return false;

	}

	public static double getAssertionDensity2(MethodBean mb) {
		double assertionDensity = 0.0;
		double asserts = 0.0;
		double size = mb.getTextContent().split("\n").length;

		Pattern pattern = Pattern.compile("assert*", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(mb.getTextContent());
		boolean matchFound = matcher.find();
		while(matcher.find()) {
			asserts++;
		}

		assertionDensity = asserts / size;

		return assertionDensity;
	}

}
