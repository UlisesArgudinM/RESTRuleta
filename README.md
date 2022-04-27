# Endpoints

1.-localhost:8082/casino/crear 
Crea una ruleta nueva y lo guarda en la base de datos

2.-localhost:8082/casino/apertura
Cambia el estado de la ruleta de cerrado a abierto metiendo en un body de json 
Formato del json
{
  "id":"1"
}

3.-localhost:8082/casino/listar
Regresa las id de la ruletas, ganancias y su estado actual

4.-localhost:8082/casino/apostar
Hace el acto de jugar con la ruleta dependiendo de los parametros metido en el body de json
Formato del json
{
    "id": "1",
    "color": "Rojo",
    "apuesta": "3000",
    "numero": ""
}

5.-localhost:8082/casino/cerrar
hace la accion de cambiar el estado a cerrado dando la id de una ruleta para posteriormente mostrar su id , ganacias y su estado 
Formato de json
{
  "id":"1"
}