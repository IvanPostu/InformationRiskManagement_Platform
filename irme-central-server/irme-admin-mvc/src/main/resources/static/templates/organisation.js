
function getBase64(file, successCallback) {
  var reader = new FileReader();
  reader.readAsDataURL(file);
  reader.onload = function () {
    successCallback(reader.result)
  };
  reader.onerror = function (error) {
    console.log('Error: ', error);
  };
}

jQuery(function () {
  

  $("#logoFileInputId").on('change', function () {
    getBase64(this.files[0], function(b64){
      $("#logoPreviewId").attr('src', b64)
      $("#base64logoHiddenId").val(b64)

    })
  });



  // var targetNode = $('#alertPlaceID')
  
  // var url = new URL(window.location.href);
  // var assignedToOrganisationsSuccessfully = url.searchParams
  //   .get('createdSuccessfully')

  // if(assignedToOrganisationsSuccessfully === '1'){
  //   targetNode.append(
  //     '<div class="alert alert-light alert-dismissable">'+
  //       '<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>'+
  //       '<p class="text-success">'+
  //         '<strong>Success!</strong> Organisation added.'+
  //       '</p>'+
  //     '</div>'
  //   )

  //   setTimeout(function(){
  //     targetNode.html('')
  //   }, 4000)
  // }

  // if(assignedToOrganisationsSuccessfully === '0'){
  //   targetNode.append(
  //     '<div class="alert alert-light alert-dismissable">'+
  //       '<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>'+
  //       '<p class="text-danger">'+
  //         '<strong>Error!</strong> Organisation is not added.'+
  //       '</p>'+
  //     '</div>'
  //   )

  //   setTimeout(function(){
  //     targetNode.html('')
  //   }, 4000)
  // }
})

