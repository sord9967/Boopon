  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@taglib prefix="st" uri="http://www.springframework.org/tags" %>
  <%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
  <html>
  <head>
    <title>Boopon!噗胖!提供各式網路書店折價券,書局,優惠券,coupon</title>
  
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" >
  <meta name="description" content="Boopon提供各式網路書店折價券,包括博客來,金石堂,誠品,TAZZE,等網路書店">
  <meta name="keywords" content="coupon,E-coupon,折價券,優惠券,書局折價,金石堂,博客來,網路書店">
  <link rel="icon" href="images/icon.ico" type="image/x-icon">
  <link href="./css/js-image-slider.css" rel="stylesheet" type="text/css" />
  <script src="js/js-image-slider.js" type="text/javascript"></script>
  <script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
  <script type="text/javascript" src="js/jquery.boopon.js"></script>
  <script type="text/javascript" src="./js/jquery.zclip.min.js"></script>
  <script type="text/javascript" src="./js/jquery.plugin.min.js"></script> 
  <script type="text/javascript" src="./js/jquery.datepick.min.js"></script>
  <script type="text/javascript" src="./js/jquery.datepick-zh-TW.js"></script>
  <script type="text/javascript" src="./js/jquery.mockjax.js"></script>
  <script type="text/javascript" src="js/jquery.validate.min.js"></script>
  <script type="text/javascript" src="js/messages_zh_TW.js"></script>
  
  <link rel="stylesheet" type="text/css" href="./css/boopon.css">
  <link rel="stylesheet" type="text/css" href="./css/datepick.css">
  <script>
	<!-- 
    $(document).ready(function(){ 
		$("html").initMouseMenu({
			bindDom:'html',
			divId:'mouseMenu',
			menuLength:80,
			menuHeight:20,
			menu:'[{"url":"https://www.facebook.com/pages/Boopon/268523623334146?ref=profile","image":"images/fb.png","styleImg":"fbMenu","title":"fb粉絲團"},{"url":"http://www.taaze.tw/apredir.html?124231094/http://www.taaze.tw","image":"images/taaze.png","styleImg":"taaze","title":"TAAZE讀冊生活"},{"url":"http://www.books.com.tw/","image":"images/bokelei.png","styleImg":"bokelei","title":"博客來"}]'
		});
		//	$.datepick.setDefaults({pickerClass: 'datepick'});
        //$("#validDate").datepick();
  		$("#validDate").datepick({dateFormat: 'yyyy/mm/dd'});

	
	<c:forEach var="coupon" items="${couponList}">
	
		$("#couponDataGrid").createCoupon({
			company: '<c:out value="${coupon.company}"/>',
			serialNum: '<c:out value="${coupon.serialNumber}"/>',
			price: '<c:out value="${coupon.discount}"/>',
			limitPrice: '<c:out value="${coupon.limitPrice}"/>',
			effectiveDate: '<c:out value="${coupon.validDate}"/>',
			type:'<c:out value="${coupon.type}"/>',
			availableRate :'<c:out value="${coupon.rate}"/>',
			messageNum:'<c:out value="${coupon.messageNum}"/>'
		});
		
		dealWithUsedBut('<c:out value="${coupon.used}"/>','<c:out value="${coupon.type}"/>','<c:out value="${coupon.serialNumber}"/>');
		copySerialNumber('<c:out value="${coupon.serialNumber}"/>');
	</c:forEach>
	
	<c:forEach var="msg" items="${msgList}">
		$("#comment_"+'<c:out value="${msg.coupon_serial_number}"/>').appendCouponMessage({message: '<c:out value="${msg.coupon_message}"/>'});	
	</c:forEach>
	

	

    });
	function openComment(comment){
		$('#'+comment).toggle('swing');
	}
	function dealWithUsedBut(used,type,serialNum){
		
		if(type=="可重複"){
			$("#report_"+serialNum).hide();
			return;
		}
		
		
		if(1==used){//used
			$("#report_"+serialNum).val("已使用");
			$("#report_"+serialNum).attr("disabled",true);

			return;
		}
	}
	
	function shareCoupon(){
		var serialNum = $("#serialNumber").val();
		var company = $("#company option:selected").text();
		var type = $("#couponType option:selected").text();
		var price = $("#couponPrice").val();
		var validDate = $("#validDate").val();
		var limitPrice = $("#limitPrice").val();
		if($("#shareForm").valid()){
			$.shareNewCoupon({
				url: 'shareCoupon',
				serialNum: serialNum,
				company: company,
				type: type,
				price: price,
				limitPrice:limitPrice,
				validDate: validDate
			});

			
		}
		
	}
	function getSerialNum(obj,attr){
		var s = $(obj).attr(attr);
		return  s.substr(s.indexOf("_")+1,s.length);
	}
	
	function reportUsed(obj){
		var serial = getSerialNum(obj,"name");
		
		
		$.reportUsed({
			url: 'reportUsed',
			serialNum: serial	
		});
			
		
	}
	
	
	
	function voting(obj,isUp){
		//同個人同張COUPON 每個SESSION只能按一次		
		var upOrDown = "DOWN";
		if(isUp){
			upOrDown="UP";
		}
		var serial = getSerialNum(obj,"name");
		
		$.voting({
			url: 'vote',
			upOrDown: upOrDown,			
			serialNum: serial
			});

		
		
		
	}
	
	function refreshClip(){
		$("div [id*='copy_']").each(function (index,ele){
			var serialNum = getSerialNum(ele,"id");
			copySerialNumber(serialNum);
			
		});
		
		
	}
	
	function addMessage(obj){
		
		var textObj = $(obj).prev().children();
		var serial = getSerialNum(textObj,"name");
		$.addMessage({
			url: 'addMessage',
			serialNum: serial,
			message: textObj.val()
		});
		
		
	}

	function couponInfoChange(serialNum,text){
			$("#coupon_"+serialNum).children().each(function(index, element) {
				if($(element).attr("id")!= 'couponMenu_'+serialNum){
				   $(element).hide();

				}
            });
			$("#"+text).fadeIn(200);
	}
	

	function copySerialNumber(serialNum){

		$("#copy_"+serialNum).zclip({
			path: "http://www.steamdev.com/zclip/js/ZeroClipboard.swf",
			copy: function(){   return $("#copy_"+serialNum).val();}
			,
			afterCopy:function(){
				$(this).css('color','red');
				//alert("已複製至剪貼簿");
			}
		});

	}
	
	
	function searchCoupon(){
		$("#searchForm").submit();
	}
	
	//-->
   </script>
  </head>
  <body>

  	<div id="topist" class="topist">
            <div class="navigation2">
  		 	<div id="couponShare" class="form_navigation">
  		 	    <div class="visitorNum">
                	目前瀏覽人數:<c:out value="${visitorNum}"/> 
                	今日人數:<c:out value="${todayVisitorNum}"/>
                </div>
  			   <form id="shareForm" action="" type="POST"> 
                    
                    
                    <label>序號:</label>
                	<input id="serialNumber" name="serialNumber" value="" type="text" required  title="優惠券的序號"/>                    
                    <label>&nbsp;&nbsp;&nbsp;金額:</label>
                	<input id="couponPrice" name="couponPrice" value="" type="text" required  class="digits"  title="可折價的金額"/>
                    <label>&nbsp;&nbsp;&nbsp;滿</label>
                	<input id="limitPrice" name="limitPrice" value="" type="text" required  class="digits"  title="消費多少即可使用"/>
            		<label>可用</label>
                    <label>&nbsp;&nbsp;&nbsp;性質:</label>
                    <select id="couponType" title="只能用一次或者可重複使用">
                    	<option value="once" selected="selected"  >一次性</option>
                        <option value="repeat" >可重複</option>
                        <option value="repeat" >每帳戶可用一次</option>
                    </select>
                    
                    <label>&nbsp;&nbsp;&nbsp;廠商:</label>
                    <select id="company"  title="哪家書店">
                    	
                    	<c:forEach var="com" items="${applicationScope.comList}">							
							<option value='<c:out value="${com.companyName}"/>'><c:out value="${com.companyName}"/></option>
						</c:forEach>
                    </select>
                    
					<label>&nbsp;&nbsp;&nbsp;到期日:</label>
                	<input id="validDate"  value="" type="text"  required  title="何時到期"/> 
            		<input id="submitShare" value="分享" type="button" onClick="shareCoupon()"/>
            	</form>

  			</div>	<!-- couponShare-->
        </div><!--navigation2-->
 	    <div class="navigation">
 			 <div class="logo">
 			 	<a href="/">
  					<img src="./images/logo.png" border="0">
                </a>

				
 			</div>
