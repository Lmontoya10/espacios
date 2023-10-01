const FormProveedorFacturaDetalle = document.getElementById('formProveedorFacturaDetalle');
const inputsForm = document.querySelectorAll('#formProveedorFacturaDetalle input');

//expresiones regulares
const expresiones = {
	selectExp: /^[1-9][0-9]*$/, //Número entero mayor de cero
	numeroEnteroExp: /^[0-9]{1,9}$/, //Número entero
	//numeroMoney: /^[0-9,.]{1,9}$/, //Número money
	numeroMoney: /^[0-9,.]{1,9}$/, //Número money
	numeroDecimalExp: /^\d.*\.?\d{1,2}$/, // Numero con 2 decimales.
}

$(document).ready(function() {
    //ACTIVAR EL SELECT 2
    $('#idListaProducto').select2();
    $('#idListaMarcaProducto').select2();
    $('#idListaTamanioProducto').select2();
    $('#idListaColorProducto').select2();
    $('#idListaTipoProducto').select2();
    
    //Carga tabla con lista de productos
    cargarListaProductosAgregados();
});

//FUNCIÓN PARA EJECUTAR LA VALIDACIÓN DEL CAMPO CON EXPRESIONES
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

function cargarListasDependientesProducto(){
	//AJAX
	cargarSelectDependienteMarca();
	cargarSelectDependienteTamanio();
	cargarSelectDependienteColor();
	cargarSelectDependienteTipo();
}

//DECLARO LAS VARIABLES
var idListaProducto, idListaMarcaProducto, idListaTamanioProducto, idListaColorProducto, idListaTipoProducto,
cantidadProducto, valorBaseProducto, descuentoPorcentajeBase1, descuentoPorcentajeBase2, descuentoPorcentajeBase3, IVAPorcentaje, 
valorIVA, valTotal, idEstadoRegistroProducto
var valorDescuento1, valorDescuento2, valorDescuento3
validacion = true;

$('#cantidadProducto').mask('#,##0.00', {reverse: true});
$('#valorBaseProducto').mask('#,##0.00', {reverse: true});
$('#descuentoPorcentajeBase1').mask('##0.00', {reverse: true});
$('#descuentoPorcentajeBase2').mask('##0.00', {reverse: true});
$('#descuentoPorcentajeBase3').mask('##0.00', {reverse: true});
$('#IVAPorcentaje').mask('##0.00', {reverse: true});
$('#valorTotal').mask('#,##0.00', {reverse: true});
  

function calcularValores(){
	
	cantidadProducto = document.getElementById("cantidadProducto").value;
	valorBaseProducto = document.getElementById("valorBaseProducto").value;
	descuentoPorcentajeBase1 = document.getElementById("descuentoPorcentajeBase1").value;
	descuentoPorcentajeBase2 = document.getElementById("descuentoPorcentajeBase2").value;
	descuentoPorcentajeBase3 = document.getElementById("descuentoPorcentajeBase3").value;
	IVAPorcentaje = document.getElementById("IVAPorcentaje").value;
	
	//le quito la comas al número ingresado para poder trabajar con ellos
	const reg = /,/g
	cantidadProducto = cantidadProducto.replace(reg,'')
	valorBaseProducto = valorBaseProducto.replace(reg,'')
	
	if (!isNaN(cantidadProducto)  && !isNaN(valorBaseProducto) && !isNaN(descuentoPorcentajeBase1) && !isNaN(descuentoPorcentajeBase2) && !isNaN(descuentoPorcentajeBase3) && !isNaN(IVAPorcentaje)){
		
		if (descuentoPorcentajeBase1>0){
			valorDescuento1 = valorBaseProducto * (descuentoPorcentajeBase1/100);
		}else{
			valorDescuento1 = 0
		}
		
		if (descuentoPorcentajeBase2>0){
			valorDescuento2 = (valorBaseProducto - valorDescuento1) * (descuentoPorcentajeBase2/100);
		}else{
			valorDescuento2 = 0
		}
		
		if (descuentoPorcentajeBase3>0){
			valorDescuento3 = (valorBaseProducto - (valorDescuento1 + valorDescuento2)) * (descuentoPorcentajeBase3/100);
		}else{
			valorDescuento3 = 0
		}
		
		valorIVA = (valorBaseProducto-valorDescuento1-valorDescuento2-valorDescuento3) * (IVAPorcentaje/100);
		//valorUnitarioProducto = (valorBaseProducto-valorDescuento1-valorDescuento2-valorDescuento3 + valorIVA)
		valTotal = (valorBaseProducto-valorDescuento1-valorDescuento2-valorDescuento3 + valorIVA) * cantidadProducto
		
		//Doy formato al número ingresado
		valTotal = valTotal.toLocaleString("en-US");
		document.getElementById("valorTotal").value = valTotal;
	}else{
		document.getElementById("valorTotal").value = 0
	}
	
}

