CREATE DATABASE matasanos;

USE matasanos;

CREATE TABLE Ciudad(
	id_ciudad INT IDENTITY(1,1) PRIMARY KEY,
	ciudad VARCHAR (200) NOT NULL
);

CREATE TABLE Colonia(
	id_colonia INT IDENTITY(1,1) PRIMARY KEY,
	nombre_colonia VARCHAR(200),
	id_ciudad INT,
	FOREIGN KEY (id_ciudad) REFERENCES Ciudad(id_ciudad)
);

CREATE TABLE Direccion(
	id_direccion INT IDENTITY(1,1) PRIMARY KEY,
	referencia VARCHAR (500) NOT NULL,
	id_colonia INT NOT NULL,
	FOREIGN KEY (id_colonia) REFERENCES Colonia(id_colonia)
);

CREATE TABLE Sucursal(
	id_sucursal INT IDENTITY (1,1) PRIMARY KEY,
	id_direccion INT,
	nombre_sucursal VARCHAR (200) NOT NULL,
	FOREIGN KEY (id_direccion) REFERENCES Direccion(id_direccion)
);

CREATE TABLE TipoMovimiento(
	id_tipo_movimiento INT IDENTITY (1,1) PRIMARY KEY,
	nombre VARCHAR(50),
	factor INT
);

CREATE TABLE Funcion(
	id_funcion INT IDENTITY (1,1) PRIMARY KEY,
	descripcion VARCHAR (500)
);

CREATE TABLE Permiso(
	id_permiso INT IDENTITY (1,1) PRIMARY KEY,
	descripcion VARCHAR (400),
	acceso_directo BIT NOT NULL,
	endpoint_url VARCHAR (200) NOT NULL
);

CREATE TABLE Departamento(
	id_departamento INT IDENTITY(1,1) PRIMARY KEY,
	nombre_departamento VARCHAR (200) NOT NULL
);

CREATE TABLE Proveedor(
	id_proveedor INT IDENTITY (1,1) PRIMARY KEY,
	razon_social VARCHAR (300) NOT NULL,
	contacto VARCHAR (300),
	RTN_contacto VARCHAR (20),
	telefono VARCHAR (15) NOT NULL,
	correo VARCHAR (250) NOT NULL,
	direccion VARCHAR(MAX) NOT NULL
);

CREATE TABLE FacturacionSAR(
	id_factura_sar INT IDENTITY (1,1) PRIMARY KEY,
	id_sucursal INT,
	fecha_vigencia DATE NOT NULL,
	rango_inicio INT NOT NULL,
	rango_fin INT NOT NULL,
	vigente BIT NOT NULL,
	cai VARCHAR (44) UNIQUE NOT NULL,
	inicio_cod_factura VARCHAR (11) NOT NULL,
	FOREIGN KEY (id_sucursal) REFERENCES Sucursal(id_sucursal)
);

CREATE TABLE Descuento(
	id_descuento INT IDENTITY (1,1) PRIMARY KEY,
	descuento_nombre VARCHAR (200),
	porcentaje DECIMAL,
	descripcion text
);

CREATE TABLE Rol(
	id_rol INT IDENTITY(1,1) PRIMARY KEY,
	nombre_rol VARCHAR (300) UNIQUE NOT NULL,
);

CREATE TABLE Cargo(
	id_cargo INT IDENTITY(1,1) PRIMARY KEY,
	descripcion VARCHAR (100)
);

CREATE TABLE FuncionCargo(
	id_funcion_cargo INT IDENTITY(1,1) PRIMARY KEY,
	id_funcion INT,
	id_cargo INT NOT NULL,
	FOREIGN KEY (id_funcion) REFERENCES Funcion(id_funcion),
	FOREIGN KEY (id_cargo) REFERENCES Cargo(id_cargo)
);

CREATE TABLE RolPermiso(
	id_rol_permiso INT IDENTITY(1,1) PRIMARY KEY,
	id_rol INT,
	id_permiso INT NOT NULL,
	acceso BIT NOT NULL,
	modificacion BIT NOT NULL,
	eliminacion BIT NOT NULL,
	creacion BIT NOT NULL,
	FOREIGN KEY (id_rol) REFERENCES Rol(id_rol),
	FOREIGN KEY (id_permiso) REFERENCES Permiso(id_permiso),
	CONSTRAINT UC_RolPermiso_id_rol_id_permiso UNIQUE (id_rol, id_permiso)
);

CREATE TABLE Categoria(
	id_categoria INT IDENTITY(1,1) PRIMARY KEY,
	nombre_categoria VARCHAR (200) NOT NULL,
	id_departamento INT NOT NULL,
	FOREIGN KEY (id_departamento) REFERENCES Departamento(id_departamento)
);

