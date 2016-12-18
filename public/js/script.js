$(document).ready(function() {
    populatePasswordAndTerms();
    drawLengthButtons();

    new Clipboard('.clipbtn');
    $(".clipbtn").tooltip();
});

function populatePasswordAndTerms(length) {
    $("#password").html("<span class='glyphicon glyphicon-repeat glyphicon-spin'></span> Loading...");

    var url = (length != null) ? "/password/" + length + "?full" : "/password?full";

    $.getJSON(url, function(passRes) {
        $("#password").text(passRes.password);
        $("#term1").text(passRes.term1);
        $("#term2").text(passRes.term2);
    }).error(function() {
         $("#password").html(
            "<span class='text-danger'><span class='glyphicon glyphicon-warning-sign'></span>" +
            " An error occurred, please try again later</span>");
         $("#term1").text("");
         $("#term2").text("");
    });

    return false;
}

function drawLengthButtons() {
    for (i = 8; i <= 30; i++) {
        $("#length-buttons").append(
            "<button type='button' class='btn btn-default' onclick='return populatePasswordAndTerms(" + i + ");'>" + i + "</button>"
        )
    }
}
