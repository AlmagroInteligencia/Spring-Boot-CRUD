// Call the dataTables jQuery plugin
$(document).ready(function() {
  cargarUsuarios();
  $('#usuarios').DataTable();
  actualizarMailUsuario();
});

function actualizarMailUsuario() {
    document.getElementById('txt-email-usuario').outerHTML = localStorage.email;
}

async function cargarUsuarios() {
    
  const request = await fetch('api/usuarios', {
    method: 'GET',
    headers: getHeaders()
  });
  
  const usuarios = await request.json();

  /*console.log(usuarios);*/

  let listadoHTML = '';
  
  for (let usuario of usuarios) {

    let botonEliminar = '<a href="#" onclick="eliminarUsuario('+usuario.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

    let telefonoTexto = usuario.telefono == null ? '-' : usuario.telefono;

    let usuarioHtml = '<tr><td>'+usuario.id+'</td><td>'+usuario.nombre+' '+usuario.apellido
      +'</td><td>'+usuario.email+'</td><td>'+telefonoTexto
      +'</td><td> ' + botonEliminar + '</td></tr>';

      listadoHTML += usuarioHtml;
  }
  
  //let usuarioHtml = '<tr><td>25</td><td>Cosme Fulanitos</td><td>cosme_fulanitos@mailfalso.com</td><td>0303456</td><td> <a href="#" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a></td></tr>';

  document.querySelector('#usuarios tbody').outerHTML = listadoHTML;

}

function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-type': 'application/json',
        'Authorization': localStorage.token
    };
}

async function eliminarUsuario(id) {

    if (!confirm('Che, ¿Estás seguro de que lo querés borrar?')) {
        return;
    }

    const request = await fetch('api/usuarios/' + id, {
        method: 'DELETE',
        headers: getHeaders()
    });
    /*alert(id);*/
    location.reload();
}