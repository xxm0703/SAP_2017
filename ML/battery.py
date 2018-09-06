from sklearn import linear_model, preprocessing
from sklearn.metrics import mean_squared_error, r2_score
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt


def get_median_filtered(signal, threshold=3):
    signal = signal.copy()
    difference = np.abs(signal - np.median(signal))
    median_difference = np.median(difference)
    if median_difference == 0:
        s = 0
    else:
        s = difference / float(median_difference)
    mask = s > threshold
    signal[mask] = np.median(signal)
    return signal


data = pd.read_csv('./battery-data.csv', header=None)

X = np.array(data[0]).reshape(data.shape[0], 1)
Y = np.array(data[1]).reshape(data.shape[0], 1)

X = get_median_filtered(X)

print(X.shape)

x_test = X[-30:]
y_test = Y[-30:]

x_train = X[:-30]
y_train = Y[:-30]

linear = linear_model.LinearRegression()

linear.fit(x_train, y_train)
y_pred = linear.predict(x_test)

print("Mean sqrt: ", mean_squared_error(y_test, y_pred))
print("Variance: ", r2_score(y_test, y_pred))

plt.scatter(X, Y, color='blue')
plt.scatter(x_test, y_pred, color='green')
plt.plot(x_test, y_pred, color='red')

plt.show()
