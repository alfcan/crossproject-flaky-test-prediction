from experiment.Experiment import Experiment
import mlflow

class CrossProjectExperimentManager:

    listPipeline=None
    tuningAlgoritm=None
    bestExperiment=None
    listExperiment=None
    mlflowExperimentName=None

    def __init__(self,listPipeline,tuningAlgorithm,mlflowExperimentName):
        self.listPipeline=listPipeline
        self.tuninAlgorithm=tuningAlgorithm
        self.bestExperiment=None
        self.listExperiment=list()
        self.mlflowExperimentName=mlflowExperimentName


    def runExperiments(self,train_tuple,validation_tuple,test_tuple,print_experiment=True,log_mlflow=True):


        for pipeline in self.listPipeline:
            exp=Experiment(pipeline.getNome(),pipeline.getPipeline())
            exp.runExperiment(train_tuple[1],
                              train_tuple[2],
                              validation_tuple[1],
                              validation_tuple[2],
                              'Evaluated',
                              self.mlflowExperimentName,
                              log_mlflow)
            if print_experiment: print(exp.toString())
            self.listExperiment.append(exp)

        self.bestExperiment=self.__findBestExperiment()
        
        listTuningExperiment=self.tuninAlgorithm.runTuning(self.bestExperiment.getPipeline(),
                                                           self.bestExperiment.getExperimentName(),
                                                           train_tuple[1],
                                                           train_tuple[2],
                                                           validation_tuple[1],
                                                           validation_tuple[2],
                                                           self.mlflowExperimentName,
                                                           print_experiment,
                                                           log_mlflow)
        self.listExperiment=listTuningExperiment

        self.bestExperiment=self.__findBestExperiment()


        testExperiment=Experiment('{}_Test'.format(self.bestExperiment.getExperimentName()),
                                  self.bestExperiment.getPipeline())
        testExperiment.runExperiment(train_tuple[1],
                                     train_tuple[2],
                                     test_tuple[1],
                                     test_tuple[2],
                                     'Validation',
                                     self.mlflowExperimentName,
                                     log_mlflow)

        return testExperiment


    def getBestExperiment(self): return self.bestExperiment

    def setListPipeline(self,listPipeline): self.listPipeline=listPipeline
    def setTuning(self,tuning): self.tuninAlgorithm=tuning

    def __findBestExperiment(self):
        bestExperiment=None
        f1_score=0
        for experiment in self.listExperiment:
            if float(experiment.getF1())>=f1_score:
                bestExperiment=experiment
                f1_score=experiment.getF1()

        return bestExperiment