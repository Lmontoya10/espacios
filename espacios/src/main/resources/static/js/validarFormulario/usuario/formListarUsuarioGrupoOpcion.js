const FormUsuario = document.getElementById('formUsuarioGrupoOpcion');


//Asigar propiedades select2 
$(document).ready(function(){
	$('#idGrupo').select2();
});

//expresiones regulares
const expresiones = {
	selectExp: /^[1-9][0-9]*$/, //Número entero mayor de cero
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
	var idGrupo = document.getElementById("idGrupo").value
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (validarCampo(expresiones.selectExp, idGrupo, 'idGrupo')){
		return true;
	}else{
		return false
	}
	
};
