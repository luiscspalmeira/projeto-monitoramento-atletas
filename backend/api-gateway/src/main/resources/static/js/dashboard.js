const atletaId = localStorage.getItem("atletaId");

const email = localStorage.getItem("usuarioEmail");

document.getElementById("usuarioLogado").innerHTML =
    "Usuário: " + (email ?? "Não informado");

window.onload = carregarDashboard;

async function carregarDashboard() {

    try {

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
        JSON.stringify(resumo, null, 2);

}

async function carregarResumoMensal() {

    const resumo =
        await API.get("/dashboard/mensal/" + atletaId);

    document.getElementById("resumoMensal").innerHTML =
        JSON.stringify(resumo, null, 2);

}

async function carregarModalidades() {

    const modalidades =
        await API.get("/dashboard/modalidades/" + atletaId);

    criarGraficoPizza(modalidades);

    criarGraficoBarras(modalidades);

}

function criarGraficoPizza(dados) {

    new Chart(document.getElementById("graficoPizza"), {

        type: "pie",

        data: {

            labels: Object.keys(dados),

            datasets: [{

                data: Object.values(dados)

            }]

        }

    });

}

function criarGraficoBarras(dados) {

    new Chart(document.getElementById("graficoBarras"), {

        type: "bar",

        data: {

            labels: Object.keys(dados),

            datasets: [{

                label: "Quantidade",

                data: Object.values(dados)

            }]

        }

    });

}

function logout() {

    localStorage.clear();

    window.location = "login.html";

}