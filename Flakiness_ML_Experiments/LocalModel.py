from sklearn.metrics import accuracy_score, precision_score, recall_score, f1_score


class LocalModel:

    id_localModel=None
    X_set=None
    y_set=None
    pipeline=None

    accuracy=None
    precision=None
    recall=None
    f1=None

    def __init__(self,id_localModel,X_set,y_set,pipeline):
        self.id_localModel=id_localModel
        self.X_set=X_set
        self.y_set=y_set
        self.pipeline=pipeline


    def setId(self,id): self.id_localModel=id
    def setX_set(self,set): self.X_set=set
    def setY_set(self,set): self.y_set=set
    def setPipeline(self,pipeline): self.pipeline=pipeline
    def setAccuracy(self, acc): self.accuracy=acc
    def setPrecision(self, pre): self.precision=pre
    def setRecall(self, rec): self.recall=rec
    def setF1(self, f1): self.f1=f1


    def getId(self): return self.id_localModel
    def getX_set(self): return self.X_set
    def getY_set(self): return self.y_set
    def getPipeline(self): return self.pipeline
    def getAccuracy(self): return self.accuracy
    def getPrecision(self): return self.precision
    def getRecall(self): return self.recall
    def getF1(self): return self.f1


    def setPerformanceLocalModel(self,y_true, y_predict):
        self.setAccuracy(accuracy_score(y_true,y_predict))
        self.setPrecision(precision_score(y_true,y_predict))
        self.setRecall(recall_score(y_true,y_predict))
        self.setF1(f1_score(y_true,y_predict))
