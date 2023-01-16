<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>payment.jsp 소스 코딩</title>
 <!-- jQuery -->
  <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
  <!-- iamport.payment.js -->
  <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
  
  <script type="text/javascript">
  
  	function fn_buy() {
		var IMP = window.IMP;
		IMP.init('imp52103657'); //iamport 대신 자신의 "가맹점 식별코드"를 사용하시면 됩니다
		// IMP.request_pay(param, callback) 결제창 호출
	      IMP.request_pay({ // param
	          pg: "html5_inicis",
	          pay_method: "card",
	          merchant_uid: 'merchant_' + new Date().getTime(),
	          name: "goods",
	          amount: 100,
	          buyer_email: "star@nate.com",
	          buyer_name: "장나라",
	          buyer_tel: "010-1234-5678",
	          buyer_addr: "서울특별시 강남구 신사동",
	          buyer_postcode: "01111",
	          m_redirect_url : "/paymentDone.do"
	      }, function (rsp) { // callback
	          if (rsp.success) {
	             var paymentInfo = {
	            		 imp_uid : rsp.imp_uid,
	            		 merchant_uid : rsp.merchant_uid,
	            		 paid_amount : rsp.paid_amound,
	            		 apply_num : rsp.apply_num,
	            		 paid_at : new Date()
	             };
	           
	          $.ajax({
	        	 url : "/paymentProcess.do",
	        	 method : "POST",
	        	 contentType : "application/json",
	        	 data : JSON.stringify(paymentInfo),
	        	 success:function(data, textStatus){
	        		 console.log(paymentInfo);
	        		 location.href = "/paymentDone.do";
	        	 },
	        	 error : function(e) {
					console.log(e);
				}        	  
	          });
	        	  
	          } else {
	              alert("결제 실패 : " + rsp.error_msg);	        	  
	          }
	      });
	    }
</script>
</head>
<body>
	<button type="button" onclick="fn_buy()">결제하기</button>
	<br><br>
	<a href="goMain.do">[처음으로]</a>


</body>
</html>




