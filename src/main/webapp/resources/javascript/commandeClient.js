$(document).ready(function(){
	$("#idArticle_search").on("keypress",function(e){
		if(e.which == '13'){
			var codeArticle=$("#idArticle_search").val();
			if(codeArticle){
				searchArticle(codeArticle);
			}
		}
	});
});


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
				           "<td>0</td>"+
				        "</tr>";
			}	
			$("#detailCommande").html(detailHtml);
		}
}

function searchArticle(codeArticle){
	if(codeArticle){ 
		var detailHtml="";
	
		$.getJSON("detailArticle",{
			codeArticle : codeArticle,
			ajax : true
		}, 
		function(data){
			if(data){
				detailHtml += 
					"<tr>"+
			           "<td>"+ data.codeArticle +"</td>"+
			           "<td>1</td>"+
			           "<td>"+ data.prixUnitaireTTC +"</td>"+
			           "<td>0</td>"+
			        "</tr>";
				$("#detailNouvelleCommande").append(detailHtml);
			}
			else{
					alert('Article non trouvé !');
			}
		}
		);
	}
}

