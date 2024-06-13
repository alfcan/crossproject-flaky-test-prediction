from imblearn.over_sampling import SMOTE
from sklearn.ensemble import RandomForestClassifier
from sklearn.linear_model import LogisticRegression, Perceptron
from sklearn.neighbors import KNeighborsClassifier
from sklearn.preprocessing import StandardScaler, MinMaxScaler
from sklearn.svm import SVC
from sklearn.tree import DecisionTreeClassifier
from xgboost import XGBClassifier
from CustomPipeline import CustomPipeline
from preProcessing.DimensionalityReduction import DimensionalityReduction
from preProcessing.FeatureSelection import FeatureSelection


class PipelineManager:

    def __init__(self):
        self.listPreProcessing=[
            ('NoPreProcessing',[]),
            ('STD',[('STD',StandardScaler())]),
            ('MINMAX',[('MINMAX',MinMaxScaler())]),
            ('PCA',[('PCA',DimensionalityReduction())]),
            ('IG',[('Feature Selection',FeatureSelection())]),
            ('SMOTE',[('SMOTE',SMOTE(sampling_strategy='auto',random_state=42,k_neighbors=4))]),
            ('STD_PCA',[('STD',StandardScaler()),
                        ('PCA', DimensionalityReduction())]),
            ('STD_IG',[('STD',StandardScaler()),
                       ('Feature Selection', FeatureSelection())]),
            ('STD_SMOTE',[('STD',StandardScaler()),
                          ('SMOTE', SMOTE(sampling_strategy='auto',random_state=42,k_neighbors=4))]),
            ('STD_PCA_SMOTE',[('STD',StandardScaler()),
                              ('PCA', DimensionalityReduction()),
                              ('SMOTE', SMOTE(sampling_strategy='auto',random_state=42,k_neighbors=4))]),
            ('STD_IG_SMOTE',[('STD',StandardScaler()),
                             ('Feature Selection', FeatureSelection()),
                             ('SMOTE', SMOTE(sampling_strategy='auto',random_state=42,k_neighbors=4))]),
            ('MINMAX_PCA',[('MINMAX',MinMaxScaler()),
                        ('PCA', DimensionalityReduction())]),
            ('MINMAX_IG',[('MINMAX',MinMaxScaler()),
                       ('Feature Selection', FeatureSelection())]),
            ('MINMAX_SMOTE',[('MINMAX',MinMaxScaler()),
                          ('SMOTE', SMOTE(sampling_strategy='auto',random_state=42,k_neighbors=4))]),
            ('MINMAX_PCA_SMOTE',[('MINMAX',MinMaxScaler()),
                              ('PCA', DimensionalityReduction()),
                              ('SMOTE', SMOTE(sampling_strategy='auto',random_state=42,k_neighbors=4))]),
            ('MINMAX_IG_SMOTE',[('MINMAX',MinMaxScaler()),
                             ('Feature Selection', FeatureSelection()),
                             ('SMOTE', SMOTE(sampling_strategy='auto',random_state=42,k_neighbors=4))]),
            ('PCA_SMOTE',[('PCA', DimensionalityReduction()),
                          ('SMOTE', SMOTE(sampling_strategy='auto',random_state=42,k_neighbors=4))]),
            ('IG_SMOTE',[('Feature Selection', FeatureSelection()),
                         ('SMOTE', SMOTE(sampling_strategy='auto',random_state=42,k_neighbors=4))])
        ]

        self.listClassificatori=[
            ('KNN',KNeighborsClassifier()),
            ('SVM',SVC(kernel='rbf', class_weight='balanced', random_state=42)),
            ('LR',LogisticRegression(class_weight='balanced', random_state=42)),
            ('DT',DecisionTreeClassifier(class_weight='balanced', random_state=42)),
            ('RF',RandomForestClassifier(class_weight='balanced', random_state=42)),
            ('XGB',XGBClassifier()),
            ('P',Perceptron(class_weight='balanced', random_state=42))
        ]

        self.listPipeline=list()
        for preProcessing in self.listPreProcessing:
            for classificatore in self.listClassificatori:
                pipeline=CustomPipeline('{}_{}'.format(preProcessing[0],classificatore[0]),
                                        preProcessing[1],
                                        classificatore[1])
                self.listPipeline.append(pipeline)



    def getListPipelineName(self):
        str=""
        for pipeline in self.listPipeline:
            str=str+pipeline.getNome()+'\n'

        return str

    def getListPipeline(self,listPipelineName):
        listPipeline=list()
        for pipelineName in listPipelineName:
            for pipeline in self.listPipeline:
                if pipeline.getNome()==pipelineName: listPipeline.append(pipeline)

        return listPipeline


