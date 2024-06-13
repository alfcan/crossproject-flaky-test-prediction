import copy
import os

import mlflow
import pandas as pd
from sklearn.cluster import KMeans
from imblearn.pipeline import Pipeline
from sklearn.decomposition import PCA
from imblearn.under_sampling import RandomUnderSampler
from sklearn.ensemble import RandomForestClassifier
from sklearn.preprocessing import StandardScaler
from yellowbrick.cluster import KElbowVisualizer
from LocalModel import LocalModel
from experiment.LocalModelExperiment import LocalModelExperiment


class CPLCM_Experiment(LocalModelExperiment):


    def __init__(self,experimentName,typeExperiment):
        super().__init__(experimentName, typeExperiment)



    def runExperiment(self,X_train_set,y_train_set,X_test_set,y_test_set,mlflowExperimentName,log_mlflow=True):

        if log_mlflow:
            experiment_ID=None
            experiment =mlflow.get_experiment_by_name(mlflowExperimentName)
            if not experiment:
                experiment_ID=mlflow.create_experiment(mlflowExperimentName)
            else:
                experiment_ID=experiment.experiment_id

        if log_mlflow:
            with mlflow.start_run(run_name=self.experimentName, experiment_id=experiment_ID) as run:
                self.run(X_train_set,y_train_set,X_test_set,y_test_set)
                self.logMlFlow()
                mlflow.end_run()
        else:
            self.run(X_train_set,y_train_set,X_test_set,y_test_set)




    def run(self,X_train_set,y_train_set,X_test_set,y_test_set):
        local_pip = Pipeline([
            ('pca', PCA()),
            #('undersample', RandomUnderSampler()),
            ('clf', RandomForestClassifier(class_weight='balanced', random_state=42))
        ])
        columns_name=X_train_set.columns
        X_train_set=pd.DataFrame(X_train_set,columns=columns_name)
        X_test_set=pd.DataFrame(X_test_set,columns=columns_name)
        
        km=KMeans(random_state=42, n_init='auto')
        elbow=KElbowVisualizer(km, k=(2,20))
        elbow.fit(X=X_train_set)
        ideal_cluster=elbow.elbow_value_

        km=KMeans(random_state=42, n_clusters=ideal_cluster, n_init='auto')
        km.fit(X=X_train_set)
        X_train_set['cluster']=km.predict(X=X_train_set)


        for i in range(0,ideal_cluster):
            X_cluster_set=X_train_set.loc[X_train_set['cluster']==i]
            X_cluster_set=X_cluster_set.drop(['cluster'],axis=1)
            indices=X_train_set.index[X_train_set['cluster'] == i].tolist()
            y_cluster_set=y_train_set.iloc[indices]
            
            local_pip.fit(X_cluster_set,y_cluster_set)

            localModel=LocalModel(i,X_cluster_set,y_cluster_set,copy.copy(local_pip))
            localModel.setPerformanceLocalModel(y_cluster_set,local_pip.predict(X_cluster_set))
            super().addLocalModel(localModel)


        y_predict=[]
        for i in range(len(X_test_set)):
            X_target_instance=X_test_set.iloc[i, ].to_numpy()
            X_target_instance=X_target_instance.reshape(1,-1)
            index_cluster=km.predict(X=X_target_instance)[0]
            local_pip=super().getLocalModel(index_cluster).getPipeline()
            y_predict.append(local_pip.predict(X_target_instance)[0])

        super().setPerformance(y_test_set,y_predict)


    def toString(self):
        return super().toString()


