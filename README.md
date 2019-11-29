# Gestor de logistica de tiendas

## Requisitos
- Maven 3.6.1
- Java 1.8.0
- OpenJDK 8


## Contenido del repositorio
### Estructura general
```
practicarmi/
├── Almacen
│   ├── pom.xml
│   └── src
│       ├── main
│       │   └── java
│       │       └── es
│       │           └── upm
│       │               └── middleware
│       │                   ├── App.java
│       │                   ├── LogisticaImpl.java
│       │                   ├── LogisticaInterfaz.java
│       │                   ├── RoturaStockException.java
│       │                   ├── Servidor.java
│       │                   └── TiendaRegistradaException.java
│       └── test
│           └── java
│               └── es
│                   └── upm
│                       └── middleware
│                           └── AppTest.java
├── docs
│   └── Practica-RMI.pdf
├── README.md
├── scripts
│   ├── run_almacen.sh
│   └── run_tienda.sh
└── Tienda
    ├── pom.xml
    └── src
        ├── main
        │   └── java
        │       └── es
        │           └── upm
        │               └── middleware
        │                   ├── App.java
        │                   ├── Cliente.java
        │                   ├── LogisticaInterfaz.java -> ../../../../../../../Almacen/src/main/java/es/upm/middleware/LogisticaInterfaz.java
        │                   ├── RoturaStockException.java -> ../../../../../../../Almacen/src/main/java/es/upm/middleware/RoturaStockException.java
        │                   └── TiendaRegistradaException.java -> ../../../../../../../Almacen/src/main/java/es/upm/middleware/TiendaRegistradaException.java
        └── test
            └── java
                └── es
                    └── upm
                        └── middleware
                            └── AppTest.java

```
- **Almacen**: Código fuente del almacén (servidor)
- **docs**: Documentación relevante
- **scripts**: Scripts para la ejecución de los distintos componentes del proyecto
- **Tienda**: Código fuente de la tienda (cliente)
- Las clases **App.java** y **AppTest.java** tienen como función la de comprobar la correcta construcción del proyecto en Maven
- Almacen (servidor):
  - **LogisticaImpl.java**: Implementación de las funciones de la interfaz (_LogisticaInterfaz.java_)
  - **LogisticaInterfaz.java**: Interfáz de las funciones. Es común tanto para cliente como para servidor
  - **RoturaStockException.java**: Excepción propia que indica la falta de stock
  - **Servidor.java**: Programa principal de la parte del servidor
  - **TiendaRegistradaException.java**: Excepción propia que indica que la tienda ya ha sido registrada
- Tienda (cliente):
  - **Cliente.java**: Programa principal de la parte del servidor 
  - **LogisticaInterfaz.java**: Enlace simbólico a la interfáz de las funciones. 
  - **RoturaStockException.java**: Enlace simbólico a la excepción propia que indica la falta de stock
  - **TiendaRegistradaException.java**: Enlace simbólico a la excepción propia que indica que la tienda ya ha sido registrada

  
## Ejecución de la aplicación
**Primero, se debe ejecutar el almacen (servidor)**
```sh
./scripts/run_almacen.sh
```
**Por último, ejecutar tantas tiendas (clientes) como se desee**
```sh
./scripts/run_tienda.sh
```
## Autores
- Víctor Nieves Sánchez (Scrum Master & Developer)
- Ruben Garcia Garcia (Product Owner & Developer)
- Eduardo Freyre Gomez (Developer)
- Miguel Gonzalo Anton (Developer)

