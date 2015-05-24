<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery-2.1.3.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>
<style>
.table {
	counter-reset: rowNumber;
}

.table tr:not(:first-child ) {
	counter-increment: rowNumber;
}

.table tr td:first-child::before {
	content: counter(rowNumber);
	min-width: 1em;
	margin-right: 0.5em;
}

.table tr th:first-child::before {
	content: '#';
	min-width: 1em;
	margin-right: 0.5em;
}
</style>