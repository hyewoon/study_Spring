<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#tabs { display: flex; border-bottom: 1px solid #3367d6; }
#tabs li {
	width: 140px;  line-height: 40px;  color:#3367d6; cursor: pointer;
	border: 1px solid #3367d6; border-bottom: none; margin-right: 0;
}
#tabs li:not(:first-child) { border-left: none; }
#tabs li.active { background-color:#3367d6; color: #fff;}
#tab-content { width: 1200px; height: 550px; margin: 20px auto; } 

.c3-chart-arc text, .c3-axis, .c3-chart { font-size: 16px !important }

#legend { display: none; justify-content: center; }
#legend li { display: flex; align-items: center; }
#legend li:not(:last-child) { margin-right: 30px }
.legend { width:15px; height: 15px; margin-top: 5px; margin-right: 5px }
	
 
</style>
<link rel="stylesheet" href="css/yearpicker.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/c3/0.7.20/c3.css"  />
<script src="js/yearpicker.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.7.20/c3.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/5.16.0/d3.min.js"></script>
<script>
$(function(){
	$('#tabs li').on('click', function(){
		if( $(this).hasClass('active') ) return;  //현재 선택된 탭인 경우
		$('#tabs li').removeClass();
		$(this).addClass('active');
		
		var idx = $(this).index();
		$('.tab').css('display', 'none');
		$('.tab').eq(idx).css('display', 'block');
		
		if( idx==0 ) department();
		else         hirement();
	});
	
	//클릭이벤트 강제발생
	$('#tabs li').eq(1).trigger('click');
	$('.legend').each(function(idx){
		$(this).css('background-color', colors[idx]);
	});
	
	$('[name=unit], #top3').change(function(){
		hirement();
	});
})



//채용인원수(년도별/월별) 시각화
function hirement(){
	init();
	
	if( $('#top3').prop('checked') ) hirement_top3();
	else  hirement_company();
}

function hirement_top3(){
	var unit = $('[name=unit]:checked').val();
	$.ajax({
		url: 'visual/hirement/top3/' + unit,
		success: function( response ){
			console.log( response );
			var info = [];
			if( unit=='year' ){
				info.push( ['부서명', '2001', '2002', '2003', '2004', '2005', '2006'
							, '2007', '2008', '2009', '2010']  );
				$(response).each(function(){
					info.push( new Array( this.DEPARTMENT_NAME, this.Y2001, this.Y2002
							, this.Y2003, this.Y2004, this.Y2005, this.Y2006
							, this.Y2007, this.Y2008, this.Y2009, this.Y2010) );
				});
			}else{
				info.push( ['부서명', '01', '02', '03', '04', '05', '06', '07'
							, '08', '09', '10', '12', '12'] );
				$(response).each(function(){
					info.push( [ this.DEPARTMENT_NAME, this.M01, this.M02, this.M03
							, this.M04, this.M05, this.M06, this.M07, this.M08
							, this.M09, this.M10, this.M11, this.M12] );
				});
			}
			
			c3.generate({
				bindto: '#graph',
				data: { columns: info, x:'부서명', type: unit=='year'? 'bar' : 'line', labels:true },
				axis: { x: { type:'category', 
						tick: { 
							format: function(d){
								//console.log( info[0][d+1], d )
								return info[0][d+1] + (unit=='year'? '년' : '월' );
							} 
						} 
				}, 
					y:{ 
						label:{
							text: (unit=='year'? '년도별 ' : '월별 ') + '채용인원수',
							position:'outer-top'} } },
				padding: { bottom: 50 },
				grid: { y: { show:true }},
				size: { height: 450 },
				legend: {
					item: { tile: { width:15, height:15 } },
					padding: 40,
				}
			});
			
			$('.c3-legend-item').css('font-size', '16px');
			$('.c3-line').css('stroke-width', '3px');
			
		}
	});
}

function hirement_company(){
	var unit = $('[name=unit]:checked').val();
	$.ajax({
		url: 'visual/hirement/' + unit,
		success: function( response ){
			//console.log( response )
			var name = [ unit ], count = [ '채용인원수' ];
			$(response).each(function(){
				name.push( this.UNIT );
				count.push( this.COUNT );
			});
			make_chart_hirement( [name, count] );
			
		},error: function(req,text){
			alert(text+':'+req.status);
		}
	});
}


function make_chart_hirement(info){
	c3.generate({
		bindto: '#graph',
		data: { columns: info, type: 'bar', labels: true,
			x: $('[name=unit]:checked').val(),	
			color: function(c, d){ return colors[ Math.floor(d.value/10) ]; },
		},
		axis: {
			x: { type: 'category' },
			y: { label: {text: info[1][0], position:'outer-top' }  }
		},
		bar: { width:30 },
		size: { height:450 },
		grid: { y: {show:true} },
		legend: { hide:true },
	});
	$('#legend').css('display', 'flex');
}

