package computation;

import bean.*;
import parser.*;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class FolderToJavaProjectConverter {


	public static Vector<ClassBean> extractClasses(String pPath) {
		Vector<ClassBean> system = new Vector<ClassBean>();
		Vector<PackageBean> packages = null;

		try {
			//Convert the folder in a Java Project
			packages = FolderToJavaProjectConverter.convert(pPath);
		} catch (CoreException e) {
			e.printStackTrace();
		}

		//Create vector of all the classes in the system
		for (PackageBean packageBean : packages) {
			for (ClassBean classBean : packageBean.getClasses()) {
				system.add(classBean);
			}
		}

		return system;
	}


	public static Vector<PackageBean> convert(String pPath) throws CoreException {
		File projectDirectory = new File(pPath);
		//System.exit(0);
		CodeParser codeParser = new CodeParser();
		Vector<PackageBean> packages = new Vector<PackageBean>();


		if (projectDirectory.isDirectory()) {
			for (File subDir : projectDirectory.listFiles()) {

				if (subDir.isDirectory()) {
					Vector<File> javaFiles = FolderToJavaProjectConverter.listJavaFiles(subDir);


					if (javaFiles.size() > 0) {
						for (File javaFile : javaFiles) {
							CompilationUnit parsed;
							try {
								parsed = codeParser.createParser(FileUtility.readFile(javaFile.getAbsolutePath()));

								TypeDeclaration typeDeclaration = (TypeDeclaration) parsed.types().get(0);
								Vector<String> imports = new Vector<String>();

								for (Object importedResource : parsed.imports())
									imports.add(importedResource.toString());

								if (!FolderToJavaProjectConverter.isAlreadyCreated(
										parsed.getPackage().getName().getFullyQualifiedName(), packages)) {

									PackageBean packageBean = new PackageBean();
									packageBean.setName(parsed.getPackage().getName().getFullyQualifiedName());

									ClassBean classBean = ClassParser.parse(typeDeclaration, packageBean.getName(), imports);
									classBean.setPathToClass(javaFile.getAbsolutePath());

									packageBean.addClass(classBean);
									packages.add(packageBean);
									//System.out.println(packageBean.getName());



								} else {
									PackageBean packageBean = FolderToJavaProjectConverter.getPackageByName(
											parsed.getPackage().getName().getFullyQualifiedName(), packages);

									ClassBean classBean = ClassParser.parse(typeDeclaration, packageBean.getName(), imports);
									classBean.setPathToClass(javaFile.getAbsolutePath());

									packageBean.addClass(classBean);
								}

								/*for(PackageBean p: packages)
									for(ClassBean c: p.getClasses())
									System.out.println(c.getName());
*/
							} catch (IOException e) {
								e.printStackTrace();
							} catch (NullPointerException e) {
								// do nothing
							} catch (IndexOutOfBoundsException e) {
								// do nothing
							}
						}
					}

				}

			}

		}

		for (PackageBean pb : packages) {

			String textualContent = "";
			for (ClassBean cb : pb.getClasses()) {
				textualContent += cb.getTextContent();
			}
			pb.setTextContent(textualContent);
		}

		return packages;
	}

	private static Vector<File> listJavaFiles(File pDirectory) {
		Vector<File> javaFiles = new Vector<File>();
		File[] fList = pDirectory.listFiles();

		if (fList != null) {
			for (File file : fList) {
				if (file.isFile()) {
					if (file.getName().contains(".java")) {
						javaFiles.add(file);
					}
				} else if (file.isDirectory()) {
					File directory = new File(file.getAbsolutePath());
					javaFiles.addAll(listJavaFiles(directory));
				}
			}
		}
		return javaFiles;
	}

	private static boolean isAlreadyCreated(String pPackageName, Vector<PackageBean> pPackages) {
		for (PackageBean pb : pPackages) {
			if (pb.getName().equals(pPackageName))
				return true;
		}

		return false;
	}

	private static PackageBean getPackageByName(String pPackageName, Vector<PackageBean> pPackages) {
		for (PackageBean pb : pPackages) {
			if (pb.getName().equals(pPackageName))
				return pb;
		}
		return null;
	}


}
