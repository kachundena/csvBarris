csvBarris
===========

Proyecto de servicios REST de archivo opendata de Barrios de la ciudad de Barcelona

Parámetros de llamadas a fichero

* FILENAME_CSV_URL: dirección pública URI de fichero CSV
* FILENAME_CSV: dirección privada de fichero CSV
* SEPARATOR_COL; separador entre campos del CSV
* CODEPAGE: código de página utilizado en el fichero

Seguridad

* KEY: Clave de seguridad para servicios REST tipo POST, PUT y DELETE

Servicios 
---------

getBarrisdeDistricte
* Parámetro entrada: districte  int32  
* Parámetro salida: Lista de Barri

getListaBarris
* Parámetro salida: Lista de Barri

getBarri
* Parámetro entrada: linea  int32  
* Parámetro salida: Barri


addBarri
* Parámetro entrada: Barri
* Parámetro header: Autorización


updatBarri
* Parámetro entrada: Barri
* Parámetro header: Autorización


deleteBarri
* Parámetro entrada: Linea int32 
* Parámetro header: Autorización


Clase Barri

Barri
{
linea:	integer (int32)
districte: integer (int32)
barri:	 integer (int32)
nombre: string
}