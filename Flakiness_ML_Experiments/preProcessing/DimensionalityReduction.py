from sklearn.base import BaseEstimator, TransformerMixin
from sklearn.decomposition import PCA
import matplotlib.pyplot as plt
import mlflow
import numpy as np
from preProcessing.CustomPreProcessing import CustomPreProcessing

class DimensionalityReduction(CustomPreProcessing):

    def __init__(self):
        self.pca=PCA()

    def fit(self, X, y=None):
        self.pca.fit(X)

        plt.clf()
        plt.plot(np.cumsum(self.pca.explained_variance_ratio_))
        plt.xlabel('Number of components')
        plt.ylabel('Explained variance')
        mlflow.log_figure(plt.gcf(),'PCA.png')

        somma=0
        n_component=0
        for value in self.pca.explained_variance_ratio_:
            n_component=n_component+1
            somma=somma+value
            if somma >=0.95:
                break

        mlflow.log_param("Numero Componenti PCA",n_component)
        self.pca=PCA(n_components=n_component)
        self.pca.fit(X)
        return self

    def transform(self, X, y=None):
        X=self.pca.transform(X)
        return X

    def fit_transform(self, X, y=None):
        self.fit(X)
        X=self.transform(X)
        return X