import os

import numpy as np
from explainability.Explainability import Explainability
from experiment.Experiment import Experiment
import mlflow

class CPFP_Experiment(Experiment):

    repositoryTarget=None
    tf=None
    tnf=None
    explainable=None
    distribution_sorgente=None
    distribution_tf_sorgente=None
    distribution_tnf_sorgente=None
    distribution_target=None
    distribution_tf_target=None
    distribution_tnf_target=None
    similarity=None
    featureImportance=None
    interpretability=None


    def __init__(self,experimentName,typeExperiment,repositoryTarget,pipeline):
        super().__init__(experimentName,typeExperiment,pipeline)
        self.repositoryTarget=repositoryTarget
        self.explainable=Explainability()

    def setTestFlaky(self,tf): self.tf=tf
    def setTestNonFlaky(self,tnf): self.tnf=tnf
    def setDistributionSource(self,distribution): self.distribution_sorgente=distribution
    def setDistributionTFSource(self,distribution): self.distribution_tf_sorgente=distribution
    def setDistributionTNFSource(self,distribution): self.distribution_tnf_sorgente=distribution
    def setDistributionTarget(self,distribution): self.distribution_target=distribution
    def setDistributionTFTarget(self,distribution): self.distribution_tf_target=distribution
    def setDistributionTNFTarget(self,distribution): self.distribution_tnf_target=distribution
    def setSimilarity(self,text): self.similarity=text
    def setFeatureImportance(self,featureImportance): self.featureImportance=featureImportance
    def setInterpretability(self,interpretability): self.interpretability=interpretability

    def getTestFlaky(self): return self.tf
    def getTestNonFlaky(self): return self.tnf
    def getDistributionSource(self): return self.distribution_sorgente
    def getDistributionTFSource(self): return self.distribution_tf_sorgente
    def getDistributionTNFSource(self): return self.distribution_tnf_sorgente
    def getDistributionTarget(self): return self.distribution_target
    def getDistributionTFTarget(self): return self.distribution_tf_target
    def getDistributionTNFTarget(self): return self.distribution_tnf_target
    def getSimilarity(self): return self.similarity
    def getFeatureImportance(self): return self.featureImportance
    def getInterpretability(self): return self.interpretability


    def run(self,X_train_set,y_train_set,X_test_set,y_test_set):
        y_true=y_test_set.to_numpy()
        self.setTestNonFlaky(y_true.shape[0]-np.count_nonzero(y_true))
        self.setTestFlaky(np.count_nonzero(y_true))


        X_testFlaky_source=X_train_set.loc[y_train_set[y_train_set == 1].index]
        X_testNonFlaky_source=X_train_set.loc[y_train_set[y_train_set == 0].index]
        X_testFlaky_target=X_test_set.loc[y_test_set[y_test_set == 1].index]
        X_testNonFlaky_target=X_test_set.loc[y_test_set[y_test_set == 0].index]

        self.setDistributionSource(self.explainable.calculateDistribution(X_train_set))
        self.setDistributionTFSource(self.explainable.calculateDistribution(X_testFlaky_source))
        self.setDistributionTNFSource(self.explainable.calculateDistribution(X_testNonFlaky_source))
        self.setDistributionTarget(self.explainable.calculateDistribution(X_test_set))
        self.setDistributionTFTarget(self.explainable.calculateDistribution(X_testFlaky_target))
        self.setDistributionTNFTarget(self.explainable.calculateDistribution(X_testNonFlaky_target))


        txt='Similarity source-target: {}\n' \
            'Similarity TF source - TF target: {}\n' \
            'Similarity TF source - TNF target: {}\n' \
            'Similarity TNF source - TNF target: {}\n' \
            'Similarity TNF source - TF target: {}'.format(
            self.explainable.similarity(X_train_set,X_test_set),
            self.explainable.similarity(X_testFlaky_source,X_testFlaky_target),
            self.explainable.similarity(X_testFlaky_source, X_testNonFlaky_target),
            self.explainable.similarity(X_testNonFlaky_source, X_testNonFlaky_target),
            self.explainable.similarity(X_testNonFlaky_source, X_testFlaky_target)
            )
        self.setSimilarity(txt)

        super().run(X_train_set,y_train_set,X_test_set,y_test_set)

        for step in self.pipeline.steps:
            if step[0]=='clf': clf=step[1]
        try:
            self.setFeatureImportance(self.explainable.featuresImportance(clf,X_train_set.columns))
        except:
            self.setFeatureImportance(None)

        self.setInterpretability(self.explainable.modelExplainability(self.pipeline,X_train_set))



    def logMlFlow(self):

        super().logMlFlow()
        mlflow.log_param("TestFlaky",self.getTestFlaky())
        mlflow.log_param("TestNonFlaky",self.getTestNonFlaky())


        tmp=[(self.getDistributionSource(),'Distribution Source.csv'),
            (self.getDistributionTarget(), 'Distribution Target.csv'),
            (self.getDistributionTFSource(), 'Distribution Source TestFlaky.csv'),
            (self.getDistributionTNFSource(), 'Distribution Source TestNonFlaky.csv'),
            (self.getDistributionTFTarget(), 'Distribution Target TestFlaky.csv'),
            (self.getDistributionTNFTarget(), 'Distribution Target TestNonFlaky.csv')]

        for tup in tmp:
            tup[0].to_csv(tup[1],index=False)
            mlflow.log_artifact(tup[1], 'Distribution')
            os.remove(tup[1])

        mlflow.log_text(self.getSimilarity(),'Similarity.txt')


        self.featureImportance.to_csv('Feature Importance.csv',index=False)
        mlflow.log_artifact('Feature Importance.csv')
        os.remove('Feature Importance.csv')

        mlflow.log_text(self.getInterpretability(),'ModelExplainability.txt')




    def toString(self):
        txt="{} - TNF:{} - TF:{} - TP:{} - FN:{} - FP:{} - TN:{} - Pr:{} - Rec:{} - F1:{} - AUC:{} - MCC:{}".format(
            self.experimentName,
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
