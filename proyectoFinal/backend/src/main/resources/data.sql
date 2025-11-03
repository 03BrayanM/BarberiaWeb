
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

-- 3. BARBEROS (4 registros)
INSERT INTO barberos (nombre, apellido, telefono, email, fechaContratacion, estado) VALUES
('Carlos', 'Ramirez', '3001234567', 'carlos.ramirez@barberia.com', '2023-01-15', true),
('Miguel', 'Torres', '3109876543', 'miguel.torres@barberia.com', '2023-03-20', true),
('Andres', 'Lopez', '3201112233', 'andres.lopez@barberia.com', '2023-06-10', true),
('Juan', 'Martinez', '3156667788', 'juan.martinez@barberia.com', '2024-02-01', false);

-- 4. CLIENTES (5 registros)
INSERT INTO clientes (nombre, apellido, telefono, email, fechaRegistro, estado) VALUES
('Pedro', 'Gonzalez', '3123456789', 'pedro.gonzalez@email.com', '2024-01-10', true),
('Luis', 'Hernandez', '3145678901', 'luis.hernandez@email.com', '2024-02-15', true),
('Diego', 'Ruiz', '3167890123', 'diego.ruiz@email.com', '2024-03-20', true),
('Santiago', 'Moreno', '3189012345', 'santiago.moreno@email.com', '2024-05-05', true),
('Sebastian', 'Castro', '3101234567', 'sebastian.castro@email.com', '2024-06-12', false);

-- 5. HORARIOS (Horario completo para barbero Carlos Ramírez - id 1)
INSERT INTO horarios (idBarbero, diaSemana, horaInicio, horaFin, disponible) VALUES
-- Barbero 1: Carlos - Lunes a Viernes
(1, 'LUNES', '08:00:00', '18:00:00', true),
(1, 'MARTES', '08:00:00', '18:00:00', true),
(1, 'MIERCOLES', '08:00:00', '18:00:00', true),
(1, 'JUEVES', '08:00:00', '18:00:00', true),
(1, 'VIERNES', '08:00:00', '18:00:00', true),
(1, 'SABADO', '09:00:00', '14:00:00', true),

-- Barbero 2: Miguel - Lunes a Sábado
(2, 'LUNES', '09:00:00', '19:00:00', true),
(2, 'MARTES', '09:00:00', '19:00:00', true),
(2, 'MIERCOLES', '09:00:00', '19:00:00', true),
(2, 'JUEVES', '09:00:00', '19:00:00', true),
(2, 'VIERNES', '09:00:00', '19:00:00', true),
(2, 'SABADO', '09:00:00', '15:00:00', true),

-- Barbero 3: Andrés - Martes a Sábado
(3, 'MARTES', '10:00:00', '20:00:00', true),
(3, 'MIERCOLES', '10:00:00', '20:00:00', true),
(3, 'JUEVES', '10:00:00', '20:00:00', true),
(3, 'VIERNES', '10:00:00', '20:00:00', true),
(3, 'SABADO', '10:00:00', '16:00:00', true);

-- 6. CITAS (5 registros con diferentes estados)
INSERT INTO citas (fechaHora, estadoCita, observaciones, idCliente, idBarbero, idServicio) VALUES
('2024-10-29 10:00:00', 'CONFIRMADA', 'Cliente prefiere corte bajo en los lados', 1, 1, 2),
('2024-10-29 14:00:00', 'PENDIENTE', NULL, 2, 2, 1),
('2024-10-30 09:00:00', 'COMPLETADA', 'Cliente satisfecho, dejo propina', 3, 1, 4),
('2024-10-30 16:00:00', 'CANCELADA', 'Cliente cancelo por motivos personales', 4, 3, 3),
('2024-10-31 11:00:00', 'CONFIRMADA', 'Primera visita del cliente', 5, 2, 5);