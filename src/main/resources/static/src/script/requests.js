async function updateNotasByTurma(notas, turma){
   const result = await fetch(`http://localhost:8080/siga/notas/${turma}`, 
        {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(notas)
        }
    )

    return await result.text(); 
}

async function getNotasByTurma(turma) {
    const notasTurma = await fetch('http://localhost:8080/siga/notas/' + turma).then(response => response.json())
    return notasTurma
}
