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
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/jquery-ui.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/font-awesome.css'/>">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

<script type="text/javascript">
$(document).ready(function(){

	$("#phone").keydown(function(e){      
      var numval=$("#phone").val();      
      if (!((e.keyCode >= 48 && e.keyCode <= 57) || 
        (e.keyCode >= 96 && e.keyCode <= 105) || 
        (e.keyCode >= 37 && e.keyCode <= 40) || 
        (e.keyCode==8) || (e.keyCode==9) || (e.keyCode==46) || 
        (e.keyCode==17) || (e.keyCode == 65 && e.ctrlKey === true) || ((e.which == 67 || e.which == 99) && e.ctrlKey === true) || 
        ((e.which == 120 || e.which == 88) && e.ctrlKey === true))) {  
		 
			 var strng=document.getElementById("phone").value;
				document.getElementById("phone").value=strng.substring(0,strng.length-1);  
          		return false;          
        }else{ var value = $(this).val();       
          
        }
      
    });
	
    /*card number */
    $("#Card_Number").keydown(function(e){      
      var numval=$("#Card_Number").val();      
      if (!((e.keyCode >= 48 && e.keyCode <= 57) || 
        (e.keyCode >= 96 && e.keyCode <= 105) || 
        (e.keyCode >= 37 && e.keyCode <= 40) || 
        (e.keyCode==8) || (e.keyCode==9) || (e.keyCode==46) || 
        (e.keyCode==17) || (e.keyCode == 65 && e.ctrlKey === true) || ((e.which == 67 || e.which == 99) && e.ctrlKey === true) || 
        ((e.which == 120 || e.which == 88) && e.ctrlKey === true))) {    
        var strng=document.getElementById("Card_Number").value;
				document.getElementById("Card_Number").value=strng.substring(0,strng.length-1);  
          return false;          
        }else{ var value = $(this).val();       
          
        }
      
    });
    /*card number */
	
	
	 /*cardnum */
    $("#cardnum").keydown(function(e){      
      var numval=$("#cardnum").val();      
      if (!((e.keyCode >= 48 && e.keyCode <= 57) || 
        (e.keyCode >= 96 && e.keyCode <= 105) || 
        (e.keyCode >= 37 && e.keyCode <= 40) || 
        (e.keyCode==8) || (e.keyCode==9) || (e.keyCode==46) || 
        (e.keyCode==17) || (e.keyCode == 65 && e.ctrlKey === true) || ((e.which == 67 || e.which == 99) && e.ctrlKey === true) || 
        ((e.which == 120 || e.which == 88) && e.ctrlKey === true))) {    
        var strng=document.getElementById("Card_Number").value;
				document.getElementById("Card_Number").value=strng.substring(0,strng.length-1);  
          return false;          
        }else{ var value = $(this).val();       
          
        }
      
    });
    /*cardnum */
	
	
	
		 /*cardnum */
    $("#ammount").keydown(function(e){      
      var numval=$("#cardnum").val();      
      if (!((e.keyCode >= 48 && e.keyCode <= 57) || 
        (e.keyCode >= 96 && e.keyCode <= 105) || 
        (e.keyCode >= 37 && e.keyCode <= 40) || 
        (e.keyCode==8) || (e.keyCode==9) || (e.keyCode==46) || 
        (e.keyCode==17) || (e.keyCode == 65 && e.ctrlKey === true) || ((e.which == 67 || e.which == 99) && e.ctrlKey === true) || 
        ((e.which == 120 || e.which == 88) && e.ctrlKey === true))) {    
        var strng=document.getElementById("Card_Number").value;
				document.getElementById("Card_Number").value=strng.substring(0,strng.length-1);  
          return false;          
        }else{ var value = $(this).val();       
          
        }
      
    });
    /*cardnum */
	
	
	
});
</script>
</head>
<body>
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
          <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
            <h3>Cinch Wallet Point of Sale</h3>
          </div>
          <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
            <h4>Welcome : ${user.merchantName}</h4>
            <h5>${user.storeId}/${user.storeName}</h5>
            <input type="hidden" name="mid" id="mid" value="${user.merchantId}" />
            <input type="hidden" name="sid" id="sid" value="${user.storeId}" />
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
            <div class="row">
              <div class="col-xs-12 col-sm-4 col-md-3 col-lg-3">
                <nav class="nav-sidebar">
                  <ul class="nav tabs">
                    <!-- <li class="active"><a href="#dashboard" data-toggle="tab">Dashboard</a></li> -->
                    <li class=""><a href="#registration" data-toggle="tab">Registration</a></li>
                    <li class=""><a href="#bal-inq" data-toggle="tab">Balance Inquiry</a></li>
                    <li class=""><a href="#earn-points" data-toggle="tab">Earn Points</a></li>
                    <li class=""><a href="#burn-points" data-toggle="tab">Burn Points</a></li>
                  </ul>
                  	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
                  <p><a href="javascript:formSubmit()" class="btn btn-info btn-block logout-btn">Logout</a></p>
                </nav>
              </div>
              <!-- end sidebar -->
              <div class="col-xs-12 col-sm-8 col-md-9 col-lg-9">
                <div class="tab-content">
                  <div class="tab-pane active" id="dashboard">
                    <h3>This is home screen</h3>
                    <p>Some welcome text will go here</p>
                    <div class="clr"></div>
                  </div>
                  <!-- end dashboard -->
                  
                  <div class="tab-pane" id="registration">
                    <form>
                      <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                          <div class="form-group">
                            <input type="text" name="fname" id="fname" placeholder="First Name" class="form-control" required>
                          </div>
                        </div>
                      </div>
                      <!-- end item -->
                      
                      <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                          <div class="form-group">
                            <input type="text" name="lname" id="lname" placeholder="Last Name" class="form-control" >
                          </div>
                        </div>
                      </div>
                      <!-- end item -->
                      
                      <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                          <div class="form-group">
                            <input type="email" name="email" id="email" placeholder="Email Id" class="form-control" pattern="[^ @]*@[^ @]*" >
                          </div>
                        </div>
                      </div>
                      <!-- end item -->
                      
                      <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                          <div class="form-group calender">
                            <input readonly type="text" class="form-control " id="datepicker" placeholder=" Date of Birth " >
                            <i class="fa fa-calendar"></i> </div>
                        </div>
                      </div>
                      <!-- end item -->
                      
                      <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                          <div class="form-group">
                            <input type="text" name="phone" id="phone" placeholder="Phone Number" class="form-control" maxlength="10">
                          </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" id="phoneDiv" style="display:none"> 
                          <p class="registrationText" id="phoneError">Phone Number OR Card Number is mandatory for registration</p> 
                        </div>
                      </div>
                      <!-- end item -->
                      
                      <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                          <div class="form-group">
                            <input type="text" name="address" id="address" placeholder="Address" class="form-control">
                          </div>
                        </div>
                      </div>
                      <!-- end item -->
                      
                      <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                          <div class="form-group">
                            <input type="text" name="cardNumber" id="cardNumber" placeholder="Card Number" class="form-control" maxlength="16">
                          </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" id="cardDiv" style="display:none"> 
                          <p class="registrationText" id="cardError"></p> 
                        </div>
                        
                      </div>
                      <!-- end item -->
                      
                      <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                          <button type="submit" value="submit" id="btnSave" class="btn btn-info btn-block login-btn">Submit</button>
                        </div>
                      </div>
                      <div class="clr"></div>
                    </form>
                  </div>
                  <!-- end registration -->
                  
                  <div class="tab-pane" id="reg-success">
                    <h2>Registration Successful !!!</h2>
                    <h3>Below are the customer detail:</h3>
                    <div class="row">
                      <div class="col-xs-12 col-sm-5 col-md-3 col-lg-3">
                        <p>Name :</p>
                      </div>
                      <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                        <p id="reg-success-name">Manoj Singh</p>
                      </div>
                    </div>
                    <div class="row" id="reg-success-card-row">
                      <div class="col-xs-12 col-sm-5 col-md-3 col-lg-3">
                        <p>Card Number :</p>
                      </div>
                      <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                        <p id="reg-success-card">5463-9489-5646-8939</p>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-xs-12 col-sm-5 col-md-3 col-lg-3">
                        <p>Phone Number :</p>
                      </div>
                      <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                        <p id="reg-success-phone">9810123123</p>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-xs-12 col-sm-5 col-md-3 col-lg-3">
                        <p>MembershipId :</p>
                      </div>
                      <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                        <p id="reg-success-membershipid">100</p>
                      </div>
                    </div>
                    
                    <div clas="clr"></div>
                  </div>
                  <!-- end balance inquiry -->
                  
                  <div class="tab-pane" id="earn-points">
                    <div class="row">
                      <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 fRight">
                        <p>Some help text might go here</p>
                      </div>
                      <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 fLeft">
                        <div class="form-group">
                          <label>Please enter card number in below box, or swipe the card.</label>
                          <input type="text" name="cardnum" id="cardnum" placeholder="Card Number/Phone Number" class="form-control" required>
                        </div>
                        <div class="form-group">
                          <input type="text" name="amount" id="ammount" placeholder="Amount" class="form-control" required>
                        </div>
                        <div class="row">
                          <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <ul class="calLi">
                              <li id="1" class="cal">1</li>
                              <li id="2" class="cal">2</li>
                              <li id="3" class="cal">3</li>
                              <li id="4" class="cal">4</li>
                              <li id="5" class="cal">5</li>
                              <li id="6" class="cal">6</li>
                              <li id="7" class="cal">7</li>
                              <li id="8" class="cal">8</li>
                              <li id="9" class="cal">9</li>
                              <li id="." class="cal">.</li>
                              <li id="0" class="cal">0</li>
                              <li id="" class="cal"><i class="fa fa-arrow-left"></i></li>
                              <div class="clr"></div>
                            </ul>
                          </div>
                        </div>
                        <div class="form-group">
                          <button type="submit" value="submit" id="btnEarnPoint" class="btn btn-info btn-block login-btn buttonsetting">Start Txn</button>
                        </div>
                      </div>
                    </div>
                    <div class="clr"></div>
                  </div>
                  <!-- end earn points -->

                  <div class="tab-pane" id="bal-inq">
                    <div class="row">
                      <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 fRight">
                        <p>Some help text might go here</p>
                      </div>
                      <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 fLeft">
                        <div class="form-group">
                          <label>Please enter card number in below box, or swipe the card.</label>
                          <input type="text" name="cardnum" id="cardnum" placeholder="Card Number" class="form-control" required>
                        </div>
                        <div class="form-group">
                          <input type="text" name="amount" id="ammount" placeholder="Amount" class="form-control" required>
                        </div>
                        <div class="row">
                          <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <ul class="calLi">
                              <li id="1" class="cal">1</li>
                              <li id="2" class="cal">2</li>
                              <li id="3" class="cal">3</li>
                              <li id="4" class="cal">4</li>
                              <li id="5" class="cal">5</li>
                              <li id="6" class="cal">6</li>
                              <li id="7" class="cal">7</li>
                              <li id="8" class="cal">8</li>
                              <li id="9" class="cal">9</li>
                              <li id="." class="cal">.</li>
                              <li id="0" class="cal">0</li>
                              <li id="" class="cal"><i class="fa fa-arrow-left"></i></li>
                              <div class="clr"></div>
                            </ul>
                          </div>
                        </div>
                        <div class="form-group">
                          <button type="submit" value="submit" class="btn btn-info btn-block login-btn buttonsetting">Start Txn</button>
                        </div>
                      </div>
                    </div>
                    <div class="clr"></div>
                  </div>
                  <!-- end balance inquiry 2-->

                  
                  <div class="tab-pane" id="burn-points">
                    <h2>Transaction Receipt</h2>
                    <p><img src="<c:url value="/resources/images/logo.png"/>"/></p>
                    <div class="row">
                      <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <table class="table table-striped table-receipt">
                          <tbody>
                            <tr>
                              <td>Merchant Detail</td>
                              <td>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</td>
                            </tr>
                            <tr>
                              <td>Txn date &amp; time</td>
                              <td>11/25/2012 and 5:50 PM</td>
                            </tr>
                            <tr>
                              <td>Other basic details</td>
                              <td>Lorem Ipsum is simply dummy text of the printing and typesetting industry</td>
                            </tr>
                            <tr>
                              <td>Txn Amount</td>
                              <td>$120</td>
                            </tr>
                            <tr>
                              <td>Points Available</td>
                              <td>100</td>
                            </tr>
                            <tr>
                              <td>Other optional/required details</td>
                              <td>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</td>
                            </tr>
                          </tbody>
                        </table>
                      </div>
                    </div>
                    <p>
                      <button type="submit" value="submit" class="btn btn-info login-btn"><i class="fa fa-print"></i> Print</button>
                      <button type="submit" value="submit" class="btn btn-info login-btn"> <i class="fa fa-check"></i> End Txn</button>
                    </p>
                    <div class="clr"></div>
                  </div>
                  <!-- end burn points --> 
                </div>
                <!-- tabber --> 
              </div>
            </div>
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
          <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> &copy; Copyright <span>2015</span>. <b>cinchwallet.com</b>, All Rights Reserved. </div>
        </div>
      </div>
      <div class="clr"></div>
    </footer>
    <!-- end footer -->
    
    <div class="clr"></div>
  </div>
  <div class="clr"></div>
