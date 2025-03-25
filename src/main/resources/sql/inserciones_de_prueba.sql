
INSERT INTO Rol (nombre_rol)
VALUES
    ('Administrador'),  -- Rol 1
	('Empleado');  -- Rol 2

INSERT INTO Permiso (descripcion, endpoint_url, acceso_directo)
VALUES 
    ('Permisos','/permisos', 0),  -- Permiso 1
    ('Usuarios', '/usuarios', 1);  -- Permiso 2
    ('Roles', '/roles', 1);  -- Permiso 2

INSERT INTO RolPermisos (id_rol, id_permiso, modificacion, eliminacion, creacion)
VALUES
    (1, 1, 1, 1, 1, 1),  -- Rol 1 (Administrador) con Permiso 1 (Permisos)
    (1, 2, 1, 1, 1, 1),  -- Rol 1 (Administrador) con Permiso 2 (Usuarios)
    (1, 3, 1, 1, 1, 1),  -- Rol 1 (Administrador) con Permiso 3 (Roles)
    (2, 2, 1, 0, 0, 0);  -- Rol 2 (Empleado) con Permiso 3 (Ver reportes)

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

INSERT INTO Sucursal (nombre_sucursal, num_establecimiento)
VALUES 
    ('Sucursal Centro', '001'),  -- Sucursal en Tegucigalpa
    ('Sucursal Norte', '002');  -- Sucursal en San Pedro Sula

INSERT INTO Usuario (usuario, contrasena, fecha_creacion, id_rol, id_usuario_creacion, id_usuario_modificacion)
VALUES 
    ('admin', 'admin123', '2025-03-14', 1, NULL, NULL);  

DECLARE @id_admin INT;
SET @id_admin = (SELECT id_usuario FROM Usuario WHERE usuario = 'admin');

	INSERT INTO Usuario (usuario, contrasena, fecha_creacion, id_rol, id_usuario_creacion, id_usuario_modificacion)
VALUES 
    ('analopez', 'empleado456', '2025-03-14', 2, @id_admin, NULL);


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


INSERT INTO Departamento (nombre_departamento)
VALUES
    ('Medicamentos'),
    ('Higiene'),
    ('Suplementos'),
    ('Cuidado Personal');

 INSERT INTO Categoria (nombre_categoria, id_departamento)
 VALUES
     ('Analgésicos', 1),
     ('Antibióticos', 1),
     ('Vitaminas', 3),
     ('Shampoo', 2),
     ('Cremas', 4),
     ('Antigripales', 1),
     ('Enjuagues bucales', 2);


INSERT INTO Proveedor (
  razon_social, contacto, RTN_contacto, telefono, correo, direccion
)
VALUES
    ('Laboratorios Fármaco S.A.', 'María López', '0801199012345', '2234-5678', 'mlopez@farmaco.hn', 'Blvd. Centroamérica, Tegucigalpa'),
    ('Distribuidora Médica del Norte', 'Carlos Rivera', '0801198709876', '2245-9988', 'crivera@dmn.com', 'Col. Kennedy, Tegucigalpa'),
    ('SanaSana Importaciones', 'Ana Gómez', '0801198511123', '2230-1122', 'ana@sanasana.hn', 'Barrio Abajo, San Pedro Sula'),
    ('Mega Farma S. de R.L.', 'Luis Torres', '0801199001234', '2277-6655', 'luis@megafarma.com', 'Col. Florencia Norte Tegucigalpa');


INSERT INTO Producto (
  nombre_producto, descripcion, precio_venta, inventario, fecha_vencimiento,
  venta_libre, precio_descuento, fecha_creacion, fecha_modificacion,
  id_categoria, id_proveedor, id_usuario_creacion, id_usuario_modificacion
)
VALUES
    ('Paracetamol 500mg', 'Analgésico para dolores leves', 15.00, 100, '2026-12-31', 1, 12.00, GETDATE(), NULL, 1, 1, 1, NULL),
    ('Ibuprofeno 400mg', 'Analgésico y antiinflamatorio', 20.00, 150, '2026-10-31', 1, NULL, GETDATE(), NULL, 1, 1, 1, NULL),
    ('Vitamina C 1000mg', 'Suplemento inmunológico', 30.00, 80, '2025-08-30', 1, 25.00, GETDATE(), NULL, 3, 1, 1, NULL);

INSERT INTO SucursalProducto (inventario_sucursal, id_producto, id_sucursal)
VALUES
    (50, 1, 1),  -- 50 unidades de Paracetamol en Sucursal Central
    (30, 1, 2),  -- 30 unidades de Paracetamol en Sucursal Norte
    (80, 2, 1),  -- 80 unidades de Ibuprofeno en Sucursal Central
    (20, 2, 2);  -- 20 unidades de Ibuprofeno en Sucursal Norte