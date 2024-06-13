from imblearn.pipeline import Pipeline
from sklearn.decomposition import PCA
from imblearn.under_sampling import RandomUnderSampler
from sklearn.ensemble import RandomForestClassifier

from experiment.CPFP_Experiment import CPFP_Experiment
from preProcessing.IG_SM_FS_TCA import IG_SM_FS_TCA
from preProcessing.TCA_Custom import TCA_Custom


class CPCTCA_Experiment(CPFP_Experiment):

    preProcessing=None


    def __init__(self,experimentName,typeExperiment,repositoryTarget):
        self.preProcessing=[IG_SM_FS_TCA(),TCA_Custom()]
        pipeline = Pipeline(steps =[('pca', PCA()),
                                    ('undersample', RandomUnderSampler()),
                                    ('clf',RandomForestClassifier(class_weight='balanced', random_state=42))])
        super().__init__(experimentName,typeExperiment,repositoryTarget,pipeline)


    def run(self,X_train_set,y_train_set,X_test_set,y_test_set):

        X_train_set_reduce=self.preProcessing[0].fit_transform(X_train_set,X_test_set,y_train_set)
        X_test_set_reduce=self.preProcessing[0].transform(X_test_set)
        X_tca_set, y_tca_set=self.preProcessing[1].fit_transform(X_train_set_reduce,X_test_set_reduce,y_train_set)
        X_target_tca_set=self.preProcessing[1].transform(X_test_set_reduce)

        super().run(X_tca_set,y_tca_set,X_target_tca_set, y_test_set)
