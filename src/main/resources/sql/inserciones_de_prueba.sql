-- Ciudades
INSERT INTO Ciudad (ciudad) 
VALUES 
	('Tegucigalpa'),
	('San Pedro Sula'),
	('La Ceiba'),
	('Comayagua'),
	('Choluteca');

-- Colonias
INSERT INTO Colonia (nombre_colonia, id_ciudad) 
VALUES
	('Colonia Palmira', 1),
	('Colonia Las Colinas', 1),
	('Colonia Satelite', 2),
	('Colonia El Carmen', 2),
	('Colonia El Centro', 3),
	('Colonia La Isla', 3),
	('Colonia La Pradera', 4),
	('Colonia Los Alamos', 5);

-- Direcciones
INSERT INTO Direccion (referencia, id_colonia) VALUES
('Frente al Parque Central', 1),
('A 2 cuadras del Mall', 2),
('Junto a la Clinica Viera', 3),
('Contiguo a Banco Ficohsa', 4),
('Plaza San Miguel, Local 5', 5);

-- Sucursales
INSERT INTO Sucursal (id_direccion, nombre_sucursal) 
VALUES
	(1, 'Farmacia Matasanos Centro'),
	(2, 'Farmacia Matasanos Norte'),
	(3, 'Farmacia Matasanos SPS'),
	(4, 'Farmacia Matasanos La Ceiba'),
	(5, 'Farmacia Matasanos Choluteca');

-- Departamentos
INSERT INTO Departamento (nombre_departamento) VALUES
('Medicamentos'),
('Cuidado Personal'),
('Suplementos'),
('Equipo Medico'),
('Maternidad');

-- Categorias
INSERT INTO Categoria (nombre_categoria, id_departamento) VALUES
('Analgesicos', 1),
('Antibioticos', 1),
('Jabones', 2),
('Shampoos', 2),
('Vitaminas', 3),
('Proteínas', 3),
('Termometros', 4),
('Tensiómetros', 4),
('Pañales', 5),
('Leche Infantil', 5);

INSERT INTO Proveedor (razon_social, contacto, RTN_contacto, telefono, correo, direccion) VALUES
('Farmaceutica Honduras S.A.', 'Juan Perez', '0801199000123', '22334455', 'ventas@farmaceuticahn.com', 'Boulevard Morazan, Tegucigalpa'),
('Distribuidora Medica S.A.', 'Maria Gonzalez', '0801198700456', '22336677', 'contacto@dmedical.com', 'Colonia Palmira, Calle Principal'),
('Importadora Internacional', 'Carlos Martinez', '0801199500789', '22338899', 'info@importadorain.com', 'Residencial Los Pinos, San Pedro Sula'),
('Suplementos Nutricionales', 'Ana Rodriguez', '0801199200321', '22330011', 'ventas@suplementoshn.com', 'Colonia Las Colinas, Tegucigalpa');


-- Primero, insertamos los permisos especificados
INSERT INTO Permiso (descripcion, endpoint_url, acceso_directo) VALUES
    ('Permisos', '/permisos', 0),      -- Permiso 1
    ('Usuarios', '/usuarios', 1),      -- Permiso 2
    ('Roles', '/roles', 1),            -- Permiso 3
    ('Empleados', '/empleados', 1),    -- Permiso 4
    ('Sucursales', '/sucursales', 1),  -- Permiso 5
    ('Cajas', '/cajas', 1),            -- Permiso 6
	('Clientes', '/clientes', 1),      -- Permiso 7
	('Recetas Medicas', '/recetas', 1),-- Permiso 8
	('Compras', '/compras', 1),-- Permiso 9
	('Inventario', '/inventory', 1),-- Permiso 10
	('Ventas', '/ventas', 1),-- Permiso 11
	('Carrito', '/carrito', 0),-- Permiso 12
	('Factura', '/factura', 0);-- Permiso 13


-- Luego insertamos los roles básicos
INSERT INTO Rol (nombre_rol) VALUES
    ('Administrador'),    -- Rol 1
    ('Gerente'),         -- Rol 2
    ('Supervisor'),      -- Rol 3
    ('Empleado');        -- Rol 4

