//CUALQUIER ACCIÓN SOBRE EL FORMULARIO
$(document).ready(function(){
    //Ejecuta el boton "Ver configuracion del perfil"
    $("#btnEditar").click(function(){
       editarProveedor();
    });
    
});

//Estilo del select2
$(document).ready(function()
{
	$('#idProveedor').select2();
});

//Valido para traer los datos según Perfil y Grupo seleccionado
function editarProveedor(){
	//Tomo los valores de los campos y los llevo a una variable
	var idProveedor = document.getElementById('idProveedor').value;
	//Valido los Select
	if (idProveedor == 0){
		document.getElementById('idProveedor').classList.remove('is-valid');
		document.getElementById('idProveedor').classList.add('is-invalid');
		mostrarToast('alerta','Debe seleccionar un proveedor');
		return;
	}else{
		document.getElementById('idProveedor').classList.remove('is-invalid');
		document.getElementById('idProveedor').classList.add('is-valid');
	}
	//Redirecciones a la vista donde lista los registros
	window.location.href = "/proveedor/editarProveedor/" + idProveedor;
}
