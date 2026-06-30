async function salvarAtividade(){

    const body = {

        atletaId:
          Number(
          document.getElementById("atletaId").value),

        modalidade:
          document.getElementById("modalidade").value,

        distancia:
          Number(
          document.getElementById("distancia").value),

        duracaoMinutos:
          Number(
          document.getElementById("tempo").value),

        dataAtividade:
          document.getElementById("data").value
    };

    await fetch(ACTIVITY_API,{

        method:"POST",

        headers:{
          "Content-Type":"application/json"
        },

        body:JSON.stringify(body)
    });

    alert("Atividade salva");
}