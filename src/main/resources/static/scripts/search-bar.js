const searchBar = document.getElementById('search-bar');
const idSucursal = 1 //que se cargue del localstorage
const resultsContainer = document.getElementById('results');
let productos = [];

document.addEventListener("DOMContentLoaded", () => {
    resultsContainer.style.display = "none";
});

searchBar.addEventListener('keyup', async (e) => {
    const searchString = e.target.value.toLowerCase();
    if (searchString.length > 2) {
            const res = await fetch(`/api/productos/busqueda_simplificada?filtro=${searchString}`);
            productos = await res.json();
            displayProducts(productos);
            resultsContainer.style.display = "block";
            console.log(productos);
    } else {
        resultsContainer.style.display = "none";
    }
});

const displayProducts = (productos) => {
    if (!Array.isArray(productos)) {
        console.error("Respuesta inesperada:", productos);
        productos = [];
    }

    const ul = resultsContainer.querySelector("ul");
    ul.innerHTML = '';

    productos.forEach((p) => {
        const li = document.createElement("li");
        li.className = "list-group-item";
        li.textContent = p.nombreProducto;

        li.addEventListener("click", () => {
            window.location.href = `/detalles?idProducto=${p.idProducto}&idSucursal=${idSucursal}`;
        });

        ul.appendChild(li);
    });
};