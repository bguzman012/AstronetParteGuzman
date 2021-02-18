import os
import urllib3
import json
from subprocess import check_output
import subprocess
import time
import json
import requests 
import http.client
import json
import requests

url = "http://localhost:8080/CallCenterAstronet/srv/astronet/ip"
url2 = "http://localhost:8080/CallCenterAstronet/srv/astronet/notificar"
usuario = "bg@gmail.com"
password = "magaly00" 
http = urllib3.PoolManager()
r = http.request('GET', url)
jsonNuevo = json.loads(r.data.decode('utf-8'))
ipGlob = ""
completed = subprocess.run(['dir'])
print('returncode:', completed.returncode)
    
if r.status==200:
    for item in jsonNuevo:
        itemAux = item.split(",")
        if itemAux[0] == usuario and itemAux[1] == password:
            ipGlob = itemAux[2] 
            cabecera1 = {'Content-type': 'application/json'}
            datos = '{"ip": "%s", "userEmpleado": "%s"}' % (ipGlob, usuario)
            solicitud = requests.post(url2, headers = cabecera1, data = datos)
            import subprocess

            
            subprocess.call("C:\Windows\System32\cmd.exe /k start ping " + ipGlob + " -t", shell=True)     
            
        


