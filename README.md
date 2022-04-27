# Endpoints

1.-localhost:8082/casino/crear /n
Crea una ruleta nueva y lo guarda en la base de datos /n

2.-localhost:8082/casino/apertura /n
Cambia el estado de la ruleta de cerrado a abierto metiendo en un body de json /n
Formato del json
{
  "id":"1"
}

3.-localhost:8082/casino/listar /n
Regresa las id de la ruletas, ganancias y su estado actual/n

4.-localhost:8082/casino/apostar /n
Hace el acto de jugar con la ruleta dependiendo de los parametros metido en el body de json /n
Formato del json
{
    "id": "1",
    "color": "Rojo",
    "apuesta": "3000",
    "numero": ""
}/n

5.-localhost:8082/casino/cerrar /n
hace la accion de cambiar el estado a cerrado dando la id de una ruleta para posteriormente mostrar su id , ganacias y su estado /n
Formato de json /n
{
  "id":"1"
}
