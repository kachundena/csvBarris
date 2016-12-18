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

Instalación en Tomcat 7
-----------------------

### Ver/Editar Configuración permisos usuarios Tomcat 7

Editar el fichero tomcat-users.xml. como usuario yo he puesto admin con clave password, pero la puedes cambiar.
```
<tomcat-users>
    <role rolename="manager-gui"/>
    <role rolename="manager-script"/>
    <user username="admin" password="password" roles="manager-gui,manager-script" />
</tomcat-users>
```

### Configurar maven 

Si no está instalado, instalar maven.

```
sudo apt-get install maven
```

Ir a configuración de maven. /etc/maven/settings.xml. Añadir en el apartado de server la siguiente configuración.

```
		<server>
			<id>TomcatServer</id>
			<username>admin</username>
			<password>password</password>
		</server>
```

### Configuracion pom

En pom.xml del proyecto añadir el siguiente plugin.

```
<plugin>
    <groupId>org.apache.tomcat.maven</groupId>
    <artifactId>tomcat7-maven-plugin</artifactId>
    <version>2.1</version>
    <configuration>
        <url>http://localhost:8080/manager/text</url>
        <server>TomcatServer</server>
        <username>admin</username>
        <password>password</password>
        <path>/csvBarris</path>
    </configuration>
</plugin> 
```

### Deploy 

Realizar el deploy a tomcat 7 con el comando 
```
mvn tomcat7:deploy
```
Si necesitas hacer un nuevo deploy utiliza
```
mvn tomcat7:redeploy
```

