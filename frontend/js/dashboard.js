let pizzaChart;
let barraChart;

async function carregarDashboard(){

    const atletaId =
      document.getElementById("atletaId").value;

    const semanal =
      await fetch(
      DASHBOARD_API +
      "/semanal/" +
      atletaId);

    const semanalJson =
      await semanal.json();

    document.getElementById("resumo")
      .innerHTML =

      "Distância: "
      + semanalJson.distanciaTotal

      + " km | Tempo: "

      + semanalJson.tempoTotal

      + " min";

    const modalidades =
      await fetch(
      DASHBOARD_API +
      "/modalidades/" +
      atletaId);

    const modalJson =
      await modalidades.json();

    if(pizzaChart){
        pizzaChart.destroy();
    }

    if(barraChart){
        barraChart.destroy();
    }

    pizzaChart = new Chart(

      document.getElementById(
      "graficoPizza"),

      {

        type:"pie",

        data:{

          labels:[
            "Corrida",
            "Bicicleta",
            "Natação"
          ],

          datasets:[{

            data:[

              modalJson.corrida,

              modalJson.bicicleta,

              modalJson.natacao
            ]
          }]
        }
      }
    );

    barraChart = new Chart(

      document.getElementById(
      "graficoBarra"),

      {

        type:"bar",

        data:{

          labels:[
            "Corrida",
            "Bicicleta",
            "Natação"
          ],

          datasets:[{

            data:[

              modalJson.corrida,

              modalJson.bicicleta,

              modalJson.natacao
            ]
          }]
        }
      }
    );
}