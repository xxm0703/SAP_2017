from sklearn import linear_model, datasets
from sklearn.metrics import mean_squared_error, r2_score
import numpy as np
import matplotlib.pyplot as plt

boston_dataset = datasets.load_boston()

test_set = 100

y_train = boston_dataset.target[:-test_set]
y_test = boston_dataset.target[-test_set:]

X = boston_dataset.data[:, np.newaxis, 5]

x_train = X[:-test_set]
x_test = X[-test_set:]

linear = linear_model.LinearRegression()
linear.fit(x_train, y_train)

y_pred = linear.predict(x_test)

print("Coefficient", linear.coef_)
print("Mean %f" % mean_squared_error(y_test, y_pred))
print("R2 score %f" % r2_score(y_test, y_pred))

plt.scatter(x_test, y_test, color='blue')
plt.plot(x_test, y_pred, color='red')

plt.show()
