<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Student DataTable</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <!-- jQuery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <!-- DataTables CSS -->
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">
        <!-- DataTables JS -->
        <script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
    </head>
    <body>
    <h1>Student Table</h1>
    <table id="studentTable" class="table table-success table-striped">
        <thead>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Gender</th>
            <th>City</th>
        </tr>
        </thead>
    </table>

    <script>
        $(document).ready(function () {
            var datatable = $("#studentTable").DataTable({
                responsive: true,
                pageLength: 10,
                lengthMenu: [[10, 25, 50, -1], [10, 25, 50, "All"]],
                processing: true,
                serverSide: true,
                ajax: {
                    url: '<%=request.getContextPath()%>/api/datatable',
                    type: "POST",
                    contentType: "application/json",
                    data: function (d) {
                        return {
                            start: d.start,
                            length: d.length
                        };
                    }
                },
                columns: [
                    { data: 'firstName' },
                    { data: 'lastName' },
                    { data: 'email' },
                    { data: 'phone' },
                    { data: 'gender' },
                    { data: 'city' }
                ],
                columnDefs: [
                    {
                        targets: [0, 1, 2, 3, 4, 5],
                        orderable: true,
                        visible: true
                    }
                ]
            });
        });
    </script>
    </body>
</html>
