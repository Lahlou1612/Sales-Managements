$(document).ready(function(){
	$("#tauxTVA").on("keyup", function(){
		TvaKeyUpFunction();
		TestEmpty();
	})
});

TvaKeyUpFunction = function(){
	var prixUnitaireHT = $("#prixUnitaireHT").val();
	var tauxTVA = $("#tauxTVA").val();
	var prixUnitaireTTC = parseFloat(parseFloat(prixUnitaireHT) * parseFloat(tauxTVA) / 100 + parseFloat(prixUnitaireHT));
	$("#prixUnitaireTTC").val(prixUnitaireTTC);
}
TestEmpty = function(){
	if($("#prixUnitaireTTC").val()=="NaN")
		{
			$("#prixUnitaireTTC").val(0);
		}
}