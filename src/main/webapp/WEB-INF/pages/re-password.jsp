<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="EN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Cinch Wallet</title>

<!-- Bootstrap CSS -->

<link href="<c:url value='/resources/css/bootstrap.min.css'/>" rel="stylesheet">
<!-- start custom css -->
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/css.css'/>">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body onload='document.resetForm.username.focus();'>

<div class="main1">
	<div id="main">

	<!-- start header -->
	<header>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					<div class="logo"><img src="<c:url value="/resources/images/logo.png"/>"/>	</div>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					<h3>CinchWallet Point of Sale</h3>
				</div>
			</div>
		</div>
		<div class="clr"></div>
	</header>
	<!-- end header -->

	<!-- start heading -->
	<div class="heading">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<h2>Cinch Wallet Terminal Provisioning</h2>
				</div>
			</div>
		</div>
		<div class="clr"></div>
	</div>
	<!-- end heading -->

	<div class="box">
		<div class="box-white"></div>
	</div>

	<!-- start main content -->
	<div class="main-content">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<form name='resetForm' action="<c:url value='/rstpass' />" method='POST'>

					<c:if test="${not empty error}">
						<div id="reset-pass-success" align="center" >
							<h2>${error}</h2>
						</div>
					</c:if>
					<c:if test="${not empty msg}">
						<div id="reset-pass-error" align="center" >
							<h2>${msg}</h2>
						</div>
					</c:if>


					<div class="login-bg">
						<div class="form-group">
							<input type="text" value="" name="username" id="username" class="form-control" placeholder="Username">
							<div class="clr"></div>
						</div>

						<div class="form-group">
							<button type="submit" value="submit" id="btnResetSubmit" class="btn btn-info btn-block login-btn">Submit</button>
							<div class="clr"></div>
						</div>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<p class="forgotText"><a href="<c:url value='/login' />">Login</a></p>
							</div>
						</div>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

						<div class="clr"></div>
					</div>
					</form>
				</div>
			</div>
		</div>
		<div class="clr"></div>
	</div>
	<!-- end main content -->

	<!-- start footer -->
	<footer>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					&copy; Copyright <span>2015</span>. <b>cinchwallet.com</b>, All Rights Reserved.
				</div>
			</div>
		</div>
		<div class="clr"></div>
	</footer>
	<!-- end footer -->
		
		<div class="clr"></div>
	</div>
	<div class="clr"></div>
</div>



<!-- jQuery -->
<script src="<c:url value='/resources/js/jquery.js'/>"></script>
<!-- Bootstrap JavaScript -->
<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/resources/js/main.js'/>"></script>

<script>
$(document).ready(function(){
    var pageheight = jQuery( '.main-content' ).height();
    if(pageheight<=500){
       jQuery('footer').addClass('pc');
       jQuery('#main').addClass('main-content1');
    }
});
</script>

</body>
</html>