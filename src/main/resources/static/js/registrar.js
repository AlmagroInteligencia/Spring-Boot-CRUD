// Call the dataTables jQuery plugin
$(document).ready(function() {
    // on Ready
  });
  
  async function registrarUsuario() {
      
    let datos = {};
    datos.nombre = document.getElementById('txtNombre').value;
    datos.apellido = document.getElementById('txtApellido').value;
    datos.email = document.getElementById('txtEmail').value;
    datos.password = document.getElementById('txtPassword').value;
    
    let repetirPassword = document.getElementById('txtRepeatPassword').value;

    if (datos.password != repetirPassword) {
        alert("Guarda! tenés que poner dos veces la misma password!");
        return;
    }
    
    const request = await fetch('api/usuarios', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-type': 'application/json'
      },
        body: JSON.stringify(datos)
    });

    alert("Bien pibe, ya tenés cuenta!");
    window.location.href = 'login.html';
    
    //const usuarios = await request.json();
  
  }
  
