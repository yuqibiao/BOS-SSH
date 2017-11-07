function showDialog(title,msg) {
	$("#myModal").find(".modal-header h3").html(title);
    $("#myModal").attr('class','modal');
    $("#myModal").find(".modal-body p").html(msg);
    setTimeout(function(){
        $("#myModal").attr('class','modal hide');
    },3000);
}
