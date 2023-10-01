//expresiones regulares
const expresiones = {
	documentoExp: /^[a-zA-ZÀ-ÿ0-9]{6,30}$/, // Letras, numeros, pueden llevar acentos.
	nombresExp: /^[a-zA-Z0-9À-ÿ\s@_.,;*-+=¿?/()]{2,100}$/, // 2 a 50 Cualquier valor
	apellidosExp: /^[a-zA-Z0-9À-ÿ\s@_.,;*-+=¿?/()]{4,100}$/, // 2 a 50 Cualquier valor
	correoExp: /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i, // Valida si el correo esta escrito correctamente
	numeroEnteroExp: /^[0-9]{1,9}$/, //Número entero
	numeroEnteroMayorCeroExp: /^[1-9][0-9]*$/, //Número entero mayor de cero
	selectMayorCero: /^[1-9][0-9]*$/, //Número entero mayor de cero
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
	var idListaNombre = document.getElementById('idListaNombre').value;
	var idListaCae = document.getElementById('idListaCae').value;
	var idListaDia = document.getElementById('idListaDia').value;
	var idListaHorario = document.getElementById('idListaHorario').value;
	var validacion = true;
	
	if(!validarCampo(expresiones.selectMayorCero, idListaNombre, 'idListaNombre')){
		validacion = false;
	}
	
	if(!validarCampo(expresiones.selectMayorCero, idListaCae, 'idListaCae')){
		validacion = false;
	}
	
	if(!validarCampo(expresiones.selectMayorCero, idListaDia, 'idListaDia')){
		validacion = false;
	}
	
	if(!validarCampo(expresiones.selectMayorCero, idListaHorario, 'idListaHorario')){
		validacion = false;
	}
	
	if(validacion == false){
		return false;
	};
	return true;
	
}


