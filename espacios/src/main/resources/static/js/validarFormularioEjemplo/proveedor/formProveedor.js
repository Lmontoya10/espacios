const FormUsuario = document.getElementById('formProveedor');

//Asigar propiedades select2 
$(document).ready(function(){
	$('#tipoIdentificacion').select2();
	$('#tipoPersonaProveedor').select2();
	$('#tipoRegimenProveedor').select2();
	$('#idPais').select2();
	$('#idDepartamento').select2();
	$('#idCiudad').select2();
});

//expresiones regulares
const expresiones = {
	nombreProveedorExp: /^[a-zA-Z0-9À-ÿ\s@_.,;*-+=¿?/()]{1,2000}$/, // Letras, numeros, pueden llevar acentos.
	selectExp: /^[1-9][0-9]*$/, //Número entero mayor de cero
	numeroIdentificacionExp: /^[0-9]{1,50}$/, // Letras, numeros, pueden llevar acentos.
	DVExp: /^[0-9]{1,5}$/, // Letras, numeros, pueden llevar acentos.
	telefonoExp: /^[0-9]{1,50}$/, // Letras, numeros, pueden llevar acentos.
	direccionEXP: /^[a-zA-Z0-9À-ÿ\s@_.,;*-+=¿?/()]{1,2000}$/, // Letras, numeros, pueden llevar acentos.
}

//FUNCIÓN PARA EJECUTAR LA VALIDACIÓN DEL CAMPO
const validarCampo = (expresion, input, idCampo) => {
	if(expresion.test(input)){
			document.getElementById(idCampo).classList.remove('is-invalid');
			document.getElementById(idCampo).classList.add('is-valid');
			return true
		} else {
			document.getElementById(idCampo).classList.remove('is-valid');
			document.getElementById(idCampo).classList.add('is-invalid');
			return false
		}
}


function validate()
{
	//Tomo los valores
	var nombreProveedor = document.getElementById("nombreProveedor").value
	var tipoIdentificacion = document.getElementById("tipoIdentificacion").value
	var numeroIdentificacion = document.getElementById("numeroIdentificacion").value
	var digitoVerificacion = document.getElementById("digitoVerificacion").value
	var tipoPersonaProveedor = document.getElementById("tipoPersonaProveedor").value
	var tipoRegimenProveedor = document.getElementById("tipoRegimenProveedor").value
	var telefonoProveedor = document.getElementById("telefonoProveedor").value
	var direccionProveedor = document.getElementById("direccionProveedor").value
	var idPais = document.getElementById("idPais").value
	var idDepartamento = document.getElementById("idDepartamento").value
	var idCiudad = document.getElementById("idCiudad").value
	var validacion = true;
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.nombreProveedorExp, nombreProveedor, 'nombreProveedor')){
		validacion= false;
	}
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.selectExp, tipoIdentificacion, 'tipoIdentificacion')){
		validacion= false;
	}
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.numeroIdentificacionExp, numeroIdentificacion, 'numeroIdentificacion')){
		validacion= false;
	}
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.DVExp, digitoVerificacion, 'digitoVerificacion')){
		validacion= false;
	}
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.selectExp, tipoPersonaProveedor, 'tipoPersonaProveedor')){
		validacion= false;
	}
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.selectExp, tipoRegimenProveedor, 'tipoRegimenProveedor')){
		validacion= false;
	}
	
	var direccionProveedor = document.getElementById("direccionProveedor").value
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.telefonoExp, telefonoProveedor, 'telefonoProveedor')){
		validacion= false;
	}
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.direccionEXP, direccionProveedor, 'direccionProveedor')){
		validacion= false;
	}
	
	if(validacion == false){
		return false;
	};
	return true;
}






