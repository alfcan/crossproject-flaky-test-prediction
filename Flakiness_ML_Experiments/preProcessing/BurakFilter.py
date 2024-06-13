import numpy as np
import pandas as pd
from sklearn.neighbors import KNeighborsClassifier
from sklearn.preprocessing import StandardScaler
from preProcessing.CustomPreProcessing import CustomPreProcessing


class BurakFilter(CustomPreProcessing):

    def __init__(self):
        self.knn=KNeighborsClassifier()
        self.std=StandardScaler()

    def fit(self, X_source, y_source, X_target, k, y_target=None):

        columnsName=X_source.columns
        seriesName=y_source.name

        X_source_np=X_source.to_numpy()
        y_source_np=y_source.to_numpy()
        X_target_np=X_target.to_numpy()
        y_target_np=y_target.to_numpy()

        Xdata=[]
        ydata=[]
        self.knn.fit(X_source_np,y_source_np)
        for instance, l in zip(X_target_np,y_target_np):
            neighbors_index=self.knn.kneighbors(instance.reshape(1,-1), k, return_distance=False)
            for neighbor_index in neighbors_index[0]:
                if not list(X_source_np[neighbor_index]) in Xdata:
                    Xdata.append(list(X_source_np[neighbor_index]))
                    ydata.append(y_source_np[neighbor_index])

        Xdata=np.asanyarray(Xdata)
        ydata=np.asarray(ydata)
        Xdata_std=self.std.fit_transform(Xdata)
        Xdata_std=pd.DataFrame(Xdata_std,columns=columnsName)
        ydata=pd.Series(ydata,name=seriesName)
        return Xdata_std,ydata

    def transform(self, X_target, y=None):
        columnsName=X_target.columns
        X_target_std=self.std.transform(X_target)
        return pd.DataFrame(X_target_std,columns=columnsName)

    def fit_transform(self, X_source, y_source, X_target, k, y_target=None):
        return self.fit(X_source, y_source, X_target, k, y_target)