package computation;

import bean.*;

import java.util.ArrayList;
import java.util.Vector;


public class TestMutationUtilities {

	public ArrayList<ClassBean> getTestCases(Vector<PackageBean> pProject) {
		ArrayList<ClassBean> testCases = new ArrayList<ClassBean>();



		for(PackageBean packageBean: pProject) {
			for(ClassBean classBean: packageBean.getClasses()) {

				/*if(classBean.getTextContent().contains("EvoSuiteTest {")) {
					testCases.add(classBean);
				}*/

				if(classBean.getTextContent().contains(" extends TestCase")) {
					testCases.add(classBean);
				}
				else if(classBean.getTextContent().contains(" extends SpringActivitiTestCase")){
					testCases.add(classBean);
				}
				else if(classBean.getTextContent().contains(" extends PluggableActivitiTestCase")){
					testCases.add(classBean);
				}
				else if(classBean.getTextContent().contains(" extends JobExecutorTestCase")){
					testCases.add(classBean);
				}
				else if(classBean.getTextContent().contains(" extends MiniClusterTestBase")) {
					testCases.add(classBean);
				}
				else if(classBean.getTextContent().contains(" extends AbstractMergeTestBase")) {
					testCases.add(classBean);
				}
				else if(classBean.getTextContent().contains(" extends MockObjectTestCase")){
					testCases.add(classBean);
				}
				else if(classBean.getTextContent().contains(" extends HBaseClusterTestCase")) {
					testCases.add(classBean);
				}
				else if(classBean.getTextContent().contains(" extends CompatibilityTest")){
					testCases.add(classBean);
				}
				else if(classBean.getTextContent().contains(" extends MultiRegionTable")) {
					testCases.add(classBean);
				}
				else if(classBean.getTextContent().contains(" extends HBaseTestCase")) {
					testCases.add(classBean);
				}
				else if(classBean.getTextContent().contains(" extends BooleanArrayAssertBaseTest")) {
					testCases.add(classBean);
				}
				else if(classBean.getTextContent().contains(" extends FloatAssertBaseTest")) {
					testCases.add(classBean);
				}
				else if(classBean.getTextContent().contains(" extends DoubleArrayAssertBaseTest")){
					testCases.add(classBean);
				}
				else if(classBean.getTextContent().contains(" extends IntArrayAssertBaseTest")){
					testCases.add(classBean);
				}
				else if(classBean.getTextContent().contains(" extends FileAssertBaseTest")){
					testCases.add(classBean);
				}
				else if(classBean.getTextContent().contains(" extends ObjectArrayAssertBaseTest")){
					testCases.add(classBean);
				}
				else if(classBean.getTextContent().contains(" extends CharSequenceAssertBaseTest")){
					testCases.add(classBean);
				}
				else if(classBean.getTextContent().contains(" extends ShortArrayAssertBaseTest")){
					testCases.add(classBean);
				}
				else if(classBean.getTextContent().contains(" extends AbstractComparableAssertBaseTest")){
					testCases.add(classBean);
				}
				else if(classBean.getTextContent().contains(" extends ClassAssertBaseTest")){
					testCases.add(classBean);
				}
				else if(classBean.getTextContent().contains(" extends AbstractAssertBaseTest")){
					testCases.add(classBean);
				}
				else if(classBean.getTextContent().contains(" extends MapAssertBaseTest")){
					testCases.add(classBean);
				}
				else if(classBean.getTextContent().contains(" extends DoubleAssertBaseTest")){
					testCases.add(classBean);
				}
				else if(classBean.getTextContent().contains(" extends TimerEventCompatibilityTest")){
					testCases.add(classBean);
				}
				else if(classBean.getTextContent().contains("@Test")) {
					testCases.add(classBean);
				} else if(classBean.getTextContent().contains("@BeforeClass")) {
					testCases.add(classBean);
				}
				else if(classBean.getTextContent().contains("@Before")) {
					testCases.add(classBean);
				}
				else if(classBean.getName().contains("Tests")) {
					testCases.add(classBean);
				}
				else if(classBean.getName().contains("_Test")) {
					testCases.add(classBean);
				}
				else if(classBean.getName().contains("test")) {
					testCases.add(classBean);
				}

				else if(classBean.getName().contains("_test")) {
					testCases.add(classBean);
				}
				else if(classBean.getName().contains("SetterTesterTest")) {
					testCases.add(classBean);
				}
				else if(classBean.getName().contains("GetterTesterTest")) {
					testCases.add(classBean);
				}

				else if(classBean.getName().contains("TaskRuntimeClaimReleaseTest")) {
					testCases.add(classBean);
				}
				else if(classBean.getName().contains("TaskRuntimeDeleteTaskTest")) {
					testCases.add(classBean);
				}
				else if(classBean.getName().contains("TaskRuntimeStandaloneTaskTest")) {
					testCases.add(classBean);
				}
				else if(classBean.getName().contains("TaskRuntimeTaskAssigneeTest")) {
					testCases.add(classBean);
				}
				else if(classBean.getName().contains("TaskRuntimeTaskForOtherTest")) {
					testCases.add(classBean);
				}
				else if(classBean.getName().contains("TaskRuntimeUnAuthorizedTest")) {
					testCases.add(classBean);
				} else if(classBean.getName().contains("TestParallelMatcher")) {
					testCases.add(classBean);
				}else if(classBean.getName().contains("TestPartitionMatcher")) {
					testCases.add(classBean);
				}
				else if(classBean.getName().equals("TestRemoteAdmin")) {
					testCases.add(classBean);
				}
				else if(classBean.getName().equals("TestStatusResource")) {
					testCases.add(classBean);
				}
				else if(classBean.getName().equals("Test00MiniCluster")) {
					testCases.add(classBean);
				}
				else if(classBean.getName().equals("TestRemoteTable")) {
					testCases.add(classBean);
				}
				else if(classBean.getName().equals("TestTableResource")) {
					testCases.add(classBean);
				}
				else if(classBean.getName().equals("PropertyUtilTest")){
					testCases.add(classBean);
				}
				else if(classBean.getName().equals("PathAssert_isNormalized_Test")) {
					testCases.add(classBean);
				}
				else if(classBean.getName().equals("CharArrayAssert_usingElementComparator_Test")) {
					testCases.add(classBean);
				}
				else if(classBean.getName().equals("PathAssert_isRegularFile_Test")){
					testCases.add(classBean);
				}
				else if(classBean.getName().equals("CharacterAssert_isGreaterThanOrEqualTo_char_Test")){
					testCases.add(classBean);
				}
				else if(classBean.getName().equals("PathAssert_isRelative_Test")){
					testCases.add(classBean);
				}
			}
		}

		/*for(ClassBean c:testCases) {
			System.out.println(c.getName());
		}*/
		return testCases;
	}
	
