import os
import shutil
from time import sleep

import pandas
from tqdm import trange
import sys
import requests

from Cloner import Cloner
from DatasetGenerator import DatasetGenerator

if __name__ == "__main__":

    if len(sys.argv)<3:
        print("Please specify the CSV file and the name of the dataset to create in the main function call.")
        exit()

    path_csv = sys.argv[1]
    name_dataset = sys.argv[2]

    repositories={
        "name": [],
        "URL": [],
        "SHA": [],
        "flaky_tests_list": [],
        "non_flaky_tests_list": []
    }

    if not os.path.exists('../sharedSpace/Repository'):
        os.makedirs('../sharedSpace/Repository')
    if not os.path.exists('../sharedSpace/MetricsDetector'):
        os.makedirs('../sharedSpace/MetricsDetector')
    if not os.path.exists('../sharedSpace/Dataset'):
        os.makedirs('../sharedSpace/Dataset')


    df=pandas.read_csv(path_csv,delimiter=';')

    for url,sha in zip(df['Project URL'],df['SHA Detected']):
        repoName = url.split('/')[-1]
        if not sha in repositories['SHA']:
            repositories['name'].append(repoName)
            repositories['URL'].append(url)
            repositories['SHA'].append(sha)
            repositories['flaky_tests_list'].append([])
            repositories['non_flaky_tests_list'].append([])

    for index,row in df.iterrows():
        sha = row['SHA Detected']
        index_sha = repositories['SHA'].index(sha)
        listTF = repositories['flaky_tests_list'][index_sha]
        listTNF = repositories['non_flaky_tests_list'][index_sha]

        test=row[df.columns[2]].replace('#','.')

        if 'IsFlaky' in df.columns:

            if row['IsFlaky']==0:
                listTNF.append(test)
            else:
                listTF.append(test)
        else:
            listTF.append(test)


    # Iterate over each repository present in the DataFrame
    cloner = Cloner()
    datasetGeneretor = DatasetGenerator()
    datasetGeneretor.createDataset(name_dataset)
    
    # Initialize a progress bar
    progressbar=trange(len(repositories['name'])-1,desc='Cloning..',leave=True)
    
    # Iterate over repositories and their associated information
    for repository,url,sha,listTF,listTNF,_ in zip(repositories['name'],
                                                    repositories['URL'],
                                                    repositories['SHA'],
                                                    repositories['flaky_tests_list'],
                                                    repositories['non_flaky_tests_list'],
                                                    progressbar):

        # Check if the repository already exists in the general dataset
        if  datasetGeneretor.repositoryExistInDataset('{}_{}'.format(repository,sha)):
            continue


        progressbar.set_description("Cloning {}_{}".format(repository,sha))
        progressbar.refresh()
        cloner.clone_repository(repository,url,sha)

        progressbar.set_description("Metrics Extract {}_{}".format(repository,sha))
        progressbar.refresh()
        PARAMS = {'repositoryName':'{}_{}'.format(repository,sha)}
        try:
            if 'DOCKER' in os.environ and os.environ['DOCKER'] == 'true':
                r=requests.get("http://flakinessmetricsdetector:8080/getFlakinessMetrics",params=PARAMS,timeout=300)
            else:
                r=requests.get("http://localhost:8080/getFlakinessMetrics",params=PARAMS,timeout=300) # For non-Docker usage
            
            if r.text=='true':
                progressbar.set_description("Generate Dataset {}_{}".format(repository,sha))
                progressbar.refresh()
                datasetGeneretor.addRepositoryToDataset('{}_{}'.format(repository,sha),listTF,listTNF)


                shutil.rmtree('../sharedSpace/Repository/{}_{}'.format(repository,sha))
                os.remove('../sharedSpace/MetricsDetector/{}_{}'.format(repository,sha))
                os.remove('../sharedSpace/MetricsDetector/{}_{}_TestReject'.format(repository,sha))
            else:
                shutil.rmtree('../sharedSpace/Repository/{}_{}'.format(repository,sha))

        except Exception as e:
            progressbar.set_description("Communication error with metricsDetector")
            progressbar.refresh()
            if os.path.exists('../sharedSpace/Repository/{}_{}'.format(repository,sha)):
                shutil.rmtree('../sharedSpace/Repository/{}_{}'.format(repository,sha))
            if os.path.exists('../sharedSpace/MetricsDetector/{}_{}'.format(repository,sha)):
                os.remove('../sharedSpace/MetricsDetector/{}_{}'.format(repository,sha))
            if os.path.exists('../sharedSpace/MetricsDetector/{}_{}_TestReject'.format(repository,sha)):
                os.remove('../sharedSpace/MetricsDetector/{}_{}_TestReject'.format(repository,sha))
            continue






