<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Student Data</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.css">
		<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.js"></script>
	</head>
	<body>
		<div class="container">
			<h2>Student Data</h2>
			<input type="text" id="searchInput" placeholder="Search Data Here..."/>
			<table id="studentTable" class="table table-striped">
				<thead>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Gender</th>
						<th>Email</th>
						<th>Phone</th>
						<th>City</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>

		<script>
			$(document).ready(function() {
				var datatableFunction = function() {
					return {
						init: function() {
							dTable = $("#studentTable").DataTable({
								"responsive": true,
								"pageLength": 10,
								"language": {
									lengthMenu: "Display _MENU_"
								},
								"lengthMenu": [[10, 25, 50, 100, 250, 500, 1000, -1], [10, 25, 50, 100, 250, 500, 1000, "All"]],
								"searchDelay": 500,
								"processing": true,
								"serverSide": true,
								ajax: {
									url: "<%=request.getContextPath()%>/api/shivam/datatable",
									type: "POST",
									contentType: "application/json",
									data: function(d) {
										var searchData = {
											searchValue: $('#searchInput').val(),
											length: d.length,
											start: d.start,
											draw: d.draw
										};
										return JSON.stringify(searchData);
									}
								},
								columns: [
									{ data: "firstName" },
									{ data: "lastName" },
									{ data: "gender" },
									{ data: "email" },
									{ data: "phone" },
									{ data: "city" }
								],
								columnDefs:[
									{
										targets: 0,
										orderable: !1,
										render: function (a, e, t, n) {
											console.log(t.firstName);
											return t.firstName;
										}
									},
									{
										targets: 1,
										orderable: !1,
										render: function (a, e, t, n) {
											console.log(t.lastName);
											return t.lastName;
										}
									},
									{
										targets: 2,
										orderable: !1,
										render: function (a, e, t, n) {
											console.log(t.gender);
											return t.gender;
										}
									},
									{
										targets: 3,
										orderable: !1,
										render: function (a, e, t, n) {
											console.log(t.email);
											return t.email;
										}
									},
									{
										targets: 4,
										orderable: !1,
										render: function (a, e, t, n) {
											console.log(t.phone);
											return t.phone;
										}
									},
									{
										targets: 5,
										orderable: !1,
										render: function (a, e, t, n) {
											console.log(t.city);
											return t.city;
										}
									}
								],
							});
							$('#searchInput').on('input', function() {
								// Reload DataTable when input changes
								dTable.ajax.reload();
							});
						}
					};
				}();
				datatableFunction.init();
			});
		</script>
	</body>
</html>
