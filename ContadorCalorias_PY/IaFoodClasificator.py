import nombres_comidas
import cv2
from tensorflow.keras.models import Sequential, Model
from tensorflow.keras.layers import Dense, Dropout, Activation, Flatten
from tensorflow.keras.layers import Convolution2D, MaxPooling2D, ZeroPadding2D, GlobalAveragePooling2D, AveragePooling2D,Input
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from tensorflow.keras.callbacks import ModelCheckpoint, CSVLogger, LearningRateScheduler, ReduceLROnPlateau
from tensorflow.keras.optimizers import SGD
from tensorflow.keras.applications.inception_v3 import InceptionV3
from tensorflow.keras.applications.inception_v3 import preprocess_input, decode_predictions
from tensorflow.keras.regularizers import l2
import tensorflow.keras.backend as K
import tensorflow.keras as keras
import math
import os
import numpy as np
import plaidml.keras
from tensorflow.keras import backend as K
plaidml.keras.install_backend()
os.environ["KERAS_BACKEND"] = "plaidml.keras.backend"
from PIL import Image
class IaFoodClasificator:
    def __init__(self):
        self.model = self.load_model()
        
    def load_model(self,name="model_incep.hdf5"):
        try:
            model = keras.models.load_model("model_ia/"+name)
            print("Model loaded")
        except Exception:
            print("Error at load model")
            base_model = InceptionV3(weights='imagenet', include_top=False, input_tensor=Input(shape=(224, 224, 3)))
            x = base_model.output
            x = AveragePooling2D(pool_size=(2, 2))(x)
            x = Dropout(.4)(x)
            x = Flatten()(x)
            predictions = Dense(36, init='glorot_uniform', W_regularizer=l2(.0005), activation='softmax')(x)
            model = Model(input=base_model.input, output=predictions)
            opt = SGD(lr=.0001, momentum=.99)
            model.compile(optimizer=opt, loss='categorical_crossentropy', metrics=['accuracy'])
        return model
            #model.summary()

   
    
    def predict(self,file):
        img = Image.open(file)
        img = img.resize((224,224))
        x_test = np.asarray(img)
        x_test = x_test.astype(float) / 225.
        x_test = np.reshape(x_test,(1,x_test.shape[0],x_test.shape[1],x_test.shape[2]))
        y_pred_conf = self.model.predict(x_test) #return probabilities of each class
        y_pred = np.argmax(y_pred_conf,axis=1)
        dat = nombres_comidas.classtolabel[str(y_pred[0])] 
        acc = np.max(y_pred_conf[0])
        return dat[0],dat[1],dat[2],dat[3], acc

