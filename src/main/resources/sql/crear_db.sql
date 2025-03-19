CREATE DATABASE matasanos;

USE matasanos;

CREATE TABLE Sucursal(
	id_sucursal INT IDENTITY (1,1) PRIMARY KEY,
	nombre_sucursal VARCHAR (200) NOT NULL,
	num_establecimiento VARCHAR (3) NOT NULL
);

CREATE TABLE Funcion(
	id_funcion INT IDENTITY (1,1) PRIMARY KEY,
	descripcion VARCHAR (500)
);

CREATE TABLE Permiso(
	id_permiso INT IDENTITY (1,1) PRIMARY KEY,
	descripcion VARCHAR (400)
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
	direccion TEXT NOT NULL
);

CREATE TABLE FacturacionSAR(
	id_factura_sar INT IDENTITY (1,1) PRIMARY KEY,
	fecha_vigencia DATE NOT NULL,
	rango_inicio INT NOT NULL,
	rango_fin INT NOT NULL,
	vigente BIT NOT NULL,
	cai VARCHAR (44) UNIQUE NOT NULL
);

CREATE TABLE Descuento(
	id_descuento INT IDENTITY (1,1) PRIMARY KEY,
	descuento_nombre VARCHAR (200),
	porcentaje DECIMAL,
	descripcion text
);

CREATE TABLE Rol(
	id_rol INT IDENTITY(1,1) PRIMARY KEY,
	nombre_rol VARCHAR (300) NOT NULL
);