-- Finalmente, asignamos permisos a roles con comentarios detallados
INSERT INTO RolPermiso (id_rol, id_permiso, acceso, modificacion, eliminacion, creacion) VALUES
    -- Permisos para Administrador (acceso completo a todo)
    (1, 1, 1, 1, 1, 1),  -- Rol 1 (Administrador) puede gestionar completamente Permisos
    (1, 2, 1, 1, 1, 1),  -- Rol 1 (Administrador) puede gestionar completamente Usuarios
    (1, 3, 1, 1, 1, 1),  -- Rol 1 (Administrador) puede gestionar completamente Roles
    (1, 4, 1, 1, 1, 1),  -- Rol 1 (Administrador) puede gestionar completamente Empleados
    (1, 5, 1, 1, 1, 1),  -- Rol 1 (Administrador) puede gestionar completamente Sucursales
    (1, 6, 1, 1, 1, 1),  -- Rol 1 (Administrador) puede gestionar completamente Cajas
	(1, 7, 1, 1, 1, 1),  -- Rol 1 (Administrador) puede gestionar completamente Clientes
	(1, 8, 1, 1, 1, 1),  -- Rol 1 (Administrador) puede gestionar completamente Recetas Medicas
	(1, 9, 1, 1, 1, 1),  -- Rol 1 (Administrador) puede gestionar completamente Compras
	(1, 10, 1, 1, 1, 1),  -- Rol 1 (Administrador) puede gestionar completamente Inventario
	(1, 11, 1, 1, 1, 1),  -- Rol 1 (Administrador) puede gestionar completamente Ventas
	(1, 12, 1, 1, 1, 1),  -- Rol 1 (Administrador) puede gestionar completamente Carrito
	(1, 13, 1, 1, 1, 1),  -- Rol 1 (Administrador) puede gestionar completamente Factura
    
    -- Permisos para Gerente (acceso casi completo excepto para Permisos)
    (2, 2, 1, 1, 0, 1),  -- Rol 2 (Gerente) puede ver, modificar y crear Usuarios (no eliminar)
    (2, 3, 1, 1, 0, 1),  -- Rol 2 (Gerente) puede ver, modificar y crear Roles (no eliminar)
    (2, 4, 1, 1, 0, 1),  -- Rol 2 (Gerente) puede ver, modificar y crear Empleados (no eliminar)
    (2, 5, 1, 1, 0, 1),  -- Rol 2 (Gerente) puede ver, modificar y crear Sucursales (no eliminar)
    (2, 6, 1, 1, 0, 1),  -- Rol 2 (Gerente) puede ver, modificar y crear Cajas (no eliminar)
    
    -- Permisos para Supervisor (acceso limitado)
    (3, 2, 1, 0, 0, 0),  -- Rol 3 (Supervisor) puede solo ver Usuarios
    (3, 4, 1, 1, 0, 0),  -- Rol 3 (Supervisor) puede ver y modificar Empleados
    (3, 5, 1, 0, 0, 0),  -- Rol 3 (Supervisor) puede solo ver Sucursales
    (3, 6, 1, 0, 0, 0),  -- Rol 3 (Supervisor) puede solo ver Cajas
    
    -- Permisos para Empleado (acceso mínimo)
    (4, 2, 1, 0, 0, 0),  -- Rol 4 (Empleado) puede solo ver Usuarios
    (4, 4, 1, 0, 0, 0);  -- Rol 4 (Empleado) puede solo ver Empleados

	-- Usuarios admin
INSERT INTO Usuario (usuario, contrasena, fecha_creacion, id_rol, activo, id_usuario_creacion) VALUES
('admin', 'admin123', GETDATE(), 1, 1, 1);          -- Administrador


