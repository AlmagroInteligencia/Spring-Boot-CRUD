// Call the dataTables jQuery plugin
$(document).ready(function() {
    // on Ready
  });

  async function iniciarSesion() {

    let datos = {};
    datos.email = document.getElementById('txtEmailLogin').value;
    datos.password = document.getElementById('txtPasswordLogin').value;

    const request = await fetch('api/login', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-type': 'application/json'
      },
        body: JSON.stringify(datos)
    });

    const respuesta = await request.text();

    if (respuesta != "No Pibe, mandaste cualquiera"){
        localStorage.token = respuesta;
        console.log(respuesta);
        alert("Bien pibe, te logueaste!");
        localStorage.email = datos.email;
        window.location.href = 'usuarios.html';
    } else {
        alert("No pibe, mandaste cualquiera! Fijate bien lo que ponés...");
    }

  }

