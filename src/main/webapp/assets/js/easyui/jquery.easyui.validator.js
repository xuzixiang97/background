$.extend($.fn.validatebox.defaults.rules, {
	isValid: {
		validator: function(text, param) {
			var value = $('#' + param[0]).combobox('getValue');
			var datas = $('#' + param[0]).combobox('getData');
			var valueField = $('#' + param[0]).combobox("options").valueField;
			var array = [];
			
			for (var i = 0; i < datas.length; i++) {
        		array.push(datas[i][valueField].toString());
            }
            
            if ($.inArray(value, array) >= 0) {
            	return true;
            }
            return false;
		},
		message: "{1}"
	},
	equals: {
		validator: function(value, param) {
			return $(param[0]).val()==value;
		},
		message: "{1}"
	},
	telephone: {
        validator: function(value, param) {
        	return /^(\d{3}-|\d{4}-)?(\d{8}|\d{7})?(-\d{1,6})?$/.test(value);
        },
        message: "{1}"
    },
    cellphone: {
        validator: function(value, param) {
        	return /^(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$/.test(value);
        },
        message: "{1}"
    },
    phone: {
    	validator: function(value, param) {
        	return /^(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$|^(\d{3}-|\d{4}-)?(\d{8}|\d{7})?(-\d{1,6})?$/.test(value);
        },
        message: "{1}"
    },
    idCard: {
	    validator: function(value, param) {
	      return /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/.test(value);
	    },
	    message: "{1}"
	},
	ip: {
        validator: function(value, param) {
            return /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/.test(value);
        },
        message: "{1}"
    },
    mac: {
        validator: function(value, param) {
            return /^[A-Fa-f\d]{2}-[A-Fa-f\d]{2}-[A-Fa-f\d]{2}-[A-Fa-f\d]{2}-[A-Fa-f\d]{2}-[A-Fa-f\d]{2}$/.test(value);
        },
        message: "{1}"
    },
    cpu: {
        validator: function(value, param) {
            return /^[A-Fa-f\d]{16}$/.test(value);
        },
        message: "{1}"
    },
    zip: {
    	validator: function(value, param) {
    		return /^[0-9]\d{5}$/.test(value);
    	},
    	message: "{1}"
    },
    qq: {
    	validator: function(value, param) {
    		return /^[1-9]\d{4,10}$/.test(value);
    	},
    	message: "{1}"
    },
    email : {
        validator : function(value) {
            var reg =  /^([A-Za-z0-9_\-\.])+\@(163.com|qq.com|42du.cn)$/;
            return reg.test(value);
        },
        message : "请正确填写您的邮箱！"
    },
    date: {
    	validator: function(value, param) {
    		return /^(?:(?!0000)[0-9]{4}([-]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-]?)0?2\2(?:29))$/.test(value);
    	},
    	message: "{1}"
    },
    dateTime: {
    	validator: function(value, param) {
    		return /^(?:(?!0000)[0-9]{4}([-]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-]?)0?2\2(?:29)) ([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$/.test(value);
    	},
    	message: "{1}"
    },
    isString: {
    	validator: function(value, param) {
    		return /^[a-zA-Z0-9]+$/.test(value);
    	},
    	message: "{1}"
    },
    isChinese: {
    	validator: function(value, param) {
    		return /[\u4E00-\u9FA5\uF900-\uFA2D]/.test(value);
    	},
    	message: "{1}"
    },
    compare: {
		validator: function(value, param) {
			if (param[0] == 0) {
				var data = $(param[1]).val();
				return (data == null || data == "" || value <= data) ? true : false;
			}else if (param[0] == 1) {
				var data = $(param[1]).val();
				return (data == null || data == "" || data <= value) ? true : false;
			}
			return false;
		},
		message: "{1}"
    },
    maxLength: {
        validator: function(value, param){
            return value.length <= param[0];
        },
        message: "长度超过限制"
    },
    unique: {
		validator: function(value, param) {
			var params = {};
			for (var i = 1; i < param.length; i++) {
				if (i == 1) {
					params[param[i]] = value;
				} else {
					params[param[i]] = $('#' + param[i]).val();
				}
			}
			
			var result = $.ajax({url: param[0], dataType: "json", type: "post", async: false, cache: false, data: params}).responseText;;
			return result=="true";
		},
		message: "{1}"
	}
});