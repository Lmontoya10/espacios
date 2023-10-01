const FormUsuario = document.getElementById('formProveedorFactura');

$('#valorFacturaFisica').mask('#,##0.00', {reverse: true});

//expresiones regulares
const expresiones = {
	selectExp: /^[1-9][0-9]*$/, //Número entero mayor de cero
	prefijoExp: /^[a-zA-Z*]{1,10}$/, // Letras
	numeroExp: /^[0-9]{1,9}$/, //Número entero
	numeroMoney: /^[0-9,.]{1,9}$/, //Número money
	fechaExp: /^([0-2][0-9]|3[0-1])(\/|-)(0[1-9]|1[0-2])\2(\d{4})$/, //Validar fecha
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
	var idTipoFactura = document.getElementById("idTipoFactura").value
	var prefijoFactura = document.getElementById("prefijoFactura").value
	var numeroFactura = document.getElementById("numeroFactura").value
	var fechaFactura = document.getElementById("fechaFactura").value
	var valorFacturaFisica = document.getElementById("valorFacturaFisica").value
	var fechaSistema = new Date();
	var validacion = true;
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.selectExp, idTipoFactura, 'idTipoFactura')){
		validacion= false;
	}
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.prefijoExp, prefijoFactura, 'prefijoFactura')){
		validacion= false;
	}
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.numeroExp, numeroFactura, 'numeroFactura')){
		validacion= false;
	}
	
	//Valido la fecha ingresada este escrita correctamente
	var fechaFacturaMom = moment(fechaFactura, 'DD-MM-YYYY');
	if(fechaFacturaMom.isValid()){
		if (expresiones.fechaExp.test(fechaFactura)== false){
			document.getElementById('fechaFactura').classList.remove('is-valid');
			document.getElementById('fechaFactura').classList.add('is-invalid');
			validacion= false;
		}else{
			document.getElementById('fechaFactura').classList.remove('is-invalid');
			document.getElementById('fechaFactura').classList.add('is-valid');
		}
	}else{
		document.getElementById('fechaFactura').classList.add('is-invalid');
		validacion= false;
	}
	
	//VALIDO QUE LA FECHA NO SEA MAYOR A LA DEL SISTEMA
	var fechaSistemaMon = moment(fechaSistema, 'DD-MM-YYYY');
	if (fechaFacturaMom > fechaSistemaMon){
		mostrarToast('alerta','La fecha de la factura no puede ser mayor a la del sistema');
		document.getElementById('fechaFactura').classList.remove('is-valid');
		document.getElementById('fechaFactura').classList.add('is-invalid');
		validacion= false;
	}
	
	if (valorFacturaFisica <= 0 ){
		document.getElementById('valorFacturaFisica').classList.remove('is-valid');
		document.getElementById('valorFacturaFisica').classList.add('is-invalid');
		validacion= false;
	}else{
		document.getElementById('valorFacturaFisica').classList.remove('is-invalid');
		document.getElementById('valorFacturaFisica').classList.add('is-valid');
	}
	
	if(validacion == false){
		return false;
	};
	
	//quito las comas a los valores numéricos para enviarlos a guardar
	const reg = /,/g
	document.getElementById('valorFacturaFisica').value = valorFacturaFisica.replace(reg,'');
	
	return true;
}






