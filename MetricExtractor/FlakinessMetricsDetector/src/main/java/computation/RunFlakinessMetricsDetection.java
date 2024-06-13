package computation;

import bean.*;
import metrics.*;

import smellDetector.*;
import testSmellDetector.*;
import org.eclipse.core.runtime.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;


public class RunFlakinessMetricsDetection {


	public boolean getMetrics(String repositoryName){

		String pathRepository = "../sharedSpace/Repository/"+repositoryName; //Da utilizzare se non si passa per docker
		//String pathRepository = "./sharedSpace/Repository/"+repositoryName;

		// Declaring Class-level test smell objects.
		AssertionRoulette assertionRoulette = new AssertionRoulette();
		MysteryGuest mysteryGuest = new MysteryGuest();
		EagerTest eagerTest = new EagerTest();
		SensitiveEquality sensitiveEquality = new SensitiveEquality();
		ResourceOptimistism resourceOptimistism = new ResourceOptimistism();
		ConditionalTestLogic conditionalTestLogic= new ConditionalTestLogic();
		FireAndForget fireAndForget = new FireAndForget();
		TestRunWar testRunWar = new TestRunWar();

		TestMutationUtilities testMutationUtilities = new TestMutationUtilities();

		ClassDataShouldBePrivateRule cdsbp = new ClassDataShouldBePrivateRule();
		ComplexClassRule complexClass = new ComplexClassRule();
		FunctionalDecompositionRule functionalDecomposition = new FunctionalDecompositionRule();
		GodClassRule godClass = new GodClassRule();
		SpaghettiCodeRule spaghettiCode = new SpaghettiCodeRule();

		File project = new File(pathRepository);
		if (!project.exists())
			return false;

		try {
			String output = "nameProject,testCase,tloc,tmcCabe,assertionDensity,assertionRoulette,mysteryGuest,eagerTest,sensitiveEquality,resourceOptimism,"+
					"conditionalTestLogic,fireAndForget,testRunWar,loc,lcom2,lcom5,cbo,wmc,rfc,mpc,halsteadVocabulary,halsteadLength,halsteadVolume,classDataShouldBePrivate,"+
					"complexClass,functionalDecomposition,godClass,spaghettiCode\n";
			String outputFile = "../sharedSpace/MetricsDetector/"+repositoryName; // For non-docker use
			//String outputFile = "./sharedSpace/MetricsDetector/"+repositoryName;

			String outputReject="nameProject,testCase\n";
			String outputProjectReject = "../sharedSpace/MetricsDetector/"+repositoryName+"_TestReject"; // For non-docker use
			//String outputProjectReject = "./sharedSpace/MetricsDetector/"+repositoryName+"_TestReject";

			// Method to convert a directory into a set of java packages.
			Vector<PackageBean> packages = FolderToJavaProjectConverter.convert(project.getAbsolutePath());
				/*for(PackageBean p:packages)
					System.out.println(p.getName());
				*///ArrayList<ClassBean> productionClasses = packages.getClasses();
			ArrayList<ClassBean> testClasses = testMutationUtilities.getTestCases(packages);

			for(ClassBean classBean: testClasses) {
				ClassBean productionClass = testMutationUtilities.getManuallyWrittenProductionClassBy(classBean.getName(), packages);



				if(productionClass==null) {
					for(MethodBean testCase: classBean.getMethods())
						outputReject += project.getName() + "," + classBean.getBelongingPackage() + "." + classBean.getName() + "." + testCase.getName()+"\n";
					continue;

				}


				//--------------------


				int isClassDataShouldBePrivate = cdsbp.isClassDataShouldBePrivate(productionClass);
				int isComplexClass = complexClass.isComplexClass(productionClass);
				int isFunctionalDecomposition = functionalDecomposition.isFunctionalDecomposition(productionClass);
				double isGodClass = godClass.isGodClass(productionClass);
				double isSpaghettiCode = spaghettiCode.isSpaghettiCode(productionClass);

				double loc = CKMetrics.getLOC(productionClass);

				double lcom2 = CKMetrics.getLCOM2(productionClass);
				double lcom5 = CKMetrics.getLCOM5(productionClass);
				//double connectivity = CKMetrics.getConnectivity(productionClass);
				//double tcc = CKMetrics.getTCC(productionClass);
				//double lcc = CKMetrics.getLCC(productionClass);

				double cbo = CKMetrics.getCBO(productionClass);

				double wmc = CKMetrics.getMcCabeMetric(productionClass);
				double rfc = CKMetrics.getRFC(productionClass);
				double mpc = CKMetrics.getMPC(productionClass);

				double halsteadVocabulary = CKMetrics.getHalsteadVocabulary(productionClass);
				double halsteadLength = CKMetrics.getHalsteadLenght(productionClass);
				double halsteadVolume = CKMetrics.getHalsteadVolume(productionClass);
				//String productionClassTxt=productionClass.getTextContent().replace("\n","\\n");

				for(MethodBean testCase: classBean.getMethods()) {

					double isAssertionRoulette = assertionRoulette.isAssertionRoulette(testCase);
					double isMysteryGuest = mysteryGuest.isMysteryGuest(testCase);

					int isEagerTest = eagerTest.isEagerTest(testCase, productionClass);

					double isSensitiveEquality = sensitiveEquality.isSensitiveEquality(testCase);
					//boolean isLazyTest = lazyTest.isLazyTest(testCase, productionClass);
					int isResourceOptimistism = resourceOptimistism.isResourceOptimistism(testCase);

					double tloc = testCase.getTextContent().split("\n").length;
					double tmcCabe = CKMetrics.getMcCabeCycloComplexity(testCase);
					double assertionDensity = CKMetrics.getAssertionDensity2(testCase);
					double isConditionalTestLogic = conditionalTestLogic.isConditionalTestLogic(testCase);
					double isFireAndForget = fireAndForget.isFireAndForget(testCase);
					double isTestRunWar = testRunWar.isTestRunWar(testCase);
					//String testMethod=testCase.getTextContent().replace("\n","\\n");



					output += project.getName()+","+classBean.getBelongingPackage() + "." + classBean.getName() + "." + testCase.getName() + ","
							+ tloc + "," + tmcCabe + "," + assertionDensity + "," + isAssertionRoulette + "," + isMysteryGuest
							+ "," + isEagerTest + "," + isSensitiveEquality +  "," + isResourceOptimistism
							+ "," + isConditionalTestLogic + "," + isFireAndForget + "," + isTestRunWar + ","
							+ loc + "," + lcom2 + "," + lcom5 + "," + cbo
							+ "," + wmc + "," + rfc + "," + mpc + "," + halsteadVocabulary + "," + halsteadLength + "," + halsteadVolume
							+ "," + isClassDataShouldBePrivate + "," + isComplexClass + "," + isFunctionalDecomposition
							+ "," + isGodClass + "," + isSpaghettiCode + "\n";
				}
			}
			FileUtility.writeFile(outputReject,outputProjectReject);
			FileUtility.writeFile(output, outputFile);
			return true;

		} catch (CoreException e) {
			e.printStackTrace();
			return false;
		}
	}

}


