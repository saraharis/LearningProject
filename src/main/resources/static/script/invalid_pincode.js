    function validate(){

    console.log("function enetred");

    var pincode = document.getElementById("pincode").value;
    var pattern = /^[1-9]{1}[0-9]{2}\s{0,1}[0-9]{3}$/;


    if(!pattern.test(pincode)){
        alert("Incorrect PinCode entered, Please try again :)");
        pincode.focus();
        console.log("yess");
        return false;
    }

    }

