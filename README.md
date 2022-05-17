# Endpoints

1.-localhost:8082/casino/crear <br>
Crea una ruleta nueva y lo guarda en la base de datos <br>

2.-localhost:8082/casino/apertura <br>
Cambia el estado de la ruleta de cerrado a abierto metiendo en un body de json <br>
Formato del json <br>
{<br>
  "id":"1"<br>
}<br>

3.-localhost:8082/casino/listar <br>
Regresa las id de la ruletas, ganancias y su estado actual <br>

4.-localhost:8082/casino/apostar <br>
Hace el acto de jugar con la ruleta dependiendo de los parametros metido en el body de json <br>
Formato del json <br>
{<br>
    "id": "1",<br>
    "color": "Rojo",<br>
    "apuesta": "3000",<br>
    "numero": ""<br>
}<br>

5.-localhost:8082/casino/cerrar <br>
hace la accion de cambiar el estado a cerrado dando la id de una ruleta para posteriormente mostrar su id , ganacias y su estado <br>
Formato de json <br>
{<br>
  "id":"1"<br>
}
<br>
EurekaServer
<br>
https://github.com/UlisesArgudinM/EurekaServer.git
<br>
GatewayServer
<br>
https://github.com/UlisesArgudinM/GatewayServer.git

