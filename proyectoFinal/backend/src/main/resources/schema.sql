
-- 1. CATEGORIAS (No tiene dependencias)
DROP TABLE IF EXISTS categorias;
CREATE TABLE categorias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- 2. SERVICIOS (Depende de categorias)
DROP TABLE IF EXISTS servicios;
CREATE TABLE servicios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500),
    precio DECIMAL(10,2) NOT NULL,
    duracion INT NOT NULL COMMENT 'Duración en minutos',
    imagen VARCHAR(255),
    estado BOOLEAN NOT NULL DEFAULT TRUE,
    idCategoria INT NOT NULL,
    FOREIGN KEY (idCategoria) REFERENCES categorias(id)
);

-- 3. BARBEROS (No tiene dependencias)
DROP TABLE IF EXISTS barberos;
CREATE TABLE barberos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100),
    fechaContratacion DATE NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE
);

-- 4. CLIENTES (No tiene dependencias)
DROP TABLE IF EXISTS clientes;
CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100),
    fechaRegistro DATE NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE
);

-- 5. HORARIOS (Depende de barberos)
DROP TABLE IF EXISTS horarios;
CREATE TABLE horarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idBarbero INT NOT NULL,
    diaSemana VARCHAR(20) NOT NULL,
    horaInicio TIME NOT NULL,
    horaFin TIME NOT NULL,
    disponible BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (idBarbero) REFERENCES barberos(id),
    CHECK (diaSemana IN ('LUNES', 'MARTES', 'MIERCOLES', 'JUEVES', 'VIERNES', 'SABADO', 'DOMINGO'))
);

-- 6. CITAS (Depende de clientes, barberos y servicios)
DROP TABLE IF EXISTS citas;
CREATE TABLE citas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fechaHora DATETIME NOT NULL,
    estadoCita VARCHAR(20) NOT NULL,
    observaciones VARCHAR(500),
    idCliente INT NOT NULL,
    idBarbero INT NOT NULL,
    idServicio INT NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES clientes(id),
    FOREIGN KEY (idBarbero) REFERENCES barberos(id),
    FOREIGN KEY (idServicio) REFERENCES servicios(id),
    CHECK (estadoCita IN ('PENDIENTE', 'CONFIRMADA', 'COMPLETADA', 'CANCELADA'))
);