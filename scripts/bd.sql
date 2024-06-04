DROP TABLE IF EXISTS `Inscripcion`;
DROP TABLE IF EXISTS `ActividadCampamento`;
DROP TABLE IF EXISTS `ActividadMonitor`;
DROP TABLE IF EXISTS`Actividad`;
DROP TABLE IF EXISTS `Monitor`;
DROP TABLE IF EXISTS `Campamento`;
DROP TABLE IF EXISTS `Usuario`;



/*
*	Tabla Usuarios
*/ 


CREATE TABLE IF NOT EXISTS`Usuario` (
  `identificador` int(3) NOT NULL, /*primary key*/
  `nombre_apellidos` varchar(256) NOT NULL, /*unique key*/
  `fechaNacimiento` date NOT NULL,
  `requiereAtencionEspecial` boolean NOT NULL,
  `email` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL,
  `rol` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


/*
*   Tabla actividad-campamento
*/

CREATE TABLE IF NOT EXISTS `ActividadCampamento` ( 
    `nombreActividad` varchar(256) NOT NULL,  
    `idCampamento` int(3) NOT NULL 
)ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `ActividadMonitor`
( 
    `nombreActividad` varchar(256) NOT NULL,  
    `idMonitor` int(3) NOT NULL     
)ENGINE=InnoDB DEFAULT CHARSET=latin1;


/*
    Tabla Monitor
*/
CREATE TABLE IF NOT EXISTS `Monitor`(
  `identificador` int(3) NOT NULL, 
  `nombre_apellidos` varchar(256) NOT NULL, 
  `educadorEspecial` boolean
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `Campamento` (
  `identificador` int(3) NOT NULL, /*primar key*/
  `fechaInicio` date NOT NULL,
  `fechaFin` date NOT NULL,
  `nivelEducativo` ENUM('infantil', 'juvenil', 'adolescente') NOT NULL,
  `maxUsuarios` int(3) NOT NULL,
  `monitorResponsable` int(3) NULL,
  `monitorEspecial`int(3) NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE IF NOT EXISTS `Actividad` (
  `nombreActividad` varchar(256) PRIMARY KEY,
  `nivelEducativo` ENUM('infantil', 'juvenil', 'adolescente') NOT NULL,
  `horario` enum('manana','tarde') NOT NULL,
  `maxParticipantes` int(3),
  `monitoresNecesarios` int(3)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*
*	Tabla Incripcion
*/
CREATE TABLE IF NOT EXISTS `Inscripcion` (
  `identificador` INT(3) NOT NULL,
  `idUsuario` int(3) NOT NULL,
  `idCampamento` int(3) NOT NULL,
  `fecha` date NOT NULL,
  `precio` float NOT NULL,
  `tipo` ENUM('completo', 'parcial') NOT NULL, /*Completo o parcial*/
  `estado` ENUM('temprano', 'tardio') NOT NULL /*Temprano o tardio*/
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


/*
    Indices de la tabla `Monitor`
*/
ALTER TABLE `Monitor`
  ADD PRIMARY KEY (`identificador`),
  MODIFY `identificador` int(3) NOT NULL AUTO_INCREMENT;

/*
*	Indices de la tabla `Usuario`
*/
ALTER TABLE `Usuario`
  ADD PRIMARY KEY (`identificador`),
  MODIFY `identificador` int(3) NOT NULL AUTO_INCREMENT;




/*
*	Indices de la tabla `Campamento`
*/
ALTER TABLE `Campamento`
  ADD PRIMARY KEY (`identificador`),
  ADD FOREIGN KEY (`monitorResponsable`) REFERENCES Monitor(`identificador`),
  ADD FOREIGN KEY (`monitorEspecial`) REFERENCES Monitor(`identificador`),
  MODIFY `identificador` int(3) NOT NULL AUTO_INCREMENT;

/*
*  Indices de la tabla `ActividadCampamento`
*/


ALTER TABLE `ActividadCampamento`
ADD FOREIGN KEY (`nombreActividad`) REFERENCES `Actividad` (`nombreActividad`),
ADD FOREIGN KEY (`idCampamento`) REFERENCES `Campamento` (`identificador`),
ADD PRIMARY KEY (`nombreActividad`, `idCampamento`);




/*
*  Indices de la tabla `ActividadMonitor`
*/

  ALTER TABLE `ActividadMonitor`
  ADD FOREIGN KEY (`nombreActividad`) REFERENCES `Actividad` (`nombreActividad`),
  ADD FOREIGN KEY (`idMonitor`) REFERENCES `Monitor` (`identificador`),
  ADD PRIMARY KEY (`nombreActividad`, `idMonitor`);



  /*
*	Indices de la tabla `Inscripcion`
*/
ALTER TABLE `Inscripcion`
  ADD PRIMARY KEY  (`identificador`),
  ADD FOREIGN KEY (`idUsuario`) REFERENCES Usuario(`identificador`),
  ADD FOREIGN KEY (`idCampamento`) REFERENCES Campamento(`identificador`),
  MODIFY `identificador` int(3) NOT NULL AUTO_INCREMENT;




