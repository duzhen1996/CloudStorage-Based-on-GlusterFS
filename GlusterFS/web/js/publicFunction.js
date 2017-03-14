/**
 * Created by DZ on 2016/1/23.
 */
function ipTranslate( a )
{
    //把a想成256位的数，转化为4个数字
    var part1 = a % 256;//这个是第一个数字
    a = (a - part1) / 256;//低位减去，然后位移

    var part2 = a % 256;//这个是第二个数字
    a = (a - part2) / 256;//再减去低位然后移位

    var part3 = a % 256;//这个是第三个数字
    a = ( a - part2 )/256;//减去低位然后移位

    var part4 = parseInt(a % 256);//这个是第四位数字

    return "" + part4 + "." + part3 + "." + part2 + "." + part1;
}

