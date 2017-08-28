var Mgr={};
var to_url="";
try{
	to_url=window.location.pathname.substring(0,window.location.pathname.indexOf('/',1));

}catch( e){
	
}
if(to_url="/mall"){
	Mgr.rootUrl=to_url+"/";
	Mgr.mgrUrl=to_url+"/mgr/";
}else{
	Mgr.rootUrl="/";
	Mgr.mgrUrl="/mgr/";
}


