function readURL(input, idNum) {
	if (input.files && input.files[0]) {
		
		let reader = new FileReader();
	
		reader.onload = function(e) {
			$("#imgPreview" + idNum).attr("src", e.target.result).width(100).height(100);
		}
		
		reader.readAsDataURL(input.files[0]);
	}
}

$(function() {
	if ($('textarea#content').length) {
		ClassicEditor.create(document.querySelector('textarea#content')).catch(error => {
			console.log(error);
		})
	}
	
	if ($('#description').length) {
		ClassicEditor.create(document.querySelector('#description')).catch(error => {
			console.log(error);
		})
	}
	
})