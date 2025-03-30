window.addEventListener("DOMContentLoaded", async () => {

    //muestra los resultados en
    const searchBar = document.getElementById("search-bar");
    const resultsContainer = document.getElementById("results");
    resultsContainer.innerHTML = "";

    const displayResults = (products) => {
        resultsContainer.innerHTML = "";

        if (products.length > 0) {
            const ul = document.createElement('ul');
            ul.classList.add('list-group');

            products.forEach(p => {
                const li = document.createElement('li');
                li.classList.add('list-group-item');
                li.textContent = p.nombreProducto;

                //evento click para cuando se selecciona un producto
                li.addEventListener('click', () => {
                    console.log(`Producto seleccionado: ${p.nombreProducto}`);
                    searchBar.value = p.nombreProducto;
                    resultsContainer.innerHTML = '';
                });

                ul.appendChild(li);
            });

            resultsContainer.appendChild(ul);
        } else {
            resultsContainer.innerHTML = "<p>No se encontraron productos</p>";
        }
    }

    //obtiene los productos
    const searchProducts = async (searchString) => {
        if (searchString.length < 3) {
            resultsContainer.innerHTML = '';
            return[];
        }

        try {
            const res = await fetch(`/api/sucursal-productos/busqueda?filtro=${searchString}&idSucursal=2`);
            const products = await res.json();
            return products;
        } catch (error) {
            console.error("Error al obtener producto ", error);
            return [];
        }
    }

    //filtra productos mientras se escribe
    searchBar.addEventListener("keyup", async (i) => {
        const searchString = i.target.value.toLowerCase();

        if (searchString) {
            const filteredProducts = await searchProducts(searchString);
            displayResults(filteredProducts);
        } else {
            resultsContainer.innerHTML = "";
        }
    })
})