</div>
<input type="hidden" name="input_type" value="cardnum" id="input_type">
<!-- jQuery --> 

<script src="<c:url value='/resources/js/jquery.js'/>"></script>

<!-- Bootstrap JavaScript --> 
<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/resources/js/jquery-ui.js'/>"></script>
<script src="<c:url value='/resources/js/main.js'/>"></script>
<script>
  $(function() {
    $( "#datepicker" ).datepicker({
      changeMonth: true,
      changeYear: true,
	  yearRange: '1975:2021'
    });
  });
  </script> 
<script>
$(document).ready(function(){
    var pageheight = jQuery( '.main-content' ).height();
    if(pageheight<=500){
       jQuery('footer').addClass('pc');
       jQuery('.main1').addClass('main-content1');
    }
	$("#cardnum").click(function(){
		$("#input_type").val('cardnum');
        		
		});
		$("#ammount").click(function(){
		$("#input_type").val('ammount');
        		
		});
	$(".cal").click(function(){
		var val = $(this).attr('id');
        var whr = $("#input_type").val();
		if(val !='')
		{
		 var nval = $("#"+whr+"").val();
		 nval = nval+val;
		 $("#"+whr+"").val(nval);
		}
		else
		{
		var nval = $("#"+whr+"").val();
		 nval = nval.substr(0, nval.length - 1);
		 $("#"+whr+"").val(nval);
		}
		});
});

</script>
</body>
</html>