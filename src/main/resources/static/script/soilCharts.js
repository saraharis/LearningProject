

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
    var annotK;
    var annotP;
    var annotN;


    //TODO: automate assigning colours based on min max


    switch(true){
    case parseInt(ds.N) >=600:
        colourN = green;
        annotN = "high";
        break;
    case parseInt(ds.N)>= 200 && parseInt(ds.N)< 600:
        colourN = yellow;
        annotN = "medium";
        break;
    case parseInt(ds.N) <200:
        colourN = red;
        annotN = "low";
        break;
    }

    if (parseInt(ds.P)>=100){
    colourP = green;
    annotP = "high";
    }
    if(parseInt(ds.P)<20){
    colourP = red;
    annotP = "low";
    }
    if(parseInt(ds.P)>=20 && parseInt(ds.P)<100){
    colourP = yellow;
    annotP = "medium";
    }

        if (parseInt(ds.K)>=300){
        colourK = green;
        annotK = "high";
        }
        if(parseInt(ds.K)<150){
        colourK = red;
        annotK = "low";
        }
        if(parseInt(ds.K)>=150 && parseInt(ds.P)<300){
        colourK = yellow;
        annotK = "medium";
        }

//TODO: add tooltip text style


    google.charts.load('current', {'packages':['corechart']});


    google.charts.setOnLoadCallback(soilChart);
    function soilChart() {

        var data = google.visualization.arrayToDataTable([
          ['Element', 'Val', { role: 'style' }, { role: 'annotation' } ],
          ['nitrogen', parseInt(ds.N), colourN, annotN],
          ['phosphorous', parseInt(ds.P), colourP, annotP],
          ['potassium', parseInt(ds.K), colourK, annotK],
        ]);

        var options = {

            title: 'Soil',
            bar: {groupWidth: "95%"},
            backgroundColor: 'black',
            titleTextStyle: { color: 'white',
                              fontName: 'raleway',
                              fontSize: '20',
                              bold: 'true',
                              italic: 'false'},
            legend: { position: "none" },
            annotations: {
                alwaysOutside : true,
                highContrast : false,
                textStyle: {

                  fontSize: 15,
                  bold: false,
                  color: 'white',

                }

              },

              hAxis: {
              color : 'white',
                textStyle: {
                  color: 'white'
                }

              },
              vAxis: {
                  gridlines: {
                      color: 'transparent'
                  }
              }
};

var testChart = new google.visualization.ColumnChart(document.getElementById('nutrientChart'));
        testChart.draw(data, options);
}

    google.charts.setOnLoadCallback(phChart);
    function phChart(){

    var data = google.visualization.arrayToDataTable([
              ['Element', 'Val', { role: 'style' }, { role: 'annotation' } ],
              ['pH', parseInt(ds.pH), colourpH, 'acidic']
                  ]);

    var options = {

                title: 'Soil',
                bar: {groupWidth: "30%"},
                backgroundColor: 'black',
                titleTextStyle: { color: 'white',
                                  fontName: 'raleway',
                                  fontSize: '20',
                                  bold: 'true',
                                  italic: 'false'},
                legend: { position: "none" },
                annotations: {
                    alwaysOutside : true,
                    highContrast : false,
                    textStyle: {

                      fontSize: 15,
                      bold: false,

                      // The color of the text.
                      color: 'white',
                    }
                    },
                  hAxis: {
                  color : 'white',
                    textStyle: {
                      color: 'white'
                    }
                  },
                  vAxis: {
                      gridlines: {
                          color: 'transparent'
                      }
                  }
    };


    var phChart = new google.visualization.ColumnChart(document.getElementById('phChart'));
            phChart.draw(data, options);




    }

