// ======================================================
// DASHBOARD DO ATLETA
// ======================================================

// ------------------------------
// Variáveis Globais
// ------------------------------

const atletaId =
    localStorage.getItem("atletaSelecionado") ||
    localStorage.getItem("atletaId");

const email =
    localStorage.getItem("usuarioEmail");

let graficoPizza = null;
let graficoSemanas = null;


// ------------------------------
// Inicialização
// ------------------------------

window.onload = inicializarDashboard;

// ------------------------------
// Inicializa toda a página
// ------------------------------

async function inicializarDashboard() {

    if (!atletaId) {

        alert("Sessão inválida.");

        window.location = "login.html";

        return;

    }

    document.getElementById("usuarioLogado").innerHTML =
        "Usuário: " + email;

    try {

        // Carrega tudo em paralelo
        const [

            resumoSemanal,
            resumoMensal,
            modalidades,
            atividades

        ] = await Promise.all([

            API.get("/dashboard/semanal/" + atletaId),

            API.get("/dashboard/mensal/" + atletaId),

            API.get("/dashboard/modalidades/" + atletaId),

            API.get("/activities/atleta/" + atletaId)

        ]);

        preencherIndicadores(atividades);

        preencherResumoSemanal(resumoSemanal);

        preencherResumoMensal(resumoMensal);

        criarGraficoPizza(modalidades);

        criarGraficoSemanas(atividades);

        preencherUltimosTreinos(atividades);

    }
    catch (erro) {

        console.error(erro);

        alert("Erro ao carregar dashboard.");

    }

}

// ======================================================
// INDICADORES
// ======================================================

function preencherIndicadores(atividades) {

    const totalTreinos =
        atividades.length;

    const distanciaTotal =
        atividades.reduce(

            (total, atividade) =>

                total + atividade.distancia,

            0

        );

    const tempoTotal =
        atividades.reduce(

            (total, atividade) =>

                total + atividade.tempo,

            0

        );

    const mediaTreino =

        totalTreinos > 0

            ? distanciaTotal / totalTreinos

            : 0;

    document.getElementById("totalTreinos").innerHTML =
        totalTreinos;

    document.getElementById("distanciaTotal").innerHTML =
        distanciaTotal.toFixed(1) + " km";

    document.getElementById("tempoTotal").innerHTML =
        tempoTotal + " min";

    document.getElementById("mediaTreino").innerHTML =
        mediaTreino.toFixed(1) + " km";

}

// ======================================================
// RESUMO SEMANAL
// ======================================================

function preencherResumoSemanal(resumo) {

    document.getElementById("resumoSemanal").innerHTML =

        `

        <p>

            <strong>Total de treinos:</strong>

            ${resumo.quantidadeTreinos}

        </p>

        <p>

            <strong>Distância total:</strong>

            ${resumo.distanciaTotal.toFixed(1)} km

        </p>

        <p>

            <strong>Tempo total:</strong>

            ${resumo.tempoTotal} min

        </p>

        `;

}

// ======================================================
// RESUMO MENSAL
// ======================================================

function preencherResumoMensal(resumo) {

    document.getElementById("resumoMensal").innerHTML =

        `

        <p>

            <strong>Total de treinos:</strong>

            ${resumo.quantidadeTreinos}

        </p>

        <p>

            <strong>Distância total:</strong>

            ${resumo.distanciaTotal.toFixed(1)} km

        </p>

        <p>

            <strong>Tempo total:</strong>

            ${resumo.tempoTotal} min

        </p>

        `;

}

// ======================================================
// GRÁFICO DE PIZZA - MODALIDADES
// ======================================================

function criarGraficoPizza(modalidades) {

    if (graficoPizza != null) {

        graficoPizza.destroy();

    }

    const labels =
        modalidades.map(item => item.modalidade);

    const valores =
        modalidades.map(item => item.quantidade);

    graficoPizza = new Chart(

        document.getElementById("graficoPizza"),

        {

            type: "pie",

            data: {

                labels: labels,

                datasets: [

                    {

                        label: "Modalidades",

                        data: valores,

                        backgroundColor: [

                            "#1976D2",

                            "#43A047",

                            "#FB8C00"

                        ]

                    }

                ]

            },

            options: {

                responsive: true,

                plugins: {

                    legend: {

                        position: "bottom"

                    }

                }

            }

        }

    );

}

// ======================================================
// GRÁFICO DAS ÚLTIMAS QUATRO SEMANAS
// ======================================================

function criarGraficoSemanas(atividades) {

    if (graficoSemanas != null) {

        graficoSemanas.destroy();

    }

    const semanas = {

        "Semana 1": 0,

        "Semana 2": 0,

        "Semana 3": 0,

        "Semana 4": 0

    };

    const hoje = new Date();

    atividades.forEach(atividade => {

        const dataTreino =
            new Date(atividade.data);

        const diferencaDias =

            Math.floor(

                (hoje - dataTreino)

                / (1000 * 60 * 60 * 24)

            );

        if (diferencaDias >= 0 &&
            diferencaDias <= 28) {

            const indiceSemana =

                Math.floor(diferencaDias / 7);

            switch (indiceSemana) {

                case 0:

                    semanas["Semana 4"]++;

                    break;

                case 1:

                    semanas["Semana 3"]++;

                    break;

                case 2:

                    semanas["Semana 2"]++;

                    break;

                case 3:

                    semanas["Semana 1"]++;

                    break;

            }

        }

    });

    graficoSemanas = new Chart(

        document.getElementById("graficoBarras"),

        {

            type: "bar",

            data: {

                labels: Object.keys(semanas),

                datasets: [

                    {

                        label: "Treinos",

                        data: Object.values(semanas),

                        backgroundColor: "#1976D2"

                    }

                ]

            },

            options: {

                responsive: true,

                scales: {

                    y: {

                        beginAtZero: true,

                        ticks: {

                            precision: 0

                        }

                    }

                },

                plugins: {

                    legend: {

                        display: false

                    }

                }

            }

        }

    );

}

// ======================================================
// ÚLTIMOS TREINOS
// ======================================================

function preencherUltimosTreinos(atividades) {

    const tbody =
        document.querySelector("#tabelaUltimosTreinos tbody");

    if (!tbody)
        return;

    tbody.innerHTML = "";

    const ultimosTreinos = atividades

        .slice()

        .sort(

            (a, b) =>

                new Date(b.data) -

                new Date(a.data)

        )

        .slice(0, 5);

    if (ultimosTreinos.length === 0) {

        tbody.innerHTML = `

            <tr>

                <td colspan="4">

                    Nenhum treino encontrado.

                </td>

            </tr>

        `;

        return;

    }

    ultimosTreinos.forEach(atividade => {

        tbody.innerHTML += `

            <tr>

                <td>

                    ${formatarData(atividade.data)}

                </td>

                <td>

                    ${atividade.modalidade}

                </td>

                <td>

                    ${atividade.distancia.toFixed(1)} km

                </td>

                <td>

                    ${atividade.tempo} min

                </td>

            </tr>

        `;

    });

}

// ======================================================
// FUNÇÕES AUXILIARES
// ======================================================

function formatarData(dataISO) {

    const data = new Date(dataISO);

    return data.toLocaleDateString(

        "pt-BR",

        {

            timeZone: "UTC"

        }

    );

}

// ======================================================
// LOGOUT
// ======================================================

function logout() {

    localStorage.clear();

    window.location = "login.html";

}