	public ArrayList<ClassBean> getManuallyWrittenTestCases(Vector<PackageBean> pProject) {
		ArrayList<ClassBean> testCases = new ArrayList<ClassBean>();





		for(PackageBean packageBean: pProject) {
			for(ClassBean classBean: packageBean.getClasses()) {

				if(classBean.getTextContent().contains(" extends TestCase")) {
					testCases.add(classBean);
				} else if(classBean.getTextContent().contains("@Test")) {
					testCases.add(classBean);
				} else if(classBean.getTextContent().contains("@BeforeClass")) {
					testCases.add(classBean);
				}


			}
		}

		return testCases;
	}

	public static ClassBean getProductionClassBy(String pTestSuiteName, Vector<PackageBean> pSystem) {

		for(PackageBean packageBean : pSystem) {
			for(ClassBean classBean : packageBean.getClasses()) {

				String productionClassName = pTestSuiteName.replace("EvoSuiteTest", "");

				if(productionClassName.equals(classBean.getName())) 
					return classBean;

			}
		}

		return null;
	}
	
	public static ClassBean getManuallyWrittenProductionClassBy(String pTestSuiteName, Vector<PackageBean> pSystem) {
		String testSuiteName;
		if (pTestSuiteName.contains("_Test")){
			testSuiteName = pTestSuiteName.replace("_Test", "");
			if(testSuiteName.contains("_with_string_based_date_representation")){
				testSuiteName = testSuiteName.replace("_with_string_based_date_representation", "");
			}
			if(testSuiteName.equals("Assertions_assertThat_with_Object")){
							testSuiteName = testSuiteName.replace("Assertions_assertThat_with_Object","Assertions");
						}
						if(testSuiteName.equals("IntArrayAssert_usingDefaultElementComparator")){
							testSuiteName = testSuiteName.replace("IntArrayAssert_usingDefaultElementComparator","IntArrays");
						}
						if(testSuiteName.equals("Integers_assertNotEqual")){
							testSuiteName = testSuiteName.replace("Integers_assertNotEqual","Integers");
						}
						if(testSuiteName.equals("Assertions_assertThat_with_BooleanArray")){
							testSuiteName = testSuiteName.replace("Assertions_assertThat_with_BooleanArray","AbstractBooleanArrayAssert");
						}
						if(testSuiteName.equals("PathAssert_isNormalized")){
							testSuiteName = testSuiteName.replace("PathAssert_isNormalized","AbstractPathAssert");
						}
						if(testSuiteName.equals("PathAssert_isRelative")){
							testSuiteName = testSuiteName.replace("PathAssert_isRelative","AbstractPathAssert");
						}
						if(testSuiteName.equals("CharArrayAssert_usingElementComparator")){
							testSuiteName = testSuiteName.replace("CharArrayAssert_usingElementComparator","AbstractCharArrayAssert");
						}
						if (testSuiteName.equals("Filter_being_condition")){
							testSuiteName = testSuiteName.replace("Filter_being_condition","Filters");
						}
						if(testSuiteName.equals("DateAssert_isToday")){
							testSuiteName = testSuiteName.replace("DateAssert_isToday","DateAssert");
						}
						if(testSuiteName.equals("PathAssert_isRegularFile")){
							testSuiteName = testSuiteName.replace("PathAssert_isRegularFile", "AbstractPathAssert");
						}
						if(testSuiteName.equals("Maps_newWeakHashMap")){
							testSuiteName = testSuiteName.replace("Maps_newWeakHashMap","Maps");
						}
						if(testSuiteName.equals("Objects_assertSame")){
							testSuiteName = testSuiteName.replace("Objects_assertSame","Objects");
						}
						if(testSuiteName.equals("ObjectArrayAssert_usingComparator")){
							testSuiteName = testSuiteName.replace("ObjectArrayAssert_usingComparator","AbstractObjectArrayAssert");
						}
						if(testSuiteName.equals("ShouldHaveOnlyElementsOfType_create")){
							testSuiteName = testSuiteName.replace("ShouldHaveOnlyElementsOfType_create","ShouldHaveOnlyElementsOfType");
						}
						if(testSuiteName.equals("ShortArrays_assertEmpty")){
							testSuiteName = testSuiteName.replace("ShortArrays_assertEmpty","ShortArrays");
						}
						if(testSuiteName.equals("ShouldBeInstance_create")){
							testSuiteName = testSuiteName.replace("ShouldBeInstance_create","ShouldBeInstance");
						}
						if(testSuiteName.equals("ByteArrays_assertContainsExactly")){
							testSuiteName = testSuiteName.replace("ByteArrays_assertContainsExactly","ByteArrays");
						}
						if(testSuiteName.equals("CharacterAssert_isGreaterThanOrEqualTo_char")){
							testSuiteName = testSuiteName.replace("CharacterAssert_isGreaterThanOrEqualTo_char","AbstractCharacterAssert");
						}

						if(testSuiteName.equals("ElementsShouldHaveAtLeast_create")){
							testSuiteName = testSuiteName.replace("ElementsShouldHaveAtLeast_create","ElementsShouldHaveAtLeast");
						}
						if(testSuiteName.equals("Iterables_assertEndsWith")){
							testSuiteName = testSuiteName.replace("Iterables_assertEndsWith","Iterables");
						}
						if(testSuiteName.equals("DateAssert_with_string_based_date_representation")){
							testSuiteName = testSuiteName.replace("DateAssert_with_string_based_date_representation","DateAssert");
						}
						if(testSuiteName.equals("BigDecimals_assertIsZero")){
							testSuiteName = testSuiteName.replace("BigDecimals_assertIsZero","BigDecimals");
						}
						if(testSuiteName.equals("Arrays_isArray")){
							testSuiteName = testSuiteName.replace("Arrays_isArray","Arrays");
						}
						if (testSuiteName.equals("Not_toString")){
							testSuiteName = testSuiteName.replace("Not_toString","Not");
						}
						if(testSuiteName.equals("CharArrays_assertHasSameSizeAs_with_Iterable")){
							testSuiteName = testSuiteName.replace("CharArrays_assertHasSameSizeAs_with_Iterable","CharArrays");
						}
						if(testSuiteName.equals("BigDecimals_assertIsNotNegative")){
							testSuiteName = testSuiteName.replace("BigDecimals_assertIsNotNegative","BigDecimals");
						}
						if(testSuiteName.equals("Shorts_assertNotEqual")){
							testSuiteName = testSuiteName.replace("Shorts_assertNotEqual","Shorts");
						}
						if(testSuiteName.equals("Maps_newHashMap")){
							testSuiteName = testSuiteName.replace("Maps_newHashMap","Maps");
						}
						if(testSuiteName.equals("ShouldBeEqual_assertj_elements_stack_trace_filtering")){
							testSuiteName = testSuiteName.replace("ShouldBeEqual_assertj_elements_stack_trace_filtering","StackTraceUtils");
						}
						if(testSuiteName.equals("Floats_assertIsStrictlyBetween")) {
							testSuiteName = testSuiteName.replace("Floats_assertIsStrictlyBetween","Floats");
						}
						if(testSuiteName.equals("Integers_assertIsNotZero")){
							testSuiteName = testSuiteName.replace("Integers_assertIsNotZero","Integers");
						}
						if(testSuiteName.equals("AbstractAssert_isNotIn_with_Iterable")){
							testSuiteName = testSuiteName.replace("AbstractAssert_isNotIn_with_Iterable","AbstractAssert");
						}
						if(testSuiteName.equals("IntArrayAssert_isSortedAccordingToComparator")){
							testSuiteName = testSuiteName.replace("IntArrayAssert_isSortedAccordingToComparator","AbstractIntArrayAssert");
						}
						if(testSuiteName.equals("ShouldBeBetween_create")){
							testSuiteName = testSuiteName.replace("ShouldBeBetween_create","ShouldBeBetween");
						}
						if(testSuiteName.equals("ListAssert_usingElementComparatorIgnoringFields")){
							testSuiteName = testSuiteName.replace("ListAssert_usingElementComparatorIgnoringFields","AbstractListAssert");
						}
						if(testSuiteName.equals("Bytes_assertGreaterThanOrEqualTo")){
							testSuiteName = testSuiteName.replace("Bytes_assertGreaterThanOrEqualTo","Bytes");
						}
						if(testSuiteName.equals("BooleanArrayAssert_containsExactly")){
							testSuiteName = testSuiteName.replace("BooleanArrayAssert_containsExactly","AbstractBooleanArrayAssert");
						}
						if(testSuiteName.equals("IntegerAssert_usingComparator")){
							testSuiteName = testSuiteName.replace("IntegerAssert_usingComparator","AbstractIntegerAssert");
						}
						if(testSuiteName.equals("CharSequenceAssert_hasSameSizeAs_with_Array")){
							testSuiteName = testSuiteName.replace("CharSequenceAssert_hasSameSizeAs_with_Array","AbstractCharSequenceAssert");
						}
						if(testSuiteName.equals("Lists_assertDoesNotContain")) {
							testSuiteName = testSuiteName.replace("Lists_assertDoesNotContain","Lists");
						}
						if(testSuiteName.equals("Iterables_assertContains")) {
							testSuiteName = testSuiteName.replace("Iterables_assertContains","Iterables");
						}
						if(testSuiteName.equals("AbstractEnumerableAssert_hasSameSizeAs_with_Array")){
							testSuiteName = testSuiteName.replace("AbstractEnumerableAssert_hasSameSizeAs_with_Array","AbstractEnumerableAssert");
						}
						if(testSuiteName.equals("IntArrays_assertHasSize")){
							testSuiteName = testSuiteName.replace("IntArrays_assertHasSize","IntArrays");
						}
						if(testSuiteName.equals("ObjectArrayAssert_haveAtMost")){
							testSuiteName = testSuiteName.replace("ObjectArrayAssert_haveAtMost","AbstractObjectArrayAssert");
						}
						if(testSuiteName.equals("FloatAssert_isPositive")){
							testSuiteName = testSuiteName.replace("FloatAssert_isPositive","AbstractFloatAssert");
						}
						if(testSuiteName.equals("DoubleArrayAssert_isSorted")){
							testSuiteName = testSuiteName.replace("DoubleArrayAssert_isSorted","AbstractDoubleArrayAssert");
						}
						if(testSuiteName.equals("IntArrayAssert_containsOnly")){
							testSuiteName = testSuiteName.replace("IntArrayAssert_containsOnly","AbstractIntArrayAssert");
						}
						if(testSuiteName.equals("ShouldBeNormalized_create")){
							testSuiteName = testSuiteName.replace("ShouldBeNormalized_create","ShouldBeNormalized");
						}
						if(testSuiteName.equals("ByteArrays_assertContainsSequence")){
							testSuiteName = testSuiteName.replace("ByteArrays_assertContainsSequence","ByteArrays");
						}
						if(testSuiteName.equals("ShouldBeBefore_create")){
							testSuiteName=testSuiteName.replace("ShouldBeBefore_create","ShouldBeBefore");
						}
						if(testSuiteName.equals("FloatArrays_assertIsSorted")){
							testSuiteName = testSuiteName.replace("FloatArrays_assertIsSorted","FloatArrays");
						}
						if(testSuiteName.equals("FileAssert_hasParentWithStringParameter")){
							testSuiteName = testSuiteName.replace("FileAssert_hasParentWithStringParameter","AbstractFileAssert");
						}
						if(testSuiteName.equals("ObjectArrayAssert_isNotEmpty")){
							testSuiteName = testSuiteName.replace("ObjectArrayAssert_isNotEmpty","AbstractObjectArrayAssert");
						}
						if(testSuiteName.equals("Longs_assertIsNotZero")){
							testSuiteName = testSuiteName.replace("Longs_assertIsNotZero","Longs");
						}
						if(testSuiteName.equals("Floats_assertIsCloseTo")){
							testSuiteName = testSuiteName.replace("Floats_assertIsCloseTo","Floats");
						}
						if(testSuiteName.equals("CharSequenceAssert_isEqualToIgnoringWhitespace")){
							testSuiteName = testSuiteName.replace("CharSequenceAssert_isEqualToIgnoringWhitespace","AbstractCharSequenceAssert");
						}
						if(testSuiteName.equals("CharArrays_assertContainsSequence")){
							testSuiteName = testSuiteName.replace("CharArrays_assertContainsSequence","CharArrays");
						}
						if(testSuiteName.equals("Floats_assertIsNotNegative")){
							testSuiteName = testSuiteName.replace("Floats_assertIsNotNegative","Floats");
						}
						if(testSuiteName.equals("ShortArrayAssert_containsSequence")){
							testSuiteName = testSuiteName.replace("ShortArrayAssert_containsSequence","AbstractShortArrayAssert");
						}
						if(testSuiteName.equals("Doubles_assertIsCloseTo")){
							testSuiteName = testSuiteName.replace("Doubles_assertIsCloseTo","Doubles");
						}
						if(testSuiteName.equals("LongArrays_assertContains")){
							testSuiteName = testSuiteName.replace("LongArrays_assertContains","LongArrays");
						}
						if(testSuiteName.equals("Integers_assertIsNegative")){
							testSuiteName = testSuiteName.replace("Integers_assertIsNegative","Integers");
						}
						if(testSuiteName.equals("Filter_on_differents_properties")){
							testSuiteName = testSuiteName.replace("Filter_on_differents_properties","Filters");
						}
						if(testSuiteName.equals("Dates_assertIsWithinSecond")){
							testSuiteName = testSuiteName.replace("Dates_assertIsWithinSecond","Dates");
						}
						if(testSuiteName.equals("Doubles_assertEqual_double_with_offset")){
							testSuiteName = testSuiteName.replace("Doubles_assertEqual_double_with_offset","Doubles");
						}
						if(testSuiteName.equals("FloatArrays_assertContains")){
							testSuiteName = testSuiteName.replace("FloatArrays_assertContains","FloatArrays");
						}
						if(testSuiteName.equals("DoubleAssert_usingComparator")){
							testSuiteName = testSuiteName.replace("DoubleAssert_usingComparator","AbstractDoubleAssert");
						}
						if(testSuiteName.equals("AbstractComparableAssert_canCallObjectAssertMethod")) {
							testSuiteName = testSuiteName.replace("AbstractComparableAssert_canCallObjectAssertMethod", "AbstractComparableAssert");
						}
						if(testSuiteName.equals("FloatArrays_assertEmpty")) {
							testSuiteName = testSuiteName.replace("FloatArrays_assertEmpty", "FloatArrays");
						}
						if(testSuiteName.equals("FloatArrays_assertContainsOnly")){
							testSuiteName = testSuiteName.replace("FloatArrays_assertContainsOnly","FloatArrays");
						}
						if(testSuiteName.equals("Lists_assertContains")){
							testSuiteName = testSuiteName.replace("Lists_assertContains","Lists");
						}
						if(testSuiteName.equals("BigDecimals_assertIsBetween")){
							testSuiteName = testSuiteName.replace("BigDecimals_assertIsBetween","BigDecimals");
						}
						if(testSuiteName.equals("ByteArrays_assertContains")){
							testSuiteName =testSuiteName.replace("ByteArrays_assertContains","ByteArrays");
						}
						if(testSuiteName.equals("CharArrays_assertIsSorted")){
							testSuiteName = testSuiteName.replace("CharArrays_assertIsSorted","CharArrays");
						}
						if(testSuiteName.equals("DoubleArrays_assertHasSameSizeAs_with_Iterable")){
							testSuiteName = testSuiteName.replace("DoubleArrays_assertHasSameSizeAs_with_Iterable","DoubleArrays");
						}
						if(testSuiteName.equals("Dates_assertIsEqualWithPrecision")){
							testSuiteName = testSuiteName.replace("Dates_assertIsEqualWithPrecision","Dates");
						}
						if(testSuiteName.equals("Longs_assertIsNotNegative")){
							testSuiteName = testSuiteName.replace("Longs_assertIsNotNegative","Longs");
						}
						if(testSuiteName.equals("ObjectArrayAssert_areExactly")){
							testSuiteName = testSuiteName.replace("ObjectArrayAssert_areExactly","AbstractObjectArrayAssert");
						}
						if(testSuiteName.equals("ByteArrayAssert_doesNotContain")){
							testSuiteName = testSuiteName.replace("ByteArrayAssert_doesNotContain","AbstractByteArrayAssert");
						}
						if(testSuiteName.equals("Integers_assertIsCloseTo")){
							testSuiteName = testSuiteName.replace("Integers_assertIsCloseTo","Integers");
						}
						if(testSuiteName.equals("Paths_assertHasFileName")){
							testSuiteName = testSuiteName.replace("Paths_assertHasFileName","Paths");
						}
						if(testSuiteName.equals("Strings_assertEqualsIgnoringCase")){
							testSuiteName = testSuiteName.replace("Strings_assertEqualsIgnoringCase","Strings");
						}
						if(testSuiteName.equals("Assertions_assertThat_with_primitive_long")){
							testSuiteName = testSuiteName.replace("Assertions_assertThat_with_primitive_long","Assertions");
						}
						if(testSuiteName.equals("Preconditions_checkNotNull_GenericObject")){
							testSuiteName = testSuiteName.replace("Preconditions_checkNotNull_GenericObject","Preconditions");
						}
						if(testSuiteName.equals("Longs_assertGreaterThan")){
							testSuiteName = testSuiteName.replace("Longs_assertGreaterThan","Longs");
						}
						if(testSuiteName.equals("ShouldBeAtIndex_create")){
							testSuiteName = testSuiteName.replace("ShouldBeAtIndex_create","ShouldBeAtIndex");
						}
						if(testSuiteName.equals("Throwables_assertHasCauseInstanceOf")){
							testSuiteName = testSuiteName.replace("Throwables_assertHasCauseInstanceOf","Throwables");
						}
						if(testSuiteName.equals("ClassAssert_isNotInterface")){
							testSuiteName = testSuiteName.replace("ClassAssert_isNotInterface","AbstractClassAssert");
						}
						if(testSuiteName.equals("Bytes_assertLessThanOrEqualTo")){
							testSuiteName = testSuiteName.replace("Bytes_assertLessThanOrEqualTo","Bytes");
						}
						if(testSuiteName.equals("LongArrays_assertDoesNotContain")){
							testSuiteName = testSuiteName.replace("LongArrays_assertDoesNotContain","LongArrays");
						}
						if(testSuiteName.equals("AbstractAssert_hasTheSameClassAs")){
							testSuiteName = testSuiteName.replace("AbstractAssert_hasTheSameClassAs","AbstractAssert");
						}
						if(testSuiteName.equals("StandardComparisonStrategy_isLessThan")){
							testSuiteName = testSuiteName.replace("StandardComparisonStrategy_isLessThan","StandardComparisonStrategy");
						}
						if(testSuiteName.equals("CharArrayAssert_isSortedAccordingToComparator")){
							testSuiteName = testSuiteName.replace("CharArrayAssert_isSortedAccordingToComparator","AbstractCharArrayAssert");
						}
						if(testSuiteName.equals("ShouldBeEqualWithTimePrecision_create")){
							testSuiteName = testSuiteName.replace("ShouldBeEqualWithTimePrecision_create","ShouldBeEqualWithTimePrecision");
						}
						if(testSuiteName.equals("Dates_assertIsInSameMonthAs")){
							testSuiteName = testSuiteName.replace("Dates_assertIsInSameMonthAs","Dates");
						}
						if(testSuiteName.equals("MapAssert_containsKeys")){
							testSuiteName = testSuiteName.replace("MapAssert_containsKeys","MapAssert");
						}
						if(testSuiteName.equals("DoubleAssert_isEqualTo_with_offset")){
							testSuiteName = testSuiteName.replace("DoubleAssert_isEqualTo_with_offset","DoubleAssert");
						}
						if(testSuiteName.contains("_create")){
							testSuiteName = testSuiteName.replace("_create","");
						}
						if(testSuiteName.contains("_assertEmpty")){
							testSuiteName = testSuiteName.replace("_assertEmpty","");
						}
						if(testSuiteName.contains("_assertContains")){
							testSuiteName = testSuiteName.replace("_assertContains","");
						}
						if(testSuiteName.contains("_assertEqual")){
							testSuiteName = testSuiteName.replace("_assertEqual","");
						}
						if(testSuiteName.contains("_assertLessThan")){
							testSuiteName = testSuiteName.replace("_assertLessThan","");
						}

						if(testSuiteName.contains("_matches")){
							testSuiteName = testSuiteName.replace("_matches","");
						}
						if(testSuiteName.equals("IntArrayAssert_usingDefaultComparator")){
							testSuiteName = testSuiteName.replace("IntArrayAssert_usingDefaultComparator","AbstractIntArrayAssert");
						}
						if(testSuiteName.equals("ObjectArrayAssert_flatExtracting")){
							testSuiteName = testSuiteName.replace("ObjectArrayAssert_flatExtracting","CartoonCharacter");
						}
						if(testSuiteName.equals("Characters_assertGreaterThanOrEqualTo")){
							testSuiteName = testSuiteName.replace("Characters_assertGreaterThanOrEqualTo","Characters");
						}
			if(testSuiteName.contains("_assertGreaterThan")){
				testSuiteName = testSuiteName.replace("_assertGreaterThan","");
			}

		}

		else if (pTestSuiteName.equals("Issue_717")){
			testSuiteName = pTestSuiteName.replace("Issue_717","JSON");
		}

		else if (pTestSuiteName.equals("Issue1298")){
			testSuiteName = pTestSuiteName.replace("Issue1298","JSON");
		}
		else if (pTestSuiteName.equals("Issue1679")){
			testSuiteName = pTestSuiteName.replace("Issue1679","JSON");
		}
		else if (pTestSuiteName.equals("Issue1769")){
			testSuiteName = pTestSuiteName.replace("Issue1769","JSON");
		}
		else if (pTestSuiteName.equals("Issue1977")){
			testSuiteName = pTestSuiteName.replace("Issue1977","JSON");
		}
		else if (pTestSuiteName.contains("_test")) {
			testSuiteName = pTestSuiteName.replace("_test", "");

		} else if (pTestSuiteName.contains("test")) {
			testSuiteName = pTestSuiteName.replace("test", "");
			if(testSuiteName.contains("Case")){
				testSuiteName = testSuiteName.replace("Case", "");
			}

		}
		else if (pTestSuiteName.contains("_Tests")){
			testSuiteName = pTestSuiteName.replace("_Tests", "");
			if(testSuiteName.contains("Case")){
				testSuiteName = testSuiteName.replace("Case", "");
			}

		}
		else if  (pTestSuiteName.contains("_tests")) {
			testSuiteName = pTestSuiteName.replace("_test", "");
			if(testSuiteName.contains("Case")){
				testSuiteName = testSuiteName.replace("Case", "");
			}

		}

		else if(pTestSuiteName.contains("Test")) {
		//	System.out.println(pTestSuiteName);
			testSuiteName = pTestSuiteName.replace("Test", "");
			if (testSuiteName.contains("Case")) {
				testSuiteName = testSuiteName.replace("Case", "");
				if (testSuiteName.contains("ThrottleTimeBatchWindow")) {
					testSuiteName = testSuiteName.replace("ThrottleTimeBatchWindow", "InputHandler");
				}
				if(testSuiteName.equals("StatelessActor")){
					testSuiteName = testSuiteName.replace("StatelessActor","OrbitStage");
				}
				if(testSuiteName.equals("ConnPool")){
					testSuiteName = testSuiteName.replace("ConnPool","PoolStats");
				}
				if (testSuiteName.equals("SpnegoBasicAuthentication")){
					testSuiteName = testSuiteName.replace("SpnegoBasicAuthentication","AuthenticationMechanism");
				}
				if(testSuiteName.equals("SpnegoAuthentication")){
					testSuiteName = testSuiteName.replace("SpnegoAuthentication","AuthenticationMechanism");
				}
				if(testSuiteName.equals("DigestAuthenticationAuth")){
					testSuiteName = testSuiteName.replace("DigestAuthenticationAuth","DigestAuthenticationMechanism");
				}
				if(testSuiteName.equals("DigestAuthentication2069")){
					testSuiteName = testSuiteName.replace("DigestAuthentication2069","DigestAuthenticationMechanism");
				}
				if(testSuiteName.equals("LoadBalancingProxy")){
					testSuiteName = testSuiteName.replace("LoadBalancingProxy","LoadBalancingProxyClient");
				}
				if(testSuiteName.equals("ClientEndpointReconnect")){
					testSuiteName = testSuiteName.replace("ClientEndpointReconnect","AnnotatedClientReconnectEndpoint");
				}

			}
			if(testSuiteName.equals("Date5_iso8601")){
				testSuiteName = testSuiteName.replace("Date5_iso8601","JSON");
			}
			if(testSuiteName.equals("Date4_indian")){
				testSuiteName = testSuiteName.replace("Date4_indian","JSON");
			}
			if(testSuiteName.equals("JSONField5")){
				testSuiteName = testSuiteName.replace("JSONField5","JSON");
			}
			if(testSuiteName.equals("Date_tz")){
				testSuiteName = testSuiteName.replace("Date_tz","JSONReader");
			}
			if(testSuiteName.equals("ProcedureSkipPersistence")){
				testSuiteName = testSuiteName.replace("ProcedureSkipPersistence","ProcedureStore");
			}
			if(testSuiteName.equals("JsrWebSocketServer07")){
				testSuiteName = testSuiteName.replace("JsrWebSocketServer07","JsrWebSocketServer");
			}
			if(testSuiteName.equals("ZookeeperRegistryCenterQueryWithoutCache")){
				testSuiteName = testSuiteName.replace("ZookeeperRegistryCenterQueryWithoutCache","ZookeeperRegistryCenter");
			}
			if(testSuiteName.equals("BoundaryTimerEventRepeatCompatibility")){
				testSuiteName = testSuiteName.replace("BoundaryTimerEventRepeatCompatibility","TimerJobEntity");
			}
			if(testSuiteName.equals("IntermediateTimerEvent")){
				testSuiteName = testSuiteName.replace("IntermediateTimerEvent","TimerJobEntity");
			}
			if(testSuiteName.equals("BoundaryTimerEvent")){
				testSuiteName = testSuiteName.replace("BoundaryTimerEvent","TimerJobQuery");
			}
			if(testSuiteName.equals("SubProcess")){
				testSuiteName = testSuiteName.replace("SubProcess","Task");
			}
			if(testSuiteName.equals("ProcessDefinitionSuspension")){
				testSuiteName = testSuiteName.replace("ProcessDefinitionSuspension","ProcessDefinition");
			}
			if(testSuiteName.equals("ServiceTaskSpringDelegation")){
				testSuiteName = testSuiteName.replace("ServiceTaskSpringDelegation","ProcessInstance");
			}
			if(testSuiteName.equals("ListenerFieldInjection")){
				testSuiteName = testSuiteName.replace("ListenerFieldInjection","ProcessInstance");
			}
			if(testSuiteName.equals("EventBasedGateway")){
				testSuiteName = testSuiteName.replace("EventBasedGateway","ProcessInstance");
			}
			if (testSuiteName.equals("Issue256")) {
				testSuiteName = testSuiteName.replace("Issue256", "WebSocketClient");
			}
			if (testSuiteName.equals("Issue713")) {
				testSuiteName = testSuiteName.replace("Issue713", "WebSocketClient");
			}
			if (testSuiteName.equals("Issue580")) {
				testSuiteName = testSuiteName.replace("Issue580", "WebSocketServer");
			}
			if (testSuiteName.equals("OpeningHandshakeRejection")) {
				testSuiteName = testSuiteName.replace("OpeningHandshakeRejection", "ServerSocket");
			}
			if (testSuiteName.contains("SpecializedMspcLinkedQueue")) {
				testSuiteName = testSuiteName.replace("SpecializedMspcLinkedQueue", "SpecializedMpscLinkedQueue");
			}
			if (testSuiteName.contains("DiffTimeDeadbandActivator")) {
				testSuiteName = testSuiteName.replace("DiffTimeDeadbandActivator", "TimeDifferenceMovingAverageTimeDeadbandActivator");
			}
			if (testSuiteName.contains("TaskRuntimeClaimRelease")) {
				testSuiteName = testSuiteName.replace("TaskRuntimeClaimRelease", "Task");
			}
			if (testSuiteName.contains("TaskRuntimeCompleteTask")) {
				testSuiteName = testSuiteName.replace("TaskRuntimeCompleteTask", "Task");
			}
			if (testSuiteName.contains("TaskRuntimeDeleteTask")) {
				testSuiteName = testSuiteName.replace("TaskRuntimeDeleteTask", "Task");
			}
			if (testSuiteName.contains("TaskRuntimeStandaloneTask")) {
				testSuiteName = testSuiteName.replace("TaskRuntimeStandaloneTask", "Task");
			}

			if (testSuiteName.contains("TaskRuntimeTaskAssignee")) {
				testSuiteName = testSuiteName.replace("TaskRuntimeTaskAssignee", "Task");
			}
			if (testSuiteName.contains("TaskRuntimeTaskForOther")) {
				testSuiteName = testSuiteName.replace("TaskRuntimeTaskForOther", "Task");
			}
			if (testSuiteName.contains("TaskRuntimeUnAuthorized")) {
				testSuiteName = testSuiteName.replace("TaskRuntimeUnAuthorized", "Task");
			}

			if(testSuiteName.equals("StatusResource")) {
				testSuiteName = testSuiteName.replace("StatusResource","StorageClusterStatusResource");
			}
			if(testSuiteName.equals("00MiniCluster")) {
				testSuiteName = testSuiteName.replace("00MiniCluster","HTable");
			}
			if(testSuiteName.equals("RemoteTable")) {
				testSuiteName = testSuiteName.replace("RemoteTable","RemoteHTable");
			}
			if(testSuiteName.equals("ScannersWithFilters")) {
				testSuiteName = testSuiteName.replace("ScannersWithFilters","ScannerModel");
			}
			if(testSuiteName.equals("MergeTable")) {
				testSuiteName = testSuiteName.replace("MergeTable","HMerge");
			}
			if(testSuiteName.equals("TableMapReduce")) {
				testSuiteName = testSuiteName.replace("TableMapReduce","TableMapReduceUtil");
			}

			if(testSuiteName.equals("ScannerTimeout")) {
				testSuiteName = testSuiteName.replace("ScannerTimeout","Scan");
			}
			if(testSuiteName.equals("MergeMeta")) {
				testSuiteName = testSuiteName.replace("MergeMeta","HMerge");
			}
			if(testSuiteName.equals("Scanner")) {
				testSuiteName = testSuiteName.replace("Scanner","Scan");
			}
			if(testSuiteName.equals("WideScanner")){
				testSuiteName = testSuiteName.replace("WideScanner","Scan");
			}
			if(testSuiteName.equals("LogRolling")){
				testSuiteName = testSuiteName.replace("LogRolling","HLog");
			}
			if(testSuiteName.equals("MergeTool")){
				testSuiteName=testSuiteName.replace("Tool","");
			}
			if(testSuiteName.equals("MultiParallelPut")){
				testSuiteName = testSuiteName.replace("MultiParallelPut","Put");
			}
			if(testSuiteName.equals("EmptyMetaInfo")){
				testSuiteName = testSuiteName.replace("EmptyMetaInfo","Scan");
			}
			if(testSuiteName.equals("Admin")){
				testSuiteName = testSuiteName.replace("Admin","HBaseAdmin");
			}
			if(testSuiteName.equals("TableInputFormatScan")){
				testSuiteName = testSuiteName.replace("TableInputFormatScan","HTable");
			}
			if(testSuiteName.equals("MasterTransitions")) {
				testSuiteName = testSuiteName.replace("MasterTransitions","HRegion");
			}
			if(testSuiteName.equals("ScanMultipleVersions")){
				testSuiteName = testSuiteName.replace("ScanMultipleVersions","Scan");
			}
			if(testSuiteName.equals("InfoServers")){
				testSuiteName = testSuiteName.replace("InfoServers","InfoServer");
			}
			if(testSuiteName.equals("MinimumServerCount")){
				testSuiteName = testSuiteName.replace("MinimumServerCount","HBaseAdmin");
			}

			if(testSuiteName.equals("StatelessActor")) {
				testSuiteName = testSuiteName.replace("StatelessActor","OrbitStage");
			}

			if(testSuiteName.equals("BasicMessages")){
				testSuiteName = testSuiteName.replace("BasicMessages","HttpResponse");
			}

			if(testSuiteName.equals("ChunkCoding")){
				testSuiteName = testSuiteName.replace("ChunkCoding","ChunkedInputStream");
			}
			if(testSuiteName.equals("ConnPool")){
				testSuiteName = testSuiteName.replace("ConnPool","LocalConnPool");
			}
			if(testSuiteName.equals("AsyncNHttpHandlers")){
				testSuiteName = testSuiteName.replace("AsyncNHttpHandlers","AsyncNHttpServiceHandler");
			}
			if(testSuiteName.equals("BufferingNHttpHandlers")) {
				testSuiteName = testSuiteName.replace("BufferingNHttpHandlers","RequestExecutionHandler");
			}
			if(testSuiteName.equals("ThrottlingNHttpHandlers")){
				testSuiteName = testSuiteName.replace("ThrottlingNHttpHandlers","ThrottlingHttpServiceHandler");
			}

			if(testSuiteName.equals("SMTPAppender_Green")){
				testSuiteName = testSuiteName.replace("SMTPAppender_Green","SMTPAppender");
			}
			if(testSuiteName.equals("InitializationOutput")){
				testSuiteName = testSuiteName.replace("InitializationOutput","TeeOutputStream");
			}
			if(testSuiteName.equals("DBAppenderHSQL")){
				testSuiteName = testSuiteName.replace("DBAppenderHSQL","DBAppender");
			}
			if(testSuiteName.equals("ContextListener")){
				testSuiteName = testSuiteName.replace("ContextListener","BasicContextListener");
			}
			if(testSuiteName.equals("SMTPAppender_SubethaSMTP")){
				testSuiteName = testSuiteName.replace("SMTPAppender_SubethaSMTP","SMTPAppender");
			}
			if(testSuiteName.equals("TimeBasedRolling")){
				testSuiteName = testSuiteName.replace("TimeBasedRolling","TimeBasedRollingPolicy");
			}
			if(testSuiteName.equals("ResilientOutputStream")){
				testSuiteName = testSuiteName.replace("ResilientOutputStream","ResilientFileOutputStream");
			}

			if(testSuiteName.equals("Deactivation")){
				testSuiteName = testSuiteName.replace("Deactivation","OrbitStage");
			}
			if(testSuiteName.equals("LifeCycle")){
				testSuiteName = testSuiteName.replace("LifeCycle","OrbitStage");
			}

			if(testSuiteName.equals("ResponseCache")){
				testSuiteName = testSuiteName.replace("ResponseCache","OkHttpClient");
			}
			if(testSuiteName.equals("AsyncApi")){
				testSuiteName = testSuiteName.replace("AsyncApi","MockWebServer");
			}
			if(testSuiteName.equals("HostnameVerifier")){
				testSuiteName = testSuiteName.replace("HostnameVerifier","FakeSSLSession");
			}
			if(testSuiteName.equals("MasterFaultTolerance")){
				testSuiteName = testSuiteName.replace("MasterFaultTolerance","TachyonFS");
			}
			if(testSuiteName.equals("BootStrap")) {
				testSuiteName = testSuiteName.replace("BootStrap","BSHostStatusCollector");
			}

			if(testSuiteName.equals("ApiV2System")){
				testSuiteName = testSuiteName.replace("ApiV2System","MutationResult");
			}
			if(testSuiteName.equals("CassandraAuth")){
				testSuiteName = testSuiteName.replace("CassandraAuth","ThriftCluster");
			}
			if(testSuiteName.equals("EntityManagerFactory")){
				testSuiteName = testSuiteName.replace("EntityManagerFactory","EntityManagerFactoryImpl");
			}
						/*if(testSuiteName.equals("HumanizeHelper")){
							System.out.println("sono qui");
						}*/

		}

		else if (pTestSuiteName.contains("tests")) {
			testSuiteName = pTestSuiteName.replace("tests", "");
			if(testSuiteName.contains("Case")){
				testSuiteName = testSuiteName.replace("Case", "");
			}

		}
		else if(pTestSuiteName.contains("Tests")) {
			testSuiteName = pTestSuiteName.replace("Tests", "");
						if(testSuiteName.contains("Case")){
							testSuiteName = testSuiteName.replace("Case", "");
						}
						if(testSuiteName.contains("Proxy")) {
							testSuiteName = testSuiteName.replace("Proxy","GrizzlyModProxy");
						}
						if(testSuiteName.equals("InMemoryRepository")){
							testSuiteName = testSuiteName.replace("InMemoryRepository","SimpleInMemoryRepository");
						}
						if(testSuiteName.equals("NoJar")){
							testSuiteName = testSuiteName.replace("NoJar","ProjectCreator");
						}
						if(testSuiteName.equals("MainClass")){
							testSuiteName = testSuiteName.replace("MainClass","ProjectCreator");
						}
						if(testSuiteName.equals("Flatdir")){
							testSuiteName = testSuiteName.replace("Flatdir","ProjectCreator");
						}
						if(testSuiteName.equals("WarPackaging")){
							testSuiteName = testSuiteName.replace("WarPackaging","JarFile");
						}
						if(testSuiteName.equals("Classifier")){
							testSuiteName = testSuiteName.replace("Classifier","ProjectCreator");
						}
						if(testSuiteName.equals("MultiProjectRepackaging")){
							testSuiteName = testSuiteName.replace("MultiProjectRepackaging","JarFile");
						}
						if(testSuiteName.equals("CustomVersionManagement")){
							testSuiteName = testSuiteName.replace("CustomVersionManagement","ProjectCreator");
						}
						if(testSuiteName.equals("Install")){
							testSuiteName = testSuiteName.replace("Install","ProjectCreator");
						}
						if(testSuiteName.equals("Repackaging")){
							testSuiteName = testSuiteName.replace("Repackaging","JarFile");
						}
						if(testSuiteName.equals("SpringLoaded")){
							testSuiteName = testSuiteName.replace("SpringLoaded","ProjectCreator");
						}
						if(testSuiteName.equals("TestCommandIntegration")){
							testSuiteName = testSuiteName.replace("TestCommandIntegration","CliTester");
						}
						if(testSuiteName.equals("DirectorySourcesIntegration")){
							testSuiteName = testSuiteName.replace("DirectorySourcesIntegration","CliTester");
						}
						if(testSuiteName.equals("ClassLoaderIntegration")){
							testSuiteName = testSuiteName.replace("ClassLoaderIntegration","CliTester");
						}
						if(testSuiteName.equals("CommandRunnerIntegration")){
							testSuiteName = testSuiteName.replace("CommandRunnerIntegration","CommandRunner");
						}
						if(testSuiteName.equals("GrabCommandIntegration")){
							testSuiteName = testSuiteName.replace("GrabCommandIntegration","CliTester");
						}
						if(testSuiteName.equals("ReproIntegration")){
							testSuiteName = testSuiteName.replace("ReproIntegration","CliTester");
						}
						if(testSuiteName.equals("SampleIntegration")){
							testSuiteName = testSuiteName.replace("SampleIntegration","CliTester");
						}
						if(testSuiteName.equals("SampleIntegrationParentApplication")){
							testSuiteName = testSuiteName.replace("SampleIntegrationParentApplication","SpringApplication");
						}
						if(testSuiteName.equals("ShutdownParentEndpoint")){
							testSuiteName = testSuiteName.replace("ShutdownParentEndpoint","SpringApplicationBuilder");
						}
						if(testSuiteName.equals("StarterDependenciesIntegration")){
							testSuiteName = testSuiteName.replace("StarterDependenciesIntegration","ProjectCreator");
						}

					}
		else if(pTestSuiteName.contains("_with_string_based_date_representation_Test")) {
			testSuiteName = pTestSuiteName.replace("_with_string_based_date_representation_Test", "");

		}

					else if(pTestSuiteName.contains("Standalone")) {
						testSuiteName = pTestSuiteName.replace("Standalone", "");

					}
					else if(pTestSuiteName.equals("JarCommandIT")) {
						testSuiteName = pTestSuiteName.replace("JarCommandIT", "JarCommand");

					}
					else if(pTestSuiteName.equals("ClusteredEntityIT")){
						testSuiteName = pTestSuiteName.replace("ClusteredEntityIT","ClusteredEntity");
					}

					else if(pTestSuiteName.equals("SecondaryIndexIT")){
						testSuiteName = pTestSuiteName.replace("SecondaryIndexIT","EntityWithSecondaryIndexOnEnum");
					}


					else if (pTestSuiteName.contains("CronTest")) {
						testSuiteName = pTestSuiteName.replace("CronTest","CronPattern");
					}
					else if(pTestSuiteName.contains("TestMemoryLimit")) {
						testSuiteName = pTestSuiteName.replace("TestMemoryLimit","NashornSandbox");
					}
					else if(pTestSuiteName.contains("SerializableTesterTest")) {
						testSuiteName = pTestSuiteName.replace("SerializableTesterTest","SerializableTester");
					}
					else if(pTestSuiteName.contains("SetterTesterTest")) {
						testSuiteName = pTestSuiteName.replace("SetterTesterTest","SetterTester");
					}





		else {
			testSuiteName = pTestSuiteName;
		}

		for(PackageBean packageBean : pSystem) {

			for(ClassBean classBean : packageBean.getClasses()) {
				//System.out.println(classBean.getName());
				//if (!(classBean.getName().equals(pTestSuiteName))) {
					//pTestSuiteName=pTestSuiteName.toLowerCase()



					if (classBean.getName().toLowerCase().equals(testSuiteName.toLowerCase())) {
						return classBean;
					}


				//}

			}
		}

		return null;
	}
	

	public static MethodBean getProductionMethodBy(String pTestMethodCall, Vector<PackageBean> pSystem) {

		for(PackageBean packageBean : pSystem) {
			for(ClassBean classBean : packageBean.getClasses()) {
				for(MethodBean methodBean : classBean.getMethods()) {
					if(methodBean.getName().equals(pTestMethodCall))
						return methodBean;
				}
			}
		}

		return null;
	}

}