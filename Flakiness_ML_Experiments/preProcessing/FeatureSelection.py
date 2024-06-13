from sklearn.base import BaseEstimator, TransformerMixin
from sklearn.ensemble import RandomForestClassifier
from statsmodels.stats.outliers_influence import variance_inflation_factor
import pandas as pd
import numpy as np
import copy
import mlflow
from preProcessing.CustomPreProcessing import CustomPreProcessing

class FeatureSelection(CustomPreProcessing):


    def fit(self, X, y=None):
        self.eppsilon_features=[]
        X_copy=copy.copy(X)
        if isinstance(X_copy, np.ndarray):
            X_copy = pd.DataFrame(X_copy)

        # Multicollinearity
        self.eppsilon_features=self.eppsilon_features+self.__multicollinearity_eppsilon_feature(X_copy)
        X_copy.drop(self.eppsilon_features,axis=1)
        # Information Gain
        self.eppsilon_features=self.eppsilon_features+self.__informationGain_epplsilon_feature(X_copy,y)
        mlflow.log_param("Eppsilon_features",len(self.eppsilon_features))
        return self



    def transform(self, X, y=None):
        if isinstance(X, np.ndarray):
            X = pd.DataFrame(X)
        X=X.drop(self.eppsilon_features,axis=1)
        return X

    def fit_transform(self, X, y=None, **fit_params):
        self.fit(X,y)
        X=self.transform(X)
        return X


    def __informationGain_epplsilon_feature(self, X,y):
        eppsilon_feature=[]

        randomForest=RandomForestClassifier(n_estimators=200,random_state=0,n_jobs=1)
        columns_name=X.columns
        randomForest.fit(X=X,y=y)
        importanceFeatures=randomForest.feature_importances_
        indices = np.argsort(importanceFeatures)[::-1]


        for f in range(X.shape[1]):
            if importanceFeatures[indices[f]] < 0.02:
                eppsilon_feature.append(X.columns[indices[f]])

        return eppsilon_feature


    def __multicollinearity_eppsilon_feature(self, X):
        eppsilon_features=[]

        eliminato = True
        while eliminato:
            max = 0

            vif = pd.DataFrame()
            vif["VIF Factor"] = [variance_inflation_factor(X.values, i) for i in range(X.shape[1])]
            vif["features"] = X.columns

            for vif_value, feature in zip(vif["VIF Factor"], vif["features"]):
                if vif_value >= 5:
                    if vif_value > max:
                        max = vif_value
                        feature_da_rimuovere = feature

            if max > 0:
                eliminato = True
                X = X.drop([feature_da_rimuovere], axis=1)
                eppsilon_features.append(feature_da_rimuovere)
            else:
                eliminato = False

        return eppsilon_features