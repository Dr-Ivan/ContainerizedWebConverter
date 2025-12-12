const inp = document.getElementById("input_area");
const out = document.getElementById("output_area");
const mode_select = document.getElementById("mode");

inp.addEventListener('input', function(){
    console.log('QWERTYUIOP');
    to_convert = inp.value;
    to_convert = to_convert.replaceAll('.', '•');
    to_convert = to_convert.replaceAll('-', '–');
    inp.value = to_convert;
});

async function decode(){
    to_convert = inp.value;
    console.log(to_convert);
    l = mode_select.value;
    console.log(l);
    //response = await fetch(`http://192.168.0.106:8080/convert/morseDecode/${l}`, {
    response = await fetch(`http://127.0.0.1:8080/convert/morseDecode/${l}`, {
        method: 'POST',
        headers: {
            'Content-Type':'Application/json'
        },
        body: to_convert
    });

    out.value = await response.text();
}