

const atletaId =
    localStorage.getItem("atletaSelecionado") ||
    localStorage.getItem("atletaId");

const email =
    localStorage.getItem("usuarioEmail");

document.getElementById("usuarioLogado").innerHTML =
    "Usuário: " + (email ?? "Não informado");

let graficoPizza = null;
let graficoBarras = null;

window.onload = carregarDashboard;

async function carregarDashboard() {

    try {

        await carregarIndicadores();

        await carregarResumoSemanal();

        await carregarResumoMensal();

        await carregarModalidades();

    } catch (erro) {

        console.error(erro);

        alert("Erro ao carregar o dashboard.");

    }

}

async function carregarResumoSemanal() {

    const resumo =
        await API.get("/dashboard/semanal/" + atletaId);

    document.getElementById("resumoSemanal").innerHTML =

        `
        <p><strong>Total de treinos:</strong> ${resumo.quantidadeTreinos}</p>

        <p><strong>Distância total:</strong> ${resumo.distanciaTotal.toFixed(1)} km</p>

        <p><strong>Tempo total:</strong> ${resumo.tempoTotal} min</p>
        `;

}

async function carregarResumoMensal() {

    const resumo =
        await API.get("/dashboard/mensal/" + atletaId);

    document.getElementById("resumoMensal").innerHTML =

        `
        <p><strong>Total de treinos:</strong> ${resumo.quantidadeTreinos}</p>

        <p><strong>Distância total:</strong> ${resumo.distanciaTotal.toFixed(1)} km</p>

        <p><strong>Tempo total:</strong> ${resumo.tempoTotal} min</p>
        `;

}

async function carregarModalidades() {

    const modalidades =
        await API.get("/dashboard/modalidades/" + atletaId);

    criarGraficoPizza(modalidades);

    criarGraficoBarras(modalidades);

}

async function carregarIndicadores() {

    const atividades =
        await API.get("/activities/atleta/" + atletaId);

    const totalTreinos = atividades.length;

    const distanciaTotal =
        atividades.reduce(
            (soma, atividade) => soma + atividade.distancia,
            0
        );

    const tempoTotal =
        atividades.reduce(
            (soma, atividade) => soma + atividade.tempo,
            0
        );

    const media =
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
        media.toFixed(1) + " km";
}

function criarGraficoPizza(dados) {

    if (graficoPizza != null) {

        graficoPizza.destroy();

    }

    graficoPizza = new Chart(

        document.getElementById("graficoPizza"),

        {

            type: "pie",

            data: {

                labels:

                    dados.map(item => item.modalidade),

                datasets: [

                    {

                        label: "Modalidades",

                        data:

                            dados.map(item => item.quantidade),

                        backgroundColor: [

                            "#42A5F5",
                            "#66BB6A",
                            "#FFA726"

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

function criarGraficoBarras(dados) {

    if (graficoBarras != null) {

        graficoBarras.destroy();

    }

    graficoBarras = new Chart(

        document.getElementById("graficoBarras"),

        {

            type: "bar",

            data: {

                labels:

                    dados.map(item => item.modalidade),

                datasets: [

                    {

                        label: "Quantidade de atividades",

                        data:

                            dados.map(item => item.quantidade),

                        backgroundColor: [

                            "#42A5F5",
                            "#66BB6A",
                            "#FFA726"

                        ]

                    }

                ]

            },

            options: {

                responsive: true,

                plugins: {

                    legend: {

                        display: false

                    }

                },

                scales: {

                    y: {

                        beginAtZero: true,

                        ticks: {

                            precision: 0

                        }

                    }

                }

            }

        }

    );

}

function logout() {

    localStorage.clear();

    window.location = "login.html";

}