jQuery(function () {
  $("#menu-toggle").css("display", "block")
  
  $("#menu-toggle").on('click', function (e) {
    e.preventDefault();
    $("#container-sidebar-wrapper").toggleClass("toggled");
  });

  var targetNode = $('#alertPlaceID')
  
  var url = new URL(window.location.href);
  var assignedToOrganisationsSuccessfully = url.searchParams
    .get('assignedToOrganisationsSuccessfully')

  if(assignedToOrganisationsSuccessfully === '1'){
    targetNode.append(
      '<div class="alert alert-light alert-dismissable">'+
        '<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>'+
        '<p class="text-success">'+
          '<strong>Success!</strong> User assigned to organisations with success.'+
        '</p>'+
      '</div>'
    )

    setTimeout(function(){
      targetNode.html('')
    }, 4000)
  }

  if(assignedToOrganisationsSuccessfully === '0'){
    targetNode.append(
      '<div class="alert alert-light alert-dismissable">'+
        '<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>'+
        '<p class="text-danger">'+
          '<strong>Error!</strong> User is not assigned to organisations.'+
        '</p>'+
      '</div>'
    )

    setTimeout(function(){
      targetNode.html('')
    }, 4000)
  }
})
