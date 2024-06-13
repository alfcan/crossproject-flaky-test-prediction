from sklearn.ensemble import RandomForestClassifier
from sklearn.preprocessing import StandardScaler
import pandas as pd
from preProcessing.CustomPreProcessing import CustomPreProcessing
from imblearn.under_sampling import NearMiss
from adapt.feature_based import TCA

class IG_SM_FS_TCA(CustomPreProcessing):


    def __init__(self,perc_featureToSelect=0.5):
        self.perc_featureToSelect=int(perc_featureToSelect*100)
        self.clf=RandomForestClassifier(n_estimators=200,random_state=42,n_jobs=1)


    def fit(self, X_source, X_target, y_source=None):

       self.clf.fit(X_source,y_source)
       importanceFeatures=self.clf.feature_importances_
       featureSimilarity=self.__feature_similarity(X_source,X_target)

       alpha=0.5
       feature_weight=[]
       for i in range(0,len(X_source.columns)):
           weight=alpha * importanceFeatures[i] + (1-alpha) * featureSimilarity[i]
           feature_weight.append(weight)

       num_feature_to_select=int(len(X_source.columns) * (self.perc_featureToSelect/100))

       liste_combinate = list(zip(feature_weight, X_source.columns))
       liste_combinate_ordinate = sorted(liste_combinate, key=lambda x: x[0], reverse=True)
       # Split sorted lists
       tmp_weight, tmp_col = zip(*liste_combinate_ordinate)

       self.feature=list(tmp_col[0:num_feature_to_select])

    def transform(self, X, y=None):
        return X[self.feature]

    def fit_transform(self, X_source, X_target, y_source=None, **fit_params):
        self.fit(X_source, X_target, y_source)
        return self.transform(X_source)


    def __feature_similarity(self,source,target):
        fs=[]
        tmp1_source=[]
        tmp2_target=[]
        for column in source.columns:
            tmpS=[]
            tmpT=[]

            # Min
            tmpS.append(source[column].min())
            tmpT.append(target[column].min())
            # Max
            tmpS.append(source[column].max())
            tmpT.append(target[column].max())
            # Range
            tmpS.append(source[column].max() - source[column].min())
            tmpT.append(target[column].max() - target[column].min())
            # Inter-quantile range
            quantileS=source[column].quantile([0.25, 0.75])
            tmpS.append(quantileS[0.75] - quantileS[0.25])
            quantileT=source[column].quantile([0.25, 0.75])
            tmpT.append(quantileT[0.75] - quantileT[0.25])
            # mean
            tmpS.append(source[column].mean())
            tmpT.append(target[column].mean())
            # median
            tmpS.append(source[column].median())
            tmpT.append(target[column].median())
            # variance
            tmpS.append(source[column].var())
            tmpT.append(target[column].var())
            # standard deivation
            tmpS.append(source[column].std())
            tmpT.append(target[column].std())
            # skewness
            tmpS.append(source[column].skew())
            tmpT.append(target[column].skew())
            # kurtosis
            tmpS.append(source[column].kurtosis())
            tmpT.append(target[column].kurtosis())

            tmp1_source.append(tmpS)
            tmp2_target.append(tmpT)


        for i in range(0,len(source.columns)):
            tot=0
            for j in range(0, len(tmp1_source[0])):
                if tmp1_source[i][j]==0 and tmp2_target[i][j]==0:
                    m=1
                else:
                    if tmp1_source[i][j]<=tmp2_target[i][j]:
                        m=tmp1_source[i][j]/tmp2_target[i][j]
                    else:
                        m=tmp2_target[i][j]/tmp1_source[i][j]
                tot=tot+m

            fs.append(tot/len(tmp1_source[0]))

        return fs
