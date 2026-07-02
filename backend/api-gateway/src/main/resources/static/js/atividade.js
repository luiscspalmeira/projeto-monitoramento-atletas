async function salvarAtividade() {

  const body = {

    atletaId: Number(
      localStorage.getItem("atletaId")
    ),

    modalidade:
      document.getElementById("modalidade").value,

    distancia: Number(
      document.getElementById("distancia").value
    ),

    tempo: Number(
      document.getElementById("tempo").value
    ),

    data:
      document.getElementById("data").value

  };

  try {

    await API.post("/activities", body);

    alert("Atividade salva com sucesso.");

    document.getElementById("distancia").value = "";
    document.getElementById("tempo").value = "";
    document.getElementById("data").value = "";

  } catch (erro) {

    console.error(erro);

    alert("Erro ao salvar atividade.");

  }

}



