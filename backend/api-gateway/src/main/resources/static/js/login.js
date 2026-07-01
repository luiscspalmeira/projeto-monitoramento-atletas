document
    .getElementById("btnEntrar")
    .addEventListener("click", entrar);

async function entrar() {

    const email =
        document.getElementById("email").value.trim();

    const senha =
        document.getElementById("senha").value.trim();

    if (email === "" || senha === "") {

        alert("Preencha todos os campos.");
        return;

    }

    try {

        const usuario = await API.post(

            "/users/login",

            {
                email: email,
                senha: senha
            }

        );

        // Dados da sessão
        localStorage.setItem("usuarioId", usuario.id);

        // Compatibilidade com páginas antigas
        localStorage.setItem("atletaId", usuario.id);

        localStorage.setItem("usuarioNome", usuario.nome);

        localStorage.setItem("usuarioEmail", usuario.email);

        localStorage.setItem("usuarioPerfil", usuario.perfil);

        localStorage.setItem("usuarioPcd", usuario.pcd);

        // Redirecionamento conforme o perfil
        if (usuario.perfil === "INSTRUTOR") {

            window.location.href = "painel-instrutor.html";

        } else {

            window.location.href = "dashboard.html";

        }

    }
    catch (erro) {

        console.error(erro);

        alert("E-mail ou senha inválidos.");

    }

}