let pages =[
    {
        html:"./views/notas.html",
        src: "./script/controller/notas.controller.js", 
        loaded: false,
        section: document.getElementById("section-notas")
    },
    {
        html:"./views/presenca.html",
        src: "./script/controller/presenca.controller.js", 
        loaded: false,
        section: document.getElementById("section-presencas")
    },
    {
        html:"./views/relatorio.html",
        src: "./script/controller/relatorio.controller.js", 
        loaded: false,
        section: document.getElementById("section-relatorios")
    }
];

const buttonsElementEl = Array.from(document.getElementsByTagName("a"));
const btnRegistrarNotasEl = document.getElementById("btn-registrar-notas");
const btnRegistrarFaltasEl = document.getElementById("btn-registrar-faltas");
const btnGerarRelatoriosEl = document.getElementById("btn-gerar-relatorios");

btnRegistrarNotasEl.addEventListener('click', () => {
    loadPage(pages[0])
});

btnRegistrarFaltasEl.addEventListener('click', () => {
    loadPage(pages[1])
});

btnGerarRelatoriosEl.addEventListener('click', () => {
    loadPage(pages[2]);
})

function hidePageSections(){
    pages.forEach(page => {
        console.log(page);
        page.section.style.display = "none"
    }) 
}

function loadPage(page){
    hidePageSections();
    
    if(!page.loaded){
        fetch(page.html).then(html => html.text()).then(content => {
            page.section.innerHTML = content;
            let scriptEl = document.createElement('script');
            scriptEl.src = page.src;
            document.body.appendChild(scriptEl);
        }); 
        page.loaded = true;   
    }

    page.section.style.display = "block";

}


buttonsElementEl.forEach(link => {
    link.addEventListener("click", () => {
        buttonsElementEl.forEach(a => {
            a.classList.remove("selected")
        });
        link.classList.add("selected");
    })
});

