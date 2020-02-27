<%@ include file="/init.jsp"%>

<input type="hidden" value="${blogId}" id="blogId"/>



<portlet:actionURL name="getBlogComments" var="getBlogComments">
		<portlet:param name="blogId" value="_replace_blogId" />
	</portlet:actionURL>

	<select required id="select_<portlet:namespace />" style="width: 300px;">
		<option value="">Selecione um post</option>
	</select>&nbsp;&nbsp;&nbsp;<a href="javascript:click_<portlet:namespace />()">listar
		comentários</a>
		
	<hr/>

<div style="display: none;" id="divtable_<portlet:namespace />">
	<table id="table_<portlet:namespace />" class="display">
		<thead>
			<tr>
				<th>id</th>
				<th>Mensagem</th>
				<th>Usuario</th>
				<th>Data</th>
				<th>Ref.</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>

<script>
	var blogPosts = ${blogPosts}
	blogPosts.forEach(function(item, index, arr) {
		var opt = new Option(item._title, item._entryId);
		if(item._entryId == $("#blogId").val()){
			opt.selected=true;
		}
		$("#select_<portlet:namespace />").append(opt);
	});

	function click_<portlet:namespace />() {
		var blogId = $("#select_<portlet:namespace />").val();
		
		var url = "${getBlogComments}";
		url = url.replace('_replace_blogId', blogId);
		window.location = url;
	}
</script>

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css" />
	
	<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/buttons/1.6.1/css/buttons.dataTables.min.css" />



<script
	src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/buttons/1.6.1/js/dataTables.buttons.min.js"></script>
<script
	src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.flash.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
<script
	src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.html5.min.js"></script>
<script
	src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.print.min.js"></script>


<link href="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/css/select2.min.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.min.js"></script>

<script>
	var blogComments = ${blogComments}
	
	function buildTable_<portlet:namespace />(f) {
		if (blogComments != null && blogComments != "") {
			var blogCommentsTable = document
					.getElementById("table_<portlet:namespace />");
			blogComments.forEach(function(item, index, arr) {
				var row = blogCommentsTable.tBodies[0].insertRow(0);
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				var cell3 = row.insertCell(2);
				var cell4 = row.insertCell(3);
				var cell5 = row.insertCell(4);
				cell1.innerHTML = item.id;
				cell2.innerHTML = item.message;
				
				cell3.innerHTML = item.userName;
				cell4.innerHTML = item.crateDate;
				cell5.innerHTML = item.answerOf;
				
			});
		}
		f();
	}

	buildTable_<portlet:namespace />(function() {
		$('#table_<portlet:namespace />').DataTable({
	        dom: 'Bfrtip',
	        buttons: [
	            'csv', 'excel', 'pdf'
	        ]
	    } );
	});
	
	$(document).ready(function() {
	    $('#select_<portlet:namespace />').select2();
	});
	
	if($("#blogId").val()!=""){
		$("#divtable_<portlet:namespace />").show();
	}
</script>

