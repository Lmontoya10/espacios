const FormUsuario = document.getElementById('formUsuario');

//expresiones regulares
const expresiones = {
	passwordExp: /^.{4,12}$/, // 4 a 12 Caracteres.
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
	var contrasenia = document.getElementById("contrasenia").value
	var password2 = document.getElementById("password2").value
	var validacion = true;
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.passwordExp, contrasenia, 'contrasenia')){
		validacion = false
	}
	
	//Valido contraseñas sean iguales
	if (contrasenia != password2){
		document.getElementById('password2').classList.remove('is-valid');
		document.getElementById('password2').classList.add('is-invalid');
		return;
	}else{
		document.getElementById('password2').classList.remove('is-invalid');
		document.getElementById('password2').classList.add('is-valid');
	}
	
	if(validacion == false){
		return false;
	};
	
	return true;
}



