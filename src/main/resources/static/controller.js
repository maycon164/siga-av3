const rowsTbody = document.getElementById("rows-tbody");

window.onload = function(){
    fetch('http://localhost:8080/siga/notas/LBD').then(response => response.json())
    .then(notas => {
        notas.forEach(n => {

            let rowElement = createRowNotaTable(n);
            rowsTbody.insertAdjacentHTML("beforeend", rowElement);
        });
    })
}


function createRowNotaTable({ra, nome, codigoDisciplina, siglaDisciplina, codigoAvaliacao, tipoAvaliacao, nota}){
    let rowElement = `
        <td class="px-6 py-4 text-sm text-gray-500">
            ${ra}
        </td>
        <td class="px-6 py-4 text-sm text-gray-500">
            ${nome}
        </td>
        <td class="px-6 py-4 text-sm text-gray-500">
            ${codigoDisciplina}
        </td>
        <td class="px-6 py-4 text-sm text-gray-500">
            ${siglaDisciplina}
        </td>
        <td class="px-6 py-4 text-sm text-gray-500">
            ${codigoAvaliacao}
        </td>
        <td class="px-6 py-4 text-sm text-gray-500">
            ${tipoAvaliacao}
        </td>
        <td class="px-6 py-4 text-sm text-gray-500">
            <input type=number max=10 min=0 value=${nota} class="shadow appearance-none border border-red-500 rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"/>

        </td>
    `
    return rowElement;
}