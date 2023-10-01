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


function mostrarOcultarCAE(){
	var perfilEstudiante = document.getElementById('perfilEstudiante');
	if (perfilEstudiante.checked == 1){
		document.getElementById('estudianteCAE').style.display = 'block';
		perfilEstudiante.value=1;
	}else{
		document.getElementById('estudianteCAE').style.display = 'none';
	}
}

function mostrarOcultarEspecialidad(){
	var perfilDocente = document.getElementById('perfilDocente');
	if (perfilDocente.checked == 1){
		document.getElementById('docenteEspecialidad').style.display = 'block';
		perfilDocente.value=1;
	}else{
		document.getElementById('docenteEspecialidad').style.display = 'none';
	}
}

function valorCheck(){
	var perfilAuxiliar = document.getElementById('perfilAuxiliar');
	var perfilAdminsitrador = document.getElementById('perfilAdminsitrador');
	if (perfilAuxiliar.checked == 1){
		perfilAuxiliar.value=1;
	}
	if (perfilAdminsitrador.checked == 1){
		perfilAdminsitrador.value=1;
	}
}



function validate()
{
	//Tomo los valores
	var identificacion = document.getElementById("identificacion").value
	var primerNombre = document.getElementById("primerNombre").value
	var segundoNombre = document.getElementById("segundoNombre").value
	var primerApellido = document.getElementById("primerApellido").value
	var segundoApellido = document.getElementById("segundoApellido").value
	var email = document.getElementById("email").value
	var perfilEstudiante = document.getElementById("perfilEstudiante")
	var perfilDocente = document.getElementById("perfilDocente")
	var perfilAuxiliar = document.getElementById("perfilAuxiliar")
	var perfilAdminsitrador = document.getElementById("perfilAdminsitrador")
	var idCAE = document.getElementById("idCAE").value
	var idListaEspecialidadDocente = document.getElementById("idListaEspecialidadDocente").value
	
	var validacion = true;
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.documentoExp, identificacion, 'identificacion')){
		validacion = false
	}
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.nombresExp, primerNombre, 'primerNombre')){
		validacion = false
	}
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	//if (!validarCampo(expresiones.nombresExp, segundoNombre, 'segundoNombre')){
	//	validacion = false
	//}
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.nombresExp, primerApellido, 'primerApellido')){
		validacion = false
	}
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	//if (!validarCampo(expresiones.nombresExp, segundoApellido, 'segundoApellido')){
	//	validacion = false
	//}
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.correoExp, email, 'email')){
		validacion = false
	}
	
	//Valido si seleccionó estudiante para solicitar que seleccione el CAE
	if (perfilEstudiante.checked == 1){
		//Valido cada uno de los campos cumpla con las expresiones de validación
		if (!validarCampo(expresiones.selectMayorCero, idCAE, 'idCAE')){
			validacion = false
		}	
	}
	
	//Valido si seleccionó docente para solicitar que seleccione la especialidad
	if (perfilDocente.checked == 1){
		//Valido cada uno de los campos cumpla con las expresiones de validación
		if (!validarCampo(expresiones.selectMayorCero, idListaEspecialidadDocente, 'idListaEspecialidadDocente')){
			validacion = false
		}	
	}
	
	
	//Valido si seleccionó docente para solicitar que seleccione la especialidad
	if (perfilEstudiante.checked == 0 && perfilDocente.checked == 0 && perfilAuxiliar.checked == 0 && perfilAdminsitrador.checked == 0){
		if (!validarCampo(expresiones.selectMayorCero, 0, 'chkPerfilSelec')){
			validacion = false
		}
	}
	
	
	
	
	if(validacion == false){
		return false;
	};
	return true;
		
}

	var perfilEstudiante = document.getElementById('perfilEstudiante');
	if (perfilEstudiante.value == 'true'){
		perfilEstudiante.checked=1;
	}
	
	var perfilDocente = document.getElementById('perfilDocente');
	if (perfilDocente.value == 'true'){
		perfilDocente.checked=1;
	}
	
	var perfilAuxiliar = document.getElementById('perfilAuxiliar');
	if (perfilAuxiliar.value == 'true'){
		perfilAuxiliar.checked=1;
	}
	
	var perfilAdminsitrador = document.getElementById('perfilAdminsitrador');
	if (perfilAdminsitrador.value == 'true'){
		perfilAdminsitrador.checked=1;
	}
	
	mostrarOcultarCAE();
	mostrarOcultarEspecialidad();


