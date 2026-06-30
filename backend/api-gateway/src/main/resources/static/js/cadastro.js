async function salvarAtleta(){

    const body = {

        nome:
          document.getElementById("nome").value,

        email:
          document.getElementById("email").value,

        senha:
          document.getElementById("senha").value,

        tipoUsuario:"ATLETA",

        pcd:
          document.getElementById("pcd").value
          === "true"
    };

    await fetch(USER_API,{

        method:"POST",

        headers:{
            "Content-Type":"application/json"
        },

        body:JSON.stringify(body)
    });

    alert("Atleta cadastrado");
}

async function salvarInstrutor(){

    const body = {

        nome:
          document.getElementById("nome").value,

        email:
          document.getElementById("email").value,

        senha:
          document.getElementById("senha").value,

        tipoUsuario:"INSTRUTOR",

        pcd:false
    };

    await fetch(USER_API,{

        method:"POST",

        headers:{
            "Content-Type":"application/json"
        },

        body:JSON.stringify(body)
    });

    alert("Instrutor cadastrado");
}