CREATE TABLE Usuario(
	id_usuario INT IDENTITY(1,1) PRIMARY KEY,
	usuario VARCHAR (100) UNIQUE NOT NULL,
	contrasena VARCHAR (300) NOT NULL,
	activo BIT NOT NULL,
	fecha_creacion DATE NOT NULL DEFAULT GETDATE(),
	fecha_modificacion DATE,
	id_rol INT NOT NULL,
	id_empleado INT,
	id_usuario_creacion INT,
	id_usuario_modificacion INT,
	FOREIGN KEY (id_rol) REFERENCES Rol(id_rol),
	FOREIGN KEY (id_usuario_creacion) REFERENCES Usuario(id_usuario),
	FOREIGN KEY (id_usuario_modificacion) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Persona(
	id_persona INT IDENTITY(1,1) PRIMARY KEY,
	primer_nombre VARCHAR(100) NOT NULL,
	segundo_nombre VARCHAR(100),
	primer_apellido VARCHAR(100) NOT NULL,
	segundo_apellido VARCHAR(100),
	dni VARCHAR(15) UNIQUE,
	id_direccion INT,
	FOREIGN KEY (id_direccion) REFERENCES Direccion(id_direccion)
);

CREATE TABLE Medico(
    id_medico INT IDENTITY (1,1) PRIMARY KEY,
    num_colegiado VARCHAR (10) NOT NULL,
	id_persona INT,
	FOREIGN KEY (id_persona) REFERENCES Persona (id_persona)
);


CREATE TABLE Cliente(
	id_cliente INT IDENTITY(1,1) PRIMARY KEY,
	rtn VARCHAR (20),
	fecha_creacion DATE NOT NULL DEFAULT GETDATE(),
	fecha_modificacion DATE,
	id_persona INT,
	id_usuario_creacion INT,
	id_usuario_modificacion INT,
	FOREIGN KEY (id_persona) REFERENCES Persona(id_persona),
	FOREIGN KEY (id_usuario_creacion) REFERENCES Usuario(id_usuario),
	FOREIGN KEY (id_usuario_modificacion) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Caja(
	id_caja INT IDENTITY(1,1) PRIMARY KEY,
	num_caja INT NOT NULL,
	id_sucursal INT NOT NULL,
	FOREIGN KEY (id_sucursal) REFERENCES Sucursal(id_sucursal)
);


CREATE TABLE Factura(
	id_factura INT IDENTITY(1,1) PRIMARY KEY,
	numero_factura VARCHAR(300) NOT NULL,
	fecha_emision DATE NOT NULL,
	rtn_cliente VARCHAR(20),
	subtotal DECIMAL NOT NULL,
	impuesto DECIMAL NOT NULL,
	id_factura_sar INT NOT NULL,
	id_usuario INT NOT NULL,
	id_cliente INT NOT NULL,
	id_caja INT,
	FOREIGN KEY (id_factura_sar) REFERENCES FacturacionSAR(id_factura_sar),
	FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
	FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
	FOREIGN KEY (id_caja) REFERENCES Caja(id_caja)
);

CREATE TABLE FacturaDescuento(
	id_factura_descuento INT IDENTITY(1,1) PRIMARY KEY,
	id_factura INT NOT NULL,
	id_descuento INT NOT NULL,
	FOREIGN KEY (id_factura) REFERENCES Factura(id_factura),
	FOREIGN KEY (id_descuento) REFERENCES Descuento(id_descuento)
);

CREATE TABLE Producto(
	id_producto INT IDENTITY(1,1) PRIMARY KEY,
	nombre_producto VARCHAR(300) NOT NULL,
	descripcion VARCHAR(MAX) NOT NULL,
	precio_venta DECIMAL NOT NULL,
	fecha_vencimiento DATE NOT NULL,
	venta_libre BIT NOT NULL,
	precio_descuento DECIMAL,
	impuesto DECIMAL,
	fecha_creacion DATE NOT NULL DEFAULT GETDATE(),
	fecha_modificacion DATE,
	costo_venta DECIMAL,
	id_categoria INT NOT NULL,
	id_proveedor INT NOT NULL,
	id_usuario_creacion INT,
	id_usuario_modificacion INT,
	FOREIGN KEY (id_categoria) REFERENCES Categoria(id_categoria),
	FOREIGN KEY (id_proveedor) REFERENCES Proveedor(id_proveedor),
	FOREIGN KEY (id_usuario_creacion) REFERENCES Usuario(id_usuario),
	FOREIGN KEY (id_usuario_modificacion) REFERENCES Usuario(id_usuario)
);

CREATE TABLE FichaInventario(
	id_ficha INT IDENTITY (1,1) PRIMARY KEY,
	cantidad INT,
	referencia VARCHAR(50),
	fecha DATE,
	id_producto INT,
	id_sucursal INT,
	id_tipo_movimiento INT,
	FOREIGN KEY (id_producto) REFERENCES Producto(id_producto),
	FOREIGN KEY (id_sucursal) REFERENCES Sucursal(id_sucursal),
	FOREIGN KEY (id_tipo_movimiento) REFERENCES TipoMovimiento(id_tipo_movimiento),
);

CREATE TABLE FacturaProducto(
	id_factura_producto INT IDENTITY(1,1) PRIMARY KEY,
	cantidad INT NOT NULL,
	precio_unitario DECIMAL NOT NULL,
	impuset_porcentaje DECIMAL,
	impuesto DECIMAL,
	subtotal DECIMAL,
	id_factura INT NOT NULL,
	id_producto INT NOT NULL,
	FOREIGN KEY (id_factura) REFERENCES Factura(id_factura),
	FOREIGN KEY (id_producto) REFERENCES Producto(id_producto)
);

CREATE TABLE Receta(
	id_receta INT IDENTITY(1,1) PRIMARY KEY,
	fecha DATE,
	descripcion VARCHAR(MAX),
	id_medico INT ,
	id_cliente INT,
	FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
	FOREIGN KEY (id_medico) REFERENCES Medico(id_medico)
);

CREATE TABLE RecetaProducto(
	id_receta_producto INT IDENTITY(1,1) PRIMARY KEY,
	cantidad INT NOT NULL,
	indicaciones VARCHAR(MAX),
	id_receta INT NOT NULL,
	id_producto INT NOT NULL,
	FOREIGN KEY (id_receta) REFERENCES Receta(id_receta),
	FOREIGN KEY (id_producto) REFERENCES Producto(id_producto)
);

CREATE TABLE Compra(
	id_compra INT IDENTITY(1,1) PRIMARY KEY,
	num_factura_compra VARCHAR(19) NOT NULL,
	fecha_compra DATE,
	fecha_entrega DATE,
	costo_total DECIMAL NOT NULL,
	id_proveedor INT NOT NULL,
	FOREIGN KEY (id_proveedor) REFERENCES Proveedor(id_proveedor)
);

CREATE TABLE ProductoCompra(
	id_producto_compra INT IDENTITY(1,1) PRIMARY KEY,
	cantidad INT NOT NULL,
	costo DECIMAL NOT NULL,
	id_compra INT NOT NULL,
	id_producto INT NOT NULL,
	FOREIGN KEY (id_compra) REFERENCES Compra(id_compra),
	FOREIGN KEY (id_producto) REFERENCES Producto(id_producto)
);

CREATE TABLE Empleado(
	id_empleado INT IDENTITY(1,1) PRIMARY KEY,
	salario DECIMAL,
	fecha_contratacion DATE NOT NULL,
	fecha_baja DATE,
	activo BIT NOT NULL,
	fecha_creacion DATE NOT NULL DEFAULT GETDATE(),
	fecha_modificacion DATE,
	id_persona INT NOT NULL,
	id_cargo INT NOT NULL,
	id_sucursal INT NOT NULL,
	id_usuario_creacion INT,
	id_usuario_modificacion INT,
	FOREIGN KEY (id_persona) REFERENCES Persona(id_persona),
	FOREIGN KEY (id_cargo) REFERENCES Cargo(id_cargo),
	FOREIGN KEY (id_sucursal) REFERENCES Sucursal(id_sucursal),
	FOREIGN KEY (id_usuario_creacion) REFERENCES Usuario(id_usuario),
	FOREIGN KEY (id_usuario_modificacion) REFERENCES Usuario(id_usuario)
);

ALTER TABLE Usuario
ADD CONSTRAINT FK_usuario_empleado
FOREIGN KEY (id_empleado) REFERENCES Empleado(id_empleado);

--Nuevo

CREATE TABLE MetodoPago(
	id_metodo INT IDENTITY(1,1) PRIMARY KEY,
	descripcion VARCHAR(100)
);

CREATE TABLE Pago(
	id_pago INT IDENTITY(1,1) PRIMARY KEY,
	importe DECIMAL,
	fecha DATE,
	id_factura INT,
	id_metodo INT,
	FOREIGN KEY (id_factura) REFERENCES Factura(id_factura),
	FOREIGN KEY (id_metodo) REFERENCES MetodoPago(id_metodo)

);

CREATE TABLE Correo(
	id_correo INT IDENTITY(1,1) PRIMARY KEY,
	correo VARCHAR(100),
	id_persona INT,
	FOREIGN KEY (id_persona) REFERENCES Persona(id_persona)
);

CREATE TABLE Telefono(
	id_telefono INT IDENTITY(1,1) PRIMARY KEY,
	telefono VARCHAR(100),
	id_persona INT,
	FOREIGN KEY (id_persona) REFERENCES Persona(id_persona)
);

--VISTAS

CREATE VIEW v_Usuario AS
    SELECT * FROM Usuario;

CREATE VIEW v_UsuarioConRol AS
    SELECT u.*, r.nombre_rol FROM Usuario u
    LEFT JOIN Rol r ON r.id_rol = u.id_rol;

CREATE VIEW v_RolPermiso AS
    SELECT rp.*, r.nombre_rol, p.descripcion, p.endpoint_url, p.acceso_directo FROM Rol r
    LEFT JOIN RolPermiso rp ON rp.id_rol = r.id_rol
    LEFT JOIN Permiso p ON p.id_permiso = rp.id_permiso;

CREATE VIEW v_ProductoSucursal AS
	SELECT p.*, nombre_categoria, d.id_departamento, nombre_departamento, s.id_sucursal, nombre_sucursal, fi.cantidad, fi.fecha, fi.referencia, tm.factor, tm.nombre FROM Producto p
	INNER JOIN Categoria c ON c.id_categoria = p.id_categoria
	INNER JOIN Departamento d ON d.id_departamento = c.id_departamento
	INNER JOIN FichaInventario fi ON fi.id_producto = p.id_producto
	INNER JOIN Sucursal s ON s.id_sucursal = fi.id_sucursal
	INNER JOIN TipoMovimiento tm ON tm.id_tipo_movimiento = fi.id_tipo_movimiento;


CREATE VIEW v_Producto AS
	SELECT p.*, d.id_departamento, nombre_categoria, nombre_departamento, fi.cantidad, fi.fecha, fi.referencia, tm.factor, tm.nombre FROM Producto p
	INNER JOIN Categoria c ON c.id_categoria = p.id_categoria
	INNER JOIN Departamento d ON d.id_departamento = c.id_departamento
	INNER JOIN FichaInventario fi ON fi.id_producto = p.id_producto
	INNER JOIN TipoMovimiento tm ON tm.id_tipo_movimiento = fi.id_tipo_movimiento;

CREATE VIEW v_Permiso AS
	SELECT * FROM Permiso;

CREATE VIEW v_Rol AS
    SELECT * FROM Rol;

CREATE VIEW v_Empleado AS
	SELECT e.*, p.primer_nombre, p.segundo_nombre, p.primer_apellido, p.segundo_apellido, p.dni, col.*, ciu.ciudad, d.id_direccion, d.referencia, c.descripcion, s.nombre_sucursal FROM Empleado e
	INNER JOIN Persona p ON e.id_persona = p.id_persona
	INNER JOIN Cargo c ON e.id_cargo = c.id_cargo
	INNER JOIN Sucursal s ON s.id_sucursal = e.id_sucursal
	INNER JOIN Direccion d ON d.id_direccion = p.id_direccion
	INNER JOIN Colonia col ON col.id_colonia = d.id_colonia
	INNER JOIN Ciudad ciu ON ciu.id_ciudad = col.id_ciudad;

CREATE VIEW v_UsuarioEmpleadoRol AS
	SELECT 
    u.id_usuario,
    u.usuario,
    u.contrasena,
    u.activo AS usuario_activo,
    u.fecha_creacion AS usuario_fecha_creacion,
    u.fecha_modificacion AS usuario_fecha_modificacion,
    u.id_rol,
	u.nombre_rol,
    u.id_empleado,
    u.id_usuario_creacion AS usuario_creador,
    u.id_usuario_modificacion AS usuario_modificador,
    e.salario,
    e.fecha_contratacion,
    e.fecha_baja,
    e.activo AS empleado_activo,
    e.fecha_creacion AS empleado_fecha_creacion,
    e.fecha_modificacion AS empleado_fecha_modificacion,
    e.id_cargo,
    e.id_sucursal,
    e.id_usuario_creacion AS empleado_creador,
    e.id_usuario_modificacion AS empleado_modificador,
    e.id_persona,
    e.primer_nombre,
    e.segundo_nombre,
    e.primer_apellido,
    e.segundo_apellido,
    e.dni,
    e.id_direccion,
    e.referencia,
    e.id_colonia,
    e.nombre_colonia,
    e.id_ciudad,
    e.ciudad
	FROM v_UsuarioConRol u
	INNER JOIN v_Empleado e ON u.id_empleado = e.id_empleado;