CREATE TABLE Ciudad(
	id_ciudad INT IDENTITY(1,1) PRIMARY KEY,
	ciudad VARCHAR (200) NOT NULL
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

CREATE TABLE RolPermisos(
	id_rol_permiso INT IDENTITY(1,1) PRIMARY KEY,
	id_rol INT,
	id_permiso INT NOT NULL,
	FOREIGN KEY (id_rol) REFERENCES Rol(id_rol),
	FOREIGN KEY (id_permiso) REFERENCES Permiso(id_permiso)
);

CREATE TABLE Categoria(
	id_categoria INT IDENTITY(1,1) PRIMARY KEY,
	nombre_categoria VARCHAR (200) NOT NULL,
	id_departamento INT NOT NULL,
	FOREIGN KEY (id_departamento) REFERENCES Departamento(id_departamento)
);

CREATE TABLE Direcciones(
	id_direccion INT IDENTITY(1,1) PRIMARY KEY,
	colonia VARCHAR (200) NOT NULL,
	direccion VARCHAR (500) NOT NULL,
	id_ciudad INT NOT NULL
	FOREIGN KEY (id_ciudad) REFERENCES Ciudad(id_ciudad)
);

CREATE TABLE Usuario(
	id_usuario INT IDENTITY(1,1) PRIMARY KEY,
	usuario VARCHAR (100) UNIQUE NOT NULL,
	contrasena VARCHAR (300) NOT NULL,
	fecha_creacion DATE NOT NULL,
	fecha_modificacion DATE,
	id_rol INT NOT NULL,
	id_usuario_creacion INT,
	id_usuario_modificacion INT,
	FOREIGN KEY (id_rol) REFERENCES Rol(id_rol),
	FOREIGN KEY (id_usuario_creacion) REFERENCES Usuario(id_usuario),
	FOREIGN KEY (id_usuario_modificacion) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Cliente(
	id_cliente INT IDENTITY(1,1) PRIMARY KEY,
	primer_nombre VARCHAR (100) NOT NULL,
	segundo_nombre VARCHAR (100),
	primer_apellido VARCHAR (100) NOT NULL,
	segundo_apellido VARCHAR (100) NOT NULL,
	telefono VARCHAR (15),
	correo VARCHAR (250),
	rtn VARCHAR (20),
	cedula VARCHAR (15) UNIQUE,
	fecha_creacion DATE,
	fecha_modificacion DATE,
	id_direccion INT NOT NULL,
	id_usuario_creacion INT,
	id_usuario_modificacion INT,
	FOREIGN KEY (id_direccion) REFERENCES Direcciones(id_direccion),
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
	total DECIMAL NOT NULL,
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
	descripcion TEXT NOT NULL,
	precio_venta DECIMAL NOT NULL,
	inventario INT NOT NULL,
	fecha_vencimiento DATE NOT NULL,
	venta_libre BIT NOT NULL,
	precio_descuento DECIMAL,
	fecha_creacion DATE,
	fecha_modificacion DATE,
	id_categoria INT NOT NULL,
	id_proveedor INT NOT NULL,
	id_usuario_creacion INT,
	id_usuario_modificacion INT,
	FOREIGN KEY (id_categoria) REFERENCES Categoria(id_categoria),
	FOREIGN KEY (id_proveedor) REFERENCES Proveedor(id_proveedor),
	FOREIGN KEY (id_usuario_creacion) REFERENCES Usuario(id_usuario),
	FOREIGN KEY (id_usuario_modificacion) REFERENCES Usuario(id_usuario),
);

CREATE TABLE FacturaProducto(
	id_factura_producto INT IDENTITY(1,1) PRIMARY KEY,
	cantidad INT NOT NULL,
	precio_unitario DECIMAL NOT NULL,
	subtotal DECIMAL,
	id_factura INT NOT NULL,
	id_producto INT NOT NULL,
	FOREIGN KEY (id_factura) REFERENCES Factura(id_factura),
	FOREIGN KEY (id_producto) REFERENCES Producto(id_producto)
);

CREATE TABLE Receta(
	id_receta INT IDENTITY(1,1) PRIMARY KEY,
	nombre_medico VARCHAR(250) NOT NULL,
	fecha DATE,
	descripcion TEXT,
	indicaciones TEXT,
	id_cliente INT,
	FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente)
);

CREATE TABLE RecetaProducto(
	id_receta_producto INT IDENTITY(1,1) PRIMARY KEY,
	cantidad INT NOT NULL,
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
	FOREIGN KEY (id_producto) REFERENCES Producto(id_producto),
);

CREATE TABLE Empleado(
	id_empleado INT IDENTITY(1,1) PRIMARY KEY,
	primer_nombre VARCHAR (100) NOT NULL,
	segundo_nombre VARCHAR (100),
	primer_apellido VARCHAR (100) NOT NULL,
	segundo_apellido VARCHAR (100) NOT NULL,
	telefono VARCHAR (15),
	correo VARCHAR (250),
	salario DECIMAL,
	fecha_contratacion DATE NOT NULL,
	fecha_baja DATE,
	activo BIT NOT NULL,
	fecha_modificacion DATE,
	id_direccion INT NOT NULL,
	id_cargo INT NOT NULL,
	id_usuario INT NOT NULL,
	id_sucursal INT NOT NULL,
	id_usuario_creacion INT,
	id_usuario_modificacion INT,
	FOREIGN KEY (id_direccion) REFERENCES Direcciones(id_direccion),
	FOREIGN KEY (id_cargo) REFERENCES Cargo(id_cargo),
	FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
	FOREIGN KEY (id_sucursal) REFERENCES Sucursal(id_sucursal),
	FOREIGN KEY (id_usuario_creacion) REFERENCES Usuario(id_usuario),
	FOREIGN KEY (id_usuario_modificacion) REFERENCES Usuario(id_usuario)
);

CREATE TABLE SucursalProducto(
	id_sucursal_producto INT IDENTITY(1,1) PRIMARY KEY,
	inventario_sucursal INT NOT NULL,
	id_producto INT NOT NULL,
	id_sucursal INT NOT NULL,
	FOREIGN KEY (id_producto) REFERENCES Producto(id_producto),
	FOREIGN KEY (id_sucursal) REFERENCES Sucursal(id_sucursal)
);

--VISTAS

CREATE VIEW v_UsuarioConPermiso AS
    SELECT u.*, r.nombre_rol, p.* FROM Usuario u
    LEFT JOIN Rol r ON r.id_rol = u.id_rol
    LEFT JOIN RolPermisos rp ON rp.id_rol = r.id_rol
    LEFT JOIN Permiso p ON p.id_permiso = rp.id_permiso;

CREATE VIEW v_UsuarioConRol AS
    SELECT u.*, r.nombre_rol FROM Usuario u
    LEFT JOIN Rol r ON r.id_rol = u.id_rol:


CREATE VIEW v_ProductoSucursal AS
	SELECT p.*, nombre_categoria, nombre_departamento, s.id_sucursal, nombre_sucursal FROM Producto p
	INNER JOIN Categoria c ON c.id_categoria = p.id_categoria
	INNER JOIN Departamento d ON d.id_departamento = c.id_departamento
	INNER JOIN SucursalProducto sp ON sp.id_producto = p.id_producto
	INNER JOIN Sucursal s ON s.id_sucursal = sp.id_sucursal;