INSERT INTO Producto (nombre_producto, descripcion, precio_venta, fecha_vencimiento, venta_libre, impuesto, id_categoria, id_proveedor, fecha_creacion, id_usuario_creacion) VALUES
('Paracetamol 500mg', 'Analgesico y antipiretico, caja con 20 tabletas', 50.00, '2025-12-31', 1, 0.15, 1, 1, GETDATE(), 1),
('Amoxicilina 250mg', 'Antibiotico de amplio espectro, frasco con 12 capsulas', 120.00, '2024-10-15', 0, 0.15, 2, 1, GETDATE(), 1),
('Jabon Antibacterial', 'Jabon liquido antibacterial 250ml', 35.00, '2026-05-30', 1, 0.15, 3, 2, GETDATE(), 1),
('Shampoo Anticaspa', 'Shampoo medicado para caspa 400ml', 85.00, '2025-08-20', 1, 0.15, 4, 2, GETDATE(), 1),
('Vitamina C 1000mg', 'Suplemento de vitamina C, frasco con 30 tabletas', 150.00, '2025-11-30', 1, 0.15, 5, 4, GETDATE(), 1),
('Termometro Digital', 'Termometro digital con pantalla LCD', 250.00, '2027-01-01', 1, 0.15, 7, 3, GETDATE(), 1);

INSERT INTO TipoMovimiento (nombre, factor) VALUES
('Compra', 1),       -- Aumenta inventario
('Venta', -1),       -- Disminuye inventario
('Ajuste Positivo', 1), -- Ajuste que aumenta
('Ajuste Negativo', -1), -- Ajuste que disminuye
('Devolucion', 1);   -- Productos devueltos

INSERT INTO FichaInventario (cantidad, referencia, fecha, id_producto, id_sucursal, id_tipo_movimiento) VALUES
(100, 'INV-INICIAL', GETDATE(), 1, 1, 1),
(50, 'INV-INICIAL', GETDATE(), 2, 1, 1),
(75, 'INV-INICIAL', GETDATE(), 3, 1, 1),
(30, 'INV-INICIAL', GETDATE(), 4, 1, 1),
(40, 'INV-INICIAL', GETDATE(), 5, 1, 1),
(20, 'INV-INICIAL', GETDATE(), 6, 1, 1),
(80, 'INV-INICIAL', GETDATE(), 1, 2, 1),
(60, 'INV-INICIAL', GETDATE(), 3, 2, 1),
(45, 'INV-INICIAL', GETDATE(), 5, 2, 1);

-- Personas
INSERT INTO Persona (
    primer_nombre, 
    segundo_nombre, 
    primer_apellido, 
    segundo_apellido, 
    dni, 
    id_direccion
) VALUES
('Juan', 'Carlos', 'García', 'López', '0801-1990-00123', 1),
('María', NULL, 'Martínez', 'Hernández', '0801-1987-00456', 2),
('Pedro', 'Antonio', 'Ramírez', NULL, '0801-1995-00789', 3),
('José', 'María', 'Flores', 'Castro', '0801-1992-00321', 4),
('Ana', 'Sofía', 'Pérez', 'González', '0801-1993-00456', 1),
('Luis', NULL, 'Mejía', NULL, '0801-1996-00678', 2),
('Carlos', 'Alberto', 'Vásquez', 'Pineda', '0801-1994-00567', 4),
('Rosa', 'Isabel', 'Castillo', 'Montenegro', '0801-1997-00789', 3),
('Mario', 'Enrique', 'Reyes', NULL, '0801-1998-00890', 4);

-- Cargos
INSERT INTO Cargo (descripcion) VALUES
('Administrador del Sistema'),
('Gerente de Farmacia'),
('Supervisor de Turno'),
('Farmaceutico'),
('Cajero'),
('Bodeguero');

DECLARE @idAdmin INT;

SELECT @idAdmin = id_usuario 
FROM Usuario 
WHERE usuario = 'admin';

