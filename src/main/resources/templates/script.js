console.log('dedicated js file');
alert('hi');
var firstItem = document.getElementById("item-1"),
    myList = document.getElementsByClassName("mylist"),
    paragraphs = document.getElementsByTagName("p"),
    images = document.querySelectorAll("img"),
    containers = document.querySelectorAll(".container");

for(i = 0; i< myList.length; i++){
myList[i].style.color = "green";
}

containers[0].addEventListener("mouseenter", mouseEnterContainer);

function mouseEnterContainer(){
console.log("mouse has entered");
}

document.addEventListener("keyup", function(event){
    console.log(event.keyCode);
});