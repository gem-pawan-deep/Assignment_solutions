import requests
import json
r1=requests.get("https://restcountries.com/v3.1/alpha/pe")
data=r1.json()
for i in data:
    for row in i["languages"]:
        languages=(i["languages"])
print("languages : ",languages)
for i in data:
    for row in i["capital"]:
        capital= i["capital"]
print("capital : ", capital)
for i in data:
    print("area : ",i["area"])
for i in data:
    print("population : ",i["population"])