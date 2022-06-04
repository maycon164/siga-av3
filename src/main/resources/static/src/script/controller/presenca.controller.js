const btnSearchFaltasEl = document.getElementById("btn-search-faltas");
const selectTurmaEl = document.getElementById("select-turma-faltas");
const dataEl = document.getElementById("data-faltas");
const rowsTbodyFaltas = document.getElementById("rows-tbody-faltas");
const btnUpdateFaltasEl = document.getElementById("btn-update-faltas");

btnSearchFaltasEl.addEventListener("click", async (event) => {
    const data = dataEl.value;
    const turma = selectTurmaEl.value;
    const faltasTurma = await getFaltasByTurmaAndData(turma, data);
    loadTableFaltas(faltasTurma);
});

function loadTableFaltas(faltas) {

    rowsTbodyFaltas.innerText = "";

    faltas.forEach(falta => {
        let rowFaltaElement = createRowFaltaTable(falta)
        rowsTbodyFaltas.insertAdjacentHTML("beforeend", rowFaltaElement);
    });
}


function createRowFaltaTable(
    { ra, nome, codigoDisciplina,
        siglaDisciplina,
        data, presenca }) {

    let rowElement = `
        <td class="px-6 py-4 text-sm text-gray-500" data-ra=${ra}>
            ${ra}
        </td>
        <td class="px-6 py-4 text-sm text-gray-500">
            ${nome}
        </td>
        <td class="px-6 py-4 text-sm text-gray-500" data-codigodisciplina=${codigoDisciplina}>
            ${codigoDisciplina}
        </td>
        <td class="px-6 py-4 text-sm text-gray-500">
            ${siglaDisciplina}
        </td>
        <td class="px-6 py-4 text-sm text-gray-500" data-date=${data}>
            ${data}
        </td>
        <td class="px-6 py-4 text-sm text-gray-500">
            <input type=number name="presenca" value=${presenca} class="shadow appearance-none border border-red-500 rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"/>
        </td>
    `
    return rowElement;
}


btnUpdateFaltasEl.addEventListener("click", async () => {
    let trs = Array.from(rowsTbodyFaltas.getElementsByTagName('tr'));
    let listOfPresencaDTO = [];

    trs.forEach(row => {
        let updatePresencaDTO = {}
        let tds = Array.from(row.getElementsByTagName("td"));

        tds.forEach(td => {

            if (td.dataset.ra) {
                updatePresencaDTO.ra = td.dataset.ra;
            }

            if (td.dataset.codigodisciplina) {
                updatePresencaDTO.codigoDisciplina = td.dataset.codigodisciplina;
            }

            if (td.dataset.date) {
                updatePresencaDTO.date = td.dataset.date;
            }

            let inputPresenca = Array.from(td.getElementsByTagName("input"));

            if (inputPresenca[0]) {
                updatePresencaDTO.presenca = inputPresenca[0].value;
            }
        })

        listOfPresencaDTO.push(updatePresencaDTO);

    })
    const turma = selectTurmaEl.value;
    const result = await updatePresencasByTurma(listOfPresencaDTO, turma);
    alert(result);

})