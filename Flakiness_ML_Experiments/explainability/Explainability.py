import pandas as pd
from sklearn.tree import export_text
import numpy as np
from scipy.spatial.distance import cosine
from sklearn.tree import DecisionTreeClassifier

class Explainability:

    def __init__(self): pass

    def calculateDistribution(self,X_set):

        data={
            'column':[],
            'Min':[],
            'Max': [],
            'Range': [],
            'Inter-Quartile Range': [],
            'Mean': [],
            'Median': [],
            'Variance': [],
            'STD': [],
            'Skewness': [],
            'Kurtosis': []
        }

        for columnName in X_set.columns:
            data['column'].append(columnName)
            data['Min'].append(X_set[columnName].min())
            data['Max'].append(X_set[columnName].max())
            data['Range'].append(X_set[columnName].max() - X_set[columnName].min())
            quantileS=X_set[columnName].quantile([0.25, 0.75])
            data['Inter-Quartile Range'].append(quantileS[0.75] - quantileS[0.25])
            data['Mean'].append(X_set[columnName].mean())
            data['Median'].append(X_set[columnName].median())
            data['Variance'].append(X_set[columnName].var())
            data['STD'].append(X_set[columnName].std())
            data['Skewness'].append(X_set[columnName].skew())
            data['Kurtosis'].append(X_set[columnName].kurtosis())

        df=pd.DataFrame(data)
        return df

    def modelExplainability(self,pipeline,X_train_set):
        y_predict=pipeline.predict(X_train_set)
        dt=DecisionTreeClassifier(class_weight='balanced',random_state=42)
        dt.fit(X_train_set,y_predict)
        explainability=export_text(dt)
        return explainability

    def featuresImportance(self,clf,features):

        data={
            'features': [],
            'importance': []
        }

        importanceFeatures=clf.feature_importances_
        indices = np.argsort(importanceFeatures)[::-1]
        for i in indices:
            data['features'].append(features[i])
            data['importance'].append(importanceFeatures[i])

        df=pd.DataFrame(data)
        return df

    def similarity(self,X_source, X_target):
        tmp=[]
        X_source_numpy=X_source.to_numpy()
        X_target_numpy=X_target.to_numpy()

        num_row_source,num_col=X_source_numpy.shape
        num_row_target,num_col=X_target_numpy.shape
        max_num_rows = max(num_row_source, num_row_target)

        if num_row_source<max_num_rows:
            padding = np.zeros((max_num_rows - num_row_source, num_col))
            X_source_numpy = np.vstack([X_source_numpy, padding])
        else:
            padding = np.zeros((max_num_rows - num_row_target, num_col))
            X_target_numpy = np.vstack([X_target_numpy, padding])

        for col in range(X_source_numpy.shape[1]):
            tmp.append(1-cosine(X_source_numpy[:,col],X_target_numpy[:,col]))
        '''
        for col in range(X_source_numpy.shape[1]):
            cosine.append(
                np.dot(X_source_numpy[:,col],X_target_numpy[:,col])/(norm(X_source_numpy[:,col])*norm(X_target_numpy[:,col]))
            )
        '''

        return np.mean(tmp)