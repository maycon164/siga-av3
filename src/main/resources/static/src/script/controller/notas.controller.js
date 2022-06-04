const tbodyEl = document.getElementById("rows-tbody");
const btnPesquisarNotasTurmaEl = document.getElementById("btn-pesquisa-turma");
const selectOptionTurmaEl = document.getElementById("select-turma");
const btnUpdateNotasEl = document.getElementById("btn-update-notas");

async function loadTable(turma) {
    tbodyEl.innerText = "";
    let notas = await getNotasByTurma(turma);
    console.log(notas);
    tbodyEl.innerText = "";

    notas.forEach(n => {
        let rowElement = createRowNotaTable(n);
        tbodyEl.insertAdjacentHTML("beforeend", rowElement);
    });

}

function createRowNotaTable({ ra, nome, codigoDisciplina, siglaDisciplina, codigoAvaliacao, tipoAvaliacao, nota }) {
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
        <td class="px-6 py-4 text-sm text-gray-500" data-codigoavaliacao=${codigoAvaliacao}>
            ${codigoAvaliacao}
        </td>
        <td class="px-6 py-4 text-sm text-gray-500">
            ${tipoAvaliacao}
        </td>
        <td class="px-6 py-4 text-sm text-gray-500">
            <input type=number max=10 min=0 name="nota" value=${nota} class="shadow appearance-none border border-red-500 rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"/>
        </td>
    `
    return rowElement;
}

btnPesquisarNotasTurmaEl.addEventListener("click", () => {
    console.log("rONALDO");
    loadTable(getTurma())
});

function getTurma() {
    return selectOptionTurmaEl.options[selectOptionTurmaEl.selectedIndex].value;
}

btnUpdateNotasEl.addEventListener("click", async () => {
    let trs = Array.from(tbodyEl.getElementsByTagName('tr'));
    let listOfUpdateNotaDTO = [];

    trs.forEach(row => {
        let updateNotaDTO = {};

        let tds = Array.from(row.getElementsByTagName("td"));

        tds.forEach(td => {
            if (td.dataset.ra) {
                updateNotaDTO.ra = td.dataset.ra;
            }
            if (td.dataset.codigoavaliacao) {
                updateNotaDTO.codigoAvaliacao = td.dataset.codigoavaliacao;
            }

            if (td.dataset.codigodisciplina) {
                updateNotaDTO.codigoDisciplina = td.dataset.codigodisciplina;
            }

            let inputNota = Array.from(td.getElementsByTagName("input"));

            if (inputNota[0]) {
                updateNotaDTO.nota = inputNota[0].value;
            }

        });

        listOfUpdateNotaDTO.push(updateNotaDTO);
    })
    const result = await updateNotasByTurma(listOfUpdateNotaDTO, getTurma());
    alert(result);
})
