const FormUsuario = document.getElementById('formOpciones');

//expresiones regulares
const expresiones = {
	nombreListaExp: /^[a-zA-Z0-9À-ÿ\s@_.,;*--+=¿?/'""'|()]{1,200}$/, //Letras, numeros, pueden llevar acentos.
	codigoListaExp: /^[a-zA-Z0-9À-ÿ\s@_.,;*--+=¿?/'""'|()]{0,20}$/, //Cualquier valor
	ordenListaExp: /^[0-9]{1,3}$/, // Sol números
	idListaDependeExp: /^[0-9]{1,6}$/, // Sol números
	valorExp: /^[0-9]{1,6}$/, // Sol números
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
	var nombreLista = document.getElementById("nombreLista").value
	var codigoLista = document.getElementById("codigoLista").value
	var ordenLista = document.getElementById("ordenLista").value
	var idListaDepende = document.getElementById("idListaDepende").value
	var valor = document.getElementById("valor").value
	var validacion = true;
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.nombreListaExp, nombreLista, 'nombreLista')){
		validacion = false;
	}
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.codigoListaExp, codigoLista, 'codigoLista')){
		validacion = false;
	}
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.ordenListaExp, ordenLista, 'ordenLista')){
		validacion = false;
	}
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.idListaDependeExp, idListaDepende, 'idListaDepende')){
		validacion= false
	}
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.valorExp, valor, 'valor')){
		validacion = false
	}
	
	if(validacion == false){
		return false;
	}
	return true;
	
}




