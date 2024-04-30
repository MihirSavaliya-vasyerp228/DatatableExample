<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Student Data</title>
<!-- Include Bootstrap CSS (optional) -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- Include jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Include DataTables CSS -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.css">
<!-- Include DataTables JS -->
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.js"></script>
</head>
<body>
	<div class="container">
		<h2>Student Data</h2>
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
			                        return JSON.stringify(d);
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
			                ],
			            });
			        }
			    };
			}();

			
			    datatableFunction.init();
			

		});
	</script>
</body>
</html>
