import sys
import warnings

from management.DatasetManager import DatasetManager
from management.ExperimentManager import ExperimentManager
from imblearn.pipeline import Pipeline
from sklearn.decomposition import PCA
from imblearn.under_sampling import RandomUnderSampler
from sklearn.ensemble import RandomForestClassifier
from experiment.crossproject.CPBF_Experiment import CPBF_Experiment
from experiment.crossproject.CPLMC_Experiment import CPLCM_Experiment
from experiment.crossproject.CPLMR_Experiment import CPLCR_Experiment
from experiment.crossproject.CPTCA import CPTCA_Experiment
from experiment.crossproject.CPTrAda import CPTrAda
from experiment.crossproject.CPCTCA import CPCTCA_Experiment

import pandas as pd
import os
import seaborn as sns
import matplotlib.pyplot as plt
from threading import Thread
import time

class TimeoutException(Exception):
    pass

def run_with_timeout(timeout, func, *args, **kwargs):
    class InterruptibleThread(Thread):
        def __init__(self):
            Thread.__init__(self)
            self.result = None
            self.exception = None

        def run(self):
            try:
                self.result = func(*args, **kwargs)
            except Exception as e:
                self.exception = e

    it = InterruptibleThread()
    it.start()
    it.join(timeout)
    if it.is_alive():
        raise TimeoutException(f"Experiment took longer than {timeout} seconds and was terminated.")
    if it.exception:
        raise it.exception
    return it.result

def create_dataframe_result(experiment_results, filename='experiment_results.csv'):
    columns = ['Experiment', 'Repository', 'TN', 'TP', 'FN', 'FP' 'Precision', 'Recall', 'F1', 'AUC', 'MCC']

    if os.path.exists(filename):
        results_df = pd.read_csv(filename)
    else:
        results_df = pd.DataFrame(columns=columns)

    df_temp = pd.DataFrame(experiment_results, columns=columns)

    for results in experiment_results:
        results_df = pd.concat([results_df, df_temp], ignore_index=True)

    results_df.to_csv(filename, index=False)

    print(f"Results saved to {filename}")

def boxplot_results():
    file_path = 'experiment_results.csv'
    # Load the CSV file
    results_df = pd.read_csv(file_path)
     
    # Convert the dataframe to long format
    results_long = pd.melt(results_df, 
                           id_vars=['Experiment', 'Repository'], 
                           value_vars=['Precision', 'Recall', 'F1', 'AUC'],
                           var_name='Metric', value_name='Score')
    
    # Set the style of the plot
    sns.set(style="whitegrid")

    # Create the boxplot
    plt.figure(figsize=(15, 10))
    ax = sns.boxplot(x='Metric', y='Score', hue='Experiment', data=results_long, palette='Set2')
    
    # Add vertical lines to separate different metrics
    num_metrics = results_long['Metric'].nunique()
    for i in range(1, num_metrics):
        plt.axvline(i - 0.5, color='grey', linestyle='--', linewidth=1)

    # Set the title and labels
    plt.title('Performance Metrics by Experiment', fontsize=20)
    plt.xlabel('Metric', fontsize=16)
    plt.ylabel('Score', fontsize=16)
    
    # Customize the legend
    plt.legend(title='Experiment', loc='lower right', fontsize=12, title_fontsize=14)
    
    # Customize tick labels
    ax.set_xticklabels(ax.get_xticklabels(), fontsize=14)
    ax.set_yticklabels(ax.get_yticklabels(), fontsize=14)
    
    # Save the plot to a file
    plt.savefig('performance_metrics_boxplot.png')

    # Show the plot
    plt.show()

def run_and_evaluate_experiment(dataset_manager, experiment, mlflow_experiment_name):    
    list_repository = dataset_manager.getListRepositoryName()
    for repo in list_repository:
        experiment_results = []
        print("Running Experiment: {} - Validation Repository: {}".format(experimentName, repo))
        datasetManager.dataset_split_by_repo(repo)
        try:
            timeout = 1800  # One hour timeout

            def experiment_runner():
                experiment.runExperiment(
                    datasetManager.getTrainSet()[1],
                    datasetManager.getTrainSet()[2],
                    datasetManager.getValidationSet()[1],
                    datasetManager.getValidationSet()[2],
                    mlflowExperimentName,
                    False
                )

            run_with_timeout(timeout, experiment_runner)

            experiment_results.append({
                'Experiment': experimentName,
                'Repository': repo,
                'TN': experiment.getTN(),
                'TP': experiment.getTP(),
                'FN': experiment.getFN(),
                'FP': experiment.getFP(),
                'Precision': experiment.getPrecision(),
                'Recall': experiment.getRecall(),
                'F1': experiment.getF1(),
                'AUC': experiment.getAUC(),
                'MCC': experiment.getMCC()
            })
            print(experiment.toString())

            create_dataframe_result(experiment_results)

        except TimeoutException as e:
            print(f"Experiment skipped due to timeout: {e}")
        except ValueError as e:
            print(f"Experiment skipped: {e}")


if __name__ == "__main__":
    warnings.filterwarnings("ignore")
    datasetName=sys.argv[1]
    
    if os.path.exists('experiment_results.csv'):
        os.remove('experiment_results.csv')

    type_experiment=['CPBF_Experiment',
                     # 'CPLMC_Experiment',
                     # 'CPLMR_Experiment',
                     'CPTCA_Experiment',
                     'CPCTCA_Experiment',
                     'CPTrAda_Experiment']
    for experiment in type_experiment:
        print(experiment)

    selected_experiments=list()
    
    choice=""
    while (choice!='0') and (choice!='1'):
        choice=input("Specify the experiment to run (0 to terminate, 1 to run all): ")
        if choice!='0' and choice!='1':
            selected_experiments.append(choice)
        if choice=='1':
            selected_experiments=type_experiment
    
    if len(selected_experiments)>0:
        mlflowExperimentName='{}_Flakiness'.format(datasetName)
        datasetManager=DatasetManager(datasetName)
        pipeline = Pipeline([('pca', PCA()), ('undersample', RandomUnderSampler()), ('clf', RandomForestClassifier())])

        for experimentName in selected_experiments:
            if experimentName=='CPBF_Experiment':
                experiment=CPBF_Experiment("CBPF_Experiment", None, None, pipeline)
            # if experimentName=='CPLMC_Experiment':
            #     experiment=CPLCM_Experiment("CPLMC_Experiment", None)
            # if experimentName=='CPLMR_Experiment':
            #      experiment=CPLCR_Experiment("CPLMR_Experiment", None)
            if experimentName=='CPTCA_Experiment':
                experiment=CPTCA_Experiment("CPCTCA_Experiment", None, None)
            if experimentName=='CPCTCA_Experiment':
                experiment=CPCTCA_Experiment("CPCTCA_Experiment", None, None)
            if experimentName=='CPTrAda_Experiment':
                for perc in [0.25, 0.5, 0.75]:
                    experimentName = f"CPTrAda_Experiment_{perc}"
                    experiment = CPTrAda(f"CPTrAda_Experiment_{perc}", None, None, perc)
                    print(f"Running CPTrAda with perc={perc}")
                    run_and_evaluate_experiment(datasetManager, experiment, mlflowExperimentName)
                continue  # Skip the rest of the loop for CPTrAda to avoid running it again below

            run_and_evaluate_experiment(datasetManager, experiment, mlflowExperimentName)

    boxplot_results()