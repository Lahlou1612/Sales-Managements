$(document).ready(function() {
	$("#detailVente").hide();

});

function updateLigneVente(idVente) {
	$
			.getJSON(
					"getStock",
					{
						idVente : idVente
					},
					function(data) {
						if (data) {
							var detailHtml = "";
							var json = $.parseJSON($("#json" + idVente).text());
							if (json) {
								for (var index = 0, i = 0; index < json.length,
										i < data.length; index++, i++) {
									detailHtml += "<tr>" + "<td>"
											+ data[i].article.idArticle
											+ "</td>" + "<td>"
											+ json[index].article.designation
											+ "</td>" + "<td>"
											+ json[index].prixUnitaireTTC
											+ "</td>" + "<td>"
											+ json[index].quantite + "</td>"
											+ "<td>" + (json[index].quantite)
											* (json[index].prixUnitaireTTC)
											+ "</td>" + "<td>"
											+ data[i].quantite + "</td>";
									if (data[i].quantite >= json[index].quantite) {
										detailHtml += "<td style='color : #18C912; font-weight:900;' >Disponible</td>"
												+ "</tr>";
									} else {
										detailHtml += "<td style='color : #F33843; font-weight:900;' >Non Disponible</td>"
												+ "</tr>";
									}
								}
							}
						}
						$("#detailVenteList").html(detailHtml);
					});
	$("#detailVente").show("slow", function() {
		$("#detailVente").show();
	});

}

function validerVente(idVente) {
	$
			.getJSON(
					"getStock",
					{
						idVente : idVente
					},
					function(data) {

						if (data) {
							var json = $.parseJSON($("#json" + idVente).text());
							var result = 0;
							if (json) {
								for (var index = 0, i = 0; index < json.length,
										i < data.length; index++, i++) {
									if (data[i].quantite >= json[index].quantite) {
										result = 1;
									} else {
										alert("Veuillez verifier s'il vous plait votre Stock !");
										result = 0;
										window.location.href = "http://localhost:8080/test/vente/";
										break;
									}
								}
								if (result != "0") {
									$.getJSON("venteValider", {
										idVente : idVente
									}, function(data) {
									});
									alert("La vente a ete validee avec succes");
									window.location.href = "http://localhost:8080/test/vente/";
								}
							}
						}
					});
}

function rejeterVente(idVente) {

	$.getJSON("venteRejeter", {
		idVente : idVente
	}, function(data) {
	});
	alert("La vente a ete bien rejetee.");
	window.location.href = "http://localhost:8080/test/vente/";
}

