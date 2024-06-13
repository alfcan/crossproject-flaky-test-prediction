FROM python:3.10.12-bookworm

WORKDIR /RepoMiner
RUN mkdir -p ../sharedSpace
RUN mkdir -p ../sharedSpace/Dataset
RUN mkdir -p ../sharedSpace/MetricsDetector
RUN mkdir -p ../sharedSpace/Repository
COPY Cloner.py DatasetGenerator.py main.py connectionTest.py ./

RUN pip install --no-cache-dir --upgrade pip && \
    pip install --no-cache-dir pandas tqdm requests pydriller


