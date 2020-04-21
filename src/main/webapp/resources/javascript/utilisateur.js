$(document).ready(function(){
	$("#role").hide();
});


function updateDetailRole(idUtilisateur)
{
	var json = $.parseJSON($("#json" + idUtilisateur).text());
	var detailHtml ="";
		if(json)
		{
			for(var index = 0 ; index <json.length ; index ++)
			{
			detailHtml += 
						"<tr>"+
				           "<td>"+ idUtilisateur +"</td>"+
				           "<td>"+ json[index].roleName +"</td>"+
				        "</tr>";
			}	
			$("#detailRole").html(detailHtml);
			$("#role").show("slow", function(){$("#role").show();});
		}
}