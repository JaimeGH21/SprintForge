-- Tabla de Usuarios 
CREATE TABLE Usuarios (
    login VARCHAR(50) PRIMARY KEY,
    pass VARCHAR(255) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    direccion VARCHAR(255),
    attribute VARCHAR(255),
    tipo_usuario VARCHAR(20) NOT NULL    
);


-- Tabla de Inmuebles
CREATE TABLE Inmuebles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    direccion VARCHAR(255) NOT NULL,
    precioNoche DOUBLE NOT NULL,
    politicaCancelacion VARCHAR(30) NOT NULL,
    propietario_login VARCHAR(50),
    FOREIGN KEY (propietario_login) REFERENCES Usuarios(login) ON DELETE CASCADE
);


-- Tabla de Disponibilidades de un Inmueble
CREATE TABLE Disponibilidades (
    id INT AUTO_INCREMENT PRIMARY KEY,
    inmueble_id INT,
    fechaInicio DATE NOT NULL,
    fechaFin DATE NOT NULL,
    precio DOUBLE NOT NULL,
    directa BOOLEAN NOT NULL,
    FOREIGN KEY (inmueble_id) REFERENCES Inmuebles(id) ON DELETE CASCADE
);


-- Tabla de Reservas
CREATE TABLE Reservas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fechaInicio DATE NOT NULL,
    fechaFin DATE NOT NULL,
    pagado BOOLEAN DEFAULT FALSE,
    activa BOOLEAN DEFAULT TRUE,
    inquilino_login VARCHAR(50),
    inmueble_id INT,
    FOREIGN KEY (inquilino_login) REFERENCES Usuarios(login),
    FOREIGN KEY (inmueble_id) REFERENCES Inmuebles(id)
);

