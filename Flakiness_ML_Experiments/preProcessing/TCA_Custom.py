from sklearn.preprocessing import StandardScaler
import pandas as pd
from preProcessing.CustomPreProcessing import CustomPreProcessing
from imblearn.under_sampling import NearMiss
from adapt.feature_based import TCA

class TCA_Custom(CustomPreProcessing):


    def __init__(self):
        self.std=StandardScaler()
        self.undersampler=NearMiss(sampling_strategy=0.3, version=3)

    def fit(self, X_source, X_target, y_source=None):

        X_scaler=self.std.fit_transform(X_source)
        X_under,y_under=self.undersampler.fit_resample(X_scaler,y_source)

        X_target_scale=self.std.transform(X_target)
        self.tca=TCA(Xt=X_target_scale,
                     n_components=len(X_source.columns),
                     kernel="rbf",random_state=42)
        X_tca=self.tca.fit_transform(X_under,X_target_scale)

        return pd.DataFrame(X_tca),pd.Series(y_under)


    def transform(self, X, y=None):
        X_scale=self.std.transform(X)
        X_tca=self.tca.transform(X_scale)
        return pd.DataFrame(X_tca)

    def fit_transform(self, X_source, X_target, y_source=None, **fit_params):
        return self.fit(X_source, X_target, y_source)

