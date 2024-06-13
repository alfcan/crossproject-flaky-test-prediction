from experiment.ClassicExperiment import ClassicExperiment
from sklearn.metrics import accuracy_score, roc_auc_score, confusion_matrix, matthews_corrcoef
from sklearn.metrics import f1_score
from sklearn.metrics import precision_score
from sklearn.metrics import recall_score
import mlflow


class Experiment(ClassicExperiment):

    pipeline=None

    def __init__(self,experimentName, typeExperiment, pipeline):
        super().__init__(experimentName,typeExperiment)
        self.pipeline=pipeline


    def getPipeline(self): return self.pipeline
    def setPipeline(self,pipeline): self.pipeline=pipeline

    def runExperiment(self,X_train_set,y_train_set,X_test_set,y_test_set,mlflowExperimentName,log_mlflow=True):

        if log_mlflow:
            experiment_ID=None
            experiment =mlflow.get_experiment_by_name(mlflowExperimentName)
            if not experiment:
                experiment_ID=mlflow.create_experiment(mlflowExperimentName)
            else:
                experiment_ID=experiment.experiment_id

        if log_mlflow:
            with mlflow.start_run(run_name=self.experimentName, experiment_id=experiment_ID) as run:
                self.run(X_train_set,y_train_set,X_test_set,y_test_set)
                self.logMlFlow()
                mlflow.end_run()
        else:
            self.run(X_train_set,y_train_set,X_test_set,y_test_set)

    def run(self,X_train_set,y_train_set,X_test_set,y_test_set):
        unique_classes = y_train_set.nunique()
        if unique_classes < 2:
            raise ValueError(f"The target 'y_train_set' needs to have more than 1 class. Got {unique_classes} class instead")
        self.pipeline.fit(X=X_train_set, y=y_train_set)
        y_predict=self.pipeline.predict(X=X_test_set)
        super().setPerformance(y_test_set,y_predict)
    

    def logMlFlow(self):

        mlflow.set_tag("ExperimentType",self.getTypeExperiment())

        mlflow.log_metric("TP_Train",self.getTP_Train())
        mlflow.log_metric("FP_Train",self.getFP_Train())
        mlflow.log_metric("TN_Train",self.getTN_Train())
        mlflow.log_metric("FN_Train",self.getFN_Train())
        mlflow.log_metric("ACC_Train",self.getAccuracyTrain())
        mlflow.log_metric("PRE_Train",self.getPrecisionTrain())
        mlflow.log_metric("REC_Train",self.getRecallTrain())
        mlflow.log_metric("F1_Train",self.getF1Train())
        mlflow.log_metric("AUC_Train",self.getAUCTrain())
        mlflow.log_metric("MCC_Train",self.getMCC_Train())

        mlflow.log_metric("TP",self.getTP())
        mlflow.log_metric("FP",self.getFP())
        mlflow.log_metric("TN",self.getTN())
        mlflow.log_metric("FN",self.getFN())
        mlflow.log_metric("ACC",self.accuracy)
        mlflow.log_metric("PRE",self.precision)
        mlflow.log_metric("REC",self.recall)
        mlflow.log_metric("F1",self.f1)
        mlflow.log_metric("AUC",self.getAUC())
        mlflow.log_metric("MCC",self.getMCC())

        mlflow.sklearn.log_model(
            self.pipeline, self.experimentName, pyfunc_predict_fn="predict"
        )

        for step in self.pipeline.steps:
            if step[0]=='clf':
                mlflow.log_params(step[1].get_params())




    def toString(self):
        str_pipeline=""
        str_pipeline_param=""
        for step in self.pipeline.steps:
            if step[0]!='clf':
                str_pipeline=str_pipeline+step[0]+"_"
            else:
                str_pipeline=str_pipeline+step[1].__class__.__name__
                str_pipeline_param=str_pipeline_param+str(step[1].get_params())

        txt='Pipeline: {} - Param: {}\n'.format(str_pipeline, str_pipeline_param)
        return super().toString()+txt

