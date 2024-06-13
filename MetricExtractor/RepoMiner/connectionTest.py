import requests


if __name__ == "__main__":

    r=requests.get("http://flakinessmetricsdetector:8080/testConnection")
    print(r.text)






