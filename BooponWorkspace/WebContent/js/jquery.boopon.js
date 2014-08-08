/**
20140614 
*/
function menuHover(obj){
			//$(obj).attr('src','images/fb_Hover.png')
}
;(function($){
		var menuFixed = false;
		var aX=0;
		var aY=30;
		var menuLength = 30;
		var menuHeight = 20;
		var menuHtml='';

		function appendMenu(url,img,styleImg,title){
			return "<div class='menus'><a href='"+url+"' Target='_new' title='"+title+"'><img   src='"+img+"' class='"+styleImg+"' onMouseOver='menuHover(this);'/></a></div>";
			
		}
	  	function followMouse(bindDom,divId){
		$(bindDom).mousemove(function(e) {
			
			$("#"+divId).remove();
			$("body").append("<div class='mouseDiv' style='position:absolute;z-index:20; height:"+menuHeight+"px; width:"+menuLength+"px; left:"+(e.pageX+aX)+"px;top:"+(e.pageY+aY)+"px;' id='"+divId+"'>"+menuHtml+"</div>");
			//$("#"+divId).fadeOut();
        
		});
			
	}
	function stopFollowMouse(bindDom){
		menuFixed=true;
		$(bindDom).unbind("mousemove");

	}
	function isClickMouseDiv(e,divId){
		var menu = $("#"+divId);
		var mp = menu.position();
		//alert("x mouse:"+e.pageX+" menu start:"+mp.left+" end:"+(mp.left+menuLength));
		//alert("y mouse:"+e.pageY+" menu start:"+mp.top+" end:"+(mp.top+menuHeight));
		if(e.pageX>mp.left&&e.pageX<(mp.left+menuLength)){
			
			if(e.pageY>mp.top&&e.pageY<(mp.top+menuHeight)){	
		
					return true;
			}
		}		
		return false;
		
	}
	$.fn.initMouseMenu = function(params){

		
		var content = $.extend({
				bindDom:'',
				divId:'',
				menuLength:'',
				menuHeight:'',
				menu:''
				
			},params);
			menuLength = content.menuLength;
			menuHeight = content.menuHeight;
			//alert(content.menu);
			var menu2 = $.parseJSON(content.menu);
			
			
			for( i=0;i<menu2.length;i++){
				menuHtml += appendMenu(menu2[i].url,menu2[i].image,menu2[i].styleImg,menu2[i].title);
			}
			
			
		return this.each ( function(){
				followMouse(content.bindDom,content.divId);
				var obj = this;
				$(content.bindDom).dblclick(function(e) {
                    stopFollowMouse(content.bindDom);
					$("#"+content.divId).css("opacity",1).hide().fadeIn("fast");
                });
						$(content.bindDom).mousedown(function(e) {
            					if(e.which==1){
									//alert("right");
									if(menuFixed &&  !isClickMouseDiv(e,content.divId)){
										followMouse(content.bindDom,content.divId);
										menuFixed=false;
								}
						}
        			});
			});
	
	};
	

	$.fn.createCoupon = function(params){
		var content = $.extend({
				company: '',
				availableRate: '',
				serialNum: '',
				price: '',
				effectiveDate: '',
				type:'',
				limitPrice:'',
				messageNum:''			
			},params);
		
		return this.each(function (){
			
				if(content.messageNum.length<=0){
					content.messageNum ='0';
				}
				var b = $(this);
				
				b.prepend(
						"				<div class='couponData' id='coupon_"+content.serialNum+"'>  "+
						"  "+
						"				  <div class='couponMenu' id='couponMenu_"+content.serialNum+"'> "+
						"                		<div class='infoImage' id='returnCoupon_"+content.serialNum+"' onClick='couponInfoChange(\""+content.serialNum+"\",\"couponMain_"+content.serialNum+"\")'></div> "+
						"                     	<div class='message' onClick='couponInfoChange(\""+content.serialNum+"\",\"comment_"+content.serialNum+"\")' > "+
						"                     		<div class='messageNum' id=messageNum_"+content.serialNum+"> "+
						"                         		"+content.messageNum+" "+
						"                        	 </div> "+
						"                     	</div> "+
						"                </div>"+
						"             	<div id='couponMain_"+content.serialNum+"'>			 "+
						"             	<div class='couponLeft' id='couponleft_"+content.serialNum+"'>  "+
						"                         <div class='coupongTitle'> "+
						"                   			<h3 class='listCoutponTitle'>"+content.company+"</h3> "+
						"                 		</div> "+
						"                         <div class='couponValid'> "+
						"                             <h3 class='listCouponValidSuccess' >可用率:<label id='rate_"+content.serialNum+"'>"+content.availableRate+"</label></h3> "+
						"                                                                                                  		                                 <div class='thumb needRadius'>  "+
						"                         			  "+
						"                             		 <div class='voteup' title='可用' name='voteUp_"+content.serialNum+"' onClick='voting(this,true)'></div>  "+
						"                                    <div class='votedown' title='失效'  name='voteDown_"+content.serialNum+"' onClick='voting(this,false)'></div> "+
						
						"                             	 </div><!-- thumb --> "+
						"                              "+
						"  "+
						"                 		</div> "+
						"   "+
						"                          "+
						"                 </div> "+
						"                 <div class='couponCenter'> "+
						"  "+
						"                 </div> "+

						"                 "+
						"                  "+
						"                  "+
						" 				<div class='couponRight'>  "+
						"  "+
						"                     <div class='couponInfo'> "+
						"                   			<h3 class='listCouponInfo'>序號: <input type='button' id='copy_"+content.serialNum+"' class='serialNumberBut' value="+content.serialNum+" onClick=''/> "+
						" </h3> "+
						"                   			<h3 class='listCouponInfo'>折價:$<font color='red'>"+content.price+"</font></h3> "+
						"                   			<h3 class='listCouponInfo'>有效期限:"+content.effectiveDate+"</h3> "+
						"                               <h3 class='listCouponInfo'>性質:"+content.type+"</h3> "+
						"                               <h3 class='listCouponInfo'>滿<font color='red'>"+content.limitPrice+"</font>即可使用</h3> "+
						"  "+
						"                               <input type='button' onClick='reportUsed(this)' name='report_"+content.serialNum+"' id='report_"+content.serialNum+"' value='回報使用'/> "+
						"  "+
						"                     </div> "+
						"  "+
						"                 </div> </div> "+
						"  "+
						"  "+
						"                  "+
						"                        	<div id='comment_"+content.serialNum+"' name='comment_"+content.serialNum+"' class='comment hideComments'>"+
						"              									<div class='commentArea'> "+
						"                 								<textarea id='commentTextArea' name='comment_"+content.serialNum+"'' cols='20		' rows='3' placeholder='請留言'></textarea>     "+         
						"                 							</div> "+
						" 				  										<input type='button' value='留言' class='messageBut' onClick='addMessage(this)' />"+
						"                   						<div class='space'></div>"+
						 "                 					</div> "+
						"             </div><!--CouponData --> "


);	
					
			
			});
			

		
	};
	
			$.fn.appendCouponMessage = function(params){
				var content = $.extend({
				message: ''
				},params);
				
				return this.each(function (){
					var b = $(this);
					b.append(
					"<div class='commentWord'> "+
" 						"+ content.message+" "+
"                      "+
"                 	</div> <div class='space'></div>"
					
					);	
						

				});
			};
			
		var shareNewCoupon = function(params){
				var content = $.extend({
					url:'',
					company: '',
					serialNum: '',
					price: '',
					limitPrice: '',
					validDate: '',
					type:''
				},params);
				/*
				alert("{\"serialNum\":\""+content.serialNum+
				    "\", \"company\":\""+content.company+
				    "\", \"price\":\""+content.price+
				    "\", \"validDate\":\""+content.validDate+
				    "\", \"type\":\""+content.type+
				    "\"}");
				    */
				$.ajax({ 
				    url: content.url, 
				    type: 'POST', 
				    dataType: 'json', 
				    data: "{\"serialNum\":\""+content.serialNum+
				    "\", \"company\":\""+content.company+
				    "\", \"price\":\""+content.price+
				    "\", \"validDate\":\""+content.validDate+
				    "\", \"type\":\""+content.type+
				    "\", \"limitPrice\":\""+content.limitPrice+
				    "\"}", 
				    contentType: 'application/json',
				    mimeType: 'application/json',
				    success: function(data) { 
						//success				
				    	alert(data);
				    	if(data.indexOf("!")<0) return;
				    	
						$("div [class='zclip']").remove();

						$("#couponDataGrid").createCoupon({
							company: content.company,
							availableRate: '0%',
							serialNum: content.serialNum,
							price: content.price,
							limitPrice: content.limitPrice,
							effectiveDate: content.validDate,
							type: content.type,
							messageNum:'0'
							});
							 $( "#coupon_"+content.serialNum ).hide();
						
							 $( "#coupon_"+content.serialNum ).animate({
			    					opacity: 1,
			    					left: "+=50",
			    					height: "toggle"
			  					}, 2000, function() {
			  						refreshClip();
			  				 });
							
							
							
							
				    },
				    error:function(data,status,er) { 
				        alert("error: "+data+" status: "+status+" er:"+er);
				    }
				});	

			
		};
		$.shareNewCoupon = shareNewCoupon;
		
		
		
		
		$.fn.searchCoupon = function(params){
				var content = $.extend({
					url:'',
					searchText: '',
				},params);
			
		};
		
		
		var reportUsed = function(params){
				var content = $.extend({
					url:'',
					serialNum:''
				},params);
				
				
				$.ajax({ 
				    url: content.url, 
				    type: 'POST', 
				    dataType: 'json', 
				    data: "{\"serialNum\":\""+content.serialNum+"\"}", 
				    contentType: 'application/json',
				    mimeType: 'application/json',
				    success: function(data) { 
						
						
						$("#report_"+content.serialNum).val("已使用");
						$("#report_"+content.serialNum).hide();
					 	$("#report_"+content.serialNum ).fadeIn("slow",function(){});
						$("#report_"+content.serialNum).attr("disabled",true);
				    },
				    error:function(data,status,er) { 
				        alert("error: "+data+" status: "+status+" er:"+er);
				    }
				});
				
				

		};// reportUsed-->	
		$.reportUsed = reportUsed;
		
		
		var voting = function(params){
				var content = $.extend({
					url:'',
					upOrDown: '',
					serialNum: ''

				},params);
				//alert(content.upOrDown+" - "+content.serialNum);
				$.ajax({ 
				    url: content.url, 
				    type: 'POST', 
				    dataType: 'json', 
				    data: "{\"serialNum\":\""+content.serialNum+"\", \"upOrDown\":\""+content.upOrDown+"\"}", 
				    contentType: 'application/json',
				    mimeType: 'application/json',
				    success: function(data) { 
				        $("#rate_"+content.serialNum).text(data);
						$("#rate_"+content.serialNum).hide();
					 	$("#rate_"+content.serialNum ).fadeIn("slow",function(){});
				    },
				    error:function(data,status,er) { 
				        alert("error: "+data+" status: "+status+" er:"+er);
				    }
				});
				
				

						
					
		};
		$.voting = voting;
				
	    var addMessage = function(params){
				var content = $.extend({
					url:'',
					message: '',
					serialNum: ''
				},params);
				$.ajax({ 
				    url: content.url, 
				    type: 'POST', 
				    dataType: 'json', 
				    data: "{\"serialNum\":\""+content.serialNum+"\", \"message\":\""+content.message+"\"}", 
				    contentType: 'application/json',
				    mimeType: 'application/json',
				    success: function(data) { 
						//success							
						$("#comment_"+content.serialNum).appendCouponMessage({message: content.message});					
						$("#comment_"+content.serialNum).hide();
					 	$("#comment_"+content.serialNum).fadeIn("slow");
					 	if($("#messageNum_"+content.serialNum).html().length<=0 )
					 		$("#messageNum_"+content.serialNum).html('1');
					 	else
					 		$("#messageNum_"+content.serialNum).html(parseInt($("#messageNum_"+content.serialNum).html())+1);
					 	
					 	
					 	
				    },
				    error:function(data,status,er) { 
				        alert("error: "+data+" status: "+status+" er:"+er);
				    }
				});

			
			
		};
		$.addMessage = addMessage;	
	
})(jQuery);