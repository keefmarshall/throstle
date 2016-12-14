$(document).ready(function() {
    populatePasswordAndTerms();
    drawLengthButtons();

    new Clipboard('.clipbtn');
    $(".clipbtn").tooltip();
});

function populatePasswordAndTerms(length) {
    var url = (length != null) ? "/password/" + length + "?full" : "/password?full";
    $.getJSON(url, function(passRes) {
        $("#password").text(passRes.password);
        $("#term1").text(passRes.term1);
        $("#term2").text(passRes.term2);
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
