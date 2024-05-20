<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
  <%@include file="common/head.jsp" %>
</head>
<body class="hold-transition sidebar-collapse layout-top-nav">
<div class="wrapper">
  <%@include file="common/topnav.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <%@include file="common/header.jsp" %>
    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <H2 Protected API</H2>
        <button onclick="Page.click();"> Click Me </button>

      </div><!-- /.container-fluid -->
    </section><!-- /.content -->
  </div><!-- /.content-wrapper -->

  <%@include file="common/footer.jsp" %>
</div>
<!-- ./wrapper -->
<%@include file="common/foot.jsp" %>

<!-- page script -->
<script>
let Page = {
  click: function() {
    $.ajax({
      url: '/zsecurity/api/products',
      type: 'GET',
      contentType: 'application/json',
      headers: {
        "Accept": "application/json",
        "Content-type": 'application/json; charset=utf-8', //set specific content type
        "Authorization" : Common.getAccessToken(),
      },
      success: function(data) {
        Common.showSuccess(data);
      },
      error: function(err) {
        Common.showError(err);
      }
    });
  }
}
</script>

</body>
</html>