const FormUsuario = document.getElementById('formPersona');

//expresiones regulares
const expresiones = {
	documentoExp: /^[a-zA-ZÀ-ÿ0-9]{6,30}$/, // Letras, numeros, pueden llevar acentos.
	nombresExp: /^[a-zA-Z0-9À-ÿ\s@_.,;*-+=¿?/()]{2,100}$/, // 2 a 50 Cualquier valor
	apellidosExp: /^[a-zA-Z0-9À-ÿ\s@_.,;*-+=¿?/()]{4,100}$/, // 2 a 50 Cualquier valor
	usuarioExp: /^[a-zA-Z0-9]{4,30}$/, // 2 a 50. Letras, numeros, no pueden llevar acentos
	contraseniaExp: /^[a-zA-Z0-9À-ÿ\s@_.,;*-+=¿?/()]{4,50}$/, // 0 a 50 Cualquier valor
	codigoUsuarioExp: /^[a-zA-Z0-9À-ÿ\s@_.,;*-+=¿?/()]{2,100}$/, // 2 a 50 Cualquier valor
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
	var idUsuario = document.getElementById("idUsuario").value
	var documento = document.getElementById("documento").value
	var nombres = document.getElementById("nombres").value
	var apellidos = document.getElementById("apellidos").value
	var usuario = document.getElementById("usuario").value
	var contrasenia = document.getElementById("contrasenia").value
	var codigoUsuario = document.getElementById("codigoUsuario").value
	var validacion = true;
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.documentoExp, documento, 'documento')){
		validacion = false
	}
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.nombresExp, nombres, 'nombres')){
		validacion = false
	}
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.apellidosExp, apellidos, 'apellidos')){
		validacion = false
	}
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.usuarioExp, usuario, 'usuario')){
		validacion = false
	}
	
	//Valido si es usuario nuevo o no, si ingreso contraseña o no
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (idUsuario!=''){
		if(contrasenia != ''){
			if (!validarCampo(expresiones.contraseniaExp, contrasenia, 'contrasenia')){
			validacion = false
			}
		}
	}else{
		if (!validarCampo(expresiones.contraseniaExp, contrasenia, 'contrasenia')){
			validacion = false
		}
	}
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.codigoUsuarioExp, codigoUsuario, 'codigoUsuario')){
		validacion = false
	}
	
	if(validacion == false){
		return false;
	};
	return true;
		
}





