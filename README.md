# Challenge ONE | Java | Back-end | Hotel Alura

![Captura de pantalla 2022-06-30 040029](https://user-images.githubusercontent.com/48140383/176614356-02993117-2e79-48b4-92b0-f693872a0c70.png)

![Captura de pantalla 2022-06-30 040304](https://user-images.githubusercontent.com/48140383/176614375-9fdfcc2a-1cbf-47e0-a741-b8e51f2219dd.png)

![Captura de pantalla 2022-06-30 040349](https://user-images.githubusercontent.com/48140383/176614399-b1a1d1df-c7fc-444e-8fa8-763097642ecc.png)

![Captura de pantalla 2022-06-30 040415](https://user-images.githubusercontent.com/48140383/176614413-548231d9-4ec8-445e-8592-500215d7d632.png)

![Captura de pantalla 2022-06-30 040527](https://user-images.githubusercontent.com/48140383/176614448-5e2a2ae3-e500-4e5a-9772-6a61e88b55cd.png)

![Captura de pantalla 2022-06-30 040604](https://user-images.githubusercontent.com/48140383/176614476-a32e221a-b9fe-4c97-bab7-2dc5a1f871f5.png)

<p align="center" >
     <img width="300" heigth="300" src="https://user-images.githubusercontent.com/101413385/173164615-192ca98a-1a44-480e-9229-9f82f456eec8.png">
</p>

## Para la simulacion del proyecto se necesita una base de datos sql y modificar la ruta de la conexión en la clase ConnectionFactory.
````
pooleDataSource.setJdbcUrl("jdbc:mysql://localhost/nombredelabasededatos");
pooleDataSource.setUser("usuario");
pooleDataSource.setPassword("contraseña");

````

### Estructura de la tablas
````
BD nombre = alura_hotel
CREATE TABLE `reservas` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `FechaEntrada` date NOT NULL,
  `FechaSalida` date NOT NULL,
  `Valor` float NOT NULL,
  `FormaPago` varchar(20) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1

CREATE TABLE `huespedes` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(20) NOT NULL,
  `Apellido` varchar(20) NOT NULL,
  `Fecha de Nacimiento` date NOT NULL,
  `Nacionalidad` varchar(50) DEFAULT NULL,
  `Telefono` varchar(15) NOT NULL,
  `Id Reserva` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Id Reserva` (`Id Reserva`),
  CONSTRAINT `huespedes_ibfk_2` FOREIGN KEY (`Id Reserva`) REFERENCES `reservas` (`Id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(20) NOT NULL,
  `password` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1
````

## Enunciado del Proyecto
---
Crea tu propia aplicación Desktop con conexión al Banco de Datos.

## 🖥️ Tecnologías Utilizadas:

- Java
- Eclipse
- MySql
- Biblioteca JCalendar
- Plugin WindowBuilder </br>

---
## ⚠️ Importante! ⚠️

☕ Use Java versión 8 para compatibilidad. </br></br>
📝 Recomendamos usar el editor de Eclipse para compatibilidad con la Interfaz Gráfica. </br></br>
🎨 La interfaz contiene dos métodos importantes:
- setResizable(false): determina el tamaño de la ventana, y a través del parámetro <strong>false</strong>, la pantalla no se puede maximizar;
- setLocationRelativeTo(null): determina la ubicación de la ventana, y a través del parámetro <strong>null</strong> la mantiene centrada en la pantalla.


🧡 <strong>Oracle</strong></br>
<a href="https://www.linkedin.com/company/oracle/" target="_blank">
<img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>

💙 <strong>Alura Latam</strong></br>
<a href="https://www.linkedin.com/company/alura-latam/mycompany/" target="_blank">
<img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>
