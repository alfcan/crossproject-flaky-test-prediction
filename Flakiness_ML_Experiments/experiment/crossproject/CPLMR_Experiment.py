import copy
import os

import mlflow
import numpy as np
from imblearn.pipeline import Pipeline
from sklearn.ensemble import RandomForestClassifier
from sklearn.decomposition import PCA
from imblearn.under_sampling import RandomUnderSampler
from LocalModel import LocalModel
from experiment.LocalModelExperiment import LocalModelExperiment


class CPLCR_Experiment(LocalModelExperiment):

    def __init__(self,experimentName,typeExperiment):
        super().__init__(experimentName, typeExperiment)



    def runExperiment(self,X_train_set,y_train_set,X_test_set,y_test_set,mlflowExperimentName,log_mlflow=True):
        self.run(X_train_set,y_train_set,X_test_set,y_test_set)



    def run(self,X_train_set,y_train_set,X_test_set,y_test_set):

        list_repository=repository_set.unique()
        for repository in list_repository:
            X_repository=X_train_set.loc[repository_set==repository]
            y_repository=y_train_set.loc[repository_set==repository]
            pipeline=Pipeline(steps =[('pca',PCA()),
                                      ('undersample', RandomUnderSampler()),
                                      ('clf',RandomForestClassifier(class_weight='balanced', random_state=42))])
            pipeline.fit(X_repository,y_repository)

            localModel=LocalModel(repository,X_repository,y_repository,copy.copy(pipeline))
            localModel.setPerformanceLocalModel(y_repository,pipeline.predict(X_repository))
            super().addLocalModel(localModel)


            list_centroid=[]
            for localModel in self.list_localModel:
                centroid=[]
                X_repository=localModel.getX_set()
                for column in X_repository.columns:
                    centroid.append(X_repository[column].mean())
                list_centroid.append(centroid)

            y_predict=[]
            indexs_repo=[]
            for i in range(len(X_test_set)):
                X_target_instance=X_test_set.iloc[i, ].to_numpy()
                X_target_instance=X_target_instance.reshape(1,-1)
                distances = np.sqrt(np.sum((X_target_instance - list_centroid)**2, axis=1))
                index_repo=np.argmin(distances,axis=0)
                pipeline=self.list_localModel[index_repo]
                y_predict.append(pipeline.predict(X_target_instance)[0])

            super().setPerformance(y_test_set,y_predict)