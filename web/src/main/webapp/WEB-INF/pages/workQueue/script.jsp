<script type="text/javascript" src="/leadtrac-web/resources/js/common/datatable.js"></script>
<script type="text/javascript" src="/leadtrac-web/resources/js/common/datamodel.js"></script>

<script type="text/javascript">
	$(function() {
		$("input[type=button][name=workLead]").on("click", function(e) {
		    $.ajax({
		        url : "service/workqueue/work",
		    }).done(function(xml) {
		        console.log(xml);
		    });
		});
		$("input[type=button][name=reviewLead]").on("click", function(e) {
		    $.ajax({
		        url : "service/workqueue/review",
		    }).done(function(xml) {
		        console.log(xml);
		    });
		});
		$("input[type=button][name=initiateLead]").focus().on("click", function(e) {
			self.location.href = "/leadtrac-web/initiateLead";
		});
	});
</script>