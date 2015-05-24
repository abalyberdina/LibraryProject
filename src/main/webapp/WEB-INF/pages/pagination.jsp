<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="firstUrl" value="?page=1&size=${size}&query=${query}" />
<c:url var="lastUrl"
	value="?page=${pageTotal}&size=${size}&query=${query}" />
<c:url var="prevUrl"
	value="?page=${currentIndex - 1}&size=${size}&query=${query}" />
<c:url var="nextUrl"
	value="?page=${currentIndex + 1}&size=${size}&query=${query} " />

<c:if test="${pageTotal > 0 and currentIndex <= pageTotal}">
<nav>
	<ul class="pagination">
		<c:choose>
			<c:when test="${currentIndex == 1}">
				<li class="disabled"><a href="#">&lt;&lt;</a></li>
				<li class="disabled"><a href="#">&lt;</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${firstUrl}">&lt;&lt;</a></li>
				<li><a href="${prevUrl}">&lt;</a></li>
			</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
			<c:url var="pageUrl" value="?page=${i}&size=${size}&query=${query}" />
			<c:choose>
				<c:when test="${i == currentIndex}">
					<li class="active"><a href="${pageUrl}"><c:out
								value="${i}" /></a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:choose>
			<c:when test="${currentIndex == pageTotal}">
				<li class="disabled"><a href="#">&gt;</a></li>
				<li class="disabled"><a href="#">&gt;&gt;</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${nextUrl}">&gt;</a></li>
				<li><a href="${lastUrl}">&gt;&gt;</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</nav>
</c:if>