//부서원수 시각화
function department(){
	init();
	$.ajax({
		url: 'visual/department',
		success: function(response){
			console.log(response)
			var count = [ '부서원수' ], name = [ '부서명' ], info = [];
			$(response).each(function(){
				count.push( this.COUNT )
				//['부서원수', 30, 200, 100, 400, 150, 250]
				name.push( this.DEPARTMENT_NAME );
				//['부서명', 'www.site1.com', 'www.site2.com', 'www.site3.com', 'www.site4.com'],
				info.push( new Array( this.DEPARTMENT_NAME, this.COUNT ) );
				// 	 ['data1', 30],
				//   ['data2', 120],

			});
			console.log('info', info)
// 			make_chart( new Array( name, count ) );
			//make_chart( [ name, count ] );  //선/막대그래프
			//make_chart( info ); //파이/도넛그래프
			if( $('[name=graph]:checked').val()=='bar' ){
				bar_chart( [ name, count ] );
			}else{
				donut_chart( info );
			}
		}
	});

}

function make_chart(info){
	//1.선그래프-기본
	//line_chart(info);
	//2.파이그래프
	//pie_chart(info);
	//3.도넛그래프
	//donut_chart(info);
	//4.막대그래프
	bar_chart(info);
}

function bar_chart(info){
	c3.generate({
		bindto: '#graph',
		data :  { columns: info, type: 'bar', x: '부서명', labels:true,
			color: function(color, data){
				//return colors[ data.index ];
				return colors[ Math.floor(data.value/10) ];
			}
		},
		axis: {
			x: { type:'category', tick: { rotate:-60 } },
			y: { label: { text:info[1][0], position:'outer-middle' } }
		},
		size: { height:450 },
		bar: { width:30 },
		grid: { y: { show:true } },
		legend: { hide:true },
	});
	
	$('#legend').css('display', 'flex');
}

var colors = [ '#009628', '#d64f15', '#f5dd02', '#026ff5', '#9402f5'
	, '#f5025f', '#02e1f5', '#1202f5', '#753f01', '#a6f29d', '#f2a2ac', '#ae83f2' ];


function donut_chart(info){
	c3.generate({
		bindto: '#graph',
		data: { columns: info, type:'donut' },
		size: { height:450 },
		padding: { bottom:50 },
		donut: {
			label: {
				format:function(v, r, id){
					return (r*100).toFixed(1) + '%('+ v + '명)';
				}
			},
			width: 100,
			title: '부서원수',
		}
	});
}

function pie_chart(info){
	c3.generate({
		bindto: '#graph',
		data: {  columns: info,  type: 'pie', },
		size: { height: 450 },
		pie: {
			label: { 
				format: function(value, ratio, id){
					return (ratio*100).toFixed(1) + '%('+ value +'명)'; //31.5% -> 0.315
				}
			}
		},
		padding: { bottom: 50 },
	});
}

function line_chart(info){
	var chart = c3.generate({
		bindto: '#graph',
	    data: {
	        //columns: [ ['부서원수', 30, 200, 100, 400, 150, 250] ]
			columns: info,
			x: '부서명',
	    },
	    axis: {
	        x: {
	            type: 'category' 
	        }
	    }
	});
}
</script>
</head>
<body>
<h3>시각화</h3>
<div class='w-px1200' style="margin: 0 auto">
	<ul id='tabs'>
		<li>부서원수</li>
		<li>채용인원수</li>
	</ul>
</div>
<div id='tab-content'>
	<div class='tab'>
		<label><input type='radio' name='graph' value='bar' checked>막대그래프</label>
		<label><input type='radio' name='graph' value='donut'>도넛그래프</label>
	</div>
	<div class='tab'>
		<label><input type='checkbox' id='top3'>TOP3부서</label>
		<label><input type='radio' name='unit' value='year' checked>년도별</label>
		<label><input type='radio' name='unit' value='month'>월별</label>
		<label><input type='text' id='begin' class='year w-px80' readonly>
				~<input type='text' id='end' class='year w-px80' readonly>
		</label>
	</div>
	<div id='graph'></div> <!-- 그래프표현할 부분 -->
	<ul id='legend'>
		<li><span class='legend'></span><span>0~9명</span></li>
		<li><span class='legend'></span><span>10~19명</span></li>
		<li><span class='legend'></span><span>20~29명</span></li>
		<li><span class='legend'></span><span>30~39명</span></li>
		<li><span class='legend'></span><span>40~49명</span></li>
		<li><span class='legend'></span><span>50~59명</span></li>
		<li><span class='legend'></span><span>60명이상</span></li>
	</ul>
</div>
<script>
function init(){
	$('#graph').empty();
	$('#legend').css('display', 'none');
}

$('[name=graph]').on('change', function(){
	department();	
});

</script>
</body>
</html>