# Source
All credits go to the authors: **[Javier Acero](https://twitter.com/jacegu)** and **[Guillermo Pascual](https://twitter.com/pasku1)**.

This workshop was held at the [Software Craftsmanship Barcelona 2013](http://jaumejornet.net/scbcn/) conference and reapeated in Madrid as a [Software Craftsmanship local charter meetup](http://www.meetup.com/madswcraft/events/152606792/). 

# Description

## Original

[Modular design with TDD]()https://speakerdeck.com/jacegu/modular-design-with-tdd)

## Transcription of slides (Spanish)

### RESTRICCIONES- Haz lo más simple que pueda funcionar.- Escribe el mejor código que puedas.- No hagas más de lo que pide la funcionalidad.- No introduzcas infraestructura si la funcionalidad no lo pide explícitamente.- No dependas de librerías si la funcionalidad no lo pide explícitamente.

### ITERACIÓN I - 10min
Un usuario puede registrarse con un nombre de usuario. Por ejemplo: “@pasku1”.Si otra persona se ha registrado usando ese mismo nombre de usuario se produce un error.

### ITERACIÓN II - 20minUn usuario puede seguir a otros usuarios. Para hacerlo basta con conocer el nickname del usuario al que se quiere seguir.Cualquiera debe poder consultar a quién sigue un determinado usuario conociendo su nickname.
### MÁS RESTRICCIONES- No se puede modificar el código escrito hasta ahora.### ITERACIÓN III - 20minLos registros de usuarios así como las listas de usuarios seguidos deben almacenarse de forma durable.### MÁS RESTRICCIONES:- Situar el código escrito hasta ahora dentro de un namespace llamado “core”- Usar el código escrito hasta ahora como si fuera una librería externa.- El código de esta iteración debe estar en un namespace diferente.- Ese namespace puede tener una única dependencia del “core”.
### ITERACIÓN IV - 30minCrear un mecanismo de entrega HTTP que permita acceder a la funcionalidad desarrollada hasta ahora.### ITERACIÓN V - 20min
Un usuario puede publicar “roars”.El resto de usuarios deben poder consultar todos los “roars” que un usuario con un determinado “nickname” ha escrito.

### ITERACIÓN VI - 40min
Poner esta nueva funcionalidad disponible en mecanismo de entrega.Garantizar la durabilidad de los datos.

## My modifications
### ITERATION IV
Substitute HTTP delivery mechanism with a console application.

# Implementation hints

## Add dependency to a simple relational database

In `build.groovy`:

```
dependencies {
    // H2
    compile 'com.h2database:h2:1.4.182'
    // sqlite
    compile 'org.xerial:sqlite-jdbc:3.7.2'
}
```

Establish a connection with [`groovy.sql.Sql`](http://groovy.codehaus.org/api/groovy/sql/Sql.html):

```
// H2 in-memory instance (not durable!)
Sql.newInstance('jdbc:h2:test','sa', '', 'org.h2.Driver')
// H2 file-backed instance; database file will be created in the working directory
Sql.newInstance('jdbc:h2:mem:dojo','sa', '', 'org.h2.Driver')

// sqlite
Sql.newInstance('jdbc:sqlite:sample.db','org.sqlite.JDBC')
```