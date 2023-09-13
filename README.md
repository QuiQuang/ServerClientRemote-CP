# Adding Dense:

- Increasing the number of units will increase processing time due to the presence of (n \* units) weights, However, it will significantly enhance the initial capacity and gradually increase thereafter.
- Increasing hidden layer may not be as meaningful and it also consumes more processing time compared to its counterparts.

# Adding

- There is little difference in changing the number of filters, but it can achieve quite high prediction results. However, it will significantly increase processing time, considering a single filter size.
- It is not much meaningful and also increases processing time.

# Adding multiple Convolution layers:

- It makes more complexity and leads to overfitting.
- It needs much more time and resources for processing.

# Adding Pooling layers:

- It reduces the spatial dimensions of the feature maps (feature maps) by a fixed scale (usually 2x2 or 3x3). This helps reduce the complexity of the model and processing time while retaining important features. This is particularly useful for controlling overfitting.
- It makes the model less sensitive to the precise location of features in the feature maps.
- Reducing the size results in less processing time.

# Adding Dropout:

- It can help to avoid overfitting in neutral network.
- It reduce the accuracy and loss in many cases.
- As expected, adding dropout causes the model to fit less well to the training data, but improves the models ability to generalise and correctly classify the test data.
