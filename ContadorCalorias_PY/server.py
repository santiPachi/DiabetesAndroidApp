import flask
from flask import jsonify
import werkzeug
from IaFoodClasificator import IaFoodClasificator
app = flask.Flask(__name__)

@app.route('/setImage', methods = ['GET', 'POST'])
def handle_request():
    imagefile = flask.request.files['image']
   
    filename = werkzeug.utils.secure_filename(imagefile.filename)
    #print("\nReceived image File name : " + imagefile.filename)
    # x_test = np.fromstring(imagefile, np.uint8)
    

    nombre,calorias,porcion,unidad, acc = iaFoodClasificator.predict(imagefile)

    acc = acc *100
    acc = str(acc)
    print(nombre, acc)
    imagefile.save(filename)
    #foodName, acc 
    return jsonify(nombre=nombre,acc=acc,calorias=calorias,porcion=porcion,unidad=unidad)
iaFoodClasificator = IaFoodClasificator()
app.run(host="0.0.0.0", port=5000, debug=True)          


