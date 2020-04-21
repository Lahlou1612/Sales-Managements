$(document).ready(function(){
//	$("#idArticle_search").on("keypress",function(e){
//		if(e.which == '13'){
//			var codeArticle=$("#idArticle_search").val();
//			if(verifierClient() && codeArticle){
//				searchArticle(codeArticle);
//			}
//		}
//	});
	
	$("#ajouterPanier").on("click",function(e){
		var codeArticle=$("#listArticle option:selected").val();
		var quantite = $("#quantite").val();
		if(codeArticle === "-1"){
			$("#notFoundMsg").show("slow", function(){$("#notFoundMsg").show();});
		}
		if(!quantite){
			$("#quatiteErrorMsg").show("slow", function(){$("#quatiteErrorMsg").show();});
		}
		if(verifierClient() && codeArticle && quantite){
			$("#quatiteErrorMsg").hide("slow", function(){$("#quatiteErrorMsg").hide();});
			var quantite = $("#quantite").val();
			searchArticle(codeArticle,quantite);
		}
	});
	
	$("#btnEnregistrerCommande").attr("disabled", true);
	$("#listClients").on("change",function(e){
		if(verifierClient()){
			$("#clientnotSelectedMsg").hide("slow", function(){$("#clientnotSelectedMsg").hide();});
			creerCommande($("#listClients option:selected").val());
		}
	});
	$("#listArticle").on("change",function(e){
		if(verifierClient()){
			$("#notFoundMsg").hide("slow", function(){$("#notFoundMsg").hide();});
		}
	});
	$("#tableauArticle").hide();
	$("#quatiteErrorMsg").hide();
	$("#notFoundMsg").hide();
	$("#clientnotSelectedMsg").hide();
	$("#unexpectedErrorMsg").hide();
});

function verifierClient(){
	var idClient= $("#listClients option:selected").val();
	if(idClient){
		if(idClient === "-1"){
			$("#clientnotSelectedMsg").show("slow", function(){$("#clientnotSelectedMsg").show();});
			return false;
		}
		return true;
	}
}
function searchArticle(codeArticle,quantite){
	if(codeArticle){ 
		var detailHtml="";
		$.getJSON("ajouterLigne",{
			codeArticle : codeArticle,
			quantite : quantite,
			ajax : true
		}, 
		function(data){
			if(data){
				var quantite = $("#quantite").val();
				var total =  quantite * data.prixUnitaire;
				if($("#qte"+data.article.idArticle).length > 0 && $("#total"+data.article.idArticle).length > 0){
					$("#qte"+data.article.idArticle).text(quantite);
					$("#total"+data.article.idArticle).text(total);
				}else{
						data.quantite = quantite;
						detailHtml += 
							"<tr  id='ligne"+data.article.idArticle+"'>"+
					           "<td>"+ data.article.codeArticle +"</td>"+
					           "<td id='qte"+data.article.idArticle+"'>"+ quantite +"</td>"+
					           
					           "<td>"+ data.prixUnitaire +"</td>"+
					           "<td id='total"+data.article.idArticle+"'>"+ total +"</td>"+
					           "<td><button class='btn btn-link' onclick='supprimerLigneCommande("+ data.article.idArticle +")'><i class='fas fa-trash'></i></button></td>"+
					           "</tr>";
						$("#detailNouvelleCommande").append(detailHtml);
						$("#notFoundMsg").hide("slow", function(){$("#notFoundMsg").hide();});
						//$("#idArticle_search").val("");
						$("#btnEnregistrerCommande").attr("disabled", false);
						$("#quantite").val("");
						$("#tableauArticle").show("slow", function(){$("#tableauArticle").show();});
			}}
		}
		).fail(function(){
			$("#notFoundMsg").show("slow", function(){$("#notFoundMsg").show();});
		});
	}

}

function creerCommande(idClient){	
	if(idClient){
	$.getJSON("creerCommande",{
		idClient: idClient,
		ajax : true
	},
	function(data){
		console.log("Client a été mis à jour !");
	});  
	}
}

function supprimerLigneCommande(idArticle){
	if($("#ligne"+idArticle).length>0){
		$.getJSON("supprimerLigne", {
			idArticle : idArticle,
			ajax : true
		},
		function(data){
			if(data){
				$("#ligne"+idArticle).hide("slow", function(){$("#ligne"+idArticle).remove()});
			}
		});
	}
	
}

function enregitrerCommandeClient(){
	$.getJSON("enregistrerCommande", function(data){
		});
	alert("Votre commande a ete bien ajoutee");
	window.location.href = "http://localhost:8080/test/commandeclient/";
}

function updateDetailCommande(idCommande)
{
	var json = $.parseJSON($("#json" + idCommande).text());
	var detailHtml ="";
		if(json)
		{
			for(var index = 0 ; index <json.length ; index ++)
			{
			detailHtml += 
						"<tr>"+
				           "<td>"+ json[index].article.codeArticle +"</td>"+
				           "<td>"+ json[index].quantite +"</td>"+
				           "<td>"+ json[index].prixUnitaire +"</td>"+
				           "<td>"+json[index].quantite * json[index].prixUnitaire+"</td>"+
				        "</tr>";
			}	
			$("#detailCommande").html(detailHtml);
		}
}

//function searchArticle(codeArticle){
//if(codeArticle){ 
//	var detailHtml="";
//	$.getJSON("ajouterLigne",{
//		codeArticle : codeArticle,
//		ajax : true
//	}, 
//	function(data){
//		if(data){
//			var total =  data.quantite * data.prixUnitaire;
//			if($("#qte"+data.article.idArticle).length > 0 && $("#total"+data.article.idArticle).length > 0){
//				$("#qte"+data.article.idArticle).text(data.quantite);
//				$("#total"+data.article.idArticle).text(total);
//			}else{
//					detailHtml += 
//						"<tr  id='ligne"+data.article.idArticle+"'>"+
//				           "<td>"+ data.article.codeArticle +"</td>"+
//				           "<td id='qte"+data.article.idArticle+"'>"+ data.quantite +"</td>"+
//				           "<td>"+ data.prixUnitaire +"</td>"+
//				           "<td id='total"+data.article.idArticle+"'>"+ total +"</td>"+
//				           "<td><button class='btn btn-link' onclick='supprimerLigneCommande("+ data.article.idArticle +")'><i class='fas fa-trash'></i></button></td>"+
//				           "</tr>";
//					$("#detailNouvelleCommande").append(detailHtml);
//					$("#notFoundMsg").hide("slow", function(){$("#notFoundMsg").hide();});
//					$("#idArticle_search").val("");
//					$("#btnEnregistrerCommande").attr("disabled", false);
//		}}
//	}
//	).fail(function(){
//		$("#notFoundMsg").show("slow", function(){$("#notFoundMsg").show();});
//	});
//}
//
//}