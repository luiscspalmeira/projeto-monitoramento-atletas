window.onload = async function () {

    await carregarUsuarios();

    await carregarVinculos();

};

async function carregarUsuarios() {

    try {

        const usuarios = await API.get("/users");

        const selectInstrutor =
            document.getElementById("instrutor");

        const selectAtleta =
            document.getElementById("atleta");

        selectInstrutor.innerHTML = "";
        selectAtleta.innerHTML = "";

        usuarios
            .filter(usuario => usuario.perfil === "INSTRUTOR")
            .forEach(instrutor => {

                const option =
                    document.createElement("option");

                option.value = instrutor.id;
                option.textContent =
                    `${instrutor.nome} (${instrutor.email})`;

                selectInstrutor.appendChild(option);

            });

        usuarios
            .filter(usuario => usuario.perfil === "ATLETA")
            .forEach(atleta => {

                const option =
                    document.createElement("option");

                option.value = atleta.id;
                option.textContent =
                    `${atleta.nome} (${atleta.email})`;

                selectAtleta.appendChild(option);

            });

        selectInstrutor.onchange = carregarVinculos;

    }
    catch (erro) {

        console.error(erro);

        alert("Erro ao carregar usuários.");

    }

}

async function vincular() {

    const instrutorId =
        Number(document.getElementById("instrutor").value);

    const atletaId =
        Number(document.getElementById("atleta").value);

    try {

        await API.post(

            "/instrutores-atletas",

            {
                instrutorId,
                atletaId
            }

        );

        alert("Atleta vinculado com sucesso!");

        await carregarVinculos();

    }
    catch (erro) {

        console.error(erro);

        alert("Erro ao vincular atleta.");

    }

}

async function carregarVinculos() {

    const instrutorId =
        document.getElementById("instrutor").value;

    if (!instrutorId)
        return;

    try {

        const atletas = await API.get(

            "/instrutores-atletas/instrutor/" +
            instrutorId +
            "/atletas"

        );

        const tbody =
            document.querySelector("#tabelaVinculos tbody");

        tbody.innerHTML = "";

        if (atletas.length === 0) {

            tbody.innerHTML = `
                <tr>
                    <td colspan="4">
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

                </tr>

            `;

        });

    }
    catch (erro) {

        console.error(erro);

        alert("Erro ao carregar vínculos.");

    }

}