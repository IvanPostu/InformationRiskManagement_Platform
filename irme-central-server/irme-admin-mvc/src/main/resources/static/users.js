
function createUserSubmitDataIsValid(submitData) {
  return submitData.selectedRoles.length > 0
    && submitData.countryCode !== ""
    && submitData.firstName !== ""
    && submitData.lastName !== ""
    && submitData.email !== ""
    && submitData.password !== ""
}

function createUsersFetcher() {
  var USERS_PER_PAGE = 5
  var currentPageIndex = 0
  var isRequest = false

  return function (refresh) {
    var usersTBody = $("#usersTBody")
    if (isRequest) return

    if (refresh) {
      usersTBody.html("")
      currentPageIndex = 0;
    }

    $.ajax({
      url: "/api/users/getAll",
      data: {
        offset: currentPageIndex * USERS_PER_PAGE,
        limit: USERS_PER_PAGE,
        sortAsc: -1
      },
      type: "GET",
      dataType: "json",
      success: function (res) {
        currentPageIndex++

        for (var index = 0; index < res.length; index++) {
          var element = res[index];

          var node = (
            "<tr>" +
            "<th scope=\"row\">" + "<a href=\"/user?userId=" + element.id + "\">" + element.id + "</a>" + "</th>" +
            "<td>" + element.email + "</td>" +
            "<td>" + element.firstName + "</td>" +
            "<td>" + element.lastName + "</td>" +
            "<td>" + element.roles.join('; ') + "</td>" +
            "</tr>"
          )

          usersTBody.html(usersTBody.html() + node)
        }
      },
      beforeSend: function (e) {
        isRequest = true
      },
      complete: function (e) {
        isRequest = false
      }
    });
  }
}

function searchUserByEmailOnTextInput() {

  var wait = 660;
  var timeout;
  var xhr = null;

  var func = function () {
    var text = this.value
    console.log(text)
  }

  return function () {
    var context = this, args = arguments;
    var later = function () {
      timeout = null;
      func.apply(context, args);
    };
    clearTimeout(timeout);
    timeout = setTimeout(later, wait);
  };
}

jQuery(function () {
  var usersFetch = createUsersFetcher();

  // setTimeout(function () {

  //   if ($('#usersDropdownMenuID').is(":hidden")) {
  //     $('#usersDropdownMenuBtnID').click()
  //   }
  // }, 1000)

  $("#searchUserByEmailInputID").on("input",searchUserByEmailOnTextInput())

  $("#menu-toggle").css("display", "block")
  $("#menu-toggle").click(function (e) {
    e.preventDefault();
    $("#container-sidebar-wrapper").toggleClass("toggled");
  });

  $("#createUserColapseId").click(function (e) {
    $("#createUserColapseBlockId").collapse("toggle")
  })

  $("#createUserSubmitBtn").on("click", function (e) {
    e.preventDefault()

    var submitData = {
      selectedRoles: [],
      email: "",
      password: "",
      firstName: "",
      lastName: "",
      countryCode: ""
    }

    $("#rolesInputId").children("li").each(function (index, elem) {
      var checkbox = elem.getElementsByTagName("input")[0]
      var checkboxText = elem.getElementsByTagName("span")[0]

      if (checkbox.checked) {
        submitData.selectedRoles.push(checkboxText.textContent)
      }
    })

    submitData.countryCode = $("#countriesSelect").val()
    submitData.firstName = $("#userFirstName").val()
    submitData.lastName = $("#userLastName").val()
    submitData.email = $("#userEmail").val()
    submitData.password = $("#userPassword").val()

    var resultMessageElement = $("#sumbitResultMessage")

    if (createUserSubmitDataIsValid(submitData)) {

      var timer = null

      $.ajax({
        type: "POST",
        url: "/api/users/create",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(submitData),
        cache: false,
        success: function (res) {
          resultMessageElement.html(
            "<div class=\"alert alert-success\" role=\"alert\">" +
            "User created with success" +
            "</div>"
          )

          usersFetch(true)
        },
        error: function (err) {
          resultMessageElement.html(
            "<div class=\"alert alert-danger\" role=\"alert\">" +
            "Error: Something went wrong" +
            "</div>"
          )
        },
        beforeSend: function (xhr) {
          resultMessageElement.html("")
          $("#createUserSubmitBtn").prop("disabled", true);
        },
        complete: function () {
          $("#createUserSubmitBtn").prop("disabled", false);

        }
      });

    } else {
      resultMessageElement.removeClass("d-none")
      resultMessageElement.removeClass("alert-success")
      resultMessageElement.addClass("alert-danger")
    }

  })

  $("#fetchNextUsersBtn").on("click", function (e) {
    usersFetch()
  })

  // fetch roles
  $.ajax({
    url: "/api/users/allRoles",
    type: "GET",
    dataType: "json",
    success: function (res) {
      for (var i = 0; i < res.length; i++) {
        var item = res[i]
        var parent = $("#rolesInputId")

        var htmlElement = "<li class=\"list-group-item\">" +
          "<input class=\"form-check-input ms-2\" type=\"checkbox\" />" +
          "<span>" + item + "</span>" +
          "</li>"

        parent.html(parent.html() + htmlElement)
      }
    }
  });

  //  fetch countris
  $.ajax({
    url: "/api/countries",
    type: "GET",
    dataType: "json",
    success: function (res) {
      var parent = $("#countriesSelect")

      for (var i = 0; i < res.length; i++) {
        var item = res[i]

        var htmlElement = "<option value=\"" + res[i].countryCode + "\">" + res[i].countryName + "</option>"

        parent.html(parent.html() + htmlElement)
      }
    }
  });

  // fetch users
  usersFetch()
})
