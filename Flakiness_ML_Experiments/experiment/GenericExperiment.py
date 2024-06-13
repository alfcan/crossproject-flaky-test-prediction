from sklearn.metrics import accuracy_score, precision_score, recall_score, f1_score, roc_auc_score, confusion_matrix, \
    matthews_corrcoef

class GenericExperiment:

    experimentName=None
    typeExperiment=None

    accuracy=-1
    precision=-1
    recall=-1
    f1=-1
    truePositiva=-1
    falsePositive=-1
    trueNegative=-1
    falseNegative=-1
    auc=-1
    mcc=-2

    def __init__(self,experimentName,typeExperiment):
        self.experimentName=experimentName
        self.typeExperiment=typeExperiment


    def getExperimentName(self): return self.experimentName
    def getTypeExperiment(self): return self.typeExperiment
    def getAccuracy(self): return self.accuracy
    def getPrecision(self): return self.precision
    def getRecall(self): return self.recall
    def getF1(self): return self.f1
    def getAUC(self): return self.auc
    def getTP(self): return self.truePositiva
    def getFP(self): return self.falsePositive
    def getTN(self): return self.trueNegative
    def getFN(self): return self.falseNegative
    def getMCC(self): return self.mcc


    def setExperimentName(self, experimentName): self.experimentName=experimentName
    def setTypeExperiment(self, typeExperiment): self.typeExperiment=typeExperiment
    def setAccuracy(self,accuracy): self.accuracy=accuracy
    def setPrecision(self,precision): self.precision=precision
    def setRecall(self,recall): self.recall=recall
    def setF1(self,f1): self.f1=f1
    def setAUC(self, auc): self.auc=auc
    def setTP(self, truePositive): self.truePositiva=truePositive
    def setFP(self, falsePositive): self.falsePositive=falsePositive
    def setTN(self, trueNegative): self.trueNegative=trueNegative
    def setFN(self, falseNegative): self.falseNegative=falseNegative
    def setMCC(self, mcc): self.mcc=mcc

    def setPerformance(self,y_true, y_predict):
        self.setAccuracy(accuracy_score(y_true,y_predict))
        self.setPrecision(precision_score(y_true,y_predict))
        self.setRecall(recall_score(y_true,y_predict))
        self.setF1(f1_score(y_true,y_predict))
        self.setAUC(roc_auc_score(y_true, y_predict))
        tn, fp, fn, tp = confusion_matrix(y_true, y_predict).ravel()
        self.setTP(tp)
        self.setFP(fp)
        self.setFN(fn)
        self.setTN(tn)
        self.setMCC(matthews_corrcoef(y_true, y_predict))


    def toString(self):
        txt='----Esperimento: {}----\n' \
               'Tipo Esperimento: {}\n' \
               '   TP: {}\n' \
               '   FP: {}\n' \
               '   TN: {}\n' \
               '   FN: {}\n' \
               '   Acc: {}\n' \
               '   Pre: {}\n' \
               '   Rec: {}\n' \
               '   F1: {}\n' \
               '   AUC: {}\n' \
               '   MCC: {}\n'.format(self.getExperimentName(),
                                     self.getTypeExperiment(),
                                     self.getTP(),
                                     self.getFP(),
                                     self.getTN(),
                                     self.getFN(),
                                     self.getAccuracy(),
                                     self.getPrecision(),
                                     self.getRecall(),
                                     self.getF1(),
                                     self.getAUC(),
                                     self.getMCC())
        return txt