<iframe src="//www.facebook.com/plugins/like.php?href=https://www.facebook.com/pages/Boopon/268523623334146?ref=profile&amp;width&amp;layout=button_count&amp;action=like&amp;show_faces=true&amp;share=true&amp;height=21" scrolling="no" frameborder="0" style="border:none; overflow:hidden; height:21px;" allowTransparency="true"></iframe>     
          </div><!--navigation-->
                <div class="description">
                	<p>歡迎來到 <font class="booponFont"><b>Boopon!</b></font> </p>
                    <p>Boopon分享各個書店的折價券，請大家利用上方功能多多分享</p>
                    <p>如果使用完有拿到新的優惠券請舉手之勞分享給各網友</p>
                    <p>tip : 按下序號即可直接複製到剪貼簿</p>
                    <p>tip : 雙擊左鍵2次可出現FB粉絲團的連結，如有任何問題可以在那發問</p>
                    <p>如有任何疑問或者合作提案也可寄信至<a href="mailto:boopon2014@gmail.com"><img src="./images/gmail.png"></a></p>
                    
                </div>
    <div class="ads">
         	<div class="adDesc">
        	<p>如果想要購書，請使用右方連結進入</p>
            <p>每買一本書將會支持本站繼續營運，謝謝您的支持!</p>
            <p>&nbsp;</p>
            <p>( 當點進去網址時會附上本站的ID，購書完畢後將會以</p>
            <p>    你結帳的價格抽點零頭給本站，當然對您的購書金</p>
            <p>    額毫無影響 )</p>
        </div><!-- adDesc-->
    	<div class="ad" id="slider">
    		 <a href="http://www.taaze.tw/apredir.html?ap124231094_b_1000273375" target="_blank" title="幾米創作時最深沈的思考與取捨，也是創作祕笈的全面公開">
    		 <img src="http://media.taaze.tw/showBanaerImage.html?pk=1000260072" border=0/></a>
    		 <a href="http://www.taaze.tw/apredir.html?ap124231094_b_1000274255" target="_blank" title="這個夏天，你和有你的記憶，將成為我生命中難忘的美好。">
    		 <img src="http://media.taaze.tw/showBanaerImage.html?pk=1000260913" border=0/></a>
    		 <a href="http://www.taaze.tw/apredir.html?ap124231094_b_1000248075" target="_blank" title="散文節、臺北藝術節、小農學堂。工作DNA、創業學堂。理查.葉慈、《失嬰記》。">
        	 <img src="http://media.taaze.tw/showBanaerImage.html?pk=1000259192" border=0/></a>
    		 <a href="http://www.taaze.tw/apredir.html?ap124231094_b_1000270375" target="_blank" title="如果生活中毒，那就閱讀│2014時報全書系書展，任選兩本75折/最低單本66折">
             <img src="http://media.taaze.tw/showBanaerImage.html?pk=1000257595" border=0/></a>
    		 <a href="http://www.taaze.tw/apredir.html?ap124231094_b_1000267955" target="_blank" title="哇！是夏季大三角，帶一本書去旅行吧 ～～">
    		 <img src="http://media.taaze.tw/showBanaerImage.html?pk=1000258772" border=0/></a>
    		 <a href="http://www.taaze.tw/apredir.html?ap124231094_b_1000271835" target="_blank" title="主題書展/雜誌69元加價購/商業書TOP100/講座活動｜7/10-8/25 全面79折">
    	     <img src="http://media.taaze.tw/showBanaerImage.html?pk=1000258592" border=0/></a>
        	 <a href="http://www.taaze.tw/apredir.html?ap124231094_b_1000270875" target="_blank" title="今年夏天，我要讀....">
        	 <img src="http://media.taaze.tw/showBanaerImage.html?pk=1000259732" border=0/></a>
        	 <a href="http://www.taaze.tw/apredir.html?ap124231094_b_1000273655" target="_blank" title="安倍喊出的一億日本人口高標，也許最終只是他的癡夢，無法達成。那台灣的夢呢？台灣的未來呢？">
        	 <img src="http://media.taaze.tw/showBanaerImage.html?pk=1000260252" border=0/></a>
        	 <a href="http://www.taaze.tw/apredir.html?ap124231094_b_1000272435" target="_blank" title="編輯百選，三書75折！年度散文導讀 x 華文朗讀節 x 免費聯文電雜，切換說故事的分身！">
        	 <img src="http://media.taaze.tw/showBanaerImage.html?pk=1000259152" border=0/></a>
        	 <a href="http://www.taaze.tw/apredir.html?ap124231094_b_1000273476" target="_blank" title="我們曾有過軀體、願望、夢想。我們不願，人們將我們遺忘。">
        	 <img src="http://media.taaze.tw/showBanaerImage.html?pk=1000260116" border=0/></a>
        </div>
    </div>


      <div class="couponDatas" id="couponDatas">
            <div class="couponSearch">
                 <form id="searchForm" name="searchForm" action="searchCoupon" method="POST" > 
                	<select id="searchText" name="searchText" title="哪家書店">
                    	
                    	<c:forEach var="com" items="${applicationScope.comList}">							
							<option value='<c:out value="${com.companyName}"/>'   <c:if test="${searchParam==com.companyName}">selected</c:if>><c:out value="${com.companyName}"/></option>
						</c:forEach>

                    </select>
           		  	<input id="submitSearch"  value="搜尋" type="button" onClick="searchCoupon()" />
    
  				</form>
      		</div><!-- couponSearch --> 
  			<!--<h2 class="listH2">折價券</h2>-->

            <div class="couponDataGrid" id="couponDataGrid"> 

            



         </div><!--couponDataGrid-->
            
  		</div><!-- couponDatas -->

	</div><!--topist -->
  </body>
  </html>