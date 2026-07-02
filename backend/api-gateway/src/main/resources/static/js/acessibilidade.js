// ==============================
// ACESSIBILIDADE
// ==============================

(function () {

    let tamanhoFonte =
        Number(localStorage.getItem("fonte")) || 16;

    document.addEventListener("DOMContentLoaded", () => {

        document.body.style.fontSize = tamanhoFonte + "px";

        if (localStorage.getItem("altoContraste") === "true") {

            document.body.classList.add("alto-contraste");

        }

    });

    window.aumentarFonte = function () {

        tamanhoFonte += 2;

        document.body.style.fontSize = tamanhoFonte + "px";

        localStorage.setItem("fonte", tamanhoFonte);

    };

    window.diminuirFonte = function () {

        if (tamanhoFonte > 12) {

            tamanhoFonte -= 2;

        }

        document.body.style.fontSize = tamanhoFonte + "px";

        localStorage.setItem("fonte", tamanhoFonte);

    };

    window.alternarContraste = function () {

        document.body.classList.toggle("alto-contraste");

        localStorage.setItem(

            "altoContraste",

            document.body.classList.contains("alto-contraste")

        );

    };

})();