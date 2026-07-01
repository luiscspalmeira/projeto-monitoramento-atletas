window.onload = carregarPainel;

/*
 * Carrega os atletas vinculados ao instrutor logado.
 */
async function carregarPainel() {

    try {

        const instrutorId = localStorage.getItem("usuarioId");

        if (!instrutorId) {

            alert("Sessão inválida.");

            window.location = "login.html";

            return;

        }

        const atletas = await API.get(
            "/instrutores-atletas/instrutor/" +
            instrutorId +
            "/atletas"
        );

        preencherTabela(atletas);

    } catch (erro) {

        console.error(erro);

        alert("Erro ao carregar atletas.");

    }

}

/*
 * Preenche a tabela de atletas.
 */
function preencherTabela(atletas) {

    const tbody = document.getElementById("tblAtletas");

    tbody.innerHTML = "";

    if (atletas.length === 0) {

        tbody.innerHTML = `
            <tr>
                <td colspan="5">
                    Nenhum atleta vinculado.
                </td>
            </tr>
        `;

        return;

    }

    atletas.forEach(atleta => {

        tbody.innerHTML += `

            <tr>

                <td>${atleta.id}</td>

                <td>${atleta.nome}</td>

                <td>${atleta.email}</td>

                <td>${atleta.pcd ? "Sim" : "Não"}</td>

                <td>

                    <button
                        onclick="selecionarAtleta(${atleta.id})">

                        Selecionar

                    </button>

                </td>

            </tr>

        `;

    });

}

/*
 * Busca o histórico do atleta selecionado.
 */
async function selecionarAtleta(atletaId) {

    try {

        const atividades = await API.get(
            "/activities/atleta/" + atletaId
        );

        preencherHistorico(atividades);

        atualizarIndicadores(atividades);

    } catch (erro) {

        console.error(erro);

        alert("Erro ao carregar histórico.");

    }

}

/*
 * Preenche a tabela de histórico.
 */
function preencherHistorico(lista) {

    const tbody = document.getElementById("tblHistorico");

    tbody.innerHTML = "";

    if (lista.length === 0) {

        tbody.innerHTML = `
            <tr>
                <td colspan="4">
                    Nenhuma atividade cadastrada.
                </td>
            </tr>
        `;

        return;

    }

    lista.forEach(a => {

        tbody.innerHTML += `

            <tr>

                <td>${a.data}</td>

                <td>${a.modalidade}</td>

                <td>${a.distancia}</td>

                <td>${a.tempo}</td>

            </tr>

        `;

    });

}

/*
 * Calcula os indicadores.
 */
function atualizarIndicadores(lista) {

    const totalTreinos = lista.length;

    const distancia = lista.reduce(

        (soma, a) => soma + a.distancia,

        0

    );

    const tempo = lista.reduce(

        (soma, a) => soma + a.tempo,

        0

    );

    const modalidades = {};

    lista.forEach(a => {

        modalidades[a.modalidade] =
            (modalidades[a.modalidade] || 0) + 1;

    });

    let modalidadeFavorita = "-";

    let maior = 0;

    for (const modalidade in modalidades) {

        if (modalidades[modalidade] > maior) {

            maior = modalidades[modalidade];

            modalidadeFavorita = modalidade;

        }

    }

    document.getElementById("totalTreinos").innerHTML =
        totalTreinos;

    document.getElementById("totalDistancia").innerHTML =
        distancia.toFixed(1) + " km";

    document.getElementById("totalTempo").innerHTML =
        tempo + " min";

    document.getElementById("modalidadeFavorita").innerHTML =
        modalidadeFavorita;

}

/*
 * Encerra a sessão.
 */
function sair() {

    localStorage.clear();

    window.location = "login.html";

}