//DEFINO LOS EVENTOS DE SOLTAR TECLA Y CLICK EN OTRA PARTE INPUT
inputsForm.forEach((input)=>{
	input.addEventListener('keyup', calcularValores);
	input.addEventListener('blur', calcularValores);
});

function validate()
{
	//Tomo los valores
	idProveedorFactura = document.getElementById("idProveedorFactura").value;
	idListaProducto = document.getElementById("idListaProducto").value;
	idListaMarcaProducto = document.getElementById("idListaMarcaProducto").value;
	idListaTamanioProducto = document.getElementById("idListaTamanioProducto").value;
	idListaColorProducto = document.getElementById("idListaColorProducto").value;
	idListaTipoProducto = document.getElementById("idListaTipoProducto").value;
	cantidadProducto = document.getElementById("cantidadProducto").value;
	valorBaseProducto = document.getElementById("valorBaseProducto").value;
	descuentoPorcentajeBase1 = document.getElementById("descuentoPorcentajeBase1").value;
	descuentoPorcentajeBase2 = document.getElementById("descuentoPorcentajeBase2").value;
	descuentoPorcentajeBase3 = document.getElementById("descuentoPorcentajeBase3").value;
	IVAPorcentaje = document.getElementById("IVAPorcentaje").value;
	valorTotal = document.getElementById("valorTotal").value;
	idUsuarioLogueado = document.getElementById("idUsuarioLogueado").value;
	idEstadoRegistroProducto = document.getElementById("fkIdListaEstadoRegistroProducto").value;
	validacion = true;
	
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.selectExp, idListaProducto, 'idListaProducto')){
		validacion= false;
	}
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.selectExp, idListaMarcaProducto, 'idListaMarcaProducto')){
		validacion= false;
	}
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.selectExp, idListaTamanioProducto, 'idListaTamanioProducto')){
		validacion= false;
	}
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.selectExp, idListaColorProducto, 'idListaColorProducto')){
		validacion= false;
	}
	//Valido cada uno de los campos cumpla con las expresiones de validación
	if (!validarCampo(expresiones.selectExp, idListaTipoProducto, 'idListaTipoProducto')){
		validacion= false;
	}
	
	//Valido los valores ingresados
	if (cantidadProducto <= 0){
		document.getElementById('cantidadProducto').classList.remove('is-valid');
		document.getElementById('cantidadProducto').classList.add('is-invalid');
		validacion= false;
	}else{
		document.getElementById('cantidadProducto').classList.remove('is-invalid');
		document.getElementById('cantidadProducto').classList.add('is-valid');
	}
	
	if (valorBaseProducto <= 0){
		document.getElementById('valorBaseProducto').classList.remove('is-valid');
		document.getElementById('valorBaseProducto').classList.add('is-invalid');
		validacion= false;
	}else{
		document.getElementById('valorBaseProducto').classList.remove('is-invalid');
		document.getElementById('valorBaseProducto').classList.add('is-valid');
	}
	
	if (descuentoPorcentajeBase1 > 100){
		document.getElementById('descuentoPorcentajeBase1').classList.remove('is-valid');
		document.getElementById('descuentoPorcentajeBase1').classList.add('is-invalid');
		validacion= false;
	}else{
		document.getElementById('descuentoPorcentajeBase1').classList.remove('is-invalid');
		document.getElementById('descuentoPorcentajeBase1').classList.add('is-valid');
	}
	
	if (descuentoPorcentajeBase2 > 100){
		document.getElementById('descuentoPorcentajeBase2').classList.remove('is-valid');
		document.getElementById('descuentoPorcentajeBase2').classList.add('is-invalid');
		validacion= false;
	}else{
		document.getElementById('descuentoPorcentajeBase2').classList.remove('is-invalid');
		document.getElementById('descuentoPorcentajeBase2').classList.add('is-valid');
	}
	
	if (descuentoPorcentajeBase3 > 100){
		document.getElementById('descuentoPorcentajeBase3').classList.remove('is-valid');
		document.getElementById('descuentoPorcentajeBase3').classList.add('is-invalid');
		validacion= false;
	}else{
		document.getElementById('descuentoPorcentajeBase3').classList.remove('is-invalid');
		document.getElementById('descuentoPorcentajeBase3').classList.add('is-valid');
	}
	
	if (IVAPorcentaje > 100 || IVAPorcentaje == ''){
		document.getElementById('IVAPorcentaje').classList.remove('is-valid');
		document.getElementById('IVAPorcentaje').classList.add('is-invalid');
		validacion= false;
	}else{
		document.getElementById('IVAPorcentaje').classList.remove('is-invalid');
		document.getElementById('IVAPorcentaje').classList.add('is-valid');
	}
	
	if (valorTotal <= 0 || valorTotal == '' ){
		document.getElementById('valorTotal').classList.remove('is-valid');
		document.getElementById('valorTotal').classList.add('is-invalid');
		validacion= false;
	}else{
		document.getElementById('valorTotal').classList.remove('is-invalid');
		document.getElementById('valorTotal').classList.add('is-valid');
	}
	
	if(validacion == false){
		return false;
	};
	
	//VALIDO SI INGRSO VALOR % DESCUENTO. SI NO LOS IGUALO A CERO
	if (descuentoPorcentajeBase1==''){
		document.getElementById("descuentoPorcentajeBase1").value = 0
		descuentoPorcentajeBase1 = 0
		}
	if (descuentoPorcentajeBase2==''){
		document.getElementById("descuentoPorcentajeBase2").value = 0
		descuentoPorcentajeBase2 = 0
		}
	if (descuentoPorcentajeBase3==''){
		document.getElementById("descuentoPorcentajeBase3").value = 0
		descuentoPorcentajeBase3 = 0
		}
	//quito las comas a los valores numéricos para enviarlos a guardar
	const reg = /,/g
	cantidadProducto = cantidadProducto.replace(reg,'')
	valorBaseProducto = valorBaseProducto.replace(reg,'')
	valorTotal = valorTotal.replace(reg,'')
	
	//Ajax registrar datos
	registrarProductoFacturaProveedor();
	return true;
}

