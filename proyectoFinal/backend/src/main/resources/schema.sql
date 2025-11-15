
-- 1. CATEGORIAS
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
