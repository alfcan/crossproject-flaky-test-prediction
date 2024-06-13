from pydriller import  Repository, Git
from git import Repo
import os

class Cloner:

    def __init__(self):
        # Define the path to the folder containing repositories dynamically based on the environment.
        # If using Docker, the path will be './sharedSpace/Repository'.
        # Otherwise, if not using Docker, the path will be '../sharedSpace/Repository'.
        if 'DOCKER' in os.environ and os.environ['DOCKER'] == 'true':
            self.path_folder = './sharedSpace/Repository'  # For Docker usage
        else:
            self.path_folder = '../sharedSpace/Repository'  # For non-Docker usage


    def clone_repository(self,repository,gitURL, gitSSH):
        try:
            path_folder_repo=os.path.join(self.path_folder,'{}_{}'.format(repository,gitSSH))
            if not self.repositoryExist(path_folder_repo):
                Repo.clone_from(gitURL,path_folder_repo)
                gr=Git(path_folder_repo)
                gr.checkout(gitSSH)
        except Exception as e:
            return False

        return True

    def repositoryExist(self,path_folder_repo):
        isExist=os.path.exists(path_folder_repo)
        return isExist