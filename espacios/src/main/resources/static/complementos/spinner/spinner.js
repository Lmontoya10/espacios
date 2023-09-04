
function guardarInformacion(formName)
{
		btnSpinnerInicio('btnGuardar','spnSpinner');
		if(!validate())
		{
			btnSpinnerFin('btnGuardar','spnSpinner');
			mostrarToast('alerta','Falta ingresar datos, Verifique la información ingresada por favor');
			return false;
		};
		document.getElementById(formName).submit();
}

function guardarInformacionAjax(idBtnAccion, idSpanSpinner)
{
		btnSpinnerInicio(idBtnAccion,idSpanSpinner);
		if(!validate()){
			btnSpinnerFin(idBtnAccion,idSpanSpinner);
			mostrarToast('alerta','Falta ingresar datos, Verifique la información ingresada por favor');
			return false;
		}else{
			btnSpinnerFin(idBtnAccion,idSpanSpinner);
			return true
		};
}


function btnSpinnerInicio (idButton, idSpan){
	document.getElementById(idButton).disabled = true;
	document.getElementById(idSpan).classList.add('spinner-border');
	document.getElementById(idSpan).classList.add('spinner-border-sm');
}

function btnSpinnerFin (idButton, idSpan){
	document.getElementById(idButton).disabled = false;
	document.getElementById(idSpan).classList.remove('spinner-border');
	document.getElementById(idSpan).classList.remove('spinner-border-sm');
}