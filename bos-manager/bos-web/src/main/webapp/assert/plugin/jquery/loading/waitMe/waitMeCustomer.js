/**
 * Created by yu
 * on 2017/11/23.
 */


/**
 * 隐藏
 *
 * @param el_id
 */
function hidden_waitMe(el){
    el.waitMe('hide');
}

/**
 *显示
 *
 * @param le_id 元素id（容器）
 * @param effect 显示效果：
 *none
 * bounce
 * rotateplane
 * stretch
 * orbit
 * roundBounce
 * win8
 * win8_linear
 * ios
 * facebook
 * rotation
 * timer
 * pulse
 * progressBar
 * bouncePulse
 * img
 */
function show_waitMe(el , effect){
    run_waitMe(el , 1 , effect);
}


/**
 * 显示
 *
 * @param el
 * @param num
 * @param effect
 */
function run_waitMe(el, num, effect){
    text = '' ;//提示信息
    fontSize = '';
    switch (num) {
        case 1:
            maxSize = '';
            textPos = 'vertical';
            fontSize = '16px';
            break;
        case 2:
            text = '';
            maxSize = 30;
            textPos = 'vertical';
            break;
        case 3:
            maxSize = 30;
            textPos = 'horizontal';
            fontSize = '18px';
            break;
    }
    el.waitMe({
        effect: effect,
        text: text,
        bg: 'rgba(255,255,255,0.7)',
        color: '#169A56',
        maxSize: maxSize,
        waitTime: -1,
        source: 'img.svg',
        textPos: textPos,
        fontSize: fontSize,
        onClose: function(el) {}
    });
}

