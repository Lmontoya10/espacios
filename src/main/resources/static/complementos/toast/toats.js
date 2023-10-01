//TOAST. RECUERDE QUE NECESITA stilos en el html .CCC
function mostrarToast(tipoToast, mensaje){
	switch(true){
		case (tipoToast == 'ok'):
			toastr.success(mensaje, 'REGISTRO', {
				timeOut: 7000,
				progressBar: true
			});
		break;
		
		case (tipoToast == "alerta"):
			toastr.warning(mensaje, 'ALERTA', {
				timeOut: 7000,
				progressBar: true
			});
		break;
		
		case (tipoToast == "error"):
			toastr.error(mensaje, 'ALERTA', {
				timeOut: 7000,
				progressBar: true
			});
		break;
	}
}