from sklearn.metrics import accuracy_score, precision_score, recall_score, f1_score, roc_auc_score, confusion_matrix, \
    matthews_corrcoef

from experiment.GenericExperiment import GenericExperiment

class ClassicExperiment(GenericExperiment):

    accuracy_train= -1
    precision_train=-1
    recall_train=-1
    f1_train=-1
    truePositiva_train=-1
    falsePositive_train=-1
    trueNegative_train=-1
    falseNegative_train=-1
    auc_train=-1
    mcc_train=-2

    def __init__(self,experimentName, typeExperiment):
        super().__init__(experimentName, typeExperiment)


    def getAccuracyTrain(self): return self.accuracy_train
    def getPrecisionTrain(self): return self.precision_train
    def getRecallTrain(self): return self.recall_train
    def getF1Train(self): return self.f1_train
    def getAUCTrain(self): return self.auc_train
    def getTP_Train(self): return self.truePositiva_train
    def getFP_Train(self): return self.falsePositive_train
    def getTN_Train(self): return self.trueNegative_train
    def getFN_Train(self): return self.falseNegative_train
    def getMCC_Train(self): return self.mcc_train

    def setAccuracyTrain(self,accuracy_train): self.accuracy_train=accuracy_train
    def setPrecisionTrain(self,precision_train): self.precision_train=precision_train
    def setRecallTrain(self,recall_train): self.recall_train=recall_train
    def setF1Train(self,f1_train): self.f1_train=f1_train
    def setAUCTrain(self, auc_train): self.auc_train=auc_train
    def setTP_Train(self, truePositive_train): self.truePositiva_train=truePositive_train
    def setFP_Train(self, falsePositive_train): self.falsePositive_train=falsePositive_train
    def setTN_Train(self, trueNegative_train): self.trueNegative_train=trueNegative_train
    def setFN_Train(self, falseNegative_train): self.falseNegative_train=falseNegative_train
    def setMCC_Train(self, mcc): self.mcc_train=mcc

    def setPerformanceTrain(self,y_true, y_predict):
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
        txt = super().toString()
        txt =txt +'Train\n' \
               '   TP: {}\n' \
               '   FP: {}\n' \
               '   TN: {}\n' \
               '   FN: {}\n' \
               '   Acc: {}\n' \
               '   Pre: {}\n' \
               '   Rec: {}\n' \
               '   F1: {}\n' \
               '   AUC: {}\n' \
               '   MCC: {}\n'.format(self.getTP_Train(),
                                     self.getFP_Train(),
                                     self.getTN_Train(),
                                     self.getFN_Train(),
                                     self.getAccuracyTrain(),
                                     self.getPrecisionTrain(),
                                     self.getRecallTrain(),
                                     self.getF1Train(),
                                     self.getAUCTrain(),
                                     self.getMCC_Train())
        return txt
