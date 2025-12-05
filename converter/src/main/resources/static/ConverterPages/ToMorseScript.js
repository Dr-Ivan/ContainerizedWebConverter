const inp = document.getElementById("input_area");
const out = document.getElementById("output_area");


async function send(){
    to_convert = inp.value;
    console.log(to_convert);
    //response = await fetch("http://192.168.0.106:8080/convert/morseEncode", {
    response = await fetch("http://127.0.0.1:8080/convert/morseEncode", {
        method: 'POST',
        headers: {
            'Content-Type':'Application/json'
        },
        body: to_convert
    });

    out.value = await response.text();
}