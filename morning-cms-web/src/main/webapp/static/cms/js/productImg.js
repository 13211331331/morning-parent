/**
 * 进行格式转换
 */
function timeFormatter(value) {
	return new Date(value).Format("yyyy-MM-dd HH:mm:ss");
}

function picImgFormatter(value, row) {
    return '<a href="' + imagelocation + '/' + row.picImg + '" target="_blank" title="' + row.name + '">' + value + '</a>';
}

function labelIdFormatter(value) {
	if (value == 1){
        return '<span class="label label-danger">热销</span>'
	}
	else if (value == 2){
        return '<span class="label label-info">新品</span>'
	}
	else if (value == 3){
        return '<span class="label label-primary">现货</span>'
	}
	else if (value == 4){
        return '<span class="label label-warning">有赠品</span>'
	}
}

function showTypeFormatter(value) {
    if (value == 1){
        return '主要展示'
    }
    else if (value == 2){
        return '详情展示'
    }
}


function actionFormatter1(value, row, index) {
	if (row.status == 1) {
		return [
			'<a class="freeze1 m-r-sm text-info" href="javascript:void(0)" title="隐藏">',
			'<i class="glyphicon glyphicon-pause"></i>',
			'</a>',
			'<a class="edit1 m-r-sm text-warning" href="javascript:void(0)" title="编辑">',
			'<i class="glyphicon glyphicon-edit"></i>',
			'</a>',
			'<a class="remove1 m-r-sm text-warning" href="javascript:void(0)" title="删除">',
			'<i class="glyphicon glyphicon-remove"></i>',
			'</a>',

		].join('');
	} else {
		return [
			'<a class="normal1 m-r-sm text-info" href="javascript:void(0)" title="显示">',
			'<i class="glyphicon glyphicon-play"></i>',
			'</a>',
			'<a class="edit1 m-r-sm text-warning" href="javascript:void(0)" title="编辑">',
			'<i class="glyphicon glyphicon-edit"></i>',
			'</a>',
			'<a class="remove1 m-r-sm text-warning" href="javascript:void(0)" title="删除">',
			'<i class="glyphicon glyphicon-remove"></i>',
			'</a>',
		].join('');
	}
}


window.actionEvents = {
	'click .freeze1' : function(e, value, row, index) {
		status_stop1(index, row.picImgId);
	},
	'click .normal1' : function(e, value, row, index) {
		status_start1(index, row.picImgId);
	},
	'click .edit1' : function(e, value, row, index) {
		layer_show(row.name, baselocation + '/product/image/' + row.picImgId + '/edit', 900, 650)
	},
	'click .remove1' : function(e, value, row, index) {
		status_remove(index, row.picImgId);
	},

};

/**
 * 隐藏产品
 */
function status_stop1(index, value) {
	layer.confirm('确认要隐藏该图片吗？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		$.ajax({
			dataType : 'json',
			type : 'post',
			url : baselocation + '/product/image/' + value + '/audit',
			success : function(result) {
				if (result.code == 1) {
					$('#table').bootstrapTable('updateRow', {
						index : index,
						row : {
							status : 0,
						}
					});
					layer.msg('该图片隐藏成功!', {
						icon : 5,
						time : 1000
					});
				} else {
					layer.alert(result.message, {
						icon : 2
					});
				}
			}
		})
	});
}





/**
 * 隐藏产品
 */
function status_remove(index, value) {
	layer.confirm('确认要删除该图片吗？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		$.ajax({
			dataType : 'json',
			type : 'post',
			url : baselocation + '/product/image/' + value + '/del',
			success : function(result) {
				if (result.code == 1) {
					$('#table').bootstrapTable('updateRow', {
						index : index
					});
					layer.msg('该图片删除成功!', {
						icon : 5,
						time : 1000
					});
					window.location.reload(); // 刷新父页面
				} else {
					layer.alert(result.message, {
						icon : 2
					});
				}
			}
		})
	});
}




/**
 * 上架产品
 */
function status_start1(index, value) {
	layer.confirm('确认要显示该图片吗？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		$.ajax({
			dataType : 'json',
			type : 'post',
			url : baselocation + '/product/image/' + value + '/audit',
			success : function(result) {
				if (result.code == 1) {
					$('#table').bootstrapTable('updateRow', {
						index : index,
						row : {
							status : 1,
						}
					});
					layer.msg('该图片显示成功!', {
						icon : 6,
						time : 1000
					});
				} else {
					layer.alert(result.message, {
						icon : 2
					});
				}
			}
		})
	});
}


/**
 * 多选框插件
 */
$(document).ready(function() {
	$('input').iCheck({
		checkboxClass : 'icheckbox_flat-green',
		radioClass : 'iradio_flat-green'
	});
});

/**
 * 系统提示
 */
$(function() {
	$('.status-tip').on("click", function() {
		layer.tips('"显示" 代表此数据可用<br>"隐藏" 代表此数据不可用', '.status-tip');
	})
})


/**
 * 表单验证
 */
