window.onload = carregarHistorico;

async function carregarHistorico() {

    const atletaId =
    localStorage.getItem("atletaSelecionado") ||
    localStorage.getItem("atletaId");

    if (!atletaId) {

        alert("Nenhum atleta está logado.");

        window.location.href = "login.html";

        return;
    }

    try {

        const atividades = await API.get(
            "/activities/atleta/" + atletaId
        );

        const tbody =
            document.querySelector("#tabelaHistorico tbody");

        tbody.innerHTML = "";

        atividades.forEach(function (atividade) {

            const linha = document.createElement("tr");

            linha.innerHTML = `

                <td>${atividade.data}</td>

                <td>${atividade.modalidade}</td>

                <td>${atividade.distancia}</td>

                <td>${atividade.tempo}</td>

            `;

            tbody.appendChild(linha);

        });

    } catch (erro) {

        console.error(erro);

        alert("Erro ao carregar histórico.");

    }

}