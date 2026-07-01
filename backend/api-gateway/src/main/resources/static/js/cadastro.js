async function salvarAtleta() {

    const nome = document.getElementById("nome").value.trim();
    const email = document.getElementById("email").value.trim();
    const senha = document.getElementById("senha").value.trim();
    const pcd = document.getElementById("pcd").value === "true";

    if (nome === "" || email === "" || senha === "") {

        alert("Preencha todos os campos.");
        return;

    }

    try {

        await API.post("/users", {

            nome: nome,
            email: email,
            senha: senha,
            perfil: "ATLETA",
            pcd: pcd

        });

        alert("Atleta cadastrado com sucesso!");

        window.location.href = "login.html";

    } catch (erro) {

        alert("Erro ao cadastrar atleta.");

        console.error(erro);

    }

}



async function salvarInstrutor() {

    const nome = document.getElementById("nome").value.trim();
    const email = document.getElementById("email").value.trim();
    const senha = document.getElementById("senha").value.trim();

    if (nome === "" || email === "" || senha === "") {

        alert("Preencha todos os campos.");
        return;

    }

    try {

        await API.post("/users", {

            nome: nome,
            email: email,
            senha: senha,
            perfil: "INSTRUTOR",
            pcd: false

        });

        alert("Instrutor cadastrado com sucesso!");

        window.location.href = "login.html";

    } catch (erro) {

        alert("Erro ao cadastrar instrutor.");

        console.error(erro);

    }

}