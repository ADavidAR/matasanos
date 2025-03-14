
INSERT INTO Rol (nombre_rol)
VALUES
    ('Administrador'),  -- Rol 1
    ('Empleado');  -- Rol 2

INSERT INTO Permiso (descripcion)
VALUES 
    ('Acceder al sistema'),  -- Permiso 1
    ('Modificar registros'),  -- Permiso 2
    ('Ver reportes');  -- Permiso 3

INSERT INTO RolPermisos (id_rol, id_permiso)
VALUES
    (1, 1),  -- Rol 1 (Administrador) con Permiso 1 (Acceder al sistema)
    (1, 2),  -- Rol 1 (Administrador) con Permiso 2 (Modificar registros)
    (2, 3);  -- Rol 2 (Empleado) con Permiso 3 (Ver reportes)


INSERT INTO Ciudad (ciudad)
VALUES 
    ('Tegucigalpa'),  -- Ciudad 1
    ('San Pedro Sula'),  -- Ciudad 2
    ('La Ceiba');  -- Ciudad 3

	INSERT INTO Direcciones (colonia, direccion, id_ciudad)
VALUES 
    ('Centro', 'Av. Principal #123', 1),  -- Dirección en Tegucigalpa
    ('Zona Industrial', 'Calle Secundaria #456', 2); -- Dirección en San Pedro Sula


	INSERT INTO Cargo (descripcion)
VALUES 
    ('Gerente de Ventas'),  -- Cargo 1
    ('Cajero');  -- Cargo 2

	INSERT INTO Sucursal (nombre, num_establecimiento)
VALUES 
    ('Sucursal Centro', '001'),  -- Sucursal en Tegucigalpa
    ('Sucursal Norte', '002');  -- Sucursal en San Pedro Sula


	INSERT INTO Usuario (usuario, contrasena, fecha_creacion, id_rol, id_usuario_creacion, id_usuario_modificacion)
VALUES 
    ('admin', 'admin123', '2025-03-14', 1, NULL, NULL);  


	INSERT INTO Usuario (usuario, contrasena, fecha_creacion, id_rol, id_usuario_creacion, id_usuario_modificacion)
VALUES 
    ('analopez', 'empleado456', '2025-03-14', 2, 1, NULL);  


INSERT INTO Empleado (
primer_nombre, 
segundo_nombre, 
primer_apellido, 
segundo_apellido, 
telefono, 
correo, 
salario, 
fecha_contratacion, 
activo, 
fecha_modificacion, 
id_direccion, 
id_cargo, 
id_usuario, 
id_sucursal, 
id_usuario_creacion, 
id_usuario_modificacion)
VALUES
    ('Carlos', 'Alberto', 'Gomez', 'Pérez', '1234567890', 'carlos.gomez@empresa.com', 3000.00, '2025-03-01', 1, NULL, 1, 1, 1, 1, 1, NULL),  
    ('Ana', 'Maria', 'Lopez', 'Martinez', '0987654321', 'ana.lopez@empresa.com', 2800.00, '2025-03-02', 1, NULL, 2, 2, 2, 2, 1, NULL);  