-- Empleados
INSERT INTO Empleado (
    salario, 
    fecha_contratacion, 
    activo, 
    id_persona, 
    id_cargo,
    id_sucursal, 
    id_usuario_creacion,
	fecha_creacion
) VALUES
(35000.00, '2023-01-15', 1, 1, 1, 1, @idAdmin, '2023-01-15'),     -- Administrador
(28000.00, '2023-02-20', 1, 2, 2, 1, @idAdmin, '2023-02-20'),     -- Gerente
(22000.00, '2023-03-10', 1, 3, 3, 1, @idAdmin, '2023-03-10'),     -- Supervisor
(18000.00, '2023-04-05', 1, 4, 4, 1, @idAdmin, '2023-04-05'),    -- Farmacéutico
(15000.00, '2023-05-12', 1, 5, 5, 1, @idAdmin, '2023-05-12'),     -- Cajero
(17000.00, '2023-06-18', 1, 6, 6, 1, @idAdmin, '2023-06-18');     -- Bodeguero

UPDATE Usuario SET id_empleado = 1 WHERE id_usuario = @idAdmin;

-- Usuarios
INSERT INTO Usuario (usuario, contrasena, fecha_creacion, id_rol, activo, id_empleado, id_usuario_creacion) VALUES
('gerente1', 'gerente123', GETDATE(), 2, 1, 2, 1),     -- Gerente
('supervisor1', 'super123', GETDATE(), 3, 1, 3, 1),    -- Supervisor
('farmaceutico1', 'farma123', GETDATE(), 4, 1, 4, 1),  -- Farmacéutico
('cajero1', 'cajero123', GETDATE(), 4, 1 , 5, 1),       -- Cajero
('bodega1', 'bodega123', GETDATE(), 4, 1, NULL, 1);       -- Bodeguero

-- Clientes
INSERT INTO Cliente (rtn, fecha_creacion, id_persona, id_usuario_creacion) VALUES
('0801199000123', GETDATE(), 5, 1),
(NULL, GETDATE(), 6, 1),
('0801199500789', GETDATE(), 7, 1),
(NULL, GETDATE(), 8, 1);

-- Recetas
INSERT INTO Receta (fecha, descripcion, nombre_medico, id_cliente) VALUES
('2023-05-10', 'Tratamiento para infección bacteriana', 'Dr. Roberto Mendoza', 1),
('2023-06-15', 'Control de dolor y fiebre', 'Dra. Laura Sánchez', 2),
('2023-07-20', 'Tratamiento para infección de vías urinarias', 'Dr. Carlos Jiménez', 3),
('2023-08-05', 'Suplemento vitamínico para sistema inmunológico', 'Dra. Patricia Gómez', 4),
('2023-09-12', 'Tratamiento para alergias estacionales', 'Dr. Eduardo Ramírez', 1);

-- Productos asociados a recetas
INSERT INTO RecetaProducto (cantidad, indicaciones, id_receta, id_producto) VALUES
(1, 'Tomar 1 tableta cada 8 horas por 7 días', 1, 2),  -- Amoxicilina para receta 1
(2, 'Tomar 1 tableta cada 6 horas si hay fiebre o dolor', 2, 1),  -- Paracetamol para receta 2
(1, 'Tomar 1 cápsula cada 12 horas por 10 días', 3, 2),  -- Amoxicilina para receta 3
(1, 'Tomar 1 tableta diaria con alimentos', 4, 5),  -- Vitamina C para receta 4
(1, 'Tomar 1 tableta cada 8 horas si hay síntomas', 5, 1),  -- Paracetamol para receta 5
(1, 'Usar shampoo 3 veces por semana', 5, 4);  -- Shampoo anticaspa para receta 5

INSERT INTO MetodoPago (descripcion) VALUES
('Efectivo'),
('Tarjeta de Credito'),
('Tarjeta de Debito'),
('Transferencia Bancaria'),
('Cheque');

INSERT INTO Descuento (descuento_nombre, porcentaje, descripcion) VALUES
('Descuento por volumen', 0.10, 'Aplica para compras mayores a 1000 Lempiras'),
('Descuento tercera edad', 0.15, 'Aplica para adultos mayores de 60 años'),
('Descuento empleado', 0.20, 'Aplica para empleados de la farmacia'),
('Promocion especial', 0.05, 'Promocion por temporada');

INSERT INTO Caja (num_caja, id_sucursal) VALUES
(1, 1),
(2, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5);