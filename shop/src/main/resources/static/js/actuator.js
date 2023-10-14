$(document).ready(function() {
	$("#healthBtn").on("click", function() {
		$.ajax({
			type : "GET",
			url : "/manage/healthcheck",
			contentType : "application/json; charset=UTF-8",
			headers : {
				"Authorization" : "Bearer " + ACCESS_TOKEN
			},
			success : function(data) {
				console.log(data);
				
				alert(JSON.stringify(data));
			},
			error : function(xhr, status, error) {
				alert("code: " + xhr.status + "\n"
					+ "message: " + xhr.reponseText + "\n"
					+ "error : " + error);
			}
		});
	});

	$("#metricsBtn").on("click", function() {
		$.ajax({
			type : "GET",
			url : "/manage/metrics",
			contentType : "application/json; charset=UTF-8",
			headers : {
				"Authorization" : "Bearer " + ACCESS_TOKEN
			},
			success : function(data) {
				console.log(data);
				
				alert(JSON.stringify(data));
			},
			error : function(xhr, status, error) {
				alert("code:" + xhr.status + "\n"
					+ "message:" + xhr.reponseText + "\n"
					+ "error:" + error
				);
			}
		});
	});
	
	$("#beansBtn").on("click", function() {
		$.ajax({
			type : "GET",
			url : "/manage/beans",
			contentType : "application/json; charset=UTF-8",
			headers : {
				"Authorization" : "Bearer " + ACCESS_TOKEN
			},
			success : function(data) {
				console.log(data);
				
				alert(JSON.stringify(data));
			},
			error : function(xhr, status, error) {
				alert("code:" + xhr.status + "\n"
					+ "message:" + xhr.reponseText + "\n"
					+ "error:" + error
				);
			}
		});
	});
});
