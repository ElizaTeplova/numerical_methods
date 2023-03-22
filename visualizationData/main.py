import requests
import json
import matplotlib.pyplot as plt

stepsNumber = 200
url = 'http://localhost:1337/function'
obj = {
    'steps': 5,
    'start': 5.0,
    'end': 200.0
}

# test = requests.get("http://localhost:1337/testInit")
# print(test.json())

headers = {'Content-type': 'application/json', 'Accept': 'text/plain'}

jsonRequestBody = {
    "steps": stepsNumber,
    "start": 1.1,
    "end": 7
}

responseFunction = requests.post(url=url, json={
    "steps": stepsNumber,
    "start": 1,
    "end": 7
}, )

# responseFunction = requests.post(url=url, json=jsonRequestBody)

print(responseFunction.json())
jfile = json.loads(responseFunction.text)

x = []
y = []

for item in jfile:
    x.append(item['x'])
    y.append(item['y'])

responseAnalyticFirstDerivativeFunction = requests.post(url="http://localhost:1337/first-derivative-analytic-function",
                                                        json=jsonRequestBody)
print(responseAnalyticFirstDerivativeFunction.json())
jfile = json.loads(responseAnalyticFirstDerivativeFunction.text)

xFirstDerivative = []
yFirstDerivative = []

for item in jfile:
    xFirstDerivative.append(item['x'])
    yFirstDerivative.append(item['y'])

# analytic second derivative function


responseAnalyticSecondDerivativeFunction = requests.post(
    url="http://localhost:1337/second-derivative-analytic-function",
    json={
        "steps": stepsNumber,
        "start": 1.5,
        "end": 7
    })
print(responseAnalyticSecondDerivativeFunction.json())
jfile = json.loads(responseAnalyticSecondDerivativeFunction.text)

xSecondDerivative = []
ySecondDerivative = []

for item in jfile:
    xSecondDerivative.append(item['x'])
    ySecondDerivative.append(item['y'])

# third derivative

responseAnalyticThirdDerivativeFunction = requests.post(
    url="http://localhost:1337/third-derivative-analytic-function",
    json={
        "steps": stepsNumber,
        "start": 1.5,
        "end": 7
    })
print(responseAnalyticThirdDerivativeFunction.json())
jfile = json.loads(responseAnalyticThirdDerivativeFunction.text)

xThirdDerivative = []
yThirdDerivative = []

for item in jfile:
    xThirdDerivative.append(item['x'])
    yThirdDerivative.append(item['y'])


# analyticPlot, numericPlot = plt.subplots()

plt.subplot(121)
plt.plot(x, y)
plt.plot(xFirstDerivative, yFirstDerivative)
plt.plot(xSecondDerivative, ySecondDerivative)
plt.plot(xThirdDerivative, yThirdDerivative)
plt.legend(["function", "firstDerivative", "secondDerivative", "thirdDerivative"], loc=0, frameon=True)

# numeric methods

# first right derivative
responseNumericRightFirstDerivativeFunction = requests.post(
    url="http://localhost:1337/first-right-derivative-numeric-function",
    json={
        "steps": stepsNumber,
        "start": 1.1,
        "end": 7
    })
jfile = json.loads(responseNumericRightFirstDerivativeFunction.text)
print("responseNumericRightFirstDerivativeFunction : ")
print(responseNumericRightFirstDerivativeFunction.json())
xNumericRightFirstDerivativeFunction = []
yNumericRightFirstDerivativeFunction = []

for item in jfile:
    xNumericRightFirstDerivativeFunction.append(item['x'])
    yNumericRightFirstDerivativeFunction.append(item['y'])

# first central derivative

responseNumericCentralFirstDerivativeFunction = requests.post(
    url="http://localhost:1337/first-central-derivative-numeric-function",
    json={
        "steps": stepsNumber,
        "start": 1.2,
        "end": 7
    })
jfile = json.loads(responseNumericCentralFirstDerivativeFunction.text)
print("responseNumericCentralFirstDerivativeFunction : ")
print(responseNumericCentralFirstDerivativeFunction.json())
xNumericCentralFirstDerivativeFunction = []
yNumericCentralFirstDerivativeFunction = []

for item in jfile:
    xNumericCentralFirstDerivativeFunction.append(item['x'])
    yNumericCentralFirstDerivativeFunction.append(item['y'])

# second derivative

responseNumericSecondDerivativeFunction = requests.post(
    url="http://localhost:1337/second-derivative-numeric-function",
    json={
        "steps": stepsNumber,
        "start": 1.5,
        "end": 7
    })
jfile = json.loads(responseNumericSecondDerivativeFunction.text)

print("numeric second: ")
print(responseNumericSecondDerivativeFunction.json())
xNumericSecondDerivativeFunction = []
yNumericSecondDerivativeFunction = []

for item in jfile:
    xNumericSecondDerivativeFunction.append(item['x'])
    yNumericSecondDerivativeFunction.append(item['y'])

# third derivative

responseNumericThirdDerivativeFunction = requests.post(
    url="http://localhost:1337/third-derivative-numeric-function",
    json={
        "steps": stepsNumber,
        "start": 1.5,
        "end": 7
    })
jfile = json.loads(responseNumericThirdDerivativeFunction.text)
print("responseNumericThirdDerivativeFunction: ")
print(responseNumericThirdDerivativeFunction.json())
xNumericThirdDerivativeFunction = []
yNumericThirdDerivativeFunction = []

for item in jfile:
    xNumericThirdDerivativeFunction.append(item['x'])
    yNumericThirdDerivativeFunction.append(item['y'])

plt.subplot(122)
plt.plot(x, y)
plt.plot(xNumericRightFirstDerivativeFunction, yNumericRightFirstDerivativeFunction)
plt.plot(xNumericCentralFirstDerivativeFunction, yNumericCentralFirstDerivativeFunction)
plt.plot(xNumericSecondDerivativeFunction, yNumericSecondDerivativeFunction)
plt.plot(xNumericThirdDerivativeFunction, yNumericThirdDerivativeFunction)
plt.legend(["function", "numericFirstDerivative", "numericCentralDerivative", "numericSecondDerivative", "numericThirdDerivative"], loc=0, frameon=True)
plt.title("function")
plt.show()
