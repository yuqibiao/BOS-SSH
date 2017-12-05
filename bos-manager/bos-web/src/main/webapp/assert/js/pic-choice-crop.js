/**
 * Created by yu
 * on 2017/12/5.
 */
jQuery(function ($) {
    $('#pic_choice').change(function(event) {//JCrop和图片预览存在冲突
        destoryJCrop();
        // 根据这个 <input> 获取文件的 HTML5 js对象
        var files = event.target.files, file;
        if (files && files.length > 0) {
            // 获取目前上传的文件
            file = files[0];
            // 获取window的 URL工具
            var URL = window.URL || window.webkitURL;
            // 通过 file生成目标 url
            var imgURL = URL.createObjectURL(file);
            $('#target').attr('src', imgURL);
            //图片选择完成后再设置JCrop
            initJCrop();
        }
    });

});
var isInitJCrop = false;
var jcrop_api;
function initJCrop(){
    var boundx;
    var boundy;
    $('#target').Jcrop({
        onChange: showInfo,
        aspectRatio: 1//框的比例
    }, function () {
        var bounds = this.getBounds();
        boundx = bounds[0];
        boundy = bounds[1];
        $("#boundx").val(boundx);
        $("#boundy").val(boundy);
        jcrop_api = this;
        jcrop_api.animateTo([100,100,296,296]);
    });
    function showInfo(c) {
        $("#x").val(c.x);
        $("#y").val(c.y);
        $("#x2").val(c.x2);
        $("#y2").val(c.y2);

        console.log("c.x："+c.x+" c.y："+c.y+" c.x2："+c.x2+"  c.y2："+c.y2
            +"  boundx："+boundx+"  boundy："+boundy);

    };
    isInitJCrop = true;
}
function destoryJCrop(){
    if(isInitJCrop){
        jcrop_api.destroy();
        isInitJCrop = false;
    }
}