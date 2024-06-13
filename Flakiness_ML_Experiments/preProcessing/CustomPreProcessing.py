from abc import ABC, abstractmethod
from sklearn.base import BaseEstimator, TransformerMixin


class CustomPreProcessing(ABC,BaseEstimator, TransformerMixin):

    @abstractmethod
    def fit(self, X, y=None):pass

    @abstractmethod
    def transform(self, X, y=None): pass

    @abstractmethod
    def fit_transform(self, X, y=None): pass
