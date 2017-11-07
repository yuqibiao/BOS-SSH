/**
 * 字符串工具类
 * Created by yu
 * on 2017/11/7.
 */

/**
 * 判断输入字符串是否为空或者全部都是空格
 * @param str
 * @returns {boolean}
 */
function isEmpty( str ){
    if ( str == "" ) return true;
    if ( str == "undefined" ) return true;
    var regu = "^[ ]+$";
    var re = new RegExp(regu);
    return re.test(str);
}
