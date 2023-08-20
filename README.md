# EldarWallet
Challenge Wallet

Esta aplicacion es una Wallet, que consta de una pantalla inicial donde figura tanto el Login, como la posibilidad de crear un "nuevo usuario"
en caso de no tener uno creado. Los datos para el Login se almacenan de manera interna en la base de datos de la aplicacion. Una vez confirmada la creacion del usuario,
se puede acceder a la pantalla principal que consta de un sector que arroja el "Saldo de dinero", un Button para agregar tarjetas y un Button para realizar un
pago generando un Codigo QR. Al intentar agregar nuevas tarjetas, se le solicita al usuario que coloque "Numero de Tarjeta", "Codigo de Seguridad" y "Fecha de Vencimiento"
Una vez colocados esos datos, la tarjeta se carga automaticamente en la pantalla principal, detectando a traves del primer numero de la tarjeta, si la tarjeta es 
Visa, Mastercard o American Express. Al utilizar el boton Pagar con QR, la aplicacion realiza un consumo del servicio del API, y automaticamente genera un codigo QR
a pantalla completa en una nueva pantalla. A esta pantalla principal, aun se le pueden agregar nuevas funcionalidades y redirecciones a otras futuras pantallas de la app.

CONSUMO DEL API:

El BASE URL que utilice:https://rapidapi.com. Una vez generada el USER-ID y API KEY, se aplico la misma en el consumo del API para asi lograr tener acceso 
a toda la informacion del API. Se hace la respectiva llamada al servicio para que genere el Codigo QR.

FUNCIONES DE LA APP:

El proyecto se realizo con una arquitectura MVVM, para que, si a futuro se desea ampliar el formato de la aplicacion, el codigo sea mas simple de reutilizar. 
Todo el flujo entre Fragments fue realizado a traves de Navigation Component.
La base de datos interna de la aplicacion se maneja a traves de ROOM.

DEPENDENCIAS UTILIZADAS:
 *Okhttp
 *Room
 *Navigation Component
