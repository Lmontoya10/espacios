const FormUsuario = document.getElementById('formProveedorBancoCuenta');

//Asigar propiedades select2 
$(document).ready(function(){
	$('#idListaNombreBanco').select2();
	$('#idListaTipoCuentaConvenioBanco').select2();
});

//expresiones regulares
const expresiones = {
	selectExp: /^[1-9][0-9]*$/, //Número entero mayor de cero
	numeroCuentaConvenioExp: /^[0-9]{1,50}$/, // número del 1 al 50
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

function validate(){
	//Tomo los valores
	var idListaNombreBanco = document.getElementById("idListaNombreBanco").value
	var idListaTipoCuentaConvenioBanco = document.getElementById("idListaTipoCuentaConvenioBanco").value
	var numeroCuentaConvenio = document.getElementById("numeroCuentaConvenio").value
	var validacion = true;
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.selectExp, idListaTipoCuentaConvenioBanco, 'idListaTipoCuentaConvenioBanco')){
		validacion = false;
	}
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.selectExp, idListaNombreBanco, 'idListaNombreBanco')){
		validacion = false;
	}
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.numeroCuentaConvenioExp, numeroCuentaConvenio, 'numeroCuentaConvenio')){
		validacion = false;
	}
	
	//Verifico si alguna validación no se cumple
	if(validacion == false){
		return false;
	};
	return true;
}





