from experiment.CPFP_Experiment import CPFP_Experiment
from preProcessing.BurakFilter import BurakFilter
from sklearn.ensemble import RandomForestClassifier
from imblearn.pipeline import Pipeline

class CPBF_Experiment(CPFP_Experiment):

    preProcessing=None

    def __init__(self,experimentName,typeExperiment,repositoryTarget,pipeline):
        self.preProcessing=BurakFilter()
        super().__init__(experimentName,typeExperiment,repositoryTarget,pipeline)


    def run(self,X_train_set,y_train_set,X_test_set,y_test_set):

        X_burak_set, y_burak_set=self.preProcessing.fit_transform(X_train_set,y_train_set,X_test_set,10,y_test_set)
        X_target_set=self.preProcessing.transform(X_test_set)

        super().run(X_burak_set,y_burak_set,X_target_set, y_test_set)