function limpiarCampos(){
	document.getElementById("cantidadProducto").value = '';
	document.getElementById("valorBaseProducto").value = '';
	document.getElementById("descuentoPorcentajeBase1").value = '';
	document.getElementById("descuentoPorcentajeBase2").value = '';
	document.getElementById("descuentoPorcentajeBase3").value = '';
	document.getElementById("IVAPorcentaje").value = '';
	document.getElementById("valorTotal").value = '';
	document.getElementById('cantidadProducto').classList.remove('is-valid');
	document.getElementById('valorBaseProducto').classList.remove('is-valid');
	document.getElementById('descuentoPorcentajeBase1').classList.remove('is-valid');
	document.getElementById('descuentoPorcentajeBase2').classList.remove('is-valid');
	document.getElementById('descuentoPorcentajeBase3').classList.remove('is-valid');
	document.getElementById('IVAPorcentaje').classList.remove('is-valid');
	document.getElementById('valorTotal').classList.remove('is-valid');
	cargarListasDependientesProducto();
}

//AJAX GUARDAR EVALUADO ASIGNADO
function registrarProductoFacturaProveedor(){
	$.ajax({
		url : "/proveedorFactura/guardarProductoFacturaDetalle",
		type : "POST",
		data : {
			"proveedorFactura.idProveedorFactura" : idProveedorFactura,
			"maeGrupoListaProducto.idLista" : idListaProducto,
			"maeGrupoListaMarcaProducto.idLista" : idListaMarcaProducto,
			"maeGrupoListaTamanioProducto.idLista" : idListaTamanioProducto,
			"maeGrupoListaColorProducto.idLista" : idListaColorProducto,
			"maeGrupoListaTipoProducto.idLista" : idListaTipoProducto,
			"cantidadProducto" : cantidadProducto,
			"valorBaseProducto" : valorBaseProducto,
			"descuentoPorcentajeBase1" : descuentoPorcentajeBase1,
			"descuentoPorcentajeBase2" : descuentoPorcentajeBase2,
			"descuentoPorcentajeBase3" : descuentoPorcentajeBase3,
			"IVAPorcentaje" : IVAPorcentaje,
			"valorTotal" : valorTotal,
			"maeUsuarioRegistra.idUsuario" : idUsuarioLogueado,
			"maeGrupoListaEstadoRegistroProducto.idLista" : idEstadoRegistroProducto
		},
		success: function (html) {
			$("#listaProductosRegistrados").html(html); //Refresco la tabla de los usuarios asignados
			mostrarToast('ok','Producto guardado correctamente'); //muestro el toast de ok
			limpiarCampos(); //Limpio los campos
			return true;
         },
		error : function(e){
			mostrarToast('error','Error al tratar de realizar el registro. ERROR: ' + e);
			console.log(e);
			return false;
		},
	});
	return true;
}


