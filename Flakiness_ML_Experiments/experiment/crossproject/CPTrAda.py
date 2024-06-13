from adapt.instance_based import TrAdaBoost
from imblearn.pipeline import Pipeline
from sklearn.decomposition import PCA
from imblearn.under_sampling import RandomUnderSampler
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split

from experiment.CPFP_Experiment import CPFP_Experiment


class CPTrAda(CPFP_Experiment):

    preProcessing=None
    percTargetLabel=None

    def __init__(self,experimentName,typeExperiment,repositoryTarget,percTargetLabel):
        self.pipeline = Pipeline(steps = [('pca', PCA()),
                                     ('undersample', RandomUnderSampler()),
                                     ('clf',RandomForestClassifier(class_weight='balanced', random_state=42))])
        self.percTargetLabel=percTargetLabel
        super().__init__(experimentName,typeExperiment,repositoryTarget,self.pipeline)


    def setPercTargetLabel(self, percTargetLabet): self.percTargetLabel=percTargetLabet
    def getPercTargetLabel(self): return self.percTargetLabel

    def run(self,X_train_set,y_train_set,X_test_set,y_test_set):


        X_target_set, X_test_set, y_target_set, y_test_set=train_test_split(X_test_set,
                                                                              y_test_set,
                                                                              stratify=y_test_set,
                                                                              test_size=0.25,
                                                                              random_state=42)

        target_size= (self.percTargetLabel/0.75)*1
        if target_size!=1:
            X_target_L,_,y_target_L,_=train_test_split(X_target_set,
                                                       y_target_set,
                                                       stratify=y_target_set,
                                                       test_size=1-target_size,
                                                       random_state=42)
        else:
            X_target_L=X_target_set
            y_target_L=y_target_set

        self.setPipeline(Pipeline(steps = [('clf', TrAdaBoost(self.pipeline, n_estimators=10, Xt=X_target_L, yt=y_target_L, random_state=42))]))
        super().run(X_train_set,y_train_set,X_test_set,y_test_set)

    def toString(self):
        txt="{}_{} - TNF:{} - TF:{} - TP:{} - FN:{} - FP:{} - TN:{} - Pr:{} - Rec:{} - F1:{} - AUC:{} - MCC:{}".format(
            self.experimentName,
            int(self.percTargetLabel*100),
            self.getTestNonFlaky(),
            self.getTestFlaky(),
            self.getTP(),
            self.getFN(),
            self.getFP(),
            self.getTN(),
            self.getPrecision(),
            self.getRecall(),
            self.getF1(),
            self.getAUC(),
            self.getMCC())

        return txt


