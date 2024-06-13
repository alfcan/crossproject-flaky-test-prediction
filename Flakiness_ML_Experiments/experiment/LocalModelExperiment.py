import numpy as np
import pandas as pd

from experiment.GenericExperiment import GenericExperiment

class LocalModelExperiment(GenericExperiment):

    list_localModel=None

    def __init__(self,experimentName,typeExperiment):
        super().__init__(experimentName,typeExperiment)
        self.list_localModel=list()

    def addLocalModel(self,localModel): self.list_localModel.append(localModel)

    def gelListLocalModel(self): return self.list_localModel
    def getLocalModel(self, id_model):
        for localModel in self.list_localModel:
            if id_model==localModel.getId(): return localModel
        return None


    def getSummaryLocalModel(self):

        data={
            'id_model':[],
            'test_non_flaky':[],
            'test_flaky':[],
            'accuracy': [],
            'precision': [],
            'recall': [],
            'f1_score': []
        }

        for localModel in self.list_localModel:
            data['id_model'].append(localModel.getId())
            y_set=localModel.getY_set()
            tf=np.count_nonzero(y_set)
            tnf=y_set.shape[0]-np.count_nonzero(y_set)
            data['test_non_flaky'].append(tnf)
            data['test_flaky'].append(tf)
            data['accuracy'].append(localModel.getAccuracy())
            data['precision'].append(localModel.getPrecision())
            data['recall'].append(localModel.getRecall())
            data['f1_score'].append(localModel.getF1())


        df=pd.DataFrame(data)
        return df

    def toString(self):
        txt = "{} - N.LM:{} - TNF:{} - TF:{} - TP:{} - FN:{} - FP:{} - TN:{} - Pr:{} - Rec:{} - F1:{} - AUC:{} - MCC:{}".format(
            self.experimentName,
            len(self.list_localModel),
            getattr(self, 'getTestNonFlaky', lambda: 'Method not available')(),
            getattr(self, 'getTestFlaky', lambda: 'Method not available')(),
            getattr(self, 'getTP', lambda: 'Method not available')(),
            getattr(self, 'getFN', lambda: 'Method not available')(),
            getattr(self, 'getFP', lambda: 'Method not available')(),
            getattr(self, 'getTN', lambda: 'Method not available')(),
            getattr(self, 'getPrecision', lambda: 'Method not available')(),
            getattr(self, 'getRecall', lambda: 'Method not available')(),
            getattr(self, 'getF1', lambda: 'Method not available')(),
            getattr(self, 'getAUC', lambda: 'Method not available')(),
            getattr(self, 'getMCC', lambda: 'Method not available')()
        )

        return txt
