$(document).ready(function() {
	$("#detailAchat").hide();
});

function updateLigneAchat(idAchat) {
			$.getJSON(
					"getStock",
					{
						idAchat : idAchat
					},
					function(data) {
						if (data) {
							var detailHtml = "";
							var json = $.parseJSON($("#json" + idAchat).text());
							if (json) {
								for (var index = 0, i = 0; index < json.length,
										i < data.length; index++, i++) {
									detailHtml += 
										"<tr>"+ 
										"<td>"+ data[i].article.idArticle+ "</td>" + 
										"<td>"+ json[index].article.designation+ "</td>" + 
										"<td>"+ json[index].prixUnitaireTTC+ "</td>" + 
										"<td>"+ json[index].quantite + "</td>"+ 
										"<td>" + (json[index].quantite) * (json[index].prixUnitaireTTC)+ "</td>" + 
										"<td>"+ data[i].quantite + "</td>" +
										"</tr>";
								}
							}
						}
						$("#detailAchatList").html(detailHtml);
					});
	$("#detailAchat").show("slow", function() {
		$("#detailAchat").show();
	});
}

function validerAchat(idAchat) {
	$.getJSON("achatValider", {
		idAchat : idAchat
	}, function(data) {
	});
	alert("L'achat a ete valide avec succes");
	window.location.href = "http://localhost:8080/test/achat/";
}

function rejeterAchat(idAchat) {
	$.getJSON("achatRejeter", {
		idAchat : idAchat
	}, function(data) {
	});
	alert("L'achat a ete bien rejete.");
	window.location.href = "http://localhost:8080/test/achat/";
}
