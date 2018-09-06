from sklearn import cluster
from sklearn.metrics import mean_squared_error, r2_score
import numpy as np
import matplotlib.pyplot as plt

X = np.array([[1, 2], [1, 4], [1, 0], [4, 3], [4, 4], [4, 0]])

kmean = cluster.KMeans(n_clusters=2, random_state=4)
kmean.fit(X)

print("Clusters: ", kmean.cluster_centers_)
cent = kmean.cluster_centers_

plt.plot(X, 'bo')
plt.scatter(cent[:, 0], cent[:, 1], color='red', marker='X', s=169)
plt.show()
