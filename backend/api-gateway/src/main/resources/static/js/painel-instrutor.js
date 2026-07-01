window.onload = carregarAtletas;

async function carregarAtletas() {

    try {

        const atletas = await API.get("/users");

        const tbody =
            document.querySelector("#tabelaAtletas tbody");

        tbody.innerHTML = "";

        atletas.forEach(function (atleta) {

            const linha = document.createElement("tr");

            linha.innerHTML = `
                <td>${atleta.id}</td>
                <td>${atleta.nome}</td>
                <td>${atleta.email}</td>
                <td>${atleta.pcd ? "Sim" : "Não"}</td>

                <td>

                    <button onclick="abrirDashboard(${atleta.id})">
                        Dashboard
                    </button>

                    <button onclick="abrirHistorico(${atleta.id})">
                        Histórico
                    </button>

                </td>
            `;

            tbody.appendChild(linha);

        });

    } catch (erro) {

        console.error(erro);

        alert("Erro ao carregar atletas.");

    }

}

function abrirDashboard(id) {

    localStorage.setItem("atletaSelecionado", id);

    window.location.href = "dashboard.html";

}

function abrirHistorico(id) {

    localStorage.setItem("atletaSelecionado", id);

    window.location.href = "historico.html";

}

function sair() {

    localStorage.removeItem("atletaSelecionado");
    localStorage.removeItem("atletaId");
    localStorage.removeItem("usuarioEmail");
    localStorage.removeItem("usuarioTipo");

    window.location.href = "login.html";

}