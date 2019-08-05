var websocket = null;

function initSocket(userName, regular) {
	if ('WebSocket' in window) {
	    websocket = new WebSocket("ws://" + getRootPath() + "/websocket/" + userName + "/" + regular);
	} else {
	    $.messager.alert('提示', '当前浏览器 Not support websocket', 'error');
	}
}

function sendMessage(messageType, data) {
	var message = {};
	message.messageType = messageType;
	message.message = data;
	websocket.send(JSON.stringify(message));
}
	
$(function() {
	websocket.onmessage = function (event) {
	    console.info(event.data);
	}
	
	window.onbeforeunload = function () {
		websocket.close();
	}
});

function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    
    if (webName == "") {
        return window.location.host;
    } else {
        return window.location.host + '/' + webName;
    }
}