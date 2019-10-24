"use strict";
var auth = auth || {};
auth =(()=>{
	const WHEN_ERR = '호출하는 JS를 찾을수 없습니다.'
	let _, js,auth_vuejs;
	
	let init =()=>{
		_ = $.ctx();
		js= $.js();
		auth_vuejs = js+'/vue/auth_vue.js'
		
	}
	
	let onCreate=()=>{
		init()
		$.getScript(auth_vuejs)
		.done(()=>{
			setContentView()
			$('#a_go_join').click(e=>{
				e.preventDefault();
				join()
			})})
		.fail(()=>{alert(WHEN_ERR)})
	}
	let setContentView=()=>{
		login()
	}
	let join=()=>{
		$('head').html(auth_vue.join_head()),
		$('body').html(auth_vue.join_body())
		$('<button>',{
			text: 'continue to check',
			href: '#',
			click: e=>{
				e.preventDefault();
				let data = {uid:$('#userid').val(),pwd:$('#password').val(),uname:$('#uname').val()}
			
				$.ajax({
					url:_+'/users/',
					type: 'POST',
					dataType:'json',
					data : JSON.stringify(data),
					contentType : 'application/json',
					success : d =>{
						alert('에이작스 성공'+ d.uid+','+d.pwd+','+d.uname);	
						login()
					},
					error : e =>{
						alert('에이작스실패');
					}
				})
			}
		})
		.addClass('btn btn-primary btn-lg btn-block')
		.appendTo('#btn_join')		
	}
	let login=()=>{
		let x ={css:$.css(),img:$.img()}
		alert('x.css'+x.css)
		$('head').html(auth_vue.login_head(x)),
		$('body').html(auth_vue.login_body(x))
		.addClass('text-center')
		$('<button>',{
			type:"submit",
			text : "Sign in",
			click : e=>{
				e.preventDefault()
				let data = {uid:$('#uid').val(),pwd:$('#pwd').val(),uname:$('#uname').val()}

				$.ajax({
					url:_+'/users/',
					type: 'POST',
					dataType:'json',
					data:JSON.stringify(data),
					contentType : 'application/json',
					success : d=>{
						alert(d.uname+'님 환영합니다' +d.uid);	
						mypage(d)
					},
					error : e=>{
						alert('에이작스실패');
					}
					
				})	
			}
		}).addClass("btn btn-lg btn-primary btn-block")
		.appendTo('#btn_login')
	
		
	}
	let mypage=d=>{
		$('body').html(auth_vue.mypage_form(d))
		.addClass("d-flex flex-column h-100")
	}

	return{onCreate,join,login,mypage}
	
})();