//AJAX CARGAR LISTA DESPLEGABLE DEPENDIENTE
function cargarSelectDependienteMarca(){
	$.ajax({
		type : "GET",
		url : "/listaOpciones/listarOpcionesDependientesEstandar/" + $('#idListaProducto').val() + '/' + '17',
		data : {},
		success: function (html) {
			$("#idListaMarcaProducto").html(html);
         },	
		error : function(e){
			console.log(e);
		},
	});
	return false;
}

//AJAX CARGAR LISTA DESPLEGABLE DEPENDIENTE
function cargarSelectDependienteTamanio(){
	$.ajax({
		type : "GET",
		url : "/listaOpciones/listarOpcionesDependientesEstandar/" + $('#idListaProducto').val() + '/' + '18',
		data : {},
		success: function (html) {
			$("#idListaTamanioProducto").html(html);
         },	
		error : function(e){
			console.log(e);
		},
	});
	return false;
}

//AJAX CARGAR LISTA DESPLEGABLE DEPENDIENTE
function cargarSelectDependienteColor(){
	$.ajax({
		type : "GET",
		url : "/listaOpciones/listarOpcionesDependientesEstandar/" + $('#idListaProducto').val() + '/' + '20',
		data : {},
		success: function (html) {
			$("#idListaColorProducto").html(html);
         },	
		error : function(e){
			console.log(e);
		},
	});
	return false;
}

//AJAX CARGAR LISTA DESPLEGABLE DEPENDIENTE
function cargarSelectDependienteTipo(){
	$.ajax({
		type : "GET",
		url : "/listaOpciones/listarOpcionesDependientesEstandar/" + $('#idListaProducto').val() + '/' + '15',
		data : {},
		success: function (html) {
			$("#idListaTipoProducto").html(html);
         },	
		error : function(e){
			console.log(e);
		},
	});
	return false;
}


//AJAX CARGAR LISTA DESPLEGABLE DEPENDIENTE
function cargarListaProductosAgregados(){
	$.ajax({
		type : "GET",
		url : "/proveedorFactura/listaProductosProveedorFactura/" + $('#idProveedorFactura').val(),
		data : {},
		success: function (html) {
			$("#listaProductosRegistrados").html(html);
         },	
		error : function(e){
			console.log(e);
		},
	});
	return false;
}

//FUNCIÓN PARA UTILIZAR EL AJAX CON METODO "POST" Y MANTENER LA SEGURIDAD CSRF. ESTA ES ENVIADA EN EL ENCABEZADO
$(function () {
    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});










