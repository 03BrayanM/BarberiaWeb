
-- 1. CATEGORIAS (5 registros)
INSERT INTO categorias (nombre) VALUES
('Cortes de Cabello'),
('Servicios de Barba'),
('Tratamientos Capilares'),
('Paquetes Combo'),
('Servicios Premium');

-- 2. SERVICIOS (5 registros)
INSERT INTO servicios (nombre, descripcion, precio, duracion, imagen, estado, idCategoria) VALUES
('Corte Clasico', 'Corte de cabello tradicional con tijera y maquina', 15000.00, 30, '/assets/images/servicios/imagen1.jpg', true, 1),
('Corte Fade', 'Corte moderno con degradado perfecto', 20000.00, 45, '/assets/images/servicios/imagen2.jpg', true, 1),
('Arreglo de Barba', 'Perfilado y arreglo de barba con toalla caliente', 12000.00, 25, '/assets/images/servicios/imagen3.jpg', true, 2),
('Corte + Barba', 'Paquete completo de corte y arreglo de barba', 25000.00, 60, '/assets/images/servicios/imagen4.jpg', true, 4),
('Tratamiento Capilar', 'Tratamiento hidratante y fortalecedor', 30000.00, 40, '/assets/images/servicios/imagen5.jpg', true, 3);

