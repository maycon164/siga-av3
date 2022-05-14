let scripts =[{src: "./script/controller/notas.controller.js", loaded: false}];

const buttonsElementEl = Array.from(document.getElementsByTagName("a"));
const btnRegistrarNotasEl = document.getElementById("btn-registrar-notas");

btnRegistrarNotasEl.addEventListener('click', () => {
    fetch("./views/notas.html").then(html => html.text()).then(content => {
        document.getElementById("main-section").innerHTML = content
    });
    loadScripts()

});

function loadScripts(){
    
    scripts.forEach(script => {

        if(!script.loaded){
            script.loaded = true;            
            let scriptEl = document.createElement('script');
            scriptEl.src = script.src;
            document.body.appendChild(scriptEl);
        }
        
    });

}


buttonsElementEl.forEach(link => {
    link.addEventListener("click", () => {
        buttonsElementEl.forEach(a => {
            a.classList.remove("selected")
        });
        link.classList.add("selected");
    })
});

