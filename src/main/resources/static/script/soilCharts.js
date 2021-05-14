

    var districtSoil = document.getElementById("soilData").innerHTML;
    var ds = JSON.parse(districtSoil);
    console.log(ds);

    var green = "#66FFB2";
    var yellow = "#FF9933";
    var red = "#FF3333";
    var colourN = green;
    var colourP= green;
    var colourK = green;
    var colourpH = green;


    //automate assigning colours based on min max


    switch(true){
    case parseInt(ds.N) >=600:
        colourN = green;
        break;
    case parseInt(ds.N)>= 200 && parseInt(ds.N)< 600:
        colourN = yellow;
        break;
    case parseInt(ds.N) <200:
        colourN = red;
        break;
    }

    if (parseInt(ds.P)>=100){
    colourP = green;
    }
    if(parseInt(ds.P)<20){
    colourP = red;
    }
    if(parseInt(ds.P)>=20 && parseInt(ds.P)<100){
    colourP = yellow;
    }

        if (parseInt(ds.K)>=300){
        colourK = green;
        }
        if(parseInt(ds.K)<150){
        colourK = red;
        }
        if(parseInt(ds.K)>=150 && parseInt(ds.P)<300){
        colourK = yellow;
        }

    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(soilChart);
    function soilChart() {

        var data = google.visualization.arrayToDataTable([
          ['Element', 'Val', { role: 'style' }],
          ['nitrogen', parseInt(ds.N), colourN],
          ['phosphorous', parseInt(ds.P), colourP],
          ['potassium', parseInt(ds.K), colourK],
          ['pH', parseInt(ds.pH), colourpH]
              ]);

        var options = {
            width: 400,
            height: 400,
            title: 'Your soil is likely to contain the following properties',
            is3D : true
};

var testChart = new google.visualization.BarChart(document.getElementById('soilChart'));
        testChart.draw(data, options);
}