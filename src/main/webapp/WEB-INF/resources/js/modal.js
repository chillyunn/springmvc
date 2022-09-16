$(function (){
    $("#confirm").on("click",function (){
        console.log('확인 클릭됨');
        const text=$("#value").val();
        console.log(text);

        $("#result").attr('value',text);
    })
})
$(function (){
    $("#transmit").on("click",function (){
        console.log('전송 클릭됨');
        const text=$("#result").val();
        console.log('전송할 값: '+ text);
        $.ajax({
            type:"POST",
            url:"/modal",

            data:{result: text},
            dataType:"text"
        });
    })
})