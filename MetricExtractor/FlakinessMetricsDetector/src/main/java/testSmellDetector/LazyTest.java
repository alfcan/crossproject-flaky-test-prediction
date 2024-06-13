package testSmellDetector;

import bean.*;

import java.util.Vector;

public class LazyTest {

	public boolean isLazyTest(ClassBean pClassBean, ClassBean pProductionClass) {
		boolean lazyTest = false;
		Vector<MethodBean> lazyTestCheck = new Vector<MethodBean>();

		for (MethodBean mb : pClassBean.getMethods()) {
			if (!mb.getName().equals(pClassBean.getName())
					&& !mb.getName().toLowerCase()
					.equals("setup")
					&& !mb.getName().toLowerCase()
					.equals("teardown") && !lazyTest) {
				Vector<MethodBean> calledMethods = (Vector<MethodBean>) mb.getMethodCalls();
				Vector<MethodBean> calledMethodsNoDuplicate = new Vector<MethodBean>();
				for (MethodBean cm : calledMethods){
					if (!calledMethodsNoDuplicate.contains(cm)){
						calledMethodsNoDuplicate.add(cm);
					}
				}


				if (calledMethodsNoDuplicate.size() > 0){
					Vector<MethodBean> cbMethods = (Vector<MethodBean>) pProductionClass.getMethods();
					for (MethodBean tmpMb : calledMethodsNoDuplicate) {
						for (MethodBean cbMethod : cbMethods){
							if (cbMethod.getName().toLowerCase().equals(tmpMb.getName().toLowerCase()) && lazyTestCheck.contains(tmpMb)) {
								lazyTest = true;
								break;
							} else if (cbMethod.getName().toLowerCase().equals(tmpMb.getName().toLowerCase()) && !lazyTestCheck.contains(tmpMb)){
								lazyTestCheck.add(tmpMb);
								break;
							}
						}
						if (lazyTest)
							break;

					}
					if (lazyTest)
						break;
				}


			}

		}

		return false;
	}

	public boolean isLazyTest(MethodBean pMethodBean, ClassBean pProductionClass) {
		boolean lazyTest = false;
		Vector<MethodBean> lazyTestCheck = new Vector<MethodBean>();

		/*if (!pMethodBean.getName().equals(pMethodBean.getBelongingClass().getName())
				&& !pMethodBean.getName().toLowerCase()
				.equals("setup")
				&& !pMethodBean.getName().toLowerCase()
				.equals("teardown")) */
		if(!pMethodBean.getName().toLowerCase()
				.equals("setup")
				&& !pMethodBean.getName().toLowerCase()
				.equals("teardown")){
			Vector<MethodBean> calledMethods = (Vector<MethodBean>) pMethodBean.getMethodCalls();
			Vector<MethodBean> calledMethodsNoDuplicate = new Vector<MethodBean>();
			for (MethodBean cm : calledMethods){
				if (!calledMethodsNoDuplicate.contains(cm)){
					calledMethodsNoDuplicate.add(cm);
				}
			}


			if (calledMethodsNoDuplicate.size() > 0){
				Vector<MethodBean> cbMethods = (Vector<MethodBean>) pProductionClass.getMethods();
				for (MethodBean tmpMb : calledMethodsNoDuplicate) {
					for (MethodBean cbMethod : cbMethods){
						if (cbMethod.getName().toLowerCase().equals(tmpMb.getName().toLowerCase()) && lazyTestCheck.contains(tmpMb)) {
							lazyTest = true;
							break;
						} else if (cbMethod.getName().toLowerCase().equals(tmpMb.getName().toLowerCase()) && !lazyTestCheck.contains(tmpMb)){
							lazyTestCheck.add(tmpMb);
							break;
						}
					}
					if (lazyTest)
						break;

				}
			}


		}
		return false;
	}

}