$(function() {
	$('#form').bootstrapValidator({
		container : 'tooltip',
		message : 'This value is not valid',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
            'name' : {
                message : '商品名称验证失败',
                validators : {
                    notEmpty : {
                        message : '商品名称不能为空'
                    },
                }
            },
			'showScore' : {
				message : '积分验证失败',
				validators : {
					notEmpty : {
						message : '积分不能为空'
					},
		            regexp: {
		                regexp: /^[0-9]*$/,
		                message: '积分只能为数字'
		            }
				}
			},
			'showPrice' : {
				message : '价钱验证失败',
				validators : {
					notEmpty : {
						message : '价钱不能为空'
					},
		            regexp: {
		                regexp: /^[0-9]*$/,
		                message: '价钱只能为数字'
		            }
				}
			},
            'paramNames' : {
                message : '参数名称验证失败',
                validators : {
                    notEmpty : {
                        message : '参数名称不能为空'
                    },
                }
            },
            'paramValues' : {
                message : '参数值验证失败',
                validators : {
                    notEmpty : {
                        message : '参数值不能为空'
                    },
                }
            },
		}
	})
		.on('success.form.bv', function(e) {
			// Prevent form submission
			e.preventDefault();

			// Get the form instance
			var $form = $(e.target);
            var params = '';
            params += $form.serialize();
			var action = $form.attr('action');
			// Use Ajax to submit form data
			if (action.indexOf('edit') !=-1) {
				$.ajax({
					data : params,
					dataType : 'json',
					type : 'post',
					url : $form.attr('action'),
					success : function(result) {
						if (result.code == 1) {
							parent.layer.msg("更新成功!", {
								shade : 0.3,
								time : 1500
							}, function() {
								window.parent.location.reload(); // 刷新父页面
							});
						} else {
							layer.msg(result.message, {
								icon : 2,
								time : 1000
							});
						}
					}
				})
			} else if (action.indexOf('create') !=-1) {
				$.ajax({
					data : $form.serialize(),
					dataType : 'json',
					type : 'post',
					url : $form.attr('action'),
					success : function(result) {
						if (result.code == 1) {
							parent.layer.msg("创建成功!", {
								shade : 0.3,
								time : 1500
							}, function() {
								window.parent.location.reload(); // 刷新父页面
							});
						} else {
							layer.msg(result.message, {
								icon : 2,
								time : 1000
							});
						}
					}
				})
			}
		});
})

/**
 * 查看按钮
 */
$(function() {
    $('.view-button').on("click", function() {
        if ($('input[name="picImg"]').val() != null && $('input[name="picImg"]').val() != "") {
            window.open(imagelocation + '/' + $('input[name="picImg"]').val());
        }
    })
})

/**
 * 图片上传
 */
$(function() {
    $('.upload-button').on("click", function() {
        var formData = new FormData();
        formData.append('advert_file', $('input[type="file"]')[0].files[0]);
        $.ajax({
            url : baselocation + '/uploads/advert',
            type : 'post',
            cache : false,
            data : formData,
            processData : false, //因为data值是FormData对象,不需要对数据做处理
            contentType : false, //因为是由<form>表单构造的FormData对象,且已经声明了属性enctype="multipart/form-data",所以这里设置为false
            success : function(result) {
                if (result.code == 1) {
                    parent.layer.msg("图片上传成功!", {
                        shade : 0.3,
                        time : 1500
                    });
                    $('input[name="picImg"]').val(result.data);
                    $('.view-button').show();
                } else {
                    layer.msg(result.message, {
                        icon : 2,
                        time : 1000
                    });
                }
            }
        });

    })
})

var elems = Array.prototype.slice.call(document.querySelectorAll('.js-switch'));

elems.forEach(function(html) {
    var switchery = new Switchery(html, {
        size : 'small'
    });
});

/**
 * 初始化菜单树
 */
var ztreeObject;
var setting = {
    data : {
        simpleData : {
            enable : true,
            idKey : "categoryId",
            pIdKey : "parentId",
            rootPId : 0
        },
        key : {
            name : 'name',
            title : 'name'
        }
    },
    check : {
        enable : true,
        chkboxType:  { "Y": "", "N": "" },
		chkStyle:"radio",
        radioType : "all"
    }
};


function deleteParamInput(index){
    $("#"+index).remove();
    $("div.paramFormGroup:first > label").html("商品参数：");
}
function addParamInput() {
    var id=$("div.paramFormGroup").length-1;
    var html="<div class=\"form-group m-t paramFormGroup\" id=\""+id+"\">" +
        "                  <label class=\"col-sm-2 col-xs-offset-1 control-label\"></label>" +
        "                  <div class=\"col-sm-2\">" +
        "                    <input type=\"text\" class=\"form-control\" name=\"paramNames\">" +
        "                  </div>" +
        "                  <div class=\"col-sm-5\">" +
        "                    <input type=\"text\" class=\"form-control\" name=\"paramValues\">" +
        "                  </div>" +
        "                  <div class=\"col-sm-1\">" +
        "                    <button type=\"button\" class=\"btn btn-default\" onclick=\"deleteParamInput("+id+")\"><i class=\"glyphicon glyphicon-minus\"></i></button>" +
        "                  </div>" +
        "                </div>";
    $("div.paramFormGroup:last").before(html);
    $("div.paramFormGroup > label").html("");
    $("div.paramFormGroup:first > label").html("商品参数：");
}